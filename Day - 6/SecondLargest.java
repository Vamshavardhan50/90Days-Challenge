import java.util.Arrays;

class Solution {

    // Method to find the second smallest and second largest elements in the array
    public static void getElements(int[] arr, int n) {
        
        // Edge case: when the array has less than 2 elements
        if (n == 0 || n == 1) {
            System.out.println(-1 + " " + -1);  // Print -1 for both second smallest and second largest
            return;
        }

        // Sort the array to easily find the second smallest and second largest elements
        Arrays.sort(arr);

        // Second smallest element is at index 1 after sorting
        int small = arr[1];

        // Second largest element is at index n-2 after sorting
        int large = arr[n - 2];

        // Output the second smallest and second largest elements
        System.out.println("Second smallest is " + small);
        System.out.println("Second largest is " + large);
    }
}

public class Main {

    public static void main(String[] args) {
        
        // Initialize the array with elements
        int[] arr = {1, 2, 4, 6, 7, 5};
        
        // Calculate the size of the array
        int n = arr.length;
        
        // Call the method to find and print the second smallest and second largest elements
        Solution.getElements(arr, n);
    }
}