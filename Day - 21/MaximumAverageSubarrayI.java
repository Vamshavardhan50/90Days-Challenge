// public class MaximumAverageSubarrayI {
//   public static void main(String[] args) {
//     int[] arr = { 1, 12, -5, -6, 50, 3 };
//     int n = arr.length;
//     int k = 4;

//     int maxAvg = Integer.MIN_VALUE;
//     for (int i = 0; i <= n - k; i++) {
//       int sum = 0; // Brutre force
//       for (int j = i; j < i + k; j++) {
//         sum += arr[j];
//       }
//       System.out.println(sum);
//       int avg = sum / k;
//       if (avg > maxAvg) {
//         maxAvg = avg;
//       }
//     }

//     System.out.println("max: " + maxAvg);
//   }
// }

import java.util.Arrays;

public class MaximumAverageSubarrayI {
  public static void main(String[] args) {
    int[] arr = { 1, 12, -5, -6, 50, 3 };
    int n = arr.length;
    int k = 4;

    // Prefix sum array
    int[] pre = new int[n];
    pre[0] = arr[0];
    for (int i = 1; i < n; i++) {
      pre[i] = pre[i - 1] + arr[i];
    }

    System.out.println(Arrays.toString(pre));

    float maxAvg = Float.NEGATIVE_INFINITY;

    // Compute average using prefix sums
    for (int i = k - 1; i < n; i++) {
      int subArraySum;
      if (i == k - 1) {
        subArraySum = pre[i];
      } else {
        subArraySum = pre[i] - pre[i - k];
      }

      float avg = (float) subArraySum / k;
      maxAvg = Math.max(maxAvg, avg);
    }

    System.out.println(maxAvg);
  }
}
