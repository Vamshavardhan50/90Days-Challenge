public class ArmstrongNumber {
  public static void main(String[] args) {
    int n = 153;
    int temp = n;

    int power = (int) Math.log10(n) + 1;

    int sum = 0;

    for (int i = 0; i < power; i++) {
      sum += Math.pow(n % 10, power);
      n /= 10;
    }
    System.out.println(sum == temp);

  }
}
