import java.io.Serializable;

public class Loan implements Serializable {
    public double amount;
    public double interest;
    public boolean approved = false;

    public Loan(double amount) {
        this.amount = amount;
        this.interest = round2(amount * 0.05); // 5% interest
    }

    private double round2(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
}
