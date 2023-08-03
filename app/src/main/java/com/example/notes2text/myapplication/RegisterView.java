package com.example.notes2text.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes2text.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterView extends AppCompatActivity {

    DBHelper MyDB1;
    private List<String> errors = Arrays.asList("User Registered! Please login", "Email alrerady exists", "UserID already exists",
            "UserID does not meet requirements", "Password does not meet requirements", "Passwords don't match");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView login = findViewById(R.id.loginclick);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterView.this, LoginView.class);
                startActivity(intent);
            }
        });
    }


    public void clickRegister(View view) {
        EditText username = (EditText) findViewById(R.id.username);
        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.password1);
        EditText password2 = (EditText) findViewById(R.id.password2);

        String userid = username.getText().toString();
        String emailid = email.getText().toString();
        String pswrd = password.getText().toString();
        String pswrd2 = password2.getText().toString();

        MyDB1 = new DBHelper(this);

        Boolean checkuser = MyDB1.uniqueUsername(userid);
        if (!checkuser) {
            Boolean checkemail = MyDB1.uniqueEmail(emailid);
            if (checkemail) {
                Toast.makeText(this, "Emailid already exists.",
                        Toast.LENGTH_SHORT).show();
            }
            else {
                Boolean insert = MyDB1.insertData(userid, pswrd, emailid);
                if (insert) {
                    Toast.makeText(this, "Registration Successful",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Registration failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
        else{
        Toast.makeText(this, "UserId exists. Please try again.",
                Toast.LENGTH_SHORT).show();
        }

    }
}