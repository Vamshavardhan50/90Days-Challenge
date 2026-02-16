public class ReverseWordInaString {
  public static void main(String[] args) {
    String s = "  a good   example";
    s = s.trim().replaceAll("\\s+", " ");

    String result = "";
    int last_index = s.length();
    for (int i = s.length() - 1; i >= 0; i--) {
      if (s.charAt(i) == ' ') {
        String str = s.substring(i + 1, last_index);
        result += str;
        result += " ";
        last_index = i;
      }
    }
    result += s.substring(0, last_index);
    System.out.println(result);
  }
}
