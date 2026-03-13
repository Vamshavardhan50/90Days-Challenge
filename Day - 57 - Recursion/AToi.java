// public class AToi {
//   public static void main(String[] args) {
//     String s = "1337c0d3";

//     s = s.trim();
//     StringBuilder res = new StringBuilder();
//     if (s.charAt(0) == '-') {
//       res.append(s.charAt(0));
//     }
//     for (int i = 0; i < s.length(); i++) {
//       if (Character.isDigit(s.charAt(i))) {
//         res.append(s.charAt(i));
//       }

//     }
//     System.out.println(Integer.parseInt(res.toString()));
//   }
// }

//Recursive approach
public class AToi {
  public static void main(String[] args) {
    String s = "   1337c0d3";
    s = s.trim();
    int sign = 1;
    int i = 0;

    // ignoring whitespaces
    while (i < s.length() && s.charAt(i) == ' ') {
      i++;
    }
    // sign
    if (s.charAt(i) == '-' || s.charAt(i) == '+') {
      sign = s.charAt(i) == '+' ? 1 : -1;
    }
    int num = 0;

    System.out.println(helper(s, i, num, sign));
  }

  public static int helper(String s, int i, int num, int sign) {
    if (i >= s.length() || !Character.isDigit(s.charAt(i))) {
      return (int) num * sign;
    }

    num = num * 10 + (s.charAt(i) - '0');

    if (sign * num <= Integer.MIN_VALUE)
      return Integer.MIN_VALUE;
    if (sign * num >= Integer.MAX_VALUE)
      return Integer.MAX_VALUE;

    return helper(s, i + 1, num, sign);

  }
}