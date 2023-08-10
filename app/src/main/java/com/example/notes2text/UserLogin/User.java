package com.example.notes2text.UserLogin;

public class User {

    //String variables to store user's info
    private String username;
    private String password;
    private String email;

    /**
     * Initializes User instance
     *
     * @param email     String of the user's email
     * @param username  String of the user's username
     * @param password  String of the user's password
     */
    public User(String email, String username, String password){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * Gets user's username
     *
     * @return  String of user's username
     */
    protected String getUsername(){
        return this.username;
    }

    /**
     * Gets user's password
     *
     * @return  String of user's password
     */
    protected String getPassword(){
        return this.password;
    }

    /**
     * Gets user's email
     *
     * @return  String of user's email
     */
    protected String getEmail(){
        return this.email;
    }

    /**
     * Changes user's username
     *
     * @param newUsername   String of the user's new username
     */
    protected void setUsername(String newUsername){
        this.username = newUsername;
    }

    /**
     * Changes user's password
     *
     * @param newPassword   String of the user's new password
     */
    protected void setPassword(String newPassword){
        this.password = newPassword;
    }

    /**
     * Changes user's email
     *
     * @param newEmail   String of the user's new email
     */
    protected void setEmail(String newEmail){
        this.email= newEmail;
    }
}
