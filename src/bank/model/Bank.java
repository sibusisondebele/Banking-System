package bank.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Opens accounts for customers and runs the monthly interest payment. */
public class Bank {
    private final String name;
    private final String branch;
    private final List<Customer> customers = new ArrayList<>();
    private int nextAccountNumber = 1001;

    public Bank(String name, String branch) {
        this.name = name;
        this.branch = branch;
    }

    public void registerCustomer(Customer customer) {
        if (customer == null) throw new BankException("Customer is required.");
        customers.add(customer);
    }

    public SavingsAccount openSavingsAccount(Customer customer, double initialDeposit) {
        SavingsAccount acc = new SavingsAccount(newAccountNumber(), branch, customer, initialDeposit);
        customer.addAccount(acc);
        return acc;
    }

    public InvestmentAccount openInvestmentAccount(Customer customer, double initialDeposit) {
        InvestmentAccount acc = new InvestmentAccount(newAccountNumber(), branch, customer, initialDeposit);
        customer.addAccount(acc);
        return acc;
    }

    public ChequeAccount openChequeAccount(Customer customer, double initialDeposit, Employer employer) {
        if (employer == null) throw new BankException("Only employed customers can open a Cheque account.");
        ChequeAccount acc = new ChequeAccount(newAccountNumber(), branch, customer, initialDeposit, employer);
        customer.addAccount(acc);
        return acc;
    }

    /** Pays interest on every interest-bearing account (polymorphism via the interface). */
    public double payMonthlyInterest() {
        double total = 0;
        for (Customer c : customers) {
            for (Account a : c.getAccounts()) {
                if (a instanceof InterestBearing ib) {
                    total += ib.payInterest();
                }
            }
        }
        return total;
    }

    private String newAccountNumber() { return String.valueOf(nextAccountNumber++); }

    public String getName() { return name; }
    public String getBranch() { return branch; }
    public List<Customer> getCustomers() { return Collections.unmodifiableList(customers); }
}
