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
    private ArrayList<Double> loanPayments;

    public LoanCalculator() {
        this.loanPayments = new ArrayList<>();
    }

    public double loanCalc(double principal, double rate, double term) {
        //Validation
        if(principal <= 0 || rate <= 0 || term <= 0) {
            throw new IllegalArgumentException("Values must be greater than 0");
        }

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

        PriorityQueue<Double> minHeap = new PriorityQueue<>();
        for (Loan loan : loans) {
            double payment = loanCalc(loan.getPrincipal(), loan.getRate(), loan.getTerm());
            minHeap.add(payment);
        }
        return minHeap.peek(); //Smallest element is at the head of the queue.
        
    }
}