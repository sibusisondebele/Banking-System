package bank.model;

/** Pays 0.05% monthly interest. Deposits only; withdrawals are not allowed. */
public class SavingsAccount extends Account implements InterestBearing {
    public static final double MONTHLY_RATE = 0.0005; // 0.05%

    public SavingsAccount(String accountNumber, String branch, Customer owner, double initialDeposit) {
        super(accountNumber, branch, owner, initialDeposit);
    }

    @Override
    public void withdraw(double amount) {
        throw new BankException("Withdrawals are not allowed from a Savings account.");
    }

    @Override
    public double getMonthlyInterestRate() { return MONTHLY_RATE; }

    @Override
    public double payInterest() {
        double interest = getBalance() * getMonthlyInterestRate();
        if (interest > 0) credit(interest, Transaction.Type.INTEREST, "Monthly interest");
        return interest;
    }

    @Override
    public String getAccountType() { return "Savings"; }
}
