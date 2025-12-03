import java.util.Arrays;

public class DupFromSortedArray {
  public static void main(String[] args) {
    int[] arr = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
    int j = 0;
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] != arr[j]) {
        arr[j + 1] = arr[i];
        j++;
      }
    }

    System.out.println(Arrays.toString(arr));
  }
}
