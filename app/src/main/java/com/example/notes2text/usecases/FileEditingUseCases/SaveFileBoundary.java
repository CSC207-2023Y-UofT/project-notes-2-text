package com.example.notes2text.usecases.FileEditingUseCases;

import android.content.Context;

import java.io.File;

/**
 * Interface that implements SaveFile. SaveFile creates a new File with the newly edited text as
 * the contents of the file and saves the file in the same location as the original text file
 */
public interface SaveFileBoundary {
    /**
     * Creates a new file with the edited text as the content
     * @param context SavePopUpActivity
     * @param fileName The file name the user just input
     * @return Returns the newly created file File object
     */
    File createFile(Context context, String fileName);

    /**
     * Saves the new file at the same folder as the original text file
     * @param context SavePopUpActivity
     * @param file The newly created file from createFile()
     * @param newPath The path to set on the file. The path places file in same folder with original
     *                txt file
     */
    void saveFile(Context context, File file, String newPath);

}
