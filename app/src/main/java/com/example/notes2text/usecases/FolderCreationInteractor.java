package com.example.notes2text.usecases;


import android.content.Context;

import com.example.notes2text.adapters.DirectoryAccessOutputBoundary;

import java.io.File;

public class FolderCreationInteractor {
    private final FolderFactory folderMaker = new FolderFactory();
    private final DirectoryAccessOutputBoundary output;
    private final Context context;

    public FolderCreationInteractor (DirectoryAccessOutputBoundary output, Context context) {
        this.output = output;
        this.context = context;
    }

    public void create(String fileName, String filePath) {
        File file = new File(filePath, fileName);

        if (!file.exists()) {
            // No folder with the same name, attempt to make new folder.
            try {
                // Make new folder.
                folderMaker.createFolder(file);
                output.FolderCreationSuccess(context);
            } catch (Exception e) {
                // Catch potential syntax error for naming.
                output.FolderCreationFailureInvalid(context);
            }
        }else {
            // When the folder with same name already exists.
            output.FolderCreationFailureSameName(context);
        }
    }
}
