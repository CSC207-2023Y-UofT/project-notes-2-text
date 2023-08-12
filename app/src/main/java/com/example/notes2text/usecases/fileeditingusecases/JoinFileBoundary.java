package com.example.notes2text.usecases.fileeditingusecases;

/**
 * Interface for JoinFiles class. Used to join the content of text files together to be saved as a
 * new text file.
 */
public interface JoinFileBoundary {
    /**
     * Extract the text from a array list of files
     * @return Returns the text from the array of files as a String in the order of the array (i.e.,
     * file at index 1 has its content at the beginning of the String
     */
    String extractContent();
}

