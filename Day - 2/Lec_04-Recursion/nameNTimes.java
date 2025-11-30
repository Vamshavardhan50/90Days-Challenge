public class nameNTimes {
  public static void printName(int n) {
    if (n == 0) {
      return;
    }
    System.out.println("Vamsha vardhan");
    printName(n - 1);
  }

  public static void main(String[] args) {
    printName(5);

  }
}
