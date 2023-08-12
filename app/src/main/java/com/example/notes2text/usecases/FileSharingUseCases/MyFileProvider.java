package com.example.notes2text.usecases.FileSharingUseCases;

import androidx.core.content.FileProvider;

import com.example.notes2text.R;

// Required implementation by Android to properly configure FileProvider.
public class MyFileProvider extends FileProvider{
    public MyFileProvider() {
        super(R.xml.file_paths);
    }
}