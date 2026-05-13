import java.util.*;

public class SumofSubarrayRanges {

  public static void main(String[] args) {

    int[] arr = { 1, 2, 3 };

    long total = subArrayRanges(arr);

    System.out.println(total);
  }

  public static long subArrayRanges(int[] arr) {

    long total = 0;

    for (int start = 0; start < arr.length; start++) {

      int min = arr[start];
      int max = arr[start];

      for (int end = start; end < arr.length; end++) {

        min = Math.min(min, arr[end]);
        max = Math.max(max, arr[end]);

        total += (max - min);
      }
    }

    return total;
  }
}