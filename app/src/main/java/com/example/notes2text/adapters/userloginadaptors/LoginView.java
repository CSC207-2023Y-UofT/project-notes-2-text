package com.example.notes2text.adapters.userloginadaptors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes2text.MainActivity;
import com.example.notes2text.R;
import com.example.notes2text.entities.userloginentities.CurrentUser;
import com.example.notes2text.usecases.userloginusecases.LoginUseCase;
import com.example.notes2text.usecases.userloginusecases.UserFactory;
import com.example.notes2text.ui.userloginframeworks.UserRep;

public class LoginView extends AppCompatActivity {


    UserRep userRep;

    /**
     * Displays the UI for Login Page and listens for user input
     * @param savedInstanceState Bundle containing the saved instance State of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String name = CurrentUser.getCurrent(LoginView.this);
        if(!((name).length() == 0)){
            userRep = new UserRep(this);
            UserFactory userFactory = new UserFactory();
            CurrentUser.setUser(userFactory.createUser(userRep.getEmail(name), name, userRep.getPassword(name)));
            Intent intent = new Intent(LoginView.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_main3);

        TextView signup = (TextView) findViewById(R.id.newuser);
        signup.setOnClickListener(new View.OnClickListener() {
            /**
             * Method that listens to Register button click
             * @param view The Sign Up button
             */
            @Override
            public void onClick(View view) {
                //Navigates to RegisterView page
                Intent intent = new Intent(LoginView.this, RegisterView.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Method that listens to Login button click
     * @param view The login button
     */
    public void loginClick(View view){

        //Get username and password input from user
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        String user = username.getText().toString();
        String pswrd = password.getText().toString();


        LoginUseCase userLogin = new LoginUseCase(this);
        //Result of log in attempt
        boolean loggedIn = userLogin.loginUser(user, pswrd);
        //Log in was successful
        if (loggedIn){
            //Show success message
            Toast.makeText(this, "Login Successful",
                    Toast.LENGTH_SHORT).show();

            //Navigates to home page of the app
            CurrentUser.setCurrent(getApplicationContext(), user);
            Intent intent = new Intent(LoginView.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        //Invalid username and password combination
        else{
            //Show error message
            Toast.makeText(this, "Incorrect Login. Please try again.",
                    Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
        finish();
    }

}