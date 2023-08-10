package com.example.notes2text.adapters.AccountSettingsAdaptors;

import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes2text.UserLogin.UserUpdateInfo;

public class PopUpUsernamePresenter extends AppCompatActivity {

    UserUpdateInfo user = new UserUpdateInfo();

    public PopUpUsernamePresenter(){}

    public void showUsername(TextView text){
        text.setText(user.getUsername());
    }
    public boolean showNewUsername(TextView text, EditText input){
        text.setText(user.getUsername());
        input.setText("");
        return true;
    }
    public boolean showUsernameError(TextView text, EditText input){
        input.setText("");
        return false;
    }
}
