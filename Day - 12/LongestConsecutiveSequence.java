import java.util.Arrays;

public class LongestConsecutiveSequence {
  public static void main(String[] args) {
    int[] arr = { 0, 3, 7, 2, 5, 8, 4, 6, 0, 1 };
    Arrays.sort(arr);

    int maxi = 1;
    int count = 1;

    for (int i = 0; i < arr.length - 1; i++) {

      if (arr[i] == arr[i + 1]) {
        continue; 
      }

      if (arr[i] + 1 == arr[i + 1]) {
        count++;
      } else {
        maxi = Math.max(maxi, count);
        count = 1;
      }
    }

    maxi = Math.max(maxi, count);

    System.out.println(Arrays.toString(arr));
    System.out.println(maxi);
  }
}
