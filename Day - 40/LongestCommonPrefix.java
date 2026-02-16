import java.util.Arrays;

public class LongestCommonPrefix {
  public static void main(String[] args) {
    String[] words = { "flower", "flow", "flight" };

    String res = "";

    Arrays.sort(words);
    System.out.println(Arrays.toString(words));

    char[] first = words[0].toCharArray();
    char[] last = words[words.length - 1].toCharArray();

    for (int i = 0; i < Math.min(first.length, last.length); i++) {
      if (first[i] == last[i]) {
        res += first[i];
      } else {
        break;
      }
    }

    System.out.println(res);
  }
}
