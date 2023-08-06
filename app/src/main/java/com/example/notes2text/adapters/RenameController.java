package com.example.notes2text.adapters;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.notes2text.R;

public class RenameController extends AppCompatDialogFragment {
    private EditText editTextFileName;
    private final Context context;

    public RenameController (Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        // Inflates the popup.
        View view = inflater.inflate(R.layout.dialog_rename_file, null);

        // Set up the popup window with buttons.
        // Empty as it simply closes, no action performed.
        builder.setView(view)
                .setTitle("Rename File")
                .setNegativeButton("cancel", (dialogInterface, i) -> {
                })
                .setPositiveButton("ok", (dialogInterface, i) -> {
                });
        // Dialogue closes.

        // Obtain the user input of the File name
        editTextFileName = view.findViewById(R.id.new_file_name);

        return builder.create();
    }
}
