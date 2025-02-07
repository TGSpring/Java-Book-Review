import java.time.LocalDate;

public class Expense {
    private double amount;
    private String description;
    private String category;
    private LocalDate date;

    //Constructor with data parameter 
    public Expense(double amount, String description, String category, LocalDate date) {
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.date = date;
    }

    //Overloaded constructor that automatically uses the current date
    public Expense(double amount, String description, String category) {
        this(amount, description, category, LocalDate.now());
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

    public LocalDate getDate() {
        return date;
    }

    public void setCategory(String category) {
        this.category = category;
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
}