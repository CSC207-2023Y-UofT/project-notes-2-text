package com.example.notes2text.UserLogin;
//Class that keeps track of the User that's logged in
public class CurrentUser {
    private static User currentUser = null;

    //Sets the currentUser to user
    public static void setUser(User user){
        currentUser = user;
    }

    //Returns the current user that is logged on
    public static User getUser(){
        return currentUser;
    }
}
