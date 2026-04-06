public class UniqueElements {
  public static void main(String[] args) {
    int[] arr = { 1, 2, 1, 3, 5, 2 };
    int ans = 0;
    for (int i = 0; i < arr.length; i++) {
      ans = ans ^ arr[i];
    }
    System.out.println(ans);
  }
}