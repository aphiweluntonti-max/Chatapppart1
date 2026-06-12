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
   public static boolean main(String[]args) {
       
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
  
           
       
       
    
    


