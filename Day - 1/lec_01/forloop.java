import java.util.Scanner;

public class forloop {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a number: ");
    int n = sc.nextInt();

    // Using a for loop to print numbers from 1 to n
    for (int i = 1; i <= n; i++) {
      System.out.println(i);
    }

    sc.close();
  }
}