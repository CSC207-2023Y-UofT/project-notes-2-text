package com.example.notes2text.directoryJoin.usecases;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.notes2text.directoryJoin.adapters.JoinController;
import com.example.notes2text.usecases.FileMenuInteractor;

import java.io.File;
import java.util.ArrayList;

public class JoinMenuInteractor extends FileMenuInteractor {

    ArrayList<File> selectedList;

    public JoinMenuInteractor(PopupMenu fileMenu, File keyFile, ArrayList<File> selectedFiles) {
        super(fileMenu, keyFile);
        selectedList = selectedFiles;
    }
    @Override
    public boolean open(Context context, View view) {
        if (super.getKeyFile().isDirectory()) {
            // If the folder to open is already selected, you intentionally can't open it: otherwise,
            // you could accidentally try to move a folder into itself or a subfolder.
            if (!selectedList.contains(super.getKeyFile())) {
                // If the file is a directory(folder), enter the folder.
                Intent intent = new Intent(context, JoinController.class);
                String path = super.getKeyFile().getAbsolutePath();
                intent.putExtra("path", path);
                Bundle bundle = new Bundle();
                bundle.putSerializable("selectedFiles", selectedList);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } else {
                Toast.makeText(context.getApplicationContext(), "Selected folders are not openable.", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            //Determine the type of the file in question.
            Toast.makeText(context, "Can't open files from inside Join menu.", Toast.LENGTH_SHORT).show();
            }
        return true;
        }
}
