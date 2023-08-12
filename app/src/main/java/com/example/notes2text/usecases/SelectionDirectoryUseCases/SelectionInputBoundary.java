package com.example.notes2text.usecases.SelectionDirectoryUseCases;

import java.io.File;
import java.util.ArrayList;

public interface SelectionInputBoundary {

    ArrayList<File> getSelectedFiles();

    void addItem(File file);

    void removeItem(File file);

    void cancel();

    void move(String moveToAddress);
}
