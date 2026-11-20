package bank.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Abstract base for all account types. An account cannot exist without an owner. */
public abstract class Account {
    private final String accountNumber;
    private final String branch;
    private final Customer owner;
    private double balance;
    private final List<Transaction> transactions = new ArrayList<>();

    protected Account(String accountNumber, String branch, Customer owner, double initialDeposit) {
        if (owner == null) throw new BankException("An account must be held by a customer.");
        if (accountNumber == null || accountNumber.isBlank()) throw new BankException("Account number is required.");
        if (initialDeposit < 0) throw new BankException("Initial deposit cannot be negative.");
        this.accountNumber = accountNumber;
        this.branch = branch;
        this.owner = owner;
        if (initialDeposit > 0) {
            credit(initialDeposit, Transaction.Type.DEPOSIT, "Initial deposit");
        }
    }

    // ---- Overloaded deposit methods ----
    public void deposit(double amount) {
        deposit(amount, "Deposit");
    }

    public void deposit(double amount, String description) {
        validateAmount(amount);
        credit(amount, Transaction.Type.DEPOSIT, description);
    }

    /** Each account type decides its own withdrawal rules (polymorphism). */
    public abstract void withdraw(double amount);

    /** Human-readable account type, e.g. "Savings". */
    public abstract String getAccountType();

    // ---- Helpers for subclasses ----
    protected void credit(double amount, Transaction.Type type, String description) {
        balance += amount;
        transactions.add(new Transaction(type, amount, description));
    }

    protected void debit(double amount, String description) {
        validateAmount(amount);
        if (amount > balance) throw new BankException("Insufficient funds.");
        balance -= amount;
        transactions.add(new Transaction(Transaction.Type.WITHDRAWAL, amount, description));
    }

    protected static void validateAmount(double amount) {
        if (amount <= 0) throw new BankException("Amount must be greater than zero.");
    }

    // ---- Getters (encapsulation: balance has no setter) ----
    public String getAccountNumber() { return accountNumber; }
    public String getBranch() { return branch; }
    public Customer getOwner() { return owner; }
    public double getBalance() { return balance; }
    public List<Transaction> getTransactions() { return Collections.unmodifiableList(transactions); }

    @Override
    public String toString() {
        return String.format("%s [%s] %s | Balance: BWP %,.2f", getAccountType(), accountNumber, branch, balance);
    }
}
