// This is the Brute force
// public class Search2DMatrix {
// public static void main(String[] args) {
// int[][] arr = { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 60 } };

// int row = arr.length;
// int col = arr[0].length;
// int target = 3;

// for (int i = 0; i < row; i++) {
// for (int j = 0; j < col; j++) {
// if (arr[i][j] == target) {
// System.out.println(true);
// break;
// }
// }
// }
// System.out.println(false);
// }
// }

public class Search2DMatrix {
  public static void main(String[] args) {
    int arr[][] = {
        { 1, 3, 5, 7 },
        { 10, 11, 16, 20 },
        { 23, 30, 34, 60 }
    };

    int target = 300;
    int rows = arr.length;
    int cols = arr[0].length;

    System.out.println(search(arr, rows, cols, target));
  }

  public static boolean search(int[][] arr, int rows, int cols, int target) {
    for (int i = 0; i < rows; i++) {
      int low = 0, high = cols - 1;
      while (low <= high) {
        int mid = low + (high - low) / 2;

        if (arr[i][mid] == target) {
          return true;
        } else if (arr[i][mid] > target) {
          high = mid - 1;
        } else {
          low = mid + 1;
        }
      }
    }
    return false;
  }
}
