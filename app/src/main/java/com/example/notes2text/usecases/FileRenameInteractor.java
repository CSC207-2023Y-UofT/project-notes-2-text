package com.example.notes2text.usecases;

import android.util.Log;

import java.io.File;

public class FileRenameInteractor {

    public void setNewFileName(File keyFile, String fileName) {
        // Get the file type.
        String oldFileName = keyFile.getName();
        int index = oldFileName.lastIndexOf('.');
        String extension = "";
        if (index > 0) {
            extension = oldFileName.substring(index + 1);
        }

        // Rename the file.
        String directoryPath = keyFile.getParent();
        assert directoryPath != null;
        File directory = new File(directoryPath);
        if (keyFile.isDirectory()){
            // Does not need the ".extension" if its a directory path.
            File newFile = new File(directory, fileName.trim() + extension);
            keyFile.renameTo(newFile);
            // In place for testing, monitor path used in runtime.
            Log.i("newFile path is", newFile.toString());
        } else {
            // Needs the "." if it is a file.
            File newFile = new File(directory, fileName.trim() + "." + extension);
            keyFile.renameTo(newFile);
            // In place for testing, monitor path used in runtime.
            Log.i("newFile path is", newFile.toString());
        }


        // In place for testing, monitor path used in runtime.
        Log.i("Directory is", directory.toString());
        Log.i("Default path is", keyFile.toString());
    }
}
