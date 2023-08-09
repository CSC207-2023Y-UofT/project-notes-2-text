package com.example.notes2text.UserLogin;

public class RegisterUseCase {
    UserRep repo = UserRep.getInstance();
    public int registerUser(String emailid, String userid, String password1, String password2) {
        int status = checkUser(emailid, userid, password1, password2);
        if (status < 1) {
            addUser(emailid, userid, password1);
        }
        return status;
    }

    public int  checkUser(String emailid, String userid, String password1, String password2) {
        if (repo.getUser2(emailid) != null) {
            return 1;
        }
        if (! validUserid(userid)) {
            return 2;
        }
        if (!password1.equals(password2)) {
            return 3;
        }
        return 0;
    }

    public boolean addUser(String emailid, String userid, String password1){
        UserFactory userFactory = new UserFactory();
        User newUser = userFactory.createUser(emailid, userid, password1);
        repo.addUser(newUser);
        return true;
    }
    public boolean validUserid(String userid){
        return  2 < userid.length() && userid.length() < 11;
    }

    public boolean validPassword(String password){
        boolean capital = false;
        boolean lc = false;
        boolean number = false;
        boolean specialcharacters = false;
        boolean length = false;
        return true;
    }

}
