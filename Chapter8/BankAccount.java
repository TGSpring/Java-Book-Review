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
 * Question 12: Line 63
 * Question 13: Line 108
 * 
 * I know that this is not efficient, but I did not think messing with the JSON configs for this problem set was a good use of time.
 */

public class BankAccount {

    String name = "Test";
    double balance = 100.0;

    // new field, initialized with 0.0.
    double transactionFee = 0.0;

    // new field for transfer method, to use withdraw method logic.
    double transferFee = 5.0;

    public static void main(String[] args) {
        // Redid print statements due to question 12 asking for toString method, much
        // cleaner.

        BankAccount account = new BankAccount();
        System.out.println("Before deposit: " + account.toString());
        account.deposit(50);
        System.out.println("After deposit: " + account.toString());




        //Used to implement and test question 13
        BankAccount ben = new BankAccount();
        ben.deposit(80.00);
        BankAccount hal = new BankAccount();

        hal.deposit(20.00);

        ben.transfer(hal, 20.00); // Ben $55, hal $40 (ben - $25, hal + $20)

        System.out.println(ben.toString());
        System.out.println(hal.toString());

        ben.transfer(hal, 10.00); // Ben $40, hal $50 (ben - $15, hal + $10)

        System.out.println(ben.toString());
        System.out.println(hal.toString());

        hal.transfer(ben, 60.00); // Ben $85, hal $0 (ben + $45, hal - $50)

        System.out.println(ben.toString());
        System.out.println(hal.toString());
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

    /*
     * Question 12
     * Add a toString method to the BankAccount class from the previous exercise.
     * Your method should return a string that contains
     * the account's name and balance separated by a comma and space. For example,
     * if an account object named yana has the name
     * "Yana" and a balance of 3.03, the call yana.toString() should return the
     * string "Yana, $3.03".
     */

    public String toString() {
        return String.format("%s, $%.2f", name, balance);
    }

    /*
     * Question 13
     * Add a transfer method ot the BankAccount class from the previous exercise.
     * Your method should move money from the current bank account to another
     * account.
     * The method accepts two parameters: a second BankAccount to accept the money,
     * and a real number for the amount of money to transfer. There is a $5.00 fee
     * for transferring
     * money, so this much must be deducted from the current account's balance
     * before any transfer. The method should modify the two BankAccount objects
     * such that "this" current object has its
     * balance decreased by the given amount plus the $5 fee, and the other
     * account's balance is increased by the given amount. If this account object
     * does not have enough money to make the full transfer,
     * transfer whatever money is left after the $5 fee is deducted. If this account
     * has under $5 or the amount is 0 or less, no transfer should occur and neither
     * account's state should be modified.
     * The following are some example calls to the method.
     * 
     * BankAccount ben = new BankAccount();
     * ben.deposit(80.00);
     * BankAccount hal = new BankAccount();
     * hal.deposit(20.00);
     * ben.transfer(hal, 20.00); //Ben $55, hal $40 (ben - $25, hal + $20)
     * ben.transfer(hal, 10.00); //Ben $40, hal $50 (ben - $15, hal + $10)
     * hal.transfer(ben, 60.00); //Ben $85, hal $0 (ben + $45, hal - $50)
     */

    public void transfer(BankAccount second, double money) {
        // Firstly I did try to implement DRY and use withdraw as a boolean flag in the
        // conditions, but the fees made it too complex for the scope of this.

        // First checks if transfer can happen at all.
        if (balance < 5 || money <= 0) {
            System.out.println("Failed.");

            // Checks if full transfer can occur.
        } else if (balance >= (money + 5)) {
            // Deducts 5 for transfer fee. This is not the transactionFee, this is why I
            // chose to not use withdraw/implement DRY.
            balance = balance - (money + 5);
            second.deposit(money);
            // If the balance is not 0, but can not make the full transfer.
        } else {
            // creates new amount with balance and fee deducted.
            double transAmount = balance - 5;
            // resets balance to 0 since it is sending what it can.
            balance = 0;
            // TransAmount is what remains, is sent to second account.
            second.deposit(transAmount);
        }
    }
}
