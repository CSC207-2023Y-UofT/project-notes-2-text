package com.example.notes2text.adapters.AccountSettingsAdaptors;

import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Presenter class for changing the password.
 */
public class PopUpPasswordPresenter extends AppCompatActivity {

    /**
     * Constructor for presenter
     *
     */
    public PopUpPasswordPresenter(){
    }

    /**
     * Sets the EditText views to the empty string.
     *
     * @param input     Input of the current password the user entered..
     * @param input2    Input of the password the user wants to change to.
     * @return          Since password change was successful, it will return true.
     */
    public boolean showPasswordChange(EditText input, EditText input2){
        input.setText("");
        input2.setText("");
        return true;
    }

    /**
     * Sets the EditText views to the empty string.
     *
     * @param input     Input of the current password the user entered..
     * @param input2    Input of the password the user wants to change to.
     * @return          Since password change was unsuccessful, it will return false.
     */
    public boolean showPasswordFail( EditText input, EditText input2){
        input.setText("");
        input2.setText("");
        return false;
    }
}
