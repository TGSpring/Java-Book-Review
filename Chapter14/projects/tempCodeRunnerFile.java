/*
 * Tyler Spring
 * 10/10/2025
 * Chapter 14 Project 2
 * Write an HTML Validator program that reads files of HTML data and uses stacks and queues to verify whether the tags 
 * in the file are properly matched. A tag consists of a named element between less-than, <, and greater-than, >, symbols.
 * Many tags apply to a range of text, in which case a pair of tags is used: an opening tag indicating the start of the range and a closing 
 * tag with a slash indicating the end of the range. For example, you can make some text bold <b> like this </b>. Tags can be nested to combine effects 
 * <b><i>bold italic</i></b>. Some tags, such as the br tag for inserting a line break or img for inserting an image, do not cover a ranger of text and are 
 * not considered to be "self-closing". Self-closing tags do not need a closing tag: for a line break, only a tag of <br> is needed. Some web developers 
 * write self-closing tags with an optional / before the >, such as <br/>.
 * 
 * The following HTML file has some errors:  the <title> tag is not closed: the </head> tag appears twice: an extraneous </br> tag appears: and the <body> 
 * tag is not properly closed.
 * 
 * <!DOCTYPE html>
 * <html>
 * <!-- This is a comment -->
 * <head>
 *      <title> Turtles are conditional
 * </head>
 * </head>
 * 
 * <body>
 *      <p>Turtles swim in the <a href="ocean.com">ocean</a>.</p<
 *  </br>
 *  <p> Some turtles are over 100 years old.
 *      Here is a picture of a turtle:
 *      <img src = "turtle.jpg" /> </p>
 *  </html>
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.io.FileNotFoundException;

public class Chpt14_Project2 {
    public static void main(String[] args) throws FileNotFoundException {
        // File tags are located in.
        Scanner reader = new Scanner(new File("HTML.txt"));

        // Queue for tags to be added to.
        Queue<String> tagQueue = new LinkedList<>();

        // Stack for matching.
        Stack<String> tagStack = new Stack<>();

        // Extracting tags by searching for < and >.
        while (reader.hasNextLine()) {
            String tags = reader.nextLine();

            // Filtering out comments.
            if (tags.trim().startsWith("<!--"))
                continue;

            // Checking for opening tag.
            int start = tags.indexOf("<");
            while (start != -1) {
                // Trying to find closing tag.
                int end = tags.indexOf(">", start);
                if (end == -1)
                    break; // Malformed tag.
                // Found open and closing tags as a match.
                String tags2 = tags.substring(start, end + 1);
                // Add to queue.
                tagQueue.add(tags2);
                // System.out.println(tags2); Testing to see if file extraction works.
                start = tags.indexOf('<', end + 1);

            }
        }

        while (!tagQueue.isEmpty()) {
            // Removing first tag from queue.
            String tag = tagQueue.remove();

            // First check for self-closing.
            if (tag.endsWith("/>") || tag.equals("<br>") || tag.equals("<br/>")) {
                // Do nothing.
            }
            // Closing tag.
            else if (tag.startsWith("</")) {
                if (tagStack.isEmpty()) {
                    System.out.println("Error: Closing tag with no matching opening tag: " + tag);
                    continue;
                }
                // Skipping malformed tags.
                if (tag.length() < 4 || !tag.endsWith(">") || tag.indexOf('<') == -1 || tag.indexOf('>') == -1) {
                    System.out.println("Error: Malformed closing tag: " + tag);
                    continue;
                }

                String openTag = tagStack.pop();

                // Double-check that the opening tag is valid before substring.
                if (!openTag.startsWith("<") || !openTag.endsWith(">")) {
                    System.out.println("Error: malformed opening tag still on stack: " + openTag);
                    continue;
                }

                // Safely extract names.
                String openTagName = openTag.substring(1, openTag.length() - 1).split(" ")[0];
                String closingTagName = tag.substring(2, tag.length() - 1).split(" ")[0];

                if (!openTagName.equals(closingTagName)) {
                    System.out.println("Error: Tag mismatch. Expected </" + openTagName + "> but found " + tag);
                }
            } else if (tag.startsWith("<") && tag.endsWith(">")) {

                // Opening tag.
                // Extracting tag name, strip < and >, ignore attributes.
                tagStack.push(tag);
            } else {
                System.out.println("Error: malformed opening tag: " + tag);
            }

        }

        if (!tagStack.isEmpty()) {
            System.out.println("Unclosed tags at the end of the file:");
            while (!tagStack.isEmpty()) {
                System.out.println(" - " + tagStack.pop());
            }
        }
    }
}


/*
 * Time Complexity:
 * n = total number of characters in the input file.
 * m = total number of HTML tags extracted.
 * 
 * 1. Reading lines and extracting tags:
 *      while ( reader.hasNextLine())
 * 
 *      - Each line is scanned for < and > characters.
 *      - In the worst case, each character in the file is examined at most once.
 *          Time O(n).
 * 
 * 2. Queue Operations:
 *      - Each tag is add()ed to the queue -> O(1) per tag.
 *      - Then each tag is remove()d from the queue -> O(1) per tag.
 *          Time O(m).
 * 
 * 3. Stack Operations:
 *      - push() for openings tags -> O(1) per tag.
 *      - pop() for closing tags -> O(1) per tag.
 *      - Comparison of tag names:
 *          openTag.substring(1, openTag.length() - 1).split(" ")[0];
 *          - subString() is O(k), where k is tag length (usually very small, constant)
 *          - split() on a small tag is O(k) -> treated as O(1) for practical purposes.
 * 
 * Total Time Complexity: O(n + m). Basically O(n) constant because m <= n.
 * 
 * Space Complexity:
 * 1. Queue: Stores all tags -> O(m).
 * 2. Stack: Stores open tags -> O(d), where d is the maximum nesting depth HTML (d <= m)
 * 3. Other variables: Constant space (strings, indices)
 * 
 *  Total Space Complexity:
 *          O(m) -> Effectively O(n) in the worst case.
 * 
 */


/*
 * PREIMPLENMENTATION NOTES:
 * 
 * Step 1: Understand the problem
 * Goal: Read an HTML file and verify that all tags are properly matched, opened
 * and closed in the correct order.
 * Tags:
 * - Opening tag: <b>
 * - Closing tag: </b>
 * - Self-closing tag: <br> or <img.../>
 * Nesting:
 * - Tags can be nested, so you need ot check that tags close in the correct
 * order.
 * 
 * Step 2: What Data Structure to use?
 * Stack: Use a stack to keep track of currently open tags.
 * - When you see an opening tag, push it onto the stack.
 * - When you see a closing tag, pop from the stack and check if it matches.
 * Queue: You might use a queue to process the tags in order as you read them
 * from the file.
 * 
 * Step 3: How to process the HTML?
 * Read the file line by line.
 * Extract tags:
 * - For each line, find substrings that start with < and end with >.
 * Ignore comments and text outside tags.
 * For each tag:
 * - If it's self-closing tag, ignore it for matching.
 * - If it's an opening tag, push onto the stack.
 * - If it's a closing tag, pop from the stack and check for a match.
 * - If the stack is empty when you see a closing tag, that's an error.
 * - At the end, if the stack isn't empty, there are unmatched opening tags.
 * 
 * Step 4: Author's Guidelines
 * No index-based access:
 * - Don't use get, set, or for-each loops on stacks/queues.
 * Only use approved methods:
 * - push, pop, peek, isEmpty, size for stacks: add, remove, peek, isEmpty, size
 * for queues.
 * Restore state if you modify a parameter stack/queue.
 * Prefer iterative solutions.
 * 
 * Step 5: Pseudocode Outline
 * Read file and extract tags into a queue.
 * Process tags one by one:
 * - For each tag in the queue.
 * - if opening tag stack.push(tagName)
 * - if closing tag:
 * - If stack is empty, error.
 * - else, stack.pop() and check if matches.
 * - If self closing, ignore for matching.
 * 
 * At end
 * - If stack is not empty, error, unclosed tags.
 */