package com.example.notes2text.adapters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.notes2text.R;

import java.io.File;

public class DirectoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);

        // Locate the layout elements in directory_activity. File List's RecyclerView and the noFile text.
        TextView noFiles = findViewById(R.id.noFilesText);
        RecyclerView fileListView = findViewById(R.id.file_list);

        // Retrieve the file path from the intent.
        String path = getIntent().getStringExtra("path");
        // Get the address of the folder currently to be shown.
        File root = new File(path);
        // Retrieve the list of files and folders from the path address.
        File[] filesDirectory = root.listFiles();

        // Assign the file list at the address to the file list recyclerview.
        // First check whether the file list is empty. Otherwise, display the retrieved list of files.
        if (filesDirectory == null || filesDirectory.length == 0){
            noFiles.setVisibility(View.VISIBLE);
        } else{
            // set noFiles to Invisible
            noFiles.setVisibility(View.INVISIBLE);
            //Assign Linear layout to file list.
            fileListView.setLayoutManager(new LinearLayoutManager(this));
            //Assign the custom adaptor to the View elements.
            fileListView.setAdapter(new FileListAdaptor(getApplicationContext(), filesDirectory));
        }




    }
}