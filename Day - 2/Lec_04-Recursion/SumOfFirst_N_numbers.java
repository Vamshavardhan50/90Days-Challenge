public class SumOfFirst_N_numbers {
  public static int sumN(int n) {
    int sum = 0;
    if (n == 0) {
      return 0;
    }
    sum = n + sumN(n - 1);
    return sum;
  }

  public static void main(String[] args) {
    System.out.println(sumN(5));
  }
}
