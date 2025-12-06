public class ConsecutiveOnes {
  public static void main(String[] args) {
    int[] arr = { 1, 0, 1, 1, 0, 1 };
    int max = Integer.MIN_VALUE, count = 0;

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == 1) {
        count++;
        if (count > max) {
          max = count;
        }
      } else {
        count = 0;
      }
    }
    System.out.println(max);

  }
}
