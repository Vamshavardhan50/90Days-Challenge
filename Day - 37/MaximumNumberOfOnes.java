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
