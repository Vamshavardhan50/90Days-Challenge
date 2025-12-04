import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoveAllZerosToTheEnd {
  public static void main(String[] args) {
    int[] arr = { 1, 0, 2, 3, 0, 4, 0, 1 };
    List<Integer> ans = new ArrayList<>();

    int n = arr.length;
    int j = arr.length - 1;

    for (int i = 0; i < n; i++) {
      if (arr[i] > 0) {
        ans.add(arr[i]);
      }
    }

    for (int i = ans.size(); i < n; i++) {
      ans.add(0);
    }
    System.out.println(ans);
  }
}
