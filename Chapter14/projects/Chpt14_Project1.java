/*
 * Tyler Spring
 * 10/1/2025
 * Chapter 14 Project 1
 * Write a primes program that finds prime numbers using the Sieve of Eratosthenes, an algorithm by a Greek mathematician of the same name who lived in the third century BC. 
 * The algorithm finds all prime numbers up to some maximum value n, as described by the following:
 * 
 * create a queue of number to process.
 * fill the queue with the integers 2 through n inclusive.
 * create an empty result queue to store primes.
 * 
 * repeat the following steps:
 *  obtain the next prime p by removing the first value.
 *      from the queue of numbers.
 *  put p into the result queue of primes.
 * loop through the queue of numbers.
 *      eliminating all numbers that are divisible by p.
 * while (p is less than the square root of n).
 * 
 * all remaining values in the numbers queue are prime,
 *  so transfer them to the result primes queue.
 */

import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class Chpt14_Project1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter n: ");
        int n = input.nextInt();

        Queue<Integer> numbersQueue = new LinkedList<>();

        // Prime results Queue.
        Queue<Integer> results = new LinkedList<>();

        // populating queue from user prompt.
        int i = 2;
        while (i <= n) {
            numbersQueue.add(i);
            i++;
        }
        System.out.println("Before prime filtering \n" + numbersQueue);

        while (!numbersQueue.isEmpty() && numbersQueue.peek() <= Math.sqrt(n)) {
            // Removing next prime.
            int p = numbersQueue.remove();
            results.add(p);

            // Filter multiples of p.
            int size = numbersQueue.size();
            for (int j = 0; j < size; j++) {
                int num = numbersQueue.remove();
                if (num % p != 0) {
                    numbersQueue.add(num);
                }
            }
        }

        // Transferring remaining numbers to results.
        while (!numbersQueue.isEmpty()) {
            results.add(numbersQueue.remove());
        }

        System.out.println("After prime filtering.");
        while (!results.isEmpty()) {
            System.out.println(results.remove() + " ");
        }
    }
}
/*
 * COMPLEXITIES
 * Space: O(n), linear. You store up to n - 1 numbers, from 2 to n in the
 * queues. At any time, the combined size of numbersQueue and results
 * is at most n - 1.
 * 
 * Time: O(n log log n), Sieve of Eratosthenes. For each prime p you filter out
 * multiples of p from the queue.
 * The total work done is proportional to the number of numbers and the number
 * of times you filter.
 */

/*
 * PRE IMPLEMENTATION NOTES
 * 
 * Step 1: fill the queue with numbers 2 to n
 * goal: add every integer from 2 to n inclusive to your first queue.
 * how: use a loop for or while and a conditional, if needed but just add every
 * number here.
 * 
 * pseudo code:
 * for i from 2 to n:
 * queue.add(i)
 * 
 * no conditional needed since all numbers from 2 to n are included.
 * 
 * Step 2: Process each prime
 * goal: remove the front value the next prime and filter out its multiples.
 * how: use remove() to get the prime (p).
 * loop: continue while p is less than or equal to sqrt(n).
 * 
 * pseudo code:
 * while queue is not empty and queue.peek() <= sqrt(n):
 * p = queue.remove()
 * resultsQueue.add(p)
 * //Step 3: filter out multiples of p.
 * 
 * peek() lets you look at the front value without removing it, useful for your
 * loop condition.
 * 
 * Step 3: Filter multiples of p.
 * goal: Remove each number from the queue, and only add it back if it's not a
 * multiple of p.
 * how: use a for loop that runs for current size of the queue since the queue
 * shrinks as you remove.
 * 
 * pseudo code:
 * for i from to queue.size():
 * num = queue.remove()
 * if num % p !=0:
 * queue.add(num)
 * //else, discard
 * 
 * you don't need peek() inside the loop, just remove() and add().
 * 
 * Step 4: Add remaining numbers to Results.
 * goal: After the main loop, all remaining numbers in the queue are primes.
 * How: remove each and add to the results queue.
 * 
 * pseudo code:
 * while queue is not empty:
 * resultsQueue.add(queue.remove())
 * 
 * POINTS:
 * use only allowed queue methods: add, remove, peek, isEmpty, size.
 * avoid index-based access or recursion.
 * use a for loop with size to process all elements in the queue.
 * use peek() for checking the next value, not for processing all elements.
 * 
 */