package com.example.notes2text.usecases.fileeditingusecases;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


public class SaveFile implements SaveFileBoundary {

    private final String content;

    /**
     * Constructor for SaveFile
     * @param content The text of the edited text file
     */
    public SaveFile(String content) {
        this.content = content;
    }

    public File createFile(Context context, String fileName) {
        File file = new File(context.getFilesDir(), fileName + ".txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(this.content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public void saveFile(Context context, File file, String newPath) {
        Path sourcePath = file.toPath();
        Path targetPath = new File(context.getExternalFilesDir(null), newPath).toPath();

        try {
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            // Handle success or show a message to the user
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the error condition
        }
    }
}
