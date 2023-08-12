package com.example.notes2text.UserLogin;

import static org.junit.Assert.*;

import com.example.notes2text.usecases.userloginusecases.UserRequirements;

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
        assertTrue(requirements.validPassword(password));
        password = "bill1$";
        assertFalse(requirements.validPassword(password));
        password = "BILL1$";
        assertFalse(requirements.validPassword(password));
        password = "Bill 1$";
        assertFalse(requirements.validPassword(password));
        password = "Bill1";
        assertFalse(requirements.validPassword(password));
        password = "Bill$";
        assertFalse(requirements.validPassword(password));
    }

    /**
     *
     * Unit test for validUsername
     */
    @Test
    public void validUsername() {
        UserRequirements requirements = new UserRequirements();
        String username = "Bill";
        assertTrue(requirements.validUsername(username));
        username = "Bii";
        assertFalse(requirements.validUsername(username));
        username = "B ill";
        assertFalse(requirements.validUsername(username));
    }
}