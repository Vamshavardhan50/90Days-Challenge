// Brute Force approach

import java.util.*;

public class SortCharactersByFrequency {
  public static void main(String[] args) {
    String s = "Aabb";

    // Step 1: Count frequency
    Map<Character, Integer> map = new HashMap<>();
    for (char ch : s.toCharArray()) {
      map.put(ch, map.getOrDefault(ch, 0) + 1);
    }

    // Step 2: Sort by frequency (descending)
    List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
    list.sort((a, b) -> b.getValue() - a.getValue());
    System.out.println(list);

    // Step 3: Build result
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<Character, Integer> entry : list) {
      for (int i = 0; i < entry.getValue(); i++) {
        sb.append(entry.getKey());
      }
    }

    System.out.println(sb.toString());
  }
}
