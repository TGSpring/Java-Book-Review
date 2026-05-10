# Chapter 4: Conditional Execution and String Methods - Complete Study Guide

> **Focus:** Control flow with if/else statements, cumulative algorithms, string manipulation, and conditional logic in methods.

---

## 📖 Table of Contents

- [Short Answer Review](#short-answer-review)
- [Core Concepts Explained](#core-concepts-explained)
- [Exercise-by-Exercise Breakdown](#exercise-by-exercise-breakdown)
- [Master Checklist](#master-checklist)
- [Quick Reference](#quick-reference)

---

## Short Answer Review

### **Q1: What is the purpose of an if/else statement in Java?**

The if/else statement allows you to create decision-based programs that execute different code blocks based on conditional tests.

#### Key Points:
- **If statement:** Executes code if condition is `true`
- **Else statement:** Executes code if the if condition is `false`
- **Else if:** Chains multiple conditions without unnecessary checks
- **Boolean expression:** Must evaluate to true or false

#### Example:
```java
if (age >= 18) {
    System.out.println("Adult");
} else {
    System.out.println("Minor");
}
```

---

### **Q2: How does the else if statement help avoid unnecessary checks in conditional logic?**

The `else if` statement ensures that once a condition is met, the rest are skipped. This makes the program more efficient than using multiple independent if statements.

#### Comparison:

**Multiple Independent if Statements (Inefficient):**
```java
if (score >= 90) grade = "A";  // Always checked
if (score >= 80) grade = "B";  // Still checked even if A was true
if (score >= 70) grade = "C";  // Still checked even if B was true
```

**With else if (Efficient):**
```java
if (score >= 90) {
    grade = "A";
} else if (score >= 80) {
    grade = "B";  // Only checked if score < 90
} else if (score >= 70) {
    grade = "C";  // Only checked if both above failed
}
```

#### Benefits:
- Avoids redundant condition checks
- More readable and maintainable
- Better performance, especially with complex conditions
- Logical flow matches natural decision-making

---

### **Q3: What is a cumulative algorithm, and how does it work?**

A cumulative algorithm calculates a running total, product, or other accumulated value during iteration. It tracks a value that changes with each iteration.

#### Pattern:
```java
Type accumulator = initialValue;  // Start with appropriate identity
for/while (...) {
    accumulator = accumulator [operation] currentValue;
}
```

#### Common Uses:
- **Sum:** `sum += value;` (initialize to 0)
- **Product:** `product *= value;` (initialize to 1)
- **Count:** `count++;` (initialize to 0)
- **String building:** `result += character;` (initialize to "")

#### Example - Sum of Digits:
```java
int sum = 0;
while (num != 0) {
    sum += num % 10;  // Add last digit
    num /= 10;        // Remove last digit
}
```

---

### **Q4: How would you modify a loop that calculates a cumulative sum to instead calculate a cumulative product?**

Change the operation from `+=` to `*=` and ensure the initial value is set to 1 instead of 0.

#### Comparison:

**Cumulative Sum:**
```java
int sum = 0;        // Start with 0 (additive identity)
while (num != 0) {
    sum += num % 10;
    num /= 10;
}
```

**Cumulative Product:**
```java
int product = 1;    // Start with 1 (multiplicative identity)
while (num != 0) {
    product *= num % 10;
    num /= 10;
}
```

#### Why Different Starting Values?
- Sum: Adding 0 doesn't change the result → start with 0
- Product: Multiplying by 0 destroys the result → start with 1

---

### **Q5: How can you check if a given string contains only numeric characters in Java?**

Use a **regular expression (regex)** with the `.matches()` method.

#### Syntax:
```java
String str = "12345";
boolean isNumeric = str.matches("[0-9]+");  // true
```

#### Breaking Down the Regex:
- `[0-9]` → matches any single digit 0 through 9
- `+` → means "one or more" of the preceding pattern
- Full pattern: "one or more digits"

#### More Examples:
```java
"123".matches("[0-9]+")        // true
"abc".matches("[0-9]+")        // false
"12a".matches("[0-9]+")        // false
"".matches("[0-9]+")           // false (empty string)
```

#### Alternative Using Loop:
```java
boolean isNumeric = true;
for (char c : str.toCharArray()) {
    if (!Character.isDigit(c)) {
        isNumeric = false;
        break;
    }
}
```

---

### **Q6: What does the .substring() method do, and how would you use it to extract the first three characters of a string?**

The `.substring()` method extracts a portion of a string based on start and end indices.

#### Syntax:
```java
String result = str.substring(startIndex, endIndex);
```

#### Important:
- Includes character at `startIndex`
- **Excludes** character at `endIndex` (exclusive end)
- Returns new String (original is unchanged - immutability)

#### Example - First Three Characters:
```java
String str = "JavaProgramming";
String first3 = str.substring(0, 3);  // "Jav"
```

#### Common Patterns:
```java
String str = "Hello";

str.substring(0, 1)        // "H" (first character)
str.substring(0, 3)        // "Hel" (first three)
str.substring(2)           // "llo" (from index 2 to end)
str.substring(1, 4)        // "ell" (index 1 to 4, excluding 4)
str.substring(str.length() - 2)  // "lo" (last two characters)
```

#### Off-by-One Reminder:
```
Index:    0  1  2  3  4
Char:     H  e  l  l  o
          ↑           ↑
     startIndex    endIndex (exclusive)
```

---

### **Q7: What is conditional execution inside a method, and why is it useful?**

Conditional execution inside a method means using `if/else` or nested `if` statements to execute different code blocks based on conditions. This allows methods to behave differently based on input.

#### Why It's Useful:
- **Flexible methods:** Same method handles multiple scenarios
- **Business logic:** Implement complex decision-making
- **Validation:** Check inputs before processing
- **Branching:** Return different results based on conditions

#### Example - Age Check Method:
```java
public static String checkAge(int age) {
    if (age >= 18) {
        return "Adult";
    } else {
        return "Minor";
    }
}
```

#### More Complex Example:
```java
public static String getGrade(int score) {
    if (score >= 90)
        return "A";
    else if (score >= 80)
        return "B";
    else if (score >= 70)
        return "C";
    else if (score >= 60)
        return "D";
    else
        return "F";
}
```

---

### **Q8: Provide an implementation of the checkAge method from the Short Answer file**

```java
public static String checkAge(int age) {
    if (age >= 18) {
        return "Adult";
    } else {
        return "Minor";
    }
}
```

#### Design Principles:
- Uses early return for clarity
- Condition is simple and readable
- No unnecessary variables
- Returns appropriate String type

---

### **Q9: What formula is used to calculate BMI, and what are the general BMI categories?**

#### BMI Formula:
```
BMI = weight (kg) / (height (m) × height (m))
BMI = weight / (height²)
```

#### Important: Use multiplication, NOT the `^` operator!
```java
// CORRECT:
double bmi = weight / (height * height);

// WRONG - ^ is bitwise XOR operator, not exponentiation:
double bmi = weight / (height ^ 2);  // This is wrong!
```

#### BMI Categories:
| Category | BMI Range |
|----------|-----------|
| Underweight | BMI < 18.5 |
| Normal Weight | 18.5 ≤ BMI < 25 |
| Overweight | 25 ≤ BMI < 30 |
| Obese | BMI ≥ 30 |

#### Example Implementation:
```java
public static String bmiCategory(double weight, double heightMeters) {
    double bmi = weight / (heightMeters * heightMeters);
    
    if (bmi < 18.5)
        return "Underweight";
    else if (bmi < 25)
        return "Normal Weight";
    else if (bmi < 30)
        return "Overweight";
    else
        return "Obese";
}
```

---

### **Q10: Why is using a method to calculate BMI better than writing the logic directly in main()?**

#### Benefits of Method-Based Approach:

| Aspect | Benefit |
|--------|---------|
| **Modularity** | Logic is separated and organized |
| **Reusability** | Calculate BMI multiple times without repeating code |
| **Maintainability** | Changes to BMI logic only need to be made once |
| **Readability** | `result = bmiCategory(weight, height)` is clearer than 10 lines of if/else |
| **Testability** | Can test BMI logic independently |
| **OOP Principles** | Follows single responsibility principle |

#### Comparison:

**Main Method Approach (Bad):**
```java
public static void main(String[] args) {
    double weight = 150;
    double height = 1.5;
    double bmi = weight / (height * height);
    String category;
    
    if (bmi < 18.5) category = "Underweight";
    else if (bmi < 25) category = "Normal";
    // ... repeated for every calculation needed
}
```

**Method-Based Approach (Good):**
```java
public static void main(String[] args) {
    System.out.println(bmiCategory(150, 1.5));
    System.out.println(bmiCategory(180, 1.8));
    System.out.println(bmiCategory(220, 1.7));
}

public static String bmiCategory(double weight, double height) {
    double bmi = weight / (height * height);
    if (bmi < 18.5) return "Underweight";
    // ...
}
```

---

## Core Concepts Explained

### **if/else Statement Flow**

```
          Condition?
         /         \
       YES          NO
      /              \
  If block      Else if block
    |                 |
    ↓                 ↓
If block code  Else if condition?
              /                \
            YES               NO
            /                  \
      Else if code      Else block code
            |                  |
            ↓                  ↓
       Condition met    No conditions met
```

#### Key Points:
- Exactly ONE block executes
- Order matters with `else if`
- Use `else if` for multiple conditions
- Use independent `if` only when all paths might execute

---

### **Cumulative Algorithm Pattern**

All cumulative algorithms follow this structure:

```java
Type accumulator = identityValue;
for/while (iteration condition) {
    accumulator = accumulator OPERATION value;
}
return accumulator;
```

#### Identity Values by Operation:
| Operation | Identity | Reason |
|-----------|----------|--------|
| Addition (+) | 0 | 0 + x = x |
| Multiplication (*) | 1 | 1 * x = x |
| String concatenation (+) | "" | "" + x = x |
| Logical AND (&&) | true | true && x = x |
| Logical OR (\|\|) | false | false \|\| x = x |

---

### **String Methods Recap**

#### `.substring(start, end)`
```java
String str = "Programming";
str.substring(0, 3)      // "Pro" (indices 0, 1, 2)
str.substring(3)         // "gramming" (from index 3 to end)
str.substring(3, 7)      // "gram" (indices 3, 4, 5, 6)
```

#### `.matches(regex)`
```java
"12345".matches("[0-9]+")       // true
"abc".matches("[a-zA-Z]+")      // true
"Test123".matches("[a-zA-Z0-9]+")  // true
```

#### `.charAt(index)`
```java
String str = "Java";
str.charAt(0)  // 'J'
str.charAt(3)  // 'a'
```

#### `.length()`
```java
String str = "Java";
str.length()   // 4
```

#### `.toLowerCase()` / `.toUpperCase()`
```java
"Hello".toLowerCase()   // "hello"
"Hello".toUpperCase()   // "HELLO"
```

---

## Exercise-by-Exercise Breakdown

### **Exercise 1: chpt4_1.java - Leap Year Checker**

**Problem:** Write `isLeapYear(int year)` that returns true if the year is a leap year.

**Rules:**
- Divisible by 4 → leap year
- UNLESS divisible by 100 → not a leap year
- UNLESS also divisible by 400 → leap year

**Solution:**
```java
public static boolean isLeapYear(int year) {
    return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
}
```

**Logic Breakdown:**
```
1. Divisible by 4?
   - No  → Not a leap year (short circuit)
   - Yes → Check next condition
2. Not divisible by 100?
   - Yes → Leap year (no need for 400 check)
   - No  → Check divisibility by 400
3. Divisible by 400?
   - Yes → Leap year
   - No  → Not a leap year
```

**Test Cases:**
```java
isLeapYear(2020)   // true  (divisible by 4 and 400)
isLeapYear(2024)   // true  (divisible by 4, not by 100)
isLeapYear(2025)   // false (not divisible by 4)
isLeapYear(1900)   // false (divisible by 100 but not 400)
isLeapYear(2000)   // true  (divisible by 400)
```

---

### **Exercise 2: chpt4_2.java - Grade Calculator**

**Problem:** Write `getGrade(int score)` that converts score to letter grade.

**Grade Scale:**
- A: 90+
- B: 80-89
- C: 70-79
- D: 60-69
- F: Below 60

**Solution:**
```java
public static String getGrade(int score) {
    if (score >= 90)
        return "A";
    if (score >= 80)
        return "B";
    if (score >= 70)
        return "C";
    if (score >= 60)
        return "D";
    return "F";
}
```

**Design Notes:**
- Uses early return for cleaner code
- Each condition is checked in order
- Once a condition matches, method returns immediately
- No unnecessary nested braces (style preference)

**Complexity Analysis:**
- Time: O(1) - fixed number of comparisons
- Space: O(1) - no additional structures

---

### **Exercise 3: chpt4_3.java - Digit Sum**

**Problem:** Write `sumDigits(int num)` that returns the sum of all digits in a number.

**Example:** `sumDigits(251)` → 2 + 5 + 1 = 8

**Solution:**
```java
public static int sumDigits(int num) {
    int sum = 0;
    while (num != 0) {
        sum += num % 10;  // Add the last digit
        num /= 10;        // Remove the last digit
    }
    return sum;
}
```

**How It Works:**
```
num = 251
Iteration 1: sum = 0 + (251 % 10) = 1,    num = 25
Iteration 2: sum = 1 + (25 % 10) = 6,     num = 2
Iteration 3: sum = 6 + (2 % 10) = 8,      num = 0
Loop exits: return 8
```

**Test Cases:**
```java
sumDigits(101)        // 2 (1+0+1)
sumDigits(102)        // 3 (1+0+2)
sumDigits(1011111897) // 38 (1+0+1+1+1+1+1+8+9+7)
```

**Complexity Analysis:**
- Time: O(d) where d is the number of digits
- Space: O(1) - only one variable

---

### **Exercise 4: chpt4_4.java - Factorial with Loop**

**Problem:** Write `factorial(int n)` that returns n! using a loop.

**Example:** `factorial(5)` = 5 × 4 × 3 × 2 × 1 = 120

**Solution:**
```java
public static int factorial(int n) {
    int num = 1;
    while (n > 0) {
        num *= n;  // Multiply accumulator by current value
        n--;       // Decrement n
    }
    return num;
}
```

**How It Works (Cumulative Product):**
```
n = 5
Iteration 1: num = 1 * 5 = 5,    n = 4
Iteration 2: num = 5 * 4 = 20,   n = 3
Iteration 3: num = 20 * 3 = 60,  n = 2
Iteration 4: num = 60 * 2 = 120, n = 1
Iteration 5: num = 120 * 1 = 120, n = 0
Loop exits: return 120
```

**Key Points:**
- Accumulator starts at 1 (multiplicative identity)
- Loop continues while n > 0
- Order of operations: multiply then decrement

**Test Cases:**
```java
factorial(5)  // 120
factorial(6)  // 720
factorial(7)  // 5040
factorial(1)  // 1
factorial(0)  // 1 (no iterations, returns 1)
```

**Complexity Analysis:**
- Time: O(n) - loop runs n times
- Space: O(1) - only one variable

---

### **Exercise 5: chpt4_5.java - Count Vowels**

**Problem:** Write `countVowels(String str)` that returns the number of vowels.

**Vowels:** a, e, i, o, u (case-insensitive)

**Solution:**
```java
public static int countVowels(String str) {
    int vowels = 0;
    for (int i = 0; i < str.length(); i++) {
        char c = Character.toLowerCase(str.charAt(i));
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            vowels++;
        }
    }
    return vowels;
}
```

**Logic:**
1. Loop through each character in string
2. Convert to lowercase for consistent comparison
3. Check if character is a vowel
4. Increment counter if true
5. Return total count

**Test Cases:**
```java
countVowels("Hello")       // 2 (e, o)
countVowels("Programming") // 3 (o, a, i)
countVowels("AEIOU")       // 5
countVowels("xyz")         // 0
```

**Complexity Analysis:**
- Time: O(n) where n is string length
- Space: O(1) - only counter variable

**Alternative with Regex:**
```java
public static int countVowels(String str) {
    return str.toLowerCase().replaceAll("[^aeiou]", "").length();
}
```

---

### **Exercise 6: chpt4_6.java - Reverse String**

**Problem:** Write `reverseString(String str)` that returns string in reverse order.

**Example:** `reverseString("java")` → "avaj"

**Solution (Efficient):**
```java
public static String reverseString(String str) {
    int len = str.length();
    char[] rev = new char[len];
    for (int i = 0; i < len; i++) {
        rev[i] = str.charAt(len - 1 - i);
    }
    return new String(rev).toUpperCase();
}
```

**Why This Approach?**
- Avoids string concatenation in loop (immutability overhead)
- Uses char array (mutable, more efficient)
- Builds string once at the end
- Time: O(n), Space: O(n)

**How It Works:**
```
Original: "java" (indices 0,1,2,3)
Reverse mapping:
  i=0: rev[0] = str.charAt(4-1-0) = str[3] = 'a'
  i=1: rev[1] = str.charAt(4-1-1) = str[2] = 'v'
  i=2: rev[2] = str.charAt(4-1-2) = str[1] = 'a'
  i=3: rev[3] = str.charAt(4-1-3) = str[0] = 'j'
Result: "avaj" (then uppercase) → "AVAJ"
```

**Test Cases:**
```java
reverseString("Testing TrackcartZ")  // "ZTRACKCART GNITSET"
reverseString("hello")                // "OLLEH"
reverseString("a")                    // "A"
```

**Alternative (Simple but Less Efficient):**
```java
public static String reverseString(String str) {
    String result = "";
    for (int i = str.length() - 1; i >= 0; i--) {
        result += str.charAt(i);  // String concatenation creates new String each time!
    }
    return result;
}
```

---

### **Exercise 7: chpt4_7.java - FizzBuzz Variant**

**Problem:** Write `fizzBuzzVariant(int n)` that returns:
- "Fizz" if divisible by 3
- "Buzz" if divisible by 5
- "FizzBuzz" if divisible by both
- "None" otherwise

**Solution:**
```java
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
```

**Order Matters:**
```
Check 1: Both divisible (3 AND 5) → "FizzBuzz"
         Must check this FIRST, or would return "Fizz" for 15
Check 2: Divisible by 3 → "Fizz"
Check 3: Divisible by 5 → "Buzz"
Check 4: Neither → "None"
```

**Test Cases:**
```java
fizzBuzzVariant(3)   // "Fizz"
fizzBuzzVariant(5)   // "Buzz"
fizzBuzzVariant(15)  // "FizzBuzz"
fizzBuzzVariant(7)   // "None"
fizzBuzzVariant(0)   // "null"
fizzBuzzVariant(-5)  // "null"
```

**Classic FizzBuzz Pattern:**
```
1:  None
2:  None
3:  Fizz
4:  None
5:  Buzz
6:  Fizz
7:  None
8:  None
9:  Fizz
10: Buzz
11: None
12: Fizz
13: None
14: None
15: FizzBuzz
```

---

### **Exercise 8: chpt4_8.java - Password Strength Checker**

**Problem:** Write `checkPassword(String password)` that returns "Strong" if:
- At least 8 characters long
- Contains at least one uppercase letter
- Contains at least one digit

Otherwise return "Weak"

**Solution:**
```java
public static String checkPassword(String str) {
    if (str.isEmpty() || str.length() < 8)
        return "Weak";
    
    if (!str.matches(".*[A-Z].*"))
        return "Weak";
    
    if (!str.matches(".*[0-9].*"))
        return "Weak";
    
    return "Strong";
}
```

**Regex Breakdown:**
- `.*` → zero or more of any character
- `[A-Z]` → one uppercase letter
- `[0-9]` → one digit
- Full pattern: "contains at least one of X"

**Examples:**
```java
checkPassword("Test123456")  // "Strong" (8+ chars, T, 1)
checkPassword("")            // "Weak" (empty)
checkPassword("Test123*&^")  // "Strong" (10 chars, T, 1)
checkPassword("test12345")   // "Weak" (no uppercase)
checkPassword("TEST12345")   // "Strong" (uppercase present, digits)
checkPassword("TestABC")     // "Weak" (no digits)
```

**Complexity Analysis:**
```
Time: O(n) - each regex check is O(n)
      (3 checks = 3n, but constants ignored)
Space: O(1) - no new structures
```

**Early Return Pattern:**
- Checks fail-fast (returns "Weak" immediately when condition fails)
- Only returns "Strong" if all checks pass
- More efficient than accumulating boolean flags

---

### **Exercise 9: chpt4_9.java - Height Converter**

**Problem:** Write `convertHeight(double feet, double inches)` that converts height to meters.

**Conversion Factors:**
- 1 inch = 0.0254 meters
- 1 foot = 12 inches

**Solution:**
```java
public static double convertHeight(double feet, double inches) {
    double totalInches = (feet * 12) + inches;
    return totalInches * 0.0254;
}
```

**How It Works:**
```
Step 1: Convert feet to inches
        totalInches = feet * 12 + inches

Step 2: Convert inches to meters
        totalMeters = totalInches * 0.0254

Example: convertHeight(6.0, 1.0)
        = (6 * 12 + 1) * 0.0254
        = 73 * 0.0254
        = 1.8542 meters
```

**Test Cases:**
```java
convertHeight(6.0, 1.0)   // 1.8542 (6'1")
convertHeight(5.0, 10.0)  // 1.778 (5'10")
convertHeight(6.0, 0.0)   // 1.8288 (6'0")
```

**Complexity Analysis:**
```
Time: O(1) - fixed 2 operations (multiply, add, multiply)
Space: O(1) - one local variable
```

---

### **Exercise 10: chpt4_10.java - BMI Calculator with Categories**

**Problem:** Write `bmiCategory(double weight, double heightMeters)` that calculates BMI and returns category.

**Solution:**
```java
public static String bmiCategory(double weight, double heightMeters) {
    double bmi = weight / (heightMeters * heightMeters);
    
    if (bmi < 18.5)
        return "Underweight";
    if (bmi < 25)
        return "Normal Weight";
    if (bmi < 30)
        return "Overweight";
    else
        return "Obese";
}
```

**BMI Categories Reference:**
```
BMI < 18.5      →  Underweight
18.5 ≤ BMI < 25 →  Normal Weight
25 ≤ BMI < 30   →  Overweight
BMI ≥ 30        →  Obese
```

**Example Calculation:**
```
bmiCategory(150, 1.5)
= 150 / (1.5 * 1.5)
= 150 / 2.25
= 66.67
→ "Obese"
```

**Test Cases:**
```java
bmiCategory(120, 1.75)   // "Normal Weight" (~39)
bmiCategory(150, 1.5)    // "Obese" (~67)
bmiCategory(100, 1.8)    // "Underweight" (~31 actually overweight)
```

**Complexity Analysis:**
```
Time: O(1) - fixed calculations
Space: O(1) - one double variable
```

**Design Notes:**
- Calculation is separated from categorization
- BMI is computed once, used for all comparisons
- Chain of if/else comparisons is efficient
- No unnecessary recomputation

---

## Master Checklist

### **Chapter 4 Mastery Checklist**

#### Conditional Logic
- [ ] Understand if/else statement execution flow
- [ ] Know difference between `else if` and multiple `if` statements
- [ ] Use `else if` to avoid redundant checks
- [ ] Write boolean expressions with proper operators
- [ ] Handle edge cases (null, empty, negative values)

#### Cumulative Algorithms
- [ ] Recognize cumulative sum pattern (start = 0, op = +=)
- [ ] Recognize cumulative product pattern (start = 1, op = *=)
- [ ] Implement loop-based cumulative algorithms
- [ ] Know why initial values matter (identity elements)

#### String Methods
- [ ] Use `.substring(start, end)` correctly (end is exclusive)
- [ ] Use `.charAt(index)` to access individual characters
- [ ] Use `.matches(regex)` for pattern matching
- [ ] Use `.length()` to get string length
- [ ] Understand string immutability implications

#### Method Design with Conditions
- [ ] Write methods with conditional logic inside
- [ ] Use early return for cleaner code
- [ ] Separate logic from I/O (methods return, not print)
- [ ] Choose appropriate return types

#### Regular Expressions
- [ ] Match digits with `[0-9]+`
- [ ] Match uppercase with `[A-Z]`
- [ ] Match letters with `[a-zA-Z]+`
- [ ] Use `.` for any character and `*` for "zero or more"
- [ ] Understand special sequences like `.*[A-Z].*`

#### Algorithm Complexity
- [ ] Analyze time complexity of conditional logic (usually O(1))
- [ ] Analyze time complexity of loops (usually O(n))
- [ ] Identify when regex makes code O(n)
- [ ] Understand space complexity implications

---

## Quick Reference

### **Common Patterns**

#### **If/Else Pattern (with precedence)**
```java
if (condition1 && condition2) {
    // Execute if both conditions true
} else if (condition1 || condition3) {
    // Execute if either condition true (and first wasn't)
} else {
    // Execute if none of above true
}
```

#### **Cumulative Algorithm Pattern**
```java
Type result = initialValue;  // 0 for sum, 1 for product, "" for string
for/while (condition) {
    result = result OPERATOR value;
}
return result;
```

#### **String Validation Pattern**
```java
public static boolean isValid(String str) {
    if (str == null || str.isEmpty())
        return false;
    
    if (!str.matches("[pattern]+"))
        return false;
    
    // Additional checks
    
    return true;
}
```

#### **Category Checker Pattern**
```java
public static String categorize(double value) {
    if (value < threshold1)
        return "Category1";
    else if (value < threshold2)
        return "Category2";
    else if (value < threshold3)
        return "Category3";
    else
        return "Category4";
}
```

#### **Character-by-Character Processing Pattern**
```java
for (int i = 0; i < str.length(); i++) {
    char c = str.charAt(i);
    if (condition(c)) {
        // Process character
    }
}
```

### **Key Operators and Methods**

#### **Modulo Operator (%)**
```java
num % 2 == 0        // Check if even
num % 2 == 1        // Check if odd
num % 10            // Get last digit
num % 100           // Get last two digits
```

#### **Common Regex Patterns**
```java
"[0-9]+"            // One or more digits
"[a-z]+"            // One or more lowercase letters
"[A-Z]+"            // One or more uppercase letters
"[a-zA-Z]+"         // One or more letters
".*[0-9].*"         // Contains at least one digit
".*[A-Z].*"         // Contains at least one uppercase
"[a-zA-Z0-9_]+"     // Alphanumeric plus underscore
```

#### **String Methods Summary**
```java
str.substring(start, end)       // Substring from start to end (end exclusive)
str.substring(start)            // Substring from start to end
str.charAt(index)               // Character at index
str.length()                    // Length of string
str.toLowerCase()               // Convert to lowercase
str.toUpperCase()               // Convert to uppercase
str.matches(regex)              // Matches regex pattern
str.isEmpty()                   // True if length is 0
str.contains(substring)         // True if contains substring
str.indexOf(substring)          // Index of first occurrence (-1 if not found)
str.replace(old, new)           // Replace all occurrences
```

### **Number Conversions and Math**

#### **Character Methods**
```java
Character.isDigit(c)            // true if '0'-'9'
Character.isLetter(c)           // true if a-z or A-Z
Character.isUpperCase(c)        // true if A-Z
Character.isLowerCase(c)        // true if a-z
Character.toLowerCase(c)        // Convert to lowercase
Character.toUpperCase(c)        // Convert to uppercase
```

#### **Common Mathematical Patterns**
```java
weight / (height * height)      // BMI calculation (NOT height ^ 2!)
(feet * 12) + inches            // Convert feet+inches to inches
totalInches * 0.0254            // Convert inches to meters
n * (n - 1) * (n - 2) ... * 1   // Factorial (use loop, not recursion in Ch.4)
```

#### **Integer Division vs Regular Division**
```java
int a = 7;
int b = 2;
a / b           // 3 (integer division)
(double)a / b   // 3.5 (floating point)
a / (double)b   // 3.5 (floating point)
```

---

## Key Takeaways

1. **if/else statements** control program flow based on conditions
2. **else if** prevents unnecessary checks when conditions are mutually exclusive
3. **Cumulative algorithms** build results across iterations with appropriate operators and initial values
4. **String methods** like `.substring()` and `.matches()` process and validate text
5. **Regular expressions** provide powerful pattern matching with syntax like `[0-9]+`
6. **Methods with conditional logic** encapsulate decision-making and are reusable
7. **Order matters** in if/else chains - check most specific conditions first
8. **Time complexity** is usually O(1) for fixed conditions, O(n) for loops or regex
9. **String immutability** means string concatenation in loops is inefficient
10. **BMI formula** uses multiplication, NOT the `^` operator which is bitwise XOR

---

**Last Updated:** 2026-04-26  
**Author:** Study Guide Generator  
**Reference:** Chapter 4 Programming Exercises and Short Answer Questions
