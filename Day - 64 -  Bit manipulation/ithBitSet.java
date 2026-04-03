public class ithBitSet {
  public static void main(String[] args) {
    int n = 10;
    String ans = "";
    int ith = 1;

    while (n != 0) {
      if (n % 2 == 1) {
        ans += '1';
      } else {
        ans += '0';
      }
      n = n / 2;
    }

    System.out.println(ans.charAt(ith));
    Character ch = ans.charAt(ith);
    if (ch == '1') {
      System.out.println(true);
    } else {
      System.out.println(false);
    }

  }
}
