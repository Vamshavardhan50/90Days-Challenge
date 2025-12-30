import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
  public static void main(String[] args) {
    int[][] arr = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };

    Arrays.sort(arr, (a, b) -> {
      if (a[0] == b[0])
        return Integer.compare(a[1], b[1]);
      return Integer.compare(a[0], b[0]);
    });
    System.out.println(Arrays.deepToString(arr));

    int start = arr[0][0];
    int end = arr[0][1];
    List<List<Integer>> temp = new ArrayList<>();

    for (int i = 1; i < arr.length; i++) {
      if (arr[i][0] <= end) {
        end = Math.max(end, arr[i][1]);
      } else {
        temp.add(Arrays.asList(start, end));
        start = arr[i][0];
        end = arr[i][1];
      }
    }
    temp.add(Arrays.asList(start, end));
    System.out.println(temp);
  }
}
