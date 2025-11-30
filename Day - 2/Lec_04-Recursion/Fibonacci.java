public class Fibonacci {

  // Recursive function to compute Nth Fibonacci number
  public static int fibonacci(int N) {
    // Base case: return N if it's 0 or 1
    if (N <= 1) {
      return N;
    }

    // Recursive case: calculate previous two terms
    int last = fibonacci(N - 1); // (N-1)th term
    int slast = fibonacci(N - 2); // (N-2)th term

    return last + slast;
  }

  public static void main(String[] args) {
    int N = 25;
    System.out.println(fibonacci(N)); // Output: 3
  }
}
