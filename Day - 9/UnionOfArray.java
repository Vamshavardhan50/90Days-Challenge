import java.util.*;

public class UnionOfArray {
  public List<Integer> findUnion(int[] arr1, int[] arr2) {
    Set<Integer> set = new TreeSet<>();

    // Insert elements from first array
    for (int num : arr1) {
      set.add(num);
    }

    // Insert elements from second array
    for (int num : arr2) {
      set.add(num);
    }

    // Convert set to list and return
    return new ArrayList<>(set);
  }

  public static void main(String[] args) {
    int[] arr1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    int[] arr2 = { 2, 3, 4, 4, 5, 11, 12 };

    UnionOfArray obj = new UnionOfArray();
    List<Integer> result = obj.findUnion(arr1, arr2);

    System.out.print("Union of arr1 and arr2 is: ");
    for (int val : result) {
      System.out.print(val + " ");
    }

  }
}