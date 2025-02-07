import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseTracker {

    private ArrayList<Expense> expenses;

    public ExpenseTracker() {
        expenses =  new ArrayList<>();
    }
    // Add an expense to the list
    public void addExpense(Expense e) {
        expenses.add(e);
    }

    /**
     * Ai Part: Rule-Based Categorization
     * 
     * We pass in a Map where keys are keywords and values are the category.
     * The expense description is converted to lowercase to ensure case-insensitive
     * matching.
     * If the description contains a keyword, we set the expense's category accordingly.
     */

     public void categorizeExpense(Expense expense, Map<String, String> keywordMappings) {
        String description = expense.getDescription().toLowerCase();
        for (Map.Entry<String, String> entry : keywordMappings.entrySet()) {
            if(description.contains(entry.getKey().toLowerCase())) {
                expense.setCategory(entry.getValue());
                break;
            }
        }
     }

     /**
      * Auto-Categorize all expenses that do not yet have a category.
       This method iterates through all stored expenses.
       For each expense with an empty or unset category, it calls categorizeExpense.
       This automates the process of assigning categories based on description keywords.
      */

      public void autoCategorizeExpenses(Map<String, String> keywordMappings) {
        for (Expense expense : expenses) {
            if (expense.getCategory() == null || expense.getCategory().isEmpty()) {
                categorizeExpense(expense, keywordMappings);
            }
        }
      }

      //Sort expenses from highest to lowest amount

      public List<Expense> getExpensesSortedByAmountDesc() {
        List<Expense> sortedList = new ArrayList<>(expenses);
        Collections.sort(sortedList, Comparator.comparingDouble(Expense::getAmount).reversed());
        return sortedList;
      }

      //Get the top N most expensive expenses
      public List<Expense> getTopNExpenses(int n) {
        List<Expense> sortedList = getExpensesSortedByAmountDesc();
        return sortedList.subList(0, Math.min(n, sortedList.size()));
      }

      //Get total spent by a given category.
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
