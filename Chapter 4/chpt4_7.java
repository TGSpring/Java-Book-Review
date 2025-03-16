/**
 * Tyler Spring
 * 3/16/2025
 * Chapter 4 question 7
 * FizzBuzz (Slight Variation)
 * 
 * Write a method fizzBuzzVariant(int n) that prints:
 * "Fizz" if n is divisible by 3
 * "Buzz" if n is divisible by 5
 * "FizzBuzz" if divisible by both
 * "None" otherwise
 */
public class chpt4_7 {
    public static void main(String[] args) {
        System.out.println(fizzBuzzVariant(5));
        System.out.println(fizzBuzzVariant(3));
        System.out.println(fizzBuzzVariant(12));
        System.out.println(fizzBuzzVariant(15));
        System.out.println(fizzBuzzVariant(7));
    }

    public static String fizzBuzzVariant(int n) {
        if (n <= 0)
            return "null";
        if (n % 3 == 0 && n % 5 == 0)
            return "FizzBuzz";
        if (n % 3 == 0)
            return "Fizz";
        if (n % 5 == 0)
            return "Buzz";

        return "None";
    }

}
