package com.example.notes2text.usecases.DirectoryUseCases;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.notes2text.entities.SelectionViewHolder;

public class SelectionViewHolderFactory implements ViewHolderAbsFactory{
    @Override
    public RecyclerView.ViewHolder create(View view) {
        return new SelectionViewHolder(view);
    }
}
