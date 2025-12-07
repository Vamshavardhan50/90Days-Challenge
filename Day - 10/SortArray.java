import java.util.Arrays;

public class SortArray {
  public static void main(String[] args) {
    int count0 = 0, count1 = 0, count2 = 0;

    int[] arr = { 1, 0, 2, 1, 0 };

    // Count the occurrences of 0, 1, and 2
    for (int value : arr) {
      if (value == 0) {
        count0++;
      } else if (value == 1) {
        count1++;
      } else {
        count2++;
      }
    }

    // Refill array in sorted order using the counts
    int index = 0;

    while (count0-- > 0)
      arr[index++] = 0;
    while (count1-- > 0)
      arr[index++] = 1;
    while (count2-- > 0)
      arr[index++] = 2;

    System.out.println(Arrays.toString(arr));
  }
}
