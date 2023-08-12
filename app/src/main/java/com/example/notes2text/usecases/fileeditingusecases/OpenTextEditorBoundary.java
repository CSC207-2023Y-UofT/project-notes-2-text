package com.example.notes2text.usecases.fileeditingusecases;

import java.io.File;

/**
 * Interface for the OpenTextEditorInteractor. OpenTextEditorInteractor extracts the text from the
 * selected text file to display to the user on the text editor page
 */
public interface OpenTextEditorBoundary {

    /**
     * Extract the content of a text file.
     * @return Returns the content of the text file as a String
     */
    String extractContent();

    /**
     * Get the file object of the text file that is open in the text editor
     * @return Returns the file object of the last opened text file
     */
    File getLastOpened();
}
