import java.util.Scanner;

public class SavingsGoalEst {
    private double savingsGoal;
    private double currentSavings;
    private double monthlyContribution;
    private double monthlyIncrease;


    public SavingsGoalEst() {
        
    }

    public SavingsGoalEst(double savingsGoal, double currentSavings, double monthlyContribution, double monthlyIncrease) {
        this.savingsGoal = savingsGoal;
        this.currentSavings = currentSavings;
        this.monthlyContribution = monthlyContribution;
        this.monthlyIncrease = monthlyIncrease;
    }
 
    public double getRemainingAmount() {
        return this.savingsGoal - this.currentSavings;
    }

    public void addSavings(double amount) {
        this.currentSavings += amount;
    }

    public void setMonthlyContribution(double contribution) {
        this.monthlyContribution = contribution;
    }

    public void monthlyIncrease(double increase) {
        this.monthlyIncrease = increase;
    }

    public double calculateFutureSavings(double months) {
        double futureSavings = this.currentSavings;
        double contribution = this.monthlyContribution;

        for(int i = 0; i < months; i++) {
            futureSavings += contribution;
            contribution += this.monthlyIncrease;
        }
        return futureSavings;
    }

    public void printProjections() {
        System.out.printf("Savings in 6 months: $%.2f\n", calculateFutureSavings(6));
        System.out.printf("Savings in 1 year: $%.2f\n", calculateFutureSavings((12)));
        System.out.printf("Savings in 5 years: $%.2f\n", calculateFutureSavings((60)));
    }

}
