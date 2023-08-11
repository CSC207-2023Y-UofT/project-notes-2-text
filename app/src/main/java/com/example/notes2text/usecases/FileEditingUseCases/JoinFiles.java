package com.example.notes2text.usecases.FileEditingUseCases;

import com.example.notes2text.entities.FileEditingEntities.TextFile;

import java.io.File;
import java.util.ArrayList;


public class JoinFiles implements JoinFileBoundary {
    private final ArrayList<File> selectedFiles;

    /**
     * The constructor for the JoinFiles class. Initializes selectedFiles attribute
     * @param fileList The array list of the selected files from the user to join together
     */
    public JoinFiles(ArrayList<File> fileList) {
        this.selectedFiles = fileList;
    }

    public String extractContent() {
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < this.selectedFiles.size(); i++) {
            TextFile textFile = new TextFile(this.selectedFiles.get(i));
            String text = textFile.extractText();
            content.append(text);
        }
        return content.toString();
    }
}
