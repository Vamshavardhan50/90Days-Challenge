public class K_thElementofarrays {

  public static void main(String[] args) {

    int[] nums1 = { 2, 3, 6, 7, 9 };
    int[] nums2 = { 1, 4, 8, 10 };
    int k = 5;

    int i = 0, j = 0, count = 0;
    int kthElement = -1;

    while (i < nums1.length && j < nums2.length) {
      if (nums1[i] <= nums2[j]) {
        kthElement = nums1[i];
        i++;
      } else {
        kthElement = nums2[j];
        j++;
      }
      count++;
      if (count == k) {
        break;
      }
    }

    // If nums1 still has elements
    while (count < k && i < nums1.length) {
      kthElement = nums1[i];
      i++;
      count++;
    }

    // If nums2 still has elements
    while (count < k && j < nums2.length) {
      kthElement = nums2[j];
      j++;
      count++;
    }

    System.out.println("K-th element = " + kthElement);
  }
}
