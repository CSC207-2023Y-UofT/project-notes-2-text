package com.example.notes2text.usecases.selectiondirectoryusecases;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.notes2text.adapters.directoryadapters.ActivitySwitchController;
import com.example.notes2text.usecases.directoryusecases.FileMenuInteractor;

import java.io.File;
import java.util.ArrayList;

public class SelectionMenuInteractor extends FileMenuInteractor {

    ArrayList<File> selectedList;

    public SelectionMenuInteractor(PopupMenu fileMenu, File keyFile, ArrayList<File> selectedFiles) {
        super(fileMenu, keyFile);
        selectedList = selectedFiles;
    }

    /**
     * Takes in application context and opens the file.
     * @param context: the application context
     * @param view: the view element on which this  file menu
     * @return true if the file was successfully opened, false if not.
     */
    @Override
    public boolean open(Context context, View view){
        if(super.getKeyFile().isDirectory()){
            // If the folder to open is already selected, you intentionally can't open it: otherwise,
            // you could accidentally try to move a folder into itself or a subfolder.
            if(!selectedList.contains(super.getKeyFile())) {
                // If the file is a directory(folder), enter the folder.
                Intent intent = new Intent(context, ActivitySwitchController.class);
                String path = super.getKeyFile().getAbsolutePath();
                intent.putExtra("path", path);
                Bundle bundle = new Bundle();
                bundle.putSerializable("selectedFiles", selectedList);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } else{
                //TODO: Move to Selection Presenter.
                Toast.makeText(context.getApplicationContext(), "Selected folders are not openable.", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            //Determine the type of the file in question.
            try {
                // The file is a file. Open the file.
                super.getFileOpener().openFile(context, super.getKeyFile());
            }catch (Exception e) {
                //TODO: Move to Selection Presenter.
                Toast.makeText(context.getApplicationContext(), "File not openable.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}
