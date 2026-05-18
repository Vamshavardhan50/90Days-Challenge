public class CountNumberofNiceSubarrays {
  public static void main(String[] args) {
    int[] arr = { 1, 1, 2, 1, 1 };
    int k = 3;

    int count = 0;

    // Pick starting index
    for (int i = 0; i < arr.length; i++) {

      int oddcount = 0;

      // Extend subarray from i to j
      for (int j = i; j < arr.length; j++) {

        if ((arr[j] & 1) == 1) {
          oddcount++;
        }

        // Check if subarray sum equals target
        if (oddcount == k) {
          count++;
        }
      }
    }

    System.out.println(count);
  }
}
