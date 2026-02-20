import java.util.Map;
import static java.util.Map.entry;

public class RomantoInteger {
  public static void main(String[] args) {

    Map<Character, Integer> map = Map.ofEntries(
        entry('I', 1),
        entry('V', 5),
        entry('X', 10),
        entry('L', 50),
        entry('C', 100),
        entry('D', 500),
        entry('M', 1000));

    String s = "MCMXCIV";
    int sum = 0;

    int i = s.length() - 1;

    while (i > 0) {

      int last = map.get(s.charAt(i));
      int prev = map.get(s.charAt(i - 1));

      if (prev < last) {
        // subtractive case (IV, IX, CM, etc.)
        sum += (last - prev);
      } else {
        // normal addition
        sum += last + prev;
      }

      i -= 2; // correct decrement
    }

    // If string length is odd, one character remains
    if (i == 0) {
      sum += map.get(s.charAt(0));
    }

    System.out.println(sum);
  }
}