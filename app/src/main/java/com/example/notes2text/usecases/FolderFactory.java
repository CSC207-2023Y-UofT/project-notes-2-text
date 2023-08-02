package com.example.notes2text.usecases;

import java.io.File;

public class FolderFactory {

    public void createFolder(File file) {
        file.mkdir();
    }
}
