//Brute force approach
// import java.util.Arrays;
// import java.util.Scanner;

// public class FindSmallestDivisorThreshold {
//   public static void main(String[] args) {
//     Scanner sc = new Scanner(System.in);
//     System.out.print("Enter the size of the array : ");
//     int n = sc.nextInt();

//     System.out.println("Enter the Array :");
//     int[] arr = new int[n];
//     for (int i = 0; i < n; i++) {
//       arr[i] = sc.nextInt();
//     }
//     System.out.print("Enter the limit :");w
//     int limit = sc.nextInt();

//     int result = 0;
//     //get the max element from the arr for the divisor 
//     int max_arr = Arrays.stream(arr).max().getAsInt();
//     for (int i = 1; i <= max_arr; i++) { // iterate over the max value and it is knowns as divisor also 
//       int sum = 0;
//       for (int j = 0; j < n; j++) {
//         sum += (int) Math.ceil((double) arr[j] / i); //calculate the (ele / i(divisor)) and add it to the sum 
//       }
//       if (sum <= limit) { //checking the sum <= limit if it is the its a answer
//         result = i;
//         break;
//       }
//     }
//     System.out.println(result);
//   }
// }
import java.util.Arrays;
import java.util.Scanner;

public class FindSmallestDivisorThreshold {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the size of the array : ");
    int n = sc.nextInt();

    System.out.println("Enter the Array :");
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }
    System.out.print("Enter the limit :");
    int limit = sc.nextInt();

    int low = 1, high = Arrays.stream(arr).max().getAsInt();

    while (low <= high) {
      int mid = low + (high - low) / 2;
      int sum = 0;

      for (int j = 0; j < n; j++) {
        sum += (int) Math.ceil((double) arr[j] / mid);
      }

      if (sum <= limit) {
        System.out.println(mid);
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
  }
}
