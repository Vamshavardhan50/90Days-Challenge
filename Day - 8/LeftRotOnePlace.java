import java.util.Arrays;

public class LeftRotOnePlace {
  public static void main(String[] args) {
    int[] arr = { 1, 2, 3, 4, 5 };

    // step-1 store first variable in temp;
    int temp = arr[0];

    // step -2 swift the elements to left side;

    for (int i = 1; i < arr.length; i++) {
      arr[i - 1] = arr[i];
    }

    // step - 3 assign the temp variable to last

    arr[arr.length - 1] = temp;

    System.out.println(Arrays.toString(arr));
  }
}