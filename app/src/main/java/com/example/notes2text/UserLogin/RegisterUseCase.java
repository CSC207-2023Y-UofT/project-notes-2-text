package com.example.notes2text.UserLogin;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

public class RegisterUseCase {

    //Attribute of a RegisterUseCase
    private UserRep userRep;
    //Messages to different registration statuses
    private  static final List<String> msg = Arrays.asList("User Registered! Please login", "UserID already exists", "UserID does not meet requirements",
            "Email already exists", "Please enter a valid email",  "Password does not meet requirements", "Passwords don't match");

    /**
     * //Constructor to initialize the attribute of a RegisterUseCase object
     * @param context The Android context that needs access to user repository.
     */
    public RegisterUseCase(Context context){
        this.userRep = new UserRep(context);

    }

    /**
     * Check to see if user input is valid to register as a user. If valid, register user and
     * inform the user they have been registered.
     * If user info is invalid, inform the user as to why user input is valid
     * @param email The email from user input
     * @param userid The userid from user input
     * @param password1 The password from user input
     * @param password2 The password confirmation from user input
     * @return a string message that explains the outome of the registration attempt
     */
    public String registerUser(String email, String userid, String password1, String password2) {
        //Checks to see if a users registration info is valid
        int status = checkUser(email, userid, password1, password2);
        //Register a user when info is valid
        if (status < 1) {
            userRep.addUser(userid, email, password1);
        }
        return msg.get(status);
    }

    /**
     * Check if a users registration info is valid
     * @param emailid The emailid from user input
     * @param userid The userid from user input
     * @param password1 The password from user input
     * @param password2 The password confirmation from user input
     * @return an integer representing the registration status
     */

    public int  checkUser(String emailid, String userid, String password1, String password2) {
        //Checks to see if username exists in the User Repository
        if (userRep.uniqueUsername(userid)) {
            return 1;
        }
        //Checks to see if username meets username requirements
        if (!validUsername(userid)){
            return 2;
        }
        //Checks to see if email exists in the User Repository
        if (userRep.uniqueEmail(emailid)){
            return 3;
        }
        //Checks to see if an email was entered
        if (emailid.isEmpty()){
            return 4;
        }
        //Checks to see if password meets username requirements
        if (!validPassword(password1)){
            return 5;
        }
        //Checks to see if passwords match
        if (!password1.equals(password2)) {
            return 6;
        }
        return 0;
    }

    /**
     * Check if password meets password requirements
     * @param password The password from user input
     * @return true if password meets password requiremtns
     */
    public boolean validPassword(String password){
        boolean capitalLetter = false;
        boolean lcLetter = false;
        boolean numeric = false;
        boolean noWhiteSpace = true;
        boolean specialCharacters = false;
        boolean length = false;
        char c;
        int i;
        for (i = 0; i < password.length(); i++){
            c = password.charAt(i);
            if (Character.isUpperCase(c)){
                capitalLetter = true;
            }
            else if(Character.isLowerCase(c)){
                lcLetter = true;
            }
            else if (Character.isDigit(c)){
                numeric = true;
            }
            else if (Character.isWhitespace(c)){
                noWhiteSpace = false;
            }
            else if (!Character.isDigit(c) && !Character.isLetter(c) && !Character.isWhitespace(c)){
                specialCharacters = true;
            }
        }
        if (5 < password.length() && password.length() < 13){
            length = true;
        }

        return capitalLetter && lcLetter && numeric && noWhiteSpace && specialCharacters
                && length;
    }

    /**
     *  Check if a User enters a username that does not contain any
     * empty white space and the password is between 4 and 12 characters long.
     * @param username The username from user input
     * @return true if username meets username requirements
     */
    public boolean validUsername(String username){
        boolean noWhiteSpace = true;
        boolean length = false;
        char c;
        char empty = ' ';
        int i;
        for (i = 0; i < username.length(); i++){
            c = username.charAt(i);
            if (Character.isWhitespace(c)){
                noWhiteSpace = false;
            }
        }
        if (3 < username.length() && username.length() < 13){
            length = true;
        }

        return length && noWhiteSpace;
    }
}
