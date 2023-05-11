package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import auth.Registration;

public class ValidationTests {
    @Test
    public void testUsingSimpleRegex() {
        var emailAddress = "username@domain.com";
        var regexPattern = "^(.+)@(\\S+)$";
        assertTrue(Registration.patternMatches(emailAddress, regexPattern));
    }
}
