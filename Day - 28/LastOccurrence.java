public class LastOccurrence {
  public static void main(String[] args) {
    int[] arr = { 5, 7, 7, 8, 8, 10 };
    int target = 8;
    int low = Lower(arr, target);
    int high = Upper(arr, target);
    System.out.println("Low : " + low);
    System.out.println("High : " + high);
  }

  public static int Lower(int[] arr, int target) {
    int low = 0, high = arr.length - 1;
    int ans = -1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (arr[mid] >= target) {
        ans = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return ans;
  }

  public static int Upper(int[] arr, int target) {
    int low = 0, high = arr.length - 1;
    int ans = -1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (arr[mid] > target) {
        ans = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return ans - 1;
  }
}
