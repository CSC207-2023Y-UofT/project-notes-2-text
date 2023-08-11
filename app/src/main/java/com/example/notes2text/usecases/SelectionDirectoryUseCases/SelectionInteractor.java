package com.example.notes2text.usecases.SelectionDirectoryUseCases;

import java.io.File;
import java.util.ArrayList;

public class SelectionInteractor implements SelectionInputBoundary {

    private final ArrayList<File> selectedFiles;

    public SelectionInteractor(){
        selectedFiles = new ArrayList<File>();
    }

    @Override
    public ArrayList<File> getSelectedFiles() {
        return selectedFiles;
    }

    /**
     * Adds a file to the list of selected files.
     * @param file to be added.
     */
    @Override
    public void addItem(File file) {
        if(!(selectedFiles.contains(file))){
            selectedFiles.add(file);
        }
    }

    /**
     * Removes a file from the list of selected files.
     * @param file File to be removed.
     */
    @Override
    public void removeItem(File file) {
        selectedFiles.remove(file);
    }

    @Override
    public void cancel() {
        selectedFiles.clear();
    }

    /**
     * Moves the files in the selected files list to the new location.
     * @param moveToAddress Address to move to.
     */
    @Override
    public void move(String moveToAddress) {

        //creates a temporary file with the same name at the new file location, and
        // overwrites the old file to the new file, moving the file to the new file spot.
        for (File selectedFile: selectedFiles) {

            String fileName = selectedFile.getName();
            File destFile = new File(moveToAddress + "/" + fileName);
            selectedFile.renameTo(destFile);
        }
        selectedFiles.clear();

    }
}
