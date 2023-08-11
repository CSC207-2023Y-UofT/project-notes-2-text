package com.example.notes2text.fileselection.adapters;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.example.notes2text.adapters.FileMenuController;
import com.example.notes2text.fileselection.usecases.SelectionMenuInteractor;
import com.example.notes2text.usecases.FileMenuFactory;

import java.io.File;
import java.util.ArrayList;

public class SelectionMenuController extends FileMenuController {
    public SelectionMenuController(Context context, View view, FileMenuFactory menuFactory) {
        super(context, view, menuFactory);
    }

    /**
     * Creates a file menu for selection purposes and sets up redirection from menu items.
     * @param menuFile
     * @param selectedList
     * @return
     */
    public boolean create(File menuFile, ArrayList<File> selectedList){
        super.fileMenu = menuFactory.create(context, view);

        //Generate the file menu's interactor.
        // Maintains SOLID and modularity: fileMenuUseCase is an interface, and the specific interactor is overrideable.
        super.fileMenuUseCase = new SelectionMenuInteractor(super.fileMenu, menuFile, selectedList);

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
}
