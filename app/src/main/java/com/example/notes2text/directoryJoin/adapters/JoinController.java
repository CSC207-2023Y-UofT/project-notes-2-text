package com.example.notes2text.directoryJoin.adapters;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes2text.R;
import com.example.notes2text.adapters.ActivitySwitchController;
import com.example.notes2text.adapters.DirectoryAccessOutputBoundary;
import com.example.notes2text.adapters.DirectoryAccessPresenter;
import com.example.notes2text.adapters.FileEditingAdapters.FileEditorActivity;
import com.example.notes2text.adapters.FileListAdaptor;
import com.example.notes2text.fileselection.adapters.SelectionController;
import com.example.notes2text.fileselection.adapters.SelectionListAdapter;
import com.example.notes2text.fileselection.adapters.SelectionPresenter;
import com.example.notes2text.fileselection.usecases.SelectionInputBoundary;
import com.example.notes2text.fileselection.usecases.SelectionInteractor;

import java.io.File;
import java.util.ArrayList;

public class JoinController extends AppCompatActivity {
    //Required collaborators
    private DirectoryAccessOutputBoundary directoryPresenter = new DirectoryAccessPresenter();

    private SelectionPresenter selectionPresenter = new SelectionPresenter();

    private SelectionInputBoundary selectionUseCase = new SelectionInteractor();

    private String filePath;
    private ArrayList<File> fileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_view);

        // Locate the layout elements in directory_activity. File List's RecyclerView and the noFile text.
        TextView noFiles = findViewById(R.id.noFilesText);
        RecyclerView fileListView = findViewById(R.id.file_list);

        filePath = getIntent().getStringExtra("path");

        if(filePath == null){
            filePath = Environment.getExternalStorageDirectory().getPath();
        }

        //get the current list of selected files from the intent.
        Bundle bundle = getIntent().getExtras();
        fileList = (ArrayList<File>) bundle.getSerializable("selectedFiles");

        //TODO: Get already open file as a file in an arraylist from intent.

        // Get the address of the folder currently to be shown.
        File root = new File(filePath);
        // Retrieve the list of files and folders from the path address.
        File[] filesDirectory = root.listFiles();

        //displays or removes no files dialog text.
        if (filesDirectory == null || filesDirectory.length == 0){
            noFiles.setVisibility(View.VISIBLE);
        } else{
            // set noFiles to Invisible
            noFiles.setVisibility(View.INVISIBLE);
        }

        //Set an adapter for the file list.

        //Assign Linear layout to file list.
        fileListView.setLayoutManager(new LinearLayoutManager(this));

        if (fileList != null && fileList.isEmpty()){
            fileListView.setAdapter(new JoinListAdapter(getApplicationContext(), filesDirectory, selectionUseCase, fileList));
        } else if (fileList != null ) {
            fileListView.setAdapter(new JoinListAdapter(getApplicationContext(), filesDirectory, selectionUseCase, fileList));
            selectionPresenter.InheritFilesSuccess(this);
        } else{
            fileListView.setAdapter(new JoinListAdapter(getApplicationContext(), filesDirectory, selectionUseCase));
        }

        setFragmentToolbar();

        setToolbarMenu(fileListView);
    }

    private void setFragmentToolbar() throws NullPointerException {
        Toolbar selectionToolbar = findViewById(R.id.join_manager_toolbar);
        setSupportActionBar(selectionToolbar);
    }

    private void setToolbarMenu(RecyclerView recyclerView) {
        addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                //Inflate the menu
                menuInflater.inflate(R.menu.join_toolbar, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.exit_join_button) {
                    Intent intent = new Intent(getApplicationContext(), ActivitySwitchController.class);
                    intent.putExtra("path", filePath);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else if (menuItem.getItemId() == R.id.back_button) {
                    //attempts to go back a layer while keeping list of selected files.
                    String higherPath = filePath;
                    File currLayerFile = new File(filePath);
                    File parentLayerFile = currLayerFile.getParentFile();
                    // This appears to work correctly: selecting a file and going back displays the file inheritance message
                    // and going back without selecting any files does not, so fileList is non-empty and has received the file correctly.
                    try {
                        higherPath = parentLayerFile.getAbsolutePath();
                        //gets the adapter that was set to the selectionView.
                        SelectionListAdapter selectionAdapter =  (SelectionListAdapter) recyclerView.getAdapter();
                        //assumes the adapter is a selectionAdapter. Actually goes back, so this is probably fine.
                        ArrayList<File> goBackFileList = selectionAdapter.getSelectedFiles();
                        //TODO: Implement back button functionality.
                        Intent intent = new Intent(getApplicationContext(), JoinController.class);
                        intent.putExtra("path", higherPath);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("selectedFiles", goBackFileList);
                        intent.putExtras(bundle);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        directoryPresenter.BackLayerSuccess(getApplicationContext());
                    } catch (NullPointerException e){
                        directoryPresenter.BackLayerFailure(getApplicationContext());
                    }
                } else if (menuItem.getItemId() == R.id.join_button) {
                    try{
                        SelectionListAdapter selectionAdapter =  (SelectionListAdapter) recyclerView.getAdapter();
                        ArrayList<File> selectedFilesList = selectionAdapter.getSelectedFiles();
                        ArrayList<File> txtFilesList = new ArrayList<File>();
                        //get just the selected text files.
                        for (File file: selectedFilesList) {
                            if(file.getName().endsWith(".txt")){
                                txtFilesList.add(file);
                            }
                        }
                        Intent intent = new Intent(getApplicationContext(), FileEditorActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("selectedFiles", txtFilesList);
                        intent.putExtras(bundle);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }catch (NullPointerException nullE){
                        Log.e("tag", nullE.getMessage());
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
                return true;
            }
        });
    }

}