import java.util.Arrays;


public class Sumofmatrix {
  public static void main(String[] args) {
    int[][] nums = { { 7, 2, 1 }, { 6, 4, 2 }, { 6, 5, 3 }, { 3, 2, 1 } };

    int ans = 0;

    for (int[] row : nums) {
      Arrays.sort(row);
    }

    for (int i = 0; i < nums[0].length; i++) {
      int max = Integer.MIN_VALUE;

      for (int j = 0; j < nums.length; j++) {
        max = Math.max(max, nums[j][i]);
      }
      ans += max;
    }
    System.out.println(ans);
  }
}
