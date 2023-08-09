package com.example.notes2text.adapters;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.example.notes2text.usecases.FileMenuFactory;
import com.example.notes2text.usecases.FileMenuInputBoundary;
import com.example.notes2text.usecases.FileMenuInteractor;

import java.io.File;

public class FileMenuController {

    protected Context context;
    protected View view;
    protected PopupMenu fileMenu;

    protected FileMenuInputBoundary fileMenuUseCase;

    protected FileMenuFactory menuFactory;


    public FileMenuController(Context context, View view, FileMenuFactory menuFactory){
        this.context = context;
        this.view = view;
        this.menuFactory = menuFactory;

    }

    public boolean create(File menuFile){
        fileMenu = menuFactory.create(context, view);

        //Generate the file menu's interactor.
        fileMenuUseCase = new FileMenuInteractor(fileMenu, menuFile);

        //Bind actions to the file menu.

        fileMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                redirect(menuItem);

                return true;
            }
        });

        fileMenu.show();
        return true;
    }

    protected void redirect(MenuItem menuItem){
        if (menuItem.getTitle().equals("OPEN")){
            //open file or folder.
            fileMenuUseCase.open(context, view);
        } else if (menuItem.getTitle().equals("MOVE")){
            // move item.
            fileMenuUseCase.move(context, view);
        }
        if (menuItem.getTitle().equals("SHARE")){
            // Redirect to third party share.
            fileMenuUseCase.share(context, view);
        }
        if (menuItem.getTitle().equals("RENAME")){
            // Bring up a rename menu.
            fileMenuUseCase.rename(context, view);
        }
        if (menuItem.getTitle().equals("DELETE")){
            // Delete the item.
            fileMenuUseCase.delete(context, view);
        }

    }
}
