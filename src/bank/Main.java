import bank.model.*;

/** Console test of the core model (no GUI or database yet). */
public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank("Demo Bank", "Gaborone Main");
        Customer thato = new Customer("C001", "Thato", "Molefe", "Plot 123, Gaborone");
        bank.registerCustomer(thato);

        SavingsAccount sav = bank.openSavingsAccount(thato, 1000);
        InvestmentAccount inv = bank.openInvestmentAccount(thato, 500);
        ChequeAccount chq = bank.openChequeAccount(thato, 0, new Employer("Acme Ltd", "Plot 9, Francistown"));

        chq.deposit(8000, "Salary");
        inv.deposit(250);
        inv.withdraw(100);

        System.out.println("-- Rule checks --");
        try { sav.withdraw(50); } catch (BankException e) { System.out.println("Savings withdraw: " + e.getMessage()); }
        try { bank.openInvestmentAccount(thato, 100); } catch (BankException e) { System.out.println("Investment open: " + e.getMessage()); }
        try { chq.withdraw(99999); } catch (BankException e) { System.out.println("Cheque overdraw: " + e.getMessage()); }

        System.out.printf("%nInterest paid this month: BWP %,.2f%n", bank.payMonthlyInterest());

        System.out.println("\n-- Accounts for " + thato + " --");
        for (Account a : thato.getAccounts()) System.out.println(a);
        System.out.printf("Total balance: BWP %,.2f%n", thato.getTotalBalance());

        System.out.println("\n-- Investment transactions --");
        inv.getTransactions().forEach(System.out::println);
    }
}
