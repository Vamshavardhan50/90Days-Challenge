public class MatrixDiagonalSum {
  public static void main(String[] args) {
    int[][] mat = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

    int row = mat.length;
    int col = mat[0].length;

    int primary = 0;

    for (int i = 0; i < row; i++) {
      primary += mat[i][i];
    }
    System.out.println("Primary :" + primary);
    int secondary = 0;
    for (int i = 0; i < row; i++) {
      secondary += mat[i][col - 1 - i];
    }
    System.out.println("Secondary :" + secondary);

    if (row % 2 == 0) {
      System.out.println(primary + secondary);
    } else {
      int middle = row / 2;
      System.out.println(primary + secondary - mat[middle][middle]);
    }
  }
}
