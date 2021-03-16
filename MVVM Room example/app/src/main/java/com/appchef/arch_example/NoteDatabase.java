package com.appchef.arch_example;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public abstract class NoteDatabase extends RoomDatabase {

    // Singleton class, only one object can be created.
    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    // To avoid multi threading operation to single database.
    public static synchronized NoteDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }

}
