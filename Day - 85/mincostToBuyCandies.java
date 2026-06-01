import java.util.Arrays;

public class mincostToBuyCandies {
  public static void main(String[] args) {
    int[] arr = { 5, 5 };
    int total = 0;
    int count = 0;

    Arrays.sort(arr);

    for (int i = arr.length - 1; i >= 0; i--) {
      count++;
      if (count % 3 != 0) {
        total += arr[i];
      }
    }
    System.out.println(total);
  }
}