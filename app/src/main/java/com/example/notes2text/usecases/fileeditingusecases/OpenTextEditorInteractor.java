package com.example.notes2text.usecases.fileeditingusecases;

import com.example.notes2text.entities.fileeditingentities.TextFile;

import java.io.File;

public class OpenTextEditorInteractor implements OpenTextEditorBoundary {
    File lastFile;

    String lastFileRoot;

    /**
     * Constructor for OpenTextEditorInteractor
     * @param file File object of the selected text file to open
     */
    public OpenTextEditorInteractor(File file) {
        this.lastFile = file;
        this.lastFileRoot = file.getAbsolutePath();
    }

    // Extract text from the txt file
    public String extractContent() {
        TextFile fileToOpen = new TextFile(this.lastFile);
        return fileToOpen.extractText();
    }

    public File getLastOpened() {
        return this.lastFile;
    }
}

