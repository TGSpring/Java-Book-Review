import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SavingsGoalEstTest {

    private SavingsGoalEst savingsGoalEst;

    @BeforeEach
    public void setUp() {
        savingsGoalEst = new SavingsGoalEst(3245, 23, 3, 4);
    }

    @Test
    public void testGetRemainingAmount() {
        assertEquals(3222, savingsGoalEst.getRemainingAmount());
    }

    @Test
    public void testCalculateFutureSavings() {
        assertEquals(101, savingsGoalEst.calculateFutureSavings(6));
        assertEquals(323, savingsGoalEst.calculateFutureSavings(12));
        assertEquals(7283, savingsGoalEst.calculateFutureSavings(60));
    }
}
