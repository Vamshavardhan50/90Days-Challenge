public class NToOne {
  public static void printToOne(int n) {
    if (n == 0) {
      return;
    }
    System.out.println(n);
    printToOne(n - 1);
  }

  public static void main(String[] args) {
    printToOne(10);

  }
}
