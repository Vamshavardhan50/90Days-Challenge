import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalsTriangle {
  public static void main(String[] args) {
    List<List<Integer>> result = new ArrayList<>();
    int numsRows = 5;

    if (numsRows >= 1)
      result.add(Arrays.asList(1));
    if (numsRows >= 2)
      result.add(Arrays.asList(1, 1));

    for (int i = 2; i < numsRows; i++) {
      List<Integer> prevRow = result.get(i - 1);
      List<Integer> currRow = new ArrayList<>();

      currRow.add(1); // first element

      for (int j = 1; j < prevRow.size(); j++) {
        currRow.add(prevRow.get(j - 1) + prevRow.get(j));
      }

      currRow.add(1); // last element

      result.add(currRow);
    }

    System.out.println(result);
  }
}
