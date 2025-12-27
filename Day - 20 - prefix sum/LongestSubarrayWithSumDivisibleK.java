import java.util.HashMap;

public class LongestSubarrayWithSumDivisibleK {
  public static void main(String[] args) {
    int[] arr = { 2, 7, 6, 1, 4, 5 };
    int k = 3;

    int sum = 0;
    int maxLen = 0;

    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(0, -1); // remainder 0 occurs before array starts

    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];

      int rem = ((sum % k) + k) % k;

      if (map.containsKey(rem)) {
        maxLen = Math.max(maxLen, i - map.get(rem));
      } else {
        map.put(rem, i);
      }
    }

    System.out.println(maxLen);
  }
}
