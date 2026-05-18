public class BinarySubWithSum {

  public static void main(String[] args) {

    int[] arr = { 1, 0, 1, 0, 1 };
    int target = 2;

    int count = 0;

    // Pick starting index
    for (int i = 0; i < arr.length; i++) {

      int sum = 0;

      // Extend subarray from i to j
      for (int j = i; j < arr.length; j++) {

        sum += arr[j];

        // Check if subarray sum equals target
        if (sum == target) {
          count++;
        }
      }
    }

    System.out.println(count);
  }
}