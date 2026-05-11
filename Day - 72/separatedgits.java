import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class separatedgits {
  public static void main(String args[]) {
    int[] arr = { 13, 25, 83, 77 };
    System.out.println(Arrays.toString(SeparateDigits(arr)));
  }

  public static int[] SeparateDigits(int[] arr) {
    List<Integer> list = new ArrayList<>();
    for (int num : arr) {
      Stack<Integer> st = new Stack<>();

      while (num > 0) {
        st.push(num % 10);
        num = num / 10;
      }

      while (!st.isEmpty()) {
        list.add(st.pop());
      }
    }
    int[] result = new int[list.size()];
    for (int i = 0; i < list.size(); i++) {
      result[i] = list.get(i);
    }
    return result;
  }
}