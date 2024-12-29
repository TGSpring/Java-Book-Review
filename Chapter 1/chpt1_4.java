/**
 * Tyler Spring
 * 12/29/2024
 * chapter 1 problem 4 
 */
public class chpt1_4 {
    public static void main(String[] args) {
        //Outer loop
        int numRows = 10;
        for(int i = 1; i <=numRows; i++) {
            //Inner loop
            for (int j = 1; j <= numRows - 1; j++) {
                System.out.print(" ");
                
            } 
            for(int k = 1; k <= i; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
