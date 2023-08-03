package com.example.notes2text.adapters.AccountSettingsAdaptors;

import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes2text.usecases.UserUpdateInfo;

public class PopUpPasswordController extends AppCompatActivity {
    PopUpPasswordPresenter presenter = new PopUpPasswordPresenter();
    public PopUpPasswordController(){}

    public boolean buttonPressed(UserUpdateInfo user, EditText input, EditText input2){
        if (user.getPassword().equals(input.getText().toString())){
            user.changePassword(input2.getText().toString());
            return presenter.showPasswordChange(user, input, input2);
        }
        return presenter.showPasswordFail(user, input, input2);
    }
}
