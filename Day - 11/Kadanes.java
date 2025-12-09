public class Kadanes {
  public static void main(String[] args) {
    int[] arr = { -2, -3, -7, -2, -10, -4 };

    int sum = 0;
    int maxi = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];
      if (sum > maxi) {
        maxi = sum;
      }
      if (sum < 0) {
        sum = 0;
      }
    }
    System.out.println(maxi);
  }
}