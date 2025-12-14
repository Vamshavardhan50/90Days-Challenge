
class Solution {
  // Function to find count of subarrays with sum equal to k
  public int subarraySum(int[] arr, int k) {
    // Size of the array
    int n = arr.length;

    // Initialize count of subarrays
    int count = 0;

    // Traverse all possible start indices
    for (int i = 0; i < n; i++) {
      // Initialize sum for current subarray
      int sum = 0;

      // Traverse all possible end indices from start
      for (int j = i; j < n; j++) {
        // Add current element to sum
        sum += arr[j];

        // If sum equals k, increment count
        if (sum == k) {
          count++;
        }
      }
    }

    // Return total count of subarrays
    return count;
  }
}

public class SubarrayWithSumK {
  public static void main(String[] args) {
    // Input array
    int[] arr = { 3, 1, 2, 4 };

    // Target sum
    int k = 6;

    // Create Solution object
    Solution sol = new Solution();

    // Call function and store result
    int result = sol.subarraySum(arr, k);

    // Print the count of subarrays
    System.out.println("The number of subarrays is: " + result);
  }
}
