// public class SqrtBinary {
//   public static void main(String[] args) {
//     int ans = 0;
//     int n = 28;

//     for (int i = 0; i < n; i++) {
//       if (i * i <= n) {
//         ans = i;
//       } else {
//         break;
//       }
//     }
//     System.out.println(ans);
//   }
// }


//optimal approach using binary searchv
class Solution {
  // This function returns the floor value of the square root of a number
  public int mySqrt(int x) {
    // Handle small numbers directly
    if (x < 2)
      return x;

    // Initialize binary search range
    int left = 1, right = x / 2, ans = 0;

    // Perform binary search
    while (left <= right) {
      // Find middle point
      long mid = left + (right - left) / 2;

      // Check if mid*mid is less than or equal to x
      if (mid * mid <= x) {
        // Store mid as potential answer
        ans = (int) mid;
        // Move to right half
        left = (int) mid + 1;
      } else {
        // Move to left half
        right = (int) mid - 1;
      }
    }

    // Return final answer
    return ans;
  }
}

public class SqrtBinary {
  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(s.mySqrt(8));
  }
}
