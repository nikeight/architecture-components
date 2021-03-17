package com.appchef.arch_example;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
// Entity helps us to avoid writing tha boiler plate code and
// at the same time create a SQL table for us.

public class Note {
    // data variables.

    @PrimaryKey(autoGenerate = true)
    // This will generate the automatic id and add in the queue
    // to give the unique ID to every table's column
    private int id;

    private String title;

    private String description;

    private int priority;

    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
