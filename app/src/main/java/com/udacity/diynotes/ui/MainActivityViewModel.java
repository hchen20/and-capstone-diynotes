package com.udacity.diynotes.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.udacity.diynotes.data.BookRepository;
import com.udacity.diynotes.data.database.NoteEntry;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private final BookRepository mRepository;
    private final LiveData<List<String>> mBooks;

    public MainActivityViewModel(BookRepository repo) {
        mRepository = repo;
        mBooks = mRepository.getAllBook();
    }

    public void insertBook(NoteEntry entry) {
        mRepository.insertBook(entry);
    }

    public LiveData<List<String>> getBooks() {
        return mBooks;
    }
}
