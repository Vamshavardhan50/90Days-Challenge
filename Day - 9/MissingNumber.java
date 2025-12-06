public class MissingNumber {
  public static void main(String[] args) {
    int[] nums = { 3, 0, 1 };
    int n = nums.length;
    int sum = 0;
    int sum_of_natural = (n * (n + 1)) / 2;
    for (int i = 0; i < n; i++) {
      sum = sum + nums[i];
    }
    System.out.println(sum_of_natural - sum);
  }
}