package com.udacity.diynotes.utils;

import android.content.Context;

import com.udacity.diynotes.AppExecutors;
import com.udacity.diynotes.data.BookRepository;
import com.udacity.diynotes.data.database.BookDatabase;
import com.udacity.diynotes.ui.MainViewModelFactory;

public class InjectorUtils {

    public static BookRepository provideRepository(Context context) {
        BookDatabase database = BookDatabase.getInstance(context);
        AppExecutors executors = AppExecutors.getInstance();

        return BookRepository.getInstance(database.bookDao(), executors);
    }

    public static MainViewModelFactory provideMainActivityViewModelFactory(Context context) {
        BookRepository repository = provideRepository(context.getApplicationContext());
        return new MainViewModelFactory(repository);
    }
}
