package com.example.notes2text.adapters.directoryadapters;

import android.content.Context;

public interface DirectoryRefreshOutputBoundary {

    /**
     * Refresh the current Directory that user is seeing.
     *
     * @param context A Context to use the Intent to refresh the Activity.
     * @param directoryPath The path to the current directory that user is in.
     */
    void refreshDirectory (Context context, String directoryPath);
}
