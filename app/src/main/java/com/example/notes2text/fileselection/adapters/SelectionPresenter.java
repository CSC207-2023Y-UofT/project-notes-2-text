package com.example.notes2text.fileselection.adapters;

import android.content.Context;
import android.widget.Toast;

public class SelectionPresenter {
    public void MoveFileSuccess(Context context) {
        Toast.makeText(context, "File moved successfully", Toast.LENGTH_SHORT).show();
    }
}