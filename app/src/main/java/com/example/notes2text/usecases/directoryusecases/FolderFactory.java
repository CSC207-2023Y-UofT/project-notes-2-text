package com.example.notes2text.usecases.directoryusecases;

import java.io.File;

public class FolderFactory {

    /**
     * Makes a new folder using the file.mkdir() method.
     *
     * @param file IO.File, representing the folder to be made.
     */
    public void createFolder(File file) {
        // Return value ignored as not helpful for implementation.
        file.mkdir();
    }
}
