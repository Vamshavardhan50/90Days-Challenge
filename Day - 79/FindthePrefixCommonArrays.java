// import java.util.Arrays;
// import java.util.HashSet;
// import java.util.Set;

// public class FindthePrefixCommonArrays {
//   public static void main(String[] args) {
//     int[] a = { 1, 3, 2, 4 };
//     int[] b = { 3, 1, 2, 4 };

//     int[] c = new int[a.length];
//     for (int i = 0; i < a.length; i++) {
//       Set<Integer> set1 = new HashSet<>();
//       Set<Integer> set2 = new HashSet<>();
//       for (int j = 0; j <= i; j++) {
//         set1.add(a[j]);
//         set2.add(b[j]);
//       }
//       Set<Integer> intersection = new HashSet<>(set1);
//       intersection.retainAll(set2);

//       c[i] = intersection.size();
//     }
//     System.out.println(Arrays.toString(c));

//   }
// }

import java.util.*;

class Solution {
  public int[] findThePrefixCommonArray(int[] A, int[] B) {
    int n = A.length;
    HashSet<Integer> set1 = new HashSet<>();
    HashSet<Integer> set2 = new HashSet<>();
    int[] res = new int[n];

    int count = 0;

    for (int i = 0; i < n; i++) {
      if (set2.contains(A[i])) {
        count++;
      }

      if (set1.contains(B[i])) {
        count++;
      }

      if (A[i] == B[i]) {
        count++;
      }

      set1.add(A[i]);
      set2.add(B[i]);

      res[i] = count;
    }

    return res;
  }
}