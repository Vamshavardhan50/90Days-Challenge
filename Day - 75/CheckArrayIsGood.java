import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CheckArrayIsGood {

  public static void main(String[] args) {

    int[] nums = { 2, 3 };

    Arrays.sort(nums);

    System.out.println(Arrays.toString(nums));

    int n = nums.length - 1;

    HashMap<Integer, Integer> map = new HashMap<>();

    // Frequency count
    for (int i = 0; i < nums.length; i++) {
      map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
    }

    boolean isGood = true;

    // Check numbers 1 to n-1 appear once
    for (int i = 1; i < n; i++) {

      if (map.getOrDefault(i, 0) != 1) {
        isGood = false;
        break;
      }
    }

    // Check n appears exactly twice
    if (map.getOrDefault(n, 0) != 2) {
      isGood = false;
    }

    // No extra numbers allowed
    if (map.size() != n) {
      isGood = false;
    }

    System.out.println(isGood);
  }
}