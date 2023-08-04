package com.example.notes2text.UserLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes2text.MainActivity;
import com.example.notes2text.R;

public class LoginView extends AppCompatActivity {

    DBHelper MyDB1;

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

        String user = username.getText().toString();
        String pswrd = password.getText().toString();


        MyDB1 = new DBHelper(this);
        boolean checkuserpassword = MyDB1.checkUserPassword(user, pswrd);

        if (checkuserpassword){
            String email = MyDB1.getEmail(user);
            Toast.makeText(this, "Login Successful",
                Toast.LENGTH_SHORT).show();
            UserFactory userFactory = new UserFactory();
            CurrentUser.setUser(userFactory.createUser(email,user,pswrd));

            Intent intent = new Intent(LoginView.this, MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Incorrect Login. Please try again.",
                Toast.LENGTH_SHORT).show();
        }

        }
    }