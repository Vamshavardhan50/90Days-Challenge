import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RotateArraybyKelements {
  public static void main(String[] args) {
    int k = 2;
    int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
    int n = arr.length;

    List<Integer> temp = new ArrayList<>();

    // step - 1 :Storing the values in temp
    for (int i = n - k; i < n; i++) {
      temp.add(arr[i]);
    }

    // step :2 swifting the values
    for (int i = n - k - 1; i >= 0; i--) {
      arr[i + k] = arr[i];
    }

    for (int i = 0; i < k; i++) {
      arr[i] = temp.get(i);
    }
    System.out.println(Arrays.toString(arr));

  }
}
