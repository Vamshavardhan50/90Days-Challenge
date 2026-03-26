public class GenerateParanthesis {
  public static void main(String[] args) {
    int n = 2;
    generate(n, 0, 0, "");
  }

  public static void generate(int n, int open, int close, String ans) {
    // base case
    if (ans.length() == 2 * n) {
      System.out.println(ans);
      return;
    }

    // add '(' if we still have some left
    if (open < n) {
      generate(n, open + 1, close, ans + "(");  
    }

    // add ')' only if it won't break validity
    if (close < open) {
      generate(n, open, close + 1, ans + ")");
    }
  }
}