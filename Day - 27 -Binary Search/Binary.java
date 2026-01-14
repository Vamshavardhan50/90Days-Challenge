public class Binary {
  public static void main(String[] args) {
    int[] arr = { -1, 0, 3, 5, 9, 12 };
    int target = 9;
    System.out.println("Binary Search");

    int low = 0, high = arr.length - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;

      if (arr[mid] == target) {
        System.out.println("index : " + mid);
        System.out.println("value : " + arr[mid]);
        break;
      } else if (arr[mid] > target) {
        high = mid - 1;
        System.out.println("Mid is greater");
      } else {
        low = mid + 1;
        System.out.println("target is lower");
      }
    }
  }
}
