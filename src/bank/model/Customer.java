package bank.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** A bank customer who can hold multiple accounts. */
public class Customer {
    private final String customerId;
    private String firstName;
    private String surname;
    private String address;
    private final List<Account> accounts = new ArrayList<>();

    public Customer(String customerId, String firstName, String surname, String address) {
        if (customerId == null || customerId.isBlank()) throw new BankException("Customer ID is required.");
        this.customerId = customerId;
        setFirstName(firstName);
        setSurname(surname);
        setAddress(address);
    }

    /** Called by Bank after an account is created for this customer. */
    void addAccount(Account account) {
        if (account.getOwner() != this) throw new BankException("Account belongs to a different customer.");
        accounts.add(account);
    }

    public double getTotalBalance() {
        double total = 0;
        for (Account a : accounts) total += a.getBalance();
        return total;
    }

    public String getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getSurname() { return surname; }
    public String getAddress() { return address; }
    public List<Account> getAccounts() { return Collections.unmodifiableList(accounts); }

    public void setFirstName(String firstName) { this.firstName = requireText(firstName, "First name"); }
    public void setSurname(String surname) { this.surname = requireText(surname, "Surname"); }
    public void setAddress(String address) { this.address = requireText(address, "Address"); }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) throw new BankException(field + " is required.");
        return value;
    }

    @Override
    public String toString() { return customerId + ": " + firstName + " " + surname; }
}
