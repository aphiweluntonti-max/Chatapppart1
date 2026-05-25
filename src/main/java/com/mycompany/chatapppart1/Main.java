/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1; //Package name may differ depending on your Netbeans  set  up

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class Main {
   public static void main(String[] args) {
       
       //Scanner allows the user to enter information
        Scanner input = new Scanner (System.in);
       
       //Create an object of the login class so we can call its methods 
       Login login = new Login();
       
       //---REGISTRATION SECTION---
       System.out.println("===USER REGISTRATION===");
       
       System.out.print("Enter a firstname:");
       String firstname = input.nextLine();
       
       System.out.print("Enter a lastname:");
       String lastname = input.nextLine();
               
       System.out.print("Enter a username:");
       String username = input.nextLine();
       
       System.out.print("Enter a passsword:");
       String password = input.nextLine();
       
       System.out.print("Enter your South African phone number(+27...):");
       String phone = input.nextLine();
       
       //Call the registerUser and storing the message it returns
       boolean response = login.registerUser(username,password,phone);
       
       //Showing the registration message
       System.out.println(response);
       
       //---LOGIN SECTION---
       System.out.println("/n===USER LOGIN===");
       
       System.out.print("Enter your username:");
       String loginUsername =input.nextLine();
       
       System.out.print("Enter your password:");
       String loginPassword =input.nextLine();
       
       //Check the login details of the user by calling the user
       boolean loggedIn = login.loginUser(loginUsername, loginPassword); 
       
       //The correct login message must be printed out
       String loginMessage = login.returnLoginStatus(loggedIn);
       System.out.println(loginMessage);
       
       //----Messaging(only if logged in)----
       if (loggedIn) {
               //Is when the user logged in successfully
                 System.out.println("Welcome to ChatApp.");
       } else {
                //print failure and exit
                System.out.println("Wrong details,sorry can you please try again!");
       }
                  
                     
               boolean running = true;
               
    while (running) {
    // 1. Display menu options to the user
    System.out.println("\n=== CHATAPP MENU ===");
    System.out.println("1) Send Message");
    System.out.println("2) Show recently sent message");
    System.out.println("3) Quit");
    System.out.print("Please enter your choice (1-3): ");
    
    // 2. FIX: Actually capture what the user types!
    int choice = input.nextInt(); 
    
    // 3. FIX: Clear the invisible "Enter" key press from the scanner buffer
    input.nextLine(); 
    
    // 4. Run the switch evaluation
    switch (choice) {
        case 1 -> {
            System.out.println("\n--- Send Message Process ---");
            System.out.println("How many messages would you like to send?"); 
            int numMessages = input.nextInt();
            input.nextLine(); // Clear buffer again after reading an integer
            
            for (int i = 0; i < numMessages; i++) {
                int messageNumber = i + 1;
                System.out.println("---Message " + messageNumber + "-----");
                
                System.out.print("Enter recipient number (+27...): ");
                String recipient = input.nextLine();
                
                System.out.print("Enter your text: ");
                String userMessage = input.nextLine();
                
                // Construct and process your message object here
                Message msg = new Message(messageNumber, recipient, userMessage);
                msg.printMessages();
                msg.sentMessage();
            }
        }
        case 2 -> System.out.println("Coming soon...");
        case 3 -> {
            System.out.println("Exiting application. Goodbye!");
            running = false; // Gracefully ends the while loop
        }
        default -> System.out.println("Invalid option number, please choose the numbers that are displayed.");
    }
}
                             
                      System.out.println("How many messages would you like to send?"); 
        // i put an input scanner so it could wait for the user's input  
        int numMessages = input.nextInt(); //read from the scanner
        
        input.nextLine();
        
        for (int i = 0; i < numMessages;i++) {
            int messageNumber = i + 1; // Its a message number that humans can read
            System.out.println("---Message" +messageNumber+ "-----");
            System.out.println("Enter your text:");
            //collect all message details inside 
            String userMessage = input.nextLine();
            
            System.out.println("Saved: \"" + userMessage + "\"");
            
        }       
                     }
                   }
                       
                   
         
     
        
     
       
   
   

