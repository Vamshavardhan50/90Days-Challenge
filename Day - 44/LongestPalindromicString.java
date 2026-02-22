public class LongestPalindromicString {
  public static void main(String[] args) {
    String s = "cbbd";
    System.out.println(PalindromicString(s));
  }

  public static String PalindromicString(String s) {
    if (s.length() <= 1) {
      return s;
    }

    String LPS = "";

    for (int i = 0; i < s.length(); i++) {

      // Odd length palindrome
      int low = i;
      int high = i;

      while (low >= 0 && high < s.length()
          && s.charAt(low) == s.charAt(high)) {
        low--;
        high++;
      }

      String palindrome = s.substring(low + 1, high);
      if (palindrome.length() > LPS.length()) {
        LPS = palindrome;
      }

      // Even length palindrome
      low = i - 1;
      high = i;

      while (low >= 0 && high < s.length()
          && s.charAt(low) == s.charAt(high)) {
        low--;
        high++;
      }

      palindrome = s.substring(low + 1, high);
      if (palindrome.length() > LPS.length()) {
        LPS = palindrome;
      }
    }

    return LPS;
  }
}
