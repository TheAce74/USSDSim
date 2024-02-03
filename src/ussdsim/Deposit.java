package ussdsim;

import java.util.Scanner;

public class Deposit {
    
    public static Account depositMenu() {
        UserDetails.userDetailsMenu();
        String username = (new Scanner(System.in).next()).toLowerCase();
        Account account = UserDetails.getAccount(username);
        System.out.println("Enter debit card details in the format:- <amount>,<card number>,<expiration date>,<cvv> e.g. 2000,1234567890098765,11/24,445");
        return account;
    }
}
