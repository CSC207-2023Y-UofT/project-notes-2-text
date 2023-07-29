package com.example.notes2text.usecases;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import java.io.File;

public class FileOpenInteractor {

    File file;

    public FileOpenInteractor(){
        super();

    }

    private String getFileType(File file){
        String fileName = file.getName();
        if (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".webp") || fileName.endsWith(".jpeg")
        || fileName.endsWith(".heif")){
            return "image/*";
        } else if (fileName.endsWith(".txt")) {
            return "text/plain";
        } else {
            return "N/A";
        }
    }

    public void openFile(Context context, File fileToOpen){
        try{
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            String fileType = getFileType(fileToOpen);
            if (fileType != "N/A") {
                intent.setDataAndType(Uri.parse(fileToOpen.getAbsolutePath()), fileType);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } else if (fileType != "text/plain") {
                // Pushes the file object to FileEditorActivity.
                // Use intent system with the calling context (which is presumably the current activity context)
                // Push to the text file editor activity.
//                Intent docIntent = new Intent(context, FileEditorActivity.class);
//                docIntent.putExtra("file", fileToOpen);
//                context.startActivity(docIntent);

                // On the way back, need to send the subdirectory path as extra object "path".
            } else {
                Toast.makeText(context.getApplicationContext(), "File not openable through Notes2Text", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context.getApplicationContext(), "File not Openable", Toast.LENGTH_SHORT).show();
        }
    }

}
