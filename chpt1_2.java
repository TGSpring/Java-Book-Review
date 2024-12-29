/**
 * Tyler Spring
 * 12/29/2024
 * chapter 1 problem 2 * 
 */
public class chpt1_2 {

    public static void main(String[] args) {
        //Outer loop
        for (int i = 1; i <= 7; i++) {
            //Inner loop
            for(int j = 1; j <= i; j++) {
                System.out.print("+");
            }
            System.out.println();
        }
    }
}