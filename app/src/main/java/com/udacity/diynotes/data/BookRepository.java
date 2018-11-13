package com.udacity.diynotes.data;


import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.udacity.diynotes.AppExecutors;
import com.udacity.diynotes.data.database.BookDao;
import com.udacity.diynotes.data.database.NoteEntry;

import java.util.List;

public class BookRepository {
    private static final String TAG = BookRepository.class.getSimpleName();

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static BookRepository sInstance;
    private final BookDao mBookDao;
    private final AppExecutors mExecutors;
    private boolean mInitialized = false;

    private BookRepository(BookDao bookDao, AppExecutors executors) {
        mBookDao = bookDao;
        mExecutors = executors;
    }

    public synchronized static BookRepository getInstance(BookDao bookDao, AppExecutors executors) {
        Log.d(TAG, "getInstance: Getting the repository");

        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new BookRepository(bookDao, executors);
                Log.d(TAG, "getInstance: Made new repository");
            }
        }
        return sInstance;
    }

    private synchronized void initializeData() {
        if (mInitialized) return;
        mInitialized = true;

        // (todo) Do something
        return;
    }

    // Database related operations
    public LiveData<List<String>> getAllBook() {
        return mBookDao.getAllBook();
    }


    public LiveData<List<String>> getNotesFromABook(String book) {
        return mBookDao.getNotesFromABook(book);
    }

    public void insertBook(NoteEntry entry) {
        mBookDao.insertBookNote(entry);
    }
}