package com.example.notes2text.adapters.AccountSettingsAdaptors;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes2text.UserLogin.UserUpdateInfo;

public class PopUpEmailController extends AppCompatActivity {
    UserUpdateInfo user = new UserUpdateInfo();
    PopUpEmailPresenter presenter = new PopUpEmailPresenter();
    public PopUpEmailController(){}

    public boolean buttonPressed(Context context, EditText input, TextView text){
        if (!(user.getEmail().equals(input.getText().toString()))){
            if(user.changeEmail(context, input.getText().toString())) {
                return presenter.showNewEmail(text, input);
            }
        }
        return presenter.showEmailError(text, input);
    }
}
