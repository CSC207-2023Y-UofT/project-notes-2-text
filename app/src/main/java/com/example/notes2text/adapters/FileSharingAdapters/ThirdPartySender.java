package com.example.notes2text.adapters.FileSharingAdapters;

import com.example.notes2text.usecases.FileSharingUseCases.ThirdPartyOutputModel;
import com.example.notes2text.usecases.FileSharingUseCases.ThirdPartyOutputBoundary;

import java.util.ArrayList;

import android.net.Uri;
import android.content.Intent;
import android.util.Log;


public class ThirdPartySender implements ThirdPartyOutputBoundary {


    /**
     * Using the list of content Uri in the given outputModel, create a new Intent
     * to access the share sheet function. Only sends text file of any type. OutputModel
     * is returned so that context may be used to check the results of the action.
     *
     * @param outputModel A model containing a list of Uri, and a Context.
     */
    public void intentShare(ThirdPartyOutputModel outputModel){
        ArrayList<Uri> fileUris = outputModel.getFileUris();
        for (Uri uri : fileUris) {
            // Check for the Uri used for sharing. Ensure consistency with that provided by FileProvider.
            Log.i("File Uri for Sharing", String.valueOf(uri));
        }

        Intent sendIntent = new Intent();

        // FLAG_ACTIVITY_NEW_TASK needed for using context outside of an Activity class.
        sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sendIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
        sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, fileUris);

        // Check the flag of sendIntent, look for 268435456 (Constant for FLAG_ACTIVITY_NEW_TASK).
        Log.i("sendIntent flag", String.valueOf(sendIntent.getFlags()));

        // Send any type of file.
        sendIntent.setType("*/*");

        /* Made separate intent variable for creating chooser for ShareSheet, as permission need to
        be granted using FLAG_ACTIVITY_NEW_TASK. */
        Intent chooserIntent = Intent.createChooser(sendIntent, null);
        chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Check the flag of chooserIntent, look for 268435456 (Constant for FLAG_ACTIVITY_NEW_TASK).
        Log.i("chooserIntent flag", String.valueOf(sendIntent.getFlags()));

        // Create the Share Sheet.
        outputModel.getContext().startActivity(chooserIntent);
    }
}