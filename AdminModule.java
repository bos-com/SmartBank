import java.util.ArrayList;

public class AdminModule {

    // Approving a loan
    public static void approveLoan(User user, Loan loan) {
        if (!loan.approved) {
            loan.approved = true;
            user.balance += loan.amount;
            Bank.collectedInterest += loan.interest;
            user.transactions.add("Loan approved: $" + loan.amount + " | Interest: $" + loan.interest);
            SmartBank_Notifications.notifyUser(user, "Your loan of $" + loan.amount + " has been approved.");
        }
    }

    // Approve all pending loans 
    public static void approveAllLoans() {
        for (User u : Bank.users.values()) {
            for (Loan l : u.loans) {
                if (!l.approved) approveLoan(u, l);
            }
        }
    }

    // Process withdrawal requests (stored as "Withdrawal requested: $X" in transactions)
    public static String processWithdrawals() {
        StringBuilder sb = new StringBuilder();
        for (User u : Bank.users.values()) {
            ArrayList<String> keep = new ArrayList<>();
            for (String tx : u.transactions) {
                if (tx.startsWith("Withdrawal requested:")) {
                    try {
                        String amtStr = tx.replace("Withdrawal requested: $", "").trim();
                        double amt = Double.parseDouble(amtStr);
                        if (amt <= u.balance) {
                            u.balance -= amt;
                            u.transactions.add("Withdrawal approved: $" + amt);
                            sb.append("Approved withdrawal for ").append(u.username).append(": $").append(amt).append("\n");
                        } else {
                            keep.add(tx); // can't approve, keep request
                            sb.append("Cannot approve withdrawal for ").append(u.username).append(": $").append(amt).append(" (insufficient)\n");
                        }
                    } catch (Exception ex) {
                        keep.add(tx); // malformed, keep
                        sb.append("Error parsing withdrawal for ").append(u.username).append("\n");
                    }
                } else {
                    keep.add(tx);
                }
            }
            u.transactions = keep;
        }
        return sb.toString();
    }

    // Broadcast message to all users
    public static void broadcast(String message) {
        SmartBank_Notifications.notifyAllUsers(message);
    }

    // Return a simple report of users
    public static String usersReport() {
        StringBuilder sb = new StringBuilder();
        for (User u : Bank.users.values()) {
            sb.append(u.username)
              .append(" | Balance: $").append(u.balance)
              .append(" | Loans: ").append(u.loans.size())
              .append("\n");
        }
        return sb.toString();
    }

    // Return total assets
    public static double totalAssets() {
        return Bank.totalBankAsset();
    }
}
