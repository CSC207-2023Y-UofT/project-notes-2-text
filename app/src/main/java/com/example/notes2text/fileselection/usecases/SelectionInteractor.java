package com.example.notes2text.fileselection.usecases;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SelectionInteractor implements SelectionInputBoundary {

    private ArrayList<File> selectedFiles;

    public SelectionInteractor(){
        selectedFiles = new ArrayList<File>();
    }

    @Override
    public ArrayList<File> getSelectedFiles() {
        return selectedFiles;
    }

    @Override
    public void addItem(File file) {
        if(!(selectedFiles.contains(file))){
            selectedFiles.add(file);
        }
    }

    @Override
    public void removeItem(File file) {
        if (selectedFiles.contains(file)){
            selectedFiles.remove(file);
        }
    }

    @Override
    public void cancel() {
        selectedFiles.clear();
    }

    @Override
    public void move(String moveToAddress) {
        // Attempt 1

//        for (File selectedFile: selectedFiles) {
//            InputStream in = null;
//            OutputStream out = null;
//            try{
//                File moveToFolder = new File(moveToAddress);
//                if(!moveToFolder.exists()){
//                    moveToFolder.mkdirs();
//                }
//
//                in = new FileInputStream(selectedFile.getAbsolutePath());
//                out =  new FileOutputStream(moveToAddress + "\\" + selectedFile.getName());
//
//                byte[] buffer = new byte[1024];
//                int read;
//                while ((read = in.read(buffer)) != -1){
//                    out.write(buffer, 0, read);
//                }
//                in.close();
//
//                out.flush();
//                out.close();
//                out = null;
//
//                //deleting the original file
////                selectedFile.delete();
//            } catch (FileNotFoundException fnfE){
//                Log.e("tag", fnfE.getMessage());
//                fnfE.printStackTrace();
//            } catch (IOException ioE){
//                Log.e("tag", ioE.getMessage());
//                ioE.printStackTrace();
//            }
//        }

        //Attempt 2

        for (File selectedFile: selectedFiles) {

            String fileName = selectedFile.getName();
            File destFile = new File(moveToAddress + "/" + fileName);
            selectedFile.renameTo(destFile);

        }

    }
}
