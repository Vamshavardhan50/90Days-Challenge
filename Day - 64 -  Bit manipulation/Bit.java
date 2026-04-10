public class Bit {
  public static void main(String[] args) {
    int n = 43261596;

    String ans = "";

    while (n != 0) {
      if (n % 2 == 1) {
        ans += '1';
      } else {
        ans += '0';
      }
      n = n / 2;
    }

    String binary32 = String.format("%32s", Integer.toBinaryString(n)).replace(' ', '0');
    System.out.println(binary32);

    System.out.println(ans);
    String reversed = new StringBuilder(ans).reverse().toString();
    System.out.println(reversed);
    // binary to decimal;

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

  }

}
