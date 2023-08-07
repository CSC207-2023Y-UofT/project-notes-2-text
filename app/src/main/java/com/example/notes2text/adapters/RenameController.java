package com.example.notes2text.adapters;

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

import com.example.notes2text.R;
import com.example.notes2text.usecases.FileMenuInputBoundary;

public class RenameController extends AppCompatDialogFragment {
    private EditText editTextFileName;
    private final Context context;
    private final FileMenuInputBoundary fileMenuUseCase;

    public RenameController (Context context, FileMenuInputBoundary fileMenuUseCase) {
        this.context = context;
        this.fileMenuUseCase = fileMenuUseCase;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        // Inflates the popup.
        View view = inflater.inflate(R.layout.dialog_rename_file, null);

        // Set up the popup window with buttons.
        builder.setView(view)
                .setTitle("Rename File")
                // Empty as it simply closes, no action performed.
                .setNegativeButton("cancel", (dialogInterface, i) -> {
                })
                // Change the name of the file.
                .setPositiveButton("ok", (dialogInterface, i) -> {
                    String newFileName = editTextFileName.getText().toString();
                    fileMenuUseCase.rename(context, newFileName);
                });
        // Dialogue closes.

        // Obtain the user input of the File name
        editTextFileName = view.findViewById(R.id.new_file_name);

        return builder.create();
    }
}
