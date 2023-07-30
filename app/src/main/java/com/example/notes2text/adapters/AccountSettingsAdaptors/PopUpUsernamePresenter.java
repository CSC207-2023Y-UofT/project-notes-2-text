package com.example.notes2text.adapters.AccountSettingsAdaptors;

import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes2text.usecases.UserUpdateInfo;

public class PopUpUsernamePresenter extends AppCompatActivity {

    public PopUpUsernamePresenter(){}

    public void showUsername(UserUpdateInfo user, TextView text){
        text.setText(user.getUsername());
    }
    public boolean showNewUsername(UserUpdateInfo user, TextView text, EditText input){
        text.setText(user.getUsername());
        input.setText("");
        return true;
    }
    public boolean showUsernameError(UserUpdateInfo user, TextView text, EditText input){
        input.setText("");
        return false;
    }
}
