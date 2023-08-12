package com.example.notes2text.UserLogin;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * Unit test class for UserRequirement
 */
public class UserRequirementsTest {

    /**
     *
     * Unit test for validPassword
     */
    @Test
    public void validPassword() {
        UserRequirements requirements = new UserRequirements();
        String password = "Bill1$";
        assertEquals(true, requirements.validPassword(password));
        password = "bill1$";
        assertEquals(false, requirements.validPassword(password));
        password = "BILL1$";
        assertEquals(false, requirements.validPassword(password));
        password = "Bill 1$";
        assertEquals(false, requirements.validPassword(password));
        password = "Bill1";
        assertEquals(false, requirements.validPassword(password));
        password = "Bill$";
        assertEquals(false, requirements.validPassword(password));
    }

    /**
     *
     * Unit test for validUsername
     */
    @Test
    public void validUsername() {
        UserRequirements requirements = new UserRequirements();
        String username = "Bill";
        assertEquals(true, requirements.validUsername(username));
        username = "Bii";
        assertEquals(false, requirements.validUsername(username));
        username = "B ill";
        assertEquals(false, requirements.validUsername(username));
    }
}