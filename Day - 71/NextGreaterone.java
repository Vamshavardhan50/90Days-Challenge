import java.util.*;

public class NextGreaterone {
  public static void main(String[] args) {
    int[] arr1 = { 4, 1, 2 };
    int[] arr2 = { 1, 3, 4, 2 };
    System.out.println(Arrays.toString(nextGreaterElement(arr1, arr2)));
  }

  public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
    HashMap<Integer, Integer> map = new HashMap<>();
    Stack<Integer> st = new Stack<>();

    // Process nums2 from right to left
    for (int i = nums2.length - 1; i >= 0; i--) {
      while (!st.isEmpty() && st.peek() <= nums2[i]) {
        st.pop();
      }

      map.put(nums2[i], st.isEmpty() ? -1 : st.peek());
      st.push(nums2[i]);
    }

    // Build result for nums1
    int[] ans = new int[nums1.length];
    for (int i = 0; i < nums1.length; i++) {
      ans[i] = map.get(nums1[i]);
    }

    return ans;
  }
}