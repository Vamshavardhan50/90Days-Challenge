import java.util.Scanner;

public class Bit {
  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);

    int st = sc.nextInt();
    sc.nextLine(); // removes leftover newline
    String b = sc.nextLine();
    System.out.println("first line:" + b);

    int x = 0;

    while (st-- > 0) {
      String s = sc.nextLine();

      if (s.contains("++")) {
        x++;
      } else {
        x--;
      }
    }

    System.out.print(x);
  }
}