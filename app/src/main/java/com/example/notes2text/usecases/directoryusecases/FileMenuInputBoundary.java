package com.example.notes2text.usecases.directoryusecases;

import android.content.Context;
import android.view.View;

import com.example.notes2text.adapters.directoryadapters.DirectoryRefreshOutputBoundary;

/**
 * Interface for file management menus.
 */
public interface FileMenuInputBoundary {


    /**
     *
     * @param context Application context
     * @param view File holder view.
     * @return true if file opening was attempted.
     */
    public boolean open(Context context, View view);


    /**
     * Move the file in question
     * @param context
     * @param view
     * @return
     */
    public boolean move(Context context, View view);

    /**
     * Share the file through any apps that accept files of the chosen file's type.
     * @param context Application context.
     * @param view File holder view
     * @return true if sharing attempted.
     */
    public boolean share(Context context, View view);

    public boolean rename(Context context, String fileName, DirectoryRefreshOutputBoundary refresher);

    public boolean delete(Context context, View view);


}
