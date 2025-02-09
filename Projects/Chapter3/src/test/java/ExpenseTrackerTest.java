import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ExpenseTrackerTest {

    private ExpenseTracker expenseTracker;
    private Map<String, String> keywordMappings;

    @BeforeEach
    public void setUp() {
        expenseTracker = new ExpenseTracker();
        keywordMappings = new HashMap<>();
        keywordMappings.put("mcdonald's", "Food");
        keywordMappings.put("netflix", "Entertainment");
        keywordMappings.put("uber", "Transport");
    }

    @Test
    public void testAddExpense() {
        Expense expense = new Expense(15.0, "Netflix subscription", "", LocalDate.now());
        expenseTracker.addExpense(expense);
        assertEquals(1, expenseTracker.getExpensesSortedByAmountDesc().size());
    }

    @Test
    public void testAutoCategorizeExpenses() throws Exception {
        Expense expense1 = new Expense(15.0, "Netflix subscription", "", LocalDate.now());
        Expense expense2 = new Expense(50.0, "Lunch at McDonald's", "", LocalDate.now());
        expenseTracker.addExpense(expense1);
        expenseTracker.addExpense(expense2);

        expenseTracker.autoCategorizeExpenses(keywordMappings);

        assertEquals("Entertainment", expense1.getCategory());
        assertEquals("Food", expense2.getCategory());
    }

    @Test
    public void testGetExpensesSortedByAmountDesc() {
        Expense expense1 = new Expense(15.0, "Netflix subscription", "", LocalDate.now());
        Expense expense2 = new Expense(1200.0, "Monthly Rent", "Rent", LocalDate.now());
        Expense expense3 = new Expense(50.0, "Lunch at McDonald's", "", LocalDate.now());
        expenseTracker.addExpense(expense1);
        expenseTracker.addExpense(expense2);
        expenseTracker.addExpense(expense3);

        assertEquals(1200.0, expenseTracker.getExpensesSortedByAmountDesc().get(0).getAmount());
        assertEquals(50.0, expenseTracker.getExpensesSortedByAmountDesc().get(1).getAmount());
        assertEquals(15.0, expenseTracker.getExpensesSortedByAmountDesc().get(2).getAmount());
    }

    @Test
    public void testGetTopNExpenses() {
        Expense expense1 = new Expense(15.0, "Netflix subscription", "", LocalDate.now());
        Expense expense2 = new Expense(1200.0, "Monthly Rent", "Rent", LocalDate.now());
        Expense expense3 = new Expense(50.0, "Lunch at McDonald's", "", LocalDate.now());
        expenseTracker.addExpense(expense1);
        expenseTracker.addExpense(expense2);
        expenseTracker.addExpense(expense3);

        assertEquals(2, expenseTracker.getTopNExpenses(2).size());
        assertEquals(1200.0, expenseTracker.getTopNExpenses(2).get(0).getAmount());
        assertEquals(50.0, expenseTracker.getTopNExpenses(2).get(1).getAmount());
    }

    @Test
    public void testGetTotalSpentByCategory() {
        Expense expense1 = new Expense(15.0, "Netflix subscription", "Entertainment", LocalDate.now());
        Expense expense2 = new Expense(50.0, "Lunch at McDonald's", "Food", LocalDate.now());
        Expense expense3 = new Expense(30.0, "Dinner at McDonald's", "Food", LocalDate.now());
        expenseTracker.addExpense(expense1);
        expenseTracker.addExpense(expense2);
        expenseTracker.addExpense(expense3);

        assertEquals(80.0, expenseTracker.getTotalSpentByCategory("Food"));
        assertEquals(15.0, expenseTracker.getTotalSpentByCategory("Entertainment"));
    }
}