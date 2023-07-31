package com.example.notes2text.adapters;

import android.content.Context;

public interface DirectoryAccessOutputBoundary {
    //Folder Creation
    void FolderCreationSuccess(Context context);

    void FolderCreationFailure(Context context);

    void BackLayerSuccess(Context context);

    void BackLayerFailure(Context context);

    //Selection view switch
}
