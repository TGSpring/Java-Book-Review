/*
 * Tyler Spring
 * 5/25/2025
 * Chapter 8 BankAccount class
 * Majority of the end of chapter programming questions for this section involve 
 * adding methods to the Stock and StockMain classes. Each problem/addition to the code will have a legend here in the header comments,
 * this will take you to where the addition/question is in the code base with the question as a comment.
 * 
 * To run file, just hit run, I got tired of copy pasting commands in stock and point class.
 * 
 * Question 11: Line 47
 * 
 * I know that this is not efficient, but I did not think messing with the JSON configs for this problem set was a good use of time.
 */

public class BankAccount {

    String name = "Test";
    double balance = 100.0;

    // new field, initialized with 0.0.
    double transactionFee = 0.0;

    public static void main(String[] args) {
        //None of this in the main was required, I am doing this for debugging/testing.
        BankAccount account = new BankAccount();
        System.out.println("Testing with: " + account.name + " " + account.balance);
        account.deposit(100);
        System.out.println("Depositing $100, new balance: " + account.balance);
        account.withdraw(100);
        System.out.println("Withdrawing $100, new balance: " + account.balance);
        System.out.println("Testing validation of withdrawal method with $150 withdrawal on $100 balance " + account.balance);
        account.withdraw(150);

    }

    public void deposit(double amount) {
        balance = balance + amount;
    }

    public void withdraw(double amount) {
        // Pre calculating total.
        double totalDeduction = amount + transactionFee;

        // If the account has enough funds after the withdrawal, the transaction
        // completes.
        if (totalDeduction <= balance) {
            balance = balance - totalDeduction;
        } else {
            // Triggers if balance is going to be negative.
            System.out.println("Insufficient funds.");
        }
    }

    /*
     * Question 11
     * Add a field to BankAccount class named transactionFee for a real number
     * representing an amount of money to deduct
     * every time the user withdraws money. The default value is $0.00, but the
     * client can change the value. Deduct
     * the transaction fee money during every withdraw call (but not from deposits).
     * Make sure that the balance cannot go negative during a withdrawal.
     * If teh withdrawal (amount plus transaction fee) would cause it to become
     * negative, don't modify the balance at all.
     */
}
