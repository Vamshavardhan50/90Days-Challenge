import java.util.ArrayList;
import java.util.List;

public class CombinationalSumOne {
  public static void main(String[] args) {
    int[] arr = { 2, 3, 6, 7 };
    int target = 7;
    combination(arr, target, 0, new ArrayList<>());
  }

  public static void combination(int[] arr, int target, int index, List<Integer> ans) {
    if (index == arr.length) {
      if (target == 0) {
        System.out.println(ans);
      }
      return;
    }

    if (arr[index] <= target) {
      ans.add(arr[index]);
      combination(arr, target - arr[index], index, ans);
      ans.remove(ans.size() - 1);
    }
    combination(arr, target, index + 1, ans);
  }
}
