import java.util.PriorityQueue;

public class KWeakestRowsinaMatrix {
  public static void main(String[] args) {

    int[][] mat = {
        { 1, 1, 0, 0, 0 },
        { 1, 1, 1, 1, 0 },
        { 1, 0, 0, 0, 0 },
        { 1, 1, 0, 0, 0 },
        { 1, 1, 1, 1, 1 }
    };

    int k = 3;

    PriorityQueue<int[]> pq = new PriorityQueue<>(
        (a, b) -> {
          if (a[0] == b[0]) {
            return a[1] - b[1];
          }
          return a[0] - b[0];
        });

    for (int i = 0; i < mat.length; i++) {
      int soldiers = 0;

      for (int j = 0; j < mat[0].length; j++) {
        if (mat[i][j] == 1) {
          soldiers++;
        }
      }

      pq.offer(new int[] { soldiers, i });
    }

    for (int i = 0; i < k; i++) {
      int[] row = pq.poll();
      System.out.println(row[1]);
    }
  }
}