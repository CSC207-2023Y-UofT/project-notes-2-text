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

/**
 * Registers the file name the user set to save file under. Registers the cancelling or saving of\
 * files.
 */
public class SavePopUpActivity extends AppCompatActivity {

    // text box used for the user to set the file name
    private EditText fileName;

    /* filePath is the file path of the original text file without the file name (used to indicate
    where to store the newly saved file) */
    private String filePath;

    // newly edited text to add to new file
    private String content;

    // file object of text file to save
    private File file;

    /**
     * Initiate local variables
     * @param savedInstanceState Intent from FileEditorActivity
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_pop_up);

        fileName = findViewById(R.id.enter_file_name);

        filePath = getIntent().getStringExtra("path");
        content = getIntent().getStringExtra("text");

        Bundle bundle = getIntent().getExtras();
        file = (File)bundle.getSerializable("file");


    }

    /**
     * Bring user back to FileEditorActivity (file editing page)
     * @param view When user clicks the Cancel button
     */
    public void buttonCancel(View view) {
        Intent switchToEditor = new Intent(SavePopUpActivity.this,
                FileEditorActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("text", content);
        bundle.putSerializable("file", file);
        switchToEditor.putExtras(bundle);
        switchToEditor.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(switchToEditor);
        } catch (Exception e) {
            String exceptionMessage = e.getMessage();
            assert exceptionMessage != null;
            int mid = exceptionMessage.length() / 2;
            Toast.makeText(this, exceptionMessage.substring(mid), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Creates a new File containing the edited text and saves it in the same folder as the original
     * file. It is saved under the name set by the user. Returns user back to the File editing page
     * @param view When user clicks the Confirm button
     */
    public void buttonConfirm(View view) {
        String newName = fileName.getText().toString();

        // create new txt file and save it
        SaveFile save = new SaveFile(content);
        File newFile = save.createFile(this, newName);

        // save txt file to same folder as original txt file
        String stringPath = filePath + "/" + newName + ".txt";
        save.saveFile(this, newFile, stringPath);

        // return to FileEditor displaying the new file
        Intent switchToEditor = new Intent(SavePopUpActivity.this,
                FileEditorActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("file", newFile);
        switchToEditor.putExtras(bundle);
        switchToEditor.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(switchToEditor);
        } catch (Exception e) {
            String exceptionMessage = e.getMessage();
            assert exceptionMessage != null;
            int mid = exceptionMessage.length() / 2;
            Toast.makeText(this, exceptionMessage.substring(mid), Toast.LENGTH_LONG).show();
        }
    }
}

