package com.example.notes2text.UserLogin;

public interface UserRepository {

    //Adds user info to a User Repository
    //Returns true if successful, false otherwise
    boolean addUser(String username, String email, String password);

    //Checks if a username exists in a User Repository
    //Returns false if a username exists repository, or true otherwise
    boolean uniqueUsername(String username);

    //Checks if an email exists in a User Repository
    //Returns false if an email exists repository, or true otherwise
    boolean uniqueEmail(String email);
}
