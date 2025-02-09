import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class SmartFinTest {

    @Test
    public void testValidationWithValidInteger() {
        // Simulate a valid positive integer input ("42") followed by a newline.
        String input = "42\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner sc = new Scanner(in);
        SmartFin smartFin = new SmartFin();
        
        // Expect that the validation method correctly parses "42" as a number.
        double result = smartFin.validation(sc, "Enter number: ", true);
        assertEquals(42.0, result);
        sc.close();
    }

    @Test
    public void testValidationWithValidDouble() {
        // Simulate a valid double input ("3.14") followed by a newline.
        String input = "3.14\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner sc = new Scanner(in);
        SmartFin smartFin = new SmartFin();
        
        // Expect that the validation method correctly parses "3.14".
        double result = smartFin.validation(sc, "Enter number: ", false);
        assertEquals(3.14, result);
        sc.close();
    }
    
    @Test
    public void testValidationInvalidThenValid() {
        // Simulate an invalid input followed by a valid integer.
        // First "abc" is invalid, then "100" is valid.
        String input = "abc\n100\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner sc = new Scanner(in);
        SmartFin smartFin = new SmartFin();
        
        // The validation method should loop until it reads a valid number ('100').
        double result = smartFin.validation(sc, "Enter number: ", true);
        assertEquals(100.0, result);
        sc.close();
    }
}
