public class MinInRotatedArray {
  public static void main(String[] args) {
    int[] arr = { 11, 13, 15, 17 };

    System.out.println(findmin(arr));
  }

  public static int findmin(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] > arr[i - 1]) {

      } else {
        return arr[i];
      }
    }
    return arr[0];
  }
}
