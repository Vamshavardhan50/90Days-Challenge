import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;

public class DeleteGreatestValueinEachRow {
  public static void main(String[] args) {
    int[][] arr = { { 1, 2, 4 }, { 3, 3, 1 } };

    for (int i = 0; i < arr.length; i++) {
      PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
      for (int j = 0; j < arr[0].length; j++) {
        pq.add(arr[i][j]);
      }

    }
  }

}