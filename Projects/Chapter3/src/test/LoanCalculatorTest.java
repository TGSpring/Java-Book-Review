import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LoanCalculatorTest {

    @Test
    public void testLoanCalc() {
        // Create an instance of the SmartFin class
        SmartFin sf = new SmartFin();

        // Test 1: Valid loan inputs
        double principal = 10000;  // Example loan amount
        double rate = 5.0;         // Example interest rate
        int term = 15;             // Example loan term in years
        double expectedPayment = 79.19;  // Expected result based on these inputs

        double actualPayment = sf.loanCalc(principal, rate, term);

        // Assert that the actual monthly payment is approximately equal to the expected value
        assertEquals(expectedPayment, actualPayment, 0.01, "Monthly payment should match the expected value.");

        // Test 2: Another loan scenario with different values
        principal = 50000;
        rate = 3.5;
        term = 10;
        expectedPayment = 498.91;

        actualPayment = sf.loanCalc(principal, rate, term);

        // Assert again for different loan values
        assertEquals(expectedPayment, actualPayment, 0.01, "Monthly payment should match the expected value.");
    }

    @Test
    public void testLoanCalcInvalidValues() {
        SmartFin sf = new SmartFin();

        // Test: Loan with negative principal
        assertThrows(IllegalArgumentException.class, () -> {
            sf.loanCalc(-10000, 5.0, 15);  // Invalid negative loan amount
        }, "Should throw IllegalArgumentException for negative principal.");

        // Test: Loan with zero principal
        assertThrows(IllegalArgumentException.class, () -> {
            sf.loanCalc(0, 5.0, 15);  // Invalid zero loan amount
        }, "Should throw IllegalArgumentException for zero principal.");
    }
}
