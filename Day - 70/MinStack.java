import java.util.Stack;

public class MinStack {

  private Stack<Integer> st;
  private Stack<Integer> minStack;

  public MinStack() {
    st = new Stack<>();
    minStack = new Stack<>();
  }

  public void push(int x) {
    st.push(x);

    if (minStack.isEmpty()) {
      minStack.push(x);
    } else {
      minStack.push(Math.min(minStack.peek(), x));
    }
  }

  public void pop() {
    if (!st.isEmpty()) {
      st.pop();
      minStack.pop();
    }
  }

  public int top() {
    return st.peek();
  }

  public int getMin() {
    return minStack.peek();
  }

  public static void main(String[] args) {
    MinStack st = new MinStack();
    st.push(1);
    st.push(2);
    st.push(3);
    System.out.println(st.getMin());
    st.pop();
    st.push(0);
    System.out.println(st.getMin());
  }
}