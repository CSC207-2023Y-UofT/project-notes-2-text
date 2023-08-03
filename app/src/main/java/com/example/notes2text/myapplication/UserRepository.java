package com.example.notes2text.myapplication;

public interface UserRepository {
    void addUser(User user);
    User getUser(String username);
    boolean validUser(User user, String password);
}
