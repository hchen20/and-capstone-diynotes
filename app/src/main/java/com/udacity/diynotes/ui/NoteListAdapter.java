package com.udacity.diynotes.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.diynotes.R;
import com.udacity.diynotes.R2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteListAdapterViewHolder> {
    private static final String TAG = NoteListAdapter.class.getSimpleName();

    // The context is used to utility method, app resources and layout inflater
    private final Context mContext;

    private List<String> mNotes;

    public NoteListAdapter(@NonNull Context context) {
        this.mContext = context;
    }

    public void swapNotes(final List<String> newNotes) {
        // If there was not book data, then recreate all of the list
        Log.d(TAG, "swapBooks: " + String.valueOf(newNotes.size()));
        if (mNotes == null) {
            mNotes = newNotes;
            notifyDataSetChanged();
        } else {
            mNotes = newNotes;
            notifyDataSetChanged();
        }
    }


    @Override
    public NoteListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.note_list_item;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        boolean attachParentImmediately = false;

        View view = inflater.inflate(layoutId, parent,attachParentImmediately);
        NoteListAdapterViewHolder viewHolder = new NoteListAdapterViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NoteListAdapterViewHolder holder, int position) {
        String note = mNotes.get(position);

        holder.noteDetailView.setText(note);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class NoteListAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private static final String TAG = "NoteListAdapterVH";

        @BindView(R.id.note_detail)
        TextView noteDetailView;

        NoteListAdapterViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Log.d(TAG, "onClick: you clicked on " + String.valueOf(position));
        }
    }
}
