import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MedianofRowWiseSortedMatrix {
  public static void main(String[] args) {
    int[][] arr = {
        { 1, 3, 5 },
        { 2, 6, 9 },
        { 3, 6, 9 }
    };

    List<Integer> list = new ArrayList<>();

    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        list.add(arr[i][j]);
      }
    }
    Collections.sort(list);
    System.out.println(list.get(list.size() / 2));
  } 
}