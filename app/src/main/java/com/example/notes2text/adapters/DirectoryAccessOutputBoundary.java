package com.example.notes2text.adapters;

import android.content.Context;

public interface DirectoryAccessOutputBoundary {
    //Folder Creation
    void FolderCreationSuccess(Context context);
    //Edited for two different failure messages for folder creation
    void FolderCreationFailureSameName(Context context);

    void FolderCreationFailureInvalid(Context context);

    void BackLayerSuccess(Context context);

    void BackLayerFailure(Context context);

    //Selection view switch
}
