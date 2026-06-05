import java.util.PriorityQueue;

public class LongestHappyString {

  static class Pair {
    char ch;
    int count;

    Pair(char ch, int count) {
      this.ch = ch;
      this.count = count;
    }
  }

  public static void main(String[] args) {
    int a = 7;
    int b = 1;
    int c = 0;

    PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> y.count - x.count);

    if (a > 0)
      pq.offer(new Pair('a', a));

    if (b > 0)
      pq.offer(new Pair('b', b));

    if (c > 0)
      pq.offer(new Pair('c', c));

    StringBuilder ans = new StringBuilder();

    while (!pq.isEmpty()) {
      Pair first = pq.poll();

        int n = ans.length();

      if (n >= 2 &&
          ans.charAt(n - 1) == first.ch &&
          ans.charAt(n - 2) == first.ch) {

        if (pq.isEmpty()) {
          break;
        }

        Pair second = pq.poll();

        ans.append(second.ch);
        second.count--;

        if (second.count > 0) {
          pq.offer(second);
        }

        pq.offer(first);

      } else {

        ans.append(first.ch);
        first.count--;

        if (first.count > 0) {
          pq.offer(first);
        }
      }
    }

    System.out.println(ans);
  }
}