import java.util.HashMap;
import java.util.Map;

public class SubarrayEqualOne {

  public static int countSubarrays(int[] arr) {
    Map<Integer, Integer> freq = new HashMap<>();
    freq.put(0, 1); // prefix sum 0 occurs once

    int prefixSum = 0;
    int count = 0;

    for (int x : arr) {
      if (x == 0) {
        prefixSum += -1;
      } else { // x == 1
        prefixSum += 1;
      }

      count += freq.getOrDefault(prefixSum, 0);
      freq.put(prefixSum, freq.getOrDefault(prefixSum, 0) + 1);
    }

    return count;
  }

  public static void main(String[] args) {
    int[] arr = { 1, 0, 0, 1, 0, 1, 1 };
    System.out.println(countSubarrays(arr)); // Output: 7
  }
}
