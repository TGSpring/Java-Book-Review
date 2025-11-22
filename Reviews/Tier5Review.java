//Idk how to name these anymore just go with it.

public class Tier5Review {

    public static void main(String[] args) {
        int[] arr = {3, 10, 2, 1, 20};
        int n = arr.length;

        int[] dp = new int[n];

        // Each element is at least an LIS of length 1 by itself.
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > maxLength) {
                maxLength = dp[i];
            }
        }

        System.out.println("Length of LIS: " + maxLength);

    }
}
