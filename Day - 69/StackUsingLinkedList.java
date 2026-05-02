import java.util.*;

public class StackUsingLinkedList {
  public static class StackLL {
    LinkedList<Integer> s = new LinkedList<>();

    public void push(int x) {
      s.addLast(x);
    }

    public int pop() {`
      if (s.isEmpty()) {
        throw new NoSuchElementException("Stack is empty");
      }
      return s.removeLast();
    }

    public int top() {
      if (s.isEmpty()) {
        throw new NoSuchElementException("Stack is empty");
      }
      return s.getLast();
    }

    public boolean isEmpty() {
      return s.isEmpty();
    }

  }

  public static void main(String[] args) {
    StackLL sq = new StackLL();
    sq.push(1);
    sq.push(2);
    sq.push(3);
    System.out.println(sq.pop()); // 3
    System.out.println(sq.top()); // 2
    System.out.println(sq.isEmpty()); // false
  }
}