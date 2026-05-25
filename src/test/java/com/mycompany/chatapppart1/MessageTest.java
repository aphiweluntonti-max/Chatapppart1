/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


package com.mycompany.chatapppart1; // <-- CRITICAL: This links the test to your main project code

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testCheckMessageID_ValidID() {
        // Create a standard message. The constructor auto-generates a 10-digit ID.
        Message msg = new Message(1, "+27831234567", "Hello World!");
        
        // Assert that the generated ID is exactly 10 characters long
        assertTrue("The message ID should be exactly 10 characters.", msg.checkMessageID());
    }

    @Test
    public void testCheckRecipientCell_ValidSouthAfricanNumber() {
        // Arrange a message with a correct SA number layout (+27 followed by 9 digits = 12 total)
        Message validMsg = new Message(1, "+27831234567", "Test message");
        
        // Assert that it passes validation
        assertTrue("The cell number format should be valid.", validMsg.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCell_InvalidFormat() {
        // Test 1: Wrong prefix (e.g., local '083' format instead of international '+27')
        Message wrongPrefix = new Message(1, "0831234567", "Test message");
        assertFalse("Should fail because it doesn't start with +27", wrongPrefix.checkRecipientCell());
        
        // Test 2: Too short
        Message tooShort = new Message(2, "+2783123", "Test message");
        assertFalse("Should fail because it is too short", tooShort.checkRecipientCell());
        
        // Test 3: Too long
        Message tooLong = new Message(3, "+2783123456789", "Test message");
        assertFalse("Should fail because it is too long", tooLong.checkRecipientCell());
    }

    @Test
    public void testCreateMessageHash() {
        String sampleText = "Hello!"; // Length is 6
        Message msg = new Message(1, "+27831234567", sampleText);
        
        // Capturing the string output 
        String generatedHash = msg.createMessageHash();
        
        // Verify the length of the expected ending
        // e.g., If text length is 6, hash should end with "6"
        String expectedLengthSuffix = String.valueOf(sampleText.length());
        
        assertTrue("The hash should end with the length of the message text.", 
                   generatedHash.endsWith(expectedLengthSuffix));
                   
        // Hash length should be exactly 3 (2 letters from ID + 1 digit for length '6')
        int expectedHashLength = 2 + expectedLengthSuffix.length(); 
        
        assertEquals("The hash string length mismatch.", expectedHashLength, generatedHash.length());
    }
}