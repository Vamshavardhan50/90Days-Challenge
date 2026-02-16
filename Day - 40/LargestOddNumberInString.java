public class LargestOddNumberInString {
  public static void main(String[] args) {
    String num = "35427";

    if ((int) num.charAt(num.length() - 1) % 2 == 1)
      System.out.println(num);
    int i = num.length() - 1;
    while (i >= 0) {
      int n = num.charAt(i);
      if (n % 2 == 1)
        System.out.println(num.substring(0, i + 1));
      i--;
    }
    System.out.println("");

  }
}
