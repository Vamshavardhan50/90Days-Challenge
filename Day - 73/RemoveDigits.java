import java.util.Stack;

public class RemoveDigits {

  public static void main(String[] args) {

    String num = "1432219";
    int k = 3;

    Stack<Character> st = new Stack<>();

    for (char ch : num.toCharArray()) {

      // Remove larger previous digits
      while (!st.isEmpty() && k > 0 && st.peek() > ch) {
        st.pop();
        k--;
      }

      st.push(ch);
    }

    // If k still remains
    while (k > 0 && !st.isEmpty()) {
      st.pop();
      k--;
    }

    // Build result
    StringBuilder sb = new StringBuilder();

    for (char ch : st) {
      sb.append(ch);
    }

    // Remove leading zeros
    while (sb.length() > 0 && sb.charAt(0) == '0') {
      sb.deleteCharAt(0);
    }

    // If empty return 0
    String result = sb.length() == 0 ? "0" : sb.toString();

    System.out.println(result);
  }
}