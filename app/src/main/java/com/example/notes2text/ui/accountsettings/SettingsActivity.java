package com.example.notes2text.ui.accountsettings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.notes2text.R;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);


        Button UsernameView = (Button) findViewById(R.id.PopUpUsernameView);

        Button PasswordView = (Button) findViewById(R.id.PopUpPasswordView);


        Button EmailView = (Button) findViewById(R.id.PopUpEmailView);

        UsernameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, PopUpUsernameView.class));
            }
        });

        PasswordView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, PopUpPasswordView.class));
            }
        });

        EmailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, PopUpEmailView.class));
            }
        });
    }
}