public class Lower {
  public static void main(String[] args) {
    int[] arr = { 2, 3, 7, 10, 11, 11, 25 };
    int x = 2;

    int low = 0, high = arr.length;

    while (low <= high) {
      int mid = low + (high - low) / 2;

      if (arr[mid] >= x) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
      System.out.println("Index : " + mid);
      System.out.println("value : " + arr[mid]);

    }

  }
}
