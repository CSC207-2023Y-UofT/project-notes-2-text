package com.example.notes2text.usecases.FileSharingUseCases;

import java.util.ArrayList;
import java.io.File;

import android.content.Context;


public class FileSharingModel {
    private final ArrayList<File> files;
    private final Context context;

    /**
     * Constructor for FileSharingModel. Initialize a model to pass information inward.
     *
     * @param context A Context that are used by downstream classes to access Android system components.
     * @param shareFile An ArrayList of IO.Files to be shared.
     */
    public FileSharingModel(Context context, ArrayList<File> shareFile) {
        this.files = shareFile;
        this.context = context;
    }

    /**
     * Getter function for the stored list of IO.File.
     *
     * @return An ArrayList of IO.File to be shared.
     */
    public ArrayList<File> getFile() {
        return files;
    }

    /**
     * Getter function for the stored Context.
     *
     * @return A Context that can be used to access Android system functions.
     */
    public Context getContext() {
        return context;
    }
}