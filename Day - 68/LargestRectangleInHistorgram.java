public class LargestRectangleInHistorgram {
  public static void main(String[] args) {
    int[] heights = { 0, 1, 0, 1 };
    System.out.println(largestRectangleArea(heights));

  }

  public static int largestRectangleArea(int[] heights) {
    int max = 0;

    for (int i = 0; i < heights.length; i++) {
      int min = Integer.MAX_VALUE;

      for (int j = i; j < heights.length; j++) {
        min = Math.min(min, heights[j]); // update min dynamically
        int width = j - i + 1;
        int area = min * width;
        max = Math.max(max, area);
      }
    }
    return max;
  }

  public static int minValuerange(int[] arr, int start, int end) {
    int min = Integer.MAX_VALUE;
    for (int i = start; i <= end; i++) {
      if (arr[i] < min) {
        min = arr[i];
      }
    }
    return min;
  }

}
