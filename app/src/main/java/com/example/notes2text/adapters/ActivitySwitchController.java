package com.example.notes2text.adapters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.notes2text.R;
import com.example.notes2text.adapters.fragments.AccountViewController;
import com.example.notes2text.adapters.fragments.DirectoryAccessController;
import com.example.notes2text.adapters.fragments.OCRFragmentController;
import com.example.notes2text.databinding.ActivityViewBinding;
import com.example.notes2text.ui.accountsettings.SettingsActivity;

public class ActivitySwitchController extends AppCompatActivity {

    ActivityViewBinding binding;
    String initialPath;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //get the path from the intent that passed to this activity and bundle it.
        initialPath = getIntent().getStringExtra("path");
//        Bundle bundle = new Bundle();
//        bundle.putString("path", initialPath);
        DirectoryAccessController dirAcCntrl = DirectoryAccessController.newInstance(initialPath);
        //Create a new instance of the directory controller and send the arguments to it.
//        DirectoryAccessController dirAcCntrl = new DirectoryAccessController();
//        dirAcCntrl.setArguments(bundle);
        // On first load, open to directory view with the root address.
        replaceFragment(dirAcCntrl);

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

        //Switched to public from private.
        public void replaceFragment(Fragment fragment){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.activity_view_frame, fragment);
            fragmentTransaction.commit();
        }
}