# Chapter 5: Loops and Input Validation - Complete Study Guide

> **Focus:** while loops, do-while loops, input validation, assertions, fencepost algorithms, and boolean control flow.

---

## 📖 Table of Contents

- [Short Answer Review](#short-answer-review)
- [Core Concepts Explained](#core-concepts-explained)
- [Exercise-by-Exercise Breakdown](#exercise-by-exercise-breakdown)
- [Master Checklist](#master-checklist)
- [Quick Reference](#quick-reference)

---

## Short Answer Review

### **Q1: When should you use a while loop instead of a for loop?**

Use a **while loop** when:
- You don't have a predetermined number of iterations
- The loop condition is validation-based (validation loops)
- You need flexibility in loop control
- The loop runs based on user input or external conditions

#### Comparison:

**For Loop** - When you know how many times to iterate:
```java
for (int i = 0; i < 10; i++) {
    // Exactly 10 iterations
}
```

**While Loop** - When you don't know iterations in advance:
```java
while (inputIsInvalid) {
    // Get input from user
    // Check if valid
    // Loop as many times as needed
}
```

#### Real-World Example:
```java
// For loop - process fixed array
for (int i = 0; i < array.length; i++) {
    process(array[i]);
}

// While loop - validate user input
while (!isValid) {
    System.out.println("Enter valid input:");
    input = scanner.nextInt();
    isValid = checkValidation(input);
}
```

---

### **Q2: What is the main difference between a while loop and a do-while loop in terms of execution?**

The key difference is **when the condition is checked**:

#### While Loop (Precondition):
- Checks condition **BEFORE** executing body
- May never execute if condition is false
- **Syntax:** `while (condition) { body }`

```java
int x = 5;
while (x < 3) {  // Condition false immediately
    System.out.println(x);  // Never executes
}
```

#### Do-While Loop (Postcondition):
- Executes body **AT LEAST ONCE**, then checks condition
- Always runs at least one iteration
- **Syntax:** `do { body } while (condition);`

```java
int x = 5;
do {
    System.out.println(x);  // Executes once
} while (x < 3);  // Then condition is checked
```

#### When to Use Do-While:
- **Menu systems** - show menu at least once
- **Input validation loops** - display prompt, get input, validate
- **Game loops** - play round, then check if continue
- **Repeating operations** - do something, then ask "again?"

#### Real Example:
```java
// Menu that always shows at least once
do {
    System.out.println("1. Play");
    System.out.println("2. Quit");
    choice = scanner.nextInt();
} while (choice != 2);

// vs While - might not show at all
while (shouldShowMenu) {
    System.out.println("1. Play");
    choice = scanner.nextInt();
}
```

---

### **Q3: What problem do fencepost algorithms solve when printing sequences, and how do they work?**

**Problem:** Avoiding **trailing separators** in output (extra comma at end, space at end, etc.)

#### Example of Problem:
```java
// WRONG - prints "1,2,3," with trailing comma
for (int i = 1; i <= 3; i++) {
    System.out.print(i + ",");
}
Output: 1,2,3,  ❌ (trailing comma)
```

#### Fencepost Algorithm Solution:
Print the **first element** outside the loop, then loop starting from second element:

```java
// CORRECT - fencepost pattern
System.out.print("1");
for (int i = 2; i <= 3; i++) {
    System.out.print("," + i);
}
Output: 1,2,3  ✅ (no trailing comma)
```

#### Why It's Called "Fencepost":
Imagine a fence: you need 10 fence posts for 9 spaces between them. Similarly:
- Element 1 (the post)
- Separator + Element 2 (post with fence to previous)
- Separator + Element 3 (post with fence to previous)
- etc.

#### Real Use Case - CSV Files:
```java
// Without fencepost - invalid CSV
1,2,3,
4,5,6,

// With fencepost - valid CSV
1,2,3
4,5,6
```

---

### **Q4: How would you modify a loop that prints numbers 1 to 10 with commas between them to avoid a trailing comma?**

Use the **fencepost algorithm**:

```java
// Print first number outside loop
System.out.print("1");

// Loop from 2 to 10, print with LEADING comma
for (int i = 2; i <= 10; i++) {
    System.out.print("," + i);
}

// Output: 1,2,3,4,5,6,7,8,9,10
```

#### Alternative Using Flag:
```java
boolean first = true;
for (int i = 1; i <= 10; i++) {
    if (!first) {
        System.out.print(",");  // Print comma before all but first
    }
    System.out.print(i);
    first = false;
}
```

#### Alternative Using Index Check:
```java
for (int i = 1; i <= 10; i++) {
    if (i != 1) {
        System.out.print(",");  // Print comma if not first
    }
    System.out.print(i);
}
```

---

### **Q5: What are the possible values of a boolean variable in Java, and how can it be used to control loops?**

A **boolean** variable can only have two values:
- `true` - condition is satisfied
- `false` - condition is not satisfied

#### Using Boolean to Control Loops:

**Flag-Controlled Loop:**
```java
boolean validInput = false;
while (!validInput) {
    System.out.println("Enter a number:");
    input = scanner.nextInt();
    
    if (input > 0) {
        validInput = true;  // Set flag to exit loop
    } else {
        System.out.println("Must be positive!");
    }
}
```

**Boolean Flag for Special Conditions:**
```java
boolean foundTarget = false;
while (!foundTarget) {
    int roll = dice.nextInt(6) + 1;
    if (roll == 6) {
        foundTarget = true;  // Exit when condition met
    }
}
```

**Multiple Conditions:**
```java
boolean isLoggedIn = false;
boolean hasCredentials = false;

while (!isLoggedIn && hasCredentials) {
    // Requires both conditions to enter loop
}
```

#### Key Point:
Booleans provide **explicit, readable control** over loop termination without relying on sentinel values or complex conditions.

---

### **Q6: How can combining multiple boolean expressions with && or || improve control over program flow?**

Boolean operators allow you to create **complex conditions** for precise control:

#### AND Operator (&&) - Both Must Be True:
```java
if (age >= 18 && licenseValid) {
    System.out.println("Can drive");  // Only if BOTH true
}
```

#### OR Operator (||) - At Least One Must Be True:
```java
if (isWeekend || isHoliday) {
    System.out.println("No work today");  // If EITHER true
}
```

#### Combining for Complex Logic:
```java
// Determine valid pair: 
// Both positive AND divisible by 3, OR one is exactly 100
if ((x > 0 && y > 0 && x % 3 == 0 && y % 3 == 0) || 
    (x == 100 || y == 100)) {
    System.out.println("Valid pair");
}
```

#### Short-Circuit Evaluation:
```java
// If first condition false, second not evaluated
if (array != null && array[0] > 5) {
    // Prevents NullPointerException
    // Second check only if first is true
}

// If first condition true, second not evaluated
if (x < 0 || y < 0) {
    return false;  // Stops at first true
}
```

#### Benefits:
- **Accuracy:** Test multiple conditions precisely
- **Efficiency:** Short-circuit evaluation saves computation
- **Readability:** Express complex logic clearly
- **Safety:** Prevent errors with null checks

---

### **Q7: What is a common technique to validate user input using a while loop?**

The **input validation pattern** with `Scanner`:

#### Basic Pattern:
```java
Scanner scanner = new Scanner(System.in);
int input = 0;
boolean valid = false;

while (!valid) {
    System.out.println("Enter a positive number:");
    
    if (scanner.hasNextInt()) {  // Check type first
        input = scanner.nextInt();
        if (input > 0) {  // Check value condition
            valid = true;
        } else {
            System.out.println("Must be positive!");
        }
    } else {
        System.out.println("Invalid input type!");
        scanner.next();  // Clear bad input
    }
}
```

#### Key Components:
1. **Type check:** `scanner.hasNextInt()`
2. **Value read:** `scanner.nextInt()`
3. **Validation check:** `if (value condition)`
4. **Error handling:** Clear input buffer with `scanner.next()`

#### Real Example:
```java
while (true) {
    if (scanner.hasNextInt()) {
        int num = scanner.nextInt();
        if (num % 7 == 0) {
            System.out.println("Valid: " + num);
            break;
        } else {
            System.out.println("Not divisible by 7!");
        }
    } else {
        System.out.println("Not an integer!");
        scanner.next();  // Clear bad input
    }
}
```

---

### **Q8: Why is Scanner.hasNextInt() useful when validating user input, and how does it prevent exceptions?**

`hasNextInt()` **checks if next input is an integer WITHOUT consuming it**.

#### Problem Without It:
```java
int input = scanner.nextInt();  // Throws InputMismatchException if user enters "abc"
```

#### Solution With It:
```java
if (scanner.hasNextInt()) {
    input = scanner.nextInt();  // Safe to read
} else {
    System.out.println("Invalid!");
    scanner.next();  // Clear the bad input
}
```

#### How It Prevents Exceptions:
1. **Checks type before reading** - doesn't consume input
2. **Prevents InputMismatchException** - only calls nextInt() if safe
3. **Allows error handling** - can clear bad input and re-prompt
4. **Graceful recovery** - program doesn't crash

#### Without hasNextInt() - Program Crashes:
```java
// If user enters "abc" when program expects integer
System.out.println("Enter age:");
int age = scanner.nextInt();  // CRASH! InputMismatchException
```

#### With hasNextInt() - Graceful Handling:
```java
System.out.println("Enter age:");
if (scanner.hasNextInt()) {
    int age = scanner.nextInt();  // Safe
} else {
    System.out.println("Age must be a number!");
    scanner.next();  // Remove bad input
    // Can loop and ask again
}
```

---

### **Q9: What happens when an assertion fails during runtime, and when should assertions be enabled?**

#### When Assertion Fails:
The program **aborts** (throws `AssertionError`) and displays the assertion message.

```java
assert x > 0 : "X must be positive";
// If x <= 0: AssertionError: X must be positive
// Program crashes with stack trace
```

#### Enabling Assertions:
Assertions are **disabled by default**. Enable with `-ea` flag:

```bash
javac MyProgram.java
java -ea MyProgram  # Enable assertions
```

#### When to Use Assertions:
- **During development/debugging** - catch logic errors early
- **Before critical operations** - validate preconditions
- **Data validation** - ensure data integrity

#### Good Uses:
```java
// Validate preconditions before complex calculation
public static double calculateBMI(double weight, double height) {
    assert weight > 0 : "Weight must be positive";
    assert height > 0 : "Height must be positive";
    return weight / (height * height);
}

// Verify array has expected size
assert scores.length >= 5 : "Must have at least 5 scores";

// Check invariants in complex algorithms
assert sortedArray[i] <= sortedArray[i + 1] : "Array not sorted";
```

---

### **Q10: Why is using assertions helpful during debugging but not recommended for handling user input or production errors?**

#### Assertions Are For **Programmer Errors**, Not User Errors:

**Programmer Error Example:**
```java
// Assertion - programmer shouldn't pass null
assert array != null : "Array cannot be null";
```

**User Error Example:**
```java
// NOT for user input - user might enter bad data
// assert input > 0;  // WRONG - user might enter negative
// Instead, use while loop to re-prompt
```

#### Why Assertions Fail for User Input:

1. **Assertions can be disabled** - in production, assertions are off
   ```bash
   java MyProgram  # Assertions disabled, invalid input not caught
   ```

2. **Poor user experience** - program crashes instead of graceful error
   ```
   ❌ AssertionError: Age must be positive
      Program crashes
   
   ✅ "Age must be positive. Try again."
      User can retry
   ```

3. **Production systems don't use them** - assertions are development tools
   ```
   Development: java -ea MyProgram
   Production:  java MyProgram  # No -ea flag, assertions off
   ```

#### Correct Approach:

**For User Input - Use Validation Loop:**
```java
boolean valid = false;
while (!valid) {
    System.out.println("Enter positive number:");
    if (scanner.hasNextInt()) {
        int input = scanner.nextInt();
        if (input > 0) {
            valid = true;
        } else {
            System.out.println("Must be positive!");
        }
    } else {
        System.out.println("Invalid type!");
        scanner.next();
    }
}
```

**For Programmer Logic - Use Assertions:**
```java
public static double divide(double a, double b) {
    assert b != 0 : "Divisor cannot be zero";  // Programmer error
    return a / b;
}
```

#### Key Distinction:
| Scenario | Tool | Why |
|----------|------|-----|
| User enters wrong type | `hasNextInt()` + while loop | Always active, user can retry |
| Array null (bug) | `assert array != null` | Development debugging |
| Invalid calculation | `assert result > 0` | Logic verification during dev |
| User age is negative | while loop with validation | Professional error handling |

---

## Core Concepts Explained

### **Loop Types Comparison**

```
┌─────────────────────────────────────────┐
│ FOR LOOP - Known iterations             │
├─────────────────────────────────────────┤
│ for (int i = 0; i < n; i++) {          │
│     // Fixed number of iterations       │
│ }                                       │
│ ✓ Clear beginning and end               │
│ ✓ Counter variable handled              │
│ ✗ Not for validation/input              │
└─────────────────────────────────────────┘

┌─────────────────────────────────────────┐
│ WHILE LOOP - Unknown iterations         │
├─────────────────────────────────────────┤
│ while (condition) {                     │
│     // May never execute                │
│ }                                       │
│ ✓ Great for validation                  │
│ ✓ Flexible condition                    │
│ ✗ Might not run at all                  │
└─────────────────────────────────────────┘

┌─────────────────────────────────────────┐
│ DO-WHILE LOOP - At least once           │
├─────────────────────────────────────────┤
│ do {                                    │
│     // Always runs at least once        │
│ } while (condition);                    │
│ ✓ Guaranteed to execute                 │
│ ✓ Good for menus                        │
│ ✗ Condition checked after               │
└─────────────────────────────────────────┘
```

---

### **Input Validation Flow**

```
User Input
    ↓
Has Next Type? (hasNextInt(), hasNextLine())
    ↓ YES          ↓ NO
Read Input     Clear Buffer (next())
    ↓           ↓
Check Value   Re-prompt User
    ↓           ↑
Valid? → Continue or Loop Back
```

---

### **Fencepost Algorithm Pattern**

```
Problem:   1,2,3,  ← trailing comma
Solution:  1,2,3   ← clean

Pattern:
1. Print first element (no separator)
2. Loop from second element
3. Print separator + element
```

---

## Exercise-by-Exercise Breakdown

### **Exercise 1: chpt5_1.java - While Loop with Input Validation**

**Problem:** Prompt user for a number divisible by 7. Keep prompting until valid.

**Solution:**
```java
Scanner sc = new Scanner(System.in);
int input = 0;

while (true) {
    if (sc.hasNextInt()) {
        input = sc.nextInt();
        if (input % 7 == 0) {
            System.out.println("Valid number: " + input);
            break;
        } else {
            System.out.println(input + " is not divisible by 7. Try again.");
        }
    } else {
        System.out.println("That is not valid integer. Try again");
        sc.next();  // Clear bad input
    }
}
```

**Key Points:**
- **Type validation first:** `hasNextInt()` checks before reading
- **Value validation second:** `input % 7 == 0` checks divisibility
- **Clear buffer:** `sc.next()` removes non-integer input
- **Loop until valid:** `while(true)` with explicit `break`

**Test Cases:**
```
Input: abc       → "That is not valid integer. Try again"
Input: 14        → "Valid number: 14"
Input: 15        → "15 is not divisible by 7. Try again"
Input: 7         → "Valid number: 7"
```

**Complexity Analysis:**
- Time: O(n) - worst case n iterations
- Space: O(1) - fixed variables

---

### **Exercise 2: chpt5_2.java - Fencepost Algorithm**

**Problem:** Print numbers 10 to 1 separated by " | " with no trailing separator.

**Solution:**
```java
System.out.print("10");
for (int i = 1; i < 10; i++) {
    System.out.print(" | ");
    int inx = 10 - i;
    System.out.print(inx + "");
}
```

**Output:** `10 | 9 | 8 | 7 | 6 | 5 | 4 | 3 | 2 | 1`

**How It Works:**
```
Print "10" outside loop (no separator)
i=1: Print " | ", then 10-1=9
i=2: Print " | ", then 10-2=8
...
i=9: Print " | ", then 10-9=1
No trailing separator!
```

**Key Points:**
- **Fencepost pattern:** First element outside loop
- **Separator first:** Leading separator (not trailing)
- **Index calculation:** `10 - i` reverses the count
- **No reverse loop:** Uses forward loop with reverse calculation

**Complexity Analysis:**
- Time: O(1) - fixed 9 iterations
- Space: O(1) - no new structures

---

### **Exercise 3: Chpt5_3.java - Boolean Control Flow**

**Problem:** Login system with 3 attempts, password is "secret".

**Solution:**
```java
Scanner user = new Scanner(System.in);
int attempts = 0;
String password = "secret";
String input = "";

System.out.println("Guess the password. You have 3 attempts.");
while (attempts < 3) {
    if (user.hasNextLine()) {
        input = user.nextLine();
        
        if (input.equals(password)) {
            System.out.println("Access Granted");
            break;
        } else {
            System.out.println("Access Denied. Try again.");
            attempts++;
        }
    }
}
```

**Key Points:**
- **Counter-based loop:** `while (attempts < 3)`
- **String comparison:** `.equals()` not `==`
- **Early exit:** `break` when password correct
- **Increment attempts:** Only on wrong password

**Test Cases:**
```
Attempt 1: secret       → Access Granted (break)
Attempt 1: wrong        → Access Denied. Try again.
Attempt 2: wrong        → Access Denied. Try again.
Attempt 3: wrong        → Loop exits, access denied
```

**Complexity Analysis:**
- Time: O(1) - max 3 iterations
- Space: O(1) - fixed variables

---

### **Exercise 4: chpt5_4.java - Assertion Guard**

**Problem:** Create `safeDivide(double a, double b)` with assertion for b ≠ 0.

**Solution:**
```java
public static double safeDivide(double a, double b) {
    assert b != 0 : "Divider b must not be zero";
    return a / b;
}
```

**Usage:**
```java
System.out.println(safeDivide(2, 2));   // 1.0 - OK
System.out.println(safeDivide(2, 0));   // AssertionError (if -ea flag used)
```

**Key Points:**
- **Assertion syntax:** `assert condition : "message";`
- **Enable with flag:** `java -ea ClassName`
- **For programmer errors:** Not for user input validation
- **Aborts on failure:** AssertionError stops program

**Complexity Analysis:**
- Time: O(1) - fixed calculation
- Space: O(1) - returns single double

---

### **Exercise 5: chpt5_5.java - Do-While Menu System**

**Problem:** Menu with options (1=Hello, 2=Goodbye, 3=Exit), loop until exit.

**Solution:**
```java
Scanner input = new Scanner(System.in);
int opt = 0;

do {
    System.out.println("Select: ");
    System.out.print("1. Say Hello.\n2. Say Goodbye.\n3. Exit.\n");
    
    if (!input.hasNextInt()) {
        System.out.println("Please enter an integer.");
        input.next();
        continue;
    }
    
    opt = input.nextInt();
    
    if (opt == 1) {
        System.out.println("Hello");
    } else if (opt == 2) {
        System.out.println("Goodbye");
    } else if (opt == 3) {
        System.out.println("Exiting");
    } else {
        System.out.println("Invalid input.");
    }
    
} while (opt != 3);
```

**Key Points:**
- **Do-while guarantees execution:** Menu displays at least once
- **Input validation:** Check type before reading
- **Continue statement:** Skip to next iteration on bad input
- **Exit condition:** Loop ends when opt == 3

**Menu Flow:**
```
Display menu
↓
Get input → Invalid type → show error, continue
↓
Valid type → Read input
↓
Check value → Execute action or show invalid
↓
Check condition → If opt != 3, repeat menu
```

**Complexity Analysis:**
- Time: O(n) - depends on user selections
- Space: O(1) - fixed variables

---

### **Exercise 6: chpt5_6.java - Complex Boolean Logic**

**Problem:** Check if valid pair: (both positive AND divisible by 3) OR (one is 100).

**Solution:**
```java
public static String validPair(int x, int y) {
    if ((x % 3 == 0) && (y % 3 == 0) && (x > -1 && y > -1) || 
        (x == 100 || y == 100)) {
        return "Valid pair";
    }
    return "Not valid";
}
```

**Condition Breakdown:**
```
Part 1: (x % 3 == 0) && (y % 3 == 0) && (x > -1 && y > -1)
        Both divisible by 3 AND both positive

OR

Part 2: (x == 100 || y == 100)
        One of them is exactly 100
```

**Test Cases:**
```java
validPair(3, 6)      // "Valid pair" (both div by 3, positive)
validPair(100, 47)   // "Valid pair" (one is 100)
validPair(4, 9)      // "Not valid" (4 not div by 3)
validPair(-3, -9)    // "Not valid" (negative)
validPair(1, 10)     // "Not valid" (neither condition met)
```

**Complexity Analysis:**
- Time: O(1) - fixed comparisons
- Space: O(1) - no new structures

---

### **Exercise 7: chpt5_7.java - Input Validation with hasNextInt()**

**Problem:** Prompt for even number, validate type and value.

**Solution:**
```java
Scanner user = new Scanner(System.in);
int input;
boolean trigger = false;

System.out.println("Please enter an even integer.");
while (!trigger) {
    if (!user.hasNextInt()) {
        System.out.println("Not an integer. Try again.");
        user.next();
        continue;
    }
    
    input = user.nextInt();
    if (input % 2 != 0) {
        System.out.println("Not even integer. Try again");
    } else {
        System.out.println("Number valid");
        trigger = true;
    }
}
```

**Validation Steps:**
1. Check type with `hasNextInt()`
2. Clear buffer if bad type with `user.next()`
3. Read value if type valid
4. Check if even with `input % 2 == 0`
5. Set flag when both checks pass

**Test Cases:**
```
Input: abc    → "Not an integer. Try again."
Input: 5      → "Not even integer. Try again"
Input: 10     → "Number valid" (trigger = true, exit)
```

**Complexity Analysis:**
- Time: O(n) - depends on user input iterations
- Space: O(1) - fixed variables

---

### **Exercise 8: chpt5_8.java - Assertion with Loop**

**Problem:** Print array values, but assert array length >= 5.

**Solution:**
```java
int[] scores = { 100, 50, 25 };

assert scores.length >= 5 : "Array must have at least 5 values.";

for (int i = 0; i < scores.length; i++) {
    System.out.println(scores[i]);
}
```

**Run Command:**
```bash
javac chpt5_8.java
java -ea chpt5_8  # Enable assertions
```

**Key Points:**
- **Assertion checks precondition** before loop
- **Program aborts if assertion fails** (when -ea flag used)
- **Run without -ea flag** to skip assertion and run normally
- **Use for data validation** before critical operations

**Test Cases:**
```
With array of 3 elements:
java -ea chpt5_8     → AssertionError: Array must have at least 5 values
java chpt5_8         → Prints: 100, 50, 25 (assertion disabled)

With array of 5+ elements:
java -ea chpt5_8     → Prints all values normally
```

**Complexity Analysis:**
- Time: O(n) - loops through array length
- Space: O(n) - array is pre-created

---

### **Exercise 9: chpt5_9.java - Fencepost with User Input**

**Problem:** Get 5 words from user, print on one line separated by spaces (no trailing space).

**Solution:**
```java
Scanner user = new Scanner(System.in);
int count = 0;
String output = "";

while (count < 5) {
    if (count > 0) {
        output += " ";  // Add leading space (not trailing)
    }
    System.out.println("Please enter 5 words: ");
    output += user.nextLine();
    count++;
}
System.out.print(output);
user.close();
```

**How It Works:**
```
count=0: No separator added, read word 1
count=1: Add " ", read word 2
count=2: Add " ", read word 3
count=3: Add " ", read word 4
count=4: Add " ", read word 5
Result: word1 word2 word3 word4 word5 (no trailing space)
```

**Key Points:**
- **Leading separator pattern:** Add space before words (except first)
- **Condition check:** `if (count > 0)` before adding space
- **Fencepost applied to input:** Not just printing

**Complexity Analysis:**
- Time: O(n) - 5 iterations plus string concatenation
- Space: O(n) - output string grows with input

---

### **Exercise 10: chpt5_10.java - Boolean Flag with Random**

**Problem:** Dice game - roll until you get 6, count rolls.

**Solution:**
```java
boolean rolledSix = false;
Random dice = new Random();
int roll = dice.nextInt(6) + 1;
int rolls = 0;

while (!rolledSix) {
    if (roll == 6) {
        System.out.println("You rolled a " + roll + " Game over. You had " + rolls + " rolls.");
        rolledSix = true;
        break;
    }
    System.out.println("You rolled a " + roll);
    roll = dice.nextInt(6) + 1;
    rolls++;
}
```

**Key Points:**
- **Boolean flag controls loop:** `while (!rolledSix)`
- **Roll counter:** Track iterations
- **Break on success:** Exit early when goal reached
- **Random range:** `nextInt(6) + 1` gives 1-6

**Typical Output:**
```
You rolled a 3
You rolled a 1
You rolled a 5
You rolled a 2
You rolled a 6 Game over. You had 4 rolls.
```

**Complexity Analysis:**
- Time: O(n) - average ~6 iterations to roll a 6
- Space: O(1) - fixed variables

---

## Case Study: Number Guessing Game

The `numGuess.java` program demonstrates chapter concepts:

```java
Random rand = new Random();
int target = rand.nextInt(100) + 1;
Scanner sc = new Scanner(System.in);
int guess = -1;
int tries = 0;

while (guess != target) {
    System.out.println("Guess the number (1 - 100): ");
    guess = sc.nextInt();
    tries++;
    
    if (guess < 1 || guess > 100) {
        System.out.println("Please guess a number between 1 and 100.");
    } else if (guess < target) {
        System.out.println("Too low! Try again!");
    } else if (guess > target) {
        System.out.println("Too high! Guess again!");
    } else {
        System.out.println("Correct!!! It took you " + tries + " tries.");
    }
}
```

**Concepts Used:**
- **Random number generation**
- **While loop with condition** (guess != target)
- **Input validation** (1-100 range check)
- **Conditional logic** (if/else if chain)
- **Counter** (tries)

---

## Master Checklist

### **Chapter 5 Mastery Checklist**

#### While Loops
- [ ] Understand when to use while over for
- [ ] Recognize validation loop pattern
- [ ] Use sentinel values (starting values)
- [ ] Handle loop condition properly
- [ ] Know loops may never execute

#### Do-While Loops
- [ ] Understand postcondition execution
- [ ] Know loop executes at least once
- [ ] Use for menus and initial prompts
- [ ] Recognize when do-while is better

#### Input Validation
- [ ] Check type first with hasNextInt(), hasNextLine()
- [ ] Clear buffer with scanner.next()
- [ ] Check value conditions after reading
- [ ] Use continue to skip to next iteration
- [ ] Implement proper error messages

#### Boolean Control
- [ ] Use boolean flags to control loops
- [ ] Combine conditions with && and ||
- [ ] Understand short-circuit evaluation
- [ ] Apply DeMorgan's laws if needed

#### Fencepost Algorithm
- [ ] Recognize trailing separator problem
- [ ] Implement first-outside pattern
- [ ] Use leading separators in loop
- [ ] Apply to both printing and input

#### Assertions
- [ ] Enable with -ea flag
- [ ] Use for programmer errors only
- [ ] Never rely on assertions for user input
- [ ] Include meaningful assertion messages
- [ ] Understand assertions can be disabled

#### Complexity Analysis
- [ ] Analyze loops based on iteration count
- [ ] Distinguish O(1) from O(n)
- [ ] Consider both time and space complexity
- [ ] Include string concatenation impact

---

## Quick Reference

### **Common Patterns**

#### **Input Validation Pattern**
```java
Scanner scanner = new Scanner(System.in);
boolean valid = false;

while (!valid) {
    System.out.println("Enter input: ");
    
    if (scanner.hasNextInt()) {
        int input = scanner.nextInt();
        if (checkCondition(input)) {
            valid = true;
        } else {
            System.out.println("Invalid value!");
        }
    } else {
        System.out.println("Invalid type!");
        scanner.next();
    }
}
```

#### **Do-While Menu Pattern**
```java
int choice = 0;
do {
    System.out.println("1. Option A");
    System.out.println("2. Option B");
    System.out.println("3. Exit");
    
    choice = scanner.nextInt();
    
    switch(choice) {
        case 1: // handle A
        case 2: // handle B
        case 3: System.out.println("Exiting");
    }
} while (choice != 3);
```

#### **Fencepost Algorithm Pattern**
```java
System.out.print(firstElement);
for (int i = 1; i < total; i++) {
    System.out.print(separator + elements[i]);
}
```

#### **Boolean Flag Pattern**
```java
boolean goalReached = false;
while (!goalReached) {
    // Do work
    if (conditionMet()) {
        goalReached = true;
        break;  // Optional, depending on design
    }
}
```

#### **Random Number Pattern**
```java
Random rand = new Random();
int randomNum = rand.nextInt(max) + min;  // Range: [min, max)
// Example: rand.nextInt(6) + 1 gives [1, 6]
```

### **Key Methods**

#### **Scanner Methods**
```java
scanner.hasNextInt()          // Check if next is int
scanner.hasNextLine()         // Check if next line exists
scanner.nextInt()             // Read integer
scanner.nextLine()            // Read entire line
scanner.nextDouble()          // Read double
scanner.next()                // Read word (clears buffer)
```

#### **String Methods**
```java
str.equals(other)             // Compare strings
str.equalsIgnoreCase(other)   // Case-insensitive comparison
str.length()                  // Length of string
str.charAt(index)             // Character at index
str.contains(substring)       // Check if contains
str.trim()                    // Remove leading/trailing spaces
```

#### **Random Methods**
```java
rand.nextInt(n)               // Random 0 to n-1
rand.nextDouble()             // Random 0.0 to 1.0
rand.nextBoolean()            // Random true or false
```

### **Loop Comparison Table**

| Feature | For | While | Do-While |
|---------|-----|-------|----------|
| **Iterations known** | ✓ | ✗ | ✗ |
| **Runs at least once** | ✗ | ✗ | ✓ |
| **Good for validation** | ✗ | ✓ | ✓ |
| **Counter auto-managed** | ✓ | ✗ | ✗ |
| **Condition checked** | Start | Start | End |

### **Boolean Operator Truth Tables**

**AND (&&)**
| A | B | A && B |
|---|---|--------|
| T | T | T |
| T | F | F |
| F | T | F |
| F | F | F |

**OR (||)**
| A | B | A \|\| B |
|---|---|----------|
| T | T | T |
| T | F | T |
| F | T | T |
| F | F | F |

---

## Key Takeaways

1. **While loops** handle unknown iteration counts and validation
2. **Do-while loops** guarantee at least one execution (perfect for menus)
3. **Input validation pattern** checks type, then value, with buffer clearing
4. **Fencepost algorithm** solves trailing separator problem elegantly
5. **hasNextInt()** prevents InputMismatchException crashes
6. **Boolean flags** provide readable loop control
7. **Complex conditions** with && and || enable precise logic control
8. **Assertions** are debugging tools, not user input handlers
9. **Random numbers** need proper range adjustment (0-5 needs +1 for 1-6)
10. **Short-circuit evaluation** makes && and || efficient

---

**Last Updated:** 2026-04-26  
**Author:** Study Guide Generator  
**Reference:** Chapter 5 Programming Exercises and Short Answer Questions
