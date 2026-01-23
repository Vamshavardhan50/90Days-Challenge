public class NoOfRotArr {

  public static void main(String[] args) {

    int[] arr = { 4, 5, 6, 7, 0, 1, 2, 3 };

    int low = 0, high = arr.length - 1;
    int target = Integer.MAX_VALUE;
    int index = -1;

    while (low <= high) {

      int mid = low + (high - low) / 2;

      // If current subarray is sorted
      if (arr[low] <= arr[high]) {
        if (arr[low] < target) {
          target = arr[low];
          index = low;
        }
        break;
      }

      // Left half sorted
      if (arr[low] <= arr[mid]) {
        if (arr[low] < target) {
          target = arr[low];
          index = low;
        }
        low = mid + 1;
      }
      // Right half sorted
      else {
        if (arr[mid] < target) {
          target = arr[mid];
          index = mid;
        }
        high = mid - 1;
      }
    }

    System.out.println("Number of rotations: " + index);
  }
}
