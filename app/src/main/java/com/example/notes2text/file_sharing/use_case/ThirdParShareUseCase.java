package com.example.notes2text.file_sharing.use_case;

import java.util.ArrayList;
import java.io.File;

import android.net.Uri;
import androidx.core.content.FileProvider;


public class ThirdParShareUseCase implements ThirdParShareInputBoundary {
    private final ThirdPartyOutputBoundary outputBoundary;

    public ThirdParShareUseCase(ThirdPartyOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }


    /**
     * Using the list of IO.File and the Context contained in the FileSharingModel to obtain
     * a list of content Uri. Using the list of Uri and the original Context, construct
     * and return an instance of ThirdPartyOutputModel.
     * @param inputFiles A model containing a list of IO.File, and a Context.
     * @return ThirdPartyOutputModel A model containing a list of Uri, and a Context.
     */
    @Override
    public ThirdPartyOutputModel create(FileSharingModel inputFiles) {

        ArrayList<File> files = inputFiles.getFile();
        ArrayList<Uri> fileUris = new ArrayList<>();
        ArrayList<String> filePaths = new ArrayList<>();
        for (File file : files) {
            filePaths.add(file.getAbsolutePath());
        }
        for (String filePath : filePaths) {
            File newFile = new File(filePath);

            /* Authority can be found in AndroidManifest Provider section */
            Uri uri = FileProvider.getUriForFile(inputFiles.getContext(),
                    "com.example.notes2text.file_sharing.use_case.MyFileProvider", newFile);
            fileUris.add(uri);
        }
        ThirdPartyOutputModel outputUri = new ThirdPartyOutputModel(inputFiles.getContext(),
                fileUris);
        return outputBoundary.intentShare(outputUri);
    }
}
