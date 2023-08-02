package com.example.notes2text.file_sharing.use_case;

import androidx.core.content.FileProvider;

import com.example.notes2text.R;

// Required implementation by Android to properly configure FileProvider.
public class MyFileProvider extends FileProvider{
    public MyFileProvider() {
        super(R.xml.file_paths);
    }
}