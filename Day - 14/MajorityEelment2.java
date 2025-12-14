import java.util.HashMap;
import java.util.Map;

public class MajorityEelment2 {
  public static void main(String[] args) {
    Map<Integer, Integer> map = new HashMap<>();
    int[] nums = { 3, 2, 3 };
    int threshold = (nums.length) / 3;

    for (int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    System.out.println(map);
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      if (entry.getValue() > threshold) {
        System.out.println("The Majority Value is : " + entry.getKey());
        break;
      }
    }

  }
}
