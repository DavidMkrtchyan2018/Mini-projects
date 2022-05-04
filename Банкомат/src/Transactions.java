import java.util.Date;

public class Transactions {
    private double amount;
    private Date timestamp = new Date();
    private String memo;
    private Account inAccount;

    public Transactions(double amount, Account inAccount) {
        this.amount = amount;
        this.inAccount = inAccount;
        this.memo = "";
    }

    public Transactions(double amount, String memo, Account inAccount) {
        // call the first constructor
        this(amount, inAccount);

        this.memo = memo;
    }

    public double getAmount() {
        return this.amount;
    }

    // get string summarizing the transaction
    public String getSummaryLine() {
        if (this.amount >= 0) {
            return String.format("%s : $%.02f : %s", this.timestamp.toString(), this.amount, this.memo);
        }
        else {
            return String.format("%s : $(%.02f) : %s", this.timestamp.toString(), this.amount, this.memo);
        }
    }
}
