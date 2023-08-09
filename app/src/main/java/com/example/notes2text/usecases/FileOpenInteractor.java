package com.example.notes2text.usecases;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.notes2text.adapters.FileEditingAdapters.FileEditorActivity;

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
            if (fileType.equals("text/plain")) {
                Intent docIntent = new Intent(context, FileEditorActivity.class);
//                docIntent.putExtra("file", fileToOpen);
                Bundle bundle = new Bundle();
                bundle.putSerializable("file", fileToOpen);
                docIntent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(docIntent);
            } else if (fileType.equals("image/*")) {
                // Pushes the file object to FileEditorActivity.
                // Use intent system with the calling context (which is presumably the current activity context)
                // Push to the text file editor activity.
                intent.setDataAndType(Uri.parse(fileToOpen.getAbsolutePath()), fileType);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                // On the way back, need to send the subdirectory path as extra object "path".
            } else {
                Toast.makeText(context.getApplicationContext(), "File not openable through Notes2Text", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context.getApplicationContext(), "File not Openable", Toast.LENGTH_SHORT).show();
        }
    }

}
