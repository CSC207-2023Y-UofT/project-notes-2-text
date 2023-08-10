package com.example.notes2text.adapters;

import android.content.Context;
import android.widget.Toast;

public class DirectoryAccessPresenter implements DirectoryAccessOutputBoundary{
    @Override
    public void FolderCreationSuccess(Context context) {
        Toast.makeText(context, "New folder made", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void FolderCreationFailureInvalid(Context context) {
        Toast.makeText(context, "Invalid file name", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void FolderCreationFailureSameName(Context context) {
        Toast.makeText(context, "Folder already exists", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void BackLayerSuccess(Context context) {
        Toast.makeText(context, "Return to parent folder", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void BackLayerFailure(Context context) {
        Toast.makeText(context, "No accessible parent folder", Toast.LENGTH_SHORT).show();
    }

}
