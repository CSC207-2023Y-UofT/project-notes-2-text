package com.example.notes2text.UserLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes2text.R;


public class RegisterView extends AppCompatActivity {


    /**
     * Displays the UI for Register Page and listens for user input
     * @param savedInstanceState Bundle containing the saved instance State of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView login = findViewById(R.id.loginclick);
        login.setOnClickListener(new View.OnClickListener() {
            //Method that listens to Login button
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterView.this, LoginView.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * Method that listens to Register button click
     * @param view The register button
     */
    public void clickRegister(View view) {

        //Get username, email, password and password reconfirmation from a User
        EditText username = (EditText) findViewById(R.id.username);
        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.password1);
        EditText password2 = (EditText) findViewById(R.id.password2);
        String userid = username.getText().toString();
        String emailid = email.getText().toString();
        String pswrd = password.getText().toString();
        String pswrd2 = password2.getText().toString();

        //Attempts to register a User
        RegisterUseCase userRegisterCase = new RegisterUseCase(this);
        //Stores the registration status
        String outcome = userRegisterCase.registerUser(emailid, userid, pswrd, pswrd2);
        //Displays the registration status
        Toast.makeText(this, outcome,
                Toast.LENGTH_SHORT).show();

    }
}