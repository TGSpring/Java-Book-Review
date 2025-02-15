import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExpenseTracker {

    private List<Expense> expenses;  // Ensure this is initialized

    // Constructor initializes the expenses list
    public ExpenseTracker() {
        this.expenses = new ArrayList<>();  // Initialize the list here
    }

    // Adds a new expense to the tracker
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    // Auto categorize expenses based on keyword mappings
    public void autoCategorizeExpenses(Map<String, String> keywordMappings) {
        for (Expense expense : expenses) {
            boolean categorized = false;
            for (Map.Entry<String, String> entry : keywordMappings.entrySet()) {
                if (expense.getDescription().toLowerCase().contains(entry.getKey().toLowerCase())) {
                    expense.setCategory(entry.getValue());
                    categorized = true;
                    break;
                }
            }
            if (!categorized) {
                expense.setCategory("Uncategorized");
            }
        }
    }

    // Get expenses sorted by amount in descending order
    public List<Expense> getExpensesSortedByAmountDesc() {
        expenses.sort((e1, e2) -> Double.compare(e2.getAmount(), e1.getAmount()));
        return expenses;
    }

    // Get the top N expenses by amount
    public List<Expense> getTopNExpenses(int n) {
        return getExpensesSortedByAmountDesc().subList(0, n);
    }

    // Get total spent by category
    public double getTotalSpentByCategory(String category) {
        return expenses.stream()
                       .filter(expense -> expense.getCategory().equalsIgnoreCase(category))
                       .mapToDouble(Expense::getAmount)
                       .sum();
    }
}
