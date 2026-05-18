import java.util.ArrayList;
import java.util.List;

public class MaxCardPoints {

  public static void main(String[] args) {

    int[] arr = { 1, 2, 3, 4, 5, 6, 1 };
    int k = 3;

    System.out.println(
        generateSequence(arr, 0, 0, k, new ArrayList<>(), 0));
  }

  public static int generateSequence(int[] arr, int count, int index, int k, List<Integer> ans, int max) {

    // If k elements selected
    if (count == k) {

      int sum = 0;

      for (int num : ans) {
        sum += num;
      }

      return Math.max(max, sum);
    }

    // Out of bounds
    if (index == arr.length) {
      return max;
    }

    // Include current element
    ans.add(arr[index]);
    max = generateSequence(arr, count + 1, index + 1, k, ans, max);
    // Backtrack
    ans.remove(ans.size() - 1);
    // Exclude current element
    max = generateSequence(arr, count, index + 1, k, ans, max);

    return max;
  }
}