import java.util.Arrays;

public class PeakEleIn2D {
  public static void main(String[] args) {
    int[][] arr = {
        { 10, 20, 15 },
        { 21, 30, 14 },
        { 7, 16, 32 }
    };

    int rows = arr.length, cols = arr[0].length;

    int low = 0, high = cols - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;

      int maxRowIndex = findMaxRowIndex(arr, rows, cols, mid);

      int left = mid - 1 >= 0 ? arr[maxRowIndex][mid - 1] : -1;
      int right = mid + 1 < cols ? arr[maxRowIndex][mid + 1] : -1;

      if (arr[maxRowIndex][mid] > left && arr[maxRowIndex][mid] > right) {
        System.out.println("row : " + maxRowIndex);
        System.out.println("col : " + mid);
        break;
      } else if (left > arr[maxRowIndex][mid]) {
        high = mid - 1; // move left
      } else {
        low = mid + 1; // move right
      }
    }

  }

  public static int findMaxRowIndex(int[][] arr, int rows, int cols, int mid) {
    int maxValue = -1;
    int index = -1;
    for (int i = 0; i < rows; i++) {
      if (arr[i][mid] > maxValue) {
        maxValue = arr[i][mid];
        index = i;
      }
    }
    return index;
  }
}
