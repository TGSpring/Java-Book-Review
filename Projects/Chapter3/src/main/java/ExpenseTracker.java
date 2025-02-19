import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseTracker {

    private List<Expense> expenses; // Ensure this is initialized

    // Constructor initializes the expenses list
    public ExpenseTracker() {
        this.expenses = new ArrayList<>(); // Initialize the list here
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
        if(n <= 0){
            return new ArrayList<>();
        }
        return getExpensesSortedByAmountDesc().subList(0, n);
    }

    // Get total spent by category
    public double getTotalSpentByCategory(String category) {
        return expenses.stream()
                .filter(expense -> expense.getCategory().equalsIgnoreCase(category))
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public List<Map.Entry<String, Double>> getTopNCategories(int n) {
        if(n <= 0) {
            return new ArrayList<>();
        }

        // Create a map to store total amounts by category
        Map<String, Double> categoryTotals = new HashMap<>();

        for (Expense expense : expenses) {
            String category = expense.getCategory();
            double amount = expense.getAmount();

            categoryTotals.put(category, categoryTotals.getOrDefault(category, 0.0) + amount);
        }

        // Converting the map into a list of entries
        List<Map.Entry<String, Double>> categoryList = new ArrayList<>(categoryTotals.entrySet());

        categoryList.sort((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()));

        return categoryList.subList(0, Math.min(n, categoryList.size()));

    }
}
