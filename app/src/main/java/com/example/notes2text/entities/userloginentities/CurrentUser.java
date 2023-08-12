/**
 * Use case for storing the current user logged in.
 */
package com.example.notes2text.entities.userloginentities;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class CurrentUser {

    //User instance of the current user.
    private static User currentUser = null;

    //String variable to store the current user's username.
    private static final String StoreUsername = "";

    /**
     * Set an User instance of the current user.
     *
     * @param user  The User instance that will be set as the current user.
     */
    public static void setUser(User user){
        currentUser = user;
    }

    /**
     * Gets the User instance of the current user.
     *
     * @return  Returns the User instance of the current user.
     */
    public static User getUser(){
        return currentUser;
    }


    /**
     * Keeps the current user logged in
     *
     * @param context   Context of the current activity.
     * @param username  String of the current user's username.
     */
    public static void setCurrent(Context context, String username) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(StoreUsername, username);
        editor.commit();
    }

    /**
     * Gets the current user logged in.
     *
     * @param context   Context of the current activity.
     * @return          Returns a string of the current user's username.
     */
    public static String getCurrent(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(StoreUsername, "");
    }

    /**
     * Removes the current user after logging out.
     *
     * @param context   Context of the current activity.
     */
    public static void clearCurrent(Context context) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.clear();
        editor.commit();
    }

}
