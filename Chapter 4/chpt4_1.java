
/**
 * Tyler Spring
 * 3/14/2025
 * Chapter 4 question 1
 * 
 * Write a method isLeapYear(int year) that returns true if the year is a leap
 * year and false otherwise.
 * A leap year is divisible by 4, but if divisible by 100, it must also be
 * divisible by 400.
 * 
 */
public class chpt4_1 {
    public static void main(String[] args) {
        System.out.println(isLeapYear(2020));
        System.out.println(isLeapYear(2024));
        System.out.println(isLeapYear(2025));
        System.out.println(isLeapYear(1998));
    }

    // Returns true if the year is divisible by 4 and either not divisible by 100 or
    // divisible by 400.
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
    }
}
