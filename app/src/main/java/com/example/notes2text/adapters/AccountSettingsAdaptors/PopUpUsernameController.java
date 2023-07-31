package com.example.notes2text.adapters.AccountSettingsAdaptors;

import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes2text.usecases.UserUpdateInfo;

public class PopUpUsernameController extends AppCompatActivity {
    PopUpUsernamePresenter presenter = new PopUpUsernamePresenter();
    public PopUpUsernameController(){}

    public boolean buttonPressed(UserUpdateInfo user, EditText input, TextView text){
        if (!(user.getUsername().equals(input.getText().toString()))){
            user.changeUsername(input.getText().toString());
            return presenter.showNewUsername(user, text, input);
        }
        return presenter.showUsernameError(user, text, input);
    }


}
