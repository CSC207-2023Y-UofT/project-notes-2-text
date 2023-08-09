package com.example.notes2text.adapters.AccountSettingsAdaptors;

import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes2text.UserLogin.UserUpdateInfo;

public class PopUpEmailPresenter extends AppCompatActivity {
    UserUpdateInfo user = new UserUpdateInfo();

    public PopUpEmailPresenter(){}

    public void showEmail(TextView text){
        text.setText(user.getEmail());
    }
    public boolean showNewEmail(TextView text, EditText input){
        text.setText(user.getEmail());
        input.setText("");
        return true;
    }
    public boolean showEmailError(TextView text, EditText input){
        input.setText("");
        return false;
    }
}
