package com.example.notes2text.UserLogin;

public interface UserRepository {


    /**
     * Add user info to a User Repository
     * @param username username of a user
     * @param email email of a user
     * @param password password of a user
     * @return true if user was added successfully
     */
    boolean addUser(String username, String email, String password);


    /**
     * Check if username exists in a User Repository
     * @param username username of a user
     * @return true if username exist in the user repository
     */
    boolean uniqueUsername(String username);

    /**
     * Checks if an email exists in a User Repository
     * @param email email of a user
     * @return true if email exist in the user repository
     */
    boolean uniqueEmail(String email);
}
