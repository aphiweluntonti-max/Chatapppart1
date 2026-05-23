/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;

/**
 *
 * @author Admin
 */
public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    
    
    public Message(int messageNumber, String recipient, String meesageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        
        this.messageID = generateMessageID();
        this.messageHash = createmesseageHash();
        
       public String generatemessageID() {
           if 
       }
    }
}
