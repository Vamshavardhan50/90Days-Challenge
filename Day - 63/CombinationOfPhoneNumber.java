import java.util.*;

public class CombinationOfPhoneNumber {
  public static void main(String[] args) {
    HashMap<Character, String> map = new HashMap<>();
    map.put('2', "abc");
    map.put('3', "def");
    map.put('4', "ghi");
    map.put('5', "jkl");
    map.put('6', "mno");
    map.put('7', "pqrs");
    map.put('8', "tuv");
    map.put('9', "wxyz");

    String digit = "23";

    List<String> result = combination(digit, "", map);
    System.out.println(result);
  }

  public static List<String> combination(String digits, String ans, HashMap<Character, String> map) {
    List<String> result = new ArrayList<>();

    if (digits.length() == 0) {
      result.add(ans);
      return result;
    }

    char ch = digits.charAt(0);
    String letters = map.get(ch);

    for (int i = 0; i < letters.length(); i++) {
      result.addAll(combination(digits.substring(1), ans + letters.charAt(i), map));
    }

    return result;
  }
}