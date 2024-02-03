package ussdsim;

import java.util.Scanner;

public class Merchant {

    public static void merchantMenu() {
        System.out.println("What can of payments would you like to make?");
        System.out.println("1: Electricity");
        System.out.println("2: Cable");
        System.out.println("3: Education");
    }

    public static Account merchantMenu(int choice) {
        switch (choice) {
            case 1 -> {
                return merchantTransfer("electricity");
            }
            case 2 -> {
               return merchantTransfer("cable");
            }
            case 3 -> {
               return merchantTransfer("education");
            }
            default -> {
                System.out.println("Option unavailable");
            }
        }
        return null;
    }

    public static Account merchantTransfer(String message) {
        UserDetails.userDetailsMenu();
        String username = (new Scanner(System.in).next()).toLowerCase();
        Account account = UserDetails.getAccount(username);
        switch (message) {
            case "electricity" -> {
                System.out.println("Enter electricity bill payment details in this format:- <amount>,<pin> e.g. 2000,1234");
            }
            case "cable" -> {
                System.out.println("Enter payment details in the format:- <amount>,<pin>,<provider>,<plan> e.g. 2000,1234,GoTV,Joli");
            }
            case "education" -> {
                System.out.println("Enter payment details in the format:- <amount>,<pin>,<body> e.g. 2000,1234,utme");
            }
            default -> {
                System.out.println("Payment could not be made for some reason");
            }
        }
        return account;
    }
}
