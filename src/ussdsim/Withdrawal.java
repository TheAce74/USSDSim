package ussdsim;

import java.util.Scanner;

public class Withdrawal {

    public static Account withdrawalMenu() {
        UserDetails.userDetailsMenu();
        String username = (new Scanner(System.in).next()).toLowerCase();
        Account account = UserDetails.getAccount(username);
        System.out.println("Enter amount you'd like to withdraw");
        return account;
    }
}
