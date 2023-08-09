package com.example.notes2text.UserLogin;

public class UserFactory {
    public User createUser(String email, String user, String password){
        return new User(email, user, password);
    }
}
