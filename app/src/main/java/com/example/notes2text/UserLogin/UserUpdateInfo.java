package com.example.notes2text.UserLogin;

import android.content.ContentValues;
import android.content.Context;

import com.example.notes2text.UserLogin.CurrentUser;
import com.example.notes2text.UserLogin.User;

/**
 * Use case for changing account info.
 */
public class UserUpdateInfo {

    //User instance of the current user.
    User user = CurrentUser.getUser();

    //Instance of the database that stores all the suers
    UserRep MyDB1;

    /**
     * Changes to the username the user wants. If the new username is acceptable, user's username
     * will be changed to that, and if it isn't valid then it stays the same.
     *
     * @param context       Context of the current activity.
     * @param newUsername   String of the username the user wants to change to.
     * @return              If username change was successful, it will return true or false
     *                      otherwise.
     */
    public boolean changeUsername(Context context, String newUsername){
        MyDB1 = new UserRep(context);
        if(MyDB1.uniqueUsername(newUsername)){
            return false;
        }
        MyDB1.updateUsername(user.getUsername(), newUsername);
        user.setUsername(newUsername);
        CurrentUser.setUser(user);
        CurrentUser.setCurrent(context, user.getUsername());
        return true;
    }

    /**
     * Changes to the password the user wants. If the new password is acceptable, user's password
     * will be changed to that, and if it isn't valid then it stays the same.
     *
     * @param context       Context of the current activity.
     * @param newPassword   String of the password the user wants to change to.
     * @return              If password change was successful, it will return true or false
     *                      otherwise.
     */
    public boolean changePassword(Context context, String newPassword){
        MyDB1 = new UserRep(context);
        MyDB1.updatePassword(user.getPassword(), newPassword);
        user.setPassword(newPassword);
        CurrentUser.setUser(user);
        return true;
    }

    /**
     * Changes to the email the user wants. If the new username is acceptable, user's email
     * will be changed to that, and if it isn't valid then it stays the same.
     *
     * @param context       Context of the current activity.
     * @param newEmail      String of the email the user wants to change to.
     * @return              If email change was successful, it will return true or false
     *                      otherwise.
     */
    public boolean changeEmail(Context context, String newEmail){
        MyDB1 = new UserRep(context);
        if(MyDB1.uniqueEmail(newEmail)){
            return false;
        }
        MyDB1.updateEmail(user.getEmail(), newEmail);
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
