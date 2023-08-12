package com.example.notes2text.adapters.AccountSettingsAdaptors;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes2text.ui.userloginframeworks.UserRep;
import com.example.notes2text.usecases.userloginusecases.UserUpdateInfo;

/**
 * Controller class for changing the username.
 */

public class PopUpUsernameController extends AppCompatActivity {

    //Stores instance of the UserUpdateInfo class
    UserUpdateInfo user;

    //Stores instance of the PopUpUsernamePresenter class
    PopUpUsernamePresenter presenter;

    /**
     * Constructor for controller
     *
     * @param context   Context of the current activity
     */
    public PopUpUsernameController(Context context){
        this.user = new UserUpdateInfo(new UserRep(context), context);
        this.presenter = new PopUpUsernamePresenter(context);
    }

    /**
     * After the user presses the confirm button, it will try to change to the username the user
     * wants and if it's successful, it will show the new username and show a pop-up message that
     * it changed, and if it wasn't successful, it will just tell that it didn't change.
     *
     * @param input     Input of the username the user wants to change to.
     * @return          If username change was successful it will return, otherwise false.
     */
    public boolean buttonPressed(EditText input, TextView text) {
        if (!(this.user.getUsername().equals(input.getText().toString()))) {
            if (this.user.changeUsername(input.getText().toString())) {
                return this.presenter.showNewUsername(text, input);
            }
        }
        return this.presenter.showUsernameError(text, input);
    }


}
