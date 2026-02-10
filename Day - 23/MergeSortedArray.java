import java.util.Arrays;

public class MergeSortedArray {
  public static void main(String[] args) {

    int kth = 5;
    int[] nums1 = { 2, 3, 6, 7, 9 };
    int m = 5;

    int[] nums2 = { 1, 4, 8, 10 };
    int n = 3;

    int i = m - 1; // last valid element in nums1
    int j = n - 1; // last element in nums2
    int k = m + n - 1; // last index of nums1

    // Merge from the back
    while (i >= 0 && j >= 0) {
      if (nums1[i] > nums2[j]) {
        nums1[k--] = nums1[i--];
      } else {
        nums1[k--] = nums2[j--];
      }
    }

    // Copy remaining nums2 elements
    while (j >= 0) {
      nums1[k--] = nums2[j--];
    }

    System.out.println(Arrays.toString(nums1));
  }
}
