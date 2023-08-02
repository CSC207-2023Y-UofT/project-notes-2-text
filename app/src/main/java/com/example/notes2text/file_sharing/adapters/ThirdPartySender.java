package com.example.notes2text.file_sharing.adapters;

import com.example.notes2text.file_sharing.use_case.ThirdPartyOutputModel;
import com.example.notes2text.file_sharing.use_case.ThirdPartyOutputBoundary;

import java.util.ArrayList;

import android.net.Uri;
import android.content.Intent;


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

        Intent sendIntent = new Intent();

        // FLAG_ACTIVITY_NEW_TASK needed for using context outside of an Activity class.
        sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sendIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
        sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, fileUris);

        // Grants per-use permission for other app to receive files.
        sendIntent.addFlags(
                Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        // Send any type of text file.
        sendIntent.setType("text/*");

        /* Made separate intent variable for creating chooser for ShareSheet, as permission need to
        be granted using FLAG_ACTIVITY_NEW_TASK. */
        Intent chooserIntent = Intent.createChooser(sendIntent, null);
        chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Create the Share Sheet.
        outputModel.getContext().startActivity(chooserIntent);
    }
}