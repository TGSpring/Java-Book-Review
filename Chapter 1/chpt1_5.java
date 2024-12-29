/**
 * Tyler Spring
 * 12/29/2024
 * chapter 1 problem 5 
 */
public class chpt1_5 {
 public static void main(String[] args) { 
    int numRows = 10;

    for(int row = 1; row <= numRows; row++) {
        if (row == 1 || row == numRows) {
            //Prints full row
            for (int i = 1; i <= numRows; i++) {
                System.out.print("*");
            } 
        } else {
            //Prints first
            System.out.print("*");

            //Prints spaces
            for (int j = 2; j <= numRows - 1; j++) {
                System.out.print(" ");
             }
                  
        System.out.print("*");

        }
             
        System.out.println();

}

}
}