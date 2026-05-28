import java.util.*;

public class TaskSchedulers {
  public static void main(String[] args) {

    String[] tasks = { "A", "A", "A", "B", "B", "B" };
    int n = 2;

    HashMap<String, Integer> map = new HashMap<>();

    for (String task : tasks) {
      map.put(task, map.getOrDefault(task, 0) + 1);
    }

    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

    for (int freq : map.values()) {
      pq.offer(freq);
    }

    int time = 0;

    while (!pq.isEmpty()) {

      List<Integer> temp = new ArrayList<>();

      int cycle = n + 1;

      while (cycle > 0 && !pq.isEmpty()) {

        int freq = pq.poll();
        freq--;

        if (freq > 0) {
          temp.add(freq);
        }

        time++;
        cycle--;
      }

      for (int freq : temp) {
        pq.offer(freq);
      }

      if (pq.isEmpty()) {
        break;
      }

      time += cycle; // idle slots
    }

    System.out.println(time);
  }
}