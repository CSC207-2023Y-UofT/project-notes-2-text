package com.example.notes2text.adapters.directoryadapters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.notes2text.R;
import com.example.notes2text.adapters.directoryadapters.fragments.AccountViewController;
import com.example.notes2text.adapters.directoryadapters.fragments.DirectoryAccessController;
import com.example.notes2text.adapters.directoryadapters.fragments.OCRFragmentController;
import com.example.notes2text.databinding.ActivityViewBinding;
import com.example.notes2text.adapters.selectiondirectoryadapters.SelectionController;

import java.io.File;
import java.util.ArrayList;

/**
 * ActivitySwitchController controls the navigation bar that switches the displayed fragment between
 * the file manager/directory, the OCR manager and the account settings fragments. It also
 * redirects between the directory and the file selection fragments depending on whether files have been
 * selected.
 */
public class ActivitySwitchController extends AppCompatActivity {

    ActivityViewBinding binding;
    String initialPath;


    /**
     * Reads the saved instance state and generates the switching behavior depending on information
     * sent to this activity through the Intent system ("path" - file path, "selectedFiles" - files
     * to be loaded into the selected files list in the selection fragment.
     * @param savedInstanceState A bundle contained the saved state of the activity. Allows resuming the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //get the path from the intent that passed to this activity and bundle it.
        initialPath = getIntent().getStringExtra("path");
        ArrayList<File> initialFileList = new ArrayList<>();
        //Initialization required for certain outcome of if-else logic.
        try{
        Bundle bundle = getIntent().getExtras();
        initialFileList = (ArrayList<File>) bundle.getSerializable("selectedFiles");
        // This is safe: the cast is required, and selectedFiles is only ever used with a list of files.
        } catch (ClassCastException e){
            initialFileList = new ArrayList<>();
        }
        if (initialFileList == null){
            DirectoryAccessController directoryAccessController = DirectoryAccessController.newInstance(initialPath);
            replaceFragment(directoryAccessController);
            Log.i("switch", "directory controller");
        } else if (initialFileList.isEmpty()) {
            SelectionController selectionController = SelectionController.newInstance(initialPath);
            replaceFragment(selectionController);
            Log.i("switch", "selection controller");
        } else{
            SelectionController selectionController = SelectionController.newInstance(initialPath, initialFileList);
            replaceFragment(selectionController);
            Toast.makeText(getApplicationContext(), "ActivitySwitchController received files", Toast.LENGTH_SHORT).show();
            Log.i("switch", "selection controller with files");
        }

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();
            if (itemId == R.id.directoryButton) {
                DirectoryAccessController directoryResume = DirectoryAccessController.newInstance(initialPath);
                replaceFragment(directoryResume);
            } else if (itemId == R.id.OCRButton) {
                replaceFragment(new OCRFragmentController());
            } else if (itemId == R.id.AccountButton) {
                replaceFragment(new AccountViewController());
            }

            return true;
        });
    }

    /**
     * Transitions to the Fragment fragment. Replaces the contents of the frame with fragment.
     * @param fragment The fragment to be displayed in the activity's frame.
     */
    //Switched to public from private.
        public void replaceFragment(Fragment fragment){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.activity_view_frame, fragment);
            fragmentTransaction.commit();
            Log.i("replaced", "fragment replaced");
        }
}