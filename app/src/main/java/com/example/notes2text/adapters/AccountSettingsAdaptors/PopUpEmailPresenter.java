package com.example.notes2text.adapters.AccountSettingsAdaptors;

import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes2text.usecases.UserUpdateInfo;

public class PopUpEmailPresenter extends AppCompatActivity {

    public PopUpEmailPresenter(){}

    public void showEmail(UserUpdateInfo user, TextView text){
        text.setText(user.getEmail());
    }
    public boolean showNewEmail(UserUpdateInfo user, TextView text, EditText input){
        text.setText(user.getEmail());
        input.setText("");
        return true;
    }
    public boolean showEmailError(UserUpdateInfo user, TextView text, EditText input){
        input.setText("");
        return false;
    }
}
