/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;

import java.util.Random;

public class Message {
    // Fields
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    
    // Constructor
    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        
        //  These will now work perfectly since the helper method is defined below
        this.messageID = generate10DigitID(); 
        this.messageHash = createMessageHash(); 
    } //  Kept the constructor closing brace clean

    // Helper method to auto-generate a 10-digit numerical ID
    private String generate10DigitID() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        
        // Generate 10 random single digits (0-9) and chain them together
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }
               
    // Checks if the messageID is exactly 10 characters long
    public boolean checkMessageID() {
        if (this.messageID != null && this.messageID.length() == 10) {
            return true;
        }
        return false;
    }

    // Validates the South African cell number format (+27...)
    public boolean checkRecipientCell() {
        if (this.recipient != null && this.recipient.startsWith("+27") && this.recipient.length() == 12) {
            return true;
        }
        return false;
    }

    // Generates the messageHash using the first 2 characters of the ID
    public String createMessageHash() {
        if (this.messageID != null && this.messageID.length() >= 2) {
            // Step 1: get first 2 characters of the message ID
            String idPart = this.messageID.substring(0, 2);
            
            // Example logic: combine the 2 chars with the message length
            return idPart + this.messageText.length();
        }
        return "0000"; 
    }

    // Displays a confirmation that the specific message was sent
    public void sentMessage() {
        System.out.println("Message #" + this.messageNumber + " has been sent to " + this.recipient);
    }

    // Prints out all the details of this specific message neatly
    public void printMessages() {
        System.out.println("\n--- Message Details ---");
        System.out.println("ID: " + this.messageID);
        System.out.println("No: " + this.messageNumber);
        System.out.println("Recipient: " + this.recipient);
        System.out.println("Text: " + this.messageText);
        System.out.println("Hash: " + this.messageHash);
    }
}
          
  

