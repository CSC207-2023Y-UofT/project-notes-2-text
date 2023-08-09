package com.example.notes2text;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.example.notes2text.adapters.ActivitySwitchController;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {


    //On creating this activity, create listeners for every button on screen (placeholder for login).
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set content view connects view (activity_main) to the controller (MainActivity).
        setContentView(R.layout.activity_main);

        MaterialButton loginDirectoryBtn = findViewById(R.id.log2dirBtn);

        loginDirectoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initially, check whether crucial permissions were granted by the user.
                if (checkPermission()){
                    //Inform user that permission had been previously granted.
                    Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    //Use android intents to switch to the main component of the app.
                    //Development note: DirectoryActivity to test the directory, ActivitySwitchController
                    // for the full version  with the navigation bar.
                    Intent intent = new Intent(MainActivity.this, ActivitySwitchController.class);
                    String path = Environment.getExternalStorageDirectory().getPath();
                    intent.putExtra("path",path);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Permission Not Yet Granted", Toast.LENGTH_SHORT).show();
                    requestPermission(); // Permission needs to be granted still.
                }
            }
        });


    }

    //Checks to see whether access to android storage directory has been granted or not.
    private boolean checkPermission(){
        int checkPerm = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return checkPerm == PackageManager.PERMISSION_GRANTED;
    }

    //Requests permission from the user to access system storage directory.
    private void requestPermission(){
        //If user previously denied the directory access request, ask them to enable from settings.
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            Toast.makeText(MainActivity.this,"Please enable storage access from your settings.",Toast.LENGTH_SHORT).show();
        } else {
            //Interact with this activity to create a temporary window asking for storage access permission
            ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},111);
        }
    }



}