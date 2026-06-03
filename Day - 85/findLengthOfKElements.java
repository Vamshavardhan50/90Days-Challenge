import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;

public class findLengthOfKElements {
  public static void main(String[] args) {
    int[] arr = { 2, 1, 3, 3 };
    int k = 2;

    int[] ans = new int[k];

    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

    for (int i = 0; i < arr.length; i++) {
      pq.add(arr[i]);
    }

    for (int i = k - 1; i >= 0; i--) {
      ans[i] = pq.poll();
    }

    System.out.println(Arrays.toString(ans));
  }
}