import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int fromCurr;
        int toCurr;
        double amount;
        Scanner scanner = new Scanner(System.in);

        System.out.println("15/02/2022\n\n");
        System.out.println("1. USD");
        System.out.println("2. EUR");
        System.out.println("3. RUB");
        System.out.println("4. GBP");
        System.out.println("5. JPY");
        System.out.println("6. CNY\n");
        System.out.print("Select the currency you want to convert from: ");
        fromCurr = scanner.nextInt();
        System.out.print("Select the currency you want to convert to: ");
        toCurr = scanner.nextInt();
        System.out.print("Enter amount: ");
        amount = scanner.nextDouble();

        switch (fromCurr) {
            case 1:
                Main.fromUSD(toCurr, amount);
                break;
            case 2:
                Main.fromEUR(toCurr, amount);
                break;
            case 3:
                Main.fromRUB(toCurr, amount);
                break;
            case 4:
                Main.fromGBP(toCurr, amount);
                break;
            case 5:
                Main.fromJPY(toCurr, amount);
                break;
            case 6:
                Main.fromCNY(toCurr, amount);
                break;
        }
    }

    public static void fromUSD(int toCurr, double amount) {
        switch (toCurr) {
            case 2:
                System.out.printf("%.02f USD - %.02f EUR\n", amount, amount * 0.88013);
                break;
            case 3:
                System.out.printf("%.02f USD - %.02f RUB\n", amount, amount * 76.58);
                break;
            case 4:
                System.out.printf("%.02f USD - %.02f GBP\n", amount, amount * 0.73);
                break;
            case 5:
                System.out.printf("%.02f USD - %.02f JPY\n", amount, amount * 115.65);
                break;
            case 6:
                System.out.printf("%.02f USD - %.02f CNY\n", amount, amount * 6.36);
                break;
        }
    }

    public static void fromEUR(int toCurr, double amount) {
        switch (toCurr) {
            case 1:
                System.out.printf("%.02f EUR - %.02f USD\n", amount, amount * 1.14);
                break;
            case 3:
                System.out.printf("%.02f EUR - %.02f RUB\n", amount, amount * 86.85);
                break;
            case 4:
                System.out.printf("%.02f EUR - %.02f GBP\n", amount, amount * 0.83765);
                break;
            case 5:
                System.out.printf("%.02f EUR - %.02f JPY\n", amount, amount * 131.18);
                break;
            case 6:
                System.out.printf("%.02f EUR - %.02f CNY\n", amount, amount * 7.2);
                break;
        }
    }

    public static void fromRUB(int toCurr, double amount) {
        switch (toCurr) {
            case 1:
                System.out.printf("%.02f RUB - %.02f USD\n", amount, amount * 0.013059);
                break;
            case 2:
                System.out.printf("%.02f RUB - %.02f EUR\n", amount, amount * 0.011515);
                break;
            case 4:
                System.out.printf("%.02f RUB - %.02f GBP\n", amount, amount * 0.009665);
                break;
            case 5:
                System.out.printf("%.02f RUB - %.02f JPY\n", amount, amount * 1.5);
                break;
            case 6:
                System.out.printf("%.02f RUB - %.02f CNY\n", amount, amount * 0.083045);
                break;
        }
    }

    public static void fromGBP(int toCurr, double amount) {
        switch (toCurr) {
            case 1:
                System.out.printf("%.02f GBP - %.02f USD\n", amount, amount * 1.35);
                break;
            case 2:
                System.out.printf("%.02f GBP - %.02f EUR\n", amount, amount * 1.19);
                break;
            case 3:
                System.out.printf("%.02f GBP - %.02f RUB\n", amount, amount * 103.46);
                break;
            case 5:
                System.out.printf("%.02f GBP - %.02f JPY\n", amount, amount * 155.19);
                break;
            case 6:
                System.out.printf("%.02f GBP - %.02f CNY\n", amount, amount * 8.59);
                break;
        }
    }

    public static void fromJPY(int toCurr, double amount) {
        switch (toCurr) {
            case 1:
                System.out.printf("%.02f JPY - %.02f USD\n", amount, amount * 0.008647);
                break;
            case 2:
                System.out.printf("%.02f JPY - %.02f EUR\n", amount, amount * 0.007623);
                break;
            case 3:
                System.out.printf("%.02f JPY - %.02f RUB\n", amount, amount * 0.6648);
                break;
            case 4:
                System.out.printf("%.02f JPY - %.02f GBP\n", amount, amount * 0.006444);
                break;
            case 6:
                System.out.printf("%.02f JPY - %.02f CNY\n", amount, amount * 0.055371);
                break;
        }
    }

    public static void fromCNY(int toCurr, double amount) {
        switch (toCurr) {
            case 1:
                System.out.printf("%.02f CNY - %.02f USD\n", amount, amount * 0.15723);
                break;
            case 2:
                System.out.printf("%.02f CNY - %.02f EUR\n", amount, amount * 0.13895);
                break;
            case 3:
                System.out.printf("%.02f CNY - %.02f RUB\n", amount, amount * 12.04);
                break;
            case 4:
                System.out.printf("%.02f CNY - %.02f GBP\n", amount, amount * 0.11639);
                break;
            case 5:
                System.out.printf("%.02f CNY - %.02f JPY\n", amount, amount * 18.06);
                break;
        }
    }
}
