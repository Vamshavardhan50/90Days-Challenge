import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Gatherer.Integrator;

public class MergeIntervals {
  public static void main(String[] args) {
    int[][] arr = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };

    // Arrays.sort(arr);
    Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
    List<List<Integer>> result = new ArrayList<>();

    // for (int[] row : arr) {
    // System.out.print(Arrays.toString(row) + ",");
    // }
    for (int i = 1; i < arr.length; i++) {
      List<Integer> temp = new ArrayList<>();
      if (arr[i][0] <= arr[i - 1][1]) {
        temp.add(arr[i - 1][0]);
        temp.add(Math.max(arr[i - 1][1], arr[i][1]));
        result.add(temp);
      }
      result.add(arr[i]);
    }
    System.out.println(result);
  }
}
