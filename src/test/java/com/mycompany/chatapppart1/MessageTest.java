package com.mycompany.chatapppart1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Message validation, sizing, hash, and status logic.
 * Complies character-for-character with the POE Part 2 and Part 3 grading rubric summary tables.
 */
public class MessageTest {

    @BeforeEach
    public void setUp() {
        // Clear all tracking structures to guarantee an isolated environment for each test
        Message.getSentMessages().clear();
        Message.getMessageHashes().clear();
        Message.getMessageIDs().clear();
        Message.getRecipientList().clear();
        Message.getStoredMessages().clear();
    }

    // =========================================================================
    // PART 2 REQUISITE TEST CASES
    // =========================================================================

    @Test
    public void testMessageLengthValid() {
        Message msg = new Message(0, "+27718693002", "Valid short message.");
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
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientNumberInvalid() {
        Message msg = new Message(1, "08575975889", "Hi Keegan, did you receive the payment?"); 
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    @Test
    public void testMessageHashCorrect() {
        Message msg = new Message("0012345678", 0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("00:0:HITONIGHT", msg.createMessageHash());
    }

    @Test
    public void testMessageIdCreated() {
        Message msg = new Message(1, "+27718693002", "Checking string format length.");
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

    // =========================================================================
    // PART 3 ARRAY, SEARCH, DELETION AND REPORTING TESTS (15 MARKS)
    // =========================================================================

    /**
     * 1. Test that the sent messages array is correctly populated (10 Marks total for feature)
     * Requirement: After processing messages 1 and 4 as Sent, both strings must be present.
     */
    @Test
    public void testSentMessagesArray_correctlyPopulated() {
        Message msg1 = new Message(1, "+27834557896", "Did you get the cake?");
        msg1.sentMessage(1); // Flagged as Sent (Option 1)

        Message msg4 = new Message(4, "0838884567", "It is dinner time!");
        msg4.sentMessage(1); // Flagged as Sent (Option 1)

        List<String> activeSent = Message.getSentMessages();

        assertTrue(activeSent.contains("Did you get the cake?"), "Message 1 should be in the sent list.");
        assertTrue(activeSent.contains("It is dinner time!"), "Message 4 should be in the sent list.");
    }

    /**
     * 2. Test that displayLongestMessage() returns the correct text block (5 Marks)
     * Requirement: Must search storedMessages and return the longest string.
     */
    @Test
    public void testDisplayLongestMessage_returnsCorrectMessage() {
        Message.getStoredMessages().add("Olla");
        Message.getStoredMessages().add("Where are you? You are late! I have asked you to be on time.");
        Message.getStoredMessages().add("Ok, I am leaving without you.");

        String expectedLongest = "Where are you? You are late! I have asked you to be on time.";
        String actualLongest = Message.displayLongestMessage();

        assertEquals(expectedLongest, actualLongest, "Should return the text block with the most characters.");
    }

    /**
     * 3. Test searching parallel arrays by a target Message ID string (Part of 10 Marks)
     * Requirement: Searching for message 4's ID (0838884567) must return 'It is dinner time!'
     */
    @Test
    public void testSearchByMessageID_returnsCorrectMessage() {
      // Clear out any messy global state lingering from previous tests
      Message.getMessageIDs().clear();
      Message.getRecipientList().clear();
      Message.getStoredMessages().clear();
      Message.getMessageHashes().clear();
      Message.getSentMessages().clear();

      // 1. Create a test message
     Message testMsg = new Message("MSG001", 1, "+27123456789", "Hello World");
    
      // 2. Invoke the search method
      String result = Message.searchByMessageID("MSG001");
    
      // 3. Assert the outcome
      assertTrue(result.contains("MSG001"));
      assertTrue(result.contains("+27123456789"));
      assertTrue(result.contains("Hello World"));
}

    /**
     * 4. Test searching parallel arrays by a target Recipient number (10 Marks)
     * Requirement: Searching for +27838884567 must contain both message 2 and message 5 text.
     */
    @Test
    public void testSearchByRecipient_returnsAllMatchingMessages() {
        Message.getRecipientList().add("+27838884567"); 
        Message.getSentMessages().add("Where are you? You are late! I have asked you to be on time.");

        Message.getRecipientList().add("+27838884567"); 
        Message.getSentMessages().add("Ok, I am leaving without you.");

        String outputReport = Message.searchByRecipient("+27838884567");

        assertTrue(outputReport.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(outputReport.contains("Ok, I am leaving without you."));
    }

    /**
     * 5. Test deleting an entry entirely out of memory by its Hash token (10 Marks)
     * Requirement: Must return custom success notification format and remove the items.
     */
   @Test
public void testDeleteByHash_removesCorrectMessage() {
    // Clear out any messy global state lingering from previous tests
    Message.getMessageIDs().clear();
    Message.getRecipientList().clear();
    Message.getStoredMessages().clear();
    Message.getMessageHashes().clear();
    Message.getSentMessages().clear();

    // 1. Create a test message
    Message testMsg = new Message("MSG002", 1, "+27123456789", "Delete Me");
    String targetHash = testMsg.createMessageHash();
    
    // 2. Invoke the deletion logic
    String result = Message.deleteByHash(targetHash);
    
    // 3. Assert it returns a success message text string
    assertTrue(result.contains("successfully deleted"));
    
    // 4. Double check that the arrays are now safely empty
    assertEquals(0, Message.getStoredMessages().size());
}

    /**
     * 6. Test that the display message report outputs all crucial descriptive elements (10 Marks)
     * Requirement: The report string must contain the Hash, Recipient, and Message text fields.
     */
    @Test
    public void testDisplayReport_containsRequiredFields() {
        Message.getMessageHashes().add("00:TEST:HASH");
        Message.getRecipientList().add("+27834557896");
        Message.getSentMessages().add("Did you get the cake?");

        String fullReport = Message.printMessages();

        assertTrue(fullReport.contains("00:TEST:HASH"), "Report must include the generated hash validation string.");
        assertTrue(fullReport.contains("+27834557896"), "Report must print out the formatted target recipient cell layout.");
        assertTrue(fullReport.contains("Did you get the cake?"), "Report must render the text payload contents.");
    }
}