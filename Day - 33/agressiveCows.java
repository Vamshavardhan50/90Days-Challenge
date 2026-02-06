import java.util.Arrays;

public class agressiveCows {

  public static void main(String[] args) {

    // Positions of stalls
    int[] arr = { 1, 2, 8, 4, 9 };

    // Number of cows to place
    int cows = 3;

    // Sort the stall positions to apply binary search logic
    Arrays.sort(arr);

    // Variable to store the final answer (maximum minimum distance)
    int ans = 0;

    // Minimum possible distance between two cows
    // Maximum possible distance (last stall - first stall)
    int low = 1, high = arr[arr.length - 1] - arr[0];

    // Binary search on the answer
    while (low <= high) {

      // Find middle distance
      int mid = low + (high - low) / 2;

      // Check if we can place all cows with at least 'mid' distance
      if (ispossible(mid, arr, cows)) {
        // If possible, update answer
        ans = mid;
        // Try for a larger minimum distance
        low = mid + 1;

      } else {
        // If not possible, reduce distance
        high = mid - 1;
      }
    }
    // Print the maximum minimum distance
    System.out.println(ans);
  }

  // This function checks if we can place 'c' cows
  // such that the minimum distance between any two is at least 'mid'
  public static boolean ispossible(int mid, int[] arr, int c) {

    // Position of last placed cow (place first cow at first stall)
    int lastpos = arr[0];
    // Number of cows placed so far
    int cow = 1;
    // Try placing remaining cows
    for (int i = 1; i < arr.length; i++) {
      // If current stall is at least 'mid' distance away
      if ((arr[i] - lastpos) >= mid) {
        // Place next cow
        cow++;
        lastpos = arr[i];
      }
      // If all cows are placed successfully
      if (cow == c) {
        return true;
      }
    }
    // If we could not place all cows
    return false;
  }
}
