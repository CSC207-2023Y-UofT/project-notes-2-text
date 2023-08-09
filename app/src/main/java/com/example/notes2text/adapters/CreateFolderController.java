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

import java.io.File;

public class CreateFolderController extends AppCompatDialogFragment {
    private EditText editTextFolderName;
    private final FolderCreationInteractor createFolder;
    private final File currentLayer;

    /**
     * Constructor for the controller class. Obtains context to initiate a new FolderCreationInterator.
     * The Interactor will be used for making the folder according to user input.
     *
     * @param context A Context, needed for initiating a new FolderCreationInteractor.
     * @param currentLayer A File that indicates the current directory user sees. Used to obtain a path
     *                     to create the folder in.
     */
    public CreateFolderController(Context context, File currentLayer) {
        //Initialize the folder creation use case class with its required presenter to display message.
        DirectoryAccessOutputBoundary output = new DirectoryAccessPresenter();
        createFolder = new FolderCreationInteractor(output, context);
        this.currentLayer = currentLayer;
    }

    /**
     * A Default method in the AppCompatDialogFragment which can be used to create the Dialog for
     * user input. Overrode to add specific actions according to user input.
     *
     * @param savedInstanceState The last saved instance state of the Fragment,
     * or null if this is a freshly created Fragment.
     *
     * @return Returns the builder. This call is used to construct and show the Dialog.
     */
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
                    String filePath = currentLayer.getAbsolutePath();
                    createFolder.create(folderName, filePath);
                });
        // Obtain the user input.
        editTextFolderName = view.findViewById(R.id.create_folder_name);

        // This call allow construction and display of the Dialog.
        return builder.create();
    }
}
