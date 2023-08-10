package com.example.notes2text.adapters.AccountSettingsAdaptors;

import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes2text.UserLogin.UserUpdateInfo;

/**
 * Presenter class for changing the username.
 */
public class PopUpUsernamePresenter extends AppCompatActivity {

    //Stores instance of the UserUpdateInfo class
    UserUpdateInfo user = new UserUpdateInfo();

    public PopUpUsernamePresenter(){}

    /**
     * Sets the TextView to the user's username
     *
     * @param text      The TextView that shows the current username.
     */
    public void showUsername(TextView text){
        text.setText(user.getUsername());
    }

    /**
     * Sets the TextView to the user's new username and tell user that change was successful
     *
     * @param text      The TextView that shows the current username.
     * @param input     The input the user changed the username to.
     * @return          Since username change was successful, it will return true.
     */
    public boolean showNewUsername(TextView text, EditText input){
        text.setText(user.getUsername());
        input.setText("");
        return true;
    }

    /**
     * Tells user that the change was unsuccessful.
     *
     * @param text      The TextView that shows the current username.
     * @param input     The input the user tried to change the username to.
     * @return          Since username change was unsuccessful, it will return false.
     */
    public boolean showUsernameError(TextView text, EditText input){
        input.setText("");
        return false;
    }
}
