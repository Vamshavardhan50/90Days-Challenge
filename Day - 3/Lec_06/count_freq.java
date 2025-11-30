import java.util.ArrayList;
import java.util.Arrays; // Imported Arrays
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class count_freq {
  public static void main(String[] args) {
    int[] arr = { 1, 2, 3, 4, 4, 2, 3 };

    HashMap<Integer, Integer> map = new HashMap<>();

    // Fix 1: Initialize the list!
    List<List<Integer>> list = new ArrayList<>();

    // Frequency Counting
    for (int i = 0; i < arr.length; i++) {
      map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
    }

    // Converting Map to List of Lists
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      // Fix 2: Use Arrays.asList to create a list from individual elements
      List<Integer> subList = Arrays.asList(entry.getKey(), entry.getValue());
      list.add(subList);
    }

    System.out.println(list);
  }
}