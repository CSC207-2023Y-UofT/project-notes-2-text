package com.example.notes2text.usecases.FileEditingUseCases;

import android.content.Context;

import java.io.File;

public interface SaveFileBoundary {
    public File createFile(Context context, String fileName);

    public void saveFile(File file);

}
