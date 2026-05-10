# Chapter 3: Methods and Calculations - Complete Study Guide

**By: Tyler Spring | Date: 1/20/2025 - 1/29/2025**

---

## 📖 Table of Contents

1. [Short Answer Review](#short-answer-review)
2. [Core Concepts Explained](#core-concepts-explained)
3. [Exercise-by-Exercise Breakdown](#exercise-by-exercise-breakdown)
4. [Case Study: Projectile Motion](#case-study-projectile-motion)
5. [Master Checklist](#master-checklist)
6. [Quick Reference](#quick-reference)

---

## Short Answer Review

### **Q1: What is the difference between a parameter and a constant in a method? Why might you use one over the other?**

#### Parameters:
- Variables **passed into** a method from outside
- Exist **only within** the method's scope
- **Destroyed** when the method completes
- Only a **copy** is passed (for primitives) or a reference (for objects)
- Changes inside the method **don't affect** the original variable

#### Constants:
- Immutable values declared with the `final` keyword
- Can have **global scope** or local scope
- **Never change** throughout the program
- Useful for configuration values (gravity, π, interest rates)

#### When to Use Each:
- **Parameters**: When a method needs different values on different calls
  ```java
  public double calcArea(double radius) {  // radius changes each call
      return Math.PI * radius * radius;
  }
  ```
- **Constants**: When a value never changes
  ```java
  public static final double GRAVITY = 9.8;  // Always 9.8
  public static final double INTEREST_RATE = 0.05;  // Company standard
  ```

**Real Example from Your Code:**
```java
// chp3_1.java
public static final double ANNUAL_INTEREST_RATE = 0.05;  // CONSTANT

public static double calcMonthPayment(double loan, double annualInterestRate, int loanYears) {
    // loan, annualInterestRate, loanYears are PARAMETERS
    // They change with each method call
}
```

---

### **Q2: Explain how method overloading works. Provide an example where overloading might be useful.**

**Method Overloading:**
Multiple methods with the **same name** but **different parameters** (type, number, or both)

#### Rules:
1. Same method name ✓
2. Different parameter signatures (type or quantity) ✓
3. Different return types alone is NOT enough ✗

#### Why Overloading?
- Cleaner, more intuitive API
- Same operation name for similar tasks
- Less confusing than `addInt()`, `addDouble()`, `addThreeInts()`

**Real Example from Your Code:**

```java
// chp3_2.java - calcArea with 3 overloaded versions

public static double calcArea(int len, int wid) {          // Rectangle
    return len * wid;
}

public static double calcArea(double rad) {                 // Circle
    return Math.PI * rad * rad;
}

public static double calcArea(double base, double height) { // Triangle
    return 0.5 * base * height;
}

// Usage:
calcArea(5, 10);           // Calls Rectangle version (two ints)
calcArea(5.0);             // Calls Circle version (one double)
calcArea(5.0, 10.0);       // Calls Triangle version (two doubles)
```

**Better than:**
```java
// ❌ Bad - confusing names
calcRectangleArea(5, 10);
calcCircleArea(5);
calcTriangleArea(5, 10);
```

---

### **Q3: What happens to a parameter variable when the method completes?**

**Short Answer:** The parameter variable is **destroyed**. Changes made to it inside the method **do NOT affect** the original variable outside.

**Detailed Explanation:**

```java
public static void modifyValue(int num) {
    num = 100;  // Changes num inside the method
    System.out.println("Inside method: " + num);  // Prints 100
}

public static void main(String[] args) {
    int x = 5;
    modifyValue(x);
    System.out.println("Outside method: " + x);  // Still prints 5!
}
```

**Why?** For primitive types, **a copy** is passed, not the original.

**Exception:** For objects (like arrays, strings), a **reference** is passed:
```java
public static void modifyArray(int[] arr) {
    arr[0] = 999;  // Changes the actual array
}

int[] myArray = {1, 2, 3};
modifyArray(myArray);
System.out.println(myArray[0]);  // Prints 999 (changed!)
```

---

### **Q4: If a method accepts multiple parameters, how can you ensure they're passed in the correct order? What happens if they aren't?**

**Solution:** Match the **order exactly** in the method call to the method declaration.

**Real Example from Your Code:**

```java
// chp3_3.java - formatDate
public static String formatDate(int day, String month, int year) {
    return String.format("%d %s %d", day, month, year);
}

// ✅ CORRECT ORDER
formatDate(20, "January", 2025);  // Output: "20 January 2025"

// ❌ WRONG ORDER
formatDate(2025, "January", 20);  // Output: "2025 January 20" (WRONG!)
formatDate("January", 20, 2025);  // COMPILATION ERROR (type mismatch)
```

**What Happens if Order is Wrong:**

| Scenario | Result |
|----------|--------|
| Types match but order wrong | **Wrong calculation/output** (silent error!) |
| Types don't match | **Compilation error** (caught at compile time) |
| Wrong number of params | **Compilation error** (caught at compile time) |

**Best Practice: Add Validation**
```java
public static String formatDate(int day, String month, int year) {
    // Validate parameter order
    if (year < 1000 && day > 31) {
        throw new IllegalArgumentException("Parameters out of order.");
    }
    if (day < 1 || day > 31) {
        throw new IllegalArgumentException("Day must be 1-31");
    }
    return String.format("%d %s %d", day, month, year);
}
```

---

### **Q5: How would you decide whether a value should be passed as a parameter or stored as a class-level constant?**

| Scenario | Use This | Example |
|----------|----------|---------|
| Value changes per call | **Parameter** | `calcArea(radius)` |
| Same value always | **Constant** | `GRAVITY = 9.8` |
| User input | **Parameter** | `calcPayment(loanAmount)` |
| Configuration | **Constant** | `TAX_RATE = 0.08` |
| Calculated result | **Return value** | `return area;` |

**Decision Tree:**
```
Does this value change per method call?
├─ YES → Use PARAMETER
└─ NO → Is it a universal constant?
    ├─ YES → Use CLASS-LEVEL CONSTANT
    └─ NO → Use LOCAL VARIABLE
```

**Real Examples from Your Code:**

```java
// chp3_1.java - Good mix of parameters and constants
public static final double ANNUAL_INTEREST_RATE = 0.05;  // Never changes

public static double calcMonthPayment(double loan,      // Changes per call
                                      double annualInterestRate,
                                      int loanYears) {
    // loan and years are parameters (change per call)
    // ANNUAL_INTEREST_RATE is a constant (never changes)
}

// caseStudy.java - Another good example
double velocity = getInput("Enter velocity: ", input);  // Parameter
double angleInRads = Math.toRadians(angle);             // Calculated

// These could be constants, but might vary by scenario:
double timeOfFlight = calcTimeOfFlight(velocity, angleInRads);
```

---

### **Q6: Name 3 useful methods from the Math class and describe what they do. When might you use each one?**

#### **Math.pow(base, exponent)** - Power Function
```java
Math.pow(2, 5);          // = 32
Math.pow(10, 3);         // = 1000
Math.pow(2, 0.5);        // = √2 ≈ 1.414
```
**When to use:**
- Exponential calculations
- Compound interest (from chp3_8.java)
- Physics formulas with exponents

**Real Example:**
```java
// chp3_8.java - Compound Interest
double a = p * Math.pow(1 + r/n, n*t);  // A = P(1 + r/n)^(nt)
```

#### **Math.round(number)** - Rounding
```java
Math.round(3.2);         // = 3
Math.round(3.7);         // = 4
Math.round(3.5);         // = 4
```
**When to use:**
- Display financial values (money)
- User-friendly output
- Reducing decimal places

#### **Math.random()** - Random Numbers
```java
Math.random();           // 0.0 to 0.999...
Math.random() * 100;     // 0 to 99.999...
Math.random() * 10 + 1;  // 1 to 10.999...
```
**When to use:**
- Simulations
- Game development
- Random test data generation

#### **Bonus Useful Methods:**

| Method | Purpose | Example |
|--------|---------|---------|
| `Math.sqrt(x)` | Square root | `Math.sqrt(16)` = 4 |
| `Math.sin(x)` | Sine (radians) | `Math.sin(Math.PI/2)` = 1.0 |
| `Math.cos(x)` | Cosine (radians) | `Math.cos(0)` = 1.0 |
| `Math.abs(x)` | Absolute value | `Math.abs(-5)` = 5 |
| `Math.max(a,b)` | Maximum | `Math.max(5, 10)` = 10 |
| `Math.min(a,b)` | Minimum | `Math.min(5, 10)` = 5 |
| `Math.toRadians(x)` | Degrees to radians | `Math.toRadians(180)` = 3.14159 |

---

### **Q7: What does it mean for a method to return a value? Why is it important to return values rather than just print them?**

**Returning a Value:**
A method executes its code, then sends a value back to the caller using the `return` statement.

```java
public static double calculateArea(double radius) {
    double area = Math.PI * radius * radius;
    return area;  // Send value back to caller
}

// Usage:
double myArea = calculateArea(5);  // Get the value back
System.out.println(myArea);         // Now we can use it
```

#### **Print vs Return Comparison:**

```java
// ❌ BAD - Print Only
public static void calculateArea(double radius) {
    double area = Math.PI * radius * radius;
    System.out.println("Area: " + area);  // Lost forever!
}

// Usage:
calculateArea(5);  // Can't save or reuse the result

// ✅ GOOD - Return Value
public static double calculateArea(double radius) {
    return Math.PI * radius * radius;
}

// Usage:
double area1 = calculateArea(5);
double area2 = calculateArea(10);
double totalArea = area1 + area2;  // Can reuse!
System.out.println(totalArea);
```

**Why Returning is Important:**

| Benefit | Example |
|---------|---------|
| **Reusability** | Use same calculation multiple times |
| **Composability** | Combine results: `a + b + c` |
| **Testability** | Easy to test: `assert result == expected` |
| **Flexibility** | Store, process, or display as needed |
| **Clean Code** | Separation of concerns |

**Real Examples from Your Code:**

```java
// chp3_5.java - getSmallestDiff
public static int getSmallestDiff(int[] arr1, int[] arr2) {
    // ... calculation ...
    return smallestDiff;  // Return the value
}

// Usage:
int diff = getSmallestDiff(array1, array2);  // We get the result
System.out.println("Difference: " + diff);    // Now we can use it
```

---

### **Q8: How does Math.pow() method work? Write a quick example of how you would calculate 2^5 using this method.**

**Math.pow() Syntax:**
```java
Math.pow(base, exponent)
```

**Calculation of 2^5:**
```java
Math.pow(2, 5);  // Returns 32.0 (as a double)
// 2 × 2 × 2 × 2 × 2 = 32
```

**Key Points:**
- Returns a **double** (not int)
- Works with negative exponents: `Math.pow(2, -1)` = 0.5
- Works with fractional exponents: `Math.pow(4, 0.5)` = 2.0 (square root)

**Real Examples from Your Code:**

```java
// chp3_1.java - Loan Payment Calculation
double rPowN = Math.pow(1 + r, n);  // (1 + r)^n for monthly payments

// chp3_8.java - Compound Interest
double a = p * Math.pow(1 + r/n, n*t);  // A = P(1 + r/n)^(nt)

// chp3_5.java - Difference Calculation
int diff = Math.abs(arr1[i] - arr2[j]);  // Uses Math.abs
```

---

### **Q9: Describe the role of the Scanner class in interactive programs. How do you validate invalid input using a Scanner?**

**Scanner's Role:**
Reads user input from the keyboard and converts it to appropriate data types.

#### **Basic Usage:**
```java
import java.util.Scanner;

Scanner sc = new Scanner(System.in);

int age = sc.nextInt();           // Read integer
double height = sc.nextDouble();  // Read decimal
String name = sc.nextLine();      // Read entire line

sc.close();  // Always close when done
```

#### **Input Validation Pattern:**

```java
// chp3_9.java - Validate positive integer
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    while (true) {
        System.out.println("Enter a positive integer: ");

        // Check if next input is an integer
        if (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a positive integer.");
            sc.next();  // Consume the bad input
            continue;   // Try again
        }

        int num = sc.nextInt();

        // Check if it's positive
        if (num > 0) {
            System.out.println("Valid input: " + num);
            break;  // Exit loop
        } else {
            System.out.println("Please enter a number greater than 0.");
        }
    }
}
```

#### **Validation Checklist:**

| Check | Method | Example |
|-------|--------|---------|
| Is it an integer? | `hasNextInt()` | `if (sc.hasNextInt())` |
| Is it a double? | `hasNextDouble()` | `if (sc.hasNextDouble())` |
| Is it a string? | `hasNext()` | `if (sc.hasNext())` |
| Is it positive? | Compare with 0 | `if (num > 0)` |
| Is it in range? | Compare bounds | `if (num >= min && num <= max)` |

**Real Examples from Your Code:**

```java
// chp3_7.java - Calculator with Menu Validation
System.out.println("Select an operation: ");
int oper = sc.nextInt();

switch (oper) {
    case 1: res = num1 + num2; break;
    case 2: res = num1 - num2; break;
    case 3: res = num1 * num2; break;
    case 4: res = num1 / num2; break;  // Should add division by zero check!
    default:
        System.out.println("Invalid operation.");
        continue;
}

// chp3_4.java - 3D Distance with Scanner
System.out.println("Enter coordinates of first point (x1, y1, z1): ");
double x1 = sc.nextDouble();
double y1 = sc.nextDouble();
double z1 = sc.nextDouble();
```

---

### **Q10: Explain what a string is in Java and how it differs from primitive types. What makes String objects immutable.**

**What is a String?**
- An **array of characters** (`char[]`)
- A **reference type** (not a primitive)
- Immutable - cannot be changed after creation

**Primitive vs Reference Types:**

```java
// PRIMITIVE TYPE - stores value directly
int age = 25;        // Value is stored as 25

// REFERENCE TYPE - stores address to object
String name = "Tyler";  // Stores address to String object
```

#### **Strings are Immutable (Fixed Size):**

```java
String original = "Hello";
original = original + " World";
// This CREATES A NEW String object
// The original "Hello" might still exist in memory (garbage collection)
```

**Why?** Arrays (including Strings) are fixed size:
- Can't add to the end of an array
- Can't remove from an array
- Any change = new array entirely

```java
// Visualized:
String s = "Java";           // Object 1: "Java" in memory
s = s + " Programming";      // Creates NEW object: "Java Programming"
                             // Old object "Java" is abandoned
```

**Immutability Benefits:**
- Thread-safe (safe in multi-threaded programs)
- Can be used as HashMap keys
- Secure (passwords won't change unexpectedly)

**Working with Strings:**

```java
// chp3_6.java - Reverse Words
public static String reverseString(String str) {
    String[] words = str.split(" ");     // Split into array
    String reversed = "";                 // Build new String
    for (int i = words.length - 1; i >= 0; i--) {
        reversed += words[i] + " ";      // Creates new String each time
    }
    return reversed.trim();
}

// chp3_10.java - Caesar Cipher
public static String encryptString(String str, int shift) {
    StringBuilder res = new StringBuilder();  // Better for building strings!
    
    for (int i = 0; i < str.length(); i++) {
        char c = str.charAt(i);
        if (Character.isLetter(c)) {
            char base = Character.isUpperCase(c) ? 'A' : 'a';
            char encryptedChar = (char) (base + ((c - base + shift) % 26 + 26) % 26);
            res.append(encryptedChar);  // Append to StringBuilder
        } else {
            res.append(c);
        }
    }
    return res.toString();  // Convert back to String
}
```

**String Methods You Used:**
```java
str.split(" ")              // Split into array
str.charAt(i)               // Get character at index
str.length()                // Get length
str.equalsIgnoreCase("yes") // Case-insensitive comparison
s.trim()                    // Remove whitespace
res.toString()              // Convert StringBuilder to String
```

---

## Core Concepts Explained

### **Parameters: Flow and Scope**

```
┌─────────────────────────────────┐
│ MAIN METHOD                     │
│ int x = 5;  ← Original variable │
│ add(x, 3);  ← Pass COPY        │
└─────────────────────────────────┘
           ↓ (copy of x, not x itself)
┌─────────────────────────────────┐
│ ADD METHOD (int num, int y)     │
│ num = 5 (copy)  ← Parameter    │
│ num = num + y = 8               │
└─────────────────────────────────┘
           ↓
┌─────────────────────────────────┐
│ MAIN METHOD                     │
│ int x = 5;  ← STILL 5!         │
│ (unchanged)                     │
└─────────────────────────────────┘
```

### **Method Overloading: Dispatch Table**

When you call `calcArea()`, Java looks at:
1. Number of parameters
2. Type of parameters
3. Order of parameters

```java
calcArea(5, 10)           // ← Two ints → Rectangle version
calcArea(5.0)             // ← One double → Circle version
calcArea(5.0, 10.0)       // ← Two doubles → Triangle version
```

### **Return Values: Data Flow**

```
Method:              Caller:
┌──────────────┐    ┌──────────────┐
│ calculate()  │    │ main()       │
│ return 42;   │───→│ x = result;  │
└──────────────┘    │ use x        │
                    └──────────────┘
```

---

## Exercise-by-Exercise Breakdown

### **Exercise 1: chp3_1.java - Loan Payment Calculator**

**Problem:** Calculate monthly payment for a loan

**Concepts:**
- Constants vs Parameters
- Math.pow()
- Method with multiple parameters

**Code Analysis:**
```java
public static final double ANNUAL_INTEREST_RATE = 0.05;  // Constant

public static double calcMonthPayment(double loan,           // Parameters
                                      double annualInterestRate,
                                      int loanYears) {
    // If no rate provided, use constant
    if (annualInterestRate <= 0) {
        annualInterestRate = ANNUAL_INTEREST_RATE;
    }
    
    // Convert to monthly rate
    annualInterestRate = annualInterestRate / 12;
    int n = loanYears * 12;                    // Number of payments
    double r = annualInterestRate / 12;        // Monthly interest rate
    
    // Monthly Payment: M = (P*r*(1+r)^n) / ((1+r)^n - 1)
    double rPowN = Math.pow(1 + r, n);         // (1+r)^n
    double monthPayment = (loan * r * rPowN) / (rPowN - 1);
    
    return monthPayment;
}
```

**Example Run:**
```
Loan: $1000
Annual Rate: 6% (0.06)
Years: 2

Monthly Payment: $43.32
```

**Key Takeaway:** Use constants for company standards, parameters for variable data

---

### **Exercise 2: chp3_2.java - Overloaded Area Calculator**

**Problem:** Calculate area for different shapes with same method name

**Concepts:**
- Method overloading
- Different parameter types/counts
- Math.PI constant

```java
// Three versions with SAME NAME, DIFFERENT SIGNATURES
public static double calcArea(int len, int wid) {           // Rectangle
    return len * wid;
}

public static double calcArea(double rad) {                  // Circle
    return Math.PI * rad * rad;
}

public static double calcArea(double base, double height) {  // Triangle
    return 0.5 * base * height;
}
```

**Usage Pattern:**
```java
calcArea(5, 10);          // 50 (rectangle)
calcArea(5);              // 78.54 (circle)
calcArea(5, 10);          // 25 (triangle) ← Same as rectangle!
```

**Why Overloading Works Here:**
- Rectangle: `int, int` (two integers)
- Circle: `double` (one double)
- Triangle: `double, double` (two doubles)

Java distinguishes by parameter signature!

**Key Takeaway:** Overloading makes APIs cleaner and more intuitive

---

### **Exercise 3: chp3_3.java - Date Formatter with Validation**

**Problem:** Format date with parameter validation

**Concepts:**
- Multiple parameters with type checking
- Order importance
- Exception handling

```java
public static String formatDate(int day, String month, int year) {
    // Validate order - if year < 1000 AND day > 31, probably reversed
    if (year < 1000 && day > 31) {
        throw new IllegalArgumentException("Parameters out of order.");
    }
    
    // Validate each parameter
    if (day < 1 || day > 31) {
        throw new IllegalArgumentException("Day must be 1-31");
    }
    
    if (month == null || month.length() == 0) {
        throw new IllegalArgumentException("Month must be valid");
    }
    
    if (year <= 0) {
        throw new IllegalArgumentException("Year must be positive");
    }
    
    return String.format("%d %s %d", day, month, year);
}
```

**Correct vs Incorrect:**
```java
formatDate(20, "January", 2025);        // ✅ "20 January 2025"
formatDate(2025, "January", 20);        // ❌ Wrong order
formatDate(32, "January", 2025);        // ❌ Invalid day
formatDate(20, "", 2025);               // ❌ Invalid month
```

**Key Takeaway:** Always validate parameters, especially when order matters!

---

### **Exercise 4: chp3_4.java - 3D Distance Calculator**

**Problem:** Find distance between two points in 3D space

**Concepts:**
- Multiple parameters
- Math.sqrt() and Math.pow()
- Scanner input
- Distance formula

**Formula:**
```
Distance = √[(x₂-x₁)² + (y₂-y₁)² + (z₂-z₁)²]
```

```java
public static double calcDist(double x1, double y1, double z1,
                              double x2, double y2, double z2) {
    // Calculate distance using formula
    double distance = Math.sqrt(
        Math.pow(x2 - x1, 2) + 
        Math.pow(y2 - y1, 2) + 
        Math.pow(z2 - z1, 2)
    );
    return distance;
}
```

**Example:**
```
Point 1: (0, 0, 0)
Point 2: (3, 4, 0)
Distance: √(3² + 4² + 0²) = √(9 + 16) = √25 = 5.0
```

**Key Takeaway:** Breaking down formulas into Math methods makes code clean

---

### **Exercise 5: chp3_5.java - Smallest Difference Between Arrays**

**Problem:** Find smallest absolute difference between elements from two arrays

**Concepts:**
- Arrays as parameters
- Return values
- Nested loops
- Math.abs()

```java
public static int getSmallestDiff(int[] arr1, int[] arr2) {
    if (arr1.length == 0 || arr2.length == 0) {
        return -1;  // Invalid input
    }
    
    int smallestDiff = Integer.MAX_VALUE;  // Start with huge number
    
    // Compare every element from arr1 with every element from arr2
    for (int i = 0; i < arr1.length; i++) {
        for (int j = 0; j < arr2.length; j++) {
            int diff = Math.abs(arr1[i] - arr2[j]);  // Absolute difference
            if (diff < smallestDiff) {
                smallestDiff = diff;  // Update if smaller
            }
        }
    }
    return smallestDiff;
}
```

**Example:**
```
Array 1: [10, 20, 14, 16, 18]
Array 2: [30, 23, 54, 33, 96]

Comparisons:
10 vs 30: diff = 20
10 vs 23: diff = 13
14 vs 23: diff = 9
16 vs 23: diff = 7
18 vs 23: diff = 5
...
Smallest: 3 (between 20 and 23)
```

**Key Takeaway:** Nested loops for comparing collections, return the result

---

### **Exercise 6: chp3_6.java - Reverse Words in a Sentence**

**Problem:** Reverse word order in a sentence

**Concepts:**
- String manipulation
- split() method
- String concatenation
- trim() method

```java
public static String reverseString(String str) {
    String[] words = str.split(" ");    // Split by spaces
    String reversed = "";
    
    // Loop backwards through array
    for (int i = words.length - 1; i >= 0; i--) {
        reversed += words[i] + " ";     // Add each word
    }
    
    return reversed.trim();  // Remove trailing space
}
```

**Example:**
```
Input:  "Java programming is fun"
Split:  ["Java", "programming", "is", "fun"]
Loop backwards:
  reversed = "fun "
  reversed = "fun is "
  reversed = "fun is programming "
  reversed = "fun is programming Java "
Trim:   "fun is programming Java"
```

**Optimization Note:** Using `StringBuilder` is more efficient:
```java
StringBuilder reversed = new StringBuilder();
for (int i = words.length - 1; i >= 0; i--) {
    reversed.append(words[i]).append(" ");
}
return reversed.toString().trim();
```

**Key Takeaway:** String concatenation is slow; use StringBuilder for building strings

---

### **Exercise 7: chp3_7.java - Interactive Calculator**

**Problem:** Build a menu-driven calculator

**Concepts:**
- Scanner for input
- Input validation
- switch statements
- do-while loops for menus

```java
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    boolean keepRunning = true;

    while (keepRunning) {
        // Get numbers
        System.out.println("Enter first number: ");
        double num1 = sc.nextDouble();
        System.out.println("Enter second number: ");
        double num2 = sc.nextDouble();

        // Validate
        if (num1 <= 0 || num2 <= 0) {
            System.out.println("Please enter positive numbers.");
            continue;  // Skip this iteration
        }

        // Show menu
        System.out.println("Select operation:");
        System.out.println("1. Addition\n2. Subtraction\n3. Multiplication\n4. Division");
        int oper = sc.nextInt();

        double result = 0;
        
        // Perform operation
        switch (oper) {
            case 1: result = num1 + num2; break;
            case 2: result = num1 - num2; break;
            case 3: result = num1 * num2; break;
            case 4: result = num1 / num2; break;  // ⚠️ No zero check!
            default:
                System.out.println("Invalid operation.");
                continue;
        }

        System.out.println("Result: " + result);

        // Ask to continue
        System.out.println("Continue? (yes/no)");
        String choice = sc.next();
        if (choice.equalsIgnoreCase("no")) {
            keepRunning = false;
        }
    }
    sc.close();
}
```

**Improvements Needed:**
```java
// Add division by zero check
case 4:
    if (num2 == 0) {
        System.out.println("Cannot divide by zero!");
        continue;
    }
    result = num1 / num2;
    break;
```

**Key Takeaway:** Interactive programs need robust validation and error handling

---

### **Exercise 8: chp3_8.java - Compound Interest Calculator**

**Problem:** Calculate compound interest: A = P(1 + r/n)^(nt)

**Concepts:**
- Math.pow() for exponents
- Formula implementation
- Scanner for input

```java
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("Enter principal: ");
    double p = sc.nextDouble();

    System.out.println("Enter annual interest rate (decimal): ");
    double r = sc.nextDouble();

    System.out.println("Enter compounding frequency per year: ");
    double n = sc.nextDouble();

    System.out.println("Enter number of years: ");
    double t = sc.nextDouble();

    // Formula: A = P(1 + r/n)^(nt)
    double a = p * Math.pow(1 + r/n, n*t);
    
    System.out.println("Final amount: " + a);
    sc.close();
}
```

**Example:**
```
Principal: $1000
Rate: 5% (0.05)
Compounding: 4 times/year (quarterly)
Time: 5 years

A = 1000 × (1 + 0.05/4)^(4×5)
A = 1000 × (1.0125)^20
A ≈ $1282.04
```

**Key Takeaway:** Math.pow() is essential for financial and scientific calculations

---

### **Exercise 9: chp3_9.java - Input Validation with Scanner**

**Problem:** Validate user input is a positive integer

**Concepts:**
- hasNextInt() method
- Error recovery
- Validation loop

```java
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    while (true) {
        System.out.println("Enter a positive integer: ");

        // Check if input is an integer
        if (!sc.hasNextInt()) {
            System.out.println("Invalid input. Enter a positive integer.");
            sc.next();  // Consume bad input
            continue;   // Try again
        }

        int num = sc.nextInt();

        // Check if it's positive
        if (num > 0) {
            System.out.println("Valid input: " + num);
            break;  // Exit loop
        } else {
            System.out.println("Number must be greater than 0.");
        }
    }
}
```

**Flow:**
```
Enter: "abc"
  → hasNextInt() = false
  → Print error
  → Consume "abc"
  → Loop again

Enter: "-5"
  → hasNextInt() = true
  → Read -5
  → num > 0? = false
  → Print error
  → Loop again

Enter: "42"
  → hasNextInt() = true
  → Read 42
  → num > 0? = true
  → Print success
  → Break
```

**Key Takeaway:** Always validate user input; test both type and range

---

### **Exercise 10: chp3_10.java - Caesar Cipher Encryption**

**Problem:** Encrypt string using Caesar cipher (shift each letter)

**Concepts:**
- String iteration with charAt()
- Character manipulation
- Alphabet wrapping
- StringBuilder for efficiency

```java
public static String encryptString(String str, int shift) {
    StringBuilder res = new StringBuilder();

    for (int i = 0; i < str.length(); i++) {
        char c = str.charAt(i);
        
        if (Character.isLetter(c)) {
            // Find base (A for uppercase, a for lowercase)
            char base = Character.isUpperCase(c) ? 'A' : 'a';
            
            // Shift character with wrapping
            char encryptedChar = (char) (base + ((c - base + shift) % 26 + 26) % 26);
            res.append(encryptedChar);
        } else {
            // Keep non-letters as-is
            res.append(c);
        }
    }
    
    return res.toString();
}
```

**Example (shift = 2):**
```
Input:  "Hello World"
Output: "Jgnnq Yqtng"

H → J (shift 2)
e → g (shift 2)
l → n (shift 2)
l → n (shift 2)
o → q (shift 2)
  →   (space unchanged)
W → Y (shift 2)
etc.
```

**Wrap-Around Logic:**
```
Y + 2 = [A=0, B=1, ..., Y=24, Z=25]
Y = 24
(24 + 2) % 26 = 26 % 26 = 0 = A ✓

Z + 1 = 25 + 1 = 26 % 26 = 0 = A ✓
```

**Key Takeaway:** Character arithmetic is powerful; StringBuilder beats string concatenation

---

## Case Study: Projectile Motion

### **The Physics**

When you launch a projectile (ball, rocket, etc.) at an angle with initial velocity, it follows a parabolic path.

**Given:**
- Initial velocity (v) in m/s
- Launch angle (θ) in degrees
- Gravity (g) = 9.8 m/s²

**Find:**
- Time of flight (T): How long until it lands?
- Maximum height (H): How high does it go?
- Range (R): How far does it travel horizontally?

### **The Equations**

```
Time of Flight:    T = (2v × sin(θ)) / g

Maximum Height:    H = (v² × sin²(θ)) / (2g)

Range:             R = (v² × sin(2θ)) / g
```

**Where:**
- v = initial velocity (m/s)
- θ = angle in **radians** (must convert from degrees!)
- g = 9.8 m/s²

### **Critical Concept: Radians vs Degrees**

```
Degrees          Radians
0°               0
90°              π/2 ≈ 1.5708
180°             π ≈ 3.1416
360°             2π ≈ 6.2832

Conversion:      radians = degrees × (π/180)
                 degrees = radians × (180/π)

Java:            Math.toRadians(degrees)
                 Math.toDegrees(radians)
```

**Why?** Math.sin() and Math.cos() expect radians!

```java
Math.sin(45);               // ❌ Wrong! Returns sin(45 radians)
Math.sin(Math.toRadians(45));  // ✅ Correct! Returns sin(45°) ≈ 0.707
```

### **Code Structure**

```java
public class caseStudy {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean continueSim = true;

        while (continueSim) {
            // 1. INPUT
            double velocity = getInput("Enter initial velocity (m/s): ", input);
            double angle = getInput("Enter launch angle (degrees): ", input);

            // 2. CONVERT TO RADIANS
            double angleInRads = Math.toRadians(angle);

            // 3. CALCULATE
            double timeOfFlight = calcTimeOfFlight(velocity, angleInRads);
            double maxHeight = calcHeight(velocity, angleInRads);
            double range = calcRange(velocity, angleInRads);

            // 4. DISPLAY RESULTS
            displayResults(timeOfFlight, maxHeight, range);

            // 5. ASK TO CONTINUE
            continueSim = askToCont(input);
        }
        input.close();
    }

    // Get input from user
    public static double getInput(String prompt, Scanner input) {
        System.out.print(prompt);
        return input.nextDouble();
    }

    // Calculate time in air
    public static double calcTimeOfFlight(double velocity, double angleInRads) {
        return (2 * velocity * Math.sin(angleInRads)) / 9.8;
    }

    // Calculate maximum height
    public static double calcHeight(double velocity, double angleInRads) {
        return (Math.pow(velocity * Math.sin(angleInRads), 2)) / (2 * 9.8);
    }

    // Calculate horizontal distance
    public static double calcRange(double velocity, double angleInRads) {
        return (Math.pow(velocity, 2) * Math.sin(2 * angleInRads)) / 9.8;
    }

    // Display results nicely formatted
    public static void displayResults(double timeOfFlight, double maxHeight, double range) {
        System.out.printf("Time of Flight: %.2f seconds%n", timeOfFlight);
        System.out.printf("Maximum Height: %.2f meters%n", maxHeight);
        System.out.printf("Range: %.2f meters%n", range);
    }

    // Ask user to continue
    public static boolean askToCont(Scanner input) {
        System.out.print("Simulate another projectile? (yes/no): ");
        String response = input.next();
        return response.equalsIgnoreCase("yes");
    }
}
```

### **Example Calculation**

```
Input:
  Velocity: 50 m/s
  Angle: 45 degrees

Convert to radians:
  45° × (π/180) = 0.7854 radians

Calculate:
  T = (2 × 50 × sin(0.7854)) / 9.8
    = (100 × 0.7071) / 9.8
    = 70.71 / 9.8
    ≈ 7.22 seconds

  H = (50² × sin²(0.7854)) / (2 × 9.8)
    = (2500 × 0.5) / 19.6
    = 1250 / 19.6
    ≈ 63.78 meters

  R = (50² × sin(2 × 0.7854)) / 9.8
    = (2500 × sin(1.5708)) / 9.8
    = (2500 × 1.0) / 9.8
    ≈ 255.10 meters
```

### **Design Principles Applied**

1. **Modularity:** Each calculation in its own method
2. **Reusability:** getInput() works for any prompt
3. **Readability:** Method names explain what they do
4. **Separation of Concerns:** Input, calculation, output separate
5. **Return Values:** Methods return data instead of printing
6. **Constants:** Gravity (9.8) could be a constant for better practice

### **Potential Improvements**

```java
// Better: Use constant for gravity
public static final double GRAVITY = 9.8;

public static double calcTimeOfFlight(double velocity, double angleInRads) {
    return (2 * velocity * Math.sin(angleInRads)) / GRAVITY;
}

// Better: Validate input
public static double getInput(String prompt, Scanner input) {
    while (true) {
        System.out.print(prompt);
        if (input.hasNextDouble()) {
            double value = input.nextDouble();
            if (value > 0) return value;
            System.out.println("Please enter a positive number.");
        } else {
            input.next();  // Consume bad input
            System.out.println("Invalid input. Enter a number.");
        }
    }
}

// Better: Validate angle (0-90 degrees)
double angle = getInput("Enter launch angle (0-90 degrees): ", input);
if (angle < 0 || angle > 90) {
    System.out.println("Angle should be between 0 and 90 degrees.");
}
```

---

## Master Checklist

### **Chapter 3 Mastery Checklist**

- [ ] **Parameters vs Constants**
  - [ ] Understand parameters are destroyed after method call
  - [ ] Know when to use constants (unchanging values)
  - [ ] Can identify parameter vs constant in code

- [ ] **Method Overloading**
  - [ ] Understand methods can have same name, different parameters
  - [ ] Can identify overloaded methods by signature
  - [ ] Know the three things Java compares: number, type, order

- [ ] **Return Values**
  - [ ] Understand return sends value back to caller
  - [ ] Know why returning is better than printing
  - [ ] Can write methods that return values

- [ ] **The Math Class**
  - [ ] Can use Math.pow()
  - [ ] Can use Math.sqrt()
  - [ ] Can use Math.sin(), Math.cos() with radians
  - [ ] Can use Math.random()
  - [ ] Can use Math.toRadians() and Math.toDegrees()

- [ ] **Strings**
  - [ ] Understand strings are immutable
  - [ ] Understand strings are arrays of characters
  - [ ] Can use split(), charAt(), length()
  - [ ] Know difference between String and primitives

- [ ] **Scanner & Validation**
  - [ ] Can use Scanner to read input
  - [ ] Can validate input with hasNextInt(), hasNextDouble()
  - [ ] Can handle invalid input gracefully
  - [ ] Can use do-while loops for menus

- [ ] **All 10 Exercises**
  - [ ] chp3_1: Constants and parameters
  - [ ] chp3_2: Method overloading
  - [ ] chp3_3: Parameter validation
  - [ ] chp3_4: Multiple parameters and formulas
  - [ ] chp3_5: Return values and arrays
  - [ ] chp3_6: String manipulation
  - [ ] chp3_7: Scanner and menus
  - [ ] chp3_8: Math.pow() for calculations
  - [ ] chp3_9: Input validation
  - [ ] chp3_10: String and character manipulation

- [ ] **Case Study: Projectile Motion**
  - [ ] Understand the three equations
  - [ ] Can convert degrees to radians
  - [ ] Can structure program with helper methods
  - [ ] Can use Scanner for user input
  - [ ] Can display formatted output with printf

---

## Quick Reference

### **Common Patterns**

#### **Overloading Pattern**
```java
public static double process(int x) { ... }
public static double process(double x) { ... }
public static double process(int x, int y) { ... }
public static double process(double x, double y) { ... }
```

#### **Input Validation Pattern**
```java
while (true) {
    System.out.print("Prompt: ");
    if (!sc.hasNextType()) {
        System.out.println("Invalid");
        sc.next();
        continue;
    }
    Type value = sc.nextType();
    if (value < min || value > max) {
        System.out.println("Out of range");
        continue;
    }
    // Use value
    break;
}
```

#### **Menu Pattern**
```java
boolean running = true;
while (running) {
    System.out.println("1. Option 1");
    System.out.println("2. Option 2");
    int choice = sc.nextInt();
    
    switch (choice) {
        case 1: doOption1(); break;
        case 2: doOption2(); break;
        default: System.out.println("Invalid");
    }
    
    System.out.print("Continue? (yes/no): ");
    String ans = sc.next();
    running = ans.equalsIgnoreCase("yes");
}
```

#### **String Building Pattern (Use StringBuilder!)**
```java
// ❌ Slow
String result = "";
for (String word : words) {
    result += word + " ";
}

// ✅ Fast
StringBuilder result = new StringBuilder();
for (String word : words) {
    result.append(word).append(" ");
}
String finalString = result.toString();
```

#### **Mathematical Formula Pattern**
```java
// Break formula into steps
double numerator = Math.pow(velocity, 2) * Math.sin(2 * angle);
double denominator = GRAVITY;
double result = numerator / denominator;

// Or use intermediate variables
double vSquared = Math.pow(velocity, 2);
double sinTerm = Math.sin(2 * angle);
double range = (vSquared * sinTerm) / GRAVITY;
```

### **Key Methods to Remember**

```java
// Math Methods
Math.pow(base, exp)           // base^exp
Math.sqrt(x)                  // √x
Math.abs(x)                   // |x|
Math.sin(radians)             // sine
Math.cos(radians)             // cosine
Math.toRadians(degrees)       // convert degrees to radians
Math.round(x)                 // round to nearest integer

// String Methods
str.split(delimiter)          // split into array
str.charAt(index)             // character at position
str.length()                  // number of characters
str.equalsIgnoreCase(other)   // case-insensitive comparison
str.trim()                    // remove leading/trailing whitespace

// Scanner Methods
sc.nextInt()                  // read integer
sc.nextDouble()               // read decimal
sc.nextLine()                 // read entire line
sc.hasNextInt()               // is next input an integer?
sc.hasNextDouble()            // is next input a double?

// Character Methods
Character.isLetter(c)         // is character a letter?
Character.isDigit(c)          // is character a digit?
Character.isUpperCase(c)      // is uppercase?
Character.isLowerCase(c)      // is lowercase?

// StringBuilder Methods
sb.append(str)                // add to end
sb.toString()                 // convert to String
```

### **Number Conversions**

```java
// String to Number
int x = Integer.parseInt("42");          // "42" → 42
double d = Double.parseDouble("3.14");   // "3.14" → 3.14

// Number to String
String s1 = String.valueOf(42);          // 42 → "42"
String s2 = Integer.toString(42);        // 42 → "42"
String s3 = "" + 42;                     // 42 → "42"

// Formatted Strings
String.format("%.2f", 3.14159);          // "3.14"
System.out.printf("Value: %d%n", 42);    // "Value: 42"
```

---

**End of Study Guide**

*Use this guide to review, teach others, or prepare for tests. Good luck! 🎓*
