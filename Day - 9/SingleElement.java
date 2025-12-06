import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Gatherer.Integrator;

public class SingleElement {
  public static void main(String[] args) {
    int[] arr = { 4, 1, 2, 1, 2 };
    HashMap<Integer, Integer> map = new HashMap<>();

    for (int e : arr) {
      map.put(e, map.getOrDefault(e, 0) + 1);
    }

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      if (entry.getValue() == 1) {
        System.out.println(entry.getKey());
      }
    }
  }
}
