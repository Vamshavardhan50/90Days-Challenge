import java.util.Scanner;

public class cf1789A {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int t = sc.nextInt();

    while (t-- > 0) {
      int n = sc.nextInt();
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = sc.nextInt();
      }

      boolean possible = false;

      // Label the outer loop so we can break out of both loops instantly
      outerLoop: for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
          if (gcd(arr[i], arr[j]) <= 2) {
            possible = true;
            break outerLoop; // Stop searching immediately
          }
        }
      }
      
      // Print the result exactly once per test case
      if (possible) {
        System.out.println("Yes");
      } else {
        System.out.println("No");
      }
    }
    sc.close();
  }

  public static int gcd(int a, int b) {
    while (b != 0) {
      int temp = b;
      b = a % b;
      a = temp;
    }
    return a;
  }
}
