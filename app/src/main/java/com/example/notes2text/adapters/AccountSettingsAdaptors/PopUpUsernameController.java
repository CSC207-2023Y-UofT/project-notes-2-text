package com.example.notes2text.adapters.AccountSettingsAdaptors;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes2text.UserLogin.UserUpdateInfo;

public class PopUpUsernameController extends AppCompatActivity {
    UserUpdateInfo user = new UserUpdateInfo();
    PopUpUsernamePresenter presenter = new PopUpUsernamePresenter();
    public PopUpUsernameController(){}

    public boolean buttonPressed(Context context, EditText input, TextView text) {
        if (!(user.getUsername().equals(input.getText().toString()))) {
            if (user.changeUsername(context, input.getText().toString())) {
                return presenter.showNewUsername(text, input);
            }
        }
        return presenter.showUsernameError(text, input);
    }


}
