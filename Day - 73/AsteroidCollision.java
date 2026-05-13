import java.util.Stack;
import java.util.Arrays;

class AsteroidCollision {

  public int[] asteroidCollision(int[] asteroids) {

    Stack<Integer> st = new Stack<>();

    for (int x : asteroids) {

      boolean destroyed = false;

      // Collision happens only when
      // stack top is positive and current is negative
      while (!st.isEmpty() && st.peek() > 0 && x < 0) {

        // Current asteroid is bigger
        if (st.peek() < Math.abs(x)) {
          st.pop();
        }

        // Both are equal
        else if (st.peek() == Math.abs(x)) {
          st.pop();
          destroyed = true;
          break;
        }

        // Stack top is bigger
        else {
          destroyed = true;
          break;
        }
      }

      // Current asteroid survives
      if (!destroyed) {
        st.push(x);
      }
    }

    // Convert stack to int[]
    int[] ans = new int[st.size()];

    for (int i = st.size() - 1; i >= 0; i--) {
      ans[i] = st.pop();
    }

    return ans;
  }

  public static void main(String[] args) {

    AsteroidCollision s = new AsteroidCollision();

    int[] asteroids = { 5, 10, -5 };

    int[] result = s.asteroidCollision(asteroids);

    System.out.println(Arrays.toString(result));
  }
}