package ussdsim;

import java.util.Scanner;

public class Transfer {

    public static Account transferMenu() {
        UserDetails.userDetailsMenu();
        String username = (new Scanner(System.in).next()).toLowerCase();
        Account account = UserDetails.getAccount(username);
        System.out.println("Enter transfer details in the format:- <amount>,<transaction pin>,<recipient's account name> e.g. 2000,1234,vicky");
        return account;
    }
}
