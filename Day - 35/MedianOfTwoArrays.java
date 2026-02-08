
// Brute force approach
import java.util.Arrays;

public class MedianOfTwoArrays {
  public static void main(String[] args) {
    int[] arr1 = { 1, 2 };
    int[] arr2 = { 3, 4 };

    int[] result = new int[arr1.length + arr2.length];
    int index = 0;
    for (int i = 0; i < arr1.length; i++) {
      result[index++] = arr1[i];
    }

    // copy num2 into result
    for (int i = 0; i < arr2.length; i++) {
      result[index++] = arr2[i];
    }
    Arrays.sort(result);
    int n = result.length;
    if (n % 2 != 0)
      System.out.println(result[n / 2]);

    System.out.println((result[(n - 1) / 2] + result[n / 2]) / 2.0);

  }

}
