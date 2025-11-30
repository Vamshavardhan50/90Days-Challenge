import java.util.Arrays;

public class BubbleSort {
  public static void main(String[] args) {
    int[] arr = { 64, 34, 25, 12, 22 };

    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[i] > arr[j]) {
          int temp = arr[j];
          arr[j] = arr[i];
          arr[i] = temp;
        }
      }
    }
    System.out.println(Arrays.toString(arr));
  }

}
