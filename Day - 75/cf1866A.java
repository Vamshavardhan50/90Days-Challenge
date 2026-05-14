import java.util.*;

public class cf1866A {
  public static void main(String args[]) {
    List<Integer> list = new ArrayList<>(List.of(0, -1, 0, 1, 0));

    Collections.sort(list);
    System.out.println(list);

    int min = Integer.MAX_VALUE;
    for (int x : list) {
      min = Math.min(min, Math.abs(x));
    }
    System.out.println(min);
  }
}