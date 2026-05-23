package com.mycompany.chatapppart1;

public class Login {
    String username;
    String password;
    String phoneNumber;

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (Character.isUpperCase(c)) {
                hasCapital = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }

        return password.length() >= 8 && hasCapital && hasNumber && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String phone) {
        return phone.startsWith("+27") && phone.length() == 12;
    }

    
    public boolean registerUser(String username, String password, String phone) {

        if (!checkUserName(username)) {
            return false;
        }

        if (!checkPasswordComplexity(password)) {
            return false;
        }

        if (!checkCellPhoneNumber(phone)) {
            return false;
        }

        // Store user details
        this.username = username;
        this.password = password;
        this.phoneNumber = phone;

        return true;
    }

    
    public boolean loginUser(String username, String password) {
        if (this.username == null || this.password == null) {
            return false;
        }

        return this.username.equals(username) && this.password.equals(password);
    }

    public String returnLoginStatus(boolean success) {
        if (success) {
            return "Welcome " + username + ", it is great to see you again.";
        } else {
            return "Username or phone number or password incorrect, please try again.";
        }
    }
}
    
