package com.example.notes2text.usecases.FileEditingUseCases;

import java.io.File;

public interface OpenTextEditorBoundary {

    public String extractContent();

    public File getLastOpened();

}
