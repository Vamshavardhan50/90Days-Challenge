public class Bit {
  public static void main(String[] args) {
    // int n = 23;
    // String ans = "";

    // while (n != 0) {
    // if (n % 2 == 1) {
    // ans += '1';
    // } else {
    // ans += '0';
    // }
    // n = n / 2;
    // }
    // System.out.println(ans);
    // String reversed = new StringBuilder(ans).reverse().toString();
    // System.out.println(reversed);
    // // binary to decimal;

    // int result = 0;
    // int indx = 0;
    // for (int i = reversed.length() - 1; i >= 0; i--) {
    // char ch = reversed.charAt(i);
    // if (ch == '1') {
    // result += 1 * Math.pow(2, indx);
    // indx++;
    // } else {
    // indx++;
    // }
    // }
    // System.out.println(result);

    int a = 5, b = 6;
    a = a ^ b;
    b = a ^ b;
    a = a ^ b;
    System.out.println(a + " " + b);
  }

}
