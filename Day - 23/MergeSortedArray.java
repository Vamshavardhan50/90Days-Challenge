import java.util.Arrays;

public class MergeSortedArray {
  public static void main(String[] args) {

    int[] num1 = { 1, 2, 3, 1, 2, 3 };
    int[] num2 = { 2, 5, 6, 1, 2, 3 };

    // create a new array to hold both arrays
    int[] result = new int[num1.length + num2.length];

    int index = 0;

    // copy num1 into result
    for (int i = 0; i < num1.length; i++) {
      result[index++] = num1[i];
    }

    // copy num2 into result
    for (int i = 0; i < num2.length; i++) {
      result[index++] = num2[i];
    }
    Arrays.sort(result);
    System.out.println(Arrays.toString(result));
  }
}
