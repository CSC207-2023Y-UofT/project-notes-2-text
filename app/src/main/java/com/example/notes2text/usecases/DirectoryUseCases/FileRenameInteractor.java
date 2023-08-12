package com.example.notes2text.usecases.DirectoryUseCases;

import android.util.Log;

import java.io.File;

public class FileRenameInteractor {

    /**
     * Change the selected file's name according to user input.
     *
     * @param keyFile A IO.File pointing to the file/folder that the user selected.
     * @param fileName A String for the user input of the new name.
     */
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
            rename(keyFile, directory, fileName.trim() + extension);
        } else {
            // Needs the "." if it is a file.
            rename(keyFile, directory, fileName.trim() + "." + extension);
        }

        // Monitor path used in runtime.
        Log.i("Directory is", directory.toString());
        Log.i("Default path is", keyFile.toString());
    }

    /**
     * Helper method to rename the file.
     *
     * @param currentFile The file that the user want to rename.
     * @param currentDirectory The directory that the file is in.
     * @param filePath The new file name for the file.
     */
    private void rename(File currentFile, File currentDirectory, String filePath) {
        File newFile = new File(currentDirectory, filePath);
        // Test if the file with the new name already exist.
        if (!newFile.exists() || newFile.equals(currentFile)) {
            // Rename the file to new name.
            currentFile.renameTo(newFile);
            // Monitor path used in runtime.
            Log.i("newFile path is", newFile.toString());
        } else {
            /* If the file with same name already exist, add (new) to the front of file name to
            avoid conflict. Use recursion to keep checking for conflicts. */
            rename(currentFile, currentDirectory, "(new)" + filePath);
        }
    }
}

