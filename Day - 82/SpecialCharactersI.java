import java.util.HashSet;
import java.util.Set;

public class SpecialCharactersI {
  public static void main(String[] args) {
    String word = "abBCab";
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
    System.out.println(count);
  }
}