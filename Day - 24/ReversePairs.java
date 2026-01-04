import java.util.ArrayList;
import java.util.List;

public class ReversePairs {
  public static void main(String[] args) {
    int[] arr = { [2147483647,2147483647,2147483647,2147483647,2147483647,2147483647]};
    int count = 0;
    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[i] > arr[j]) {
          count++;
        }
      }
    }
    System.out.println(count);
  }
}
