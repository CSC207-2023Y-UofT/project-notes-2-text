package com.example.notes2text.usecases.FileEditingUseCases;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveFile implements SaveFileBoundary {

    private final String content;

    public SaveFile(String content) {
        this.content = content;
    }

    public File createFile(Context context, String fileName) {
        File file = null;
        try {
            FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(this.content.getBytes());
            outputStream.close();

            file = new File(context.getFilesDir(), fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public void saveFile(File file) {

    }
}
