package com.example.notes2text.myapplication;

import java.util.HashMap;
import java.util.Map;

public class UserRep {
    private Map<User, String> users = new HashMap<User, String>();

    public void addUser(User newUser) {
        users.put(newUser, newUser.password);
    }

    public User getUser(String username) {
        for (User user : users.keySet()) {
            if (user.username.equals(username)) {
                return user;
            }
        }
        return null;
    }
    public boolean validUser(User user, String password){
        if (users.get(user).equals(password)){
            return true;
        }
        return false;
    }
}
