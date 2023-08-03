package com.example.notes2text.adapters.FileEditingAdapters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notes2text.R;
import com.example.notes2text.usecases.FileEditingUseCases.SaveFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SavePopUpActivity extends AppCompatActivity {

    private EditText fileName;
    private String filePath;
    private String content;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_pop_up);

        fileName = findViewById(R.id.enter_file_name);

        filePath = getIntent().getStringExtra("path");
        content = getIntent().getStringExtra("text");

    }

    //Return back to FileEditorActivity
    public void buttonCancel(View view) {
        Intent switchToEditor = new Intent(SavePopUpActivity.this,
                FileEditorActivity.class);
        startActivity(switchToEditor);
    }

    public void buttonConfirm(View view) {
        String newName = fileName.getText().toString();

        // create new txt file and save it
        SaveFile save = new SaveFile(content);
        File newFile = save.createFile(SavePopUpActivity.this, newName);
        // saveFile not implemented yet
        save.saveFile(newFile);
        // return back to FileEditorActivity
        Intent switchToEditor = new Intent(SavePopUpActivity.this,
                FileEditorActivity.class);
        startActivity(switchToEditor);
    }
}