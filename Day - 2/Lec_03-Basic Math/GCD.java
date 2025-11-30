public class GCD {

  public static int gcd(int a, int b) {
    // code here
    while (a > 0 && b > 0) {
      if (a > b)
        a = a % b;
      else
        b = b % a;
    }
    if (a == 0) {
      return b;
    } else {
      return a;
    }
  }
  public static void main(String[] args) {
    int a = 56;
    int b = 98;
    System.out.println("GCD of " + a + " and " + b + " is: " + gcd(a, b));
  }
}
