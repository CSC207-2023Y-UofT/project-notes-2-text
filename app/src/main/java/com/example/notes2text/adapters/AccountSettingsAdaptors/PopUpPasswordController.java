package com.example.notes2text.adapters.AccountSettingsAdaptors;

import android.content.Context;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes2text.UserLogin.UserUpdateInfo;

public class PopUpPasswordController extends AppCompatActivity {
    UserUpdateInfo user = new UserUpdateInfo();
    PopUpPasswordPresenter presenter = new PopUpPasswordPresenter();
    public PopUpPasswordController(){}

    public boolean buttonPressed(Context context, EditText input, EditText input2){
        if (user.getPassword().equals(input.getText().toString())){
            if(user.changePassword(context, input2.getText().toString())) {
                return presenter.showPasswordChange(input, input2);
            }
        }
        return presenter.showPasswordFail(input, input2);
    }
}
