package com.udacity.diynotes.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.udacity.diynotes.data.BookRepository;

public class DetailedNoteViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final BookRepository mRepository;
    private String mBookName;

    public DetailedNoteViewModelFactory(BookRepository repo, String bookName) {
        this.mRepository = repo;
        this.mBookName = bookName;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        // noinspection unchecked
        return (T) new DetailedNoteViewModel(mRepository, mBookName);
    }
}
