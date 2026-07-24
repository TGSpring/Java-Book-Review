# Chapter 8 — Complete Study Guide

> Focus: Object-oriented programming (OOP) fundamentals, class design, encapsulation, constructors, accessor/mutator methods, the `this` keyword, abstraction, cohesion, and practical object modeling with real-world classes (Stock, Point, BankAccount).

---

## Table of Contents
- Short answer review (29 questions across 5 sections)
- Core concepts explained
- Files & classes (detailed walkthroughs)
  - Stock class & StockMain
  - Point class & PointMain
  - BankAccount class
- Object-oriented design patterns
- Common pitfalls and how to fix them
- Suggested refactors & improvements
- Practice exercises
- Quick reference (OOP methods and terminology)
- Code examples
- Mini-quiz
- Master checklist

---

## Short answer review

### Section 8.1: Object-Oriented vs. Procedural Programming

**Question 1: Describe the difference between OOP and procedural programming.**
- **Procedural**: Focuses on functions and the order of execution; breaking complex tasks into smaller subtasks. Manipulates data externally. Mnemonic: "verb-noun" (action first).
- **OOP**: Focuses on modeling real-world entities with state and behavior. Promotes encapsulation and code reuse. Mnemonic: "noun-verb" (object first).
- **Key distinction**: OOP organizes code around objects; procedural organizes code around functions.

**Question 2: What is an object? How is it different from a class?**
- **Object**: A programming entity that contains state (data/fields) and behavior (methods). Instances of a class; the actual runtime entity.
- **Class**: A blueprint or template for creating objects. Defines what fields and methods all objects of that type will have.
- **Analogy**: A class is like a cookie cutter; an object is like the actual cookie made from that cutter.

---

### Section 8.2: Fields, Parameters, Accessors, and Mutators

**Question 6: Explain the differences between a field and a parameter.**
- **Field** (instance variable):
  - Declared inside the class body (between `{}`)
  - Part of an object's internal state
  - Persists for the object's lifetime
  - Also called instance variable, data member, or attribute
  - Scope: accessible throughout the class
  
- **Parameter**:
  - Declared in a method header (between `()`)
  - Passed into a method; temporary
  - Scope: only within that method
  - Used to pass values to methods or constructors

**Syntax difference**:
```java
// Field
public class MyClass {
    private int myField;  // declared in class body
}

// Parameter
public void myMethod(int myParameter) {  // declared in method header
    // myParameter only exists within myMethod
}
```

**Question 8: What is the difference between an accessor and a mutator? What naming conventions apply?**
- **Accessor** (getter):
  - Returns information about state **without modifying** it
  - Named with convention: `getFieldName()` (e.g., `getBalance()`)
  - Return type is the field type or related type
  - No side effects; read-only
  
- **Mutator** (setter):
  - **Changes** the state of an object's fields
  - Named with convention: `setFieldName(newValue)` (e.g., `setBalance(100)`)
  - Often return type is `void`
  - May validate input before modifying
  
**Example**:
```java
// Accessor
public double getBalance() {
    return balance;
}

// Mutator
public void setBalance(double amount) {
    if (amount >= 0) {
        balance = amount;
    }
}
```

---

### Section 8.3: Constructors and the `this` Keyword

**Question 17: What is a constructor? How is it different from other methods?**
- **Constructor**:
  - Special method that initializes an object's state when it's created
  - **No return type** (not even `void`)
  - Must have the **same name as the class**
  - Called automatically when object is instantiated with `new`
  - If no constructor is defined, Java provides a default no-argument constructor
  - If any constructor is defined, the default is **no longer provided**

- **Difference from regular methods**:
  - Methods have return types; constructors don't
  - Methods are called explicitly; constructors are called automatically on object creation
  - Methods can be called multiple times; constructors once per object

**Example**:
```java
public class Point {
    private int x, y;
    
    // Constructor (no return type, same name as class)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    // Regular method (has return type)
    public int getX() {
        return x;
    }
}
```

**Question 20: What is the meaning of the keyword `this`? Describe 3 ways it can be used.**

The `this` keyword refers to the **implicit parameter** (the object on which a method was called).

**Use case 1: Disambiguating shadowed variables**
```java
public void setLocation(int x, int y) {
    this.x = x;  // this.x refers to field; x refers to parameter
    this.y = y;
}
```

**Use case 2: Calling another method on the same object**
```java
public void reset() {
    this.setLocation(0, 0);  // equivalent to setLocation(0, 0)
}
```

**Use case 3: Constructor chaining**
```java
public class Point {
    private int x, y;
    
    // Full constructor
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    // No-arg constructor (chains to full)
    public Point() {
        this(0, 0);  // calls the 2-arg constructor
    }
}
```

**Why use `this`?** Makes intent clear, avoids shadowing bugs, enables constructor chaining.

---

### Section 8.4: Abstraction and Encapsulation

**Question 22: What is abstraction? How do objects provide abstraction?**
- **Abstraction**: Focusing on essential properties and behaviors while hiding complex inner workings.
- **How objects provide it**: Encapsulation separates the **public interface** (methods users interact with) from the **private implementation** (how it works internally).
- **Benefit**: Users don't need to understand implementation details; they just call methods.

**Example**: A `BankAccount` object abstracts away all the complexity of managing money. Users call `deposit()` and `withdraw()`; they don't need to know how balances are stored or fees are calculated.

**Question 28: How does encapsulation allow you to change the internal implementation of a class?**
- **Strategy**: Declare fields and helper methods as `private`; expose only public accessor/mutator methods.
- **Benefit**: External code depends on the public interface, not the implementation.
- **Change scenario**: If you wanted to change from `double balance` to `BigDecimal balance` internally, you could do so without breaking client code that calls `getBalance()` (since only the method signature matters, not the internal representation).

**Example**:
```java
// Client code only knows this interface
public double getBalance() { ... }
public void setBalance(double amount) { ... }

// Implementation can change internally without breaking client code
// Old: private double balance;
// New: private BigDecimal balance;  // same methods, different internals
```

---

### Section 8.5: Cohesion

**Question 29: What is cohesion? How can you tell whether a class is cohesive?**
- **Cohesion**: The extent to which code for a class represents a single abstraction.
- **Highly cohesive**: All fields and methods work together toward one well-defined responsibility.
- **Low cohesion**: Unrelated methods and fields; the class tries to do too much.

**How to tell if a class is cohesive:**
- ✅ All methods use all (or most) fields
- ✅ Class has a clear, single purpose
- ✅ No unrelated methods added for convenience
- ❌ Class contains UI code and business logic mixed together
- ❌ Methods operate on disjoint sets of fields
- ❌ Class name doesn't describe what it does (e.g., "Utility")

**Example from Chapter 8**:
- ❌ Bad: `Stock` class handles purchases, profit calculation, **and user prompts**
- ✅ Good: Separate `Stock` (model) from `StockMain` (UI/interaction)

---

## Core concepts explained

### Object-Oriented Design Fundamentals
- **Encapsulation**: Bundling data and methods; hiding internals, exposing interface
- **Abstraction**: Focusing on what an object does, not how it does it
- **Cohesion**: Class has one clear responsibility
- **Coupling**: Dependency between classes (want low coupling, high cohesion)

### Class Structure
```java
public class ClassName {
    // FIELDS (state)
    private int field1;
    private String field2;
    
    // CONSTRUCTORS (initialization)
    public ClassName() { ... }
    public ClassName(int f1, String f2) { ... }
    
    // ACCESSORS (getters; read-only)
    public int getField1() { return field1; }
    public String getField2() { return field2; }
    
    // MUTATORS (setters; modify state)
    public void setField1(int value) { field1 = value; }
    public void setField2(String value) { field2 = value; }
    
    // OTHER METHODS (business logic)
    public void doSomething() { ... }
}
```

### Constructor Patterns
- **No-arg constructor**: `public ClassName() { ... }`
- **Parameterized constructor**: `public ClassName(int x, String s) { ... }`
- **Constructor chaining**: `public ClassName() { this(0, "default"); }`
- **Default constructor**: Automatically provided if no constructor defined

### Validation in Constructors and Mutators
```java
public class Stock {
    public Stock(String symbol) {
        if (symbol == null) {
            throw new NullPointerException("Symbol cannot be null");
        }
        this.symbol = symbol;
    }
}
```

### Immutability
Some classes intentionally have no mutators to prevent state changes:
```java
public class ImmutablePoint {
    private final int x;
    private final int y;
    
    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
    // Only getters, no setters
}
```

---

## Files & classes — detailed walkthroughs

### Stock Class

**Purpose**: Model a stock investment; track shares purchased, total cost, and compute profit.

**Fields**:
- `private String symbol` — ticker symbol (e.g., "AAPL")
- `private int totalShares` — cumulative shares purchased
- `private double totalCost` — total money spent on all shares

**Key Methods**:

**Constructor: `Stock(String theSymbol)`**
- Initializes a new Stock with 0 shares and 0 cost
- Validates that symbol is not null; throws `NullPointerException` otherwise
- **Design pattern**: Validates input in constructor to establish class invariants

```java
public Stock(String theSymbol) {
    if (theSymbol == null) {
        throw new NullPointerException();
    }
    symbol = theSymbol;
    totalShares = 0;
    totalCost = 0.0;
}
```

**Method: `purchase(int shares, double pricePerShare)`**
- Records a purchase: adds shares to totalShares and updates totalCost
- Pre-condition validation: throws `IllegalArgumentException` if shares < 0 or price < 0
- **Design pattern**: Validates all inputs before modifying state

```java
public void purchase(int shares, double pricePerShare) {
    if (shares < 0 || pricePerShare < 0.0) {
        throw new IllegalArgumentException();
    }
    totalShares += shares;
    totalCost += shares * pricePerShare;
}
```

**Method: `getProfit(double currentPrice)`**
- Calculates profit/loss based on current price
- Formula: `(totalShares * currentPrice) - totalCost`
- Pre-condition: currentPrice >= 0.0
- Returns double (negative if loss, positive if profit)

```java
public double getProfit(double currentPrice) {
    if (currentPrice < 0.0) {
        throw new IllegalArgumentException();
    }
    double marketValue = totalShares * currentPrice;
    return marketValue - totalCost;
}
```

**Question 10: `clear()` method**
- Resets totalShares and totalCost to 0
- Allows reusing the same Stock object for new investment
- **Purpose**: Clean state for fresh analysis

```java
public void clear() {
    totalShares = 0;
    totalCost = 0;
}
```

**Complexity Analysis**: All methods O(1) time and O(1) space (only scalar fields, no collections).

**Cohesion**: ✅ Highly cohesive — all methods and fields relate to stock investment tracking. ✅ No UI code mixed in (that's in StockMain).

---

### Point Class

**Purpose**: Model a 2D point with (x, y) coordinates; compute distances, slopes, and geometric relationships.

**Fields**:
- `private int x` — x-coordinate
- `private int y` — y-coordinate

**Key Methods**:

**Constructor: `Point()` (no-arg)**
- Initializes point to origin (0, 0)
- Uses constructor chaining with `this(0, 0)` to call parameterized constructor
- **Design pattern**: Avoids code duplication via chaining

```java
public Point() {
    this(0, 0);  // chains to parameterized constructor
}
```

**Constructor: `Point(int x, int y)` (parameterized)**
- Initializes point to given coordinates
- Calls `setLocation(x, y)` to centralize validation/assignment logic
- **Design pattern**: Extract common logic into a helper method

```java
public Point(int x, int y) {
    setLocation(x, y);
}
```

**Method: `distanceFromOrigin()`**
- Returns Euclidean distance from (0, 0)
- Formula: √(x² + y²)
- Accessor; does not modify state
- **Complexity**: O(1) time, O(1) space

```java
public double distanceFromOrigin() {
    return Math.sqrt(x * x + y * y);
}
```

**Question 1: `quadrant()` method**
- Returns which quadrant the point falls in (1-4), or 0 if on an axis
- **Quadrant 1**: x > 0, y > 0
- **Quadrant 2**: x < 0, y > 0
- **Quadrant 3**: x < 0, y < 0
- **Quadrant 4**: x > 0, y < 0
- **Axis (0)**: x == 0 or y == 0
- **Complexity**: O(1) time (constant comparisons)

```java
public int quadrant() {
    if (x == 0 || y == 0) {
        return 0;
    } else if (x > 0 && y > 0) {
        return 1;
    } else if (x < 0 && y > 0) {
        return 2;
    } else if (x < 0 && y < 0) {
        return 3;
    } else {  // x > 0, y < 0
        return 4;
    }
}
```

**Question 2: `flip()` method**
- Negates and swaps x and y coordinates
- Example: (5, -3) → (3, -5); (4, 17) → (-17, -4)
- Uses temporary variable to perform swap: `swap = x; x = -y; y = -swap;`
- **Key insight**: `-y` is cleaner than `y * -1`

```java
public void flip() {
    int swap = x;
    x = -y;
    y = -swap;
}
```

**Question 3: `manhattanDistance(Point other)`**
- Calculates Manhattan distance to another point
- Formula: |x1 - x2| + |y1 - y2|
- Represents distance traveling only horizontally and vertically
- Accessor; does not modify state
- **Complexity**: O(1) time

```java
public int manhattanDistance(Point other) {
    return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
}
```

**Question 4: `isVertical(Point other)`**
- Returns true if two points line up vertically (same x-coordinate)
- Accessor; boolean return
- **Complexity**: O(1) time

```java
public boolean isVertical(Point other) {
    return this.x == other.x;
}
```

**Question 5: `slope(Point other)`**
- Calculates slope of line between this point and another
- Formula: (y2 - y1) / (x2 - x1)
- Throws `IllegalArgumentException` if x-coordinates are identical (undefined slope)
- **Complexity**: O(1) time
- **Pitfall**: Must handle vertical lines; avoid division by zero

```java
public double slope(Point other) {
    if (this.x == other.x) {
        throw new IllegalArgumentException("Cannot compute slope; same x-coordinates");
    }
    return (other.y - this.y) / (double)(other.x - this.x);
}
```

**Question 6: `isCollinear(Point p1, Point p2)`**
- Checks if three points (this, p1, p2) lie on the same line
- **Approach**: Compute slopes between all three pairs, compare with rounding
- **Pitfall**: Floating-point precision; use `Math.round()` to 4 decimal places before comparison
- **Complexity**: O(1) time (constant slope computations)

```java
public boolean isCollinear(Point p1, Point p2) {
    double slope1 = this.slope(p1);
    double slope2 = this.slope(p2);
    double slope3 = p1.slope(p2);
    
    // Round to 4 decimal places for comparison
    double round1 = Math.round(slope1 * 10000.0) / 10000.0;
    double round2 = Math.round(slope2 * 10000.0) / 10000.0;
    double round3 = Math.round(slope3 * 10000.0) / 10000.0;
    
    return round1 == round2 && round1 == round3;
}
```

**Design observations**:
- ✅ Well-encapsulated; fields private, methods public
- ✅ Good use of helper methods (setLocation, slope) to avoid duplication
- ✅ Constructor chaining reduces code duplication
- ⚠️ Could add a `translate(int dx, int dy)` method (already in code) for moving points

---

### BankAccount Class

**Purpose**: Model a simple bank account with deposits, withdrawals, and transfers.

**Fields**:
- `String name` — account holder's name
- `double balance` — current account balance
- `double transactionFee` — fee charged per withdrawal (default 0.0)
- `double transferFee` — flat fee per transfer (5.0)

**Key Methods**:

**Method: `deposit(double amount)`**
- Adds money to balance; no fee charged
- **Mutator**; modifies state directly

```java
public void deposit(double amount) {
    balance = balance + amount;
}
```

**Method: `withdraw(double amount)`**
- Removes money from balance; charges `transactionFee`
- **Pre-condition check**: balance must remain non-negative after withdrawal + fee
- If insufficient funds, prints error and doesn't modify balance
- **Complexity**: O(1) time

```java
public void withdraw(double amount) {
    double totalDeduction = amount + transactionFee;
    if (totalDeduction <= balance) {
        balance = balance - totalDeduction;
    } else {
        System.out.println("Insufficient funds.");
    }
}
```

**Question 11: `transactionFee` field**
- New field allowing customizable fee per withdrawal
- Default: 0.0; can be set by client code
- Fee is deducted during withdrawal, not deposit
- **Design pattern**: Public field for simplicity (could be private with setter for better encapsulation)

**Question 12: `toString()` method**
- Returns a string like "Name, $123.45"
- Uses `String.format()` for currency formatting with 2 decimal places
- **Complexity**: O(1) time (short string)

```java
public String toString() {
    return String.format("%s, $%.2f", name, balance);
}
```

**Question 13: `transfer(BankAccount second, double money)` method**
- Transfers money from this account to another
- **Flat $5 fee** deducted from this account (separate from transactionFee)
- If full transfer not possible, transfers partial amount (remaining after fee)
- **Edge cases**:
  - If balance < $5 or money <= 0: no transfer occurs
  - If balance >= (money + $5): full transfer with fee
  - Otherwise: partial transfer (balance - $5) sent

```java
public void transfer(BankAccount second, double money) {
    if (balance < 5 || money <= 0) {
        System.out.println("Failed.");
    } else if (balance >= (money + 5)) {
        // Full transfer
        balance = balance - (money + 5);
        second.deposit(money);
    } else {
        // Partial transfer (send what's left after fee)
        double transferAmount = balance - 5;
        balance = 0;
        second.deposit(transferAmount);
    }
}
```

**Design observations**:
- ✅ Simple, understandable interface
- ⚠️ Could use better encapsulation: `name` and `transactionFee` should be `private`
- ⚠️ `withdraw()` doesn't return success/failure (prints instead); could return boolean for better testability
- ⚠️ No input validation on name or initial balance

**Complexity Analysis**: All methods O(1) time and O(1) space.

---

## Object-Oriented Design Patterns

### 1. Constructor Chaining
**Pattern**: Call another constructor from a constructor to avoid duplication.
```java
public Point() {
    this(0, 0);  // calls Point(int, int)
}
```

### 2. Validation in Constructors
**Pattern**: Check preconditions in constructor to establish class invariants.
```java
public Stock(String symbol) {
    if (symbol == null) throw new NullPointerException();
    this.symbol = symbol;
}
```

### 3. Private Helper Methods
**Pattern**: Extract common logic into a private method to avoid duplication.
```java
public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
}

// Both constructors call setLocation
public Point() { this(0, 0); }
public Point(int x, int y) { setLocation(x, y); }
```

### 4. Encapsulation with Private Fields and Public Accessors/Mutators
**Pattern**: Hide implementation; expose only necessary interface.
```java
private int balance;  // hidden

public double getBalance() { return balance; }  // public accessor
public void setBalance(double amount) { this.balance = amount; }  // public mutator
```

### 5. State Validation Before Modification
**Pattern**: Check conditions before changing state; don't partially modify if validation fails.
```java
public void withdraw(double amount) {
    double total = amount + fee;
    if (total <= balance) {
        balance -= total;  // modify only if valid
    }
    // don't modify if invalid
}
```

---

## Common pitfalls and how to fix them

### Pitfall 1: Forgetting to Initialize Fields in Constructor
**Problem**: Fields retain default values (0, null, false) if not explicitly initialized.
```java
// ❌ Bad: balance not initialized
public class BankAccount {
    private double balance;
    public BankAccount(double initial) {
        // forgot to set balance = initial
    }
}

// ✅ Good: explicitly initialize
public BankAccount(double initial) {
    balance = initial;
}
```

### Pitfall 2: Mixing UI Code with Business Logic
**Problem**: Stock class handles purchases AND user prompts → low cohesion, hard to test.
```java
// ❌ Bad: Stock does both business logic and UI
public class Stock {
    public void purchase() {
        System.out.println("Enter shares:");  // UI!
        int shares = scanner.nextInt();       // UI!
        // ... purchase logic
    }
}

// ✅ Good: Separate concerns
public class Stock {
    public void purchase(int shares, double price) {
        // ... business logic only
    }
}
// UI logic in StockMain or separate class
```

### Pitfall 3: Not Validating Input in Constructor
**Problem**: Invalid objects created; bugs hard to debug.
```java
// ❌ Bad: no validation
public Stock(String symbol) {
    this.symbol = symbol;  // symbol could be null
}

// ✅ Good: validate
public Stock(String symbol) {
    if (symbol == null) {
        throw new NullPointerException("Symbol cannot be null");
    }
    this.symbol = symbol;
}
```

### Pitfall 4: Accidentally Shadowing Parameters with Fields
**Problem**: Forgetting `this.` when parameter has same name as field.
```java
// ❌ Bad: confusing
public Point(int x, int y) {
    x = x;  // assigns x to itself! field not updated
    y = y;
}

// ✅ Good: use this
public Point(int x, int y) {
    this.x = x;
    this.y = y;
}
```

### Pitfall 5: Returning Mutable Field References (Leaking Internal State)
**Problem**: Client can modify private field through returned reference.
```java
// ❌ Bad: client can modify internal state
private int[] data;
public int[] getData() {
    return data;  // client gets reference; can modify!
}

// ✅ Good: return copy or unmodifiable view
public int[] getData() {
    return Arrays.copyOf(data, data.length);
}
```

### Pitfall 6: Floating-Point Precision in Comparisons
**Problem**: Direct `==` comparison of doubles fails due to rounding errors.
```java
// ❌ Bad: floating-point precision issues
double slope1 = 1.0 / 3.0;  // 0.33333...
double slope2 = 1.0 / 3.0;
if (slope1 == slope2) { ... }  // may be false!

// ✅ Good: round before comparing
double round1 = Math.round(slope1 * 10000.0) / 10000.0;
double round2 = Math.round(slope2 * 10000.0) / 10000.0;
if (round1 == round2) { ... }
```

### Pitfall 7: Partial State Modification on Error
**Problem**: If validation fails partway, object left in inconsistent state.
```java
// ❌ Bad: partially modifies if error occurs
public void setData(String x, String y) {
    this.x = Integer.parseInt(x);  // succeeds
    this.y = Integer.parseInt(y);  // throws exception; x already changed!
}

// ✅ Good: validate all before modifying
public void setData(String x, String y) {
    int xVal = Integer.parseInt(x);  // validate
    int yVal = Integer.parseInt(y);  // validate
    this.x = xVal;                    // now modify
    this.y = yVal;
}
```

### Pitfall 8: Not Using Constructor Chaining When Applicable
**Problem**: Duplicate initialization logic in multiple constructors.
```java
// ❌ Bad: duplicated code
public Point(int x, int y) {
    this.x = x;
    this.y = y;
}
public Point() {
    this.x = 0;
    this.y = 0;
}

// ✅ Good: chain constructors
public Point(int x, int y) {
    this.x = x;
    this.y = y;
}
public Point() {
    this(0, 0);  // calls full constructor
}
```

---

## Suggested refactors & improvements

### 1. Add Getter/Setter for Better Encapsulation
```java
// Current: public fields
public class BankAccount {
    String name;
    double balance;
}

// Improved: private fields with accessors/mutators
public class BankAccount {
    private String name;
    private double balance;
    
    public String getName() { return name; }
    public void setName(String newName) { this.name = newName; }
    
    public double getBalance() { return balance; }
    // setBalance intentionally omitted; balance only changes via deposit/withdraw
}
```

### 2. Separate UI from Business Logic
```java
// Before: Stock handles user interaction
public class Stock {
    public void purchase() {
        System.out.println("Shares: ");
        int shares = scanner.nextInt();
    }
}

// After: Stock is model; StockMain is UI
public class Stock {
    public void purchase(int shares, double price) { ... }
}

public class StockMain {
    public static void main(String[] args) {
        System.out.println("Shares: ");
        int shares = scanner.nextInt();
        stock.purchase(shares, price);
    }
}
```

### 3. Return Success/Failure Instead of Printing
```java
// Current: prints error
public void withdraw(double amount) {
    if (totalDeduction > balance) {
        System.out.println("Insufficient funds.");
    }
}

// Improved: returns success
public boolean withdraw(double amount) {
    double totalDeduction = amount + transactionFee;
    if (totalDeduction <= balance) {
        balance -= totalDeduction;
        return true;
    }
    return false;
}
```

### 4. Use Constants for Magic Numbers
```java
// Before: magic number
public void transfer(BankAccount other, double amount) {
    if (balance < 5) return;
    // ...
}

// After: named constant
public class BankAccount {
    private static final double TRANSFER_FEE = 5.0;
    
    public void transfer(BankAccount other, double amount) {
        if (balance < TRANSFER_FEE) return;
    }
}
```

### 5. Add Javadoc Comments
```java
/**
 * Transfers money from this account to another.
 * 
 * @param recipient the account to receive funds
 * @param amount the amount to transfer (pre-fee)
 * @throws IllegalArgumentException if amount <= 0 or recipient is null
 */
public void transfer(BankAccount recipient, double amount) {
    if (amount <= 0 || recipient == null) {
        throw new IllegalArgumentException("Invalid transfer parameters");
    }
    // ... implementation
}
```

### 6. Implement equals() and hashCode()
```java
@Override
public boolean equals(Object obj) {
    if (!(obj instanceof Point)) return false;
    Point other = (Point) obj;
    return this.x == other.x && this.y == other.y;
}

@Override
public int hashCode() {
    return Object.hash(x, y);
}
```

---

## Practice exercises (next steps)

1. **Extend BankAccount**: Add an `overdraftFee` field; charge it if withdrawal would make balance negative (instead of rejecting).
2. **Add Account History**: Track all deposits/withdrawals in a list; add a `getTransactionHistory()` method.
3. **Interest Calculation**: Add `applyInterest(double rate)` method to BankAccount; compound daily/monthly/yearly.
4. **Point Distance Matrix**: Create a method that takes an array of Points and returns a 2D distance matrix.
5. **Immutable Point**: Create an `ImmutablePoint` subclass with no mutators; use `final` fields.
6. **Stock Dividend**: Add a `dividend` field to Stock; modify `getProfit()` to include dividend income.
7. **Unit Tests**: Write JUnit tests for Stock, Point, and BankAccount classes.
8. **Reflection Project**: Design your own class (e.g., `Car`, `Book`, `Student`) with appropriate fields, constructors, and methods following OOP principles.

---

## Quick reference — OOP terminology and patterns

### Class Design Checklist
- [ ] Clear, single responsibility (cohesion)
- [ ] Private fields (encapsulation)
- [ ] Public getter/setter methods for all accessed fields
- [ ] Constructors validate input (preconditions)
- [ ] No public mutable fields (except constants)
- [ ] toString() method for easy debugging
- [ ] equals() and hashCode() if used in collections (advanced)

### Method Types
```java
// Accessor (getter): read state, no modification
public int getValue() { return value; }

// Mutator (setter): modify state
public void setValue(int newValue) { value = newValue; }

// Query: compute/derive information from state
public double getArea() { return width * height; }

// Command: perform action, may modify state
public void resize(int newWidth, int newHeight) { ... }
```

### Constructor Patterns
```java
// No-arg constructor (default values)
public Point() { this(0, 0); }

// Parameterized constructor
public Point(int x, int y) { this.x = x; this.y = y; }

// Copy constructor
public Point(Point other) { this(other.x, other.y); }
```

### Validation Patterns
```java
// Null check
if (symbol == null) throw new NullPointerException(...);

// Range check
if (amount < 0) throw new IllegalArgumentException(...);

// State-dependent check
if (x == other.x) throw new IllegalArgumentException("vertical line");
```

### Design Principles
- **DRY (Don't Repeat Yourself)**: Use helper methods, constructor chaining
- **SRP (Single Responsibility Principle)**: Each class does one thing
- **Encapsulation**: Hide implementation; expose interface
- **Cohesion**: Related code grouped together; unrelated code separated

---

## Code examples

### Example 1: Well-Designed Stock Class with Validation
```java
/**
 * Model class for stock investment tracking.
 * Highly cohesive, no UI code.
 */
public class Stock {
    private static final double EPSILON = 0.01;
    private final String symbol;
    private int totalShares;
    private double totalCost;
    
    public Stock(String symbol) {
        if (symbol == null || symbol.isEmpty()) {
            throw new IllegalArgumentException("Symbol cannot be null or empty");
        }
        this.symbol = symbol;
        this.totalShares = 0;
        this.totalCost = 0.0;
    }
    
    public void purchase(int shares, double pricePerShare) {
        if (shares <= 0 || pricePerShare < 0.0) {
            throw new IllegalArgumentException("Invalid purchase parameters");
        }
        totalShares += shares;
        totalCost += shares * pricePerShare;
    }
    
    public double getProfit(double currentPrice) {
        if (currentPrice < 0.0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        double marketValue = totalShares * currentPrice;
        return marketValue - totalCost;
    }
    
    public double getAverageCostPerShare() {
        if (totalShares == 0) {
            throw new IllegalStateException("No shares purchased");
        }
        return totalCost / totalShares;
    }
    
    public void clear() {
        totalShares = 0;
        totalCost = 0.0;
    }
    
    @Override
    public String toString() {
        return String.format("Stock(%s): %d shares @ $%.2f total", 
                           symbol, totalShares, totalCost);
    }
}
```

### Example 2: Immutable Point Class
```java
/**
 * Immutable 2D point. Once created, cannot be modified.
 */
public final class ImmutablePoint {
    private final int x;
    private final int y;
    
    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    
    public double distanceFromOrigin() {
        return Math.sqrt(x * x + y * y);
    }
    
    public ImmutablePoint translate(int dx, int dy) {
        return new ImmutablePoint(x + dx, y + dy);  // returns new object
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ImmutablePoint)) return false;
        ImmutablePoint other = (ImmutablePoint) obj;
        return this.x == other.x && this.y == other.y;
    }
    
    @Override
    public int hashCode() {
        return Object.hash(x, y);
    }
    
    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}
```

### Example 3: Enhanced BankAccount with Better Design
```java
public class BankAccount {
    private static final double DEFAULT_TRANSACTION_FEE = 0.0;
    private static final double TRANSFER_FEE = 5.0;
    
    private final String accountNumber;
    private String holderName;
    private double balance;
    private double transactionFee;
    
    public BankAccount(String accountNumber, String holderName, double initialBalance) {
        if (accountNumber == null || holderName == null) {
            throw new IllegalArgumentException("Account number and name cannot be null");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
        this.transactionFee = DEFAULT_TRANSACTION_FEE;
    }
    
    public String getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }
    public void setHolderName(String name) { this.holderName = name; }
    public double getBalance() { return balance; }
    public double getTransactionFee() { return transactionFee; }
    public void setTransactionFee(double fee) {
        if (fee < 0) throw new IllegalArgumentException("Fee cannot be negative");
        this.transactionFee = fee;
    }
    
    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive");
            return false;
        }
        balance += amount;
        return true;
    }
    
    public boolean withdraw(double amount) {
        double total = amount + transactionFee;
        if (total <= 0) {
            System.out.println("Withdrawal amount must be positive");
            return false;
        }
        if (total > balance) {
            System.out.println("Insufficient funds");
            return false;
        }
        balance -= total;
        return true;
    }
    
    public boolean transfer(BankAccount recipient, double amount) {
        if (recipient == null) {
            throw new IllegalArgumentException("Recipient cannot be null");
        }
        if (amount <= 0) {
            System.out.println("Transfer amount must be positive");
            return false;
        }
        double total = amount + TRANSFER_FEE;
        if (total > balance) {
            System.out.println("Insufficient funds for transfer");
            return false;
        }
        this.balance -= total;
        recipient.balance += amount;
        return true;
    }
    
    @Override
    public String toString() {
        return String.format("Account(%s): %s - $%.2f", 
                           accountNumber, holderName, balance);
    }
}
```

---

## Mini-quiz (self-check)

1. **What is the difference between a class and an object?**
   - Answer: A class is a blueprint/template; an object is an instance created from that blueprint.

2. **Why should fields be private and accessed via public getter/setter methods?**
   - Answer: Encapsulation; allows you to change internal implementation without breaking client code.

3. **What does constructor chaining accomplish?**
   - Answer: Avoids code duplication; allows one constructor to call another with `this(...)`.

4. **Why is validation important in constructors?**
   - Answer: Establishes class invariants; prevents objects in invalid states from being created.

5. **What is the difference between `this.field` and `field`?**
   - Answer: Both refer to the same field; `this.field` is explicit and useful when shadowed by a parameter.

6. **What is cohesion and why is it important?**
   - Answer: Cohesion is how well a class's responsibilities fit together. High cohesion makes code easier to understand, test, and maintain.

7. **Why should UI code be separated from business logic?**
   - Answer: Separation of concerns; business logic is reusable and testable; UI can be changed without affecting logic.

8. **When would you use an immutable class (no mutators)?**
   - Answer: When the object's state should never change after creation; safe for sharing, thread-safe, can be used as map keys.

---

## Master checklist — before submitting

- [ ] **Fields**: All declared as `private`; access via public methods only
- [ ] **Constructors**: Validate all inputs; establish class invariants
- [ ] **Constructor chaining**: Used where applicable to avoid duplication
- [ ] **Accessors/Mutators**: Follow naming conventions (get/set)
- [ ] **Encapsulation**: No public mutable fields; no leaking internal references
- [ ] **State validation**: Checked before modification; consistent on error
- [ ] **toString()**: Implemented for easy debugging
- [ ] **UI separation**: Business logic separate from user interaction
- [ ] **Cohesion**: Class has single, clear responsibility
- [ ] **Complexity analysis**: Time/space for key methods documented
- [ ] **Edge cases**: Empty/null/zero/negative values handled appropriately
- [ ] **No magic numbers**: Use named constants for repeated values

---

## Key Takeaways

1. **OOP models real-world entities** as objects with state and behavior, not as procedures.
2. **Encapsulation** hides implementation details; exposes only public interface.
3. **Constructors** initialize objects and establish invariants; they don't have return types.
4. **`this` keyword** refers to the current object; useful for shadowed variables and constructor chaining.
5. **Accessors (getters)** retrieve state; **mutators (setters)** modify state.
6. **Validation** should happen in constructors and mutators to maintain class invariants.
7. **Constructor chaining** reduces duplication: `public Point() { this(0, 0); }`
8. **Cohesion** is high when all methods/fields relate to one responsibility.
9. **Separation of concerns** keeps UI separate from business logic.
10. **Floating-point comparisons** require rounding before `==` to avoid precision issues.

---

**Last Updated:** 2026-07-12  
**Author:** Generated from Chapter 8 source files in repository TGSpring/Java-Book-Review  
**Status:** Complete with 3 major classes (Stock, Point, BankAccount), detailed walkthroughs, OOP patterns, pitfalls, and comprehensive reference material
