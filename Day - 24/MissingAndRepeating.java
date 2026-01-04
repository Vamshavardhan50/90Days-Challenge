import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MissingAndRepeating {
  public static void main(String[] args) {

    int[] arr = { 4, 3, 6, 2, 1, 1 };
    int n = arr.length;

    long totalSum = (long) n * (n + 1) / 2;
    long sum = 0;

    HashMap<Integer, Integer> map = new HashMap<>();

    for (int num : arr) {
      sum += num;
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    int repeated = -1;
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      if (entry.getValue() > 1) {
        repeated = entry.getKey();
        break;
      }
    }

    int missing = (int) (totalSum - sum + repeated);

    ArrayList<Integer> result = new ArrayList<>();
    result.add(repeated);
    result.add(missing);

    System.out.println(result);
  }
}
