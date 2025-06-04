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
 * Question 10: Line 67
 * 
 * I know that this is not efficient, but I did not think messing with the JSON configs for this problem set was a good use of time.
 */

//Stock class represents purchases of shares of a stock.
package Chapter8;

public class Stock {

    private String symbol; // Stock symbol.
    private int totalShares; // total shares purchased.
    private double totalCost; // total cost for all shares

    // initializes a new Stock with no shares purchased
    // pre: symbol != null.

    public Stock(String theSymbol) {
        if (theSymbol == null) {
            throw new NullPointerException();
        }

        symbol = theSymbol;
        totalShares = 0;
        totalCost = 0.0;
    }

    // Returns the total profit or loss earned on this stock,
    // based on the given price per share.
    // pre: currentPrice >= 0.0.

    public double getProfit(double currentPrice) {
        if (currentPrice < 0.0) {
            throw new IllegalArgumentException();
        }

        double marketValue = totalShares * currentPrice;
        return marketValue - totalCost;
    }

    // Records purchase of the given shares at the given price
    // pre: shares >= 0 && pricePerShare >= 0.0

    public void purchase(int shares, double pricePerShare) {
        if (shares < 0 || pricePerShare < 0.0) {
            throw new IllegalArgumentException();
        }

        totalShares += shares;
        totalCost += shares * pricePerShare;
    }

    /*
     * Question 10
     * Add the following mutator method to the Stock class.
     * public void clear()
     * Reset this Stock's number of shares purchased and total cost to 0.
     */
    public void clear() {
        totalShares = 0;
        totalCost = 0;

    }
}
