import java.util.ArrayList;

public class Account {
    private String name;
    private String accountID;
    private User holder;
    private ArrayList<Transactions> transakctions;

    public Account(String name, User holder, Bank theBank) {
        this.name = name;
        this.holder = holder;

        //get new account ID, create empty list of transactions
        this.accountID = theBank.getNewAccountID();
        this.transakctions = new ArrayList<Transactions>();
    }

    public String getAccID() {
        return accountID;
    }

    // get summary line for account
    public String getSummaryLine() {
        double balance = this.getBalance();

        if (balance >= 0) {
            return String.format("%s : $%.02f : %s", this.accountID, balance, this.name);
        }
        else {
            return String.format("%s : $(%.02f) : %s", this.accountID, balance, this.name);
        }
    }

    public double getBalance() {
        double balance = 0;
        for (Transactions t : this.transakctions) {
            balance += t.getAmount();
        }
        return balance;
    }

    public void printTransHistory() {
        System.out.printf("\nTransaction history for the account %s\n", this.accountID);

        for (int i = this.transakctions.size() - 1; i >= 0; i--) {
            System.out.println(this.transakctions.get(i).getSummaryLine());
        }
        System.out.println();
    }

    // create a new transaction object and add it to list
    public void addTransaction(double amount, String memo) {
        Transactions newTrans = new Transactions(amount, memo, this);
        this.transakctions.add(newTrans);
    }
}
