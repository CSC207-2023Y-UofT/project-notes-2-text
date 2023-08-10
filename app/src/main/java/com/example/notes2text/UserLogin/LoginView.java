package com.example.notes2text.UserLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes2text.MainActivity;
import com.example.notes2text.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class LoginView extends AppCompatActivity {

    DBHelper MyDB1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Checks if a user was still logged in before the app was closed
        String name = CurrentUser.getCurrent(LoginView.this);
        if(!((name).length() == 0)){
            MyDB1 = new DBHelper(this);
            UserFactory userFactory = new UserFactory();
            CurrentUser.setUser(userFactory.createUser(MyDB1.getEmail(name),name,MyDB1.getPassword(name)));
            Intent intent = new Intent(LoginView.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

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
            CurrentUser.setCurrent(getApplicationContext(), user);
            Intent intent = new Intent(LoginView.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "Incorrect Login. Please try again.",
                Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * When back button is pressed on login page, it goes to the android home screen
     * instead of the previous activity.
     */
    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

}