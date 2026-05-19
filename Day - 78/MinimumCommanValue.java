public class MinimumCommanValue {
  public static void main(String[] args) {
    int[] nums1 = { 1, 2, 3 };
    int[] nums2 = { 2, 4 };

    int i = 0, j = 0;

    while (i < nums1.length && j < nums2.length) {
      if (nums1[i] == nums2[j]) {
        System.out.println(nums1[i]);
        break;
      }
      if (nums1[i] > nums2[j]) {
        j++;
      } else {
        i++;
      }

    }
  }
}
