package com.example.notes2text.usecases.userloginusecases;

import com.example.notes2text.entities.userloginentities.User;

// Creates Users
public class UserFactory {


    /**
     * Create a user
     * @param email email of a user
     * @param username username of a user
     * @param password password of a user
     * @return a new user
     */
    public User createUser(String email, String username, String password){
        return new User(email, username, password);
    }
}
