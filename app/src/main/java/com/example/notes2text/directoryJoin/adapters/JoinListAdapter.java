package com.example.notes2text.directoryJoin.adapters;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.notes2text.R;
import com.example.notes2text.entities.SelectionViewHolder;
import com.example.notes2text.fileselection.adapters.SelectionListAdapter;
import com.example.notes2text.fileselection.usecases.SelectionInputBoundary;
import com.example.notes2text.usecases.FileMenuFactory;

import java.io.File;
import java.util.ArrayList;

public class JoinListAdapter extends SelectionListAdapter {
    public JoinListAdapter(Context context, File[] fileList, SelectionInputBoundary selectionUseCase) {
        super(context, fileList, selectionUseCase);
    }

    public JoinListAdapter(Context context, File[] fileList, SelectionInputBoundary selectionUseCase, ArrayList<File> inputFileList) {
        super(context, fileList, selectionUseCase, inputFileList);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectionViewHolder holder, int position){
        File chosenFile = fileList[position];
        //Changes the text element of the holder to match the name of the file.
        holder.textElement.setText(chosenFile.getName());

        if (chosenFile.isDirectory()) {
            // If the file in question is a folder, represent it as a folder.
            holder.imageElement.setImageResource(R.drawable.baseline_folder_24);
        } else {
            // the file is not a folder, represent it as just a regular file.
            holder.imageElement.setImageResource(R.drawable.baseline_insert_drive_file_24);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            //If item was clicked, add the file to the list of files, and display appropriate changes to the holder object.
            @Override
            public void onClick(View view) {
                //If item is checked, remove it from the list. If not, add it to the list.
                if (holder.selectionCheck.isChecked()) {
                    //visual indication
                    holder.selectionCheck.setChecked(false);
                    holder.viewElement.setBackgroundColor(0x00008080);
                    // remove item from selection list.
                    selectionUseCase.removeItem(chosenFile);
                } else {
                    //visual indication
                    holder.selectionCheck.setChecked(true);
                    holder.viewElement.setBackgroundColor(0xFF008080);
                    //add item to selected list.
                    selectionUseCase.addItem(chosenFile);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                JoinMenuController fileMenuController = new JoinMenuController(context, view, new FileMenuFactory());
                return fileMenuController.create(chosenFile, getSelectedFiles());
            }
        });
    }
}