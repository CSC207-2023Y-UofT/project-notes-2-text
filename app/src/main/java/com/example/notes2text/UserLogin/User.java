package com.example.notes2text.UserLogin;

//A user object that stores their username, password, email
public class User {

    //Attributes of a User
    protected String username;
    protected String password;
    protected String email;

    //Constructor of a User to initialize attributes of an User object
    public User(String email, String username, String password){
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
