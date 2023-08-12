package com.example.notes2text.adapters.AccountSettingsAdaptors;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes2text.UserLogin.UserRep;
import com.example.notes2text.UserLogin.UserUpdateInfo;

/**
 * Controller class for changing the email.
 */
public class PopUpEmailController extends AppCompatActivity {

    //Stores instance of the UserUpdateInfo class
    UserUpdateInfo user;

    //Stores instance of the PopUpEmailPresenter class
    PopUpEmailPresenter presenter;

    /**
     * Constructor for controller
     *
     * @param context   Context of the current activity
     */
    public PopUpEmailController(Context context){
        this.user = new UserUpdateInfo(new UserRep(context), context);
        this.presenter = new PopUpEmailPresenter(context);
    }

    /**
     * After the user presses the confirm button, it will try to change to the email the user
     * wants and if it's successful, it will show the new email and show a pop-up message that
     * it changed, and if it wasn't successful, it will just tell that it didn't change.
     *
     * @param input     Input of the email the user wants to change to.
     * @return          If email change was successful it will return, otherwise false.
     */
    public boolean buttonPressed(EditText input, TextView text){
        if (!(this.user.getEmail().equals(input.getText().toString()))){
            if(this.user.changeEmail(input.getText().toString())) {
                return this.presenter.showNewEmail(text, input);
            }
        }
        return this.presenter.showEmailError(text, input);
    }
}
