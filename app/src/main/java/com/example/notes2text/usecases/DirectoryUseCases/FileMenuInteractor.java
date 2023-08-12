package com.example.notes2text.usecases.DirectoryUseCases;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.notes2text.adapters.DirectoryAdapters.ActivitySwitchController;
import com.example.notes2text.adapters.DirectoryAdapters.DirectoryRefreshOutputBoundary;
import com.example.notes2text.usecases.FileSharingUseCases.ShareObserver;

import java.io.File;
import java.util.ArrayList;

public class FileMenuInteractor implements FileMenuInputBoundary {

    private final File keyFile;
    private final PopupMenu fileMenu;

    /* Initialize an observer for initiating sharing function */
    private final ShareObserver SHARING = new ShareObserver();

    FileOpenInteractor fileOpener = new FileOpenInteractor();
    FileRenameInteractor fileRenamer = new FileRenameInteractor();
    public FileMenuInteractor(PopupMenu fileMenu, File keyFile){
        this.keyFile = keyFile;
        this.fileMenu = fileMenu;

    }

    /**
     * Opens the file.
     * @param context Application context.
     * @param view View element of of the file in question.
     */
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
        /* Implementation of Share function */
        if (keyFile.isDirectory()) {
            // Blocks user from sharing folders.
            Toast.makeText(context.getApplicationContext(), "Cannot share folder", Toast.LENGTH_SHORT).show();
        } else {
            // Wrap the file in an ArrayList as required by the sharing use case.
            ArrayList<File> files = new ArrayList<>();
            files.add(keyFile);
            SHARING.share(context, files);
        } return true;
    }

    @Override
    public boolean rename(Context context, String fileName, DirectoryRefreshOutputBoundary refresher) {
        // Set the new file name with the rename use case class.
        fileRenamer.setNewFileName(keyFile, fileName);
        // File renamed, refresh Directory to see changes.
        // Null warning ignored as by logic method will never return null as there has to be a directory that user is viewing.
        refresher.refreshDirectory(context, keyFile.getParentFile().getAbsolutePath());
        // Notify the user.
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
