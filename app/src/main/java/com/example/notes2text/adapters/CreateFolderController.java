package com.example.notes2text.adapters;

import com.example.notes2text.R;
import com.example.notes2text.usecases.FolderCreationInteractor;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class CreateFolderController extends AppCompatDialogFragment {
    private EditText editTextFolderName;

    private final FolderCreationInteractor createFolder;

    public CreateFolderController(Context context) {
        //Initialize the folder creation use case class with its required presenter to display message.
        DirectoryAccessOutputBoundary output = new DirectoryAccessPresenter();
        createFolder = new FolderCreationInteractor(output, context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Create a new dialogue builder.
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        // Link to the dialogue xml file.
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_folder_create, null);


        // Build the pop up dialogue.
        // Empty as it should not do anything, simply ends the dialogue.
        // Should get the user input for folder name, and pass them to the interactor.
        builder.setView(view)
                .setTitle("Create Folder")

                // Button for cancelling folder creation.
                .setNegativeButton("cancel", (dialogInterface, i) -> {
                })

                // Button for confirming folder creation.
                .setPositiveButton("create", (dialogInterface, i) -> {
                    String folderName = editTextFolderName.getText().toString();
                    String filePath = String.valueOf(Environment.getExternalStorageDirectory());
                    createFolder.create(folderName, filePath);
                });
        editTextFolderName = view.findViewById(R.id.create_folder_name);

        return builder.create();
    }
}
