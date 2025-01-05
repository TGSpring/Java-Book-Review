/**
 * Tyler Spring
 * 1/5/2025
 * 2.6
 * Drawing rectangle from user input.
 */
import java.util.Scanner;

public class chpt2_6 {

    public static void drawRec(int width, int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);

        System.out.println("Enter width:");
        int width = sc.nextInt();

        System.out.println("Enter height:");
        int height = sc.nextInt();

        drawRec(width, height);
        sc.close();
    }
}
