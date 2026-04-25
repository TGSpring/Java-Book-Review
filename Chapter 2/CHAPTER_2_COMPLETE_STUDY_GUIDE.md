# Chapter 2: Loops and Recursion - Complete Study Guide

**By: Tyler Spring | Date: 1/3/2025 - 1/10/2025**

---

## 📖 Table of Contents

1. [Short Answer Review](#short-answer-review)
2. [Core Concepts Explained](#core-concepts-explained)
3. [Exercise-by-Exercise Breakdown](#exercise-by-exercise-breakdown)
4. [Common Loop Patterns](#common-loop-patterns)
5. [Recursion Deep Dive](#recursion-deep-dive)
6. [Master Checklist](#master-checklist)
7. [Quick Reference](#quick-reference)

---

## Short Answer Review

### **Q1: What is the difference between a while loop and a for loop? When might you use each one?**

#### **While Loop**
- Continues executing **as long as** a condition is true
- Used when **number of iterations is unknown**
- Better for **event-driven** logic (waiting for input, searching)
- Condition checked **BEFORE** each iteration

```java
// Unknown how many times we need to loop
int count = 0;
while (count < targetValue) {
    // Get input
    // Check condition
    // Update
    count++;
}
```

#### **For Loop**
- Used when **number of iterations is known**
- Better for **iterating** through collections/ranges
- Compact syntax combines: initialization, condition, increment
- Condition checked **BEFORE** each iteration

```java
// Know we need to loop 100 times
for (int i = 1; i <= 100; i++) {
    // Process element
}
```

#### **Comparison Table**

| Aspect | While Loop | For Loop |
|--------|-----------|----------|
| **Iterations Known?** | No | Yes |
| **Use Case** | Searching, user input, events | Iterating arrays, counting |
| **Syntax** | Simple condition | Initialization; condition; increment |
| **Loop Variable** | Optional | Usually has one |
| **Example** | Sentinel value loops | `for (int i = 0; i < 10; i++)` |

#### **Real Examples from Your Code**

```java
// chpt2_1.java - FOR LOOP (know we need 100 iterations)
for (int i = 1; i <= 100; i++) {
    sum += i;  // Sum 1 to 100
}

// chpt2_8.java - WHILE LOOP (unknown how many inputs)
while (input != -1) {
    input = sc.nextInt();
    if (target == input) count++;
}

// chpt2_10.java - DO-WHILE LOOP (need at least 1 run)
do {
    System.out.println("Enter a number: ");
    input = sc.nextInt();
    if (input != -1) numSum(input);
} while (input != -1);
```

---

### **Q2: What are the differences between the break and continue statements in loops? When might you use each?**

#### **Break Statement**
- **Terminates the entire loop immediately**
- Program continues after the loop
- Used when you're **done** and need to exit completely

```java
for (int i = 1; i <= 10; i++) {
    if (i == 5) {
        break;  // Exit loop entirely
    }
    System.out.println(i);
}
// Output: 1, 2, 3, 4
// (stops at 5, never prints 5-10)
```

#### **Continue Statement**
- **Skips the rest of current iteration** and moves to next
- Loop condition is still checked
- Used when you want to **skip certain iterations** but keep looping

```java
for (int i = 1; i <= 10; i++) {
    if (i % 2 == 0) {
        continue;  // Skip this iteration
    }
    System.out.println(i);
}
// Output: 1, 3, 5, 7, 9
// (skips even numbers but keeps looping)
```

#### **Combined Example from Your Notes**

```java
for (int i = 1; i <= 10; i++) {
    if (i == 5) {
        break;  // Exit loop when i equals 5
    }
    if (i % 2 == 0) {
        continue;  // Skip even numbers
    }
    System.out.println(i);
}
// Output: 1, 3
// Prints 1 (odd), prints 3 (odd), then break at 5
```

#### **When to Use**

| Statement | Use When | Example |
|-----------|----------|---------|
| **break** | Found what you're looking for | Searching: `if (found) break;` |
| **break** | Condition impossible to meet | Error case: `if (invalid) break;` |
| **continue** | Want to skip this item | Filter: `if (negative) continue;` |
| **continue** | Need special handling for some items | Skip even numbers: `if (i%2==0) continue;` |

---

### **Q3: What is the difference between while and do-while loops? When would you use one over the other?**

#### **While Loop**
- Checks condition **BEFORE** executing body
- Body might **never execute** if condition is false initially
- Use when loop might not need to run

```java
int attempts = 0;
while (attempts < 0) {  // False from start!
    System.out.println("This never prints");
    attempts++;
}
```

#### **Do-While Loop**
- Checks condition **AFTER** executing body
- Body **always runs at least once**, even if condition is false
- Use when something must happen before checking condition

```java
int attempts = 0;
do {
    System.out.println("This prints once");
    attempts++;
} while (attempts < 0);  // False, but body already ran!
```

#### **Visual Comparison**

```
While:              Do-While:
┌─────────────┐    ┌─────────────┐
│ Check cond? │    │ Run body    │
└──────┬──────┘    └──────┬──────┘
       │                  │
    NO │              ┌────────┐
       ↓              │ Check  │
      Exit            │  cond? │
    YES │             └────┬───┘
       ↓            NO │   │ YES
    ┌─────────┐       ↓   ↓
    │Run body │       Exit Loop
    └────┬────┘
         │
         ↓
    Back to check
```

#### **Real Examples from Your Code**

```java
// chpt2_10.java - DO-WHILE (must prompt at least once)
do {
    System.out.println("Enter a number: ");
    input = sc.nextInt();
    if (input != -1) {
        numSum(input);
    }
} while (input != -1);
// User MUST be prompted at least once
```

#### **When to Use**

| Loop | Use When | Example |
|------|----------|---------|
| **while** | Might not need to run | Searching empty list |
| **while** | Need to validate first | Reading file that might not exist |
| **do-while** | Must run at least once | User menu (prompt first, then decide) |
| **do-while** | Need input before validating | Input validation loop |

---

### **Q4: Explain what an infinite loop is and how it can occur. How can you avoid infinite loops?**

#### **What is an Infinite Loop?**
A loop that **never terminates** because the exit condition is never satisfied.

#### **How They Occur**

**Problem 1: Missing or Incorrect Increment**
```java
// ❌ INFINITE - i never changes!
while (i < 10) {
    System.out.println(i);
    // i++ is missing!
}

// ✅ FIXED
while (i < 10) {
    System.out.println(i);
    i++;  // Update the variable
}
```

**Problem 2: Wrong Comparison Operator**
```java
// ❌ INFINITE - condition never false
for (int i = 1; i != 10; i += 2) {
    System.out.println(i);
}
// i: 1, 3, 5, 7, 9, 11, 13... (never equals 10!)

// ✅ FIXED
for (int i = 1; i <= 10; i += 2) {
    System.out.println(i);
}
```

**Problem 3: Condition Always True**
```java
// ❌ INFINITE
while (true) {
    System.out.println("Help me!");
}

// ✅ FIXED
while (condition) {  // Some condition that can be false
    System.out.println("I will eventually stop");
}
```

**Problem 4: Recursion Without Base Case**
```java
// ❌ INFINITE - no base case!
public static void countdown(int n) {
    System.out.println(n);
    countdown(n - 1);  // Never stops!
}

// ✅ FIXED
public static void countdown(int n) {
    if (n < 0) return;  // BASE CASE
    System.out.println(n);
    countdown(n - 1);
}
```

#### **How to Avoid Infinite Loops**

**Strategy 1: Ensure Update Happens**
```java
for (int i = 0; i < 100; i++) {  // ✅ i increments each iteration
    // ...
}

int x = 0;
while (x < 100) {
    // ...
    x++;  // ✅ Make sure this always happens
}
```

**Strategy 2: Use Correct Comparison Operators**
```java
// ✅ Use <= or >= (not != for ranges)
for (int i = 1; i <= 10; i++)
for (int i = 10; i >= 1; i--)

// ❌ AVOID != for ranges
for (int i = 1; i != 10; i += 3)  // Might skip the value
```

**Strategy 3: Add Debug Output**
```java
int i = 0;
while (i < 10) {
    System.out.println("DEBUG: i = " + i);  // Check variable
    // ...
    i++;
}
```

**Strategy 4: Recursion Base Cases**
```java
public static void recurse(int n) {
    if (n <= 0) return;  // BASE CASE FIRST!
    System.out.println(n);
    recurse(n - 1);
}
```

---

### **Q5: What is the purpose of a loop control variable? How is it typically used in a for loop?**

#### **Loop Control Variable Definition**
A variable that controls how many times a loop executes. It:
- **Starts** at an initial value
- **Updates** (increments/decrements) each iteration
- **Terminates** the loop when condition is no longer true

#### **Three Parts of a For Loop**

```java
for (int i = 1;      // 1. INITIALIZATION
     i <= 100;       // 2. CONDITION (checks i)
     i++) {          // 3. UPDATE (changes i)
    sum += i;
}
```

| Part | Role | Example |
|------|------|---------|
| **Initialization** | Set starting value | `int i = 1` |
| **Condition** | Check if loop continues | `i <= 100` |
| **Update** | Change the variable | `i++` |

#### **How It Works Step-by-Step**

```java
for (int i = 1; i <= 3; i++) {
    System.out.println(i);
}

// Execution:
// 1. i = 1 (initialize)
// 2. i <= 3? YES (condition true)
// 3. Print 1 (body)
// 4. i++ → i = 2 (update)
// 5. i <= 3? YES (condition true)
// 6. Print 2 (body)
// 7. i++ → i = 3 (update)
// 8. i <= 3? YES (condition true)
// 9. Print 3 (body)
// 10. i++ → i = 4 (update)
// 11. i <= 3? NO (condition false)
// 12. Exit loop

// Output: 1, 2, 3
```

#### **Real Example from Your Code**

```java
// chpt2_1.java - Loop control variable i
for (int i = 1; i <= 100; i++) {
    sum += i;  // i is used here
}
// i controls: start (1), end (100), and increment (1 each time)
```

#### **Loop Control Variable Uses**

```java
// 1. Count iterations
for (int count = 0; count < 10; count++) {
    System.out.println("Iteration " + count);
}

// 2. Access array elements
int[] arr = {10, 20, 30};
for (int i = 0; i < arr.length; i++) {
    System.out.println(arr[i]);  // Use i to access
}

// 3. Perform calculations
int sum = 0;
for (int i = 1; i <= 100; i++) {
    sum += i;  // Use i in calculation
}

// 4. Countdown
for (int i = 10; i >= 1; i--) {
    System.out.println(i);  // Decrement instead
}
```

---

### **Q6: What is the difference between the increment (++) and the decrement (--) operators? How can they be used in loops?**

#### **Increment Operator (++)**
Adds 1 to a variable
```java
int x = 5;
x++;        // x is now 6
x += 1;     // Same as x++
```

#### **Decrement Operator (--)**
Subtracts 1 from a variable
```java
int x = 5;
x--;        // x is now 4
x -= 1;     // Same as x--
```

#### **Pre vs Post Increment** (Advanced)

```java
// POST-INCREMENT (i++ returns old value)
int x = 5;
int y = x++;    // y = 5, then x = 6
System.out.println("x: " + x + ", y: " + y);  // x: 6, y: 5

// PRE-INCREMENT (++i returns new value)
int x = 5;
int y = ++x;    // x = 6, then y = 6
System.out.println("x: " + x + ", y: " + y);  // x: 6, y: 6

// In loops, usually no difference
for (int i = 0; i < 10; i++)   // Same as
for (int i = 0; i < 10; ++i)   // This
```

#### **Using in Loops**

```java
// Count UP
for (int i = 1; i <= 10; i++) {
    System.out.println(i);  // 1, 2, 3, ..., 10
}

// Count DOWN
for (int i = 10; i >= 1; i--) {
    System.out.println(i);  // 10, 9, 8, ..., 1
}

// Skip by 2
for (int i = 0; i < 10; i += 2) {
    System.out.println(i);  // 0, 2, 4, 6, 8
}

// Skip by 3
for (int i = 0; i < 20; i += 3) {
    System.out.println(i);  // 0, 3, 6, 9, 12, 15, 18
}
```

#### **Real Examples from Your Code**

```java
// chpt2_1.java - COUNT UP
for (int i = 1; i <= 100; i++) {
    sum += i;
}

// chpt2_4.java - IMPLIED DECREMENT
// n /= 10 is like decrementing n by removing last digit
while (n != 0) {
    rev = rev * 10 + n % 10;
    n = n / 10;  // Reduce n
}

// chpt2_5.java - COUNT UP
for (int i = 0; i <= n; i++) {
    System.out.println("Fib number at " + n + " is " + fib(i));
}
```

---

## Core Concepts Explained

### **Loop Structure & Flow**

#### **For Loop Anatomy**
```
┌─────────────────────────────────────────────┐
│ for (int i = 0; i < 10; i++)                │
│     ▲         ▲       ▲    ▲                 │
│     │         │       │    │                 │
│  Initialize Check  Continue Update         │
│     │         │       │    │                 │
│  Once    Before   After   Each              │
│           body     body    iteration        │
└─────────────────────────────────────────────┘
```

#### **While Loop Anatomy**
```
┌──────────────────────┐
│ while (condition)    │
│    ▲                 │
│    └── Check before  │
│        each time     │
└──────────────────────┘

Every iteration:
1. Check condition
2. If TRUE: run body, repeat
3. If FALSE: exit
```

#### **Do-While Loop Anatomy**
```
┌─────────────────────┐
│ do {                │
│    // body          │
│ } while (condition) │
│        ▲            │
│        └── Check    │
│           AFTER     │
└─────────────────────┘

Every iteration:
1. Run body
2. Check condition
3. If TRUE: repeat
4. If FALSE: exit
```

### **When Loop Terminates**

```java
// Loop continues while condition is TRUE
for (int i = 1; i <= 5; i++) {
    // i=1: 1<=5? YES → run
    // i=2: 2<=5? YES → run
    // i=3: 3<=5? YES → run
    // i=4: 4<=5? YES → run
    // i=5: 5<=5? YES → run
    // i=6: 6<=5? NO → STOP
}

// Loop continues while condition is TRUE
while (x < 100) {
    // If x becomes >= 100, loop stops
    x += 10;
}
```

### **Nested Loops (Loops Inside Loops)**

```java
// chpt2_6.java - Drawing Rectangle
for (int i = 0; i < height; i++) {        // Outer loop (rows)
    for (int j = 0; j < width; j++) {     // Inner loop (columns)
        System.out.print("*");             // Prints for each column
    }
    System.out.println();                  // Newline after each row
}

// Example: width=3, height=2
// i=0: j=0,1,2 → prints "***"
// i=1: j=0,1,2 → prints "***"
//
// Output:
// ***
// ***
```

**Nested Loop Performance:**
```
Outer loop: 100 times
Inner loop: 100 times per outer
Total executions: 100 × 100 = 10,000
```

---

## Exercise-by-Exercise Breakdown

### **Exercise 1: chpt2_1.java - Sum 1 to 100**

**Problem:** Find the sum of all integers from 1 to 100

**Concepts:**
- For loops with accumulator pattern
- Loop control variable

**Code Analysis:**
```java
int sum = 0;
for (int i = 1; i <= 100; i++) {
    sum += i;  // Add current number to sum
}
System.out.println("The sum is: " + sum);
```

**Execution Trace:**
```
i=1:   sum = 0 + 1 = 1
i=2:   sum = 1 + 2 = 3
i=3:   sum = 3 + 3 = 6
i=4:   sum = 6 + 4 = 10
...
i=100: sum = 4950 + 100 = 5050

Output: The sum is: 5050
```

**Mathematical Check:**
```
Formula: Sum = n(n+1)/2 = 100(101)/2 = 5050 ✓
```

**Key Takeaway:** Use += operator to accumulate values in loops

---

### **Exercise 2: chpt2_2.java - Factorial with Recursion**

**Problem:** Calculate factorial using recursion

**Concepts:**
- Recursion
- Base case
- Recursive case

**Code Analysis:**
```java
public static int calcFact(int n) {
    if (n == 1 || n == 0) {
        return 1;  // BASE CASE
    } else {
        return n * calcFact(n - 1);  // RECURSIVE CASE
    }
}
```

**Execution Trace for calcFact(5):**
```
calcFact(5)
  = 5 * calcFact(4)
    = 5 * (4 * calcFact(3))
      = 5 * (4 * (3 * calcFact(2)))
        = 5 * (4 * (3 * (2 * calcFact(1))))
          = 5 * (4 * (3 * (2 * 1)))     ← Base case reached
        = 5 * (4 * (3 * 2))
      = 5 * (4 * 6)
    = 5 * 24
  = 120
```

**Call Stack Visualization:**
```
calcFact(5) → calcFact(4) → calcFact(3) → calcFact(2) → calcFact(1)
                                                          return 1
                                                       ← 2 * 1 = 2
                                                     ← 3 * 2 = 6
                                                   ← 4 * 6 = 24
                                                 ← 5 * 24 = 120
```

**Output:**
```
factorial of 5 is 120
```

**Key Concepts:**
- **Base Case:** When to stop (n == 0 or n == 1)
- **Recursive Case:** Call itself with n-1
- **Unwinding:** Results combine as recursion returns

**Key Takeaway:** Every recursive function needs a base case!

---

### **Exercise 3: chpt2_3.java - Iterative vs Mathematical Sum**

**Problem:** Compare two ways to sum 1 to n

**Concepts:**
- Iteration vs formula
- Time complexity (Big O)

**Code Analysis:**
```java
// METHOD 1: Iterative (O(n))
public static int iterSum(int n) {
    int sum = 0;
    for (int i = 1; i <= n; i++) {
        sum += i;
    }
    return sum;
}

// METHOD 2: Mathematical (O(1))
public static int mathSum(int n) {
    return n * (n + 1) / 2;  // Formula: n(n+1)/2
}
```

**Example with n=5:**
```
Iterative:
sum = 0
sum = 0 + 1 = 1
sum = 1 + 2 = 3
sum = 3 + 3 = 6
sum = 6 + 4 = 10
sum = 10 + 5 = 15

Mathematical:
5 * (5 + 1) / 2 = 5 * 6 / 2 = 30 / 2 = 15
```

**Performance Comparison:**

| n | Iterative | Mathematical |
|---|-----------|--------------|
| 10 | 10 additions | 1 calculation |
| 1,000 | 1,000 additions | 1 calculation |
| 1,000,000 | 1,000,000 additions | 1 calculation |

**Output:**
```
Iterative sum of 5 is 15
Mathematical sum of 5 is 15
```

**Time Complexity:**
- Iterative: **O(n)** - proportional to input
- Mathematical: **O(1)** - constant time

**Key Takeaway:** Think about efficiency! Sometimes a formula beats a loop!

---

### **Exercise 4: chpt2_4.java - Reverse a Number**

**Problem:** Reverse digits of a number (12345 → 54321)

**Concepts:**
- While loops with unknown iterations
- Modulo operator (%)
- Integer division

**Code Analysis:**
```java
public static int revNum(int n) {
    int rev = 0;
    while (n != 0) {
        rev = rev * 10 + n % 10;  // Add last digit to reversed
        n = n / 10;                 // Remove last digit
    }
    return rev;
}
```

**Step-by-Step for 12345:**
```
n = 12345, rev = 0

Iteration 1:
  n % 10 = 5 (last digit)
  rev = 0 * 10 + 5 = 5
  n = 12345 / 10 = 1234

Iteration 2:
  n % 10 = 4
  rev = 5 * 10 + 4 = 54
  n = 1234 / 10 = 123

Iteration 3:
  n % 10 = 3
  rev = 54 * 10 + 3 = 543
  n = 123 / 10 = 12

Iteration 4:
  n % 10 = 2
  rev = 543 * 10 + 2 = 5432
  n = 12 / 10 = 1

Iteration 5:
  n % 10 = 1
  rev = 5432 * 10 + 1 = 54321
  n = 1 / 10 = 0

n == 0? YES → exit loop
return 54321
```

**Output:**
```
Reversed number of 12345 is 54321
```

**Key Concepts:**
- `n % 10` gets the last digit
- `n / 10` removes the last digit
- `rev * 10` shifts digits left (5 → 50)

**Key Takeaway:** Modulo and division are powerful for digit manipulation!

---

### **Exercise 5: chpt2_5.java - Fibonacci Sequence**

**Problem:** Generate Fibonacci numbers up to n

**Concepts:**
- Recursion with multiple recursive calls
- Inefficiency of naive recursion

**Code Analysis:**
```java
public static int fib(int n) {
    if (n <= 1) {
        return n;  // BASE CASE: fib(0)=0, fib(1)=1
    } else {
        return fib(n - 1) + fib(n - 2);  // RECURSIVE CASE
    }
}

public static void main(String[] args) {
    int n = 10;
    for (int i = 0; i <= n; i++) {
        System.out.println("Fib number at " + n + " is " + fib(i));
    }
}
```

**Fibonacci Sequence:**
```
fib(0) = 0
fib(1) = 1
fib(2) = fib(1) + fib(0) = 1 + 0 = 1
fib(3) = fib(2) + fib(1) = 1 + 1 = 2
fib(4) = fib(3) + fib(2) = 2 + 1 = 3
fib(5) = fib(4) + fib(3) = 3 + 2 = 5
fib(6) = fib(5) + fib(4) = 5 + 3 = 8
...
```

**Recursion Tree for fib(4):**
```
                    fib(4)
                   /      \
              fib(3)        fib(2)
             /      \       /     \
         fib(2)    fib(1) fib(1) fib(0)
        /     \
    fib(1)  fib(0)
```

**Inefficiency Problem:**
- fib(5) calls fib(3) **twice**
- fib(10) has **177 function calls**!
- fib(20) has **21,891 function calls**!

**Output:**
```
Fib number at 10 is 0
Fib number at 10 is 1
Fib number at 10 is 1
Fib number at 10 is 2
Fib number at 10 is 3
Fib number at 10 is 5
Fib number at 10 is 8
Fib number at 10 is 13
Fib number at 10 is 21
Fib number at 10 is 34
Fib number at 10 is 55
```

**Key Takeaway:** Recursion is elegant but can be inefficient! Use loops or memoization for better performance.

---

### **Exercise 6: chpt2_6.java - Draw Rectangle**

**Problem:** Draw a rectangle using asterisks

**Concepts:**
- Nested loops
- Scanner input
- Print vs println

**Code Analysis:**
```java
public static void drawRec(int width, int height) {
    for (int i = 0; i < height; i++) {           // Rows
        for (int j = 0; j < width; j++) {        // Columns
            System.out.print("*");                 // Print without newline
        }
        System.out.print("\n");                    // Newline after row
    }
}

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    System.out.println("Enter width:");
    int width = sc.nextInt();
    
    System.out.println("Enter height:");
    int height = sc.nextInt();
    
    drawRec(width, height);
    sc.close();
}
```

**Example: width=5, height=3**

```
Outer loop (i=0):
  Inner loop: j=0,1,2,3,4 → prints "*****"
  println → newline

Outer loop (i=1):
  Inner loop: j=0,1,2,3,4 → prints "*****"
  println → newline

Outer loop (i=2):
  Inner loop: j=0,1,2,3,4 → prints "*****"
  println → newline

Output:
*****
*****
*****
```

**Key Concepts:**
- `System.out.print()` - doesn't add newline
- `System.out.println()` - adds newline
- Outer loop = rows
- Inner loop = columns

**Key Takeaway:** Nested loops are perfect for 2D patterns!

---

### **Exercise 7: chpt2_7.java - Random Integer Generator**

**Problem:** Generate n random integers between min and max

**Concepts:**
- Random class
- Range calculation for random numbers
- Scanner input/output

**Code Analysis:**
```java
public static void randInt(int n, int min, int max) {
    Random rand = new Random();
    for (int i = 0; i < n; i++) {
        // Generate random in range [min, max]
        int randomValue = rand.nextInt((max - min) + 1) + min;
        System.out.println(randomValue);
    }
}

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    System.out.println("Enter the number of random integers to generate:");
    int n = sc.nextInt();
    
    System.out.println("Enter a minimum value: ");
    int min = sc.nextInt();
    
    System.out.println("Enter a maximum value: ");
    int max = sc.nextInt();
    
    randInt(n, min, max);
    sc.close();
}
```

**Random Number Formula:**
```
rand.nextInt(range) returns 0 to range-1
range = (max - min) + 1

Example: Generate random from 1 to 10
  max = 10, min = 1
  range = (10 - 1) + 1 = 10
  rand.nextInt(10) returns 0-9
  Add min: 0-9 + 1 = 1-10 ✓
```

**Example: n=5, min=10, max=15**
```
Iteration 1: rand.nextInt(6) = 2, result = 2 + 10 = 12
Iteration 2: rand.nextInt(6) = 4, result = 4 + 10 = 14
Iteration 3: rand.nextInt(6) = 0, result = 0 + 10 = 10
Iteration 4: rand.nextInt(6) = 5, result = 5 + 10 = 15
Iteration 5: rand.nextInt(6) = 1, result = 1 + 10 = 11

Output:
12
14
10
15
11
```

**Key Concepts:**
- `new Random()` creates random number generator
- `nextInt(n)` returns 0 to n-1
- Add min to shift range to [min, max]

**Key Takeaway:** Understanding the formula is crucial for correct random ranges!

---

### **Exercise 8: chpt2_8.java - Sentinel Loop (Count Matches)**

**Problem:** Count how many inputs match a target value

**Concepts:**
- While loops with sentinel values
- Counter pattern
- Conditional incrementing

**Code Analysis:**
```java
public static void main(String[] args) {
    int count = 0;
    int input = 0;
    
    Scanner sc = new Scanner(System.in);
    
    System.out.println("Enter the target value: ");
    int target = sc.nextInt();
    
    System.out.println("Enter a series of integers. Enter -1 to quit: ");
    
    // Loop until sentinel value (-1) is entered
    while (input != -1) {
        input = sc.nextInt();
        if (target == input) {
            count++;
        }
    }
    
    System.out.println("Count: " + count);
    sc.close();
}
```

**Execution Example:**
```
Target: 5

Input: 5   → count = 1
Input: 5   → count = 2
Input: 3   → count = 2 (no match)
Input: 5   → count = 3
Input: -1  → exit loop

Output: Count: 3
```

**Sentinel Value Pattern:**
- Special value that signals "stop"
- Common: -1, "exit", "quit"
- Not processed as data

**⚠️ Bug in Original Code:**
The loop initializes `input = 0`, so if the user's first input is -1, the condition is already true and the loop doesn't run. Should initialize `input = 0` or check differently:

```java
// Better approach
int input;
System.out.println("Enter integers (-1 to quit): ");
input = sc.nextInt();  // Get first input

while (input != -1) {
    if (target == input) count++;
    input = sc.nextInt();  // Get next input
}
```

**Key Takeaway:** Sentinel loops need careful initialization!

---

### **Exercise 9: chpt2_9.java - Prime Number Checker**

**Problem:** Check if a number is prime

**Concepts:**
- Early exit with return
- Efficient prime checking algorithm
- Square root optimization

**Code Analysis:**
```java
public static void isPrime(int pNum) {
    if (pNum <= 1) {
        System.out.println("Not prime");
        return;
    }
    
    // Only check up to √n
    for (int i = 2; i * i < pNum; i++) {
        if (pNum % i == 0) {
            System.out.println("Not prime");
            return;  // Exit immediately
        }
    }
    
    System.out.println("Prime");
}

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter a number to check if it is prime:");
    int pNum = sc.nextInt();
    isPrime(pNum);
}
```

**Why Check Only Up to √n?**

```
If n has a factor greater than √n, 
it must also have a factor less than √n.

Example: n = 36
√36 = 6
Factors: 1, 2, 3, 4, 6, 9, 12, 18, 36
- 2 < 6 ✓
- 3 < 6 ✓
- 4 < 6 ✓
- 6 ≤ 6 ✓
- Need not check 9, 12, 18, 36
```

**Examples:**

**Prime Check: 17**
```
17 > 1? YES (continue)
i=2: 2*2=4 < 17? YES, 17%2=1 (not zero)
i=3: 3*3=9 < 17? YES, 17%3=2 (not zero)
i=4: 4*4=16 < 17? YES, 17%4=1 (not zero)
i=5: 5*5=25 < 17? NO (exit loop)
→ Prime
```

**Non-Prime Check: 15**
```
15 > 1? YES (continue)
i=2: 2*2=4 < 15? YES, 15%2=1 (not zero)
i=3: 3*3=9 < 15? YES, 15%3=0 (ZERO!)
→ Not prime (return immediately)
```

**Efficiency:**
```
Check n=100 for primality:
√100 = 10
Only need to check: 2, 3, 4, 5, 6, 7, 8, 9
(not 11-99!)
```

**Key Takeaway:** Use `return` to exit early! Optimize with √n!

---

### **Exercise 10: chpt2_10.java - Sum Even and Odd Numbers**

**Problem:** Separate sum for even and odd numbers up to n

**Concepts:**
- Modulo operator for even/odd detection
- Do-while loops
- Sentinel values

**Code Analysis:**
```java
public static void numSum(int input) {
    int evenSum = 0;
    int oddSum = 0;
    
    // Loop through 0 to input
    for (int i = 0; i <= input; i++) {
        if (i % 2 == 0) {
            evenSum += i;
        } else {
            oddSum += i;
        }
    }
    
    System.out.printf("Sum of even numbers: " + evenSum + " \n"
                    + "Sum of odd numbers: " + oddSum + "\n");
}

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int input = 0;
    
    // Do-while ensures at least one prompt
    do {
        System.out.println("Enter number (or -1 to quit): ");
        input = sc.nextInt();
        if (input != -1) {
            numSum(input);
        }
    } while (input != -1);
    
    sc.close();
}
```

**Even/Odd Detection:**
```
number % 2 == 0 → EVEN
number % 2 != 0 → ODD

0 % 2 = 0 (even)
1 % 2 = 1 (odd)
2 % 2 = 0 (even)
3 % 2 = 1 (odd)
...
```

**Example: n=5**
```
i=0: 0%2==0? YES → evenSum = 0
i=1: 1%2==0? NO  → oddSum = 1
i=2: 2%2==0? YES → evenSum = 2
i=3: 3%2==0? NO  → oddSum = 4
i=4: 4%2==0? YES → evenSum = 6
i=5: 5%2==0? NO  → oddSum = 9

Output:
Sum of even numbers: 6 (0+2+4)
Sum of odd numbers: 9 (1+3+5)
```

**Key Concepts:**
- `i % 2 == 0` detects even numbers
- Separate accumulators for each sum
- Do-while for menu loop

**Key Takeaway:** Modulo is perfect for even/odd checks!

---

### **Exercise 11: chpt2_11.java - Approximating Pi**

**Problem:** Use Leibniz formula to approximate π

**Concepts:**
- Mathematical series
- Alternating sums
- Precision improvement with iterations

**Code Analysis:**
```java
public static void ApxPi(int input) {
    double sum = 0;
    
    // Leibniz formula: π/4 = 1 - 1/3 + 1/5 - 1/7 + ...
    for (int i = 0; i <= input; i++) {
        int denominator = 2 * i + 1;  // 1, 3, 5, 7, ...
        
        if (i % 2 == 0) {
            sum += 1.0 / denominator;  // Add odd terms
        } else {
            sum -= 1.0 / denominator;  // Subtract even terms
        }
    }
    
    double pi = 4 * sum;  // Multiply by 4 to get π
    System.out.printf("Pi is approximately: %.10f\n", pi);
}

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter iterations: ");
    int input = sc.nextInt();
    ApxPi(input);
}
```

**Leibniz Formula:**
```
π/4 = 1 - 1/3 + 1/5 - 1/7 + 1/9 - ...

So: π = 4 × (1 - 1/3 + 1/5 - 1/7 + 1/9 - ...)
```

**Building the Series:**
```
Denominator Pattern: 2*i + 1
i=0: 2*0+1 = 1
i=1: 2*1+1 = 3
i=2: 2*2+1 = 5
i=3: 2*3+1 = 7
...

Sign Pattern (i % 2):
i=0: even → ADD   (1/1)
i=1: odd  → SUBTRACT (1/3)
i=2: even → ADD   (1/5)
i=3: odd  → SUBTRACT (1/7)
```

**Step-by-Step for input=3:**
```
i=0: sum = 0 + 1/1 = 1.0
i=1: sum = 1.0 - 1/3 = 0.667
i=2: sum = 0.667 + 1/5 = 0.867
i=3: sum = 0.867 - 1/7 = 0.724

pi = 4 * 0.724 = 2.897
```

**Accuracy Improves with More Iterations:**
```
Iterations: 100    → π ≈ 3.1316
Iterations: 1000   → π ≈ 3.1406
Iterations: 10000  → π ≈ 3.1415
Iterations: 100000 → π ≈ 3.14159
(Actual: π ≈ 3.14159...)
```

**Output Example:**
```
Enter iterations: 10000
Pi is approximately: 3.1415426536
```

**Key Concepts:**
- Alternating series (add, subtract, add, subtract)
- Denominator pattern (1, 3, 5, 7, ...)
- More iterations = more accuracy

**Key Takeaway:** Mathematical series converge to better approximations!

---

## Common Loop Patterns

### **Pattern 1: Accumulator (Sum)**
```java
int sum = 0;
for (int i = 1; i <= n; i++) {
    sum += i;  // Add each element
}
System.out.println(sum);
```

### **Pattern 2: Counter**
```java
int count = 0;
while (hasMore) {
    if (condition) {
        count++;  // Increment when condition met
    }
}
System.out.println(count);
```

### **Pattern 3: Sentinel Loop**
```java
int input;
do {
    System.out.print("Enter (or -1 to quit): ");
    input = sc.nextInt();
    if (input != -1) {
        // Process input
    }
} while (input != -1);
```

### **Pattern 4: Nested Loop (2D)**
```java
for (int i = 0; i < rows; i++) {
    for (int j = 0; j < cols; j++) {
        // Process [i][j]
    }
}
```

### **Pattern 5: Search Loop**
```java
boolean found = false;
for (int i = 0; i < arr.length; i++) {
    if (arr[i] == target) {
        found = true;
        break;  // Exit immediately
    }
}
```

### **Pattern 6: Running Total with Condition**
```java
int sum = 0;
for (int i = 0; i < arr.length; i++) {
    if (arr[i] > 0) {
        sum += arr[i];  // Only add positive numbers
    }
}
```

### **Pattern 7: Even/Odd Separation**
```java
int evenSum = 0, oddSum = 0;
for (int i = 0; i <= n; i++) {
    if (i % 2 == 0) {
        evenSum += i;
    } else {
        oddSum += i;
    }
}
```

---

## Recursion Deep Dive

### **What is Recursion?**
A function that calls itself to solve a problem by breaking it into smaller identical subproblems.

### **Required Components**

**1. Base Case (Termination)**
- Simplest version of problem with direct answer
- Prevents infinite recursion
- Must be reachable

```java
if (n <= 0) return 0;  // Base case
```

**2. Recursive Case**
- Problem becomes smaller
- Function calls itself with smaller input
- Eventually reaches base case

```java
return n + sum(n - 1);  // Recursive case
```

### **How Recursion Works**

**Example: factorial(5)**

```
factorial(5)
│
├─ 5 != 0, so:
│  5 * factorial(4)
│  │
│  ├─ 4 != 0, so:
│  │  4 * factorial(3)
│  │  │
│  │  ├─ 3 != 0, so:
│  │  │  3 * factorial(2)
│  │  │  │
│  │  │  ├─ 2 != 0, so:
│  │  │  │  2 * factorial(1)
│  │  │  │  │
│  │  │  │  ├─ 1 == 0 or 1, so:
│  │  │  │  │  return 1 ← BASE CASE
│  │  │  │  │
│  │  │  │  ← return 2 * 1 = 2
│  │  │  │
│  │  │  ← return 3 * 2 = 6
│  │  │
│  │  ← return 4 * 6 = 24
│  │
│  ← return 5 * 24 = 120
```

### **Stack Visualization**

```
Call Stack:
┌──────────────────┐
│ factorial(1)=1   │ ← Base case, start returning
│ factorial(2)=?   │
│ factorial(3)=?   │
│ factorial(4)=?   │
│ factorial(5)=?   │ ← Original call
└──────────────────┘

As functions return:
┌──────────────────┐
│ factorial(1)=1   │ ← return 1
│ factorial(2)=?   │
│ factorial(3)=?   │
│ factorial(4)=?   │
│ factorial(5)=?   │
└──────────────────┘

┌──────────────────┐
│ factorial(2)=2×1 │ ← return 2
│ factorial(3)=?   │
│ factorial(4)=?   │
│ factorial(5)=?   │
└──────────────────┘

... and so on until all return
```

### **Recursion vs Loop**

**Recursion:**
```java
public static int factorial(int n) {
    if (n <= 1) return 1;
    return n * factorial(n - 1);
}
```

**Loop:**
```java
public static int factorial(int n) {
    int result = 1;
    for (int i = 2; i <= n; i++) {
        result *= i;
    }
    return result;
}
```

**Pros/Cons:**
| Recursion | Loop |
|-----------|------|
| ✅ Elegant, matches problem structure | ✅ More efficient |
| ✅ Natural for tree/graph problems | ✅ No stack overflow |
| ❌ Slower (function calls) | ❌ Less intuitive for some problems |
| ❌ Stack overflow risk | ❌ More code sometimes |

### **Common Recursive Patterns**

**Pattern 1: Linear Recursion**
```java
// Count down
public static void countDown(int n) {
    if (n <= 0) return;
    System.out.println(n);
    countDown(n - 1);
}
```

**Pattern 2: Accumulator Recursion**
```java
// Sum 1 to n
public static int sum(int n) {
    if (n <= 0) return 0;
    return n + sum(n - 1);
}
```

**Pattern 3: Binary Recursion** (Two recursive calls)
```java
// Fibonacci
public static int fib(int n) {
    if (n <= 1) return n;
    return fib(n - 1) + fib(n - 2);
}
```

**Pattern 4: Factorial**
```java
public static int factorial(int n) {
    if (n <= 1) return 1;
    return n * factorial(n - 1);
}
```

---

## Master Checklist

### **Chapter 2 Mastery Checklist**

- [ ] **Loop Fundamentals**
  - [ ] Understand for loop structure (init; condition; update)
  - [ ] Understand while loop structure (condition check first)
  - [ ] Understand do-while loop structure (body runs first)
  - [ ] Know when to use each loop type

- [ ] **Loop Control**
  - [ ] Understand and use `break` statement
  - [ ] Understand and use `continue` statement
  - [ ] Know when to use each

- [ ] **Loop Variables**
  - [ ] Understand loop control variables
  - [ ] Can write increment/decrement patterns
  - [ ] Can use loop variable in calculations

- [ ] **Infinite Loops**
  - [ ] Recognize infinite loop patterns
  - [ ] Avoid missing increments
  - [ ] Avoid wrong comparison operators

- [ ] **Patterns**
  - [ ] Accumulator pattern (sum, product)
  - [ ] Counter pattern (count matches)
  - [ ] Sentinel value pattern
  - [ ] Nested loops
  - [ ] Search with break
  - [ ] Even/odd detection

- [ ] **Recursion**
  - [ ] Understand base case (critical!)
  - [ ] Understand recursive case
  - [ ] Can trace recursive calls
  - [ ] Understand call stack
  - [ ] Know when recursion is appropriate

- [ ] **All 11 Exercises**
  - [ ] chpt2_1: Sum 1-100 (accumulator)
  - [ ] chpt2_2: Factorial (recursion)
  - [ ] chpt2_3: Iterative vs formula (efficiency)
  - [ ] chpt2_4: Reverse number (modulo/division)
  - [ ] chpt2_5: Fibonacci (binary recursion)
  - [ ] chpt2_6: Draw rectangle (nested loops)
  - [ ] chpt2_7: Random integers (range formula)
  - [ ] chpt2_8: Sentinel loop (count matches)
  - [ ] chpt2_9: Prime checker (√n optimization)
  - [ ] chpt2_10: Even/odd sum (modulo)
  - [ ] chpt2_11: Approximate π (alternating series)

- [ ] **Advanced Topics**
  - [ ] Understand time complexity (O(n), O(1))
  - [ ] Recognize inefficient recursion
  - [ ] Apply loop optimizations

---

## Quick Reference

### **Loop Syntax**

```java
// FOR LOOP
for (init; condition; update) {
    // body
}

// WHILE LOOP
while (condition) {
    // body
}

// DO-WHILE LOOP
do {
    // body
} while (condition);

// FOR-EACH LOOP (arrays/collections)
for (type item : collection) {
    // body
}
```

### **Common Loop Conditions**

```java
for (int i = 0; i < 10; i++)         // 0 to 9
for (int i = 1; i <= 10; i++)        // 1 to 10
for (int i = 10; i > 0; i--)         // 10 down to 1
for (int i = 0; i < arr.length; i++) // all array elements
```

### **Loop Control Statements**

```java
break;      // Exit loop immediately
continue;   // Skip to next iteration
return;     // Exit method (and loop)
```

### **Operators in Loops**

```java
i++         // Increment by 1
i--         // Decrement by 1
i += 2      // Add 2
i -= 3      // Subtract 3
i *= 2      // Multiply by 2
i /= 3      // Divide by 3

i % 2       // Remainder (modulo)
```

### **Recursion Template**

```java
public static returnType methodName(parameters) {
    // BASE CASE
    if (baseCondition) {
        return baseValue;
    }
    
    // RECURSIVE CASE
    return methodName(modifiedParameters);
}
```

### **Key Math Operators**

```java
%   // Modulo (remainder)      7 % 3 = 1
/   // Integer division        7 / 3 = 2
*   // Multiplication           7 * 3 = 21
+   // Addition                 7 + 3 = 10
-   // Subtraction             7 - 3 = 4

// Power (Math class)
Math.pow(2, 3)  // 2^3 = 8
Math.sqrt(9)    // √9 = 3
```

### **Random Number Generation**

```java
Random rand = new Random();

// 0 to 9
rand.nextInt(10);

// 1 to 10
rand.nextInt(10) + 1;

// min to max
rand.nextInt((max - min) + 1) + min;
```

### **String/Input Methods**

```java
Scanner sc = new Scanner(System.in);

int x = sc.nextInt();           // Read integer
double d = sc.nextDouble();     // Read decimal
String s = sc.nextLine();       // Read line
char c = sc.next().charAt(0);   // Read character

sc.close();  // Always close!
```

---

**End of Chapter 2 Study Guide**

*Use this guide to review, teach others, or prepare for tests. Loops and recursion are fundamental - master them! 🎓*
