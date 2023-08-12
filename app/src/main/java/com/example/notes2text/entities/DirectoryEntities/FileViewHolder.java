package com.example.notes2text.entities.DirectoryEntities;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes2text.R;

public class FileViewHolder extends RecyclerView.ViewHolder {

    public TextView textElement;
    public ImageView imageElement;

    public FileViewHolder(@NonNull View holderView) {
        super(holderView);
        // the textElement and imageElement will determine the appearance of each holder item in the RecyclerView.
        textElement = holderView.findViewById(R.id.file_label_view);
        imageElement = holderView.findViewById(R.id.icon_view);
    }
}
