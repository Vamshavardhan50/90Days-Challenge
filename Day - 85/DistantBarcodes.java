import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DistantBarcodes {
  static class Pair {
    int key;
    int count;

    Pair(int key, int count) {
      this.key = key;
      this.count = count;
    }
  }

  public static void main(String[] args) {
    int[] barcodes = { 1, 1, 1, 1, 2, 2, 3, 3 };

    Map<Integer, Integer> map = new HashMap<>();
    PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
      if (a.count != b.count) {
        return b.count - a.count;
      }
      return b.key - a.key;
    });

    for (int n : barcodes) {
      map.put(n, map.getOrDefault(n, 0) + 1);
    }

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      pq.offer(new Pair(entry.getKey(), entry.getValue()));
    }

    int[] result = new int[barcodes.length];
    int idx = 0;
    while (pq.size() >= 2) {
      Pair first = pq.poll();
      Pair second = pq.poll();

      result[idx++] = first.key;
      result[idx++] = second.key;

      first.count--;
      second.count--;

      if (first.count > 0) {
        pq.offer(first);
      }
      if (second.count > 0) {
        pq.offer(second);
      }
    }

    if (!pq.isEmpty()) {
      result[idx] = pq.poll().key;
    }
    System.out.println(Arrays.toString(result));
  }
}
