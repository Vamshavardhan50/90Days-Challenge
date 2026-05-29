public class MinRepWithDigitSum {
  public static void main(String[] args) {
    int[] arr = { 999, 19, 199 };
    int min = Integer.MAX_VALUE;

    for (int n : arr) {
      int sum = 0;
      while (n > 0) {
        int lastDigit = n % 10;
        sum += lastDigit;
        n /= 10;

      }
      min = Math.min(min, sum);
    }
    System.out.println(min);
  }
}