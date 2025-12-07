// Longest Subarray with given Sum K (Positives)

import java.util.Arrays;

public class LongestSubarray {
  public static void main(String[] args) {
    int[] arr = { 10, 5, 2, 7, 1, 9 };
    int k = 15;

    int n = arr.length;

    // prefixSum[i] = sum of arr[0..i-1]
    // prefixSum[0] = 0
    int[] prefixSum = new int[n + 1];
    prefixSum[0] = 0;

    for (int i = 0; i < n; i++) {
      prefixSum[i + 1] = prefixSum[i] + arr[i];
    }

    System.out.println("Prefix sum: " + Arrays.toString(prefixSum));

    int max=Integer.MIN_VALUE;
    for(int i=1;i<prefixSum.length;i++){
      if(prefixSum[i]==15){
        max=i;
      }
    }
    System.out.println(max);
  }
}
