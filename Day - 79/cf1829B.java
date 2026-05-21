public class cf1829B {
  public static void main(String[] args) {
    int[] arr = { 1, 0, 0, 0, 1, 0, 0, 0, 1 };

    int count = 0;
    int max = 0;

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == 0) {
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
