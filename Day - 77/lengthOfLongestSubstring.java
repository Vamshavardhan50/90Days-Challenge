public class lengthOfLongestSubstring {
  public static void main(String[] args) {

    String s = "pwwkew";

    int max = Integer.MIN_VALUE;
    String res = "";

    for (int i = 0; i < s.length(); i++) {
      String sb = String.valueOf(s.charAt(i));

      if (res.contains(sb)) {
        res = "";
      } else {
        res += sb;
      }

      max = Math.max(max, res.length());

    }
    System.out.println(max);
  }
}