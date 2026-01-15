public class FloorAndCeil {
  public static void main(String[] args) {
    int[] arr = { 3, 4, 4, 7, 8, 10 };
    int target = 8;
    int low = 0, high = arr.length - 1;
    int floor = 0, ceil = 0;

    while (low <= high) {
      int mid = low + (high - low) / 2;

      if (arr[mid] <= target) {
        floor = arr[mid];
        low = mid + 1;
      }
      if (arr[mid] >= target) {
        ceil = arr[mid]; // Potential ceil
        high = mid - 1;
      }
    }
    System.out.println(floor);
    System.out.println(ceil);
  }

}
