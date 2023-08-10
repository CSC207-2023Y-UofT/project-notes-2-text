package com.example.notes2text.usecases;

import android.content.Context;
import android.view.View;
import android.widget.PopupMenu;

public class FileMenuFactory {

    public FileMenuFactory(){

    }

    /**
     * Creates a popup menu for file management.
     * @param context Application context.
     * @param view the view element on which the popup is called.
     * @return Returns the file popup menu.
     */
    public PopupMenu create(Context context, View view){
        PopupMenu fileMenu = new PopupMenu(context, view);
        fileMenu.getMenu().add("OPEN");
        fileMenu.getMenu().add("MOVE");
        fileMenu.getMenu().add("SHARE");
        fileMenu.getMenu().add("RENAME");
        fileMenu.getMenu().add("DELETE");
        return fileMenu;
    }
}
