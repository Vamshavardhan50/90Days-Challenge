import java.util.Arrays;

public class SelectionSort {
  public static void main(String[] args) {

    // Sample array to sort
    int[] arr = { 13, 46, 24, 52, 20, 9 };

    /*
     * Selection Sort Idea:
     * 
     * We divide the array into two parts:
     * 1) The left side → already sorted
     * 2) The right side → not sorted yet
     * 
     * In every iteration:
     * - Find the smallest element from the unsorted part
     * - Swap it with the first element of the unsorted part
     * - That element now becomes part of the sorted section
     */

    for (int i = 0; i < arr.length; i++) {

      // Find the index of the smallest value starting from position i
      int minIndex = MinSearch(arr, i);

      // Swap the current element with the smallest element found
      int temp = arr[i];
      arr[i] = arr[minIndex];
      arr[minIndex] = temp;
    }

    // Print the sorted array
    System.out.println(Arrays.toString(arr));
  }

  /*
   * This method searches for the smallest element
   * in the array starting from the given index.
   * 
   * It returns the index of that smallest element,
   * not the value itself.
   */
  public static int MinSearch(int[] arr, int start) {

    // Assume the first element in this range is the smallest
    int minIndex = start;

    // Check the rest of the elements to see if we find something smaller
    for (int i = start; i < arr.length; i++) {
      if (arr[i] < arr[minIndex]) {
        minIndex = i; // Update if a smaller element is found
      }
    }

    return minIndex; // Return the position of the smallest element
  }
}
