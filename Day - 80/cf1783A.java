import java.nio.channels.Pipe.SourceChannel;
import java.util.ArrayList;
import java.util.Scanner;

import javax.management.AttributeList;

public class cf1783A {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int t = sc.nextInt();
    while (t-- > 0) {
      int n = sc.nextInt();
      int[] a = new int[n];

      for (int i = 0; i < n; i++) {
        a[i] = sc.nextInt();
      }

      if (a[0] == a[n - 1]) {
        System.out.println("NO");
        continue;
      } else {
        System.out.println("YES");

        StringBuilder sb = new StringBuilder();

        sb.append(a[n - 1]).append(" ");

        for (int i = 0; i < n - 1; i++) {
          sb.append(a[i]).append(" ");
        }

        System.out.println(sb);

      }
    }
  }

}
