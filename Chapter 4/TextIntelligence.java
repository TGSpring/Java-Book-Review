
/**
 * 
 * Tyler Spring 
 * 6/24/2026
 * 
 * Chapter 4 Project – Text Intelligence Program

Purpose:
This project is designed specifically to reinforce Chapter 4 concepts from Building Java Programs. The goal is not to create the most advanced application possible, but to repeatedly practice conditional execution, cumulative algorithms, string processing, loops, and method design.

Learning Objectives:

* if statements
* if/else statements
* else-if chains
* boolean expressions
* conditional execution inside methods
* cumulative counting algorithms
* string processing
* charAt()
* substring()
* matches()
* loops
* return values
* input validation
* modular program design

Project Description:
Create a console-based Text Intelligence Program that accepts a line of text from the user and generates an analysis report about that text.

Example Input:

Enter text:
Java Is Awesome 123

Example Output:

========================
TEXT ANALYSIS REPORT
====================

Length: 19
Vowels: 6
Contains Number: Yes
Contains Uppercase: Yes
Contains Only Letters: No
First Three Characters: Jav
Reversed Text: 321 emosewA sI avaJ

Required Features and Methods:

1. Text Length

Method:

public static int getLength(String text)

Purpose:
Return the total number of characters in the text.

Concepts Practiced:

* return values
* String methods

---

2. Count Vowels

Method:

public static int countVowels(String text)

Purpose:
Count and return the number of vowels.

Concepts Practiced:

* loops
* charAt()
* conditional logic
* cumulative counting

---

3. Contains Digit

Method:

public static boolean containsDigit(String text)

Purpose:
Return true if at least one numeric character exists.

Concepts Practiced:

* boolean methods
* conditionals
* early return

---

4. Contains Uppercase

Method:

public static boolean containsUppercase(String text)

Purpose:
Return true if at least one uppercase letter exists.

Concepts Practiced:

* Character methods
* conditionals
* loops

---

5. Contains Only Letters

Method:

public static boolean containsOnlyLetters(String text)

Purpose:
Determine whether the string contains only alphabetic characters.

Examples:

HelloWorld -> true
Hello123 -> false

Concepts Practiced:

* matches()
* validation
* boolean return values

---

6. First Three Characters

Method:

public static String firstThree(String text)

Purpose:
Return the first three characters of the text.

Concepts Practiced:

* substring()

Consider:
What should happen if the string has fewer than three characters?

---

7. Reverse Text

Method:

public static String reverseText(String text)

Purpose:
Return a reversed version of the text.

Example:

Java

becomes

avaJ

Concepts Practiced:

* loops
* cumulative string building
* charAt()

---

Suggested Development Phases

Phase 1:

* Create Scanner
* Accept user input
* Verify input is stored correctly

Phase 2:

* Implement getLength()
* Implement countVowels()

Phase 3:

* Implement containsDigit()
* Implement containsUppercase()

Phase 4:

* Implement containsOnlyLetters()
* Implement firstThree()
* Implement reverseText()

Phase 5:

* Improve formatting
* Add validation and edge-case handling

Stretch Goals (Optional)

Count Digits

public static int countDigits(String text)

Count Words

public static int countWords(String text)

Password Strength Checker

public static String passwordStrength(String password)

Palindrome Checker

public static boolean isPalindrome(String text)

Examples:

racecar -> true
hello -> false

Tutor Rules

This project is being completed as a learning exercise.

Do NOT seek or copy complete solutions.

For each feature:

1. Design the algorithm first.
2. Explain why it works.
3. Implement it.
4. Test it with multiple inputs.
5. Refactor if needed.

Focus on understanding:

* why a condition is written a certain way
* why a loop works
* why an accumulator starts with a specific value
* why a method returns a particular type

Success is measured by understanding the concepts, not by finishing the project quickly.

 */
import java.util.Scanner;

public class TextIntelligence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter text: ");
        String userText = sc.nextLine();

        System.out.println("Entered text: " + userText);

        System.out.println("length of text: " + getLength(userText));

        System.out.println("Vowel Count: " + vowelCount(userText));

        System.out.println("The text contains digits: " + containsDigits(userText));

        System.out.println("The text contains upper case letters: " + containsUpperCase(userText));

        System.out.println("The text contains only letters: " + onlyLetters(userText));

        System.out.println("First three letters of text: " + firstThree(userText));

        System.out.println("The text in reverse: " + reverseText(userText));

        System.out.println("Number of digits in text: " + countDigits(userText));

        System.out.println("Number of words in text: " + countWords(userText));

        System.out.println("The text is a Palindrome: " + isPalindrome(userText));

        sc.close();
    }

    // Method for finding length of string.
    public static int getLength(String userText) {
        return userText.length();
    }

    // Method for counting number of vowels in string.
    public static int vowelCount(String userText) {
        int count = 0;
        String lower = userText.toLowerCase();
        for (int i = 0; i < lower.length(); i++) {

            char letter = lower.charAt(i);
            if (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u') {
                count++;
            }

        }
        return count;
    }

    // Method for containsDigits.
    public static Boolean containsDigits(String userText) {
        return userText.matches(".*\\d.*");

    }

    // Method for contains UpperCase.
    public static boolean containsUpperCase(String userText) {
        return userText.matches(".*[A-Z].*");
    }

    // Method for onlyLetters.
    public static boolean onlyLetters(String userText) {
        if (containsDigits(userText) == false) {
            return true;
        }
        return false;
    }

    // Method for firstThree.
    // I am aware this makes 3 strings. StringBuilder is overkill for this I do not
    // care.
    public static String firstThree(String userText) {
        String three = "";
        if (getLength(userText) < 3) {
            return "Text has length of: " + getLength(userText);
        }
        for (int i = 0; i < 3; i++) {
            three += userText.charAt(i);
        }
        return three;
    }

    // Method for reverseText.
    public static String reverseText(String userText) {
        String rev = "";

        for (int i = userText.length() - 1; i > -1; i--) {
            rev += userText.charAt(i);
        }
        return rev;
    }

    // Method for counting digits.
    public static int countDigits(String text) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            if ('0' <= letter && letter <= '9') {
                count++;
            }
        }
        return count;
    }

    // Method for counting words.
    public static int countWords(String text) {
        boolean flag = false;
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' ' && !flag) {
                flag = true;
                count++;
            } else if (text.charAt(i) == ' ') {
                flag = false;
            }
        }
        return count;
    }

    // Method for checking if text is Palindrome.
    // This does fail for spaces and different cases, but thats not the point for
    // this project.
    public static boolean isPalindrome(String text) {
        int i = 0;
        int j = text.length() - 1;

        while (i < j) {
            if (text.charAt(i) == text.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }
}
