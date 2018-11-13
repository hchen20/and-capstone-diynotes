package com.udacity.diynotes.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

@Database(entities = {NoteEntry.class}, version = 1)
public abstract class BookDatabase extends RoomDatabase {
    private static final String TAG = BookDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "book";

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static BookDatabase sInstance;

    public static BookDatabase getInstance(Context context) {
        Log.d(TAG, "getInstance: Getting the database");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        BookDatabase.class,
                        BookDatabase.DATABASE_NAME).build();
                Log.d(TAG, "getInstance: Made new database");
            }
        }
        return sInstance;
    }

    // The associated DAOs for the database
    public abstract BookDao bookDao();
}
