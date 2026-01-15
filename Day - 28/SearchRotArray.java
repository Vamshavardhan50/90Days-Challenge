public class SearchRotArray {
  public static void main(String[] args) {
    int[] arr = { 4, 5, 6, 7, 0, 1, 2 };
    int target = 0;
    int low = 0, high = arr.length - 1;
    int ans = -1;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (arr[mid] == target) {
        ans = mid;
        System.out.println(mid);
        break;
      }
      if (arr[mid] >= target) {
        // low = mid + 1;
        high = mid - 1;
      } else {
        // high = mid - 1;
        low = mid + 1;
      }
    }
    System.out.println(ans);

  }
}
