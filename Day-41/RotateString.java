public class RotateString {
  public static void main(String[] args) {
    String s = "abcde";
    String goal = "abced";
    System.out.println(CheckRotateString(s, goal));
  }

  public static boolean CheckRotateString(String s, String goal) {
    if (s.length() != goal.length()) {
      return false;
    }
    if (s.equals(goal)) {
      return true;
    }
    String res = "";
    for (int i = 1; i < s.length(); i++) {
      res = "";
      res += s.substring(i);
      res += s.substring(0, i);
      System.out.println(res);
      if (res.equals(goal)) {
        return true;
      }
    }
    return false;
  }
}
