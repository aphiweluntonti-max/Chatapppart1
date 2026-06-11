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
               
       System.out.print("Enter a username(Must contain minimum of 5 characters and a underscore(_)):");  
       String username = input.nextLine();
       
       System.out.print("Enter a passsword(Must contain minimum of 8 characters consisting of 1 uppercase, 1 number, 1 special character):");
       String password = input.nextLine();
       
       System.out.print("Enter your South African phone number(+27...):");
       String phone = input.nextLine();
       
       //Call the registerUser and storing the message it returns
       boolean response = login.registerUser(username,password,phone);
       
       //Showing the registration message
       System.out.println(response);
       
       //---LOGIN SECTION---
       System.out.println("/n===USER LOGIN===");
       
       System.out.print("Enter your username(Must contain minimum of 5 characters and a underscore(_) ):");
       String loginUsername =input.nextLine();
       
       System.out.print("Enter your password(Must contain minimum of 8 characters consisting of 1 uppercase, 1 number, 1 special character):");
       String loginPassword =input.nextLine();
       
       //Check the login details of the user by calling the user
       boolean loggedIn = login.loginUser(loginUsername, loginPassword); 
       
       //The correct login message must be printed out
       String loginMessage = login.returnLoginStatus(loggedIn);
       System.out.println(loginMessage);
       
       //----Messaging(only if logged in)----
     
    
        
        if (loggedIn) {
            System.out.println("Welcome to ChatApp!"); // Exact required string
            Message.loadStoredMessages();
            
            boolean running = true;
            while (running) {
                System.out.println("\n1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.println("4) Stored Messages"); //part 3 button 
                
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
                            
                            System.out.print("Enter recipient number(+27): ");
                            String recipient = input.nextLine();
                            
                            System.out.print("Enter your message text(250 characters max): ");
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
                    case 4 -> {
                        boolean subRunning = true;
                        while (subRunning) {
                        System.out.println("\n--- Stored Messages Management ---");
                        System.out.println("a) Display all stored messages");
                        System.out.println("b) Display longest message");
                        System.out.println("c) Search by message ID");
                        System.out.println("d) Search by recipient");
                        System.out.println("e) Delete by message hash");
                        System.out.println("f) Display full report");
                        System.out.println("g) Return to the main menu");
                        
                        System.out.print("\nSelect an option: ");
                        // FIX 1: Capture what the user actually types in!
                        String subMenuOptions = input.nextLine().trim().toLowerCase();
                            switch (subMenuOptions) {
                            case "a" -> {
                                System.out.println(Message.displayAllStoredMessages());
                            }
                            case "b" -> {
                                System.out.println("\nLongest Stored Message:");
                                System.out.println(Message.displayLongestMessage());
                            }
                            case "c" -> {
                            Scanner searchIdScanner = new Scanner(System.in);
        
                            System.out.print("Enter Message ID to search(10 digit number please) : ");
                            String targetID = searchIdScanner.nextLine(); // Safely reads the full string entry
        
                            // Call your search method and print the result
                            String searchResult = Message.searchByMessageID(targetID);
                            System.out.println(searchResult);
                            }
                            case "d" -> {
                                // FIX 3: Ask the user for the target recipient number
                                System.out.print("Enter the recipient cell number (+27...): ");
                                String searchRecipient = input.nextLine().trim();
                                System.out.println("\nMessages Found:\n" + Message.searchByRecipient(searchRecipient));
                            }
                            case "e" -> {
                                 // If it still gives an error, create a temporary scanner right here:
                               Scanner localScanner = new Scanner(System.in);
        
                               System.out.print("Please enter the Message Hash to delete: ");
                               String inputHash = localScanner.nextLine(); 
        
                               String result = Message.deleteByHash(inputHash);
                               System.out.println(result);
                               
                            }
                            case "f" -> {
                                System.out.println(Message.printMessages());
                            }
                            case "g" -> {
                                System.out.println("Returning to main menu");
                                subRunning = false;
                            }
                            default -> System.out.println("Invalid option. Returning to main menu.");
                        }
                    } 
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
 
                
                       
                   
         
     
        
     
       
   
   

