import java.util.*;

public class MinimumAmountofTimetoFillCups {
  public static void main(String[] args) {
    int[] arr = { 1, 4, 2 };
    int sum = Arrays.stream(arr).sum();
    int max = Arrays.stream(arr).max().getAsInt();

    int answer = Math.max(max, (sum + 1) / 2);
    System.out.println(answer);
  }
}