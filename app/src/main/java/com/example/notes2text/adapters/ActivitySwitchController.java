package com.example.notes2text.adapters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.example.notes2text.R;
import com.example.notes2text.adapters.fragments.AccountViewController;
import com.example.notes2text.adapters.fragments.DirectoryAccessController;
import com.example.notes2text.adapters.fragments.OCRFragmentController;
import com.example.notes2text.databinding.ActivityViewBinding;
import com.example.notes2text.fileselection.adapters.SelectionController;

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
//        Bundle bundle = new Bundle();
//        bundle.putString("path", initialPath);
        ArrayList<File> initialFileList = new ArrayList<File>();
        try{
        Bundle bundle = getIntent().getExtras();
        initialFileList = (ArrayList<File>) bundle.getSerializable("selectedFiles");
        } catch (ClassCastException e){
            initialFileList = new ArrayList<File>();
        }
        if (initialFileList == null){
            DirectoryAccessController dirAcCntrl = DirectoryAccessController.newInstance(initialPath);
            replaceFragment(dirAcCntrl);
        } else if (initialFileList.isEmpty()) {
            SelectionController sltnCntrl = SelectionController.newInstance(initialPath);
            replaceFragment(sltnCntrl);
        } else{
            SelectionController sltnCntrl = SelectionController.newInstance(initialPath, initialFileList);
            replaceFragment(sltnCntrl);
            Toast.makeText(getApplicationContext(), "ActivitySwitchController received files", Toast.LENGTH_SHORT).show();
        }
//        DirectoryAccessController dirAcCntrl = DirectoryAccessController.newInstance(initialPath);
//        //Create a new instance of the directory controller and send the arguments to it.
////        DirectoryAccessController dirAcCntrl = new DirectoryAccessController();
////        dirAcCntrl.setArguments(bundle);
//        // On first load, open to directory view with the root address.
//        replaceFragment(dirAcCntrl);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();
            if (itemId == R.id.directoryButton) {
//                //Create a bundle containing the required information to send to the directory activity.
//                //Required information: current root address, context.
//                Bundle bundle2 = new Bundle();
//                bundle2.putString("path", initialPath);
//                //Create a new instance of the DirectoryAccess activity and pass the bundle to it.
//                DirectoryAccessController dirAccess = new DirectoryAccessController();
//                replaceFragment(dirAccess);
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
        }
}