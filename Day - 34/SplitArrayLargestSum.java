import java.util.Arrays;

public class SplitArrayLargestSum {

  public int splitArray(int[] nums, int k) {
    int low = Arrays.stream(nums).max().getAsInt();
    int high = Arrays.stream(nums).sum();

    int answer = high;

    while (low <= high) {
      int mid = low + (high - low) / 2;

      if (isValid(mid, nums, k)) {
        answer = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return answer;
  }

  private boolean isValid(int maxAllowedSum, int[] nums, int k) {
    int subarrays = 1;
    int currentSum = 0;

    for (int num : nums) {
      if (currentSum + num <= maxAllowedSum) {
        currentSum += num;
      } else {
        subarrays++;
        currentSum = num;

        if (subarrays > k)
          return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    SplitArrayLargestSum solution = new SplitArrayLargestSum();
    int[] nums = { 7, 2, 5, 10, 8 };
    int k = 2;
    System.out.println(solution.splitArray(nums, k));
  }
}
