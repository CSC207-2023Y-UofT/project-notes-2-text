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

    private EditText textToEdit;

    private Spannable original;

    private File file;

    /**
     * Extracts the text from the selected text file and displays it on the File editor.
     * Initiates the instance variables.
     * textToEdit is the text box that displays the content from the selected text file which the
     * user can edit.
     * original is the unedited text from the selected text file
     * file is the file object of the selected text file
     * @param savedInstanceState Intent from ActivitySwitchController
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_editor);

        textToEdit = findViewById(R.id.editTextTextMultiLine);
        original = textToEdit.getText();

        Bundle bundle = getIntent().getExtras();
        // Receiving file from ActivitySwitchController
        file = (File)bundle.getSerializable("file");
        // Receiving array of selected files from JoinController
        ArrayList<File> selectedFiles = (ArrayList<File>)bundle.getSerializable("selectedFiles");

        // if received file from ActivitySwitchController
        if (file != null) {
            OpenTextEditorBoundary openTextEditor = new OpenTextEditorInteractor(file);
            String content = openTextEditor.extractContent();
            textToEdit.setText(content);
        // if received array list of files from JoinController
        } else if (selectedFiles != null) {
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
        Intent switchToHome = new Intent(FileEditorActivity.this,
                ActivitySwitchController.class);
        TextFile currentFile = new TextFile(file);
        // Passing file path without the file name to ActivitySwitchController to open right folder
        String location = currentFile.getLocation();
        switchToHome.putExtra("path", location);
        startActivity(switchToHome);
    }

    /**
     * Brings user to the SavePopUpActivity to input name of new file
     * @param view When user clicks the save button
     */
    public void buttonSave(View view) {
        // passes the file path when switching to save screen
        String path = file.getAbsolutePath();
        String fileName = file.getName();
        String actualPath = path.replace(fileName, "");
        String text = textToEdit.getText().toString();

        Intent switchToSave = new Intent(FileEditorActivity.this,
                SavePopUpActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("text", text);
        bundle.putSerializable("path", actualPath);
        switchToSave.putExtras(bundle);
        switchToSave.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(switchToSave);
        } catch (Exception e) {
            String exceptionMessage = e.getMessage();
            assert exceptionMessage != null;
            int mid = exceptionMessage.length()/2;
            Toast.makeText(this, exceptionMessage.substring(mid), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Bring user to JoinController to select which files they want to join together
     * @param view When user clicks the join button
     */
    public void buttonJoin(View view) {
        Intent switchToJoin = new Intent(FileEditorActivity.this,
                JoinController.class);
        Bundle bundle = new Bundle();
        ArrayList<File> fileToSend = new ArrayList<>();
        fileToSend.add(file);

        String path = file.getAbsolutePath();
        String fileName = file.getName();
        String actualPath = path.replace(fileName, "");

        bundle.putSerializable("selectedFiles", fileToSend);
        switchToJoin.putExtra("path", actualPath);

        switchToJoin.putExtras(bundle);
        switchToJoin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try{
            startActivity(switchToJoin);
        } catch (Exception e) {
            String exceptionMessage = e.getMessage();
            assert exceptionMessage != null;
            int mid = exceptionMessage.length()/2;
            Toast.makeText(this, exceptionMessage.substring(mid), Toast.LENGTH_LONG).show();
        }
    }
}
