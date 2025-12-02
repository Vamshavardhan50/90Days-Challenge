public class LargestElementInAnArray {
  public static void main(String[] args) {
    int[] arr = { 2, 5, 1, 3, 0 };

    int n = arr.length;
    int max = arr[0];

    for (int i = 0; i < n; i++) {
      if (arr[i] > max) {
        max = arr[i];
      }
    }
    System.out.println(max);
  }
}
