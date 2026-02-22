public class StringToAtoi {

  public static void main(String[] args) {
    String s = "1337c0d3";
    System.out.println(Atoi(s));
  }

  public static int Atoi(String s) {
    if (s == null)
      return 0;

    s = s.trim();
    if (s.isEmpty())
      return 0;

    int i = 0;
    int sign = 1;

    // Handle sign
    if (s.charAt(i) == '+' || s.charAt(i) == '-') {
      sign = (s.charAt(i) == '-') ? -1 : 1;
      i++;
    }

    int num = 0;

    while (i < s.length() && Character.isDigit(s.charAt(i))) {

      int digit = s.charAt(i) - '0';

      // Check overflow BEFORE multiplying
      if (num > (Integer.MAX_VALUE - digit) / 10) {
        return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      }

      num = num * 10 + digit;
      i++;
    }

    return num * sign;
  }
}