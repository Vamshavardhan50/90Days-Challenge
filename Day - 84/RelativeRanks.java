import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import javax.swing.event.InternalFrameAdapter;

public class RelativeRanks {
  public static void main(String[] args) {
    int[] arr = { 10, 3, 8, 9, 4 };

    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

    for (int i = 0; i < arr.length; i++) {
      pq.offer(arr[i]);
    }

    HashMap<Integer, String> map = new HashMap<>();

    int i = 1;
    while (!pq.isEmpty()) {
      if (i == 1) {
        map.put(pq.poll(), "Gold Medal");
      } else if (i == 2) {
        map.put(pq.poll(), "Silver Medal");
      } else if (i == 3) {
        map.put(pq.poll(), "Bronze Medal");
      } else {
        map.put(pq.poll(), String.valueOf(i));
      }
      i++;
    }

    System.out.println(map);
    String[] res = new String[arr.length];

    for (int j = 0; j < arr.length; j++) {
      res[j] = map.get(arr[j]);
    }
    System.out.println(Arrays.toString(res));
  }
}