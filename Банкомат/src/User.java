import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;
    private String userID;
    private byte pinHash[];
    private ArrayList<Account> accounts;


    public User(String firstName, String lastName, String pin, Bank theBank) {
        this.firstName = firstName;
        this.lastName = lastName;

        // create a new PIN
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        //get new user ID, create empty list of account
        this.userID = theBank.getNewUserID();
        this.accounts = new ArrayList<Account>();

        //add new user in bank
        theBank.addUser(this);

        System.out.printf("New user %s %s with %s ID created.\n", firstName, lastName, this.userID);
    }

    public String getFirstName() {
        return firstName;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public String getUserID() {
        return userID;
    }

    public boolean validatePIN(String aPIN) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPIN.getBytes()), this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        return false;
    }

    public void printAccountsSummary() {
        System.out.println("\n\n======================================================\n");
        System.out.printf("\n%s's accounts summary\n", this.firstName);
        for (int i = 0; i < accounts.size(); i++) {
            System.out.printf("%d. %s", i + 1, this.accounts.get(i).getSummaryLine());
            System.out.println();
        }
    }

    public int numAccounts() {
        return this.accounts.size();
    }

    public void printAcctTransHistory(int acctIndex) {
        this.accounts.get(acctIndex).printTransHistory();
    }

    public double getAcctBalance(int acctIndex) {
        return this.accounts.get(acctIndex).getBalance();
    }

    public String getAcctID(int acctIndex) {
        return this.accounts.get(acctIndex).getAccID();
    }

    public void addAcctTransaction(int acctIndex, double amount, String memo) {
        this.accounts.get(acctIndex).addTransaction(amount, memo);
    }
}
