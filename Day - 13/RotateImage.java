import java.util.ArrayList;
import java.util.List;

public class RotateImage {
  public static void main(String[] args) {
    int[][] matrix = { { 5, 1, 9, 11 }, { 2, 4, 8, 10 }, { 13, 3, 6, 7 }, { 15, 14, 12, 16 } };

    List<List<Integer>> result = new ArrayList<>();

    int n = matrix.length;
    for (int i = 0; i < n; i++) {
      List<Integer> temp = new ArrayList<>();
      for (int j = n - 1; j >= 0; j--) {
        temp.add(matrix[j][i]);
      }
      result.add(temp);
    }
    System.out.println(result);
  }

}
