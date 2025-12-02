/*
 * RECURSIVE BUBBLE SORT
 * 
 * Concept: Use recursion instead of nested loops
 * - Each recursive call performs one pass (bubbles largest element to end)
 * - Recursively sort the remaining n-1 elements
 * 
 * Time Complexity: O(n²) in worst/average case, O(n) in best case
 * Space Complexity: O(n) due to recursion stack
 * Stable: Yes
 */

public class RecursiveBubbleSort {

  // Recursive bubble sort function
  public static void recursiveBubbleSort(int[] arr, int n) {
    // Base case: if array size is 1, it's already sorted
    if (n == 1) {
      return;
    }

    // One pass of bubble sort - bubble up the largest element
    // After this pass, the largest element is at the end
    for (int i = 0; i < n - 1; i++) {
      if (arr[i] > arr[i + 1]) {
        // Swap if elements are in wrong order
        int temp = arr[i];
        arr[i] = arr[i + 1];
        arr[i + 1] = temp;
      }
    }

    // Recursively sort the remaining n-1 elements
    // Last element is already in correct position
    recursiveBubbleSort(arr, n - 1);
  }

  // Alternative approach: Pure recursion (no loop)
  public static void recursiveBubbleSortPure(int[] arr, int n, int index) {
    // Base case 1: if array size is 1, sorted
    if (n == 1) {
      return;
    }

    // Base case 2: if we've compared all adjacent pairs in this pass
    if (index == n - 1) {
      // Start next pass with reduced size
      recursiveBubbleSortPure(arr, n - 1, 0);
      return;
    }

    // Compare and swap if needed
    if (arr[index] > arr[index + 1]) {
      int temp = arr[index];
      arr[index] = arr[index + 1];
      arr[index + 1] = temp;
    }

    // Move to next pair in current pass
    recursiveBubbleSortPure(arr, n, index + 1);
  }

  // Optimized recursive bubble sort with flag
  public static void recursiveBubbleSortOptimized(int[] arr, int n) {
    // Base case: if array size is 1, sorted
    if (n == 1) {
      return;
    }

    boolean swapped = false;

    // One pass of bubble sort
    for (int i = 0; i < n - 1; i++) {
      if (arr[i] > arr[i + 1]) {
        int temp = arr[i];
        arr[i] = arr[i + 1];
        arr[i + 1] = temp;
        swapped = true;
      }
    }

    // If no swaps occurred, array is sorted
    if (!swapped) {
      return;
    }

    // Recursively sort remaining elements
    recursiveBubbleSortOptimized(arr, n - 1);
  }

  // Utility function to print array
  public static void printArray(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  // Main method to test recursive bubble sort
  public static void main(String[] args) {
    // Test Case 1: Standard recursive bubble sort
    System.out.println("=== Test 1: Standard Recursive Bubble Sort ===");
    int[] arr1 = { 64, 34, 25, 12, 22, 11, 90 };
    System.out.println("Original Array:");
    printArray(arr1);

    recursiveBubbleSort(arr1, arr1.length);

    System.out.println("Sorted Array:");
    printArray(arr1);

    // Test Case 2: Pure recursive approach (no loop)
    System.out.println("\n=== Test 2: Pure Recursive Bubble Sort ===");
    int[] arr2 = { 5, 1, 4, 2, 8 };
    System.out.println("Original Array:");
    printArray(arr2);

    recursiveBubbleSortPure(arr2, arr2.length, 0);

    System.out.println("Sorted Array:");
    printArray(arr2);

    // Test Case 3: Optimized recursive bubble sort
    System.out.println("\n=== Test 3: Optimized Recursive Bubble Sort ===");
    int[] arr3 = { 1, 2, 5, 4, 3 }; // Nearly sorted
    System.out.println("Original Array:");
    printArray(arr3);

    recursiveBubbleSortOptimized(arr3, arr3.length);

    System.out.println("Sorted Array:");
    printArray(arr3);

    // Test Case 4: Already sorted array
    System.out.println("\n=== Test 4: Already Sorted (Best Case) ===");
    int[] arr4 = { 1, 2, 3, 4, 5 };
    System.out.println("Original Array:");
    printArray(arr4);

    recursiveBubbleSortOptimized(arr4, arr4.length);

    System.out.println("Sorted Array (should return immediately):");
    printArray(arr4);
  }
}
