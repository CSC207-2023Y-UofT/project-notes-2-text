package com.example.notes2text.adapters.AccountSettingsAdaptors;

import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes2text.UserLogin.UserUpdateInfo;

public class PopUpPasswordPresenter extends AppCompatActivity {
    UserUpdateInfo user = new UserUpdateInfo();


    public PopUpPasswordPresenter(){}

    public boolean showPasswordChange(EditText input, EditText input2){
        input.setText("");
        input2.setText("");
        return true;
    }
    public boolean showPasswordFail( EditText input, EditText input2){
        input.setText("");
        input2.setText("");
        return false;
    }
}
