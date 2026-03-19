import java.util.*;

public class DistinctNumber {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    long n = sc.nextInt();

    long[] arr = new long[(int) n];

    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextLong();
    }

    Arrays.sort(arr);

    long count = 1; // at least one element

    for (int i = 1; i < n; i++) {
      if (arr[i] != arr[i - 1]) {
        count++;
      }
    }

    System.out.println(count);
  }
}