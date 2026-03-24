public class BasicRecursion {
  public static void print(int n) {
    if (n == 1) {
      System.out.println(n);
      return;
    }
    print(n - 1);
    System.out.println(n);
  }

  public static void printNtoOne(int n) {
    if (n == 1) {
      System.out.println(n);
      return;
    }
    System.out.println(n);
    printNtoOne(n - 1);
  }

  public static int factorial(int n, int ans) {

    if (n == 1) {
      ans *= n;
      return ans;
    }
    ans *= n;
    return factorial(n - 1, ans);
  }

  static int Rev(int n, int ans) {
    if (n <= 0) {
      return ans;
    }
    int lastdigit = n % 10;
    ans = ans * 10 + lastdigit;
    return Rev(n / 10, ans);

  }

  public static void main(String[] args) {
    // print(7);
    // printNtoOne(5);
    // System.out.println(factorial(5, 1));
    System.out.println(Rev(1278, 0));

  }
}
