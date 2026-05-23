package com.mycompany.chatapppart1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    
    Login login = new Login();
    
    @Test
    public void testValidUsername(){
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testInvalidUsername_NoUnderscore(){
        assertFalse(login.checkUserName("kyle!!!!"));
    }

    @Test
    public void testInvalidUsername_TooLong() {
        assertFalse(login.checkUserName("kyleonTop_"));
    }

    @Test
    public void testValidPassword(){
        assertTrue(login.checkPasswordComplexity("Ch&&sec@Ke99!"));
    }

    @Test
    public void testInvalidPassword_NoCapital(){
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testInvalidPassword_NoNumbers(){
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testValidPhoneNumber(){
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testInvalidPhoneNumber(){
        assertFalse(login.checkCellPhoneNumber("0838968976"));
    }

    @Test
    public void testLoginSuccess(){
        // Register with VALID details
        login.registerUser("ky_1","Ch&&sec@Ke99!", "+27838968976");

        // Attempt login with same correct details
        assertTrue(login.loginUser("ky_1", "Ch&&sec@Ke99!"));
    }

    @Test
    public void testLoginFailed(){
        // Register with VALID details first
        login.registerUser("ky_1","Ch&&sec@Ke99!", "+27838968976");

        // Attempt login with WRONG password
        assertFalse(login.loginUser("ky_1", "password"));
    }
}
