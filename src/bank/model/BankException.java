package bank.model;

/** Thrown when a banking business rule is broken (e.g. withdrawing from a savings account). */
public class BankException extends RuntimeException {
    public BankException(String message) {
        super(message);
    }
}
