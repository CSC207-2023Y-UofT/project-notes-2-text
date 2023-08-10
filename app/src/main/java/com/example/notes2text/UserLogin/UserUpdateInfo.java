package com.example.notes2text.UserLogin;

import android.content.ContentValues;
import android.content.Context;

import com.example.notes2text.UserLogin.CurrentUser;
import com.example.notes2text.UserLogin.User;

public class UserUpdateInfo {
    User user = CurrentUser.getUser();
    UserRepImpl MyDB1;

    public boolean changeUsername(Context context, String newUsername){
        MyDB1 = new UserRepImpl(context);
        if(MyDB1.uniqueUsername(newUsername)){
            return false;
        }
        MyDB1.updateUsername(user.getUsername(), newUsername);
        user.setUsername(newUsername);
        CurrentUser.setUser(user);
        CurrentUser.setCurrent(context, user.getUsername());
        return true;
    }
    public boolean changePassword(Context context, String newPassword){
        MyDB1 = new UserRepImpl(context);
        MyDB1.updatePassword(user.getPassword(), newPassword);
        user.setPassword(newPassword);
        CurrentUser.setUser(user);
        return true;
    }
    public boolean changeEmail(Context context, String newEmail){
        MyDB1 = new UserRepImpl(context);
        if(MyDB1.uniqueEmail(newEmail)){
            return false;
        }
        MyDB1.updateEmail(user.getEmail(), newEmail);
        user.setEmail(newEmail);
        CurrentUser.setUser(user);
        return true;
    }

    public String getUsername(){
        return user.getUsername();
    }
    public String getPassword(){
        return user.getPassword();
    }
    public String getEmail(){
        return user.getEmail();
    }

}
