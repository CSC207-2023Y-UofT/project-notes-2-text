package com.example.notes2text.UserLogin;

import static org.junit.Assert.*;

import com.example.notes2text.entities.userloginentities.CurrentUser;
import com.example.notes2text.entities.userloginentities.User;
import com.example.notes2text.usecases.userloginusecases.UserFactory;

import org.junit.Test;

/**
 * Unit test class for CurrentUser
 */
public class CurrentUserTest {

    /**
     * Unit test for setUser()
     */
    @Test
    public void setUser() {
        UserFactory userFactory = new UserFactory();
        CurrentUser.setUser(userFactory.createUser("joe20", "Joe", "strong"));
        User user = CurrentUser.getUser();
        assertEquals("joe20", user.getEmail());
        assertEquals("Joe", user.getUsername());
        assertEquals("strong", user.getPassword());
    }

    /**
     * Unit test for getUser()
     */
    @Test
    public void getUser() {
        UserFactory userFactory = new UserFactory();
        User user = userFactory.createUser("joe20", "Joe", "strong");
        CurrentUser.setUser(user);
        assertEquals(user, CurrentUser.getUser());
    }
}