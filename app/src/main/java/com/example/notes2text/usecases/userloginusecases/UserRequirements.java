package com.example.notes2text.usecases.userloginusecases;

public class UserRequirements {
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
