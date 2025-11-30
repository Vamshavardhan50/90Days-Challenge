import java.util.*;

public class ReverseArray {
  public static List<Integer> reverseArray(int[] arr, int length, List<Integer> temp) {
    if (length == 0) {
      System.out.println(temp);
      return temp;
    }
    temp.add(arr[length - 1]);
    reverseArray(arr, length - 1, temp);
  }

  public static void main(String[] args) {
    int[] arr = { 1, 2, 3, 4, 5 };
    List<Integer> ans = reverseArray(arr, arr.length, new ArrayList<Integer>());
    System.out.println(ans);
  }
}