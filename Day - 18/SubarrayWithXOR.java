import java.util.Arrays;

public class SubarrayWithXOR {
  public static void main(String[] args) {
    int count = 0;
    int[] arr = { 4, 2, 2, 6, 4 };
    int n = arr.length;

    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        int[] subarray = Arrays.copyOfRange(arr, i, j + 1);
        int result = findXOR(subarray);
        if (result == 6) {
          count++;
        }
      }
    }
    System.out.println(count);
  }

  public static int findXOR(int[] arr) {
    int xor = arr[0];
    for (int i = 1; i < arr.length; i++) {
      xor ^= arr[i];
    }
    return xor;
  }
}
