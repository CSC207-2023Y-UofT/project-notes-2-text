package com.example.notes2text.file_sharing.use_case;

import androidx.core.content.FileProvider;

import com.example.notes2text.R;


public class MyFileProvider extends FileProvider{
    public MyFileProvider() {
        super(R.xml.file_paths);
    }
}