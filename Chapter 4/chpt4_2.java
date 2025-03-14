/**
 * Tyler Spring
 * 3/14/2025
 * Chapter 4 question 2
 * 
 * Grade Calculator
 * 
 * Write a method getGrade(int score) that returns:
 * "A" if score >= 90
 * "B" if score >= 80
 * "C" if score >= 70
 * "D" if score >= 60
 * "F" otherwise
 * 
 */
public class chpt4_2 {
    public static void main(String[] args) {
        System.out.println(getGrade(91));
        System.out.println(getGrade(90));
        System.out.println(getGrade(89));
        System.out.println(getGrade(100));
        System.out.println(getGrade(79));
        System.out.println(getGrade(60));
        System.out.println(getGrade(59));
    }

    public static String getGrade(int score) {
        if (score >= 90)
            return "A";
        if (score >= 80)
            return "B";
        if (score >= 70)
            return "C";
        if (score >= 60)
            return "D";
        return "F";
    }
}
