public class SubArrayMin {

}

class Solution {

  public void printSubarrays(int[] arr) {
    generate(arr, 0);
  }

  // Recursive function for starting index
  private void generate(int[] arr, int start) {

    // Base case
    if (start == arr.length) {
      return;
    }

    // Print all subarrays starting from 'start'
    print(arr, start, start);

    // Move to next starting index
    generate(arr, start + 1);
  }

  // Recursive function for ending index
  private void print(int[] arr, int start, int end) {

    // Base case
    if (end == arr.length) {
      return;
    }

    // Print current subarray
    for (int i = start; i <= end; i++) {
      System.out.print(arr[i] + " ");
    }

    System.out.println();

    // Extend subarray
    print(arr, start, end + 1);
  }
}