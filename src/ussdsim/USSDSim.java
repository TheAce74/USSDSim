package ussdsim;

import java.util.Scanner;

public class USSDSim {

    static Account account;

    public static int AVERAGE_REQUEST_DELAY = 3000;

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        //welcome the user and display available options
        System.out.println("\nWelcome to EasyBanking USSD Suite, what action would you like to perform today?");
        System.out.println("1. Account creation");
        System.out.println("2. Fund deposits via debit cards");
        System.out.println("3. Make internal transfers");
        System.out.println("4. Merchant payments");
        System.out.println("5. Fixed savings");
        System.out.println("6. Withdraw savings");
        System.out.println("7. Check account details (for already registered users)");
        System.out.println("8. Delete account");

        //take in user input
        Scanner scanner = new Scanner(System.in);
        int service = scanner.nextInt();

        //handle service selected
        switch (service) {
            case 1 -> {
                Account.accountCreationMenu();
                String[] details = (scanner.next()).split(",");
                account = new Account(details[0].toLowerCase(), Integer.parseInt(details[1]));

                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(AVERAGE_REQUEST_DELAY);
                        String message = account.createAccount();
                        System.out.println(message);

                        System.out.println("Would you like to perform another action?");
                        System.out.println("1: Yes \n2: No");
                        int response = scanner.nextInt();
                        if (response == 1) {
                            mainMenu();
                        } else {
                            endMessage();
                        }
                    } catch (InterruptedException | NullPointerException | ArrayIndexOutOfBoundsException ex) {
                        System.out.println("An error occurred - " + ex.getMessage());
                    }
                });
                thread.start();

                System.out.println("Account creation request processing...");
            }
            case 2 -> {
                account = Deposit.depositMenu();
                double amount = Double.parseDouble((scanner.next()).split(",")[0]);

                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(AVERAGE_REQUEST_DELAY);
                        String message = account.deposit(amount);
                        System.out.println(message);

                        System.out.println("Would you like to perform another action?");
                        System.out.println("1: Yes \n2: No");
                        int response = scanner.nextInt();
                        if (response == 1) {
                            mainMenu();
                        } else {
                            endMessage();
                        }
                    } catch (InterruptedException | NullPointerException | ArrayIndexOutOfBoundsException ex) {
                        System.out.println("An error occurred - " + ex.getMessage());
                    }
                });
                thread.start();

                System.out.println("Account funding underway...");
            }
            case 3 -> {
                account = Transfer.transferMenu();
                String[] details = (scanner.next()).split(",");
                double amount = Double.parseDouble(details[0]);
                int pin = Integer.parseInt(details[1]);
                String recipient = details[2];

                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(AVERAGE_REQUEST_DELAY);
                        String message = account.transfer(amount, pin, recipient);
                        System.out.println(message);

                        System.out.println("Would you like to perform another action?");
                        System.out.println("1: Yes \n2: No");
                        int response = scanner.nextInt();
                        if (response == 1) {
                            mainMenu();
                        } else {
                            endMessage();
                        }
                    } catch (InterruptedException | NullPointerException | ArrayIndexOutOfBoundsException ex) {
                        System.out.println("An error occurred - " + ex.getMessage());
                    }
                });
                thread.start();

                System.out.println("Processing funds transfer...");
            }
            case 4 -> {
                Merchant.merchantMenu();
                int choice = scanner.nextInt();
                account = Merchant.merchantMenu(choice);
                String[] details = (scanner.next()).split(",");

                double amount;
                int pin;
                String recipient;

                if (details.length == 2) {
                    amount = Double.parseDouble(details[0]);
                    pin = Integer.parseInt(details[1]);
                    recipient = "nepa";
                } else {
                    amount = Double.parseDouble(details[0]);
                    pin = Integer.parseInt(details[1]);
                    recipient = details[2];
                }

                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(AVERAGE_REQUEST_DELAY);
                        String message = account.transfer(amount, pin, recipient);
                        System.out.println(message);

                        System.out.println("Would you like to perform another action?");
                        System.out.println("1: Yes \n2: No");
                        int response = scanner.nextInt();
                        if (response == 1) {
                            mainMenu();
                        } else {
                            endMessage();
                        }
                    } catch (InterruptedException | NullPointerException | ArrayIndexOutOfBoundsException ex) {
                        System.out.println("An error occurred - " + ex.getMessage());
                    }
                });
                thread.start();

                System.out.println("Processing merchant payment...");
            }
            case 5 -> {
                account = Savings.savingsMenu();
                double amount = scanner.nextDouble();

                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(AVERAGE_REQUEST_DELAY);
                        String message = account.save(amount);
                        System.out.println(message);

                        System.out.println("Would you like to perform another action?");
                        System.out.println("1: Yes \n2: No");
                        int response = scanner.nextInt();
                        if (response == 1) {
                            mainMenu();
                        } else {
                            endMessage();
                        }
                    } catch (InterruptedException | NullPointerException | ArrayIndexOutOfBoundsException ex) {
                        System.out.println("An error occurred - " + ex.getMessage());
                    }
                });
                thread.start();

                System.out.println("Processing savings...");
            }
            case 6 -> {
                account = Withdrawal.withdrawalMenu();
                double amount = scanner.nextDouble();

                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(AVERAGE_REQUEST_DELAY);
                        String message = account.withdraw(amount);
                        System.out.println(message);

                        System.out.println("Would you like to perform another action?");
                        System.out.println("1: Yes \n2: No");
                        int response = scanner.nextInt();
                        if (response == 1) {
                            mainMenu();
                        } else {
                            endMessage();
                        }
                    } catch (InterruptedException | NullPointerException | ArrayIndexOutOfBoundsException ex) {
                        System.out.println("An error occurred - " + ex.getMessage());
                    }
                });
                thread.start();

                System.out.println("Processing withdrawal...");
            }
            case 7 -> {
                UserDetails.userDetailsMenu();
                String username = (scanner.next()).toLowerCase();

                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(AVERAGE_REQUEST_DELAY);
                        account = UserDetails.getAccount(username);
                        account.printAccountDetails();

                        System.out.println("Would you like to perform another action?");
                        System.out.println("1: Yes \n2: No");
                        int response = scanner.nextInt();
                        if (response == 1) {
                            mainMenu();
                        } else {
                            endMessage();
                        }
                    } catch (InterruptedException | NullPointerException | ArrayIndexOutOfBoundsException ex) {
                        System.out.println("An error occurred - " + ex.getMessage());
                    }
                });
                thread.start();

                System.out.println("Fetching account details...");
            }
            case 8 -> {
                UserDetails.userDetailsMenu();
                String username = (scanner.next()).toLowerCase();

                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(AVERAGE_REQUEST_DELAY);
                        account = UserDetails.getAccount(username);
                        String message = account.deleteAccount();
                        System.out.println(message);

                        System.out.println("Would you like to perform another action?");
                        System.out.println("1: Yes \n2: No");
                        int response = scanner.nextInt();
                        if (response == 1) {
                            mainMenu();
                        } else {
                            endMessage();
                        }
                    } catch (InterruptedException | NullPointerException | ArrayIndexOutOfBoundsException ex) {
                        System.out.println("An error occurred - " + ex.getMessage());
                    }
                });
                thread.start();

                System.out.println("Checking for account...");
            }
            default ->
                System.out.println("Service unavailable");
        }
    }

    public static void endMessage() {
        System.out.println("Thanks for banking with us");
    }
}
