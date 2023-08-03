package com.example.notes2text.UserLogin;

public class CurrentUser {
    private static User currentUser = null;

    public static void setUser(User user){
        currentUser = user;
    }

    public static User getUser(){
        return currentUser;
    }
}
