package com.example.notes2text.UserLogin;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

public class CurrentUser {

    private static User currentUser = null;

    private static final String StoreUsername = "";

    public static void setUser(User user){
        currentUser = user;
    }

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