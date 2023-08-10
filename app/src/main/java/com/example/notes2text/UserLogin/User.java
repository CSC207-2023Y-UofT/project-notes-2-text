package com.example.notes2text.UserLogin;

public class User {
    private String username;
    private String password;
    private String email;

    public User(String email, String username, String password){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    protected String getUsername(){
        return this.username;
    }
    protected String getPassword(){
        return this.password;
    }
    protected String getEmail(){
        return this.email;
    }

    protected void setUsername(String newUsername){
        this.username = newUsername;
    }
    protected void setPassword(String newPassword){
        this.password = newPassword;
    }
    protected void setEmail(String newEmail){
        this.email= newEmail;
    }
}
