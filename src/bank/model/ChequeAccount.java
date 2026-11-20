package bank.model;

/** Salary account. No interest, deposits and withdrawals of any amount. Needs employer details. */
public class ChequeAccount extends Account {
    private final Employer employer;

    public ChequeAccount(String accountNumber, String branch, Customer owner, double initialDeposit, Employer employer) {
        super(accountNumber, branch, owner, initialDeposit);
        this.employer = employer;
    }

    @Override
    public void withdraw(double amount) {
        debit(amount, "Withdrawal");
    }

    public Employer getEmployer() { return employer; }

    @Override
    public String getAccountType() { return "Cheque"; }
}
