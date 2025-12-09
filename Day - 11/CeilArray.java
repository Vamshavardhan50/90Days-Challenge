public class CeilArray {
  public static void main(String[] args) {
    int[] arr = { 1, 2, 8, 10, 11, 12, 19 };
    int target = 9;

    int low = 0, high = arr.length - 1;

    // Edge cases: before first, after last
    if (target <= arr[0]) {
      System.out.println(arr[0]);
      return;
    }
    if (target > arr[arr.length - 1]) {
      System.out.println("Not found");
      return;
    }

    while (low <= high) {
      int mid = low + (high - low) / 2;

      // Direct hit
      if (arr[mid] == target) {
        System.out.println(arr[mid]);
        return;
      }

      // Target is between arr[mid - 1] and arr[mid] → ceil is arr[mid]
      if (mid - 1 >= 0 && arr[mid - 1] < target && target < arr[mid]) {
        System.out.println(arr[mid]);
        return;
      }

      // Normal binary search
      if (arr[mid] < target) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    System.out.println("Not found");
  }
}
