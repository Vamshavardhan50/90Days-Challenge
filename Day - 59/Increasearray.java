import java.util.Scanner;

//lets try the problem by differences
public class Increasearray {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    long n = sc.nextLong();
    long moves = 0;

    long prev = sc.nextLong();

    for (int i = 1; i < n; i++) {
      long current = sc.nextInt();
      if (current < prev) {
        moves += prev - current;
      } else {
        prev = current;
      }
    }

    System.out.println(moves);
  }
}