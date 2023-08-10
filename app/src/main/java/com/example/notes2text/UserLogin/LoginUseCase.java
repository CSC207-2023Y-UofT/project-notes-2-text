package com.example.notes2text.UserLogin;

import android.content.Context;

public class LoginUseCase {


    //UserRepository that keeps tracks of all registered users
    private UserRepImpl userRep;

    //Constructor to initialize the attribute of a LoginUseCase object
    public LoginUseCase(Context context){
        this.userRep = new UserRepImpl(context);
    }

    /*
    Returns true if login was successful, false otherwise
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

    /*Return true if a User with given username and password
    exists in the User Repository, false otherwise
     */
    public boolean checkUser(String username, String password){
        return userRep.checkUserPassword(username, password);
    }
}
