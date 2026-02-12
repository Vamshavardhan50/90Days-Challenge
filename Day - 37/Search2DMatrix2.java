
public class Search2DMatrix2 {
  public static void main(String[] args) {
    int[][] arr = { { 1, 4, 7, 11, 15 }, { 2, 5, 8, 12, 19 }, { 3, 6, 9, 16, 22 }, { 10, 13, 14, 17, 24 },
        { 18, 21, 23, 26, 30 } };

    int row = arr.length;
    int col = arr[0].length;
    int target = 3; 

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (arr[i][j] == target) {
          System.out.println(true);
          break;
        }
      }
    }
    System.out.println(false);
  }
}