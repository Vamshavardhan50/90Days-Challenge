import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class ArrayZerobySubEqualamt {
  public static void main(String[] args) {
    int[] nums = { 1, 5, 0, 3, 5 };

    Set<Integer> set = new HashSet<>();

    for (int n : nums) {
      if (n > 0) {
        set.add(n);
      }
    }
    System.out.println(set.size());
  }
}
