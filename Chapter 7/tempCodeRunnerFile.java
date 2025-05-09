public class Chpt7_7 {
    public static void main(String[] args) {
        int[] nums = { 3, 8, 10, 1, 9, 14, -3, 0, 14, 207, 56, 98, 12 };
        System.out.println(longestSortedSequence(nums));
    }

    public static int longestSortedSequence(int[] nums) {
        // if array is empty.
        if (nums.length == 0) {

            return 0;
        }
        // Length to be returned
        int maxLength = 0;
        int curLength = 1;
        // Iterate array of integers.
        for (int i = 0; i < nums.length - 1; i++) {

            if (nums[i + 1] >= nums[i]) {
                System.out.println(nums[i + 1] + " " + nums[i]);
                curLength++;

            } else {
                maxLength = curLength;
                curLength = 1;
            }

        }
        maxLength = Math.max(maxLength, curLength);

        return maxLength;

    }
}
