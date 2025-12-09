import java.util.Arrays;

import javax.management.RuntimeMBeanException;

public class RearrangeArrayBySign {
  public static void main(String[] args) {

    int[] arr = { -1, 1 };
    int[] ans = new int[arr.length];
    int EvenIndex = 0, OddIndex = 1;

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > 0) {
        ans[EvenIndex] = arr[i];
        EvenIndex += 2;
      } else {
        ans[OddIndex] = arr[i];
        OddIndex += 2;
      }
    }
    System.out.println(Runtime.getRuntime());
    System.out.println(Arrays.toString(ans));
  }

}
