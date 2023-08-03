package com.example.notes2text.adapters.FileEditingAdapters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

import com.example.notes2text.R;
import com.example.notes2text.adapters.ActivitySwitchController;
import com.example.notes2text.usecases.FileEditingUseCases.EditTextInteractor;
import com.example.notes2text.usecases.FileEditingUseCases.OpenTextEditorInteractor;
import com.example.notes2text.usecases.FileEditingUseCases.TextFile;

import java.io.File;

public class FileEditorActivity extends AppCompatActivity {

    private EditText textToEdit;

    private Spannable original;

    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_editor);

        textToEdit = findViewById(R.id.editTextTextMultiLine);
        original = textToEdit.getText();

        // Receiving file from ActivitySwitchController
        file = (File)getIntent().getSerializableExtra("file");
        OpenTextEditorInteractor openTextEditor = new OpenTextEditorInteractor(file);
        String content = openTextEditor.extractContent();
        // Display text from txt file
        textToEdit.setText(content);


    }

    public void buttonCapitalize(View view) {

        Spannable text = new SpannableStringBuilder(textToEdit.getText());
        // get index of text that is selected
        int starting = textToEdit.getSelectionStart();
        int ending = textToEdit.getSelectionEnd();

        EditTextInteractor editText = new EditTextInteractor(text);
        String alteredText = editText.capitalize(starting, ending);

        textToEdit.setText(alteredText);

    }

    public void buttonLowercase(View view) {

        Spannable text = new SpannableStringBuilder(textToEdit.getText());
        int starting = textToEdit.getSelectionStart();
        int ending = textToEdit.getSelectionEnd();

        EditTextInteractor editText = new EditTextInteractor(text);
        String alteredText = editText.lowercase(starting, ending);

        textToEdit.setText(alteredText);
    }

    public void buttonReset(View view) {
        Spannable text = original;
        textToEdit.setText(text);
    }

    // Switch to ActivitySwitchController when pressing home button
    public void buttonHome(View view) {
        Intent switchToHome = new Intent(FileEditorActivity.this,
                ActivitySwitchController.class);
        TextFile currentFile = new TextFile(file);
        // Passing file path without the file name to ActivitySwitchController to open right folder
        String location = currentFile.getLocation();
        switchToHome.putExtra("path", location);
        startActivity(switchToHome);
    }

    public void buttonSave(View view) {
        // passes the file path when switching to save screen
        String path = file.getAbsolutePath();
        String fileName = file.getName();
        String actualPath = path.replace(fileName, "");
        String text = textToEdit.getText().toString();

        Intent switchToSave = new Intent(FileEditorActivity.this,
                SavePopUpActivity.class);
        //passing txt file path and txt file content to SavePopUpActivity
        switchToSave.putExtra("path", actualPath);
        switchToSave.putExtra("text", text);
        startActivity(switchToSave);


    }
}