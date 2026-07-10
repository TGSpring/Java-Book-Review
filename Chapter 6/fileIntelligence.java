
/**
 * Tyler Spring
 * 7/3/2026
 * Chapter 6 Project
 *
 * File Intelligence Utility
 *
 * ============================================================
 * PROJECT GOAL
 * ============================================================
 *
 * Build a menu-driven Java console application that analyzes and
 * transforms text files using only the concepts introduced through
 * Chapter 6 of Building Java Programs.
 *
 * This project is intended to reinforce:
 *
 * • Reading from files using Scanner
 * • Writing to files using PrintStream
 * • Line-by-line file processing
 * • Token-by-token parsing with mini-Scanners
 * • String processing
 * • Input validation
 * • Loops and cumulative algorithms
 * • Basic file transformations
 *
 * This project intentionally avoids later Java topics so the focus
 * remains on mastering Chapter 6 concepts.
 *
 * ============================================================
 * ALLOWED CHAPTER CONCEPTS
 * ============================================================
 *
 * ✓ Scanner
 * ✓ Scanner(File)
 * ✓ PrintStream
 * ✓ File
 * ✓ hasNext()
 * ✓ hasNextLine()
 * ✓ hasNextInt()
 * ✓ next()
 * ✓ nextLine()
 * ✓ nextInt()
 * ✓ useDelimiter()
 * ✓ String methods
 * ✓ loops
 * ✓ conditionals
 * ✓ methods
 * ✓ cumulative algorithms
 * ✓ Math
 *
 * ============================================================
 * DO NOT USE
 * ============================================================
 *
 * ✗ StringBuilder
 * ✗ Arrays
 * ✗ ArrayLists
 * ✗ Collections
 * ✗ Streams
 * ✗ Lambdas
 * ✗ Enums
 * ✗ Records
 * ✗ Advanced Regular Expressions
 * ✗ Any material introduced after Chapter 6
 *
 * The purpose of this project is repetition of the Chapter 6
 * techniques—not solving the problems using newer Java features.
 *
 * ============================================================
 * PROGRAM REQUIREMENTS
 * ============================================================
 *
 * The program should repeatedly display a menu until the user
 * chooses to exit.
 *
 * ==============================
 * FILE INTELLIGENCE
 * ==============================
 *
 * 1. File Statistics
 * 2. Search Word
 * 3. Replace Word
 * 4. Remove Blank Lines
 * 5. Number Every Line
 * 6. Reverse Words Per Line
 * 7. Duplicate Word Report
 * 8. Export Statistics Report
 * 9. Exit
 *
 * ============================================================
 * FEATURE 1 — FILE STATISTICS
 * ============================================================
 *
 * Ask the user for an input filename.
 *
 * Display:
 *
 * • Total lines
 * • Total words
 * • Total characters
 * • Longest line length
 * • Average words per line
 *
 * Concepts practiced:
 * • Scanner(File)
 * • nextLine()
 * • mini Scanner
 * • cumulative algorithms
 *
 * ============================================================
 * FEATURE 2 — SEARCH WORD
 * ============================================================
 *
 * Ask for:
 *
 * • filename
 * • search word
 *
 * Print every line number where the word appears.
 *
 * Display total occurrences.
 *
 * Search should compare whole words using equals().
 *
 * Concepts practiced:
 *
 * • nested Scanners
 * • token parsing
 * • counting
 *
 * ============================================================
 * FEATURE 3 — REPLACE WORD
 * ============================================================
 *
 * Ask for:
 *
 * • input filename
 * • output filename
 * • word to replace
 * • replacement word
 *
 * Create a new file containing the replaced text while leaving
 * the original file unchanged.
 *
 * Concepts practiced:
 *
 * • PrintStream
 * • replace()
 * • simultaneous reading and writing
 *
 * ============================================================
 * FEATURE 4 — REMOVE BLANK LINES
 * ============================================================
 *
 * Ask for:
 *
 * • input filename
 * • output filename
 *
 * Copy every non-empty line into the output file.
 *
 * Blank lines should not appear in the new file.
 *
 * Use trim() when determining whether a line is blank.
 *
 * ============================================================
 * FEATURE 5 — NUMBER EVERY LINE
 * ============================================================
 *
 * Ask for:
 *
 * • input filename
 * • output filename
 *
 * Example:
 *
 * 1: Hello
 * 2: World
 * 3: Java
 *
 * Concepts practiced:
 *
 * • counters
 * • formatted output
 * • PrintStream
 *
 * ============================================================
 * FEATURE 6 — REVERSE WORDS PER LINE
 * ============================================================
 *
 * Reverse the order of words on every line.
 *
 * Example:
 *
 * Input:
 * Java is fun
 *
 * Output:
 * fun is Java
 *
 * IMPORTANT:
 *
 * Do NOT use arrays.
 *
 * Solve using only Chapter 6 String methods such as:
 *
 * • substring()
 * • lastIndexOf()
 * • indexOf()
 * • trim()
 *
 * ============================================================
 * FEATURE 7 — DUPLICATE WORD REPORT
 * ============================================================
 *
 * Detect consecutive duplicate words on each line.
 *
 * Example:
 *
 * cat cat dog dog dog fish
 *
 * Output:
 *
 * cat -> 2
 * dog -> 3
 *
 * Ignore duplicates that are not consecutive.
 *
 * Concepts practiced:
 *
 * • previous token
 * • counters
 * • equals()
 *
 * ============================================================
 * FEATURE 8 — EXPORT STATISTICS REPORT
 * ============================================================
 *
 * Generate a formatted report file using the statistics calculated
 * in Feature 1.
 *
 * Example:
 *
 * FILE REPORT
 * ------------------------
 * Filename:
 * Total Lines:
 * Total Words:
 * Total Characters:
 * Longest Line:
 * Average Words Per Line:
 *
 * Use PrintStream to generate the report.
 *
 * ============================================================
 * INPUT VALIDATION
 * ============================================================
 *
 * • Validate all menu choices.
 * • Continue prompting until a valid menu option is entered.
 * • Verify that every input file exists before attempting to read it.
 * • Display friendly error messages instead of crashing.
 *
 * ============================================================
 * PROGRAM DESIGN
 * ============================================================
 *
 * Organize the project into methods.
 *
 * Suggested methods:
 *
 * main()
 * printMenu()
 * getMenuChoice()
 * fileStatistics()
 * searchWord()
 * replaceWord()
 * removeBlankLines()
 * numberLines()
 * reverseWords()
 * duplicateReport()
 * exportReport()
 *
 * Additional helper methods are encouraged whenever they reduce
 * repeated code.
 *
 * ============================================================
 * LEARNING OBJECTIVES
 * ============================================================
 *
 * After completing this project, the student should be comfortable:
 *
 * ✓ Reading files line-by-line
 * ✓ Parsing lines into individual tokens
 * ✓ Writing output files
 * ✓ Performing cumulative algorithms on file data
 * ✓ Manipulating Strings using Chapter 6 methods
 * ✓ Building reusable file-processing methods
 * ✓ Validating user and file input
 * ✓ Applying Scanner effectively without mixing parsing strategies
 *
 * The emphasis of this project is mastering the techniques taught
 * in Chapter 6 rather than using newer Java features that simplify
 * the implementation.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class fileIntelligence {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("File Intelligence");
            System.out.println("Select a task.");
            System.out.println("1. File Statistics");
            System.out.println("2. Search Word");
            System.out.println("3. Replace Word");
            System.out.println("4. Remove Blank Lines");
            System.out.println("5. Number Every Line");
            System.out.println("6. Reverse Words Per Line");
            System.out.println("7. Duplicate Word Report");
            System.out.println("8. Export Statistics Report");
            System.out.println("9. Exit");

            choice = getInt(sc, 1, 9);

            switch (choice) {
                case 1:
                    // file stats.
                    fileStats(sc);
                    break;
                case 2:
                    // Search Word.
                    searchWord(sc);
                    break;
                case 3:
                    // Replace Word.
                    replaceWord(sc);
                    break;
                case 4:
                    // Remove Blank Liens.
                    removeBlank(sc);
                    break;
                case 5:
                    // Number Every line.
                    numberLine(sc);
                    break;
                case 6:
                    // Reverse words per line.
                    reverseWords(sc);
                    break;
                case 7:
                    // Duplicate Words Report
                    dupWord(sc);
                    break;
                case 8:
                    // Export Stats report.
                    exportReport(sc);
                    break;
                default:
                    // invalid input.
            }

            // Allows pause before rePrompt. Help with formatting.
            if (choice != 9) {
                pressEnter(sc);
            }
        } while (choice != 9);
        sc.close();
    }

    // method for fileStats.
    private static void fileStats(Scanner sc) throws FileNotFoundException {
        int totalLines = 0;
        int totalWords = 0;
        int totalChars = 0;
        int longestLine = 0;

        File file = getFile(sc);
        Scanner fs = new Scanner(file);

        while (fs.hasNextLine()) {
            String line = fs.nextLine();
            totalLines++;
            totalChars += line.length();

            if (longestLine < line.length()) {
                longestLine = line.length();
            }

            Scanner ls = new Scanner(line);

            while (ls.hasNext()) {
                totalWords++;
                ls.next();
            }
            ls.close();
        }

        if (totalLines == 0) {
            System.out.println("No data in file.");
        } else {

            double average = (double) totalWords / totalLines;

            System.out.println("Total lines: " + totalLines);
            System.out.println("Total words: " + totalWords);
            System.out.println("Total characters: " + totalChars);
            System.out.println("Longest line: " + longestLine);
            System.out.printf("Average words per line: %.2f%n", average);
        }
        fs.close();
    }

    // method for wordSearch.
    private static void searchWord(Scanner sc) throws FileNotFoundException {
        int matches = 0;

        File file = getFile(sc);
        Scanner fs = new Scanner(file);

        System.out.println("Enter the word to search for: ");
        String word = sc.nextLine();

        while (fs.hasNextLine()) {
            String line = fs.nextLine();
            Scanner ls = new Scanner(line);
            while (ls.hasNext()) {
                String test = ls.next();
                if (word.equalsIgnoreCase(test)) {
                    matches++;
                }
            }
            ls.close();
        }
        System.out.println("This word appears " + matches + " times.");
        fs.close();

    }

    // method for replaceWord
    private static void replaceWord(Scanner sc) throws FileNotFoundException {
        File file = getFile(sc);
        Scanner fs = new Scanner(file);

        System.out.println("Enter word to replace:");
        String target = sc.nextLine();

        System.out.println("Enter replacement word:");
        String replacement = sc.nextLine();

        System.out.println("Enter output file name:");
        String outputFileName = sc.nextLine();
        PrintStream out = new PrintStream(outputFileName);

        while (fs.hasNextLine()) {
            String line = fs.nextLine();
            Scanner ls = new Scanner(line);
            String newLine = "";
            while (ls.hasNext()) {
                String word = ls.next();
                if (word.equals(target)) {
                    newLine += replacement + " ";
                } else {
                    newLine += word + " ";
                }
            }
            ls.close();
            out.println(newLine);
        }
        fs.close();
        out.close();

    }

    // method for removeBlank
    private static void removeBlank(Scanner sc) throws FileNotFoundException {

        File file = getFile(sc);
        Scanner fs = new Scanner(file);

        System.out.println("Enter output file name:");
        String outputFileName = sc.nextLine();

        PrintStream out = new PrintStream(outputFileName);

        while (fs.hasNextLine()) {
            String line = fs.nextLine();
            if (!line.trim().isEmpty()) {
                out.println(line);
            }

        }
        fs.close();
        out.close();

    }

    // method for numberLine.
    private static void numberLine(Scanner sc) throws FileNotFoundException {
        int number = 1;

        File file = getFile(sc);
        Scanner fs = new Scanner(file);

        System.out.println("Enter output file name:");
        String outputFileName = sc.nextLine();

        PrintStream out = new PrintStream(outputFileName);

        while (fs.hasNextLine()) {
            String line = fs.nextLine();

            out.println(number + ": " + line);
            number++;
        }
        fs.close();
        out.close();
    }

    // method for reverseWords
    private static void reverseWords(Scanner sc) throws FileNotFoundException {

        File file = getFile(sc);
        Scanner fs = new Scanner(file);

        System.out.println("Enter output file name:");
        String outputFileName = sc.nextLine();

        PrintStream out = new PrintStream(outputFileName);

        while (fs.hasNextLine()) {
            String line = fs.nextLine().trim();
            String newLine = "";

            while (!line.isEmpty()) {
                int space = line.lastIndexOf(" ");

                if (space == -1) {
                    newLine += line;
                    break;
                }

                String word = line.substring(space + 1);
                newLine += word + " ";

                line = line.substring(0, space).trim();
            }

            out.println(newLine.trim());
        }

        fs.close();
        out.close();
    }

    // method for dupWord
    private static void dupWord(Scanner sc) throws FileNotFoundException {

        File file = getFile(sc);
        Scanner fs = new Scanner(file);

        while (fs.hasNextLine()) {
            String line = fs.nextLine();

            Scanner ls = new Scanner(line);

            String previous = "";
            int count = 0;

            while (ls.hasNext()) {
                String word = ls.next();

                if (word.equals(previous)) {
                    count++;
                } else {
                    if (count > 1) {
                        System.out.println(previous + " -> " + count);
                    }
                    previous = word;
                    count = 1;
                }

            }
            // check final duplicate group
            if (count > 1) {
                System.out.println(previous + " -> " + count);
            }

            ls.close();
        }

        fs.close();
    }

    // method for exportReport
    private static void exportReport(Scanner sc) throws FileNotFoundException {

        int totalLines = 0;
        int totalWords = 0;
        int totalChars = 0;
        int longestLine = 0;

        File file = getFile(sc);
        Scanner fs = new Scanner(file);

        System.out.println("Enter output file name:");
        String outputFileName = sc.nextLine();

        PrintStream out = new PrintStream(outputFileName);

        while (fs.hasNextLine()) {

            String line = fs.nextLine();
            totalLines++;
            totalChars += line.length();

            if (longestLine < line.length()) {
                longestLine = line.length();
            }

            Scanner ls = new Scanner(line);

            while (ls.hasNext()) {
                totalWords++;
                ls.next();
            }
            ls.close();
        }

        double average = (double) totalWords / totalLines;

        out.println("FILE REPORT");
        out.println("------------------------");
        out.println("Filename: " + file.getName());
        out.println("Total Lines: " + totalLines);
        out.println("Total Words: " + totalWords);
        out.println("Total Characters: " + totalChars);
        out.println("Longest Line: " + longestLine);
        out.println("Average Words Per Line: " + average);

        fs.close();
        out.close();
    }

    // Input validation method.
    private static int getInt(Scanner sc, int min, int max) {
        while (true) {
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Enter an integer.");
                sc.nextLine();
                continue;
            }

            int input = sc.nextInt();

            if (input < min || input > max) {
                System.out.println("Out of range. Enter a value between " + min + " and " + max);
                sc.nextLine();
                continue;
            }
            sc.nextLine();
            return input;
        }
    }

    // getFile helper method
    private static File getFile(Scanner sc) {
        while (true) {
            System.out.print("Enter filename: ");
            String fileName = sc.nextLine();

            File file = new File(fileName);

            if (file.exists()) {
                return file;
            }

            System.out.println("File not found.");
        }
    }

    // Pause before next Iteration method.
    private static void pressEnter(Scanner sc) {
        System.out.println("Press Enter to return to the menu...");
        sc.nextLine();
    }
}
