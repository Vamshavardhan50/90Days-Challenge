// This is the Brute force approach for this one
// import java.util.ArrayList;
// import java.util.List;

// public class MaximumNumberOfOnes {
// public static void main(String[] args) {
// int n = 3, m = 3;
// int[][] mat = { { 1, 1, 1 }, { 0, 0, 1 }, { 0, 0, 0 } };

// int row = mat.length;
// int col = mat[0].length;
// int maxCount = 0;
// int maxRowIndex = 0;

// List<Integer> index = new ArrayList<>();
// for (int i = 0; i < row; i++) {
// int numberofone = 0;
// for (int j = 0; j < col; j++) {
// if (mat[i][j] == 1) {
// numberofone++;
// }
// if (numberofone > maxCount) {
// maxCount = numberofone;
// maxRowIndex = i;
// }
// }
// index.add(numberofone);
// }
// System.out.println(maxRowIndex);
// }
// }

//The Optimal Approach is simple 
// lets think that each row in the matrix/grid is the array every array we can apply BS and find the lowerbound

// thought of optimal:
// for loop  -- > it act as the row to move 
//   BS --> it will search number of ones in a row


public class MaximumNumberOfOnes {
  public static void main(String[] args) {
    int rows = 4, cols = 4;
    int[][] arr = {
        { 0, 1, 1, 1 },
        { 0, 0, 1, 1 },
        { 1, 1, 1, 1 },
        { 0, 0, 0, 0 }
    };

    int maxOnes = 0;
    int rowIndex = -1;

    for (int i = 0; i < rows; i++) {
      int low = 0, high = cols - 1;
      int firstOneIndex = -1;

      // Binary search to find first occurrence of 1
      while (low <= high) {
        int mid = low + (high - low) / 2;

        if (arr[i][mid] == 1) {
          firstOneIndex = mid;
          high = mid - 1; // move left
        } else {
          low = mid + 1; // move right
        }
      }

      if (firstOneIndex != -1) {
        int onesCount = cols - firstOneIndex;

        if (onesCount > maxOnes) {
          maxOnes = onesCount;
          rowIndex = i;
        }
      }
    }

    System.out.println("Row with maximum 1s: " + rowIndex);
  }
}
