package com.mycompany.chatapppart1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Message validation, sizing, hash, and status logic.
 * Complies character-for-character with the POE Part 2 grading rubric summary table.
 */
public class MessageTest {

    @Test
    public void testMessageLengthValid() {
        Message msg = new Message(0, "+27718693002", "Valid short message.");
        // Tests the standard class method
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageLengthInvalid() {
        // Construct a string explicitly 260 characters long (exceeds the limit by 10)
        StringBuilder longText = new StringBuilder();
        for (int i = 0; i < 260; i++) {
            longText.append("a");
        }
        Message msg = new Message(0, "+27718693002", longText.toString());
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", msg.checkMessageLength());
    }

    @Test
    public void testRecipientNumberValid() {
        // Test message 1 field data values from assignment guide
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientNumberInvalid() {
        // Test message 2 field data values from assignment guide (invalid prefix)
        Message msg = new Message(1, "08575975889", "Hi Keegan, did you receive the payment?"); 
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    @Test
    public void testMessageHashCorrect() {
        // To force the exact hash "00:0:HITONIGHT", we pass an explicit "00" ID to our test constructor
        Message msg = new Message("0012345678", 0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("00:0:HITONIGHT", msg.createMessageHash());
    }

    @Test
    public void testMessageIdCreated() {
        Message msg = new Message(1, "+27718693002", "Checking string format length.");
        // Verifies that the ID exists and is of correct length bounds
        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testSentMessageSendChosen() {
        Message msg = new Message(1, "+27718693002", "Testing suboption 1.");
        assertEquals("Message successfully sent.", msg.sentMessage(1));
    }

    @Test
    public void testSentMessageDisregardChosen() {
        Message msg = new Message(1, "+27718693002", "Testing suboption 2.");
        assertEquals("Press 0 to delete the message.", msg.sentMessage(2));
    }

    @Test
    public void testSentMessageStoreChosen() {
        Message msg = new Message(1, "+27718693002", "Testing suboption 3.");
        assertEquals("Message successfully stored.", msg.sentMessage(3));
    }
}
    
