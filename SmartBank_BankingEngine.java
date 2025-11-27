public class SmartBank_BankingEngine {

    public static void deposit(User user, double amount){
        user.balance += amount;
        user.transactions.add("Deposit: $" + amount);
    }

    public static void submitLoan(User user, double amount){
        Loan loan = new Loan(amount);
        user.loans.add(loan);
        user.transactions.add("Loan requested: $" + amount);
    }

    public static void approveLoan(User user, Loan loan){
        loan.approved = true;
        user.balance += loan.amount; 
        Bank.collectedInterest += loan.interest;
        user.transactions.add("Loan approved: $" + loan.amount + " | Interest: $" + loan.interest);
        SmartBank_Notifications.notifyUser(user, "Your loan of $" + loan.amount + " has been approved!");
    }
}
