
/*
 * Loan calculator with error handling
	- Modify the loan calculator to handle inputs (negatives, zeros, or non-numeric input).
	- Allow user to enter multiple loan scenarios and store them in an array for later review.
	CHALLENGE:
		Implement a method that takes an array of different loans and returns the lowest monthly payment.
 */
import java.util.ArrayList;
import java.util.PriorityQueue;

public class LoanCalculator {
    private ArrayList<Loan> loans;

    public LoanCalculator() {
        this.loans = new ArrayList<>();
    }

    public double loanCalc(double principal, double rate, double term) {
        // Validation
        if (principal <= 0 || rate <= 0 || term <= 0) {
            throw new IllegalArgumentException("Values must be greater than 0");
        }

        rate = rate / 100 / 12;
        int months = (int) term * 12;
        double monthlyPayment = (principal * rate) / (1 - Math.pow(1 + rate, -months));

        Loan loan = new Loan(principal, rate, term);
        loans.add(loan);

        return calculateMonthlyPayment(loan);
    }

    public double calculateMonthlyPayment(Loan loan) {
        double rate = loan.getRate() / 100 / 12;
        int months = (int) loan.getTerm() * 12;
        return (loan.getPrincipal() * rate) / (1 - Math.pow(1 + rate, -months));
    }

    public double getLowestPayment(ArrayList<Loan> loansList) {
        if (loansList.isEmpty()) {
            System.out.println("No loan payments stored yet");
            return -1;
        }
        double lowestPayment = Double.MAX_VALUE;
        for (Loan loan : loansList) {
            double payment = calculateMonthlyPayment(loan);
            lowestPayment = Math.min(lowestPayment, payment);
        }
        return lowestPayment;

    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }
}