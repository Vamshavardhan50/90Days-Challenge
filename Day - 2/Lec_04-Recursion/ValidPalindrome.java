public class ValidPalindrome {
  public static void main(String[] args) {
    String phrase = "ab_a";
    String ans = phrase.toLowerCase().replaceAll("[^a-z0-9]", "");
    String reversed = new StringBuilder(ans).reverse().toString();
    System.out.println(reversed);

  }
}



// Now the regex means:
// a–z → keep
// 0–9 → keep
// _ → keep
// everything else → remove