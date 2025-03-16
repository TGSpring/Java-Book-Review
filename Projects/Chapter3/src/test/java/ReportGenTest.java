import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

public class ReportGenTest {
    private final String filename = "test_Report.txt";
    private final String filenameE = "Empty_test_Report.txt";

    @Test
    public void testGenerateReport() {
        LoanCalculator lcT = new LoanCalculator();
        LoanCalculator lcTE = new LoanCalculator();

        ExpenseTracker etT = new ExpenseTracker();
        ExpenseTracker etTE = new ExpenseTracker();

        SavingsGoalEst sgT = new SavingsGoalEst();
        SavingsGoalEst sgTE = new SavingsGoalEst();

        Expense expense = new Expense(1000, "Testing", "Pets");

        lcT.loanCalc(1., 2, 3);
        etT.addExpense(expense);
        sgT.addSavings(20);
        sgT.calculateFutureSavings(200);
        sgT.monthlyIncrease(50);

        ReportGen rg = new ReportGen(lcT, etT, sgT);
        ReportGen rgE = new ReportGen(lcTE, etTE, sgTE);

        rg.generateReport(filename);
        rgE.generateReport(filenameE);

        String TestReport = readFileContent(filename);

        assertTrue(TestReport.contains("SmartFin Financial Summary Report"));
        assertTrue(TestReport.contains("Loan Summary:"));
        assertTrue(TestReport.contains("Expense Breakdown:"));
        assertTrue(TestReport.contains("Savings Projections:"));
        assertTrue(TestReport.contains("Transaction History:"));

    }

    //This is turned off for viewing after "mvn test" is ran. To have included in test, uncomment.
    /**@AfterEach
    public void cleanUp() {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
    }**/

    private String readFileContent(String fileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
