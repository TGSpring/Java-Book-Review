# Chapter 7 — Complete Study Guide

> Focus: Single-dimensional and multidimensional arrays, array traversal algorithms, array search and modification methods, matrix operations, reference semantics, and algorithmic analysis (time/space complexity).

---

## Table of Contents
- Short answer review
- Core concepts explained
- Files & exercises (detailed, one-per-file)
  - Chpt7_1 — lastIndexOf
  - Chpt7_2 — range
  - Chpt7_3 — countInRange
  - Chpt7_4 — isSorted
  - Chpt7_5 — stdev (standard deviation)
  - Chpt7_6 — contains
  - Chpt7_7 — longestSortedSequence
  - Chpt7_8 — collapse
  - Chpt7_9 — matrixAdd
  - Chpt7_10 — isMagicSquare
  - Short response question — comprehensive review
- Cross-file patterns & best practices
- Common pitfalls and how to fix them
- Suggested refactors & improvements
- Practice exercises
- Quick reference (array methods and algorithms)
- Code examples
- Mini-quiz
- Master checklist

---

## Short answer review

### 7.1 Array Basics
- **First element**: `numbers[0]` — array indices are zero-based
- **Last element (known length)**: `numbers[9]` for a 10-element array
- **Last element (dynamic)**: `numbers[numbers.length - 1]` — always works regardless of size
- **Declaration with initialization**: `int[] data = {7, -1, 13, 24, 6};` — all in one statement
- **Note**: `array.length` is a field, not a method; do not use `array.length()`

### 7.2 Array-Traversal Algorithms
- **Traversal**: Iterating through each element of an array to perform an operation (sum, count, search, etc.)
- **Example problem**: Computing the sum of all elements — initialize sum to 0, then loop and accumulate
- **Pattern**: `for (int i = 0; i < array.length; i++)` visits every element exactly once

### 7.3 Reference Semantics
- **Value vs. Reference semantics**: Integers are passed by value (independent copies); arrays are passed by reference (both variables point to same memory)
- **Swap arrays**: Works correctly because method receives reference; modifications persist after method returns
- **Swap integers**: Does not work because copies are swapped, not originals
- **Aliasing**: When two references point to the same object (e.g., `list3 = list2`), modifications via one reference are visible via the other

### 7.4 Advanced Array Techniques
- **Array shift**: `numbers[i] = numbers[i + 1]` slides elements left; the last element retains its old value
- **Array of objects**: Declare `Book[] books = new Book[3]`, then instantiate each element: `books[0] = new Book(...)`
- **Jagged arrays**: 2D arrays with varying row lengths; declare as `int[][] jag = new int[5][]`, then create each row individually with `jag[r] = new int[r + 1]`

### 7.5 Multidimensional Arrays
- **2D array creation**: `int[][] matrix = new int[3][4];` creates 3 rows, 4 columns
- **Nested loops**: Outer loop iterates rows, inner loop iterates columns: `for (int r = 0; r < matrix.length; r++) for (int c = 0; c < matrix[0].length; c++)`
- **Element access**: `matrix[r][c]` accesses row r, column c
- **Jagged 2D**: Each row can have different length; use `matrix[r].length` inside inner loop to respect individual row sizes

---

## Core concepts explained

### Single-Dimensional Arrays
- **Declaration**: `int[] arr;` declares reference; `int[] arr = new int[10];` allocates array
- **Initialization**: `int[] arr = {1, 2, 3};` initializes with literals; other elements default to 0 (int), 0.0 (double), false (boolean), null (objects)
- **Access & Modification**: `arr[i]` gets/sets element at index i; index must be in range [0, length-1]
- **Length**: `arr.length` is a public field (not a method) that returns the array size

### Multidimensional Arrays
- **2D structure**: `int[][] matrix` is logically a table with rows and columns
- **Declaration**: `int[][] m = new int[rows][cols];` creates rectangular matrix; or `int[][] m = new int[rows][];` creates jagged structure
- **Access**: `matrix[row][column]` accesses specific cell
- **Row vector**: `matrix[r]` returns the r-th row (a 1D array)

### Array Traversal Patterns
- **Linear scan**: `for (int i = 0; i < arr.length; i++)` — visit every element once, O(n) time
- **Search backwards**: `for (int i = arr.length - 1; i >= 0; i--)` — useful for finding last occurrence
- **Nested (2D)**: `for (int r = 0; r < rows; r++) for (int c = 0; c < cols; c++)` — visit every cell, O(rows * cols) time

### Algorithms Covered
- **Search**: lastIndexOf, contains (subsequence search)
- **Analysis**: range, isSorted, longestSortedSequence
- **Aggregation**: countInRange, stdev (standard deviation)
- **Transformation**: collapse (pair summation), matrixAdd
- **Validation**: isMagicSquare (all row/column/diagonal sums equal)

### Reference Semantics
- Arrays are reference types: assignment copies the reference, not the array data
- Two variables can alias the same array; modifications through one are visible via the other
- Method parameters: modifying an array inside a method affects the original array outside
- Copying: to create independent copy, iterate and manually copy elements or use `System.arraycopy()`

### Time & Space Complexity
- **Time**: Typically O(n) for single-pass traversals, O(n²) for nested loops over 2D structures
- **Space**: Usually O(1) if no extra data structures; O(n) if creating a new array of size n
- **Example**: `isMagicSquare` is O(n²) time (visits all cells once) and O(1) space (only variables, no extra arrays)

---

## Files & exercises — detailed walkthroughs

### Chpt7_1 — lastIndexOf

**Problem**: Given an array of integers and a target value, return the index of the last (rightmost) occurrence. Return -1 if not found.

**Example**: In `[74, 85, 102, 99, 101, 85, 56]`, the last index of 85 is 5.

**Approach**:
- Iterate **backwards** from `arr.length - 1` down to 0
- On first match, return immediately (that's the last occurrence)
- If loop completes without match, return -1

**Why backwards?** Scanning from right to left means the first match found is automatically the rightmost (last) one.

**Code pattern**:
```java
for (int i = arr.length - 1; i >= 0; i--) {
    if (arr[i] == value) {
        return i;  // First match found; since we go backwards, this is the last
    }
}
return -1;
```

**Complexity**: O(n) time worst-case (value not found, scan entire array), O(1) space.

**Key insight**: Backwards iteration is more efficient than forward scan because we don't need to track the maximum index seen so far.

---

### Chpt7_2 — range

**Problem**: Return the **range** of an array, defined as `(max - min + 1)`. The +1 is intentional (inclusive count of values between min and max).

**Example**: In `[36, 12, 25, 19, 46, 31, 22]`, range = 46 - 12 + 1 = 35.

**Approach**:
- Initialize `max = arr[0]` and `min = arr[0]`
- Single forward pass: update max and min as encountered
- Return `max - min + 1`

**Why single pass?** Both max and min are found in one traversal; no need to sort or make multiple passes.

**Code pattern**:
```java
int max = arr[0];
int min = arr[0];
for (int i = 0; i < arr.length; i++) {
    if (arr[i] > max) max = arr[i];
    if (arr[i] < min) min = arr[i];
}
return max - min + 1;
```

**Complexity**: O(n) time, O(1) space.

**Common mistake**: Forgetting the `+1` — the problem specifically defines range this way to count inclusively.

---

### Chpt7_3 — countInRange

**Problem**: Count how many elements fall within a given min and max (inclusive).

**Example**: In `[14, 1, 22, 17, 36, 7, -43, 5]` with min=3, max=18, count is 4 (elements: 14, 17, 7, 5).

**Approach**:
- Initialize `count = 0`
- Loop through all elements
- If `arr[i] >= min && arr[i] <= max`, increment count
- Return count

**Code pattern**:
```java
int count = 0;
for (int i = 0; i < arr.length; i++) {
    if (arr[i] >= min && arr[i] <= max) {
        count++;
    }
}
return count;
```

**Complexity**: O(n) time, O(1) space.

**Boundary handling**: Use `>=` and `<=` (not `>` and `<`) to include min and max in the range.

---

### Chpt7_4 — isSorted

**Problem**: Return true if array is in non-decreasing (sorted) order; false otherwise. A one-element array is considered sorted.

**Example**: `[1.5, 4.3, 7.0, 19.5, 25.1, 46.2]` is sorted (true). `[16.1, 12.3, 22.2, 14.4]` is not sorted (false).

**Approach**:
- Loop from index 0 to `length - 2` (compare each element with next)
- If `arr[i] > arr[i+1]`, return false immediately (not sorted)
- If loop completes without finding a violation, return true

**Why `length - 2`?** We compare `arr[i]` with `arr[i+1]`, so stopping at index `length - 2` prevents index out-of-bounds.

**Code pattern**:
```java
for (int i = 0; i < arr.length - 1; i++) {
    if (arr[i] > arr[i + 1]) {
        return false;
    }
}
return true;
```

**Complexity**: O(n) time worst-case (array is sorted), O(1) space.

**Non-decreasing vs. strictly increasing**: This checks non-decreasing (allows duplicates). Strictly increasing would use `>=` instead of `>`.

---

### Chpt7_5 — stdev (Standard Deviation)

**Problem**: Compute the standard deviation of an array of integers using the formula:
```
stdev = √(Σ(xi – mean)² / (n – 1))
```

**Approach**:
1. Compute mean: sum all elements, divide by n
2. Compute sum of squared differences: for each element, compute `(element - mean)²` and accumulate
3. Divide by (n - 1) and take square root

**Code pattern**:
```java
double sum = 0;
for (int x : nums) sum += x;
double mean = sum / nums.length;

double sqSum = 0;
for (int x : nums) {
    sqSum += Math.pow(x - mean, 2);
}
return Math.sqrt(sqSum / (nums.length - 1));
```

**Complexity**: O(n) time (two passes), O(1) space.

**Why (n-1)?** This is the sample standard deviation formula (Bessel's correction). Using n gives population standard deviation.

**Common mistake**: Using `sqSum / nums.length` instead of `sqSum / (nums.length - 1)`.

---

### Chpt7_6 — contains

**Problem**: Check if array `a2` appears as a **consecutive subsequence** inside array `a1`. Both arrays may be empty; every array contains the empty array.

**Example**: `a1 = [1, 6, 2, 1, 4, 1, 2, 1, 8]`, `a2 = [1, 2, 1]` → true (found at index 5).

**Approach**:
- Edge cases: if `a2.length == 0`, return true; if `a1.length < a2.length`, return false
- Outer loop: for each starting position i in a1 (up to `a1.length - a2.length`)
- Inner loop: compare a2 against a1 starting at position i
- If all elements match, return true
- If no match found, return false

**Code pattern**:
```java
if (a2.length == 0) return true;
if (a1.length < a2.length) return false;

for (int i = 0; i <= a1.length - a2.length; i++) {
    boolean match = true;
    for (int j = 0; j < a2.length; j++) {
        if (a1[i + j] != a2[j]) {
            match = false;
            break;
        }
    }
    if (match) return true;
}
return false;
```

**Complexity**: O((n - m + 1) * m) time worst-case, where n = a1.length, m = a2.length; O(1) space.

**Key insight**: The nested loop approach is simple; more efficient algorithms (KMP, Boyer-Moore) exist for large arrays but are overkill for this problem.

---

### Chpt7_7 — longestSortedSequence

**Problem**: Find the length of the longest consecutive sorted (non-decreasing) subsequence in the array.

**Example**: In `[3, 8, 10, 1, 9, 14, -3, 0, 14, 207, 56, 98, 12]`, the longest sorted sequence is `[-3, 0, 14, 207]` with length 4.

**Approach**:
- Track `curLength` (current sequence length) and `maxLength` (best found so far)
- Initialize both to 0; special case: if array is empty, return 0
- Loop: if next element >= current element, increment curLength; else save curLength to maxLength and reset curLength to 1
- After loop, one final check: `maxLength = Math.max(maxLength, curLength)` (in case last sequence is longest)

**Why final check?** If the array ends with a long sorted sequence, the loop's else-branch never triggers, so we must check after the loop.

**Code pattern**:
```java
if (nums.length == 0) return 0;
int maxLength = 0;
int curLength = 1;
for (int i = 0; i < nums.length - 1; i++) {
    if (nums[i + 1] >= nums[i]) {
        curLength++;
    } else {
        maxLength = Math.max(maxLength, curLength);
        curLength = 1;
    }
}
maxLength = Math.max(maxLength, curLength);  // Final check
return maxLength;
```

**Complexity**: O(n) time, O(1) space.

**Non-decreasing (not strictly increasing)**: Sequences can contain duplicates (e.g., `[1, 1, 2, 2]` is one sorted sequence of length 4).

---

### Chpt7_8 — collapse

**Problem**: Create a new array where each pair of consecutive elements from the input is replaced by their sum. If the array has odd length, the last element is not paired and appears unchanged in the result.

**Example**: `[7, 2, 8, 9, 4, 13, 7, 1, 9, 10]` → `[9, 17, 17, 8, 19]` (pairs: 7+2=9, 8+9=17, 4+13=17, 7+1=8, 9+10=19).

**Odd example**: `[1, 2, 3, 4, 5]` → `[3, 7, 5]` (pairs: 1+2=3, 3+4=7; last 5 unpaired).

**Approach**:
- Determine result array size: `(length + 1) / 2` handles both even and odd cases
- Loop with `i += 2` (step by 2): `result[i / 2] = nums[i] + nums[i + 1]`
- After loop, if odd length, append the last element: `result[result.length - 1] = nums[nums.length - 1]`

**Code pattern**:
```java
int[] result = new int[(nums.length + 1) / 2];
for (int i = 0; i < nums.length - 1; i += 2) {
    result[i / 2] = nums[i] + nums[i + 1];
}
if (nums.length % 2 != 0) {
    result[result.length - 1] = nums[nums.length - 1];
}
return result;
```

**Complexity**: O(n) time, O(n/2) = O(n) space for result array.

**Key insight**: The original array is not modified; a new array is returned.

---

### Chpt7_9 — matrixAdd

**Problem**: Add two matrices element-wise. Given matrices A and B of the same dimensions, return C where `C[i][j] = A[i][j] + B[i][j]` for all i, j.

**Example**:
```
A = [[1, 2, 3], [4, 5, 6]]
B = [[6, 5, 4], [3, 2, 1]]
Result = [[7, 7, 7], [7, 7, 7]]
```

**Approach**:
- Create result matrix with same dimensions as A and B
- Nested loops over rows and columns
- For each position (i, j), add elements from both matrices and store in result

**Code pattern**:
```java
int rows = a.length;
int cols = a[0].length;
int[][] result = new int[rows][cols];

for (int i = 0; i < rows; i++) {
    for (int j = 0; j < cols; j++) {
        result[i][j] = a[i][j] + b[i][j];
    }
}
return result;
```

**Complexity**: O(rows * cols) time (visit each cell once), O(rows * cols) space (result matrix).

**Assumption**: Matrices are guaranteed same dimensions (no validation needed per problem statement).

---

### Chpt7_10 — isMagicSquare

**Problem**: Determine if a square matrix is "magic": all rows, columns, and both diagonals sum to the same value.

**Example**: `[[2, 7, 6], [9, 5, 1], [4, 3, 8]]` is magic (all sums = 15).

**Approach**:
1. Check if matrix is square (all rows same length as number of rows)
2. Compute target sum from first row
3. Nested loop over all cells:
   - Accumulate each row sum; compare to target
   - Accumulate each column sum; compare to target
   - On diagonal (i == j): accumulate main diagonal
   - On anti-diagonal (i + j == rows - 1): accumulate secondary diagonal
4. After loop, verify both diagonal sums match target

**Code pattern**:
```java
// Check square
for (int i = 0; i < a.length; i++) {
    if (a[i].length != a.length) return false;
}

// Target sum from first row
int target = 0;
for (int r = 0; r < a[0].length; r++) {
    target += a[0][r];
}

int diag1 = 0, diag2 = 0;
for (int i = 0; i < rows; i++) {
    int rowSum = 0, colSum = 0;
    for (int j = 0; j < cols; j++) {
        rowSum += a[i][j];
        colSum += a[j][i];
        if (i == j) diag1 += a[i][j];
        if (i + j == rows - 1) diag2 += a[i][j];
    }
    if (rowSum != target || colSum != target) return false;
}
if (diag1 != target || diag2 != target) return false;
return true;
```

**Complexity**: O(n²) time (n = rows/cols), O(1) space.

**Diagonal formulas**:
- Main diagonal: `i == j` (top-left to bottom-right)
- Anti-diagonal: `i + j == rows - 1` (top-right to bottom-left)

---

### Short response question

This file contains 10 short-answer questions covering array basics, traversals, reference semantics, advanced techniques, and multidimensional arrays.

**Key answers**:
- Array indexing is zero-based; `arr[0]` is first, `arr[arr.length - 1]` is last
- Traversal = iterating through structure to perform operation
- Reference semantics: modifications to array via one reference visible to all references
- Aliasing: multiple references pointing to same object
- Arrays are passed by reference to methods; modifications persist
- Jagged arrays created by declaring outer array, then initializing each row individually
- 2D element value = f(row index + column index) common pattern in initialization

---

## Cross-file patterns and best practices

### 1. Backward Iteration for Last Occurrence
Many problems (lastIndexOf, longestSortedSequence) benefit from or use iteration strategies tailored to the problem:
- **Backward**: Natural for finding last occurrences; first match is automatically rightmost
- **Two-pass**: Sometimes required (e.g., mean calculation before variance)

### 2. Nested Loops for 2D Traversal
- Standard pattern: outer loop (rows), inner loop (columns)
- Access cell: `matrix[row][col]`
- Diagonal checks: i == j (main), i + j == size - 1 (anti)

### 3. State Tracking
- `curLength` in longestSortedSequence tracks current sequence
- `maxLength` tracks best found so far
- Update logic: reset curLength on "break" condition; save max before resetting

### 4. Edge Case Handling
- Empty arrays: return sensible default (0, false, -1, depending on context)
- Single element: often considered valid (e.g., one-element array is sorted)
- Odd-length arrays: requires special case handling (e.g., collapse's last unpaired element)

### 5. Algorithm Combinations
- Some problems combine multiple techniques:
  - **isMagicSquare**: matrix traversal + multiple sum checks (rows, columns, diagonals)
  - **contains**: nested loop search for pattern match
  - **longestSortedSequence**: single-pass with state tracking

### 6. Time vs. Space Trade-offs
- **collapse, matrixAdd**: must allocate new arrays → O(n) space
- **lastIndexOf, range, countInRange, isSorted**: only scalars → O(1) space
- **stdev**: two passes but same O(n) time, O(1) space

---

## Common pitfalls and how to fix them

### Pitfall 1: Index Out-of-Bounds in Loops
**Problem**: `for (int i = 0; i < arr.length; i++)` is safe; `for (int i = 0; i <= arr.length; i++)` goes out-of-bounds.

**Fix**: Use strict `<` comparison; avoid `<=` when checking against `.length`.

### Pitfall 2: Forgetting Edge Cases
**Problem**: `range()` assumes non-empty array. Dividing by zero in percentages.

**Fix**: Add guards: check array length > 0 before accessing arr[0]; check denominator != 0 before division.

### Pitfall 3: Off-by-One Errors in Loop Bounds
**Problem**: `collapse()` steps by 2, so loop bound is `i < nums.length - 1` to avoid unpaired single element.

**Fix**: Think through the last valid i value: if stepping by 2, the last valid starting position is length - 2 (pairs i with i+1).

### Pitfall 4: Forgetting Final State Check
**Problem**: In `longestSortedSequence()`, if the longest sequence is at the end, the loop's else-branch never triggers, so maxLength isn't updated.

**Fix**: Always add a final check after the loop: `maxLength = Math.max(maxLength, curLength);`

### Pitfall 5: Reference vs. Value Confusion
**Problem**: Modifying an array parameter in a method affects the original (reference); modifying an int parameter does not (value).

**Fix**: Remember arrays are objects (reference type); integers are primitives (value type). If you want to create an independent copy, iterate and manually copy or use System.arraycopy().

### Pitfall 6: Incorrect Diagonal Formulas in 2D
**Problem**: Using wrong indices for diagonals in `isMagicSquare()`.

**Fix**: Main diagonal: `i == j`. Anti-diagonal: `i + j == size - 1` (not `i + j == size`).

### Pitfall 7: Not Initializing Tracking Variables Correctly
**Problem**: In `longestSortedSequence()`, initializing `curLength = 0` means a single-element sequence has length 0, which is wrong.

**Fix**: Initialize to 1 (one element already "seen"), then increment on continuation.

---

## Suggested refactors & improvements

### 1. Generics for Type Safety
Current code assumes `int[]`. Adding generic methods:
```java
public static <T extends Comparable<T>> int lastIndexOf(T[] arr, T value) {
    for (int i = arr.length - 1; i >= 0; i--) {
        if (arr[i].equals(value)) return i;
    }
    return -1;
}
```

### 2. Exception Handling
Add defensive checks and informative error messages:
```java
public static int range(int[] arr) {
    if (arr == null || arr.length == 0) {
        throw new IllegalArgumentException("Array must not be null or empty");
    }
    // ... rest of method
}
```

### 3. Helper Methods for Clarity
Extract repeated logic (e.g., column-sum in isMagicSquare):
```java
private static int getColumnSum(int[][] matrix, int col) {
    int sum = 0;
    for (int r = 0; r < matrix.length; r++) {
        sum += matrix[r][col];
    }
    return sum;
}
```

### 4. Use of Built-in Methods
Java 8+ streams simplify some operations:
```java
// Instead of manual loop for sum
int sum = Arrays.stream(arr).sum();

// For max/min
int max = Arrays.stream(arr).max().orElse(Integer.MIN_VALUE);
```

### 5. Copy Constructor for Arrays
Instead of manually duplicating:
```java
int[] copy = Arrays.copyOf(originalArray, originalArray.length);
int[][] copy2D = Arrays.stream(matrix)
                       .map(int[]::clone)
                       .toArray(int[][]::new);
```

### 6. Javadoc Comments
Add method documentation:
```java
/**
 * Returns the index of the last occurrence of value in array arr.
 * @param arr the array to search
 * @param value the value to find
 * @return index of last occurrence, or -1 if not found
 */
public static int lastIndexOf(int[] arr, int value) { ... }
```

---

## Practice exercises (next steps)

1. **Extend lastIndexOf** to handle null arrays and return -1 safely; add a unit test suite.
2. **Add duplicate-detection** to array methods: modify `isSorted()` to also return the index of first out-of-order element.
3. **Implement matrix transpose**: create a method `transpose(int[][] matrix)` that returns the transposed matrix.
4. **Extend contains()** to return the starting index of the match (or -1 if not found) instead of just true/false.
5. **Combine range and countInRange**: write a method `rangeElements()` that returns an array of all elements within the range [min, max].
6. **Multi-pass algorithms**: implement a method that finds the second-largest element (requires tracking max and second-max).
7. **2D array search**: implement `contains2D()` to search for a submatrix within a larger matrix.
8. **Performance comparison**: compare the time to compute standard deviation on a large array vs. using `Math.pow()` vs. manual multiplication.

---

## Quick reference — array algorithms

### Search Algorithms
```java
// Linear search (forward)
for (int i = 0; i < arr.length; i++) {
    if (arr[i] == target) return i;  // or return true
}
return -1;  // or return false

// Linear search (backward) — for last occurrence
for (int i = arr.length - 1; i >= 0; i--) {
    if (arr[i] == target) return i;
}
return -1;

// Subsequence search
for (int i = 0; i <= haystack.length - needle.length; i++) {
    boolean match = true;
    for (int j = 0; j < needle.length; j++) {
        if (haystack[i+j] != needle[j]) {
            match = false;
            break;
        }
    }
    if (match) return true;
}
return false;
```

### Analysis Algorithms
```java
// Min and max in one pass
int min = arr[0], max = arr[0];
for (int i = 1; i < arr.length; i++) {
    if (arr[i] < min) min = arr[i];
    if (arr[i] > max) max = arr[i];
}

// Sum and average
int sum = 0;
for (int x : arr) sum += x;
double avg = (double) sum / arr.length;

// Longest increasing run
int maxLen = 1, curLen = 1;
for (int i = 0; i < arr.length - 1; i++) {
    if (arr[i + 1] >= arr[i]) {
        curLen++;
    } else {
        maxLen = Math.max(maxLen, curLen);
        curLen = 1;
    }
}
maxLen = Math.max(maxLen, curLen);  // Final check
```

### Transformation Algorithms
```java
// Pair summation (collapse)
int[] result = new int[(arr.length + 1) / 2];
for (int i = 0; i < arr.length - 1; i += 2) {
    result[i / 2] = arr[i] + arr[i + 1];
}
if (arr.length % 2 != 0) {
    result[result.length - 1] = arr[arr.length - 1];
}

// Matrix addition (element-wise)
for (int i = 0; i < rows; i++) {
    for (int j = 0; j < cols; j++) {
        result[i][j] = a[i][j] + b[i][j];
    }
}
```

### 2D Array Patterns
```java
// Traverse all cells
for (int r = 0; r < rows; r++) {
    for (int c = 0; c < matrix[r].length; c++) {
        process(matrix[r][c]);
    }
}

// Main diagonal
for (int i = 0; i < size; i++) {
    process(matrix[i][i]);
}

// Anti-diagonal
for (int i = 0; i < size; i++) {
    process(matrix[i][size - 1 - i]);
}

// Row sum
int sum = 0;
for (int c = 0; c < matrix[r].length; c++) {
    sum += matrix[r][c];
}

// Column sum
int sum = 0;
for (int r = 0; r < matrix.length; r++) {
    sum += matrix[r][c];
}
```

---

## Code examples

### Example 1: Generic lastIndexOf with Validation
```java
/**
 * Find the last index of a value in an array.
 * @param arr the array (must be non-null)
 * @param value the value to find
 * @return last index, or -1 if not found
 * @throws IllegalArgumentException if arr is null
 */
public static int lastIndexOf(int[] arr, int value) {
    if (arr == null) throw new IllegalArgumentException("Array is null");
    for (int i = arr.length - 1; i >= 0; i--) {
        if (arr[i] == value) return i;
    }
    return -1;
}
```

### Example 2: Safe Range Computation
```java
public static int range(int[] arr) {
    if (arr == null || arr.length == 0) {
        throw new IllegalArgumentException("Array is null or empty");
    }
    int max = arr[0], min = arr[0];
    for (int i = 1; i < arr.length; i++) {
        max = Math.max(max, arr[i]);
        min = Math.min(min, arr[i]);
    }
    return max - min + 1;
}
```

### Example 3: Defensive countInRange
```java
public static int countInRange(int[] arr, int min, int max) {
    if (arr == null) return 0;
    if (min > max) {
        throw new IllegalArgumentException("min > max");
    }
    int count = 0;
    for (int x : arr) {
        if (x >= min && x <= max) count++;
    }
    return count;
}
```

### Example 4: Subsequence Search with Result Index
```java
/**
 * Find the starting index of needle in haystack.
 * @return starting index, or -1 if not found
 */
public static int indexOfSubsequence(int[] haystack, int[] needle) {
    if (needle.length == 0) return 0;
    if (haystack.length < needle.length) return -1;
    
    for (int i = 0; i <= haystack.length - needle.length; i++) {
        boolean match = true;
        for (int j = 0; j < needle.length; j++) {
            if (haystack[i + j] != needle[j]) {
                match = false;
                break;
            }
        }
        if (match) return i;  // Return index instead of just true
    }
    return -1;
}
```

### Example 5: Matrix Transpose
```java
public static int[][] transpose(int[][] matrix) {
    int rows = matrix.length;
    int cols = matrix[0].length;
    int[][] result = new int[cols][rows];
    
    for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) {
            result[c][r] = matrix[r][c];  // Swap indices
        }
    }
    return result;
}
```

### Example 6: Enhanced longestSortedSequence with Bounds
```java
public static int longestSortedSequence(int[] arr) {
    if (arr == null || arr.length == 0) return 0;
    
    int maxLen = 1, curLen = 1;
    for (int i = 0; i < arr.length - 1; i++) {
        if (arr[i + 1] >= arr[i]) {
            curLen++;
        } else {
            maxLen = Math.max(maxLen, curLen);
            curLen = 1;
        }
    }
    // Critical: final check after loop
    return Math.max(maxLen, curLen);
}
```

---

## Mini-quiz (self-check)

1. **Why iterate backwards in lastIndexOf?** 
   - Answer: First match found going backwards is automatically the rightmost (last) occurrence; avoids tracking max index.

2. **What formula determines the result size in collapse()?**
   - Answer: `(nums.length + 1) / 2` handles both even and odd lengths correctly.

3. **How do you check if a number is in the main diagonal of a square matrix?**
   - Answer: `i == j` (row index equals column index).

4. **What is the final state check in longestSortedSequence and why is it needed?**
   - Answer: `Math.max(maxLength, curLength)` after the loop; ensures the longest sequence at the end is counted (loop's else-branch doesn't trigger if sequence extends to end).

5. **What is reference semantics and why does it matter for arrays?**
   - Answer: Arrays are objects; variables hold references to memory, not copies. Multiple variables can alias the same array, and modifications via one reference are visible via all.

6. **Why does isMagicSquare check `i == j` and `i + j == rows - 1`?**
   - Answer: `i == j` identifies main diagonal (top-left to bottom-right); `i + j == rows - 1` identifies anti-diagonal (top-right to bottom-left).

7. **What is the time complexity of contains() and why?**
   - Answer: O((n - m + 1) * m) = O(n*m) where n = haystack.length, m = needle.length; nested loops check all candidate positions and all elements of needle.

8. **When should you use O(1) space algorithms vs. O(n) space algorithms?**
   - Answer: Use O(1) when problem allows in-place modifications or only requires a scalar result (e.g., range, countInRange). Use O(n) when you must allocate a new array (e.g., collapse, matrixAdd).

---

## Master checklist — before submitting

- [ ] **Correctness**: All methods return correct results on provided examples
- [ ] **Edge cases**: Empty arrays, single elements, odd-length arrays (where applicable) handled
- [ ] **Bounds**: No off-by-one errors; loop conditions are safe (use `<`, not `<=` with `.length`)
- [ ] **Final checks**: Methods with state tracking (longestSortedSequence, etc.) include final state check after loops
- [ ] **Reference semantics**: Understand whether method modifies input array or creates new one
- [ ] **Complexity analysis**: Time and space complexity documented in comments
- [ ] **Variable initialization**: All tracking variables (max, min, curLength, etc.) initialized appropriately
- [ ] **Diagonal formulas**: 2D array methods use correct diagonal checks (i == j, i + j == size - 1)
- [ ] **No magic numbers**: Array sizes, loop bounds, and formulas explained in comments
- [ ] **Testing**: Each method tested on at least the provided examples and one edge case

---

## Key Takeaways

1. **Array indexing is zero-based**: First element is at index 0; last is at index length - 1.
2. **Traversal is fundamental**: Most array problems involve iterating through elements to search, analyze, or transform.
3. **Backward iteration** finds last occurrences efficiently without tracking.
4. **Reference semantics** mean arrays are passed by reference; modifications inside methods affect originals.
5. **Nested loops** are the standard pattern for 2D array traversal; O(n²) time typical.
6. **State tracking** (curLength, maxLength, etc.) allows single-pass algorithms for complex problems.
7. **Edge case handling** is critical: empty arrays, odd lengths, single elements must be anticipated.
8. **Time/Space trade-offs** exist: some problems require O(n) space (new arrays); others stay O(1).
9. **Diagonal formulas** in 2D: main (i == j), anti (i + j == size - 1).
10. **Final state checks** after loops ensure results like longest sequence at end-of-array are counted.

---

**Last Updated:** 2026-07-12  
**Author:** Generated from Chapter 7 source files in repository TGSpring/Java-Book-Review  
**Status:** Complete with 10 exercises, short-answer review, patterns, pitfalls, and comprehensive reference material
