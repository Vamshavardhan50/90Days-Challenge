public class MInInSortedArray {
  public static void main(String[] args) {
    int[] arr = { 3, 4, 5, 1, 2 };
    int ans = Integer.MAX_VALUE;

    int low = 0, high = arr.length - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;

      if (arr[low] <= arr[high]) {
        ans = Math.min(arr[low], ans);
        break;
      } else if (arr[mid] >= arr[low]) {
        ans = Math.min(arr[low], ans);
        low = mid + 1;
      } else {
        ans = Math.min(arr[mid], ans);
        high = mid - 1;
      }
    }
    System.out.println(ans);
  }
}
