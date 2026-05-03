import java.util.Arrays;

public class Stacks1 {
  public static class stack {
    int[] arr = new int[5];
    int top = -1;

    void push(int x) {
      if (top >= arr.length - 1) {
        System.out.println("Stack overflow");
        return;
      }
      arr[++top] = x;
    }

    void pop() {
      if (top < 0) {
        System.out.println("Stack underflow");
        return;
      }
      top--;
    }

    void peek() {

      if (top < 0) {
        System.out.println("Stack is empty");
        return;
      }
      System.out.println(arr[top]);

      System.out.println(Arrays.toString(arr));
    }

    boolean isEmpty() {
      return top < 0;
    }

    boolean isFull() {
      return top == arr.length - 1;
    }
  }

  public static void main(String[] args) {
    stack st = new stack();
    st.push(0);
    st.push(1);
    st.push(2);
    st.push(3);
    st.peek();
    st.pop();
    st.peek();
    System.out.println(st.isEmpty());
    System.out.println(st.isFull());
  }
}
