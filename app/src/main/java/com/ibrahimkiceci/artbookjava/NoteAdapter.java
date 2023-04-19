package com.ibrahimkiceci.artbookjava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ibrahimkiceci.artbookjava.databinding.RecyclerRowBinding;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    ArrayList<Note> noteArrayList;

    public NoteAdapter(ArrayList<Note> noteArrayList){
        this.noteArrayList = noteArrayList;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NoteHolder(recyclerRowBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {

        holder.binding.recyclerViewTextView.setText(noteArrayList.get(position).title);


    }

    @Override
    public int getItemCount() {
        return noteArrayList.size();
    }

    public class NoteHolder extends RecyclerView.ViewHolder {
        private RecyclerRowBinding binding;

        public NoteHolder(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
