package com.example.notes2text.adapters.DirectoryAdapters;

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
import com.example.notes2text.usecases.DirectoryUseCases.FileMenuInputBoundary;

public class RenameController extends AppCompatDialogFragment {
    private EditText editTextFileName;
    private final Context context;
    private final FileMenuInputBoundary fileMenuUseCase;
    private final DirectoryRefreshPresenter refresher;

    /**
     * Constructor for RenameController class.
     *
     * @param context Needed to use the method in fileMenuUseCase.
     * @param fileMenuUseCase An instance of FileMenuInputBoundary, which is implemented by
     *                        FileMenuInteractor. Used to access the use case for renaming file,
     *                        and alerting the user.
     */
    public RenameController (Context context, FileMenuInputBoundary fileMenuUseCase) {
        this.context = context;
        this.fileMenuUseCase = fileMenuUseCase;
        refresher = new DirectoryRefreshPresenter();
    }

    /**
     * A default method in AppCompatDialogFragment that can be used to create a new Dialog. Overrode
     * to add specific actions to perform according to user's interaction with the Dialog.
     *
     * @param savedInstanceState The last saved instance state of the Fragment,
     * or null if this is a freshly created Fragment.
     *
     * @return Returns the AlertDialog, this method call is used to construct the AlertDialog, which
     * is displayed when .show() method is called.
     */
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
                    fileMenuUseCase.rename(context, newFileName, refresher);
                });
        // Dialogue closes.

        // Obtain the user input of the File name
        editTextFileName = view.findViewById(R.id.new_file_name);

        return builder.create();
    }
}
