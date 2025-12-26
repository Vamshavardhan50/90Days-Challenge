// import java.util.Arrays;

// public class EquilibriumPoint {
// public static void main(String[] args) {
// int[] arr = { -7, 1, 5, 2, -4, 3, 0 };
// int n = arr.length;
// int[] prefix = new int[n]; //Better but space is very high
// int[] suffix = new int[n];

// prefix[0] = arr[0];
// suffix[n - 1] = arr[n - 1];

// for (int i = 1; i < n; i++) {
// prefix[i] = prefix[i - 1] + arr[i];
// suffix[n - 1 - i] = suffix[n - i] + arr[n - 1 - i];
// }

// System.out.println(Arrays.toString(prefix));
// System.out.println(Arrays.toString(suffix));

// for (int i = 0; i < n; i++) {
// if (prefix[i] == suffix[i]) {
// System.out.println("Equilibrium index: " + i);
// }
// }

// System.out.println("No equilibrium point found");
// }
// }

import java.util.Arrays;

public class EquilibriumPoint {
  public static void main(String[] args) {
    int[] arr = { -7, 1, 5, 2, -4, 3, 0 };
    int n = arr.length;

    int totalsum = Arrays.stream(arr).sum();

    int left=0;
    for(int i=0;i<n;i++){
      int right = totalsum-arr[i]-left;

      if(left==right){
        System.out.println(i);
      }
      left+=arr[i];
    }
  }
}
