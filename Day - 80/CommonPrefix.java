import java.util.HashSet;

public class CommonPrefix {
  public static void main(String[] args) {
    int[] arr1 = { 1, 10, 100 };
    int[] arr2 = { 1000 };

    HashSet<Integer> set = new HashSet<>();

    // making arr1 prefixes
    for (int num : arr1) {
      while (num > 0) {
        set.add(num);
        num = num / 10;
      }
    }
    System.out.println(set);

    // making arr2 prefix and checking maxlength
    int maxlength = 0;
    for (int num : arr2) {

      while (num > 0) {

        if (set.contains(num)) {
          maxlength = Math.max(maxlength,
              String.valueOf(num).length());
          break;
        }

        num /= 10;
      }
    }

    System.out.println(maxlength);

  }
}
