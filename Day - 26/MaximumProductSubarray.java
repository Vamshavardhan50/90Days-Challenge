public class MaximumProductSubarray {

  public static void main(String[] args) {

    int[] arr = { 1, 2, -3, 0, -4, -5 };
    int n = arr.length;

    int max = Integer.MIN_VALUE;

    int prefix = 1;
    for (int i = 0; i < n; i++) {
      prefix *= arr[i];
      max = Math.max(max, prefix);
      if (prefix == 0) {
        prefix = 1;
      }
    }

    int suffix = 1;
    for (int i = n - 1; i >= 0; i--) {
      suffix *= arr[i];
      max = Math.max(max, suffix);
      if (suffix == 0) {
        suffix = 1;
      }
    }

    System.out.println(max);
  }
}
