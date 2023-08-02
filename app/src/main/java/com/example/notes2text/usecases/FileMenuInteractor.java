package com.example.notes2text.usecases;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.notes2text.adapters.ActivitySwitchController;
import com.example.notes2text.adapters.DirectoryActivity;

import java.io.File;

public class FileMenuInteractor implements FileMenuInputBoundary {

    private File keyFile;
    private PopupMenu fileMenu;

    FileOpenInteractor fileOpener = new FileOpenInteractor();
    public FileMenuInteractor(PopupMenu fileMenu, File keyFile){
        this.keyFile = keyFile;
        this.fileMenu = fileMenu;

    }

    @Override
    public boolean open(Context context, View view) {
        if(keyFile.isDirectory()){
            // If the file is a directory(folder), enter the folder.
            Intent intent = new Intent(context, ActivitySwitchController.class);
            String path = keyFile.getAbsolutePath();
            intent.putExtra("path",path);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        } else {
            //Determine the type of the file in question.
            try {
                // The file is a file. Open the file.
                fileOpener.openFile(context, keyFile);
            }catch (Exception e) {
                Toast.makeText(context.getApplicationContext(), "File not openable.", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    @Override
    public boolean move(Context context, View view) {
        Toast.makeText(context.getApplicationContext(), "File moved", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean share(Context context, View view) {
        Toast.makeText(context.getApplicationContext(), "File shared", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean rename(Context context, View view) {
        Toast.makeText(context.getApplicationContext(), "File renamed", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean delete(Context context, View view) {
        String fileName = keyFile.getName();
        boolean isDeleted = keyFile.delete();
        String deletedMsg = fileName + " deleted";
        if (isDeleted){
            Toast.makeText(context.getApplicationContext(), deletedMsg, Toast.LENGTH_SHORT).show();
            view.setVisibility(View.GONE);
        }
        return isDeleted;
    }

    public File getKeyFile(){
        return keyFile;
    }
    public PopupMenu getFileMenu(){
        return fileMenu;
    }

    public FileOpenInteractor getFileOpener(){
        return fileOpener;
    }
}
