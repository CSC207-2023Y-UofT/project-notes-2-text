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
        File newFile = new File(directory, fileName.trim() + "." + extension);
        keyFile.renameTo(newFile);

        // In place for testing, monitor path used in runtime.
        Log.i("Directory is", directory.toString());
        Log.i("Default path is", keyFile.toString());
        Log.i("newFile path is", newFile.toString());
    }
}
