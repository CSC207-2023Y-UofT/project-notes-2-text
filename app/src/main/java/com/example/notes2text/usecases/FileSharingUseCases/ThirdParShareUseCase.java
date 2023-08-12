package com.example.notes2text.usecases.FileSharingUseCases;

import java.util.ArrayList;
import java.io.File;

import android.net.Uri;
import androidx.core.content.FileProvider;
import android.util.Log;


public class ThirdParShareUseCase {
    private final ThirdPartyOutputBoundary outputBoundary;

    public ThirdParShareUseCase(ThirdPartyOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }


    /**
     * Using the list of IO.File and the Context contained in the FileSharingModel to obtain
     * a list of content Uri. Using the list of Uri and the original Context, construct
     * and return an instance of ThirdPartyOutputModel.
     * @param inputFiles A model containing a list of IO.File, and a Context.
     */
    public void share(FileSharingModel inputFiles) {

        ArrayList<File> files = inputFiles.getFile();
        ArrayList<Uri> fileUris = new ArrayList<>();
        ArrayList<String> filePaths = new ArrayList<>();
        for (File file : files) {
            filePaths.add(file.getAbsolutePath());
        }
        for (String filePath : filePaths) {
            File newFile = new File(filePath);

            // Authority can be found in AndroidManifest Provider section
            Uri uri = FileProvider.getUriForFile(inputFiles.getContext(),
                    "com.example.notes2text.usecases.FileSharingUseCases.MyFileProvider", newFile);
            fileUris.add(uri);

            /* Since this method cannot be unit tested, Log is used to monitor output at runtime
            with Android Logcat instead. */
            Log.v("File conversion", String.valueOf(uri));
        }

        // Make a new outputModel with the new uris and the original context to pass a layer outward.
        ThirdPartyOutputModel outputUri = new ThirdPartyOutputModel(inputFiles.getContext(),
                fileUris);
        outputBoundary.intentShare(outputUri);
    }
}
