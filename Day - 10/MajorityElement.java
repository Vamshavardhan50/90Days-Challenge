import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
  public static void main(String[] args) {
    int[] arr = { 7, 0, 0, 1, 7, 7, 2, 7, 7 };

    HashMap<Integer, Integer> map = new HashMap<>();

    for (int e : arr) {
      map.put(e, map.getOrDefault(e, 0) + 1);
    }
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      if (entry.getValue() > (arr.length / 2)) {
        System.out.println(entry.getKey());
      }
    }
    System.out.println(map);
  }
}
