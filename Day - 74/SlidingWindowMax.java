import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMax {

  public static void main(String[] args) {

    int[] nums = { 5, 1, 3, 4, 2, 6 };
    int k = 3;

    int[] ans = maxSlidingWindow(nums, k);

    System.out.println(Arrays.toString(ans));
  }

  public static int[] maxSlidingWindow(int[] nums, int k) {

    int n = nums.length;

    int[] res = new int[n - k + 1];

    // Stores indices
    Deque<Integer> dq = new LinkedList<>();

    int idx = 0;

    for (int i = 0; i < n; i++) {

      // Remove indices outside window
      while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
        dq.pollFirst();
      }

      // Remove smaller elements from back
      while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
        dq.pollLast();
      }

      // Add current index
      dq.offerLast(i);

      // Window formed
      if (i >= k - 1) {
        res[idx++] = nums[dq.peekFirst()];
      }
    }

    return res;
  }
}