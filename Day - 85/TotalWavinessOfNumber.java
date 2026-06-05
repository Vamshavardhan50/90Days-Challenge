import java.util.Arrays;

public class TotalWavinessOfNumber {
  public static void main(String[] args) {
    int num1 = 4848;
    int num2 = 4848;

    int wave = 0;

    for (int j = num1; j <= num2; j++) {
      int[] digits = String.valueOf(j)
          .chars()
          .map(c -> c - '0')
          .toArray();

      for (int i = 1; i < digits.length - 1; i++) {
        if (digits[i] > digits[i - 1] && digits[i] > digits[i + 1]) {
          wave++;
        } else if (digits[i] < digits[i - 1] && digits[i] < digits[i + 1]) {
          wave++;

        }
      }
    }
    System.out.println(wave);

  }
}