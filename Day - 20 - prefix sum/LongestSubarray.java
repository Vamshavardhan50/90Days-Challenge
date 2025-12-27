import java.util.HashMap;

public class LongestSubarray {

  public static void main(String[] args) {
    int[] arr = { 10, 5, 2, 7, 1, -10 };
    int target = 15;

    HashMap<Integer, Integer> map = new HashMap<>();

    int sum = 0;
    int ans = 0;

    // Handles subarray starting from index 0
    map.put(0, -1);

    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];

      // Case 1: subarray starts from index 0
      if (sum == target) {
        ans = Math.max(ans, i + 1);
      }

      // Case 2: subarray exists between two indices
      if (map.containsKey(sum - target)) {
        int j = map.get(sum - target);
        ans = Math.max(ans, i - j);
      }

      // Store prefix sum only if not already present
      map.putIfAbsent(sum, i);
    }

    System.out.println(ans);
  }
}
