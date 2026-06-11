import java.util.Arrays;

public class LeftAndRightSum {
    public static void main(String[] args) {
        int[] nums = { 1 };

        int[] pre = new int[nums.length];
        int[] suf = new int[nums.length];

        // Left sums
        pre[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }

        // Right sums
        suf[nums.length - 1] = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            suf[i] = suf[i + 1] + nums[i + 1];
        }

        int[] res = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int sum = pre[i] - suf[i];
            if (sum < 0) {
                sum = -1 * sum;
            }
            res[i] = sum;
        }
        System.out.println(Arrays.toString(res));

    }
}