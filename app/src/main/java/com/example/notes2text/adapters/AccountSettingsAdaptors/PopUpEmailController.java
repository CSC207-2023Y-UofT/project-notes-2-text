package com.example.notes2text.adapters.AccountSettingsAdaptors;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes2text.UserLogin.UserUpdateInfo;

/**
 * Controller class for changing the email.
 */
public class PopUpEmailController extends AppCompatActivity {

    //Stores instance of the UserUpdateInfo class
    UserUpdateInfo user = new UserUpdateInfo();

    //Stores instance of the PopUpEmailPresenter class
    PopUpEmailPresenter presenter = new PopUpEmailPresenter();
    public PopUpEmailController(){}

    /**
     * After the user presses the confirm button, it will try to change to the email the user
     * wants and if it's successful, it will show the new email and show a pop-up message that
     * it changed, and if it wasn't successful, it will just tell that it didn't change.
     *
     * @param context   Context of the current activity.
     * @param input     Input of the email the user wants to change to.
     * @return          If email change was successful it will return, otherwise false.
     */
    public boolean buttonPressed(Context context, EditText input, TextView text){
        if (!(user.getEmail().equals(input.getText().toString()))){
            if(user.changeEmail(context, input.getText().toString())) {
                return presenter.showNewEmail(text, input);
            }
        }
        return presenter.showEmailError(text, input);
    }
}
