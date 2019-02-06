package de.dhbwka.exercise.classes;

import lombok.Getter;
import lombok.Setter;

/**
 * The Account.
 *
 * @author Leonhard Gahr
 */
public class Account {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";

    @Getter
    private final String IBAN;

    @Getter
    @Setter
    private String name;

    @Getter
    private double balance;

    @Getter
    @Setter
    private double limit;

    /**
     * Instantiates a new Account.
     *
     * @param IBAN    the iban (must be a valid IBAN)
     * @param name    the name
     * @param balance the balance (must be >= 0)
     * @param limit   the limit (must be >= 0)
     */
    public Account(String IBAN, String name, double balance, double limit) {
        if (!IBAN.matches("^[A-Z]{2}(?:[ ]?[0-9]){18,20}$"))
            throw new IllegalArgumentException("Invalid IBAN provided");
        if (balance < 0 || limit < 0) throw new IllegalArgumentException("Balance and limit must not be negative");
        this.IBAN = IBAN;
        this.name = name;
        this.balance = balance;
        this.limit = limit;
    }

    /**
     * Instantiates a new Account with a default balance (0)
     *
     * @param IBAN  the iban
     * @param name  the name
     * @param limit the limit
     */
    public Account(String IBAN, String name, double limit) {
        this(IBAN, name, 0, limit);
    }

    /**
     * Instantiates a new Account with a default balance (0) and limit (1000)
     *
     * @param IBAN the iban
     * @param name the name
     */
    public Account(String IBAN, String name) {
        this(IBAN, name, 0, 1000);
    }

    /**
     * Process deposit.
     *
     * @param amount the amount (must be > 0)
     */
    public void processDeposit(double amount) {
        assert amount > 0 : "Value must be greater than 0";
        balance += amount;
    }

    /**
     * Process payment boolean.
     *
     * @param amount the amount
     * @return the boolean whether the payment has been processed or the process failed due to insufficient
     */
    public boolean processPayment(double amount) {
        assert amount > 0 : "Value must be greater than 0";

        if (balance - amount < -limit) {
            return false;
        }

        balance -= amount;
        return true;
    }

    @Override
    public String toString() {
        return String.format("IBAN: %s (%s)%nBalance: %s%.2f€%s; Limit: %.2f€", IBAN, name, balance < 0 ? ANSI_RED : ANSI_RESET, balance, ANSI_RESET, limit);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Account account = new Account("DE97 2424 2424 2442 2432 23", "Donald Duck", 500, 1000);
        System.out.println(account);
        account.processDeposit(200);
        System.out.println(account);
        account.processPayment(900);
        System.out.println(account);
        if (!account.processPayment(2000)) System.out.println(ANSI_RED + "Account doesn't cover payment" + ANSI_RESET);
        System.out.println(account);
    }
}
