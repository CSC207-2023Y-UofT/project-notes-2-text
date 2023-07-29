package com.example.notes2text.file_sharing.use_case;

import java.util.ArrayList;

import android.net.Uri;
import android.content.Context;

public class ThirdPartyOutputModel{
    private final ArrayList<Uri> fileUris;
    private final Context context;

    public ThirdPartyOutputModel(Context context, ArrayList<Uri> shareFiles){
        this.fileUris = shareFiles;
        this.context = context;
    }

    public ArrayList<Uri> getFileUris(){
        return fileUris;
    }

    public Context getContext() {
        return context;
    }
}
