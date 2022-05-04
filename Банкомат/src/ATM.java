import java.util.InputMismatchException;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Bank bank = new Bank("Ardshin Bank");
        User user = new User("David", "Mkrtchyan", "1234", bank);

        // add a checking account
        Account newAccount = new Account("Checking", user, bank);
        user.addAccount(newAccount);
        bank.addAccount(newAccount);

        User curUser;
        while (true) {
            //stay in the login prompt until successful login
            curUser = ATM.mainMenuPrompt(bank, scanner);

            // stay in main menu until user quits
            ATM.printUserMenu(curUser, scanner);
        }
    }

    public static User mainMenuPrompt(Bank bank, Scanner scanner) {
        String userID;
        String PIN;
        User curUser;

        do {
            System.out.printf("\n\nWelcome to %s\n\n", bank.getName());
            System.out.print("Enter ID: ");
            userID = scanner.next();
            System.out.print("Enter PIN: ");
            PIN = scanner.next();
            curUser = bank.userLogin(userID, PIN);

            if (curUser == null) {
                System.out.println("Incorrect user ID/PIN combination. Please try again");
            }

        } while (curUser == null); // continue looping until successful login

        return curUser;
    }

    public static void printUserMenu(User user, Scanner scanner) {
        // print a summary of user's accounts
        user.printAccountsSummary();

        int choice;

        do {
            System.out.println("\n------------------------------------------------------");
            System.out.printf("\nWelcome %s, what would you like to do?\n", user.getFirstName());
            System.out.println("1. Deposit");
            System.out.println("2. Withdrawal");
            System.out.println("3. Show account transaction history");
            System.out.println("4. Transfer");
            System.out.println("5. Quit\n");
            System.out.println("------------------------------------------------------\n");
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please choose 1-5");
            }
        } while (choice < 1 || choice > 5);

        // process the choice
        switch (choice) {
            case 1:
                ATM.depositFunds(user, scanner);
                break;
            case 2:
                ATM.withdrawalFunds(user, scanner);
                break;
            case 3:
                ATM.showTransHistory(user, scanner);
                break;
            case 4:
                ATM.transferFunds(user, scanner);
                break;
        }

        // redisplay the menu until the user wants to quit
        if (choice != 5) {
            ATM.printUserMenu(user, scanner);
        }
    }

    // case 1, process a fund deposit to an account
    public static void depositFunds(User user, Scanner scanner) {
        int toAcct;
        double amount;
        //double acctBalance;
        String memo;

        // get the account to deposit to
        do {
            System.out.printf("Enter the number (1-%d) of the account to deposit in: ", user.numAccounts());
            toAcct = scanner.nextInt() - 1;

            if (toAcct < 0 || toAcct >= user.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (toAcct < 0 || toAcct >= user.numAccounts());

        //acctBalance = user.getAcctBalance(toAcct);


        // get the amount to deposit
        do {
            System.out.print("Enter the amount to deposit: $");
            amount = scanner.nextFloat();

            if(amount < 0) {
                System.out.println("Amount must be greater than 0.");
            }
        } while (amount < 0);

        // gobble up rest of previous inputs
        //scanner.next();

        // get a memo
        System.out.print("Enter a memo: ");
        memo = scanner.next();

        // do the deposit
        user.addAcctTransaction(toAcct, amount, memo);
    }

    // case 2, process a fund withdraw from an account
    public static void withdrawalFunds(User user, Scanner scanner) {
        int fromAcct;
        double amount;
        double acctBalance;
        String memo;

        // get the account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account to withdraw from: ", user.numAccounts());
            fromAcct = scanner.nextInt() - 1;

            if (fromAcct < 0 || fromAcct >= user.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (fromAcct < 0 || fromAcct >= user.numAccounts());

        acctBalance = user.getAcctBalance(fromAcct);


        // get the amount to transfer
        do {
            System.out.printf("Enter the amount of transfer (max $%.02f): $", acctBalance);
            amount = scanner.nextDouble();

            if(amount < 0) {
                System.out.println("Amount must be greater than 0.");
            }
            else if (amount > acctBalance) {
                System.out.println("Amount must not be greater than balance.");
            }
        } while (amount < 0 || amount > acctBalance);


        // gobble up rest of previous inputs
        //scanner.next();

        // get a memo
        System.out.print("Enter a memo: ");
        memo = scanner.next();

        // do the withdraw
        user.addAcctTransaction(fromAcct, -1 * amount, memo);
    }

    // case 3
    public static void showTransHistory(User user, Scanner scanner) {
        int theAcct;

        // get account whose transactions history to look at
        do {
            System.out.printf("Enter the number (1-%d) of the account whose transactions you want to see: ", user.numAccounts());
            theAcct = scanner.nextInt() - 1;

            if (theAcct < 0 || theAcct >= user.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (theAcct < 0 || theAcct >= user.numAccounts());

        // print the transaction history
        user.printAcctTransHistory(theAcct);
    }

    // case 4, process transferring funds from one account to another
    public static void transferFunds(User user, Scanner scanner) {
        int fromAcct;
        int toAcct;
        double amount;
        double acctBalance;

        // get the account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account to transfer from: ", user.numAccounts());
            fromAcct = scanner.nextInt() - 1;

            if (fromAcct < 0 || fromAcct >= user.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (fromAcct < 0 || fromAcct >= user.numAccounts());

        acctBalance = user.getAcctBalance(fromAcct);


        // get account to transfer to
        do {
            System.out.printf("Enter the number (1-%d) of the account to transfer to: ", user.numAccounts());
            toAcct = scanner.nextInt() - 1;

            if (toAcct < 0 || toAcct >= user.numAccounts() || toAcct == fromAcct) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (toAcct < 0 || toAcct >= user.numAccounts());


        // get the amount to transfer
        do {
            System.out.printf("Enter the amount of transfer (max $%.02f): $", acctBalance);
            amount = scanner.nextDouble();

            if(amount < 0) {
                System.out.println("Amount must be greater than 0.");
            }
            else if (amount > acctBalance) {
                System.out.println("Amount must not be greater than balance.");
            }
        } while (amount < 0 || amount > acctBalance);


        // do the transfer
        user.addAcctTransaction(fromAcct, -1 * amount, String.format("Transfer to accoaunt %s", user.getAcctID(toAcct)));
        user.addAcctTransaction(toAcct, amount, String.format("Transfer to accoaunt %s", user.getAcctID(fromAcct)));
    }
}
