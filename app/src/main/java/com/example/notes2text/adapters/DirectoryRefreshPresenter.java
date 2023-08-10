package com.example.notes2text.adapters;

import android.content.Context;
import android.content.Intent;

public class DirectoryRefreshPresenter implements DirectoryRefreshOutputBoundary {

    public void refreshDirectory (Context context, String directoryPath) {
        Intent intent = new Intent(context, ActivitySwitchController.class);
        intent.putExtra("path",directoryPath);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
