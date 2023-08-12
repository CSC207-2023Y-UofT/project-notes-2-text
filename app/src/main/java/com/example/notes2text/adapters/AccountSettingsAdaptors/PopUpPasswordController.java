package com.example.notes2text.adapters.AccountSettingsAdaptors;

import android.content.Context;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notes2text.ui.userloginframeworks.UserRep;
import com.example.notes2text.usecases.userloginusecases.UserUpdateInfo;

/**
 * Controller class for changing the password.
 */
public class PopUpPasswordController extends AppCompatActivity {

    //Stores instance of the UserUpdateInfo class
    UserUpdateInfo user;

    //Stores instance of the PopUpPasswordPresenter class
    PopUpPasswordPresenter presenter;

    /**
     * Constructor for controller
     *
     * @param context   Context of the current activity
     */
    public PopUpPasswordController(Context context){
        this.user = new UserUpdateInfo(new UserRep(context), context);
        this.presenter = new PopUpPasswordPresenter();
    }

    /**
     * After the user presses the confirm button, it will try to change to the password the user
     * wants and if it's successful, it will show a pop-up message that it changed, and if it
     * wasn't successful, it will just tell that it didn't change.
     *
     * @param input     Input of the current password the user entered.
     * @param input2    Input of the password the user wants to change to.
     * @return          If password change was successful it will return, otherwise false.
     */
    public boolean buttonPressed(EditText input, EditText input2){
        if (this.user.getPassword().equals(input.getText().toString())){
            if(this.user.changePassword(input2.getText().toString())) {
                return this.presenter.showPasswordChange(input, input2);
            }
        }
        return this.presenter.showPasswordFail(input, input2);
    }
}
