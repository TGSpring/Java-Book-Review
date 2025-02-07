
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class LoanCalculatorTest {

    @Test
    public void testLoanCalcValidValues() {
        LoanCalculator lc = new LoanCalculator();

        // Test 1: Example loan scenario
        double principal = 10000;   // Example loan amount
        double rate = 5.0;          // Example interest rate
        int term = 15;              // Example loan term in years
        double expectedPayment = 79.19;  // Expected result based on these inputs

        double actualPayment = lc.loanCalc(principal, rate, term);

        // Assert that the actual monthly payment is approximately equal to the expected value
        assertEquals(expectedPayment, actualPayment, 0.2, "Monthly payment should match the expected value.");

        // Test 2: Another loan scenario with different values
        principal = 50000;
        rate = 3.5;
        term = 10;
        expectedPayment = 494.43;  // Updated expected result based on manual calculation

        actualPayment = lc.loanCalc(principal, rate, term);

        // Assert again for different loan values
        assertEquals(expectedPayment, actualPayment, 0.2, "Monthly payment should match the expected value.");
    }

    @Test
    public void testLoanCalcInvalidValues() {
        LoanCalculator lc = new LoanCalculator();

        // Test: Loan with negative principal
        assertThrows(IllegalArgumentException.class, () -> {
            lc.loanCalc(-10000, 5.0, 15);  // Invalid negative loan amount
        }, "Should throw IllegalArgumentException for negative principal.");

        // Test: Loan with zero principal
        assertThrows(IllegalArgumentException.class, () -> {
            lc.loanCalc(0, 5.0, 15);  // Invalid zero loan amount
        }, "Should throw IllegalArgumentException for zero principal.");
    }
}