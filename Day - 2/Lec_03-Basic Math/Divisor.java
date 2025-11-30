public class Divisor {
  public static void main(String[] args) {
    long start = System.nanoTime();
    int n = 21191;
    for (int i = 1; i <= n; i++) {
      if (n % i == 0) {
        System.out.println(i);
      }
    }

    long time = System.nanoTime() - start;

    System.out.println("Function took: " + (time / 1_000_000.0) + " ms");

  }
}
