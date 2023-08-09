package com.example.notes2text.usecases.FileEditingUseCases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextFile {

    // Text stored in txt file
    private String content;

    // Root address of file
    private final String rootAddress;

    private final File file;

    private final String fileName;

    // returns text from a text file
    public TextFile(File file) {
        this.rootAddress = file.getAbsolutePath();
        this.file = file;
        this.fileName = file.getName();
    }

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
            e.printStackTrace();
        }
        if (stringBuilder != null) {
            this.content = stringBuilder.toString();
        } return this.content;
    }

    public String getLocation() {
        return this.rootAddress.replace(this.fileName, "");
    }
}

