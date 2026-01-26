// public class NthRoot {
//   public static void main(String[] args) {
//     int n = 3, m = 27;
//     long power;

//     for (int i = 1; i <= m; i++) {
//       power = (long) Math.pow(i, n);
//       if (power == m) {
//         System.out.println(i);
//         break;
//       }
//       if (power > m) {
//         break;
//       }
//     }
//     System.out.println(-1);
//   }
// }

public class NthRoot {
  public static void main(String[] args) {
    int n = 3, m = 27;
    int low = 0, high = m;
    long power;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      power = (long) Math.pow(mid, n);

      if (power == m) {
        System.out.println(mid);
        break;
      }
      if (power > m) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
  }
}