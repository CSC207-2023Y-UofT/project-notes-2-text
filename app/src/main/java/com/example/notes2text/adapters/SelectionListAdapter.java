package com.example.notes2text.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;;import com.example.notes2text.entities.FileViewHolder;

import java.io.File;
import java.util.ArrayList;

public class SelectionListAdapter extends FileListAdaptor {

    private ArrayList<File> selectedFiles;

    public SelectionListAdapter(Context context, File[] fileList) {
        super(context, fileList);
        selectedFiles = new ArrayList<>();
    }


}
