package com.example.notes2text.myapplication;

public class LoginUseCase {

    public boolean checkUser(String username, String password){
        UserRep repository = new UserRep();
        User activeUser = repository.getUser(username);
        if (activeUser != null){
            return repository.validUser(activeUser, password);
        }
        return false;
    }
}
