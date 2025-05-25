/*
 * Tyler Spring
 * 5/25/2025
 * Chapter 8 Stock class
 * Majority of the end of chapter programming questions for this section involve 
 * adding methods to the Stock and StockMain classes. Each problem/addition to the code will have a legend here in the header comments,
 * this will take you to where the addition/question is in the code base with the question as a comment.
 * 
 * To run use these commands:
 *  cd C:\Users\tyler\OneDrive\Desktop\JavaBookReview
 *  javac Chapter8\*.java
 *  java Chapter8.StockMain
 * 
 * I know that this is not efficient, but I did not think messing with the JSON configs for this problem set was a good use of time.
 */
package Chapter8;

import java.util.*;

public class StockMain {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        // First Stock
        System.out.print("First stock's symbol: ");
        String symbol1 = console.next();
        Stock stock1 = new Stock(symbol1);
        double profit1 = makePurchase(stock1, console);

        // Second Stock
        System.out.print("Second stock's symbol: ");
        String symbol2 = console.next();
        Stock stock2 = new Stock(symbol2);
        double profit2 = makePurchase(stock2, console);

        // Report which stock made more money
        if (profit1 > profit2) {
            System.out.println(symbol1 + " was more profitable than " + symbol2 + ".");
        } else if (profit2 > profit1) {
            System.out.println(symbol2 + " was more profitable than " + symbol1 + ".");
        } else { // profit 1 == profit 2
            System.out.println(symbol1 + " and " + symbol2 + " are equally profitable.");
        }

    }

    // Make purchases of stock and return the profit.
    public static double makePurchase(Stock currentStock, Scanner console) {
        System.out.print("How many purchases did you make?");
        int numPurchases = console.nextInt();

        // Ask about each purchase
        for (int i = 1; i <= numPurchases; i++) {
            System.out.print(i + ": How many shares, at what price per share? ");
            int numShares = console.nextInt();
            double pricePerShare = console.nextDouble();

            // Ask the stock object to record this purchase
            currentStock.purchase(numShares, pricePerShare);
        }

        // Use the stock object to compute profit.
        System.out.print("What is today's per share?");
        double currentPrice = console.nextDouble();

        double profit = currentStock.getProfit(currentPrice);
        System.out.println("Net profit/loss: $" + profit);
        return profit;
    }
}