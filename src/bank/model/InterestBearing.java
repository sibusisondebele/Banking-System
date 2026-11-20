package bank.model;

/** Interface for any account that earns monthly interest. */
public interface InterestBearing {
    /** Monthly interest rate as a fraction (e.g. 0.05 = 5%). */
    double getMonthlyInterestRate();

    /** Calculates interest on the current balance, credits it, and returns the amount paid. */
    double payInterest();
}
