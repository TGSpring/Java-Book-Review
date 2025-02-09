import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ExpenseTracker {

    private ArrayList<Expense> expenses;
    private predictCat cat;

    public ExpenseTracker() {
        expenses = new ArrayList<>();
        try {
            this.cat = new predictCat();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize predictCat", e);
        }

    }

    // Add an expense to the list
    public void addExpense(Expense e) {
        expenses.add(e);
    }

    public void autoCategorizeExpenses(Map<String, String> keywordMappings) throws Exception {
        for (Expense expense : expenses) {
            if (expense.getCategory() == null || expense.getCategory().isEmpty()) {

                String description = expense.getDescription().toLowerCase();
                boolean mapped = false;
                for (Map.Entry<String, String> entry : keywordMappings.entrySet()) {
                    if (description.contains(entry.getKey().toLowerCase())) {
                        expense.setCategory(entry.getValue());
                        mapped = true;
                        break;
                    }
                }
                if (!mapped) {
                    String category = cat.predictCat(expense.getDescription());
                    expense.setCategory(category);
                }
            }
        }
    }

    // Sort expenses from highest to lowest amount

    public List<Expense> getExpensesSortedByAmountDesc() {
        List<Expense> sortedList = new ArrayList<>(expenses);
        Collections.sort(sortedList, Comparator.comparingDouble(Expense::getAmount).reversed());
        return sortedList;
    }

    // Get the top N most expensive expenses
    public List<Expense> getTopNExpenses(int n) {
        List<Expense> sortedList = getExpensesSortedByAmountDesc();
        return sortedList.subList(0, Math.min(n, sortedList.size()));
    }

    // Get total spent by a given category.
    public double getTotalSpentByCategory(String category) {
        double total = 0;
        for (Expense expense : expenses) {
            if (expense.getCategory() != null && expense.getCategory().equalsIgnoreCase(category)) {
                total += expense.getAmount();
            }
        }
        return total;
    }

}
