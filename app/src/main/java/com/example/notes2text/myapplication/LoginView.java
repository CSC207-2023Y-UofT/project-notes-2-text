package com.example.notes2text.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes2text.R;

public class LoginView extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TextView login = (TextView) findViewById(R.id.login);
        TextView user = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);

        TextView signup = (TextView) findViewById(R.id.newuser);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginView.this, RegisterView.class);
                startActivity(intent);
            }
        });
    }

    public void loginClick(View view){
        LoginUseCase loginAttempt = new LoginUseCase();
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        if (loginAttempt.checkUser(username.getText().toString(), password.getText().toString()))
            Toast.makeText(this, "Incorrect Login. Please try again.",
                Toast.LENGTH_SHORT).show();
    }
}