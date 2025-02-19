import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ReportGen {

    private LoanCalculator lc;
    private ExpenseTracker et;
    private SavingsGoalEst sg;

    public ReportGen(LoanCalculator lc, ExpenseTracker et, SavingsGoalEst sg) {
        this.lc = lc;
        this.et = et;
        this.sg = sg;
    }

    public void generateReport(String filename) {

        try (FileWriter fw = new FileWriter(filename)) {
            fw.write("SmartFin Financial Summary Report\n");
            fw.write("=================================\n\n");
            fw.write("Loan Summary: \n");
            fw.write(generateLoanSummary(lc));
            fw.write("\nExpense Breakdown:\n ");
            fw.write(generateExpenseBreakdown());
            fw.write("\nSavings Projections:\n");
            fw.write(generateSavingsProjections());
            fw.write("\nTransaction History:\n");
            fw.write(generateTransactionHistory());

            System.out.println("Report saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing report: " + e.getMessage());
        }
    }

    public static String generateLoanSummary(LoanCalculator lc) {
        ArrayList<Loan> loans = lc.getLoans();
        if (loans.isEmpty()) {
            return "No loan records available.\n";
        }

        double highestPayment = Double.MIN_VALUE;
        double lowestPayment = Integer.MAX_VALUE;
        StringBuilder summaryStr = new StringBuilder();

        for (Loan loan : loans) {
            double payment = lc.calculateMonthlyPayment(loan);
            highestPayment = Math.max(highestPayment, payment);
            lowestPayment = Math.min(lowestPayment, payment);

            summaryStr.append(String.format(
                    "Principal: $%.2f | Rate: %.2f%% | Term: %.1f years | Monthly Payment: $%.2f\n",
                    loan.getPrincipal(), loan.getRate(), loan.getTerm(), payment));
        }

        summaryStr.append("\nHighest Monthly Payment: $").append(String.format("%.2f", highestPayment))
                .append("\nLowest Monthly Payment: $").append(String.format("%.2f", lowestPayment))
                .append("\n");

        return summaryStr.toString();
    }

    private String generateExpenseBreakdown() {
        List<Expense> expenses = et.getExpensesSortedByAmountDesc();

        if(expenses.isEmpty()){
            return "No expenses recorded.\n";
        }

        Map<String, Double> categoryTotals = new HashMap<>();

        //Using single map to store total amounts by category.
        for (Expense expense : expenses) {
            categoryTotals.put(expense.getCategory(),
            categoryTotals.getOrDefault(expense.getCategory(), 0.0) + expense.getAmount());
        }

        //Sort categories by total spent.
        List<Map.Entry<String, Double>> entryList = new ArrayList<>(categoryTotals.entrySet());
        entryList.sort((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()));

        StringBuilder breakdown = new StringBuilder();
        breakdown.append("Category-wise Spending:\n");

        for(Map.Entry<String, Double> entry : entryList) {
            breakdown.append(String.format("%s: $%.2f\n", entry.getKey(), entry.getValue()));
        }

        return breakdown.toString();
    }

    private String generateSavingsProjections() {
        StringBuilder projections = new StringBuilder();

        projections.append("Savings Projections:\n");

        projections.append(String.format("Savings in 6 months: $%.2f\n", sg.calculateFutureSavings(6)));
        projections.append(String.format("Savings in 1  year: $%.2f\n", sg.calculateFutureSavings(12)));
        projections.append(String.format("Savings in 5 years: $%.2f\n", sg.calculateFutureSavings(60)));

        return projections.toString();
    }

    private String generateTransactionHistory() {
        List<Expense> expenses = et.getExpensesSortedByAmountDesc();

        if (expenses.isEmpty()) {
            return "No transactions recorded.\n";
        }

        expenses.sort((e1, e2) -> e1.getDate().compareTo(e2.getDate()));

        StringBuilder history = new StringBuilder();
        history.append("Transaction History:\n");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        for (Expense expense : expenses) {
            String formattedDate = expense.getDate().format(formatter);

            history.append(String.format("Date: %s | Category: %s | Amount: $%.2f | Description: %s\n",
            formattedDate, expense.getCategory(), expense.getAmount(), expense.getDescription()));
        }

        return history.toString();
    }
}
