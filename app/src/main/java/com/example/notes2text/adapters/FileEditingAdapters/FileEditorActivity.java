package com.example.notes2text.adapters.FileEditingAdapters;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.notes2text.R;
import com.example.notes2text.adapters.DirectoryAdapters.ActivitySwitchController;
import com.example.notes2text.adapters.JoinDirectoryAdapters.JoinController;
import com.example.notes2text.usecases.FileEditingUseCases.EditTextBoundary;
import com.example.notes2text.usecases.FileEditingUseCases.EditTextInteractor;
import com.example.notes2text.usecases.FileEditingUseCases.JoinFileBoundary;
import com.example.notes2text.usecases.FileEditingUseCases.JoinFiles;
import com.example.notes2text.usecases.FileEditingUseCases.OpenTextEditorBoundary;
import com.example.notes2text.usecases.FileEditingUseCases.OpenTextEditorInteractor;
import com.example.notes2text.entities.FileEditingEntities.TextFile;
import java.io.File;
import java.util.ArrayList;

/**
 * Displays text of selected text file in the file editor page. Registers the button clicks from the
 * user when editing file, joining files, saving files.
 */
public class FileEditorActivity extends AppCompatActivity {

    // Text box that displays txt file content that can be edited
    private EditText textToEdit;

    // Original state of text before any changes
    private Spannable original;

    // File to open
    private File file;

    // Path of the file
    private String path;

    // First file in the array when joining files (i.e., the first selected file)
    private File firstFile;

    /**
     * Extracts the text from the selected text file and displays it on the File editor.
     * Initiates the instance variables.
     * @param savedInstanceState Intent from ActivitySwitchController
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_editor);

        textToEdit = findViewById(R.id.editTextTextMultiLine);
        original = textToEdit.getText();

        Bundle bundle = getIntent().getExtras();
        // Receiving file from ActivitySwitchController or SavePopUpActivity
        file = (File) bundle.getSerializable("file");
        // Receiving array of selected files from JoinController to join files
        ArrayList<File> selectedFiles = (ArrayList<File>) bundle.getSerializable("selectedFiles");
        // Receiving text from SavePopUpActivity when user cancels saving file
        String text = (String) bundle.getSerializable("text");

        // if user cancels saving file
        if (text != null) {
            textToEdit.setText(text);
        // if received file from ActivitySwitchController or SavePopUpActivity
        } else if (file != null) {
            OpenTextEditorBoundary openTextEditor = new OpenTextEditorInteractor(file);
            // Extract content from file to display to user
            String content = openTextEditor.extractContent();
            path = file.getAbsolutePath();
            firstFile = file;
            textToEdit.setText(content);
        // if received array list of files from JoinController
        } else if (selectedFiles != null) {
            // Extract content from selected files and join them together to display to user
            firstFile = selectedFiles.get(0);
            path = firstFile.getAbsolutePath();
            JoinFileBoundary joinFiles = new JoinFiles(selectedFiles);
            String joinedText = joinFiles.extractContent();
            textToEdit.setText(joinedText);
        }
    }

    /**
     * To capitalize selected text
     * @param view When user clicks the capitalize button
     */
    public void buttonCapitalize(View view) {
        Spannable text = new SpannableStringBuilder(textToEdit.getText());
        // get index of text that is selected
        int starting = textToEdit.getSelectionStart();
        int ending = textToEdit.getSelectionEnd();

        EditTextBoundary editText = new EditTextInteractor(text);
        String alteredText = editText.capitalize(starting, ending);

        textToEdit.setText(alteredText);
    }

    /**
     * To lowercase selected text
     * @param view When user clicks the lowercase button
     */
    public void buttonLowercase(View view) {
        Spannable text = new SpannableStringBuilder(textToEdit.getText());
        // get index of text that is selected
        int starting = textToEdit.getSelectionStart();
        int ending = textToEdit.getSelectionEnd();

        EditTextBoundary editText = new EditTextInteractor(text);
        String alteredText = editText.lowercase(starting, ending);

        textToEdit.setText(alteredText);
    }

    /**
     * To reset text to the original text
     * @param view When user presses the reset button
     */
    public void buttonReset(View view) {
        Spannable text = original;
        textToEdit.setText(text);
    }

    /**
     * Go back to the directory
     * @param view When user clicks the home button
     */
    public void buttonHome(View view) {
        if (file != null) {
            returnToDirectory(file);
        } else if (firstFile != null) {
            returnToDirectory(firstFile);
        }
    }

    /**
     * Brings user to the SavePopUpActivity to input name of new file
     * @param view When user clicks the save button
     */
    public void buttonSave(View view) {
        // When saving standard text file
        if (file != null) {
            goToSavePopUpActivity(file);
        // When saving a joined text file
        } else if (firstFile != null)  {
            goToSavePopUpActivity(firstFile);
        }
    }

    /**
     * Bring user to JoinController to select which files they want to join together
     * @param view When user clicks the join button
     */
    public void buttonJoin(View view) {
        // Create intent to switch between activities
        Intent switchToJoin = new Intent(FileEditorActivity.this,
                JoinController.class);
        Bundle bundle = new Bundle();
        ArrayList<File> fileToSend = new ArrayList<>();
        fileToSend.add(file);

        // Get path of file without file name
        String actualPath = getPathNoName(file);

        /* Pass the path of file without file name and the currently opened file object in an array
            to JoinController */
        bundle.putSerializable("selectedFiles", fileToSend);
        switchToJoin.putExtra("path", actualPath);
        switchToJoin.putExtras(bundle);
        switchToJoin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Initiate the switching of activities
        try{
            startActivity(switchToJoin);
        } catch (Exception e) {
            String exceptionMessage = e.getMessage();
            assert exceptionMessage != null;
            int mid = exceptionMessage.length()/2;
            Toast.makeText(this, exceptionMessage.substring(mid), Toast.LENGTH_LONG).show();
        }
    }

    private void returnToDirectory(File file) {
        // Indicate current activity and which activity to switch to
        Intent switchToHome = new Intent(FileEditorActivity.this,
                ActivitySwitchController.class);
        TextFile currentFile = new TextFile(file);
        // Passing file path without the file name to ActivitySwitchController to open right folder
        String location = currentFile.getLocation();
        switchToHome.putExtra("path", location);
        // Switch to ActivitySwitchController
        startActivity(switchToHome);
    }

    private void goToSavePopUpActivity(File file) {
        String actualPath = getPathNoName(file);
        String text = textToEdit.getText().toString();

        Intent switchToSave = new Intent(FileEditorActivity.this,
                SavePopUpActivity.class);
        // Pass text on screen and path to SavePopUpActivity
        Bundle bundle = new Bundle();
        bundle.putSerializable("text", text);
        bundle.putSerializable("path", actualPath);
        bundle.putSerializable("file", file);
        switchToSave.putExtras(bundle);
        switchToSave.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(switchToSave);
        } catch (Exception e) {
            String exceptionMessage = e.getMessage();
            assert exceptionMessage != null;
            int mid = exceptionMessage.length() / 2;
            Toast.makeText(this, exceptionMessage.substring(mid), Toast.LENGTH_LONG).show();
        }
    }

    private String getPathNoName(File file) {
        // Get the path of current file without the name of file
        String fileName = file.getName();
        return path.replace(fileName, "");
    }
}
