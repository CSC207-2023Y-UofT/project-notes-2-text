package com.example.notes2text.UserLogin;

public class LoginUseCase {

    public boolean checkUser(String username, String password){
        UserRep repository = UserRep.getInstance();
        User activeUser = repository.getUser(username);
        if (activeUser != null){
            if (activeUser.password.equals(password)) {
                return true;
            }
        }

        return false;
    }
}
