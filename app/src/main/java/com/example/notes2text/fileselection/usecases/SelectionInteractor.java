package com.example.notes2text.fileselection.usecases;

import java.io.File;
import java.util.ArrayList;

public class SelectionInteractor implements SelectionInputBoundary {

    private ArrayList<File> selectedFiles;

    public SelectionInteractor(){
        selectedFiles = new ArrayList<File>();
    }

    @Override
    public ArrayList<File> getSelectedFiles() {
        return selectedFiles;
    }

    @Override
    public void addItem(File file) {
        if(!(selectedFiles.contains(file))){
            selectedFiles.add(file);
        }
    }

    @Override
    public void removeItem(File file) {
        if (selectedFiles.contains(file)){
            selectedFiles.remove(file);
        }
    }

    @Override
    public void cancel() {
        selectedFiles.clear();
    }

    @Override
    public void move(String moveToAddress) {

    }
}
