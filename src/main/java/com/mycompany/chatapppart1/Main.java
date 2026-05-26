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
            System.out.println("Welcome to ChatApp."); // Exact required string
            
            boolean running = true;
            while (running) {
                System.out.println("\n1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Select an option: ");
                
                int choice = input.nextInt();
                input.nextLine(); // Clear scanner buffer
                
                switch (choice) {
                    case 1 -> {
                        System.out.println("How many messages would you like to send?");
                        int numMessages = input.nextInt();
                        input.nextLine(); // Clear buffer
                        
                        for (int i = 0; i < numMessages; i++) {
                            int messageNumber = i + 1;
                            System.out.println("--- Message " + messageNumber + " ---");
                            
                            System.out.print("Enter recipient number: ");
                            String recipient = input.nextLine();
                            
                            System.out.print("Enter your message text: ");
                            String text = input.nextLine();
                            
                            // Build tracking instance
                            Message msg = new Message(messageNumber, recipient, text);
                            
                            // Check validations first
                            System.out.println(msg.checkRecipientCell());
                            System.out.println(msg.checkMessageLength());
                            
                            // Submenu for delivery choices
                            System.out.println("\nWhat would you like to do with this message?");
                            System.out.println("1) Send Message");
                            System.out.println("2) Disregard Message");
                            System.out.println("3) Store Message to send later");
                            
                            int subOption = input.nextInt();
                            input.nextLine(); // Clear buffer
                            
                            String resultText = msg.sentMessage(subOption);
                            System.out.println(resultText);
                            
                            // Print details in specified order
                            System.out.println("\n--- Final Summary ---");
                            msg.printMessages();
                        }
                    }
                    case 2 -> System.out.println("Coming Soon.");
                    case 3 -> {
                        System.out.println("Exiting application. Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            }
        } else {
            System.out.println("Login Failure. Exiting.");
        }
        input.close();
    }
}
                   
                       
                   
         
     
        
     
       
   
   

