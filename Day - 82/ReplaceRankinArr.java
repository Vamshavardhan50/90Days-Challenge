import java.util.*;

public class ReplaceRankinArr {
  public static void main(String[] args) {

    int[] arr = { 20, 15, 26, 2, 98, 6 };

    PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getKey));

    // pair = (value, original index)
    for (int i = 0; i < arr.length; i++) {
      pq.add(Map.entry(arr[i], i));
    }

    System.out.println(pq);
    int[] res = new int[arr.length];

    int rank = 1;
    int prev = -1;

    while (!pq.isEmpty()) {

      Map.Entry<Integer, Integer> p = pq.poll();

      int value = p.getKey();
      int index = p.getValue();

      if (value != prev) {
        prev = value;
        res[index] = rank;
        rank++;
      } else {
        res[index] = rank - 1;
      }
    }

    System.out.println(Arrays.toString(res));
  }
}