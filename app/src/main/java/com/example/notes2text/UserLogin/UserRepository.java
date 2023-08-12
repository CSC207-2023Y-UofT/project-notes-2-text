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

    /**
     *  Check if a username and password combination exists in one of
     *  the rows in DBNAME database, false otherwise
     * @param username username of a possible user
     * @param password password of a possible user
     * @return true if username and password exists
     */
    boolean checkUserPassword(String username, String password);

    /**
     * Get the email corresponding to the username
     * @param username username of a user
     * @return the email corresponding to the username
     */
    String getEmail(String username);

    /**
     * Gets the user's password from the database.
     *
     * @param username String of the user's username
     * @return Returns the user's password.
     */
    String getPassword(String username);

    /**
     * Updates the user's username in the database
     *
     * @param username      String of the user's old username
     * @param newUsername   String of the user's new username
     */
    void updateUsername(String username, String newUsername);

    /**
     * Updates the user's info in the database
     *
     * @param username String of the user's username
     * @param newPassword String of the user's new password
     */
    void updatePassword(String username, String newPassword);

    /**
     * Updates the user's email in the database
     *
     * @param email      String of the user's old email
     * @param newEmail   String of the user's new email
     */
    void updateEmail(String email, String newEmail);

}
