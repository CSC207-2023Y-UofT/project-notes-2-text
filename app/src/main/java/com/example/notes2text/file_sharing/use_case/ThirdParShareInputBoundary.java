package com.example.notes2text.file_sharing.use_case;

public interface ThirdParShareInputBoundary {
    ThirdPartyOutputModel create(FileSharingModel inputFiles);
}
