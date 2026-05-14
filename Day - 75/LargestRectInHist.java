import java.util.Arrays;
import java.util.*;

public class LargestRectInHist {
  public static void main(String[] args) {
    int[] heights = { 2, 1, 5, 6, 2, 3 };

    int n = heights.length;

    int[] next = nextsmaller(heights);
    int[] prev = prevsmaller(heights);

    int maxArea = 0;

    for (int i = 0; i < n; i++) {

      int width = next[i] - prev[i] - 1;

      int area = heights[i] * width;

      maxArea = Math.max(maxArea, area);
    }
    System.out.println(maxArea);

  }

  public static int[] nextsmaller(int[] nums) {
    int n = nums.length;
    int[] ans = new int[n];

    Stack<Integer> st = new Stack<>();

    for (int i = n - 1; i >= 0; i--) {

      while (!st.isEmpty() &&
          nums[st.peek()] >= nums[i]) {

        st.pop();
      }

      ans[i] = st.isEmpty() ? n : st.peek();

      st.push(i);
    }

    return ans;
  }

  public static int[] prevsmaller(int[] nums) {

    int n = nums.length;
    int[] ans = new int[n];

    Stack<Integer> st = new Stack<>();

    for (int i = 0; i < n; i++) {

      while (!st.isEmpty() &&
          nums[st.peek()] >= nums[i]) {

        st.pop();
      }

      ans[i] = st.isEmpty() ? -1 : st.peek();

      st.push(i);
    }

    return ans;
  }

}
