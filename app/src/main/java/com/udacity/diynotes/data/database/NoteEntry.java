package com.udacity.diynotes.data.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "book")
public class NoteEntry {

    // id is the auto generated by {@link Room} primary key
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String bookName;
    private String bookNote;

    @Ignore
    public NoteEntry(String book, String note) {
        this.bookName = book;
        this.bookNote = note;
    }

    // Constructor used by Room to create NoteEntry
    public NoteEntry(int id, String bookName, String bookNote) {
        this.id = id;
        this.bookName = bookName;
        this.bookNote = bookNote;
    }

    public int getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookNote() {
        return bookNote;
    }
}
