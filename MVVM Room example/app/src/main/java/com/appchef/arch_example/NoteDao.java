package com.appchef.arch_example;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface NoteDao {

    // For every Entity we create a specific Dao interface

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);
    // We can pass many arguments here ex: a list , multiple components etc.

    // when we want custom query
    @Query("DELETE FROM note_table")
    void deleteAll();

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();
    // Live Data gives result at the real time to the activity. Handled by Room Library.  
}
