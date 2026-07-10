# Chapter 6 — Complete Study Guide

> Focus: File I/O, token- and line-level parsing with Scanner, text and string transformations, streaming output (PrintStream), and simple state machines for parsing (e.g., comment removal).

---

## Table of Contents
- Short answer review
- Core concepts explained
- Files & exercises (detailed, one-per-file)
  - Chpt6_1 — boyGirl
  - Chpt6_2 — evenNumbers
  - Chpt6_3 — negativeSum
  - Chpt6_4 — readEntireFile
  - Chpt6_5 — flipLines
  - Chpt6_6 — doubleSpace
  - Chpt6_7 — stripComments (uses program.java)
  - Chpt6_8 — printDuplicates
  - Chpt6_9 — plusScores
  - Chpt6_10 — leetSpeak
  - program.java — sample input for stripComments
- Cross-file patterns & best practices
- Common pitfalls and how to fix them
- Suggested refactors & improvements
- Practice exercises
- Quick reference (Scanner and String methods)
- Code examples
- Mini-quiz
- Master checklist

---

## Short answer review
- When parsing text files, prefer streaming (process line-by-line) to avoid loading entire files into memory unless you need the whole content at once.
- Use Scanner.hasNextLine() / nextLine() for line-based iteration; create a per-line mini-Scanner when you need token-level parsing on that single line to avoid next()/nextInt() vs nextLine() mixing problems.
- For detecting multi-line constructs (like /* ... */), maintain small state (boolean inBlock) across lines.
- Use PrintStream (or PrintWriter) when you need to create/write to files.

---

## Core concepts explained
- Scanner: hasNextLine(), nextLine(), hasNextInt(), nextInt(), next(), useDelimiter(String). Use hasNext* checks before reading to avoid exceptions.
- Delimiters: use useDelimiter to split tokens by commas or custom separators (e.g., ",\\s*" for comma+optional-space lists).
- String methods: indexOf, substring, replace, charAt, trim, length, equals/equalsIgnoreCase, toLowerCase / toUpperCase.
- Streaming I/O: write results as you read (PrintStream.println) to keep memory usage O(1).
- State machines: a boolean flag (e.g., inBlock) is a small, effective state to handle constructs spanning multiple lines.

---

## Files & exercises — detailed walkthroughs

### Chpt6_1 — boyGirl
- Problem: Input file contains alternating names (boy, girl, boy, ...) each followed by an integer. Compute the number of boys and girls and the absolute difference between the sum of boys' integers and girls' integers.
- What to watch for: file may end on either gender (odd number of lines).
- Approach used:
  - Read the file line-by-line.
  - For each line, create a mini-Scanner to pick the name token (ignored after read) and an optional integer.
  - Maintain a counter (starting at 1). Use parity (counter % 2) to decide boy or girl.
  - Track boySum, girlSum, boyCount, girlCount; after reading, compute Math.abs(boySum - girlSum) and print counts and difference.
- Why line-scanner? Prevents NoSuchElementException when tokens missing, keeps line boundaries explicit.
- Complexity: O(n) time (n = lines), O(1) extra space.
- Pitfall: Assumes name and number are on same line. If format changes, parser must be adapted.

Example (conceptual):
Input lines:
- "Max 10"  // boy
- "Jane 5"  // girl
- "Bob 7"   // boy
Output: "2 boys, 1 girls" and difference computed.

---

### Chpt6_2 — evenNumbers
- Problem: File contains comma-separated integers. Print total count, sum, even count and percent of evens (formatted to two decimals).
- Approach:
  - Set scanner delimiter: reader.useDelimiter(",\\s*");
  - Loop while hasNextInt(): read nextInt(), increment counters, test num % 2 == 0 for even count.
  - Compute percent = ((double) evenCount / total) * 100 and print with System.out.printf("%.2f%%").
- Edge cases: empty file would produce division by zero; add a guard to handle zero total.
- Complexity: O(n) time, O(1) space.

Example formatting: "12 numbers, sum = 214" and "8 evens (66.67%)" for sample input.

---

### Chpt6_3 — negativeSum
- Problem: Read integers and determine whether the running sum (starting from the first number) ever becomes negative. If so, print the sum and step at which it became negative and return true; otherwise print "No negative sum." and return false.
- Approach:
  - Read line-by-line and create a mini-Scanner for each line's tokens.
  - Keep a running sum and a step counter; after adding a token, check if sum < 0. If first negative sum found, print and return true immediately.
- Complexity: O(n) time worst-case, O(1) space.
- Pitfall: The code in folder treats first negative cumulative sum as the stopping condition (correct per prompt). Validate tokens before using nextInt().

---

### Chpt6_4 — readEntireFile
- Problem: Return entire file contents as a single String preserving line breaks.
- Approach: Read each line with nextLine() and append it with "\n" to a result string.
- Note: Using String concatenation in a loop is simple but inefficient for very large files; prefer StringBuilder for large inputs.
- Complexity: Time O(n), Space O(n) for the returned string.

---

### Chpt6_5 — flipLines
- Problem: Output the file so that each pair of lines is swapped. If there is an odd last line, leave it unchanged.
- Approach:
  - While hasNextLine(): read firstLine; if hasNextLine() then read secondLine and append secondLine + newline + firstLine; else append the single leftover line.
- Complexity: O(n) time, memory O(n) if you build a full string; can stream to PrintStream to avoid that.
- Pitfall: Be careful about newline placement to match expected output.

---

### Chpt6_6 — doubleSpace
- Problem: Write a double-spaced version of input file to a PrintStream (insert blank line between each input line).
- Approach:
  - For each line read, print it to output then, if a next line exists, print an empty line (output.println()).
  - Use PrintStream for direct file writing to keep memory constant.
- Complexity: O(n) time, O(1) extra memory.

---

### Chpt6_7 — stripComments
- Problem: Remove Java-style comments from an input Java file and return the uncommented text. Must remove both single-line (// to end of line) and block comments (/* ... */), and handle block comments that span multiple lines.
- Input sample: program.java (in folder) contains a few examples of inline comments and block comments.
- Approach (stateful):
  - Maintain boolean inBlock indicating whether parser is currently inside a /* ... */ block.
  - For each input line:
    - If inBlock: search for "*/". If found, remove up to end marker and continue processing remainder of line; if not found, skip the line.
    - If not inBlock: repeatedly search for the earliest of "/*" and "//". If a block start occurs before any //, remove portion from block start; look for its end on the same line; otherwise set inBlock and keep only text before block start. If // occurs first, strip from its index to end of line.
  - Append the cleaned line to output.
- Caveats: This is a line-based heuristic. It does not detect comment-like sequences inside string literals (e.g., "System.out.println("// not a comment")"). Handling that correctly requires additional states (inString) and handling escapes.
- Complexity: O(n) in characters.

---

### Chpt6_8 — printDuplicates
- Problem: For each line, detect consecutive duplicate tokens on the same line and print each duplicated token along with the count of consecutive occurrences (ignore duplicates across lines).
- Approach:
  - For each line, create a Scanner. Set prevToken = first token, count = 1. For each next token: if equals(prevToken) increment count; else if count > 1 append prevToken + " * " + count to output, reset count to 1 and prevToken to current token. After the line, flush last group if count > 1.
- Note: token equality uses String.equals (case-sensitive). Consider equalsIgnoreCase or lowercasing tokens if case-insensitive behavior is desired.
- Complexity: O(total tokens) time, O(1) extra space.

---

### Chpt6_9 — plusScores
- Problem: Each student record is two lines: a name line and a line with a sequence of '+' and '-' characters. Compute the percent of '+' characters and print the name followed by percent plus.
- Approach:
  - Read name line, then read the symbol line (trim it). Count plus characters by iterating characters; percent = ((double) plus / symbols.trim().length()) * 100.
- Pitfalls: If symbol line is empty, you must avoid division by zero; code assumes valid input.
- Complexity: O(n) time in characters.

---

### Chpt6_10 — leetSpeak
- Problem: Convert input text to leet speak and write to output file. Transformations: o→0, e→3, a→4, t→7, final 's' → 'z'. Wrap each word in parentheses and preserve line breaks.
- Approach:
  - For each line, tokenize (mini-Scanner). For each token, chain replace("o","0").replace("e","3")..., then if the last char of the token is 's' replace it with 'z' via substring. Append "(" + token + ") " to line output and println to PrintStream for each processed line.
- Caveats:
  - Code applies replacements only to lowercase letters; uppercase letters will not be transformed. For more robust behavior, process both cases or normalize to lower/upper and preserve capitalization logic.
  - Punctuation attached to words is treated as part of token; consider stripping punctuation if you want to avoid transforming punctuation.
- Complexity: O(n) time, O(1) extra memory per line.

---

### program.java (sample input)
- Purpose: A small Java program included as a test input to Chpt6_7.stripComments. It contains examples of single-line comments, inline comments adjacent to code, and an open/close block comment pair.
- Use: feed to stripComments to verify removal of both // and /* */ patterns.

---

## Cross-file patterns and best practices
- Per-line mini-Scanner pattern: Almost every program that needs tokenization creates a new Scanner over each line (Scanner lineScanner = new Scanner(line)), then uses next()/nextInt() safely. Benefits:
  - avoids mixing nextLine() and next()/nextInt() on same Scanner
  - simplifies handling variable token counts per line
- Guard with hasNext*/hasNextLine(): Prevents NoSuchElementException
- Stream output when possible: Writing to PrintStream as you read keeps memory low
- Use trim() when counting characters from lines that may contain trailing whitespace

---

## Common pitfalls & fixes
- Mixing nextInt() and nextLine() on the same Scanner: after nextInt() a newline remains; prefer line-level scanners or consume the leftover newline.
- Division by zero when computing percentages: check denominator == 0 before dividing.
- String concatenation in loops: for large files prefer StringBuilder instead of repeated `+` concatenation.
- stripComments false positives with string literals: add an "inString" state and honor escape characters (`\\`) to avoid removing comment markers inside string/char literals.

---

## Suggested refactors & improvements
- Replace repeated String `+=` loops with StringBuilder in readEntireFile, flipLines, printDuplicates, leetSpeak.
- Use try-with-resources for Scanners and PrintStreams to ensure closures even on exceptions:
```java
try (Scanner in = new Scanner(new File("input.txt"));
     PrintStream out = new PrintStream(new File("out.txt"))) {
    // processing
}
```
- Hardening:
  - Add explicit input validation and helpful error messages for malformed lines.
  - Make methods accept filenames/Streams as parameters (already done) and add CLI wrappers for convenience.
- Improve stripComments with: states (inBlock, inString, inChar) and an escape-handling mechanism for quotes.

---

## Practice exercises (next steps)
1. Refactor Chpt6_4 to use StringBuilder and compare memory/time on a large test file.
2. Expand Chpt6_7 to ignore comment markers inside string and character literals (implement inString/inChar flags and handle escapes).
3. Make leetSpeak case-preserving (uppercase letters map to uppercase replacements where appropriate) and handle punctuation boundary cases.
4. Add defensive checks and return/throw informative exceptions for malformed input (e.g., missing second line in Chpt6_9).
5. Create JUnit tests for each method using sample input files placed under Chapter 6/test-resources/ and assert expected output strings.

---

## Quick reference — common Scanner & String operations
- Scanner:
  - hasNextLine(), nextLine(), hasNextInt(), nextInt(), next(), useDelimiter(String), close()
- String:
  - length(), charAt(i), indexOf(sub), substring(start, end), replace(old, new), trim(), equals(), equalsIgnoreCase()
- I/O:
  - new Scanner(new File("path")), new PrintStream(new File("path"))

---

## Code examples
Below are compact, copy-paste-ready Java examples that illustrate the common patterns used across Chapter 6.

1) Line-level tokenization using a mini-Scanner (safe mixing of line and token parsing)
```java
try (Scanner file = new Scanner(new File("input.txt"))) {
    while (file.hasNextLine()) {
        String line = file.nextLine();
        try (Scanner tokens = new Scanner(line)) {
            if (tokens.hasNext()) {
                String name = tokens.next();
            }
            if (tokens.hasNextInt()) {
                int value = tokens.nextInt();
                // process value
            }
        }
    }
}
```

2) Input validation with hasNextInt()
```java
Scanner sc = new Scanner(System.in);
int n;
while (true) {
    System.out.print("Enter an integer: ");
    if (sc.hasNextInt()) {
        n = sc.nextInt();
        break;
    } else {
        System.out.println("Not an integer, try again.");
        sc.next(); // clear bad token
    }
}
```

3) Fencepost pattern (print comma-separated values without trailing comma)
```java
String[] items = {"apple","banana","cherry"};
if (items.length > 0) {
    System.out.print(items[0]);
    for (int i = 1; i < items.length; i++) {
        System.out.print(", " + items[i]);
    }
}
```

4) Efficient readEntireFile with StringBuilder
```java
public static String readEntireFile(File f) throws FileNotFoundException {
    StringBuilder sb = new StringBuilder();
    try (Scanner in = new Scanner(f)) {
        while (in.hasNextLine()) {
            sb.append(in.nextLine()).append('\n');
        }
    }
    return sb.toString();
}
```

5) Simple stripComments state machine (handles /* */ and //; does NOT handle strings)
```java
public static String stripComments(Scanner input) {
    StringBuilder out = new StringBuilder();
    boolean inBlock = false;
    while (input.hasNextLine()) {
        String line = input.nextLine();
        if (inBlock) {
            int end = line.indexOf("*/");
            if (end != -1) {
                line = line.substring(end + 2);
                inBlock = false;
            } else {
                continue; // skip entire line
            }
        }
        while (true) {
            int blockStart = line.indexOf("/*");
            int lineStart = line.indexOf("//");
            if (blockStart != -1 && (lineStart == -1 || blockStart < lineStart)) {
                int blockEnd = line.indexOf("*/", blockStart + 2);
                String before = line.substring(0, blockStart);
                if (blockEnd != -1) {
                    line = before + line.substring(blockEnd + 2);
                    continue; // look for more comments
                } else {
                    out.append(before).append('\n');
                    inBlock = true;
                    break;
                }
            } else if (lineStart != -1) {
                out.append(line.substring(0, lineStart)).append('\n');
                break;
            } else {
                out.append(line).append('\n');
                break;
            }
        }
    }
    return out.toString();
}
```

6) Improved leetSpeak example with StringBuilder and case handling
```java
public static String leetWord(String w) {
    StringBuilder sb = new StringBuilder(w.length());
    for (int i = 0; i < w.length(); i++) {
        char c = w.charAt(i);
        boolean upper = Character.isUpperCase(c);
        char lc = Character.toLowerCase(c);
        char mapped;
        switch (lc) {
            case 'o': mapped = '0'; break;
            case 'e': mapped = '3'; break;
            case 'a': mapped = '4'; break;
            case 't': mapped = '7'; break;
            default: mapped = lc; break;
        }
        if (i == w.length() - 1 && mapped == 's') mapped = 'z';
        if (upper) mapped = Character.toUpperCase(mapped);
        sb.append(mapped);
    }
    return sb.toString();
}

// usage per token:
// String leeted = leetWord(token);
// output.println("(" + leeted + ")");
```

---

## Mini-quiz (self-check)
1. Why create a mini-Scanner for each line instead of tokenizing the entire file Scanner? (answer: avoids mixing line- vs token-level methods and leftover newline issues)
2. What state variable is used to handle block comments across lines? (answer: boolean inBlock)
3. How to avoid division by zero when computing percentage of pluses? (answer: check denominator > 0 before dividing)
4. Why is StringBuilder recommended for constructing large result strings? (answer: repeated string concatenation is O(n^2) in some implementations; StringBuilder is linear)

---

## Master checklist — things to verify before submitting solutions
- [ ] Every Scanner/PrintStream is closed (prefer try-with-resources)
- [ ] All hasNext*/hasNextLine() checks are used before next*/nextLine()
- [ ] Methods that return strings document whether they include trailing newline
- [ ] Edge cases covered: empty file, odd number of lines, missing tokens
- [ ] Complexity analysis (time and space) added in comments where appropriate

---

**Last updated:** 2026-07-03
**Author:** Generated from Chapter 6 source files in repository TGSpring/Java-Book-Review
