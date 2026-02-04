// Brute force Approach  
// import java.util.Arrays;
// import java.util.Scanner;

// public class capacity_to_ship_packages_within_d_days {
//   public static void main(String[] args) {
//     Scanner sc = new Scanner(System.in);
//     System.out.print("Enter the size of the array : ");
//     int n = sc.nextInt();

//     System.out.println("Enter the Array :");
//     int[] arr = new int[n];
//     for (int i = 0; i < n; i++) {
//       arr[i] = sc.nextInt();
//     }
//     System.out.print("Enter the days :");
//     int days = sc.nextInt();

//     int max_arr = Arrays.stream(arr).max().getAsInt();
//     int sum = Arrays.stream(arr).sum();

//     int answer = 0;

//     for (int i = max_arr; i <= sum; i++) {

//       int currentLoad = 0;
//       int usedDays = 1; // start from day 1

//       for (int j = 0; j < arr.length; j++) {
//         if (currentLoad + arr[j] > i) {
//           usedDays++; // move to next day
//           currentLoad = arr[j]; // start new load
//         } else {
//           currentLoad += arr[j];
//         }
//       }
//       if (usedDays <= days) {
//         answer = i;
//         break; // smallest found, stop
//       }

//     }
//     System.out.println("Minimum capacity required: " + answer);

//   }
// }

import java.util.Arrays;
import java.util.Scanner;

public class capacity_to_ship_packages_within_d_days {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the size of the array : ");
    int n = sc.nextInt();

    System.out.println("Enter the Array :");
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }
    System.out.print("Enter the days :");

    int days = sc.nextInt();
    
    int max_arr = Arrays.stream(arr).max().getAsInt();
    int sum = Arrays.stream(arr).sum();
    int answer = sum;
    int low = max_arr;
    int high = sum;

    while (low <= high) {
      int currentLoad = 0;
      int usedDays = 1; // start from day 1
      int mid = low + (high - low) / 2;

      for (int j = 0; j < arr.length; j++) {
        if (currentLoad + arr[j] > mid) {
          usedDays++; // move to next day
          currentLoad = arr[j]; // start new load
        } else {
          currentLoad += arr[j];
        }
      }
      if (usedDays <= days) {
        answer = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }

    }
    System.out.println("Minimum capacity required: " + answer);

  }
}
