package ussdsim;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Account {

    String username;
    int transactionPin;
    double accountBalance = 0;
    double savings = 0;

    //constructor with some default values
    public Account(String username, int transactionPin) {
        this.username = username;
        this.transactionPin = transactionPin;
    }

    //overloaded constructor with all values specified
    public Account(String username, int transactionPin, double accountBalance, double savings) {
        this.username = username;
        this.transactionPin = transactionPin;
        this.accountBalance = accountBalance;
        this.savings = savings;
    }

    public static void accountCreationMenu() {
        System.out.println("Enter username, and a 4-digit transaction pin in this format(no spaces):- <username>,<pin> e.g. chisom,1234");
    }

    //this method will simulate creating a user in a fake database - I didn't want to dive into sql
    public String createAccount() {
        String filename = this.username + ".txt";
        try {
            File user = new File(filename);
            if (user.createNewFile()) {
                try (FileWriter userWriter = new FileWriter(user)) {
                    userWriter.write("username: " + this.username + ", " + "transactionPin: " + this.transactionPin + ", " + "accountBalance: " + this.accountBalance + ", " + "savings: " + this.savings);
                    return "Account created successfully";
                } catch (IOException e) {
                    return "An error occurred - " + e.getMessage();
                }

            } else {
                return "Account already exists.";
            }
        } catch (IOException e) {
            return "An error occurred - " + e.getMessage();
        }
    }

    //this method will simply print account details to the console
    public void printAccountDetails() {
        System.out.println("Username: " + this.username);
        System.out.println("Transaction pin: " + this.transactionPin);
        System.out.println("Account balance: " + this.accountBalance);
        System.out.println("Savings: " + this.savings);
    }

    //this method will simulate deleting a user
    public String deleteAccount() {
        String filename = this.username + ".txt";
        File user = new File(filename);
        if (user.delete()) {
            return "Account deleted successfully";
        } else {
            return "Failed to delete account";
        }
    }

    //this method will simulate depositing money in the user's account
    public String deposit(double amount) {
        this.accountBalance += amount;
        String user = this.username + ".txt";
        try (FileWriter userWriter = new FileWriter(user)) {
            userWriter.write("username: " + this.username + ", " + "transactionPin: " + this.transactionPin + ", " + "accountBalance: " + this.accountBalance + ", " + "savings: " + this.savings);
            return "Amount deposited successfully";
        } catch (IOException e) {
            return "An error occurred - " + e.getMessage();
        }
    }

    //this method will simulate transferring money from the user's account
    public String transfer(double amount, int pin, String recipient) {
        if (this.transactionPin == pin) {
            if (this.accountBalance < amount) {
                return "Insufficient funds";
            } else {
                this.accountBalance -= amount;
                String user = this.username + ".txt";
                try (FileWriter userWriter = new FileWriter(user)) {
                    userWriter.write("username: " + this.username + ", " + "transactionPin: " + this.transactionPin + ", " + "accountBalance: " + this.accountBalance + ", " + "savings: " + this.savings);
                    return "You've successfully transferred " + amount + " to " + recipient;
                } catch (IOException e) {
                    return "An error occurred - " + e.getMessage();
                }
            }
        } else {
            return "Incorrect transaction pin";
        }

    }

    //this method will simulate setting up fixed savings in a user's account
    public String save(double amount) {
        if (this.accountBalance < amount) {
            return "Insufficient funds";
        } else {
            this.accountBalance -= amount;
            this.savings += amount;
            String user = this.username + ".txt";
            try (FileWriter userWriter = new FileWriter(user)) {
                userWriter.write("username: " + this.username + ", " + "transactionPin: " + this.transactionPin + ", " + "accountBalance: " + this.accountBalance + ", " + "savings: " + this.savings);
                return "You've successfully saved " + amount;
            } catch (IOException e) {
                return "An error occurred - " + e.getMessage();
            }
        }
    }
    
    //this method will simulate withdrawing from fixed savings in a user's account
    public String withdraw(double amount) {
        if (this.savings < amount) {
            return "Insufficient funds in savings box";
        } else {
            this.savings -= amount;
            this.accountBalance += amount;
            String user = this.username + ".txt";
            try (FileWriter userWriter = new FileWriter(user)) {
                userWriter.write("username: " + this.username + ", " + "transactionPin: " + this.transactionPin + ", " + "accountBalance: " + this.accountBalance + ", " + "savings: " + this.savings);
                return "You've successfully withdrawn " + amount + " to your main account";
            } catch (IOException e) {
                return "An error occurred - " + e.getMessage();
            }
        }
    }
}
