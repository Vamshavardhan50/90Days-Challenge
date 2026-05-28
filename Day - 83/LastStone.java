import java.util.Collections;
import java.util.PriorityQueue;

public class LastStone {
  public static void main(String[] args) {
    int[] stones = { 1 };

    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

    for (int i = 0; i < stones.length; i++) {
      pq.add(stones[i]);
    }
    System.out.println(pq);

    while (pq.size() > 1) {
      int first = pq.poll();
      int second = pq.poll();

      if (first != second) {
        pq.add(first - second);
      }
    }

    System.out.println(pq.isEmpty() ? 0 : pq.poll());

  }
}