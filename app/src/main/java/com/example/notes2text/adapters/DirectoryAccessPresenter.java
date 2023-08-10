package com.example.notes2text.adapters;

import android.content.Context;
import android.widget.Toast;

/**
 * Communicates key directory information to the user by displaying the messages on the current activity's view.
 */
public class DirectoryAccessPresenter implements DirectoryAccessOutputBoundary{
    /**
     * Informs the user that a folder has been successfully created.
     * @param context The activity where the message should be displayed to.
     */
    @Override
    public void FolderCreationSuccess(Context context) {
        Toast.makeText(context, "New folder made", Toast.LENGTH_SHORT).show();
    }

    /**
     * Informs the user that a folder could not be successfully created.
     * @param context The activity where the message should be displayed to.
     */
    @Override
    public void FolderCreationFailure(Context context) {
        Toast.makeText(context, "Invalid file name for new folder", Toast.LENGTH_SHORT).show();
    }

    /**
     * Informs the user of successful return to parent folder.
     * @param context The activity where the message should be displayed to.
     */
    @Override
    public void BackLayerSuccess(Context context) {
        Toast.makeText(context, "Returned to parent folder", Toast.LENGTH_SHORT).show();
    }

    /**
     * Informs the user that there is no parent folder to return to.
     * @param context The activity where the message should be displayed to.
     */
    @Override
    public void BackLayerFailure(Context context) {
        Toast.makeText(context, "No accessible parent folder", Toast.LENGTH_SHORT).show();
    }

}
