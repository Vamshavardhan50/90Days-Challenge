// Brute force approach to solve but we need to solve in the BS

// public class KthMissingPosiNumber {
//   public static void main(String[] args) {
//     int[] vec = { 4, 7, 9, 10 }; // Sorted array
//     int k = 4; // We want the 4th missing number

//     KthMissingPosiNumber finder = new KthMissingPosiNumber();
//     int ans = finder.missingK(vec, k); // Call method

//     System.out.println("The missing number is: " + ans); // Output result

//   }

//   public int missingK(int[] vec, int k) {
//     for (int i = 0; i < vec.length; i++) {
//       if (vec[i] <= k) {
//         k++; // Skip current number and adjust k
//       } else {
//         break; // Stop if current number is greater than k
//       }
//     }
//     return k;
//   }
// }
public class KthMissingPosiNumber {
  public static void main(String[] args) {
    int[] vec = { 4, 7, 9, 10 }; // Sorted array
    int k = 4; // We want the 4th missing number

    KthMissingPosiNumber finder = new KthMissingPosiNumber();
    int ans = finder.missingK(vec, k); // Call method

    System.out.println("The missing number is: " + ans); // Output result

  }

  public int missingK(int[] vec, int k) {
    int low = 0, high = vec.length;

    while (low <= high) {
      int mid = low + (high - low) / 2;

      int missing = vec[mid] - (mid + 1);

      if (missing < k) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return low + k;
  }
}
