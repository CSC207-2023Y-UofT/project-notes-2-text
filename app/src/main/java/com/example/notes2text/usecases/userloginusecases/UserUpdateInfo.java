package com.example.notes2text.usecases.userloginusecases;

import android.content.Context;

import com.example.notes2text.entities.userloginentities.CurrentUser;
import com.example.notes2text.entities.userloginentities.User;

/**
 * Use case for changing account info.
 */
public class UserUpdateInfo {

    //User instance of the current user.
    User user = CurrentUser.getUser();

    //Instance of UserRequirements class
    UserRequirements requirements = new UserRequirements();

    //Instance of the database that stores all the suers
    UserRepository repo;

    //Context of app's current activity
    Context context;

    /**
     * Constructor for UserUpdateInfo
     *
     * @param repo      Database of all the users
     * @param context   Context of the current activity
     */
    public UserUpdateInfo(UserRepository repo, Context context){
        this.repo = repo;
        this.context = context;
    }

    /**
     * Changes to the username the user wants. If the new username is acceptable, user's username
     * will be changed to that, and if it isn't valid then it stays the same.
     *
     * @param newUsername   String of the username the user wants to change to.
     * @return              If username change was successful, it will return true or false
     *                      otherwise.
     */
    public boolean changeUsername(String newUsername){
        if(this.repo.uniqueUsername(newUsername) || !requirements.validUsername(newUsername)){
            return false;
        }
        this.repo.updateUsername(user.getUsername(), newUsername);
        user.setUsername(newUsername);
        CurrentUser.setUser(user);
        CurrentUser.setCurrent(this.context, user.getUsername());
        return true;
    }

    /**
     * Changes to the password the user wants. If the new password is acceptable, user's password
     * will be changed to that, and if it isn't valid then it stays the same.
     *
     * @param newPassword   String of the password the user wants to change to.
     * @return              If password change was successful, it will return true or false
     *                      otherwise.
     */
    public boolean changePassword(String newPassword){
        if(!requirements.validPassword(newPassword)){
            return false;
        }
        this.repo.updatePassword(user.getUsername(), newPassword);
        user.setPassword(newPassword);
        CurrentUser.setUser(user);
        return true;
    }

    /**
     * Changes to the email the user wants. If the new username is acceptable, user's email
     * will be changed to that, and if it isn't valid then it stays the same.
     *
     * @param newEmail      String of the email the user wants to change to.
     * @return              If email change was successful, it will return true or false
     *                      otherwise.
     */
    public boolean changeEmail(String newEmail){
        if(this.repo.uniqueEmail(newEmail) || newEmail.contains(" ")){
            return false;
        }
        this.repo.updateEmail(user.getEmail(), newEmail);
        user.setEmail(newEmail);
        CurrentUser.setUser(user);
        return true;
    }

    /**
     * Gets the user's current username.
     *
     * @return  Returns the user's current username.
     */
    public String getUsername(){
        return user.getUsername();
    }

    /**
     * Gets the user's current password.
     *
     * @return  Returns the user's current password.
     */
    public String getPassword(){
        return user.getPassword();
    }

    /**
     * Gets the user's current email.
     *
     * @return  Returns the user's current email.
     */
    public String getEmail(){
        return user.getEmail();
    }

}
