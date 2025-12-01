import java.util.Arrays;

public class InsertionSort {
  public static void main(String[] args) {
    int[] arr = { 64, 25, 12, 22, 11 };
    int key, j;
    int n = arr.length;
    for (int i = 1; i < n; i++) {
      key = i;
      j = i - 1;

      while (j >= 0 && arr[j] > key) {
        arr[j + 1] = arr[j]; // Shift right
        j--;
      }
      arr[j + 1] = key;
    }
    System.out.println(Arrays.toString(arr));
  }
}
