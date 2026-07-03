# Chapter 6 — Comprehensive Study Guide

This study guide covers every source file in the Chapter 6 folder of the repository and explains each problem, the provided solution, design decisions, complexity, common pitfalls, and improvement suggestions. It is intended as a single reference file for learning the chapter's file- and string-processing techniques.

---

## Overview: common themes in Chapter 6
- Primary focus: file I/O, token/line processing, and string manipulation using java.util.Scanner, java.io.File, and java.io.PrintStream.
- Most solutions read input incrementally (streaming) rather than loading full files into memory; this keeps space usage low.
- Typical pattern: read line-by-line (Scanner.hasNextLine/nextLine) and when needed create a "mini-Scanner" over the line to tokenize safely. This avoids mixing nextLine() with next()/nextInt() directly on the same Scanner.
- String operations used often: indexOf, substring, replace, charAt, trim, length, concatenation.
- Each solution documents time complexity as O(n) and space as O(1) (except where full-file strings are returned — then space is O(n) for the returned string).

---

## Files covered
- Chpt6_1.java — boyGirl
- Chpt6_2.java — evenNumbers
- Chpt6_3.java — negativeSum
- Chpt6_4.java — readEntireFile
- Chpt6_5.java — flipLines
- Chpt6_6.java — doubleSpace
- Chpt6_7.java — stripComments
- Chpt6_8.java — printDuplicates
- Chpt6_9.java — plusScores
- Chpt6_10.java — leetSpeak
- program.java — sample Java program (used as input for stripComments)
- multiple plain text files used as sample inputs (numbers.txt, input.txt, input2.txt, reverse.txt, doubleSpace.txt, dups.txt, plusScore.txt, leetSpeak.txt, etc.)

---

## Detailed walkthrough — question by question

### Chpt6_1: boyGirl
- Problem: Input is a sequence of names and integers where names alternate between boys and girls. Compute counts of boys and girls and the absolute difference between total integers for boys vs girls. Input may end on either gender.
- Approach in code:
  - Read file line-by-line.
  - For each line, use a line-scanner to extract name (ignored beyond presence) and then integer (if present).
  - Maintain a counter starting at 1: odd -> boy, even -> girl. Update counts and sums accordingly.
  - Print counts and Math.abs(boySum - girlSum).
- Why this works: Alternation is tracked by counter parity, scanning each line prevents NoSuchElement exceptions when lines are incomplete.
- Pitfalls & improvements:
  - Assumes a name + integer appear on the same line. If format changes, parsing should be adjusted.
  - Could accept starting gender as a parameter instead of assuming "boy" first.
  - For many lines, consider validating token parsing and reporting malformed lines.
- Complexity: O(n) time, O(1) aux space.

### Chpt6_2: evenNumbers
- Problem: Given comma-separated integers in a file, report total count, sum, count of evens and percent evens (two decimals).
- Approach:
  - Configure Scanner delimiter with reader.useDelimiter(",\\s*") to split on commas and optional spaces.
  - Loop hasNextInt(), accumulate sum, total count, even count.
  - Compute percent as ((double) eCount / total) * 100 and print via printf("%.2f%%").
- Pitfalls & improvements:
  - If file is empty, division by zero will occur — add a guard: if total==0 print a helpful message.
  - Using BigInteger/long if numbers can exceed int range.
- Complexity: O(n) time, O(1) space.

### Chpt6_3: negativeSum
- Problem: Determine whether a running sum from the start ever becomes negative. Return true and print when it first becomes negative; otherwise return false.
- Approach:
  - Read line-by-line, create mini-Scanner for ints within a line.
  - Maintain running sum and step counter; check sum < 0 after each addition.
- Key insight: stop at first negative cumulative sum to avoid unnecessary work.
- Pitfalls:
  - The code treats the first negative sum as the stopping condition (as required).
  - If non-integer tokens exist, logic would need to skip or report them.

### Chpt6_4: readEntireFile
- Problem: Return the entire contents of a file as a single String.
- Approach:
  - Read each line with nextLine() and concatenate it to a result with an appended "\n" to preserve line breaks.
- Tradeoffs:
  - Concatenation with `+` in a loop can be inefficient for very large files. Use StringBuilder when handling large inputs.
  - The returned string consumes O(n) space proportional to file size.

### Chpt6_5: flipLines
- Problem: Reverse every pair of lines in the file; leave final line if odd number of lines.
- Approach:
  - Loop while hasNextLine(): read first line into tempFlip. If hasNextLine() then read second line and append second then first to output; else append lone last line.
- Edge cases:
  - Correct newline placement is important to match expected formatting.
- Complexity: O(n) time.

### Chpt6_6: doubleSpace
- Problem: Write a double-spaced version of an input file into an output PrintStream (insert blank line between each line).
- Approach:
  - Stream each input line to the PrintStream with println, then if another line exists output an extra println() blank line.
- Advantages:
  - Streaming avoids building large in-memory structures; memory stays O(1).

### Chpt6_7: stripComments
- Problem: Remove Java-style comments from a file: both single-line (`//` to EOL) and block comments (`/* ... */`) which may span lines. Return processed text.
- Approach (algorithmic detail):
  - Maintain boolean inBlock to indicate whether parsing is currently within a block comment.
  - For each input line:
    - If inBlock, search for end marker `*/`. If not found, skip the line. If found, strip up to the end marker and resume processing remainder of line.
    - Within the current line, repeatedly search for next `/*` and `//`. If a `/*` is found before any `//`, extract the text before it and then search for the matching `*/` on the same line. If `*/` is not found, set inBlock=true and keep text before the block. If `//` is first (or only), truncate from the `//` onwards.
  - Append processed pieces to output with `\n`.
- Important caveats:
  - This is a practical, line-based heuristic. It does not handle comment-like sequences inside string literals (e.g., "System.out.println("// not comment")") — a full parser would be needed to handle those correctly.
  - Ordering of checks (block comment vs line comment) matters to avoid incorrectly removing code.

### Chpt6_8: printDuplicates
- Problem: For each line, find consecutive duplicate tokens and print each duplicated token together with the number of times it repeats consecutively. Don't merge across line boundaries.
- Approach:
  - For each line, build a line-scanner. Initialize prevToken with the first token, count=1. For each subsequent token: if token.equals(prevToken) increment count; otherwise, if count>1 append (prevToken + " * " + count) to output, reset count=1, prevToken=token. After line ends, if count>1 append final group.
- Notes:
  - The implementation compares tokens with equals (case-sensitive). The exercise permits ignoring cross-line repetition only; if you want case-insensitive grouping use equalsIgnoreCase or normalize tokens.

### Chpt6_9: plusScores
- Problem: Each student record spans two lines: a name line and a symbol line of plus/minus chars. For each student, compute percent of plus chars.
- Approach:
  - Read name line, read symbol line, iterate characters and count '+'; compute percentage = (plus / trimmedSymbolsLength) * 100 and format `%` output string.
- Pitfalls:
  - Trim the symbol string before measuring length to avoid stray whitespace affecting denominator.
  - Validate empty symbol line (avoid division by zero).

### Chpt6_10: leetSpeak
- Problem: Translate text to leet speak and write to an output file. Replace characters as specified (o -> 0, e -> 3, a -> 4, t -> 7, and final 's' -> 'z'), preserve line breaks, and wrap each resulting word in parentheses.
- Approach:
  - Line-by-line processing; for each token perform a chain of .replace("o","0").replace("e","3")... then if final char is 's' replace last char with 'z'. Wrap token in parentheses and append.
- Notes & improvements:
  - Replacement uses lowercase letters; if input has uppercase letters you may want to normalize or handle uppercase replacements too.
  - Edge: punctuation attached to words (like "year's") will be processed as part of the token; consider removing or handling punctuation for more natural results.

---

## Patterns observed across the folder
- Line-scoped tokenization: repeatedly the author prefers reading each line and then instantiating a Scanner on that line for token-level parsing. This prevents mixing nextLine() with next()/nextInt() and is robust to lines with variable token counts.
- HaveNext checks before consuming tokens: most methods check hasNextLine() and hasNext()/hasNextInt() before reading tokens — good defensive practice.
- Per-line processing reduces memory footprint: methods that must transform or scan content stream output (e.g., doubleSpace uses PrintStream and does not store everything) to keep memory small.
- String concatenation used for building results: acceptable for small sample files; use StringBuilder when processing large files to avoid quadratic behavior.
- Complexity analysis is consistent: streaming algorithms are O(n) time and O(1) extra space (unless returning a full-string result).

---

## Robustness & improvement suggestions (general)
- Validate input assumptions explicitly (empty files, missing tokens, malformed lines) and produce helpful error messages.
- Replace repeated String concatenation in loops with StringBuilder for performance on large files.
- When parsing comment-removal or languages-aware features (stripComments), be aware that string literals or character literals can contain comment-like sequences that naive indexOf scanning will mis-handle. Consider a simple state machine including string-literal detection if you need correctness on real code.
- Normalize tokens when case-insensitive behavior is desired (e.g., printDuplicates with case-insensitivity).
- Close Scanners and PrintStreams in try-with-resources to ensure resources are always closed even when exceptions occur.

---

## Suggested practice exercises (next steps)
- Refactor Chpt6_4 to use StringBuilder and measure time/memory on a large test file.
- Extend Chpt6_7 (stripComments) to ignore comment markers inside string literals and character literals.
- Add command-line parameter handling so each program can accept input/output file paths instead of hard-coded filenames.
- Create unit tests for each method using JUnit and sample input files placed under a `test-resources/` directory.

---

## Where this ties to earlier chapters (idea from Chapter 5 study guide)
- Many earlier chapters introduce control flow, arrays, and string basics. Here those building blocks are applied to streaming and parsing problems. The consistent theme: combine careful input scanning with small state variables (counters, flags) to implement robust text-transformation algorithms.

---

If you want, I can:
- Add this study guide file to the repository (I have created it in this commit).
- Also produce per-problem small unit-tests or example input files and expected outputs saved into a `Chapter 6/tests/` subfolder.
