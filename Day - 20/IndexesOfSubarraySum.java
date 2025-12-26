import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class IndexesOfSubarraySum {
  public static void main(String[] args) {
    int[] arr = { 1, 2, 3, 7, 5 };
    int target = 6;

    HashMap<Integer, Integer> map = new HashMap<>();
    List<Integer> result = new ArrayList<>();

    int sum = 0;
    map.put(0, -1); // prefix sum 0 before array starts

    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];

      if (map.containsKey(sum - target)) {
        int start = map.get(sum - target) + 1;
        int end = i;
        result.add(start);
        result.add(end);
        break; // remove if you want all subarrays
      }

      // store prefix sum only if not already present
      map.putIfAbsent(sum, i);
    }

    System.out.println(result);
  }
}
