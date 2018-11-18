package com.example.guice.viewmodelexample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tasrul on 18-Nov-18.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> notes=new ArrayList<>();

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {

        holder.tvTitle.setText(notes.get(position).getTitle());
        holder.tvDes.setText(notes.get(position).getDescription());
        holder.tvPriority.setText(String.valueOf(notes.get(position).getPriority()));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes)
    {
        this.notes=notes;
        notifyDataSetChanged();
    }

    class NoteHolder extends RecyclerView.ViewHolder
    {
        private TextView tvTitle;
        private TextView tvDes;
        private TextView tvPriority;

        public NoteHolder(View itemView) {
            super(itemView);

            tvTitle=itemView.findViewById(R.id.tv_title);
            tvDes=itemView.findViewById(R.id.tv_des);
            tvPriority=itemView.findViewById(R.id.tv_priority);
        }
    }
}
