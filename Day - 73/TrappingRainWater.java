import java.util.Arrays;

public class TrappingRainWater {
  public static void main(String[] args) {
    int[] arr = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };

    int[] leftMax = new int[arr.length];
    int[] RightMax = new int[arr.length];

    leftMax[0] = arr[0];
    RightMax[arr.length - 1] = arr[arr.length - 1];

    // leftmax prefix array to access faster
    for (int i = 1; i < arr.length; i++) {
      leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
    }

    // RightMax prefix array to access faster
    for (int i = arr.length - 2; i >= 0; i--) {
      RightMax[i] = Math.max(RightMax[i + 1], arr[i]);
    }
    System.out.println(Arrays.toString(leftMax));
    System.out.println(Arrays.toString(RightMax));

    int ans = 0;
    for (int i = 0; i < arr.length; i++) {
      ans += Math.min(leftMax[i], RightMax[i]) - arr[i];
    }
    System.out.println(ans);
  }
}