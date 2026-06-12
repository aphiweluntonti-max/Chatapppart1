package com.mycompany.chatapppart1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Scanner allows the user to enter information
        Scanner input = new Scanner(System.in);
        
        // Create an object of the login class so we can call its methods
        Login login = new Login();
        
        // ==================================================
        // --- PART 1: REGISTRATION SECTION ---
        // ==================================================
        System.out.println("===USER REGISTRATION===");
        
        System.out.print("Enter a firstname: ");
        String firstname = input.nextLine();
        
        System.out.print("Enter a lastname: ");
        String lastname = input.nextLine();
                
        System.out.print("Enter a username: ");
        String username = input.nextLine();
        
        System.out.print("Enter a password: ");
        String password = input.nextLine();
        
        System.out.print("Enter your South African phone number (+27...): ");
        String phone = input.nextLine();
        
        // Step 1: Run registration (from Part 1)
        boolean response = login.registerUser(username, password, phone);
        System.out.println("Registration Status: " + response);
        
        // ==================================================
        // --- PART 1: LOGIN SECTION ---
        // ==================================================
        System.out.println("\n===USER LOGIN===");
        
        System.out.print("Enter your username: ");
        String loginUsername = input.nextLine();
        
        System.out.print("Enter your password: ");
        String loginPassword = input.nextLine();
        
        // Step 2: Attempt login and check the result
        boolean loggedIn = login.loginUser(loginUsername, loginPassword); 
        
        // Print out the mandatory status message
        String loginMessage = login.returnLoginStatus(loggedIn);
        System.out.println(loginMessage);
        
        // ==================================================
        // --- PART 2 & 3: MAIN APPLICATION LOGIC ---
        // ==================================================
        if (loggedIn) {
            // Show welcome message and launch menu
            System.out.println("Welcome to ChatApp.");
            
            // Part 3: Load previously stored JSON messages right after login
            Message.loadStoredMessages();
            
            boolean running = true; // Controls the main application while loop
            
            while (running) {
                // Main application menu options
                System.out.println("\n1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.println("4) Stored Messages"); 
                System.out.print("Select an option: ");
                
                int choice = input.nextInt();
                input.nextLine(); // Clear the scanner buffer newline character
                
                switch (choice) {
                    case 1:
                        // --- Option 1: Send Messages ---
                        System.out.println("How many messages would you like to send?");
                        int numMessages = input.nextInt();
                        input.nextLine(); // Clear scanner buffer
                        
                        for (int i = 0; i < numMessages; i++) {
                            int messageNumber = i + 1; 
                            System.out.println("\n--- Message " + messageNumber + " ---");
                            
                            System.out.print("Enter recipient cell number (+27...): ");
                            String recipientCell = input.nextLine();
                            
                            System.out.print("Enter your message (Max 250 characters): ");
                            String text = input.nextLine();
                            
                            // A temporary object used strictly for formatting checks
                            Message validator = new Message();
                            System.out.println(validator.checkRecipientCell(recipientCell));
                            System.out.println(validator.checkMessageLength(text));
                            
                            // FIXED: Construct the real Message item passing your parameters to populate array vectors
                            Message message = new Message(messageNumber, recipientCell, text);
                            
                            // sentMessage() processes local array file saving routines
                            String actionFeedback = message.sentMessage();
                            System.out.println(actionFeedback);
                            
                            // Mandated display tracking order sequence layout rules
                            System.out.println("\n--- Captured Details ---");
                            System.out.println("Message ID: " + message.getMessageID());          
                            System.out.println("Message Hash: " + message.createMessageHash()); 
                            System.out.println("Recipient: " + recipientCell);                  
                            System.out.println("Message: " + text);                             
                        }
                        
                        // Print dynamic total session count after message loop exits
                        System.out.println("\nTotal session messages processed: " + Message.returnTotalMessages());
                        break;
                        
                    case 2:
                        // --- Option 2: Mandated placeholder text string ---
                        System.out.println("Coming Soon.");
                        break;
                        
                    case 3:
                        // --- Option 3: Clean application termination ---
                        System.out.println("Exiting ChatApp. Goodbye!");
                        running = false; 
                        break;
                        
                    case 4:
                        // ==========================================================
                        // --- INTEGRATED CASE 4: STORED MESSAGES SUB-MENU ---
                        // ==========================================================
                        boolean inSubMenu = true;
                        
                        while (inSubMenu) {
                            // Displays all required Part 3 choices
                            System.out.println("\n=== STORED MESSAGES SUB-MENU ===");
                            System.out.println("a) Display all stored messages");
                            System.out.println("b) Display longest message");
                            System.out.println("c) Search by message ID");
                            System.out.println("d) Search by recipient");
                            System.out.println("e) Delete by message hash");
                            System.out.println("f) Display full report");
                            System.out.println("g) Return to Main Menu");
                            System.out.print("Select a sub-option (a-g): ");
                            
                            String subChoice = input.nextLine().trim().toLowerCase();
                            
                            if (subChoice.isEmpty()) {
                                System.out.println("Please select an option.");
                                continue;
                            }
                            
                            char selection = subChoice.charAt(0);
                            
                            switch (selection) {
                                case 'a':
                                    System.out.println("\n--- Current Stored Messages ---");
                                    // FIXED: Connected directly to displayAllStoredMessages() array engine
                                    System.out.println(Message.displayAllStoredMessages()); 
                                    break;
                                    
                                case 'b':
                                    System.out.println("\n--- Character Optimization Metrics ---");
                                    String longest = Message.displayLongestMessage(); 
                                    System.out.println("Longest: " + longest);
                                    break;
                                    
                                case 'c':
                                    System.out.print("Enter unique Message ID to retrieve: ");
                                    String targetID = input.nextLine();
                                    String idResult = Message.searchByMessageID(targetID); 
                                    System.out.println(idResult);
                                    break;
                                    
                                case 'd':
                                    System.out.print("Enter Target Recipient cell number: ");
                                    String targetRecipient = input.nextLine();
                                    String recipientResult = Message.searchByRecipient(targetRecipient); 
                                    System.out.println(recipientResult);
                                    break;
                                    
                                case 'e':
                                    System.out.print("Enter Message Hash key to clear: ");
                                    String targetHash = input.nextLine();
                                    String deleteResult = Message.deleteByHash(targetHash); 
                                    System.out.println(deleteResult);
                                    break;
                                    
                                case 'f':
                                    System.out.println("\n--- Formatted Session Report ---");
                                    String reportData = Message.printMessages(); 
                                    System.out.println(reportData);
                                    break;
                                    
                                case 'g':
                                    System.out.println("Returning to the main dashboard menu...");
                                    inSubMenu = false; // Exits sub-menu while loop safely
                                    break;
                                    
                                default:
                                    System.out.println("Invalid sub-selection. Please type an alphabetical character from a to g.");
                                    break;
                            }
                        }
                        break; // End of Case 4
                        
                    default:
                        System.out.println("Invalid selection. Please enter a choice between 1 and 4.");
                        break;
                }
            }
        } else {
            System.out.println("Access Denied. Application closing.");
        }
        
        input.close(); 
    }
} // FIXED: Added missing class closure brace to resolve parsing errors