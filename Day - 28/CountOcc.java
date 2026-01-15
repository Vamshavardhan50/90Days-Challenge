public class CountOcc {
  public static void main(String[] args) {
    int[] arr = { 8, 9, 10, 12, 12, 12 };
    int target = 12;

    int low = lower(arr, target);
    int high = upper(arr, target);
    System.out.println(low);
    System.out.println(high);

    if (low == -1) {
      System.out.println("Length : 0");
    } else {
      System.out.println("Length : " + (high - low));
    }
  }

  // First index where arr[i] >= target
  public static int lower(int[] arr, int target) {
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

  // First index where arr[i] > target
  public static int upper(int[] arr, int target) {
    int low = 0, high = arr.length - 1;
    int ans = arr.length;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (arr[mid] > target) {
        ans = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return ans;
  }
}
