import java.util.Arrays;

public class AveragePrefix {
  public static void main(String[] args) {
    int[] arr = { 10, 20, 30, 40, 50 };
    int n = arr.length;

    int[] average = new int[n];
    average[0] = arr[0];
    int sum = arr[0];

    for (int i = 1; i < n; i++) {
      sum += arr[i];
      average[i] = (sum) / (i + 1);
    }
    System.out.println(Arrays.toString(average));
  }
}
