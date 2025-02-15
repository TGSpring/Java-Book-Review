import java.time.LocalDate;
import java.util.Objects;

public final class Expense {
    private final double amount;
    private final String description;
    private String category; // Changed to mutable for easier updates
    private final LocalDate date;

    // Constructor with data parameter
    public Expense(double amount, String description, String category, LocalDate date) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
        if (category == null || category.trim().isEmpty()) {
            this.category = "Uncategorized"; // Default to "Uncategorized" if the category is empty
        } else {
            this.category = category;
        }
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    // Overloaded constructor that automatically uses the current date
    public Expense(double amount, String description, String category) {
        this(amount, description, (category == null || category.isEmpty()) ? "Uncategorized" : category,
                LocalDate.now());
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category; // Added setter for category
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Expense expense = (Expense) o;
        return Double.compare(expense.amount, amount) == 0 &&
                description.equals(expense.description) &&
                category.equals(expense.category) &&
                date.equals(expense.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, description, category, date);
    }
}
