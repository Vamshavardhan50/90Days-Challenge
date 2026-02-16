public class RemoveOutermostParentheses {
  public static void main(String[] args) {
    String str = "(()())(())(()(()))";
    
    String result = "";
    int count = 0;


    for (char ch : str.toCharArray()) {
      if (ch == '(') {
        count++;
        if (count > 1) {
          result += ch;
        }
      } else {
        count -= 1;
        if (count > 0) {
          result += ch;
        }
      }
    }
    System.out.println(result);

  }
}
