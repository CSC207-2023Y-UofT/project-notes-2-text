package com.example.notes2text.usecases.userloginusecases;

import android.content.Context;

import com.example.notes2text.entities.userloginentities.CurrentUser;
import com.example.notes2text.entities.userloginentities.User;
import com.example.notes2text.ui.userloginframeworks.UserRep;

public class LoginUseCase {


    //UserRepository that keeps tracks of all registered users
    private UserRep userRep;

    /**
     *  Constructor to initialize the attribute of a LoginUseCase object
     * @param context The Android context that needs to use the user repository
     */
    public LoginUseCase(Context context){
        this.userRep = new UserRep(context);
    }

    /**
     * Attempt to login a user, with username and password. If user information
     * exists in the repository, log them on.
     * @param username username from user input
     * @param password password from user input
     * @return true if user is user successfully logged on
     */
    public boolean loginUser(String username, String password){
        if (checkUser(username, password)){
            UserFactory userFactory = new UserFactory();
            String email = userRep.getEmail(username);
            User currUser = userFactory.createUser(email, username, password);
            CurrentUser.setUser(currUser);
            return true;
        }
        return false;
    }

    /**
     * Check if a User with given username and password
     *     exists in the User Repository, false otherwise
     * @param username username from user input
     * @param password password from user input
     * @return true if username and password is a valid combination in the user repository
     */
    public boolean checkUser(String username, String password){
        return userRep.checkUserPassword(username, password);
    }
}
