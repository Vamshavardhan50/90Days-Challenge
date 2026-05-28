import java.util.HashSet;
import java.util.Set;

public class SpecialCharactersI {
  public static void main(String[] args) {
    String word = "AbBCab";

    boolean LowerCase = word.contains("c");
    boolean UpperCase = word.contains("C");
    Set<Character> lower = new HashSet<>();
    Set<Character> upper = new HashSet<>();

    for (char ch : word.toCharArray()) {
      if (Character.isLowerCase(ch)) {
        lower.add(ch);
      } else {
        upper.add(Character.toLowerCase(ch));
      }
    }

    int count = 0;

    for (char ch : lower) {
      if (upper.contains(ch)) {
        count++;
      }
    }
    if (LowerCase && UpperCase) {
      System.out.println(count);
    } else {
      System.out.println(0);
    }
  }
}