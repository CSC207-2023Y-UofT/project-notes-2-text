package com.example.notes2text.usecases.directoryusecases;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public interface ViewHolderAbsFactory {

    RecyclerView.ViewHolder create(View view);
}
