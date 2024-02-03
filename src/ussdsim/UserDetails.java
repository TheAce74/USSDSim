package ussdsim;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserDetails {

    public static void userDetailsMenu() {
        System.out.println("Enter username (no spaces)");
    }

    //get existing user details and return user account
    public static Account getAccount(String username) {
        String filename = username + ".txt";
        try {
            File user = new File(filename);
            try (Scanner myReader = new Scanner(user)) {
                String data = null;
                while (myReader.hasNextLine()) {
                    data = myReader.nextLine();
                }
                if (data != null) {
                    String[] props = data.split(", ");
                    String name = props[0].split(": ")[1];
                    String pin = props[1].split(": ")[1];
                    String balance = props[2].split(": ")[1];
                    String savings = props[3].split(": ")[1];

                    return new Account(name, Integer.parseInt(pin), Double.parseDouble(balance), Double.parseDouble(savings));
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("An error occurred - " + ex.getMessage());
        }
        return null;
    }
}
