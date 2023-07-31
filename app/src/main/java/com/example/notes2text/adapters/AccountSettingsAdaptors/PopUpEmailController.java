package com.example.notes2text.adapters.AccountSettingsAdaptors;

import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes2text.usecases.UserUpdateInfo;

public class PopUpEmailController extends AppCompatActivity {
    PopUpEmailPresenter presenter = new PopUpEmailPresenter();
    public PopUpEmailController(){}

    public boolean buttonPressed(UserUpdateInfo user, EditText input, TextView text){
        if (!(user.getEmail().equals(input.getText().toString()))){
            user.changeEmail(input.getText().toString());
            return presenter.showNewEmail(user, text, input);
        }
        return presenter.showEmailError(user, text, input);
    }
}
