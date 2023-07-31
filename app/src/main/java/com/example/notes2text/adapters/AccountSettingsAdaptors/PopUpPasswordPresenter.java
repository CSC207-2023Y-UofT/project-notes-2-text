package com.example.notes2text.adapters.AccountSettingsAdaptors;

import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes2text.usecases.UserUpdateInfo;

public class PopUpPasswordPresenter extends AppCompatActivity {


    public PopUpPasswordPresenter(){}

    public boolean showPasswordChange(UserUpdateInfo user, EditText input, EditText input2){
        input.setText("");
        input2.setText("");
        return true;
    }
    public boolean showPasswordFail(UserUpdateInfo user, EditText input, EditText input2){
        input.setText("");
        input2.setText("");
        return false;
    }
}
