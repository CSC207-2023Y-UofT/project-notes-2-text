package com.example.notes2text.usecases.directoryusecases;


import android.content.Context;
import android.util.Log;

import com.example.notes2text.adapters.directoryadapters.DirectoryAccessOutputBoundary;
import com.example.notes2text.adapters.directoryadapters.DirectoryRefreshOutputBoundary;

import java.io.File;

public class FolderCreationInteractor {
    private final FolderFactory folderMaker = new FolderFactory();
    private final DirectoryAccessOutputBoundary outputBoundary;
    private final Context context;
    private final DirectoryRefreshOutputBoundary refresher;


    /**
     * Constructor for the class. Obtains an Output Boundary to access a presenter and alert user of
     * actions performed in this class. Obtains a context to use the presenter methods.
     *
     * @param output An instance of DirectoryAccessOutputBoundary, which is implemented by
     *               DirectoryAccessPresenter. Contains methods to alert users of certain status of
     *               an action. 
     * @param context A context, needed to use the methods in the Output Boundary.
     */

    public FolderCreationInteractor (DirectoryAccessOutputBoundary output, Context context, DirectoryRefreshOutputBoundary refresher) {
        this.outputBoundary = output;
        this.context = context;
        this.refresher = refresher;
    }


    /**
     * Creates a new folder. Test if the folder already exist, if exist, alert user and does not make
     * the folder. Then test if the input will lead to an error , and alert the user that input is
     * invalid if it is invalid. If folder does not exist and file name is valid, makes the new folder
     * with the name and alert the user that folder has been made.
     *
     * @param fileName A String input from user obtained from the dialog box for folder creation.
     *                 Used for the name of the folder.
     * @param filePath The path to the directory in which the folder will be added to.
     */
    public void create(String fileName, String filePath) {
        File file = new File(filePath, fileName);

        // Monitor the path for the directory which the folder will be added to.
        Log.i ("Directory Path", "Folder made in: " + filePath);

        if (!file.exists()) {
            // No folder with the same name, attempt to make new folder.
            try {
                // Make new folder.
                folderMaker.createFolder(file);
                // New file in directory, refresh directory.
                refresher.refreshDirectory(context, filePath);
                outputBoundary.FolderCreationSuccess(context);
            } catch (SecurityException e) {
                // Catch potential SecurityException error for folder creation.
                Log.e("Folder creation exception", e.getMessage());
                // Alert user.
                outputBoundary.FolderCreationFailureInvalid(context);
            }
        }else {
            // When the folder with same name already exists.
            Log.w("Folder Creation Failed", "Folder already exist at: " + file.getAbsolutePath());
            outputBoundary.FolderCreationFailureSameName(context);
        }
    }
}
