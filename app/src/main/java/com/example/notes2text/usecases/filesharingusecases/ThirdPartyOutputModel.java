package com.example.notes2text.usecases.filesharingusecases;

import java.util.ArrayList;

import android.net.Uri;
import android.content.Context;

public class ThirdPartyOutputModel{
    private final ArrayList<Uri> fileUris;
    private final Context context;

    /**
     * Constructor for ThirdPartyOutputModel, creates a model to send information outward in layers.
     *
     * @param context A Context that can be used to access Android system functions.
     * @param shareFiles an ArrayList of Uris of the files that needs to be shared.
     */
    public ThirdPartyOutputModel(Context context, ArrayList<Uri> shareFiles){
        this.fileUris = shareFiles;
        this.context = context;
    }

    /**
     * Getter function for the stored ArrayList of Uris.
     *
     * @return An ArrayList of content type Uris.
     */
    public ArrayList<Uri> getFileUris(){
        return fileUris;
    }

    /**
     * Getter function for the stored Context.
     *
     * @return A Context that can be used to access Android system functions.
     */
    public Context getContext() {
        return context;
    }
}
