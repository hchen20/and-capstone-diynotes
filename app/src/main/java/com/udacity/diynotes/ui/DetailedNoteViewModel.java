package com.udacity.diynotes.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.udacity.diynotes.data.BookRepository;
import com.udacity.diynotes.data.database.NoteEntry;

import java.util.List;

public class DetailedNoteViewModel extends ViewModel {
    private final BookRepository mRepository;
    private final LiveData<List<String>> mNotes;

    public DetailedNoteViewModel(BookRepository repo, String bookName) {
        mRepository = repo;
        mNotes = mRepository.getNotesFromABook(bookName);
    }

    public void insertBook(NoteEntry entry) {
        mRepository.insertBook(entry);
    }

    public LiveData<List<String>> getBooks() {
        return mNotes;
    }

}
