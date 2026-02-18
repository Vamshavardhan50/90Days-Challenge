import java.util.HashMap;
import java.util.Map;

public class IsomorphicString {
  public static void main(String[] args) {
    String s1 = "badc";
    String s2 = "b a";

    System.out.println(CheckIsomorphicString(s1, s2));
  }

  public static boolean CheckIsomorphicString(String s1, String s2) {
    Map<Character, Character> map = new HashMap<>();

    for (int i = 0; i < s1.length(); i++) {
      char original = s1.charAt(i);
      char replacement = s2.charAt(i);

      if (!map.containsKey(original)) {
        if (!map.containsValue(replacement)) {
          map.put(original, replacement);
        } else {
          return false;
        }
      } else {
        char mappedChar = map.get(original);
        if (mappedChar != replacement) {
          return false;
        }
      }
    }
    return true;
  }
}