# Chapter 9 — Complete Study Guide

> Focus: Inheritance, polymorphism, abstract classes, interfaces, method overriding, the `super` keyword, code reuse, and practical inheritance hierarchies with Employee and Ticket classes.

---

## Table of Contents
- Short answer review (14+ questions across 3 sections)
- Core concepts explained
- Files & classes (detailed walkthroughs)
  - Ticket hierarchy (abstract base class with 3 subclasses)
  - Employee hierarchy (base class with 2 subclasses)
  - Shape interface (Octagon and Hexagon implementations)
- Inheritance patterns and design
- Common pitfalls and how to fix them
- Suggested refactors & improvements
- Practice exercises
- Quick reference (inheritance and polymorphism terminology)
- Code examples
- Mini-quiz
- Master checklist

---

## Short answer review

### Section 9.1: Inheritance Basics

**Question 1: What is code reuse? How does inheritance help achieve code reuse?**
- **Code reuse**: The practice of writing program code once and using it in many contexts (DRY principle).
- **How inheritance enables it**:
  - Subclasses inherit all fields and methods from superclass
  - Don't need to rewrite common functionality
  - Changes to parent class automatically propagate to children
  - Reduces maintenance burden and bugs
- **Example**: Instead of repeating `showHours()`, `showSalary()`, and `showVacation()` in every employee type, define once in `Employee` and override only what differs.

**Question 2: What is the difference between overloading and overriding a method?**

| Aspect | Overloading | Overriding |
|--------|------------|-----------|
| **Definition** | Multiple methods same name, different parameters | New version of inherited method |
| **Inheritance** | No inheritance involved | Requires inheritance (subclass) |
| **Location** | Same class | Different classes (parent & child) |
| **Parameters** | Must differ (type, number, or order) | Same as parent method |
| **Return type** | Can differ | Must be same (or covariant) |
| **Example** | `print(int)` and `print(String)` in same class | `speak()` in Dog overrides `speak()` in Animal |
| **When used** | Handle multiple input types conveniently | Customize behavior for subclass |

**Example of overloading**:
```java
public class Calculator {
    public int add(int a, int b) { return a + b; }
    public double add(double a, double b) { return a + b; }  // same name, different params
    public String add(String a, String b) { return a + b; }
}
```

**Example of overriding**:
```java
public class Animal {
    public void speak() { System.out.println("generic sound"); }
}
public class Dog extends Animal {
    @Override
    public void speak() { System.out.println("bark"); }  // replaces parent version
}
```

---

### Section 9.2: Interacting with the Superclass

**Question 5: Explain the difference between the `this` keyword and the `super` keyword. When should each be used?**

| Aspect | `this` | `super` |
|--------|--------|---------|
| **Refers to** | Current instance of this class | Parent class members |
| **Usage** | Access own fields, methods, constructors | Access overridden methods, parent constructor |
| **Scope** | Works on any object of the class | Only within subclass context |
| **Constructor chaining** | `this(params)` calls another constructor in same class | `super(params)` calls parent constructor |
| **Method calls** | `this.method()` calls own method | `super.method()` calls parent's version |

**Use `this` when**: Disambiguating shadowed variables, constructor chaining, passing object reference.
**Use `super` when**: Need parent implementation, calling parent constructor, accessing overridden method from parent.

**Examples**:
```java
// Using this
public class Point {
    private int x;
    public Point(int x) {
        this.x = x;  // disambiguate parameter from field
    }
    public void reset() {
        this.setLocation(0);  // call own method
    }
}

// Using super
public class SavingsAccount extends BankAccount {
    @Override
    public void withdraw(double amount) {
        System.out.println("Withdrawing from savings...");
        super.withdraw(amount);  // call parent's withdraw logic
    }
    
    public SavingsAccount(String name) {
        super(name);  // call parent constructor
    }
}
```

**Question 6: Can undergraduateStudent access inherited private fields? Can it call inherited public methods?**

Given:
```java
public class Student {
    private String name;      // private: inaccessible to subclass
    private int age;          // private: inaccessible to subclass
    
    public void setAge(int age) { ... }  // public: accessible
}

public class UndergraduateStudent extends Student {
    // Questions: can we access name, age, setAge?
}
```

**Answers**:
- ❌ **Cannot access `name` and `age`**: Even though inherited, `private` fields are inaccessible to subclasses. Only the enclosing class can access private members.
- ✅ **Can call `setAge()`**: `public` methods are accessible to subclasses. Any `public` or `protected` inherited member is accessible to subclass code.
- **Key lesson**: Use `protected` for fields that subclasses need to access; use `private` for internal implementation details.

---

### Section 9.3: Polymorphism

**Question 14: Polymorphic method calls with Mammal, SeaCreature, Whale, Squid hierarchy**

Given inheritance hierarchy:
```
SeaCreature
├── Mammal
│   └── Whale
└── Squid
```

With these classes:
```java
public class SeaCreature {
    public void method1() { System.out.println("creature 1"); }
    public void method2() { System.out.println("creature 2"); }
    public String toString() { return "ocean-dwelling"; }
}

public class Mammal extends SeaCreature {
    public void method1() { System.out.println("warm-blooded"); }
}

public class Whale extends Mammal {
    public void method1() { System.out.println("spout"); }
    public String toString() { return "BIG!"; }
}

public class Squid extends SeaCreature {
    public void method2() { System.out.println("tentacles"); }
}
```

**Analysis**:
- **Polymorphism rule**: The type of the **object** (runtime type) determines which method is called, not the type of the **reference** (compile-time type).
- **Method resolution**: Start in the object's actual class; if not found, walk up the inheritance chain.

**Example calls**:
```java
SeaCreature[] creatures = {
    new Whale(),   // stored as SeaCreature ref, but actual object is Whale
    new Squid(),   // stored as SeaCreature ref, but actual object is Squid
    new Mammal()   // stored as SeaCreature ref, but actual object is Mammal
};

for (SeaCreature c : creatures) {
    c.method1();  // Which method runs?
}
```

**Output**:
- `Whale.method1()` → prints "spout" (Whale overrides method1)
- `Squid.method1()` → prints "creature 1" (Squid doesn't override; uses SeaCreature)
- `Mammal.method1()` → prints "warm-blooded" (Mammal overrides method1)

---

## Core concepts explained

### Inheritance Hierarchy Structure
```
SuperClass (parent)
    ↓ extends
SubClass (child)
    ↓ extends
SubSubClass (grandchild)
```

- **Subclasses inherit** all fields and methods from superclass
- **Overriding**: Subclass provides new implementation of inherited method
- **Extending**: Subclass adds new fields/methods not in parent

### Abstract Classes vs. Interfaces

| Aspect | Abstract Class | Interface |
|--------|---|---|
| **Keyword** | `public abstract class` | `public interface` |
| **Can have state** | Yes (fields) | No (only constants) |
| **Methods** | Mix of abstract and concrete | All abstract (Java 8+ can have default) |
| **Constructors** | Yes | No |
| **Access modifiers** | Any (public, protected, private) | Only public |
| **Inheritance** | `extends` (single parent) | `implements` (multiple allowed) |
| **Purpose** | Partial implementation (template) | Contract/specification |
| **Instantiation** | Cannot create instances | Cannot create instances |

### Polymorphism
**Definition**: "Many forms." Objects of different subclasses respond to the same method call in different ways.

**Key principle**: Use **runtime type** (object's actual class), not compile-time type (reference type).

```java
Animal a = new Dog();      // compile-time type: Animal; runtime type: Dog
a.speak();                 // calls Dog.speak(), not Animal.speak()
```

### Method Resolution Order
1. Check the **actual object's class** for the method
2. If not found, walk up the inheritance chain
3. Use the first matching method found
4. If none found anywhere, compile error

---

## Files & classes — detailed walkthroughs

### Ticket Hierarchy (Abstract Base Class with Subclasses)

**Design**: A ticket for a campus event. Base class is abstract; subclasses represent different ticket types.

#### Ticket (Abstract Base Class)

**Purpose**: Define common ticket interface and shared behavior.

**Fields**:
- `private int ticketNumber` — unique identifier for ticket

**Key Methods**:

**Constructor: `Ticket(int ticketNumber)`**
- Initializes ticket with a unique number
- Protected so only subclasses can call it via `super()`

```java
public Ticket(int ticketNumber) {
    this.ticketNumber = ticketNumber;
}
```

**Abstract method: `getPrice()`**
- Must be implemented by each subclass
- Different ticket types have different pricing logic
- Makes Ticket abstract (cannot instantiate directly)
- **Design pattern**: Template method; subclass must provide specific pricing

```java
public abstract double getPrice();
```

**Protected getter: `getTicketNumber()`**
- Returns ticket number
- `protected` so subclasses can access it (not private)
- Used by subclasses (e.g., StudentAdvanceTicket.toString())

```java
protected int getTicketNumber() {
    return ticketNumber;
}
```

**Method: `toString()`**
- Returns string like "Number: 17, Price: 50.0"
- Calls `getPrice()` polymorphically (each subclass's version called)
- **Design pattern**: Uses polymorphism to get subclass-specific price

```java
@Override
public String toString() {
    return "Number: " + ticketNumber + ", Price: " + getPrice();
}
```

**Why abstract?**
- ✅ Ensures every subclass implements `getPrice()`
- ✅ Prevents accidentally creating a generic Ticket with no price logic
- ✅ Establishes contract that all tickets must have a price

---

#### WalkUpTicket (Question 6)

**Problem**: Tickets purchased day-of-event cost $50.

**Approach**:
- Constructor takes only ticket number (no days-in-advance)
- `getPrice()` always returns 50.0
- Simple subclass; no new fields

```java
public class WalkUpTicket extends Ticket {
    public WalkUpTicket(int ticketNumber) {
        super(ticketNumber);  // pass to parent constructor
    }
    
    @Override
    public double getPrice() {
        return 50.0;
    }
}
```

**Complexity**: O(1) time and space.

**Inheritance pattern**: Simplest subclass; inherits toString() as-is.

---

#### AdvanceTicket (Question 7)

**Problem**: Advance tickets cost $30 if purchased ≥10 days early, $40 otherwise.

**Approach**:
- Constructor takes ticket number and days-in-advance
- `getPrice()` returns 30 or 40 based on daysInAdvance
- Overrides `getPrice()` only; inherits toString() from Ticket

**Fields**:
- `private int daysInAdvance` — when ticket was purchased relative to event

```java
public class AdvanceTicket extends Ticket {
    private int daysInAdvance;
    
    public AdvanceTicket(int ticketNumber, int daysInAdvance) {
        super(ticketNumber);  // pass to parent constructor
        this.daysInAdvance = daysInAdvance;
    }
    
    @Override
    public double getPrice() {
        if (daysInAdvance >= 10) {
            return 30.0;
        } else {
            return 40.0;
        }
    }
}
```

**Design observations**:
- ✅ Follows is-a relationship: AdvanceTicket IS-A Ticket
- ✅ Uses `super()` to initialize parent fields
- ✅ `getPrice()` implements specific logic for advance tickets

**Complexity**: O(1) time and space.

---

#### StudentAdvanceTicket (Question 8)

**Problem**: Student advance tickets cost half of normal advance tickets ($15 or $20). Must note "Must show Student ID" when printed.

**Approach**:
- Constructor takes ticket number and days-in-advance
- `getPrice()` returns $15 (≥10 days) or $20 (<10 days)
- **Overrides `toString()`** to append student ID requirement
- Uses `super.toString()` to get parent's format, then appends

**Fields**:
- `private int SdaysInAdvance` — days in advance (note: poorly named; should be `daysInAdvance` for consistency)

```java
public class StudentAdvanceTicket extends Ticket {
    private int SdaysInAdvance;
    
    public StudentAdvanceTicket(int ticketNumber, int SdaysInAdvance) {
        super(ticketNumber);
        this.SdaysInAdvance = SdaysInAdvance;
    }
    
    @Override
    public double getPrice() {
        if (SdaysInAdvance >= 10) {
            return 15.0;
        } else {
            return 20.0;
        }
    }
    
    @Override
    public String toString() {
        return super.toString() + " Must show Student ID";
    }
}
```

**Key technique**: Calling `super.toString()` to reuse parent implementation, then appending additional info.

**Example output**:
- Normal advance ticket (10+ days): "Number: 456, Price: 30.0"
- StudentAdvanceTicket (10+ days): "Number: 113, Price: 15.0 Must show Student ID"

**Complexity**: O(1) time and space.

**Design observations**:
- ✅ Demonstrates method overriding with refinement
- ✅ Shows how to call parent method via `super` and extend its behavior
- ⚠️ Field naming: `SdaysInAdvance` is non-idiomatic; should be `daysInAdvance` to match AdvanceTicket

---

### Employee Hierarchy

**Design**: Base Employee class with two specialized subclasses (Marketer, Janitor).

#### Employee (Base Class)

**Purpose**: Define common employee behavior; all employees can apply for vacation, show hours/salary/vacation.

**Methods** (all use hardcoded values):
- `applyForVacation()` — prints "Use the yellow vacation form."
- `showHours()` — prints "I work 40 hours per week."
- `showSalary()` — prints "My salary is $40,000."
- `showVacation()` — prints "I receive 2 weeks vacation."

**Design note**: All methods print to console (tight coupling to UI). Better design would return strings and have main handle printing. However, this reflects book's example.

```java
public class Employee {
    public void applyForVacation() {
        System.out.println("Use the yellow vacation form.");
    }
    public void showHours() {
        System.out.println("I work 40 hours per week.");
    }
    public void showSalary() {
        System.out.println("My salary is $40,000.");
    }
    public void showVacation() {
        System.out.println("I receive 2 weeks vacation.");
    }
}
```

**Complexity**: O(1) time per method (just printing).

---

#### Marketer (Question 1)

**Problem**: Marketers make $50,000 (vs. $40,000 standard) and have an `advertise()` method.

**Approach**:
- Extends Employee
- Overrides `showSalary()` only (other properties same)
- Adds new method `advertise()`

```java
public class Marketer extends Employee {
    @Override
    public void showSalary() {
        System.out.println("My salary is $50,000.");
    }
    
    public void advertise() {
        System.out.println("Act now, while supplies last!");
    }
}
```

**Design observations**:
- ✅ Reuses inherited methods (hours, vacation, vacation form)
- ✅ Overrides only what differs (salary)
- ✅ Adds new behavior (advertise)
- ❌ Redundantly re-implements inherited methods (applyForVacation, showHours, showVacation) in original code — can be removed

**Complexity**: O(1) per method.

---

#### Janitor (Question 2)

**Problem**: Janitors work 80 hours/week, make $30,000, get 5 days vacation (vs. 2 weeks), and have a `clean()` method.

**Approach**:
- Extends Employee
- Overrides `showHours()`, `showSalary()`, and `showVacation()` (all differ)
- Adds new method `clean()`

```java
public class Janitor extends Employee {
    @Override
    public void showHours() {
        System.out.println("I work 80 hours per week");
    }
    
    @Override
    public void showSalary() {
        System.out.println("My salary is $30,000");
    }
    
    @Override
    public void showVacation() {
        System.out.println("I receive 5 days of vacation");
    }
    
    public void clean() {
        System.out.println("Workin' for the man");
    }
}
```

**Design observations**:
- ✅ Overrides multiple methods to customize behavior
- ✅ Adds role-specific method (clean)
- ✅ Inherits and reuses applyForVacation() unchanged

**Complexity**: O(1) per method.

---

### Shape Hierarchy (Interface-Based)

**Design**: Shape interface defines contract; Octagon and Hexagon implement it.

#### Shape (Interface)

**Purpose**: Contract that all shape classes must provide area and perimeter.

**Methods** (abstract):
- `double getArea()` — returns area of shape
- `double getPerimeter()` — returns perimeter of shape

```java
public interface Shape {
    public double getArea();
    public double getPerimeter();
}
```

**Key note**: Interface has no implementation; subclasses must provide all methods.

---

#### Octagon (Question 15)

**Problem**: Regular octagon with side length. Implements Shape interface.

**Approach**:
- `implements Shape` (not `extends`)
- Constructor takes side length
- Implement `getArea()` and `getPerimeter()` using geometric formulas

**Formulas**:
- Area: `2 * (1 + √2) * s²`
- Perimeter: `8 * s`

```java
public class Octagon implements Shape {
    private double sides;
    
    public Octagon(double sides) {
        this.sides = sides;
    }
    
    @Override
    public double getArea() {
        return 2 * (1 + Math.sqrt(2)) * Math.pow(sides, 2);
    }
    
    @Override
    public double getPerimeter() {
        return 8 * sides;
    }
}
```

**Complexity**: O(1) time per method (constant math operations).

**Design observations**:
- ✅ Implements interface contract (both methods required)
- ✅ Immutable after construction (side length never changes)
- ✅ Uses Math utilities correctly

---

#### Hexagon (Question 16)

**Problem**: Regular hexagon with side length. Implements Shape interface.

**Approach**:
- `implements Shape`
- Constructor takes side length
- Implement `getArea()` and `getPerimeter()`

**Formulas**:
- Area: `(3√3 / 2) * s²`
- Perimeter: `6 * s`

```java
public class Hexagon implements Shape {
    private double sides;
    
    public Hexagon(double sides) {
        this.sides = sides;
    }
    
    @Override
    public double getArea() {
        return (3 * Math.sqrt(3) / 2) * Math.pow(sides, 2);
    }
    
    @Override
    public double getPerimeter() {
        return 6 * sides;
    }
}
```

**Complexity**: O(1) time per method.

**Design observations**:
- ✅ Parallel to Octagon; both follow same pattern
- ✅ Interface enables polymorphism: can store both in `Shape[]` array

---

### Polymorphism in Action (ShapesMain)

**Example**:
```java
Octagon oct = new Octagon(8);
Hexagon hex = new Hexagon(6);

// Polymorphic array
Shape[] shapes = { oct, hex };
for (Shape s : shapes) {
    System.out.println(s.getArea() + " area.");
    System.out.println(s.getPerimeter() + " perimeter.");
}
```

**Why this works**: Each `Shape` reference calls the appropriate subclass method. At runtime:
- `oct.getArea()` calls Octagon's implementation
- `hex.getArea()` calls Hexagon's implementation

---

## Inheritance Patterns and Design

### Pattern 1: Abstract Base Class (Ticket)
```java
public abstract class Ticket {
    private int ticketNumber;
    public abstract double getPrice();  // subclasses must implement
    public String toString() { return "..."; }  // shared implementation
}
```
**When to use**: Share common structure and some behavior; force subclasses to implement specific methods.

### Pattern 2: Concrete Subclass with Refinement (StudentAdvanceTicket)
```java
public class StudentAdvanceTicket extends Ticket {
    @Override
    public String toString() {
        return super.toString() + " Must show Student ID";  // call parent, then add
    }
}
```
**When to use**: Enhance or extend parent's behavior without completely replacing it.

### Pattern 3: Interface as Contract (Shape)
```java
public interface Shape {
    double getArea();
    double getPerimeter();
}
```
**When to use**: Define a contract without caring about parent class; allow multiple implementations.

### Pattern 4: Method Overriding for Specialization (Janitor)
```java
public class Janitor extends Employee {
    @Override
    public void showHours() { System.out.println("I work 80 hours per week"); }
}
```
**When to use**: Subclass has different behavior for the same method.

### Pattern 5: Adding New Behavior (Marketer.advertise())
```java
public class Marketer extends Employee {
    public void advertise() { System.out.println("Act now, while supplies last!"); }
}
```
**When to use**: Subclass adds new methods not in parent.

---

## Common pitfalls and how to fix them

### Pitfall 1: Forgetting `@Override` Annotation
**Problem**: Subclass method doesn't actually override parent method (e.g., typo in name).

```java
// ❌ Bad: typo in method name; not actually overriding
public class Marketer extends Employee {
    public void showSalry() {  // should be showSalary
        System.out.println("My salary is $50,000.");
    }
}

// ✅ Good: @Override catches this error at compile time
public class Marketer extends Employee {
    @Override
    public void showSalary() {  // compiler verifies this exists in parent
        System.out.println("My salary is $50,000.");
    }
}
```

### Pitfall 2: Forgetting `super()` in Constructor
**Problem**: Parent class fields not initialized; parent constructor logic not executed.

```java
// ❌ Bad: forgot to call super()
public class AdvanceTicket extends Ticket {
    private int daysInAdvance;
    public AdvanceTicket(int ticketNumber, int daysInAdvance) {
        // forgot super(ticketNumber)
        this.daysInAdvance = daysInAdvance;
        // ticketNumber field in Ticket never set!
    }
}

// ✅ Good: call super() first
public class AdvanceTicket extends Ticket {
    private int daysInAdvance;
    public AdvanceTicket(int ticketNumber, int daysInAdvance) {
        super(ticketNumber);  // initialize parent fields
        this.daysInAdvance = daysInAdvance;
    }
}
```

### Pitfall 3: Making Abstract Class Instantiable
**Problem**: Forgot `abstract` keyword; can accidentally create Ticket object (which has no price implementation).

```java
// ❌ Bad: not abstract; can create Ticket() which has no getPrice() implementation
public class Ticket {
    public abstract double getPrice();  // Error: non-abstract class can't have abstract method
}

// ✅ Good: mark class as abstract
public abstract class Ticket {
    public abstract double getPrice();  // Now subclasses must override
}
```

### Pitfall 4: Accessing Private Fields from Subclass
**Problem**: Subclass tries to access private parent field; compilation fails.

```java
// ❌ Bad: name is private in Student; UndergraduateStudent can't access
public class UndergraduateStudent extends Student {
    public void printName() {
        System.out.println(name);  // Error: name is private in Student
    }
}

// ✅ Good: use protected getter or make field protected
public class Student {
    protected String name;  // or use public getName()
}

public class UndergraduateStudent extends Student {
    public void printName() {
        System.out.println(name);  // Now allowed
    }
}
```

### Pitfall 5: Forgetting to Override in Subclass When Required
**Problem**: Subclass doesn't implement required abstract method; compilation fails.

```java
// ❌ Bad: didn't implement getPrice()
public class MyTicket extends Ticket {
    // Error: must implement abstract method getPrice()
}

// ✅ Good: implement all abstract methods
public class MyTicket extends Ticket {
    @Override
    public double getPrice() {
        return 25.0;
    }
}
```

### Pitfall 6: Confusing `implements` vs. `extends`
**Problem**: Using wrong keyword (e.g., `class Octagon extends Shape` when Shape is an interface).

```java
// ❌ Bad: Shape is an interface, not a class
public class Octagon extends Shape { ... }  // Error

// ✅ Good: use implements for interfaces
public class Octagon implements Shape { ... }
```

### Pitfall 7: Violating Liskov Substitution Principle (LSP)
**Problem**: Subclass behavior is incompatible with parent contract; polymorphism breaks.

```java
// ❌ Bad: StudentAdvanceTicket.getPrice() might throw exception
// violating Ticket's contract that getPrice() always returns price
public class StudentAdvanceTicket extends Ticket {
    @Override
    public double getPrice() {
        if (daysInAdvance < 0) throw new IllegalArgumentException();  // dangerous!
        return (daysInAdvance >= 10) ? 15.0 : 20.0;
    }
}

// ✅ Good: subclass respects parent's contract
public class StudentAdvanceTicket extends Ticket {
    @Override
    public double getPrice() {
        // Never throw checked exceptions parent doesn't declare
        // Always return a valid price
        return (daysInAdvance >= 10) ? 15.0 : 20.0;
    }
}
```

### Pitfall 8: Redundantly Re-implementing Inherited Methods
**Problem**: Subclass copies parent's implementation instead of using inheritance.

```java
// ❌ Bad: Marketer duplicates showHours() from Employee
public class Marketer extends Employee {
    @Override
    public void showHours() {
        System.out.println("I work 40 hours per week.");  // same as parent!
    }
}

// ✅ Good: only override what's different
public class Marketer extends Employee {
    @Override
    public void showSalary() {
        System.out.println("My salary is $50,000.");  // different from parent
    }
    // inherit showHours() as-is
}
```

---

## Suggested refactors & improvements

### 1. Use `protected` Instead of Private for Fields Subclasses Need
```java
// Before: private; subclass can't access
public abstract class Ticket {
    private int ticketNumber;
}

// After: protected; subclass can access
public abstract class Ticket {
    protected int ticketNumber;  // accessible to subclasses
}
```

### 2. Separate UI from Business Logic
```java
// Before: methods print to console
public class Employee {
    public void showSalary() {
        System.out.println("My salary is $40,000.");
    }
}

// After: methods return data; UI code handles printing
public class Employee {
    public int getSalary() {
        return 40000;
    }
}

public class EmployeeMain {
    public static void main(String[] args) {
        System.out.println("Salary: " + employee.getSalary());
    }
}
```

### 3. Add Polymorphic Collections
```java
// Before: separate variables for each subclass
Marketer marketer = new Marketer();
Janitor janitor = new Janitor();

// After: polymorphic list
List<Employee> employees = new ArrayList<>();
employees.add(new Marketer());
employees.add(new Janitor());

for (Employee emp : employees) {
    emp.showSalary();  // calls subclass's version
}
```

### 4. Use Enums for Constants (Ticket Days Threshold)
```java
// Before: magic number
public class AdvanceTicket extends Ticket {
    @Override
    public double getPrice() {
        if (daysInAdvance >= 10) {  // magic 10
            return 30.0;
        }
    }
}

// After: named constant
public abstract class Ticket {
    protected static final int ADVANCE_THRESHOLD = 10;
}

public class AdvanceTicket extends Ticket {
    @Override
    public double getPrice() {
        if (daysInAdvance >= ADVANCE_THRESHOLD) {
            return 30.0;
        }
    }
}
```

### 5. Add Documentation and Javadoc
```java
/**
 * Abstract base class for event tickets.
 * Subclasses must implement pricing logic via getPrice().
 */
public abstract class Ticket {
    /**
     * Returns the price of this ticket.
     * @return price in dollars
     */
    public abstract double getPrice();
}
```

### 6. Use Method References and Streams for Polymorphic Operations
```java
// Before: explicit loop
List<Ticket> tickets = Arrays.asList(
    new WalkUpTicket(1),
    new AdvanceTicket(2, 15)
);
for (Ticket t : tickets) {
    System.out.println(t);
}

// After: streams
tickets.stream()
       .map(Ticket::toString)
       .forEach(System.out::println);
```

---

## Practice exercises (next steps)

1. **Add a third Employee type**: Create `Lawyer` class with $100,000 salary and 4 weeks vacation.
2. **Create a Ticket summary method**: Add `static void printTicketInfo(Ticket t)` to show all ticket details.
3. **Implement a polymorphic Ticket array**: Create array of mixed ticket types; calculate total revenue.
4. **Add Shape hierarchy extensions**: Create `Triangle`, `Circle`, `Rectangle` implementing Shape interface.
5. **Create a discount system**: Add `applyDiscount(double percent)` method to Ticket hierarchy; track discounted prices.
6. **Polymorphic employee manager**: Create an `EmployeeManager` class that manages employees polymorphically (add, remove, list, calculate payroll).
7. **Test polymorphism thoroughly**: Write unit tests verifying that each subclass's overridden methods work correctly.
8. **Refactor Employee to return data**: Change all `show*` methods to return Strings; separate UI printing from business logic.

---

## Quick reference — inheritance and polymorphism terminology

### Inheritance Keywords
```java
// Extend a class (single inheritance)
public class Marketer extends Employee { ... }

// Implement an interface (multiple allowed)
public class Octagon implements Shape { ... }

// Call parent constructor
public Marketer() { super(); }

// Call parent method
super.showSalary();

// Mark method as intentionally overriding parent
@Override
public void showSalary() { ... }
```

### Method Overriding Rules
- ✅ Same method name
- ✅ Same parameters (type, number, order)
- ✅ Same or covariant return type (can narrow in subclass)
- ✅ Access modifier can be same or broader (not narrower)
- ✅ Cannot reduce exception scope (can throw fewer exceptions)

### Access Modifiers and Inheritance
| Modifier | Class | Subclass (same package) | Subclass (different package) | Outside |
|----------|-------|---------|---------|---------|
| `public` | ✅ | ✅ | ✅ | ✅ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| (no modifier) | ✅ | ✅ | ❌ | ❌ |
| `private` | ✅ | ❌ | ❌ | ❌ |

### Polymorphism Key Concepts
- **Compile-time type** (reference type): What the variable is declared as
- **Runtime type** (object type): What the object actually is
- **Method dispatch**: Runtime type determines which method is called
- **Liskov Substitution Principle**: Subclass can substitute for parent without breaking program

---

## Code examples

### Example 1: Complete Ticket Hierarchy
```java
// Abstract base class
public abstract class Ticket {
    protected static final int ADVANCE_THRESHOLD = 10;
    private final int ticketNumber;
    
    public Ticket(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
    
    public abstract double getPrice();
    
    protected int getTicketNumber() {
        return ticketNumber;
    }
    
    @Override
    public String toString() {
        return String.format("Number: %d, Price: $%.2f", ticketNumber, getPrice());
    }
}

// Walk-up ticket
public class WalkUpTicket extends Ticket {
    private static final double WALKUP_PRICE = 50.0;
    
    public WalkUpTicket(int ticketNumber) {
        super(ticketNumber);
    }
    
    @Override
    public double getPrice() {
        return WALKUP_PRICE;
    }
}

// Advance ticket
public class AdvanceTicket extends Ticket {
    private static final double EARLY_PRICE = 30.0;
    private static final double LATE_PRICE = 40.0;
    private final int daysInAdvance;
    
    public AdvanceTicket(int ticketNumber, int daysInAdvance) {
        super(ticketNumber);
        this.daysInAdvance = daysInAdvance;
    }
    
    @Override
    public double getPrice() {
        return (daysInAdvance >= ADVANCE_THRESHOLD) ? EARLY_PRICE : LATE_PRICE;
    }
}

// Student advance ticket with enhanced toString
public class StudentAdvanceTicket extends AdvanceTicket {
    private static final double STUDENT_EARLY = 15.0;
    private static final double STUDENT_LATE = 20.0;
    
    public StudentAdvanceTicket(int ticketNumber, int daysInAdvance) {
        super(ticketNumber, daysInAdvance);
    }
    
    @Override
    public double getPrice() {
        return (daysInAdvance >= ADVANCE_THRESHOLD) ? STUDENT_EARLY : STUDENT_LATE;
    }
    
    @Override
    public String toString() {
        return super.toString() + " Must show Student ID";
    }
}
```

### Example 2: Polymorphic Ticket Manager
```java
public class TicketManager {
    private List<Ticket> tickets = new ArrayList<>();
    
    public void addTicket(Ticket t) {
        tickets.add(t);
    }
    
    public double getTotalRevenue() {
        return tickets.stream()
                     .mapToDouble(Ticket::getPrice)
                     .sum();
    }
    
    public void printAllTickets() {
        tickets.forEach(System.out::println);
    }
}

// Usage
TicketManager manager = new TicketManager();
manager.addTicket(new WalkUpTicket(1));
manager.addTicket(new AdvanceTicket(2, 15));
manager.addTicket(new StudentAdvanceTicket(3, 5));

System.out.println("Total revenue: $" + manager.getTotalRevenue());
```

### Example 3: Enhanced Employee Hierarchy with Refactoring
```java
public abstract class Employee {
    protected String name;
    
    public Employee(String name) {
        this.name = name;
    }
    
    public String getName() { return name; }
    
    public abstract int getHoursPerWeek();
    public abstract int getSalary();
    public abstract int getVacationDays();
    
    public void applyForVacation() {
        System.out.println("Use the yellow vacation form.");
    }
    
    @Override
    public String toString() {
        return String.format("%s: %d hrs/week, $%d/year, %d days vacation",
                           name, getHoursPerWeek(), getSalary(), getVacationDays());
    }
}

public class Marketer extends Employee {
    private static final int MARKETER_SALARY = 50000;
    private static final int STANDARD_HOURS = 40;
    private static final int STANDARD_VACATION = 14;
    
    public Marketer(String name) {
        super(name);
    }
    
    @Override
    public int getHoursPerWeek() { return STANDARD_HOURS; }
    
    @Override
    public int getSalary() { return MARKETER_SALARY; }
    
    @Override
    public int getVacationDays() { return STANDARD_VACATION; }
    
    public void advertise() {
        System.out.println("Act now, while supplies last!");
    }
}

public class Janitor extends Employee {
    private static final int JANITOR_SALARY = 30000;
    private static final int JANITOR_HOURS = 80;
    private static final int JANITOR_VACATION = 5;
    
    public Janitor(String name) {
        super(name);
    }
    
    @Override
    public int getHoursPerWeek() { return JANITOR_HOURS; }
    
    @Override
    public int getSalary() { return JANITOR_SALARY; }
    
    @Override
    public int getVacationDays() { return JANITOR_VACATION; }
    
    public void clean() {
        System.out.println("Workin' for the man");
    }
}
```

---

## Mini-quiz (self-check)

1. **Why is Ticket abstract and not concrete?**
   - Answer: Each ticket type has different pricing logic. Abstract ensures subclasses implement `getPrice()` and prevents accidentally creating a generic Ticket with no price.

2. **What does `super()` do in AdvanceTicket constructor?**
   - Answer: Calls the parent class (Ticket) constructor to initialize ticketNumber field.

3. **If Marketer doesn't override `showHours()`, what happens?**
   - Answer: Marketer inherits Employee's `showHours()` and uses that (prints "I work 40 hours per week.").

4. **What is the difference between `extends` (Ticket) and `implements` (Shape)?**
   - Answer: `extends` inherits from a class (share state/implementation); `implements` realizes an interface (contract only).

5. **How does polymorphism work in ShapesMain?**
   - Answer: Reference type is `Shape`; actual object type is `Octagon` or `Hexagon`. Calls to `getArea()` invoke the subclass's implementation.

6. **Can StudentAdvanceTicket inherit from AdvanceTicket?**
   - Answer: Yes (yes in the improved example). It would inherit `daysInAdvance` and override `getPrice()` with student rates.

7. **What is the Liskov Substitution Principle?**
   - Answer: Subclass must be usable anywhere parent is expected. `Marketer` should be usable wherever `Employee` is used without breaking code.

8. **If a subclass doesn't implement an abstract method, what happens?**
   - Answer: Compilation error; abstract methods must be implemented by concrete subclasses.

---

## Master checklist — before submitting

- [ ] **Inheritance**: Correct use of `extends` (classes) vs. `implements` (interfaces)
- [ ] **Constructors**: All call `super()` first; parent fields initialized
- [ ] **@Override**: Annotation used consistently for overridden methods
- [ ] **Abstract classes**: Marked `abstract` if they have abstract methods or shouldn't be instantiated
- [ ] **Abstract methods**: All abstract methods implemented by concrete subclasses
- [ ] **Access modifiers**: Fields `protected` or `private` (not public); use appropriate level
- [ ] **Method overriding**: Same signature as parent; no errors from typos
- [ ] **Polymorphism**: Verified that subclass methods are called via parent references
- [ ] **toString()**: Implemented consistently; uses `super.toString()` where appropriate
- [ ] **No redundant code**: Subclasses don't re-implement inherited behavior unchanged
- [ ] **Liskov Substitution**: Subclass behavior respects parent contract
- [ ] **Testing**: Verified each subclass behaves correctly in polymorphic contexts

---

## Key Takeaways

1. **Inheritance enables code reuse** — subclasses inherit and extend parent functionality.
2. **Abstract classes define contracts** — force subclasses to implement required methods.
3. **Interfaces define pure contracts** — no shared implementation; multiple implementations allowed.
4. **Method overriding** replaces inherited method with subclass-specific version.
5. **Polymorphism** — use runtime type (object's actual class), not compile-time type (reference type).
6. **`super` keyword** calls parent class methods/constructors; `this` refers to current instance.
7. **Constructor chaining** — subclass `super()` calls parent constructor; must be first statement.
8. **Access modifiers matter** — `private` hidden from subclasses; `protected` visible; `public` visible everywhere.
9. **Liskov Substitution Principle** — subclass must be substitutable for parent without breaking code.
10. **Separate concerns** — business logic separate from UI; polymorphism enables flexible design.

---

**Last Updated:** 2026-07-24  
**Author:** Generated from Chapter 9 source files in repository TGSpring/Java-Book-Review  
**Status:** Complete with 3 hierarchies (Ticket, Employee, Shape), detailed walkthroughs, inheritance patterns, pitfalls, and comprehensive reference material
