public class SortedArrayII {
  public static void main(String[] args) {
    int[] arr = { 2, 5, 6, 0, 0, 1, 2 };
    int target = 0;

    int low = 0, high = arr.length - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;

      if (arr[mid] == target) {
        System.out.println(true);
      }

      if (arr[low] == arr[mid] && arr[mid] == arr[high]) {
        low++;
        high--;
      } else if (arr[low] <= arr[mid]) {
        if (target >= arr[low] && target < arr[mid]) {
          high = mid - 1;
        } else {
          low = mid + 1;
        }
      } else {
        if (target > arr[mid] && target <= arr[high]) {
          low = mid + 1;
        } else {
          high = mid - 1;
        }
      }
    }
    System.out.println(false);

  }
}
