package com.example.notes2text.UserLogin;

public class User {
    protected String username;
    protected String password;
    protected String email;

    public User(String email, String username, String password){
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
