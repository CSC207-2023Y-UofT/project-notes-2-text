package com.example.notes2text.fileselection.usecases;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.notes2text.adapters.ActivitySwitchController;
import com.example.notes2text.fileselection.adapters.SelectionController;
import com.example.notes2text.usecases.FileMenuInteractor;

import java.io.File;
import java.util.ArrayList;

public class SelectionMenuInteractor extends FileMenuInteractor {

    ArrayList<File> selectedList;

    public SelectionMenuInteractor(PopupMenu fileMenu, File keyFile, ArrayList<File> selectedFiles) {
        super(fileMenu, keyFile);
        selectedList = selectedFiles;
    }

    @Override
    public boolean open(Context context, View view){
        if(super.getKeyFile().isDirectory()){
            // If the file is a directory(folder), enter the folder.
            Intent intent = new Intent(context, ActivitySwitchController.class);
            String path = super.getKeyFile().getAbsolutePath();
            intent.putExtra("path",path);
            Bundle bundle = new Bundle();
            bundle.putSerializable("selectedFiles", selectedList);
            intent.putExtras(bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            //Determine the type of the file in question.
            try {
                // The file is a file. Open the file.
                super.getFileOpener().openFile(context, super.getKeyFile());
            }catch (Exception e) {
                Toast.makeText(context.getApplicationContext(), "File not openable.", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }
}
