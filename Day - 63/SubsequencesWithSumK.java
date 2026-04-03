import java.util.ArrayList;
import java.util.List;

public class SubsequencesWithSumK {
  public static void main(String args[]) {
    int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    int target = 7;
    generateSequence(arr, 0, 0, target, new ArrayList<>());
  }

  public static void generateSequence(int[] arr, int sum, int index, int target, List<Integer> ans) {
    if (index == arr.length) {
      if (sum == target && ans.size() == 3) {
        System.out.println(ans);
      }
      return;
    }
    generateSequence(arr, sum, index + 1, target, ans);
    ans.add(arr[index]);
    generateSequence(arr, sum + arr[index], index + 1, target, ans);
    ans.remove(ans.size() - 1);
  }
}