package com.example.notes2text.usecases;

import android.content.Context;
import android.view.View;
import android.widget.PopupMenu;

import com.example.notes2text.adapters.DirectoryRefreshOutputBoundary;

import java.io.File;

/**
 * Interface for file management menus.
 */
public interface FileMenuInputBoundary {


    public boolean open(Context context, View view);

    public boolean move(Context context, View view);

    public boolean share(Context context, View view);

    public boolean rename(Context context, String fileName, DirectoryRefreshOutputBoundary refresher);

    public boolean delete(Context context, View view);


}
