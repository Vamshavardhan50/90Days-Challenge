
public class Upper {
  public static void main(String[] args) {
    int[] arr = { 3, 5, 8, 9, 15, 19 };
    int x = 9;

    int low = 0, high = arr.length - 1;
    int ans = arr.length;

    while (low <= high) {
      int mid = low + (high - low) / 2;

      if (arr[mid] > x) {
        ans = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    System.out.println(ans);

  }
}
