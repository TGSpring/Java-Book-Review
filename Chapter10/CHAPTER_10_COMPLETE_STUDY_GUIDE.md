# Chapter 10 — Complete Study Guide

> Focus: ArrayLists (dynamic arrays), generics, the Comparable interface, collections algorithms, and practical applications with real-world projects (vowel counting, rectangle overlapping, GUI window management).

---

## Table of Contents
- Short answer review (2 sections, key questions)
- Core concepts explained
- Programming problems & walkthroughs
  - Chpt10_Q1 — averageVowels (ArrayList iteration, vowel counting)
  - Chpt10_Q2 — interLeave (ArrayList manipulation)
- Project: Overlapping rectangles
  - Rectangle class with containment logic
  - Point class
  - topMost algorithm and GUI window z-ordering concepts
  - Design trade-offs (ArrayList vs. LinkedList)
- ArrayList operations reference
- Common pitfalls and how to fix them
- Suggested refactors & improvements
- Practice exercises
- Quick reference (ArrayList and Comparable)
- Code examples
- Mini-quiz
- Master checklist

---

## Short answer review

### Section 10.1: ArrayLists

**Question 1: What is an ArrayList? In what cases should you use an ArrayList rather than an array?**

**ArrayList definition**:
- A resizable array implementation from `java.util` package
- Dynamically grows/shrinks as elements are added/removed
- Type-safe when using generics: `ArrayList<String>`, `ArrayList<Integer>`
- Provides convenient methods: `add()`, `remove()`, `get()`, `size()`, `contains()`, etc.
- Backed by a regular array internally; when full, creates larger array and copies

**When to use ArrayList**:
- ✅ Size unknown at compile time
- ✅ Frequently adding/removing elements
- ✅ Want convenient built-in methods (`add`, `remove`, `sort`, etc.)
- ✅ Building a list from input or dynamic operations
- ❌ Size fixed and performance-critical (use array instead)
- ❌ Frequently inserting/removing at beginning (use LinkedList instead)

**When to use Array**:
- ✅ Size known and fixed at compile time
- ✅ Performance-critical; want minimal overhead
- ✅ Memory-constrained; fixed size is more efficient
- ✅ Working with primitive types (more efficient than ArrayList<Integer>)
- ❌ Need to resize; would require manual array copying

**Example**:
```java
// ArrayList: good for dynamic list
ArrayList<String> events = new ArrayList<>();
events.add("Monday");
events.add("Tuesday");
events.add("Wednesday");  // can keep adding

// Array: good for fixed list
String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
// size is known and fixed
```

---

### Section 10.2: The Comparable Interface

**Question 19: Describe how to arrange an ArrayList into sorted order. What must be true about the type of elements in the list in order to sort it?**

**How to sort an ArrayList**:

**Option 1: Using `Collections.sort()` (static method)**
```java
ArrayList<String> names = new ArrayList<>();
names.add("Zoe");
names.add("Alice");
names.add("Bob");

Collections.sort(names);  // sorts in-place
// Result: [Alice, Bob, Zoe]
```

**Option 2: Using `list.sort()` (instance method, Java 8+)**
```java
names.sort(null);  // null means use natural ordering (Comparable)
```

**Option 3: Using a Comparator (custom ordering)**
```java
names.sort((a, b) -> b.compareTo(a));  // reverse alphabetical
// Result: [Zoe, Bob, Alice]
```

**Requirements for sorting**:

**Option A: Elements implement Comparable**
- Class defines `compareTo()` method
- Establishes "natural ordering" for that type
- Built-in types (String, Integer, Double) already implement Comparable
- Example:
```java
public class Student implements Comparable<Student> {
    private String name;
    private double gpa;
    
    @Override
    public int compareTo(Student other) {
        return Double.compare(this.gpa, other.gpa);  // sort by GPA
    }
}

ArrayList<Student> students = new ArrayList<>();
// ... add students
Collections.sort(students);  // uses Student.compareTo()
```

**Option B: Provide a Comparator**
- Separate comparison function
- Allows multiple sorting strategies for same type
- Example:
```java
ArrayList<Student> students = new ArrayList<>();
// Sort by name (different from natural GPA ordering)
students.sort((s1, s2) -> s1.getName().compareTo(s2.getName()));
```

**Key insight**: Comparable = natural ordering (one way); Comparator = flexible ordering (many ways).

---

## Core concepts explained

### ArrayList Basics
```java
ArrayList<T> list = new ArrayList<>();  // T is type parameter (generic)

// Common operations (all O(1) amortized except where noted)
list.add(element);                      // add to end
list.add(index, element);               // insert at index (O(n) worst-case; elements shift)
list.remove(index);                     // remove at index (O(n) worst-case)
list.get(index);                        // retrieve element at index
list.size();                            // number of elements
list.contains(element);                 // O(n) linear search
list.clear();                           // remove all
list.isEmpty();                         // check if empty
```

### Generics
**Type safety**: Prevents `ClassCastException` by checking types at compile time.
```java
// Without generics (old style; unsafe)
ArrayList list = new ArrayList();
list.add("hello");
String s = (String) list.get(0);  // requires cast; can fail

// With generics (modern style; safe)
ArrayList<String> list = new ArrayList<>();
list.add("hello");
String s = list.get(0);  // no cast needed; compiler guarantees type
```

### Comparable vs. Comparator

| Aspect | Comparable | Comparator |
|--------|-----------|-----------|
| **Interface** | Comparable<T> | Comparator<T> |
| **Method** | `compareTo(T other)` | `compare(T a, T b)` |
| **Purpose** | Define natural ordering | Define custom ordering |
| **Multiple strategies** | One per class | Many possible |
| **Example** | String, Integer | custom lambda or class |
| **Usage** | `Collections.sort(list)` | `list.sort(comparator)` |

### Generic Collections
```java
// Type parameter T ensures type safety
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(5);
numbers.add(10);
int sum = 0;
for (int n : numbers) {  // safe; compiler knows it's int
    sum += n;
}

// Polymorphism works with generics
ArrayList<Comparable<?>> mixed = new ArrayList<>();
mixed.add("hello");      // String is Comparable
mixed.add(42);           // Integer is Comparable
```

---

## Programming Problems & Walkthroughs

### Chpt10_Q1: averageVowels

**Problem**: Given an ArrayList of strings, return the average number of vowel characters across all strings. Return 0.0 for empty list.

**Example**: `["testing", "hello", "goodbye", "bingo", "underway"]`
- "testing": 2 vowels (e, i)
- "hello": 2 vowels (e, o)
- "goodbye": 3 vowels (o, o, e)
- "bingo": 2 vowels (i, o)
- "underway": 3 vowels (u, e, a)
- Total: 12 vowels / 5 strings = 2.4 average

**Approach**:

1. **Edge case**: If ArrayList is empty, return 0.0
2. **Iterate** through each string in the ArrayList
3. **For each string**: Iterate through each character
4. **Check** if character is a vowel (a, e, i, o, u, case-insensitive)
5. **Count** total vowels
6. **Divide** total vowels by number of strings (cast to double to avoid integer division)

**Code**:
```java
public static double averageVowels(ArrayList<String> vowels) {
    int totalVowels = 0;
    
    if (vowels.isEmpty()) {
        return 0.0;
    }
    
    // Iterate each string
    for (String v : vowels) {
        // Iterate each character in string
        for (int i = 0; i < v.length(); i++) {
            char ch = v.charAt(i);
            // Check if vowel (case-insensitive)
            if ("aeiouAEIOU".indexOf(ch) >= 0) {
                totalVowels++;
            }
        }
    }
    
    // Cast to double to avoid integer division
    return (double) totalVowels / vowels.size();
}
```

**Key techniques**:
- **Vowel check**: `"aeiouAEIOU".indexOf(ch) >= 0` returns ≥0 if ch is found, -1 otherwise
- **Case-insensitive**: Include both uppercase and lowercase vowels in string
- **Type casting**: `(double) totalVowels` ensures floating-point division
- **Empty check**: Handle edge case of empty ArrayList upfront

**Complexity**:
- **Time**: O(n*m) where n = number of strings, m = average string length
- **Space**: O(1) — only tracking count variable

**Q&A from code**:

*Q1: Why use ArrayList<String> instead of array?*
- Dynamic size; easy to test with different sizes without pre-allocating array

*Q3: Why cast to double before dividing?*
- Integer division truncates: 7/3 = 2 (wrong); 7.0/3 = 2.333... (correct)

*Q5: Time complexity and improvements?*
- Currently O(n*m) where n = strings, m = chars per string. Can't be improved fundamentally (must check every character); optimization: cache `vowels.size()` instead of calling in loop

---

### Chpt10_Q2: interLeave

**Problem**: Given two ArrayLists, interleave them (alternate elements from each).

**Example**: `[1, 2, 3]` and `[4, 5, 6]` → `[1, 4, 2, 5, 3, 6]`

**Edge cases**:
- What if lists are different sizes? (Common approach: alternate until one is exhausted, then append remaining)
- Example: `[1, 2]` and `[3, 4, 5]` → `[1, 3, 2, 4, 5]`

**Approach**:

1. Loop while both lists have elements
2. Alternately add from each list (often add to a result list)
3. Handle remaining elements from longer list

**Example implementation**:
```java
public static void interLeave(ArrayList<Integer> a1, ArrayList<Integer> a2) {
    // Option 1: Modify a1 in-place (insert into a1)
    int a2Index = 0;
    for (int i = 0; i < a1.size() && a2Index < a2.size(); i += 2) {
        a1.add(i + 1, a2.get(a2Index));  // insert element from a2
        a2Index++;
    }
    
    // Append remaining elements from a2
    a1.addAll(a2.subList(a2Index, a2.size()));
}

// Or Option 2: Create new result list (cleaner)
public static ArrayList<Integer> interLeave(ArrayList<Integer> a1, ArrayList<Integer> a2) {
    ArrayList<Integer> result = new ArrayList<>();
    int i = 0, j = 0;
    
    // Alternate from both lists
    while (i < a1.size() && j < a2.size()) {
        result.add(a1.get(i++));
        result.add(a2.get(j++));
    }
    
    // Append remaining from a1
    while (i < a1.size()) {
        result.add(a1.get(i++));
    }
    
    // Append remaining from a2
    while (j < a2.size()) {
        result.add(a2.get(j++));
    }
    
    return result;
}
```

**Complexity**:
- **Time**: O(n + m) where n = a1.size(), m = a2.size()
- **Space**: O(n + m) if creating new list (Option 2); O(1) if modifying in-place (Option 1, but insertion is O(n))

**Pitfall**: In-place insertion into middle of ArrayList is O(n) per insertion due to element shifting. Creating a new list is cleaner.

---

## Project: Overlapping Rectangles

**Problem**: Given rectangles and a point, find the topmost rectangle containing that point. Useful for GUI window managers (z-ordering).

### Rectangle Class

**Purpose**: Represent a rectangle with position and dimensions; check if point is inside.

**Fields**:
- `private int x, y` — top-left corner coordinates
- `private int height, width` — dimensions

**Constructor**:
```java
public Rectangle(int x, int y, int height, int width) {
    this.x = x;
    this.y = y;
    this.height = height;
    this.width = width;
}
```

**Key method: `contains(Point p)`**

Checks if point `p` is inside rectangle.

**Logic**:
- Point is inside if: `p.x >= rect.x AND p.x <= rect.x + width AND p.y >= rect.y AND p.y <= rect.y + height`

```java
public boolean contains(Point p) {
    return p.getX() >= x && p.getX() <= x + width &&
           p.getY() >= y && p.getY() <= y + height;
}
```

**Complexity**: O(1) — constant comparison operations

**Method: `toString()`**

Returns string representation for debugging.

```java
@Override
public String toString() {
    return "Rectangle[x=" + x + ", y=" + y + ", w=" + width + ", h=" + height + "]";
}
```

---

### Point Class

**Purpose**: Represent a 2D point.

**Fields**:
- `private int x, y` — coordinates

**Constructor & Accessors**:
```java
public class Point {
    private int x, y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
}
```

**Complexity**: O(1) for all operations.

---

### Overlapping Algorithm (topMost)

**Problem**: Given a list of overlapping rectangles and a point, find the topmost rectangle containing that point.

**Key insight**: Rectangles are stored in z-order (bottom-to-top). The **last** rectangle in the list is the topmost (drawn last in GUI).

**Approach**:
1. Iterate **backwards** through rectangles (from top to bottom)
2. Check if point is contained in rectangle
3. Return the first match (which is topmost)

```java
public static Rectangle topMost(ArrayList<Rectangle> rects, Point p) {
    // Iterate backwards; last rectangle (topmost) checked first
    for (int i = rects.size() - 1; i >= 0; i--) {
        if (rects.get(i).contains(p)) {
            return rects.get(i);  // Found topmost
        }
    }
    return null;  // Point not in any rectangle
}
```

**Complexity**: O(n) where n = number of rectangles (worst-case: point not in any).

**Why backwards iteration?**
- Rectangles ordered bottom-to-top in list
- Last = topmost (like GUI window stacking)
- Iterate backwards to check topmost first
- Return immediately upon finding match

**Real-world application**: GUI window managers (e.g., OS desktop)
- User clicks a point
- Check from top window downward
- Return the topmost window containing click
- Only that window receives mouse event

---

### Design Trade-offs: ArrayList vs. LinkedList

From Review.txt:

**ArrayList Advantages**:
- ✅ Fast random access: `get(index)` is O(1)
- ✅ Easy to implement (backed by array)
- ✅ Good cache locality (contiguous memory)
- ✅ Suitable for z-ordering: `add()` to end is O(1)

**ArrayList Disadvantages**:
- ❌ Inserting/removing in middle: O(n) due to element shifting
- ❌ Memory usage: may allocate more than needed

**LinkedList Advantages**:
- ✅ Fast insertion/removal anywhere: O(1) once positioned
- ✅ Better memory efficiency for sparse structures

**LinkedList Disadvantages**:
- ❌ Slow random access: `get(index)` is O(n)
- ❌ Higher memory overhead (pointers)
- ❌ Poor cache locality

**For GUI windows (Overlapping project)**:
- ArrayList is perfect
- Number of windows typically small (≤100)
- Frequent random access (checking containment)
- Infrequent insertion/removal (usually append new window to top)
- Conclusion: ArrayList is best choice

---

## ArrayList Operations Reference

### Adding Elements
```java
list.add(element);                    // add to end (O(1) amortized)
list.add(index, element);             // insert at index (O(n) worst-case)
list.addAll(otherList);               // add all from another collection
list.addAll(index, otherList);        // insert all at index
```

### Removing Elements
```java
list.remove(index);                   // remove by index (O(n) worst-case)
list.remove(element);                 // remove by value (O(n), first occurrence)
list.removeAll(collection);           // remove all matching values
list.clear();                         // remove all elements
```

### Querying
```java
list.get(index);                      // get element at index
list.indexOf(element);                // index of first occurrence (-1 if not found)
list.lastIndexOf(element);            // index of last occurrence
list.contains(element);               // O(n) linear search
list.size();                          // number of elements
list.isEmpty();                       // check if empty
```

### Iteration
```java
// Enhanced for-loop (recommended)
for (String s : list) {
    System.out.println(s);
}

// Index-based loop
for (int i = 0; i < list.size(); i++) {
    System.out.println(list.get(i));
}

// Iterator (good for removal during iteration)
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    String s = it.next();
    if (shouldRemove(s)) {
        it.remove();  // safe removal
    }
}

// Stream API (Java 8+)
list.stream()
    .filter(s -> s.length() > 5)
    .forEach(System.out::println);
```

### Sorting
```java
Collections.sort(list);               // in-place sort (uses Comparable)
list.sort(null);                      // Java 8+, null = use Comparable
list.sort(comparator);                // sort with custom Comparator
Collections.reverse(list);            // reverse order
Collections.shuffle(list);            // random order
```

---

## Common pitfalls and how to fix them

### Pitfall 1: Integer Division When Computing Average
**Problem**: Dividing two integers gives integer result; truncates decimal.

```java
// ❌ Bad: integer division
int totalVowels = 12;
int count = 5;
double avg = totalVowels / count;  // 12 / 5 = 2 (wrong!)

// ✅ Good: cast to double first
double avg = (double) totalVowels / count;  // 12.0 / 5 = 2.4
```

### Pitfall 2: Forgetting Generic Type Parameter
**Problem**: ArrayList without generic type is unsafe; requires casts.

```java
// ❌ Bad: raw type (unsafe, generates compiler warning)
ArrayList list = new ArrayList();
list.add("hello");
list.add(42);  // oops, mixed types
String s = (String) list.get(1);  // ClassCastException at runtime!

// ✅ Good: use generic type
ArrayList<String> list = new ArrayList<>();
list.add("hello");
// list.add(42);  // compiler error; prevents bug
String s = list.get(0);  // safe, no cast
```

### Pitfall 3: Modifying ArrayList During Iteration
**Problem**: Adding/removing elements while iterating causes `ConcurrentModificationException`.

```java
// ❌ Bad: modifying during enhanced for loop
for (String s : list) {
    if (s.length() < 3) {
        list.remove(s);  // ConcurrentModificationException!
    }
}

// ✅ Good: use Iterator.remove()
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    String s = it.next();
    if (s.length() < 3) {
        it.remove();  // safe removal through iterator
    }
}

// ✅ Or: collect removals, then remove after loop
ArrayList<String> toRemove = new ArrayList<>();
for (String s : list) {
    if (s.length() < 3) {
        toRemove.add(s);
    }
}
list.removeAll(toRemove);
```

### Pitfall 4: Assuming 0-Based Indexing Issues
**Problem**: Off-by-one errors when accessing ArrayList by index.

```java
// ❌ Bad: accessing beyond bounds
ArrayList<Integer> list = new ArrayList<>();
list.add(10);
list.add(20);
System.out.println(list.get(2));  // IndexOutOfBoundsException! Only indices 0, 1

// ✅ Good: check bounds or use size()
if (index >= 0 && index < list.size()) {
    System.out.println(list.get(index));
}
```

### Pitfall 5: Not Handling Empty Collections
**Problem**: Operating on empty ArrayList without checking; `get()` throws exception.

```java
// ❌ Bad: assume non-empty
double avg = totalVowels / vowels.size();  // ArithmeticException if empty!

// ✅ Good: check empty first
if (vowels.isEmpty()) {
    return 0.0;
} else {
    return totalVowels / vowels.size();
}
```

### Pitfall 6: Inefficient ArrayList Insertion in Loop
**Problem**: Repeatedly inserting at index 0 or middle causes O(n²) behavior.

```java
// ❌ Bad: O(n²) — shifting elements each time
ArrayList<Integer> list = new ArrayList<>();
for (int i = 0; i < 10000; i++) {
    list.add(0, i);  // O(n) each time; total O(n²)
}

// ✅ Good: build result separately, add at end
ArrayList<Integer> temp = new ArrayList<>();
for (int i = 0; i < 10000; i++) {
    temp.add(i);  // O(1)
}
Collections.reverse(temp);  // O(n) once
```

### Pitfall 7: Not Casting Before Generic Type Operations
**Problem**: Forgetting to cast when element needs to be treated as subclass.

```java
// ❌ Bad: no cast; compilation error
ArrayList<Object> objects = new ArrayList<>();
objects.add(new Student("Alice", 3.8));
String name = objects.get(0).getName();  // Error: Object has no getName()

// ✅ Good: cast to correct type
Student student = (Student) objects.get(0);
String name = student.getName();

// ✅ Better: use ArrayList<Student> instead
ArrayList<Student> students = new ArrayList<>();
students.add(new Student("Alice", 3.8));
String name = students.get(0).getName();  // No cast needed
```

### Pitfall 8: Comparing Strings with `==` Instead of `.equals()`
**Problem**: `==` checks reference equality (same object), not value equality.

```java
// ❌ Bad: reference comparison
ArrayList<String> words = new ArrayList<>();
words.add("hello");
if (words.get(0) == "hello") { ... }  // might be false! (different String objects)

// ✅ Good: value comparison
if (words.get(0).equals("hello")) { ... }
```

---

## Suggested refactors & improvements

### 1. Use Streams for Vowel Counting (Java 8+)
```java
// Before: explicit loops
public static double averageVowels(ArrayList<String> vowels) {
    int total = 0;
    for (String v : vowels) {
        for (int i = 0; i < v.length(); i++) {
            if ("aeiouAEIOU".indexOf(v.charAt(i)) >= 0) {
                total++;
            }
        }
    }
    return vowels.isEmpty() ? 0.0 : (double) total / vowels.size();
}

// After: functional style
public static double averageVowels(ArrayList<String> vowels) {
    if (vowels.isEmpty()) return 0.0;
    
    long vowelCount = vowels.stream()
                            .mapToLong(s -> s.chars()
                                            .filter(c -> "aeiouAEIOU".indexOf(c) >= 0)
                                            .count())
                            .sum();
    return (double) vowelCount / vowels.size();
}
```

### 2. Extract Vowel Check to Separate Method
```java
private static boolean isVowel(char c) {
    return "aeiouAEIOU".indexOf(c) >= 0;
}

public static double averageVowels(ArrayList<String> vowels) {
    if (vowels.isEmpty()) return 0.0;
    
    int total = 0;
    for (String v : vowels) {
        for (int i = 0; i < v.length(); i++) {
            if (isVowel(v.charAt(i))) {
                total++;
            }
        }
    }
    return (double) total / vowels.size();
}
```

### 3. Use Comparable for Rectangle Z-Ordering
```java
// Make Rectangle implement Comparable (by z-order)
public class Rectangle implements Comparable<Rectangle> {
    private int x, y, height, width;
    private int zOrder;  // z-order value
    
    @Override
    public int compareTo(Rectangle other) {
        return Integer.compare(this.zOrder, other.zOrder);
    }
}

// Now can sort easily
Collections.sort(rectangles);  // sort by z-order
```

### 4. Use Generics in topMost to Handle Different Point Types
```java
// Generic version (more flexible)
public static <T extends Shape> T topMost(ArrayList<T> shapes, Point p) {
    for (int i = shapes.size() - 1; i >= 0; i--) {
        if (shapes.get(i).contains(p)) {
            return shapes.get(i);
        }
    }
    return null;
}
```

### 5. Add Logging for Debugging
```java
public static Rectangle topMost(ArrayList<Rectangle> rects, Point p) {
    System.out.println("Finding topmost rectangle for point: (" + p.getX() + ", " + p.getY() + ")");
    for (int i = rects.size() - 1; i >= 0; i--) {
        System.out.println("Checking: " + rects.get(i));
        if (rects.get(i).contains(p)) {
            System.out.println("Found topmost: " + rects.get(i));
            return rects.get(i);
        }
    }
    System.out.println("No rectangle contains point");
    return null;
}
```

### 6. Create Immutable Point Class
```java
public final class Point {
    private final int x, y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) return false;
        Point p = (Point) obj;
        return this.x == p.x && this.y == p.y;
    }
    
    @Override
    public int hashCode() {
        return 31 * x + y;
    }
    
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
```

---

## Practice exercises (next steps)

1. **Extend averageVowels**: Add a method `consonantRatio()` that returns the percentage of consonants (vs. all letters).
2. **Implement interLeave in reverse**: Given interleaved list, "deinterleave" it back to two lists.
3. **Rectangle extensions**:
   - Add `overlaps(Rectangle other)` to check if two rectangles overlap
   - Add `area()` and `perimeter()` methods
   - Add `translate(int dx, int dy)` to move rectangle
4. **Advanced topMost**:
   - Return **all** rectangles containing a point (not just topmost)
   - Add `bottomMost()` for bottommost rectangle
5. **Sorting with Comparable**:
   - Make Rectangle implement Comparable by area
   - Sort a list of rectangles and display in order
6. **GUI Window Simulation**:
   - Create a WindowManager class
   - Simulate mouse clicks; return topmost window
   - Add `bringToFront(Rectangle)` to move window to top
7. **Performance comparison**:
   - Benchmark ArrayList vs. LinkedList for interleave operations
   - Test with 1000, 10000, 100000 elements
8. **Unit tests**:
   - Write JUnit tests for averageVowels, interLeave, topMost
   - Test edge cases (empty lists, single elements, identical items)

---

## Quick reference — ArrayList and Comparable

### ArrayList Cheat Sheet
```java
// Creation and basic operations
ArrayList<String> list = new ArrayList<>();
list.add("item");
list.remove(0);
list.get(0);
list.size();

// Iteration patterns
for (String s : list) { ... }                    // enhanced for
for (int i = 0; i < list.size(); i++) { ... }  // index-based
list.forEach(System.out::println);              // method reference

// Sorting
Collections.sort(list);                         // natural order
list.sort((a, b) -> b.compareTo(a));           // reverse order
Collections.reverse(list);                      // reverse list

// Searching
Collections.binarySearch(list, target);         // requires sorted list
list.indexOf(element);                          // linear search
list.contains(element);                         // O(n) search
```

### Comparable vs. Comparator
```java
// Comparable: natural ordering
public class Student implements Comparable<Student> {
    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.gpa, other.gpa);  // return -1, 0, or 1
    }
}
Collections.sort(students);  // uses compareTo

// Comparator: custom ordering
Comparator<Student> byName = (s1, s2) -> s1.getName().compareTo(s2.getName());
students.sort(byName);

// Lambda shorthand
students.sort((s1, s2) -> Integer.compare(s1.getGPA(), s2.getGPA()));
```

### Generic Type Wildcards
```java
ArrayList<String> strings = new ArrayList<>();
ArrayList<? extends Object> wildcardList = strings;  // can read, not write
ArrayList<?> unknown = strings;                       // completely unknown type
```

---

## Code examples

### Example 1: Enhanced averageVowels with Stream API
```java
public static double averageVowels(ArrayList<String> words) {
    if (words.isEmpty()) return 0.0;
    
    long totalVowels = words.stream()
        .mapToLong(word -> word.chars()
            .filter(c -> "aeiouAEIOU".indexOf(c) >= 0)
            .count())
        .sum();
    
    return (double) totalVowels / words.size();
}
```

### Example 2: Robust interLeave with Proper Handling
```java
public static <T> ArrayList<T> interLeave(ArrayList<T> a1, ArrayList<T> a2) {
    ArrayList<T> result = new ArrayList<>();
    int i = 0, j = 0;
    
    // Alternate from both lists while both have elements
    while (i < a1.size() && j < a2.size()) {
        result.add(a1.get(i++));
        result.add(a2.get(j++));
    }
    
    // Append remaining from a1
    result.addAll(a1.subList(i, a1.size()));
    
    // Append remaining from a2
    result.addAll(a2.subList(j, a2.size()));
    
    return result;
}
```

### Example 3: Complete Rectangle and Point Implementation
```java
public class Point {
    private final int x, y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    
    @Override
    public String toString() { return "(" + x + ", " + y + ")"; }
}

public class Rectangle {
    private int x, y, width, height;
    
    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public boolean contains(Point p) {
        return p.getX() >= x && p.getX() <= x + width &&
               p.getY() >= y && p.getY() <= y + height;
    }
    
    public int getArea() { return width * height; }
    public int getPerimeter() { return 2 * (width + height); }
    
    @Override
    public String toString() {
        return String.format("Rectangle[x=%d, y=%d, w=%d, h=%d]", x, y, width, height);
    }
}

public class Overlapping {
    public static Rectangle topMost(ArrayList<Rectangle> rects, Point p) {
        for (int i = rects.size() - 1; i >= 0; i--) {
            if (rects.get(i).contains(p)) {
                return rects.get(i);
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
        ArrayList<Rectangle> windows = new ArrayList<>();
        windows.add(new Rectangle(0, 0, 200, 200));    // window 1
        windows.add(new Rectangle(100, 100, 200, 200)); // window 2 (overlapping)
        windows.add(new Rectangle(150, 150, 150, 150)); // window 3 (topmost)
        
        Point click = new Point(160, 160);
        Rectangle topWindow = topMost(windows, click);
        System.out.println("Clicked window: " + topWindow);  // prints window 3
    }
}
```

---

## Mini-quiz (self-check)

1. **Why cast totalVowels to double before dividing?**
   - Answer: To avoid integer division truncation (7/3 = 2 vs. 7.0/3 = 2.333...).

2. **How do you check if a character is in a string?**
   - Answer: `"aeiouAEIOU".indexOf(ch) >= 0` returns ≥0 if found, -1 if not.

3. **What does it mean for rectangles to be in z-order?**
   - Answer: Ordered by depth (front-to-back); last in list is topmost (drawn last).

4. **Why iterate backwards in topMost?**
   - Answer: Topmost rectangle is last in list; check it first for efficiency.

5. **What is the difference between ArrayList and array?**
   - Answer: ArrayList is dynamic (resizable); array is fixed size. ArrayList easier to use; array more efficient.

6. **What makes a type Comparable?**
   - Answer: Implements Comparable<T> interface with `compareTo()` method.

7. **Can you sort ArrayList<String> with Collections.sort()?**
   - Answer: Yes, String already implements Comparable (alphabetical order).

8. **What happens if you modify ArrayList during iteration?**
   - Answer: ConcurrentModificationException. Use Iterator.remove() or collect changes, remove after loop.

---

## Master checklist — before submitting

- [ ] **ArrayList operations**: Correct use of `add()`, `remove()`, `get()`, `size()`
- [ ] **Generic types**: ArrayList declared with type parameter (e.g., `ArrayList<String>`)
- [ ] **Type casting**: Cast to double before division; cast elements to correct type when needed
- [ ] **Empty collection handling**: Check `isEmpty()` before operations that assume elements
- [ ] **Index bounds**: All indices in range [0, size-1]; no off-by-one errors
- [ ] **Iteration patterns**: Use enhanced for-loop or iterator; safe from concurrent modification
- [ ] **Algorithm correctness**: Tested with multiple cases (empty, single, multiple elements)
- [ ] **Complexity analysis**: Time and space documented for key operations
- [ ] **Edge cases**: Empty lists, single elements, unequal-sized lists handled
- [ ] **Comparable implementation**: compareTo() returns -1, 0, or 1; consistent with equals()

---

## Key Takeaways

1. **ArrayList is a dynamic array** — grows/shrinks automatically, provides convenience methods.
2. **Use ArrayList when size unknown or frequently changing; array when fixed size or performance critical.**
3. **Generics provide type safety** — `ArrayList<String>` prevents mixing types, avoids casts.
4. **Avoid integer division truncation** — cast to double when computing averages: `(double) num / denom`.
5. **Z-ordering in GUI** — rectangles stored bottom-to-top; topmost is last in list.
6. **Iterate backwards for efficiency** — topMost checks topmost first; exits early on match.
7. **Comparable defines natural ordering** — `compareTo()` returns -1 (less), 0 (equal), or 1 (greater).
8. **Never modify ArrayList during iteration** — use Iterator.remove() or collect and remove after.
9. **ArrayList insertion in middle is O(n)** — avoid in loops; build in temp list instead.
10. **Empty collections require defensive checks** — test isEmpty() before `.get()`, `.size()`, etc.

---

**Last Updated:** 2026-08-05  
**Author:** Generated from Chapter 10 source files in repository TGSpring/Java-Book-Review  
**Status:** Complete with 2 programming problems (averageVowels, interLeave), 1 project (Overlapping rectangles), comprehensive reference material, and best practices
