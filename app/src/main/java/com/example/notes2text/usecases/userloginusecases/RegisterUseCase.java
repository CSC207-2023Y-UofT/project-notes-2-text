package com.example.notes2text.usecases.userloginusecases;

import android.content.Context;

import com.example.notes2text.ui.userloginframeworks.UserRep;

import java.util.Arrays;
import java.util.List;

public class RegisterUseCase {

    //Attribute of a RegisterUseCase
    private UserRep userRep;

    //Instance of UserRequirements class
    private UserRequirements requirements = new UserRequirements();
    //Messages to different registration statuses
    private  static final List<String> msg = Arrays.asList("User Registered! Please login", "UserID already exists", "UserID does not meet requirements",
            "Email already exists", "Please enter a valid email",  "Password does not meet requirements", "Passwords don't match");

    /**
     * //Constructor to initialize the attribute of a RegisterUseCase object
     * @param context The Android context that needs access to user repository.
     */
    public RegisterUseCase(Context context){
        this.userRep = new UserRep(context);

    }

    /**
     * Check to see if user input is valid to register as a user. If valid, register user and
     * inform the user they have been registered.
     * If user info is invalid, inform the user as to why user input is valid
     * @param email The email from user input
     * @param userid The userid from user input
     * @param password1 The password from user input
     * @param password2 The password confirmation from user input
     * @return a string message that explains the outome of the registration attempt
     */
    public String registerUser(String email, String userid, String password1, String password2) {
        //Checks to see if a users registration info is valid
        int status = checkUser(email, userid, password1, password2);
        //Register a user when info is valid
        if (status < 1) {
            userRep.addUser(userid, email, password1);
        }
        return msg.get(status);
    }

    /**
     * Check if a users registration info is valid
     * @param emailid The emailid from user input
     * @param userid The userid from user input
     * @param password1 The password from user input
     * @param password2 The password confirmation from user input
     * @return an integer representing the registration status
     */

    public int  checkUser(String emailid, String userid, String password1, String password2) {
        //Checks to see if username exists in the User Repository
        if (userRep.uniqueUsername(userid)) {
            return 1;
        }
        //Checks to see if username meets username requirements
        if (!requirements.validUsername(userid)){
            return 2;
        }
        //Checks to see if email exists in the User Repository
        if (userRep.uniqueEmail(emailid)){
            return 3;
        }
        //Checks to see if an email was entered
        if (emailid.isEmpty()){
            return 4;
        }
        //Checks to see if password meets username requirements
        if (!requirements.validPassword(password1)){
            return 5;
        }
        //Checks to see if passwords match
        if (!password1.equals(password2)) {
            return 6;
        }
        return 0;
    }

}
