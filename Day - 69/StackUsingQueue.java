import java.util.*;

public class StackUsingQueue {
  public static class StackQueue {
    Queue<Integer> q = new LinkedList<>();

    public void push(int x) {
      int s = q.size();

      q.add(x);

      for (int i = 0; i < s; i++) {
        q.add(q.poll());
      }
    }

    public int pop() {
      int poppedValue = q.peek();
      q.poll();
      return poppedValue;
    }

    public int top() {
      return q.peek();
    }

    public boolean isEmpty() {
      return q.isEmpty();
    }
  }

  public static void main(String[] args) {
    StackQueue sq = new StackQueue();
    sq.push(1);
    sq.push(2);
    sq.push(3);
    System.out.println(sq.pop());
    System.out.println(sq.top());
    System.out.println(sq.isEmpty());
  }
}
