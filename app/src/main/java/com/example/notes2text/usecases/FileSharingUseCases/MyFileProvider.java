package com.example.notes2text.usecases.FileSharingUseCases;

import androidx.core.content.FileProvider;

import com.example.notes2text.R;

// Required implementation by Android to properly configure FileProvider.
public class MyFileProvider extends FileProvider{

    /**
     * Constructor for MyFileProvider. This implementation provides FileProvider with its needed
     * authority to access the files. Will not be called directly anywhere in the program.
     */
    public MyFileProvider() {
        super(R.xml.file_paths);
    }
}