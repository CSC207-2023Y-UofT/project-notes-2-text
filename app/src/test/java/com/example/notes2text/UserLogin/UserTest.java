package com.example.notes2text.UserLogin;

import junit.framework.TestCase;

public class UserTest extends TestCase {

    User testUser;

    public void setUp() throws Exception {
        testUser = new User("test@gmail.com", "TestUser", "testpassword");
    }

    public void testGetUsername() {
        String username = testUser.getUsername();
        assertEquals(username, "TestUser");
    }

    public void testGetPassword() {
        String username = testUser.getPassword();
        assertEquals(username, "testpassword");

    }

    public void testGetEmail() {
        String username = testUser.getEmail();
        assertEquals(username, "test@gmail.com");
    }

    public void testSetUsername() {
        testUser.setUsername("newusername");
        assertEquals("newusername", testUser.getUsername());
    }

    public void testSetPassword() {
        testUser.setPassword("newpassword");
        assertEquals("newpassword", testUser.getPassword());
    }

    public void testSetEmail() {
        testUser.setEmail("newemail@gmail.com");
        assertEquals("newemail@gmail.com", testUser.getEmail());
    }
}