package ussdsim;

import java.util.Scanner;

public class Savings {

    public static Account savingsMenu() {
        UserDetails.userDetailsMenu();
        String username = (new Scanner(System.in).next()).toLowerCase();
        Account account = UserDetails.getAccount(username);
        System.out.println("Enter amount you'd like to save");
        return account;
    }
}
