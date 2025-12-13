import java.util.ArrayList;
import java.util.List;

public class SetMatrixZeros {
  public static void main(String[] args) {
    int[][] matrix = {
        { 1, 1, 1 },
        { 1, 0, 1 },
        { 1, 1, 1 }
    };

    List<Integer> marker = new ArrayList<>();

    int rows = matrix.length;
    int cols = matrix[0].length;

    // collect zero positions as (row, col) pairs
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (matrix[i][j] == 0) {
          marker.add(i); // row
          marker.add(j); // column
        }
      }
    }

    // apply each stored pair
    for (int k = 0; k < marker.size(); k += 2) {
      int r = marker.get(k);
      int c = marker.get(k + 1);

      // zero the entire row r
      for (int j = 0; j < cols; j++) {
        matrix[r][j] = 0;
      }

      // zero the entire column c
      for (int i = 0; i < rows; i++) {
        matrix[i][c] = 0;
      }
    }

    // print matrix
    for (int[] row : matrix) {
      for (int v : row) System.out.print(v + " ");
      System.out.println();
    }
  }
}
