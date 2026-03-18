import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RightMaximum {
  public static void main(String[] args) {

    List<Integer> arr = new ArrayList<>(Arrays.asList(2, 1, 2, 3, 1));

    int max = 0;
    int maxIndex = -1;
    int operations = 0;

    while (arr.size() != 0) {

      max = Integer.MIN_VALUE;
      maxIndex = -1;

      for (int i = 0; i < arr.size(); i++) {
        if (arr.get(i) > max && i >= maxIndex) {
          max = arr.get(i);
          maxIndex = i;
        }
      }

      // remove elements from maxIndex to end
      for (int i = arr.size() - 1; i >= maxIndex; i--) {
        arr.remove(i);
      }

      operations++;
    }

    System.out.println("MaxIndex : " + maxIndex);
    System.out.println("Max : " + max);
    System.out.println("Operations : " + operations);
  }
}