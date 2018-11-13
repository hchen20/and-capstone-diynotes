package com.udacity.diynotes.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.udacity.diynotes.data.BookRepository;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final BookRepository mRepository;

    public MainViewModelFactory(BookRepository repo) {
        this.mRepository = repo;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        // noinspection unchecked
        return (T) new MainActivityViewModel(mRepository);
    }
}
