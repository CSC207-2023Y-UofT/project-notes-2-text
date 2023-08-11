package com.example.notes2text.usecases.DirectoryUseCases;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public interface ViewHolderAbsFactory {

    RecyclerView.ViewHolder create(View view);
}
