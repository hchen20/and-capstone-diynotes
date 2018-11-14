package com.udacity.diynotes.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.udacity.diynotes.R;
import com.udacity.diynotes.R2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookListAdapterViewHolder> {
    private static final String TAG = BookListAdapter.class.getSimpleName();

    // The context is used to utility method, app resources and layout inflater
    private final Context mContext;

    private List<String> mBooks;

    public BookListAdapter(@NonNull Context context) {
        this.mContext = context;
    }

    public void swapBooks(final List<String> newBooks) {
        // If there was not book data, then recreate all of the list
        if (mBooks == null) {
            mBooks = newBooks;
            notifyDataSetChanged();
        } else {
            mBooks = newBooks;
            notifyDataSetChanged();
        }
    }

    @Override
    public void onBindViewHolder(BookListAdapterViewHolder holder, int position) {
        String bookTitle = mBooks.get(position);

        holder.bookTitleView.setText(bookTitle);
    }


    @Override
    public BookListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.book_list_item;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        boolean attachParentImmediately = false;

        View view = inflater.inflate(layoutId, parent,attachParentImmediately);
        BookListAdapterViewHolder viewHolder = new BookListAdapterViewHolder(view);

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        if (mBooks == null)
            return 0;
        return mBooks.size();
    }

    class BookListAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private static final String TAG = "BookListAdapterVH";

        @BindView(R2.id.book_title)
        TextView bookTitleView;

        BookListAdapterViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
        }
    }
}
