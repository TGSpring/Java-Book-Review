
/* ####### 
 * I DID NOT HAVE THE REQUIREMENTS TO DO THE LAST PROJECT FOR THIS CHAPTER,
 * IT WAS THE GUITAR HERO PROJECT AND REQUIRED SOURCE CODE AND SOUND CARD. 
 * I FED CHAPGPT THE PREVIOUS PROJECTS AND GOT THIS. ENJOY.
 * 
 * Tyler Spring
 * 10/15/25
 * ChatGPT Project
 * 
 * Write a program that simulates a simple text editor with undo dna redo capabilities.
 * The program should support a small set of commands that modify or display the text being edited.
 * You will use two stacks to manage the history of changes:
 *      - The undo stack keeps track of text states before each edit.
 *      - The redo stack keeps track of text states that were undone and can be restored.
 * 
 * You may only use the following methods of the Stack class:
 *      - push, pop, peek, isEmpty, and size.
 * 
 * You should not use indexing, iteration, or built-in collections directly.
 * 
 * Example:
 * 
 *      Type Hello
 *      Type World
 * 
 *      Show
 *      -> Hello World
 *      
 *      Undo
 *      Show
 *      -> Hello 
 * 
 *      Redo 
 *      Show 
 *      -> Hello World
 *      
 *      Quit
 * 
 * Rules:
 *  1. Each time the user types new text, save the current text before editing, on the undo stack, and clear 
 * the redo stack.
 * 
 *  2. When the user chooses Redo, move the current text to the redo stack and revert to the top of the undo stack.
 * 
 *  3. When the user chooses Redo, move the current text to the undo stack and restore the top of the redo stack.
 * 
 *  4. If there's nothing to undo or redo, print a message like "Nothing to undo." or "Nothing to redo."
 * 
 *  5. Continue processing commands until the user types Quit.
 */
import java.util.Stack;
import java.util.Scanner;

public class ChatGPTMadePrt {
    public static void main(String[] args) {
        Stack<String> undoStack = new Stack<>();
        Stack<String> redoStack = new Stack<>();

        String currentText = "";

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Enter Command: ");
            String line = input.nextLine().trim();

            String command = line.toLowerCase();

            // Quit
            if (command.equals("quit")) {
                System.out.println("Exiting editor");
                break;
            }

            // Show
            if (command.equals("show")) {
                System.out.println("-> " + currentText);
                continue;
            }

            // Undo
            if (command.equals("undo")) {
                if (undoStack.isEmpty()) {
                    System.out.println("Nothing to undo.");
                } else {
                    redoStack.push(currentText);
                    currentText = undoStack.pop();
                }
                continue;
            }

            // Redo
            if (command.equals("redo")) {
                if (redoStack.isEmpty()) {
                    System.out.println("Nothing to redo.");
                } else {
                    undoStack.push(currentText);
                    currentText = redoStack.pop();
                }
                continue;
            }

            // Anything else → treat as typed text
            undoStack.push(currentText);
            currentText += line;
            redoStack.clear();
        }

        input.close();
    }
}
/*
 * TYPE COMPLEXITIES
 * Time Complexity:
 * - undoStack.push(currentText) = O(1), for the stack operation itself.
 * - currentText += line = Strings are immutable in Java, so this creates a new
 * string of length
 * currentText.length() + line.length() = O(n), where n is the length of
 * currentText.
 * - redoStack.clear() = O(1) for clearing a stack, just resets the pointer
 * internally.
 * 
 * Space Complexity:
 * - Stores a new string in undoStack = O(n) extra space.
 * - currentText also grows = O(n).
 * - Overall: O(n).
 * 
 * UNDO COMPLEXITIES:
 * Time Complexity:
 * - redoStack.push() = O(1)
 * - undoStack.pop() = O(1)
 * - Overall O(1)
 * 
 * Space Complexity:
 * - redoStack grows by one string = O(n), n = length of currentText.
 * - currentText itself is replaced; no extra copies beyond the stack = O(n).
 * - Overall: O(n).
 * 
 * REDO COMPLEXITIES:
 * - Same as Redo.
 * 
 * SHOW COMPLEXITIES:
 * Time Complexity:
 * - Printing a string of length n = O(n).
 * - Overall: O(n).
 * Space Complexity:
 * - Temporary string created for = concatenation = O(n).
 * - Overall: O(n) 
 */


 
/*
 * PREIMPLENMENTATION NOTES
 * Step 1: Create two stacks:
 * - undoStack
 * - redoStack
 * 
 * Keep a variable for the current text:
 * - currentText
 * 
 * Step 2: Handle user input:
 * - Use a loop to continuously prompt the user for commands:
 * - Inside this loop:
 * - Parse input, check if it starts with "Type", equals "Undo", etc.
 * - Respond accordingly.
 * 
 * Step 3: Implement Type logic:
 * - When the user types new text:
 * 1. Push the current text onto the undoStack.
 * 2. Append the new text to currentText.
 * 3. Clear the redoStack because new edits invalidate redo history.
 * 
 * Step 4: Implement undo logic:
 * - If undoStack is not empty:
 * 1. Push teh current text onto the redoStack.
 * 2. Pop the top of undoStack and assign it to currentText.
 * Otherwise, print "Nothing to undo."
 * 
 * Step 5: Implement redo logic:
 * - If redoStack is not empty:
 * 1. Push the current text onto the undoStack.
 * 2. Pop the top of redoStack and assign it to currentText.
 * Otherwise, print "Nothing to redo".
 * 
 * 
 */