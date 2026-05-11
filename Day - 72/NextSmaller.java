import java.util.*;

public class NextSmaller {
  public static void main(String[] args) {

    int[] arr1 = { 4, 8, 5, 2, 25 };
    System.out.println(Arrays.toString(nextsmaller(arr1)));
  }

  public static int[] nextsmaller(int[] nums1) {
    HashMap<Integer, Integer> map = new HashMap<>();
    Stack<Integer> st = new Stack<>();

    // Process nums1 from right to left
    for (int i = nums1.length - 1; i >= 0; i--) {
      while (!st.isEmpty() && st.peek() >= nums1[i]) {
        st.pop();
      }

      map.put(nums1[i], st.isEmpty() ? -1 : st.peek());
      st.push(nums1[i]);
    }

    // Build result for nums1
    int[] ans = new int[nums1.length];
    for (int i = 0; i < nums1.length; i++) {
      ans[i] = map.get(nums1[i]);
    }

    return ans;
  }
}