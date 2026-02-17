import java.util.Arrays;
import java.util.Collection;

public class ValidAnagram {
  public static void main(String[] args) {
    String s1 = "anagram";
    String s2 = "nagaram";
    System.out.println(CheckValidAnagram(s1, s2));
  }

  public static boolean CheckValidAnagram(String s1, String s2) {
    if (s1.length() != s2.length()) {
      return false;
    }

    char[] arr1 = s1.toCharArray();
    char[] arr2 = s2.toCharArray();
    Arrays.sort(arr1);
    Arrays.sort(arr2);
    return Arrays.equals(arr1, arr2);

  }
}
