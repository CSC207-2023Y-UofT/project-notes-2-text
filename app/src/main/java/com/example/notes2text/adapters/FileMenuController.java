package com.example.notes2text.adapters;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.fragment.app.FragmentManager;

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

    //Needed for transaction of Activity to creating Dialog for renaming files.
    private FragmentManager fragManager;


    public FileMenuController(Context context, View view, FileMenuFactory menuFactory){
        this.context = context;
        this.view = view;
        this.menuFactory = menuFactory;

    }

    //For when FragmentManager is needed for renaming Files.
    public FileMenuController(Context context, View view, FileMenuFactory menuFactory, FragmentManager fragManager){
        this.context = context;
        this.view = view;
        this.menuFactory = menuFactory;
        this.fragManager = fragManager;

    }

    /**
     * Creates a file menu popup for menuFile.
     * @param menuFile the file on which to open the popup menu.
     * @return true when the menu is successfully created and called.
     */
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

    /**
     * Redirects to the appropriate use case function call for the menu item that is pressed.
     * @param menuItem the pressed menu item.
     */
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
            openDialog();
        }
        if (menuItem.getTitle().equals("DELETE")){
            // Delete the item.
            fileMenuUseCase.delete(context, view);
        }

    }

    /**
     * A helper method that intialize a new RenameController and construct a new Dialog.
     */
    private void openDialog() {
        RenameController renameFile = new RenameController(context, fileMenuUseCase);

        // Default method in AppCompatDialogFragment that displays the Dialog built.
        renameFile.show(fragManager, "Create Rename File Dialogue");
    }
}
