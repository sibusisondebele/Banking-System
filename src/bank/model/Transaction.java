package bank.model;

import java.time.LocalDateTime;

/** A single record of money moving in or out of an account. */
public class Transaction {
    public enum Type { DEPOSIT, WITHDRAWAL, INTEREST }

    private final Type type;
    private final double amount;
    private final String description;
    private final LocalDateTime timestamp;

    public Transaction(Type type, double amount, String description) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    public Type getType() { return type; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format("%s | %-10s | BWP %,10.2f | %s", timestamp.withNano(0), type, amount, description);
    }
}
