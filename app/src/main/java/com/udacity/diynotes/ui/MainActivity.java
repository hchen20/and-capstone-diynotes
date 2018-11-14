package com.udacity.diynotes.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.udacity.diynotes.AppExecutors;
import com.udacity.diynotes.R;
import com.udacity.diynotes.R2;
import com.udacity.diynotes.data.database.NoteEntry;
import com.udacity.diynotes.utils.InjectorUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.google.firebase.analytics.FirebaseAnalytics.Param;

public class MainActivity extends AppCompatActivity  {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R2.id.book_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R2.id.add_book_fab)
    FloatingActionButton mAddBook;

    @BindView(R.id.adView)
    AdView mAdView;

    private BookListAdapter mBookListAdapter;
    private MainActivityViewModel mViewModel;
    private int mPosition = RecyclerView.NO_POSITION;

    private final AppExecutors mExecutor = AppExecutors.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        MobileAds.initialize(this, getString(R.string.admob_app_id));
        // Create an ad request

        AdRequest adRequest = new AdRequest.Builder()
                                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                                .build();
        mAdView.loadAd(adRequest);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                                                    LinearLayoutManager.VERTICAL,
                                        false);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);

        mBookListAdapter = new BookListAdapter(this);
        mRecyclerView.setAdapter(mBookListAdapter);
        MainViewModelFactory factory = InjectorUtils.provideMainActivityViewModelFactory(this.getApplicationContext());
        mViewModel = ViewModelProviders.of(this,factory).get(MainActivityViewModel.class);

        // Insert a place holder book to prevent startup crash
        if (mViewModel.getBooks() == null) {
            mExecutor.diskIO().execute(() -> {
                mViewModel.insertBook(new NoteEntry("Adventure", "Start"));

            });
        }

        mViewModel.getBooks().observe(this, bookEntries -> {
            mBookListAdapter.swapBooks(bookEntries);
            if (mPosition == RecyclerView.NO_POSITION) mPosition = 0;
            mRecyclerView.smoothScrollToPosition(mPosition);

            if (bookEntries == null) {
                Toast.makeText(this, "There is no book currently", Toast.LENGTH_LONG).show();
            }
        });

        // Source: https://stackoverflow.com/questions/26097513/android-simple-alert-dialog
        mAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String note = "Note Start";



                final EditText input = new EditText(MainActivity.this);
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Please enter a book name");
                alertDialog.setView(input);
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // (todo) Implement insert new Book here
                        String bookName = input.getText().toString();
                        NoteEntry noteEntry = new NoteEntry(bookName, note);

                        Log.d(TAG, "onClick: About to clicked");
                        mExecutor.diskIO().execute(() -> {
                            mViewModel.insertBook(noteEntry);

                        });

                        // Firebase Analytics Logging
                        Bundle params = new Bundle();
                        params.putString(Param.ITEM_ID, bookName);
                        FirebaseAnalytics analytics = FirebaseAnalytics.getInstance(MainActivity.this);
                        analytics.logEvent(Event.ADD_TO_CART, params);


                        Log.d(TAG, "onClick: Result of the click");
                        Toast.makeText(getApplicationContext(), bookName, Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                alertDialog.show();
            }
        });
    }
}
