package com.example.notes2text.UserLogin;

//Class that keeps track of the User that's logged in

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

public class CurrentUser {

    private static User currentUser = null;

    //Sets the currentUser to user

    private static final String StoreUsername = "";

    public static void setUser(User user){
        currentUser = user;
    }

    //Returns the current user that is logged on
    public static User getUser(){
        return currentUser;
    }

    public static void setCurrent(Context context, String username) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(StoreUsername, username);
        editor.commit();
    }

    public static String getCurrent(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(StoreUsername, "");
    }

    public static void clearCurrent(Context context) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.clear();
        editor.commit();
    }

}
