public class MaxNestingDepthoftheParentheses {
  public static void main(String[] args) {
    String s = "(1+(2*3)+((8)/4))+1";
    int currentCount = 0, max = 0;
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (ch == '(') {
        currentCount++;
        if (currentCount > max) {
          max = currentCount;
        }
      }
      if (ch == ')') {
        currentCount--;
      }
    }
    System.out.println(max);

  }
}