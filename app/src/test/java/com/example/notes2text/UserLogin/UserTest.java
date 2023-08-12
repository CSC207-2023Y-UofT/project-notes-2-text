package com.example.notes2text.UserLogin;

import static org.junit.Assert.*;

import com.example.notes2text.entities.userloginentities.User;

import org.junit.Test;

/**
 * Unit test class for User
 */
public class UserTest {

    /**
     * Unit test for getUsername
     */
    @Test
    public void getUsername() {
        User user = new User("joe20", "Joe", "strong");
        assertEquals("Joe", user.getUsername());
    }

    /**
     * Unit test for getPassword
     */
    @Test
    public void getPassword() {
        User user = new User("joe20", "Joe", "strong");
        assertEquals("strong", user.getPassword());
    }

    /**
     * Unit test for getEmail
     */
    @Test
    public void getEmail() {
        User user = new User("joe20", "Joe", "strong");
        assertEquals("joe20", user.getEmail());
    }

    /**
     * Unit test for setUsername
     */
    @Test
    public void setUsername() {
        User user = new User("joe20", "Joe", "strong");
        user.setUsername("Bill");
        assertEquals("Bill", user.getUsername());
    }

    /**
     * Unit test for setPassword
     */
    @Test
    public void setPassword() {
        User user = new User("joe20", "Joe", "strong");
        user.setPassword("weak");
        assertEquals("weak", user.getPassword());
    }

    /**
     * Unit test for setEmail
     */
    @Test
    public void setEmail() {
        User user = new User("joe20", "Joe", "strong");
        user.setUsername("chris100");
        assertEquals("chris100", user.getUsername());
    }
}