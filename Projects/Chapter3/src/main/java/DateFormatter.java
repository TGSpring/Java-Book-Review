import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DateFormatter {

    /**
     * Validates and formats a date.
     * Prompts the user until a valid date is entered.
     * Converts the formatted date into a Unix timestamp.
     */
    public long validateAndFormatDate(Scanner sc) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);
        Date date = null;

        while (date == null) {
            System.out.print("Enter a date (MM/dd/yyyy): ");
            String dateStr = sc.nextLine();

            try {
                date = dateFormat.parse(dateStr);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }

        System.out.println("Formatted Date: " + dateFormat.format(date));
        return date.getTime() / 1000; // Convert to Unix timestamp
    }
}