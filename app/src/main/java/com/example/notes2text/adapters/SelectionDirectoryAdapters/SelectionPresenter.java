package com.example.notes2text.adapters.SelectionDirectoryAdapters;

import android.content.Context;
import android.widget.Toast;

public class SelectionPresenter {
    public void MoveFileSuccess(Context context) {
        Toast.makeText(context, "File moved successfully", Toast.LENGTH_SHORT).show();
    }
    public void MoveFileFailure(Context context) {
        Toast.makeText(context, "File movement error", Toast.LENGTH_SHORT).show();
    }

    public void MoveFileNoFiles(Context context) {
        Toast.makeText(context, "No files have been selected.", Toast.LENGTH_SHORT).show();
    }

    public void InheritFilesSuccess(Context context){
        Toast.makeText(context, "Inherited files from previous", Toast.LENGTH_SHORT).show();
    }

    public void showFileAddress(Context context, String fileLocation, String fileName){
        Toast.makeText(context, fileLocation + "/" + fileName, Toast.LENGTH_SHORT).show();
    }
}
