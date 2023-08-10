package com.example.notes2text.usecases.FileEditingUseCases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

/**
 * Represents the text files that the user creates after scanning photos.
 */
public class TextFile {

    // Text stored in txt file
    private String content;

    // Root address of file
    private final String rootAddress;

    private final File file;

    private final String fileName;

    /**
     * Constructor for TextFile
     * @param file File object of a text file
     */
    public TextFile(File file) {
        this.rootAddress = file.getAbsolutePath();
        this.file = file;
        this.fileName = file.getName();
    }

    /**
     * Extracts the text from a text file
     * @return Returns the text from the text file as a String
     */
    public String extractText() {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            if (this.file.exists() && this.rootAddress.endsWith(".txt")) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append("\n"); // Add a newline character for each line
                }

                bufferedReader.close();

            } else {
                // Handle the case when the file does not exist
                this.content = "File not found";
            }
        } catch (IOException e) {
            this.content = "Not a text file";
        }
        if (!Objects.equals(this.content, "File not found") &&
                !Objects.equals(this.content, "Not a text file")) {
            this.content = stringBuilder.toString();
        } return this.content;
    }

    /**
     * Gets the path of the file
     * @return Returns the path of the file as a String
     */
    public String getLocation() {
        return this.rootAddress.replace(this.fileName, "");
    }
}
