package com.appchef.arch_example;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;

    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        // Room will create the essential body for the NoteDao abstract method.
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    // We don't have to handle the background threads of the
    // Live Data method (maintained by Room)

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }

    // For rest we have to maintain the background threads.
    public void insert(Note note){
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    public void update(Note note){
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    public  void delete(Note note){
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    public void deleteAllNotes(){
        new DeleteAllNoteAsyncTask(noteDao).execute();
    }

    private static  class InsertNoteAsyncTask extends AsyncTask<Note,Void,Void> {

        private NoteDao noteDao;

        // to hit static variables we create a constructor
        private  InsertNoteAsyncTask(NoteDao noteDao){
            this.noteDao  = noteDao;
        }

        // This method is only mandatory, rest are optional
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static  class UpdateNoteAsyncTask extends AsyncTask<Note,Void,Void> {

        private NoteDao noteDao;

        // to hit static variables we create a constructor
        private  UpdateNoteAsyncTask(NoteDao noteDao){
            this.noteDao  = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static  class DeleteNoteAsyncTask extends AsyncTask<Note,Void,Void> {

        private NoteDao noteDao;

        // to hit static variables we create a constructor
        private  DeleteNoteAsyncTask(NoteDao noteDao){
            this.noteDao  = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static  class DeleteAllNoteAsyncTask extends AsyncTask<Note,Void,Void> {

        private NoteDao noteDao;

        // to hit static variables we create a constructor
        private  DeleteAllNoteAsyncTask(NoteDao noteDao){
            this.noteDao  = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.deleteAll();
            return null;
        }
    }
}
