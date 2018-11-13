package com.udacity.diynotes.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

// Book Dao provides an api for all data operations with the BookDatabase
@Dao
public interface BookDao {

    // This method retrieves all books for MainActivity display
    @Query("Select DISTINCT  bookName FROM book")
    LiveData<List<String>> getAllBook();

    // This method retrieves all notes from a book for NotesActivity display
    @Query("Select bookNote FROM book WHERE bookName = :book")
    LiveData<List<String>> getNotesFromABook(String book);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertBookNote(NoteEntry entry);
}
