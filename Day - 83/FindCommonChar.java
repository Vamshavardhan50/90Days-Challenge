import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindCommonChar {
  public static void main(String[] args) {

    String[] words = { "bella", "label", "roller" };

    // Sort chars inside each word
    for (int i = 0; i < words.length; i++) {
      char[] chars = words[i].toCharArray();
      Arrays.sort(chars);
      words[i] = new String(chars);
    }

    // Sort words by length
    Arrays.sort(words, (a, b) -> a.length() - b.length());

    List<Character> set = new ArrayList<>();

    for (int i = 0; i < words[0].length(); i++) {

      char ch = words[0].charAt(i);
      boolean common = true;

      for (int j = 1; j < words.length; j++) {

        if (!words[j].contains(String.valueOf(ch))) {
          common = false;
          break;
        }
      }

      if (common) {
        set.add(ch);
      }
    }

    System.out.println(set);
  }
}