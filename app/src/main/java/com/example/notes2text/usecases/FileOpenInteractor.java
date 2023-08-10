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

    /**
     * Gets the MIME type of file, for text documents or image type files. Else, returns N/A.
     * @param file of which the MIME type is needed.
     * @return the MIME type of the file.
     */
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

    /**
     * Attempts to open fileToOpen. If image, opens in system gallery. If text file, opens in
     * application text editor.
     * @param context Application context.
     * @param fileToOpen file to be opened.
     */
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
                docIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                try {
                    context.startActivity(docIntent);
                } catch (Exception e){
//                    String exceptionMessage = e.getMessage();
//                    int mid = exceptionMessage.length()/2;
                    Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
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
