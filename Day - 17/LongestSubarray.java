// import java.util.*;
// import java.util.stream.IntStream;;

// public class LongestSubarray {
//   public static void main(String[] args) {
//     int[] arr = { 9, -3, 3, -1, 6, -5 };
//     int max = Integer.MIN_VALUE;
//     int n = arr.length;

//     for (int i = 0; i < n; i++) {
//       for (int j = i; j < n; j++) {

//         int[] subArray = Arrays.copyOfRange(arr, i, j + 1); // As this wont work because its take O(n2) this wont be accepted and 
//         int sum = IntStream.of(subArray).sum();

//         if (subArray.length >= max && sum == 0) {
//           max = subArray.length;
//         }
//       }
//     }
//     System.out.println("Longest Maximum Subarray : " + max);
//   }

// }

import java.util.HashMap;
import java.util.Scanner;

public class LongestSubarray {
  public static void main(String[] args) {

    int[] arr = { 9, -3, 3, -1, 6, -5 };

    HashMap<Integer, Integer> map = new HashMap<>();
    int sum = 0;
    int maxLen = 0;

    // Important: handles subarray starting from index 0
    map.put(0, -1);

    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];

      // If prefix sum already exists, zero-sum subarray found
      if (map.containsKey(sum)) {
        int len = i - map.get(sum);
        maxLen = Math.max(maxLen, len);
      }
      // Store first occurrence only
      else {
        map.put(sum, i);
      }
    }

    System.out.println("Longest Zero Sum Subarray Length: " + maxLen);
  }
}
