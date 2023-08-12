package com.example.notes2text.adapters.JoinDirectoryAdapters;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.notes2text.R;
import com.example.notes2text.entities.DirectoryEntities.SelectionViewHolder;
import com.example.notes2text.adapters.SelectionDirectoryAdapters.SelectionListAdapter;
import com.example.notes2text.usecases.SelectionDirectoryUseCases.SelectionInputBoundary;
import com.example.notes2text.usecases.DirectoryUseCases.FileMenuFactory;

import java.io.File;
import java.util.ArrayList;

/**
 * A recycler view adapter for the join activity.
 */
public class JoinListAdapter extends SelectionListAdapter {
    /**
     * Creates the file list adapter for the join file selection activity.
     * @param context Activity context
     * @param fileList List of files to display through the recyclerview.
     * @param selectionUseCase The selection use case input boundary.
     */
    public JoinListAdapter(Context context, File[] fileList, SelectionInputBoundary selectionUseCase) {
        super(context, fileList, selectionUseCase);
    }

    /**
     * Generates the recycler view adapter for the join selection recycler view.
     * @param context Activity context
     * @param fileList List of files to display through the recyclerview.
     * @param selectionUseCase The selection use case input boundary.
     * @param inputFileList A list of selected files to start with.
     */
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
