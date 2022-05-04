import java.util.ArrayList;
import java.util.Random;

public class Bank {
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    public Bank(String name) {
        this.name = name;
        users = new ArrayList<User>();
        accounts = new ArrayList<Account>();
    }

    public String getName() {
        return this.name;
    }

    public String getNewUserID() {
        int length = 6;
        String userID;
        boolean nonUnique;
        Random rng = new Random();

        do {
            userID = "";
            nonUnique = false;

            for (int i = 0; i < length; i++) {
                userID += ((Integer)rng.nextInt(10)).toString();
            }

            for (User u : users) {
                if (userID.matches(u.getUserID())) {
                    nonUnique = true;
                    break;
                }
            }

        } while (nonUnique);

        return userID;
    }

    public String getNewAccountID() {
        int length = 10;
        String accID;
        boolean nonUnique;
        Random rng = new Random();

        do {
            accID = "";
            nonUnique = false;

            for (int i = 0; i < length; i++) {
                accID += ((Integer)rng.nextInt(10)).toString();
            }

            for (Account a : accounts) {
                if (accID.matches(a.getAccID())) {
                    nonUnique = true;
                    break;
                }
            }

        } while (nonUnique);

        return accID;
    }

    public void addUser(User user) {
        this.users.add(user);

        // create a savings account for the user and add to User and Bank accounts lists
        Account newAccount = new Account("Savings", user, this);
        user.addAccount(newAccount);
        this.addAccount(newAccount);
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public User userLogin(String userID, String PIN) {
        for (User user : this.users) {
            if (userID.matches(user.getUserID()) && user.validatePIN(PIN)) {
                return user;
            }
        }

        return null;
    }
}
