package com.example.notes2text.adapters.AccountSettingsAdaptors;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes2text.ui.userloginframeworks.UserRep;
import com.example.notes2text.usecases.userloginusecases.UserUpdateInfo;

/**
 * Presenter class for changing the email.
 */
public class PopUpEmailPresenter extends AppCompatActivity {

    //Stores instance of the UserUpdateInfo class
    UserUpdateInfo user;

    /**
     * Constructor for presenter
     *
     * @param context   Context of the current activity
     */
    public PopUpEmailPresenter(Context context){
        this.user = new UserUpdateInfo(new UserRep(context), context);
    }

    /**
     * Sets the TextView to the user's email
     *
     * @param text      The TextView that shows the current email.
     */
    public void showEmail(TextView text){
        text.setText(this.user.getEmail());
    }

    /**
     * Sets the TextView to the user's new email and tell user that change was successful
     *
     * @param text      The TextView that shows the current email.
     * @param input     The input the user changed the email to.
     * @return          Since email change was successful, it will return true.
     */
    public boolean showNewEmail(TextView text, EditText input){
        text.setText(this.user.getEmail());
        input.setText("");
        return true;
    }

    /**
     * Tells user that the change was unsuccessful.
     *
     * @param text  Passes the TextView that shows the current email.
     * @param input Passes the input the user tried to change the email to.
     * @return      Since email change was unsuccessful, it will return false.
     */
    public boolean showEmailError(TextView text, EditText input){
        input.setText("");
        return false;
    }
}
