package com.example.notes2text.UserLogin;

// Creates Users
public class UserFactory {

    //Returns a User object given an email, username and password
    public User createUser(String email, String username, String password){
        return new User(email, username, password);
    }
}
