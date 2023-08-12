package com.example.notes2text.entities.directoryentities;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes2text.R;

public class SelectionViewHolder extends RecyclerView.ViewHolder{

    public TextView textElement;
    public ImageView imageElement;

    public View viewElement;

    public CheckBox selectionCheck;

    public SelectionViewHolder(@NonNull View holderView) {
        super(holderView);
        textElement = holderView.findViewById(R.id.file_label_view);
        imageElement = holderView.findViewById(R.id.icon_view);
        selectionCheck = holderView.findViewById(R.id.file_select_check);
        viewElement = holderView;
    }
}
