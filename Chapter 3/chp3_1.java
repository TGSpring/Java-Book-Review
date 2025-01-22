/**
 * Tyler Spring
 * 1/22/2025
 * 
 * Parameter Usage and Constants
 * Write a method calcMonthPayment that takes 3 parameters.
 * A loan (double)
 * An annual interest rate (double)
 * A loan in years (int)
 * The method should return the monthly payment using the formula:
 * monthPayment = ((P * r) * (1 + r)^n) / ((1 + r)^n) - 1)
 * P = loan amount
 * r = annual interest rate / 12
 * n = loan in years * 12
 * ** Ensure the annual interest rate is constant at 5% if it is not provided.**
 */
public class chp3_1 {
    public static final double ANNUAL_INTEREST_RATE = 0.05;

    public static double calcMonthPayment(double loan, double annualInterestRate, int loanYears) {
        if (annualInterestRate <= 0) {
            annualInterestRate = ANNUAL_INTEREST_RATE;
        }
            annualInterestRate = annualInterestRate / 12;
            int n = loanYears * 12; // Number of payments
            double r = annualInterestRate / 12; // Monthly interest rate

            // Monthly Payment formula
            double rPowN = Math.pow(1 + r, n);
            double monthPayment = (loan * r * rPowN) / (rPowN - 1);

            System.out.println("Monthly interest rate (r): " + r);
            System.out.println("(1 + r)^n: " + rPowN);
            System.out.println("Numerator: " + (loan * r * rPowN));
            System.out.println("Denominator: " + (rPowN - 1));

            return monthPayment;
    }

    public static void main(String[] args) {

        double loan = 1000;
        double annualInterestRate = 0.06;
        int loanYears = 2;

        System.out.println("The monthly payment is: " +
                calcMonthPayment(loan, annualInterestRate, loanYears));

    }
}