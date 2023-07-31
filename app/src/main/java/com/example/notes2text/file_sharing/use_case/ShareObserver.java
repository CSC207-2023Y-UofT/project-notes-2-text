package com.example.notes2text.file_sharing.use_case;

import com.example.notes2text.file_sharing.adapters.ThirdPartySender;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;

public class ShareObserver {

    /**
     * Called when the FileMenuInteractor wants to perform a share. This method initialize the
     * necessary classes for sharing with Android ShareSheet, and perform the share function.
     * @param context Context of the application, can show the current state.
     * @param files List of IO.File that point to what the user wants to share.
     */
    public void share(Context context, ArrayList<File> files){
        FileSharingModel fileToShare = new FileSharingModel(context, files);
        ThirdPartyOutputBoundary shared = new ThirdPartySender();
        ThirdParShareUseCase sharing = new ThirdParShareUseCase(shared);
        sharing.share(fileToShare);
    }
}
