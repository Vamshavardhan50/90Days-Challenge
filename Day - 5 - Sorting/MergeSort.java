/*
 * MERGE SORT - Divide and Conquer Algorithm
 * Time Complexity: O(n log n) in all cases
 * Space Complexity: O(n) - requires extra space for temporary arrays
 * Stable: Yes - maintains relative order of equal elements
 */

public class MergeSort {

  // Main merge sort function
  public static void mergeSort(int[] arr, int left, int right) {
    if (left < right) {
      // Find the middle point
      int mid = left + (right - left) / 2;

      // Recursively sort first half
      mergeSort(arr, left, mid);

      // Recursively sort second half
      mergeSort(arr, mid + 1, right);

      // Merge the sorted halves
      merge(arr, left, mid, right);
    }
  }

  // Merge function to combine two sorted subarrays
  public static void merge(int[] arr, int left, int mid, int right) {
    // Calculate sizes of two subarrays
    int n1 = mid - left + 1; // Size of left subarray
    int n2 = right - mid; // Size of right subarray

    // Create temporary arrays
    int[] leftArray = new int[n1];
    int[] rightArray = new int[n2];

    // Copy data to temporary arrays
    for (int i = 0; i < n1; i++) {
      leftArray[i] = arr[left + i];
    }
    for (int j = 0; j < n2; j++) {
      rightArray[j] = arr[mid + 1 + j];
    }

    // Merge the temporary arrays back into arr[left...right]
    int i = 0; // Initial index of left subarray
    int j = 0; // Initial index of right subarray
    int k = left; // Initial index of merged array

    // Compare elements from both arrays and merge
    while (i < n1 && j < n2) {
      if (leftArray[i] <= rightArray[j]) {
        arr[k] = leftArray[i];
        i++;
      } else {
        arr[k] = rightArray[j];
        j++;
      }
      k++;
    }

    // Copy remaining elements of leftArray[] if any
    while (i < n1) {
      arr[k] = leftArray[i];
      i++;
      k++;
    }

    // Copy remaining elements of rightArray[] if any
    while (j < n2) {
      arr[k] = rightArray[j];
      j++;
      k++;
    }
  }

  // Utility function to print array
  public static void printArray(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  // Main method to test merge sort
  public static void main(String[] args) {
    int[] arr = { 38, 27, 43, 3, 9, 82, 10 };

    System.out.println("Original Array:");
    printArray(arr);

    // Call merge sort
    mergeSort(arr, 0, arr.length - 1);

    System.out.println("\nSorted Array:");
    printArray(arr);

    // Test with another array
    System.out.println("\n--- Test Case 2 ---");
    int[] arr2 = { 64, 25, 12, 22, 11 };
    System.out.println("Original Array:");
    printArray(arr2);

    mergeSort(arr2, 0, arr2.length - 1);

    System.out.println("Sorted Array:");
    printArray(arr2);
  }
}
