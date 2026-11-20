package bank.model;

/** Pays 5% monthly interest. Needs BWP500 to open. Deposits and withdrawals allowed. */
public class InvestmentAccount extends Account implements InterestBearing {
    public static final double MONTHLY_RATE = 0.05; // 5%
    public static final double MINIMUM_OPENING_DEPOSIT = 500.00;

    public InvestmentAccount(String accountNumber, String branch, Customer owner, double initialDeposit) {
        super(accountNumber, branch, owner, checkOpeningDeposit(initialDeposit));
    }

    // Runs before the account is created so a failed rule never leaves a half-built account.
    private static double checkOpeningDeposit(double initialDeposit) {
        if (initialDeposit < MINIMUM_OPENING_DEPOSIT) {
            throw new BankException("An Investment account needs an initial deposit of at least BWP"
                    + String.format("%.2f", MINIMUM_OPENING_DEPOSIT) + ".");
        }
        return initialDeposit;
    }

    @Override
    public void withdraw(double amount) {
        debit(amount, "Withdrawal");
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
    public String getAccountType() { return "Investment"; }
}
