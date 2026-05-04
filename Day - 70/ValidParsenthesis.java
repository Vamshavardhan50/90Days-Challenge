import java.util.Stack;

public class ValidParsenthesis {
  public static void main(String[] args) {
    String s = ")(){}";

    Stack<Character> st = new Stack<>();

    for (char ch : s.toCharArray()) {
      if (ch == '(' || ch == '[' || ch == '{') {
        st.push(ch);
      } else if (ch == ')' && !st.isEmpty() && st.peek() == '(') {
        st.pop();
      } else if (ch == ']' && !st.isEmpty() && st.peek() == '[') {
        st.pop();
      } else if (ch == '}' && !st.isEmpty() && st.peek() == '{') {
        st.pop();
      }
    }
    System.out.println(st.isEmpty());
  }
}