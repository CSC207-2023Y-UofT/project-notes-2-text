package com.example.notes2text.UserLogin;

import java.util.HashMap;
import java.util.Map;

public class UserRep {
    private static UserRep instance;
    private Map<User, String> users = new HashMap<>();

    public static UserRep getInstance() {
        if (instance == null){
            instance = new UserRep();
        }
        return instance;
    }

    public void addUser(User newUser) {
        users.put(newUser, newUser.getPassword());
    }

    public User getUser(String username) {
        for (Map.Entry<User, String> userEntry: users.entrySet()) {
            if (username.equals(userEntry.getValue())){
                return userEntry.getKey();
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

    public User getUser2(String email){
        for (User user: users.keySet()) {
            if (user.equals(user.getEmail())){
                return user;
            }
        }
        return null;
    }
}
