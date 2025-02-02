/*
 * Loan calculator with error handling
	- Modify the loan calculator to handle inputs (negatives, zeros, or non-numeric input).
	- Allow user to enter multiple loan scenarios and store them in an array for later review.
	CHALLENGE:
		Implement a method that takes an array of different loans and returns the lowest monthly payment.
 */
import java.util.ArrayList;

public class LoanCalculator {
    private ArrayList<Double> loanPayments;

    public LoanCalculator() {
        this.loanPayments = new ArrayList<>();
    }

    public double loanCalc(double principal, double rate, double term) {
        rate = rate / 100 / 12;
        int months = (int) term * 12;
        double monthlyPayment = (principal * rate) / (1 - Math.pow(1 + rate, -months));

        loanPayments.add(monthlyPayment);
        return monthlyPayment;
    }

    public double getLowestPayment(ArrayList<Loan> loans) {
        if (loans.isEmpty()) {
            System.out.println("No loan payments stored yet");
            return -1;
        }

        // Calculate payments and store them in an array
        double[] payments = new double[loans.size()];
        for (int i = 0; i < loans.size(); i++) {
            Loan loan = loans.get(i);
            payments[i] = loanCalc(loan.getPrincipal(), loan.getRate(), loan.getTerm());
        }

        // Insertion Sort
        for (int i = 1; i < payments.length; i++) {
            double key = payments[i];
            int j = i - 1;
            while (j >= 0 && payments[j] > key) {
                payments[j + 1] = payments[j];
                j = j - 1;
            }
            payments[j + 1] = key;
        }

        return payments[0]; // The first element is the lowest after sorting
    }
}