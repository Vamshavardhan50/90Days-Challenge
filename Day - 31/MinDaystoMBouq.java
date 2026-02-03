import java.util.Arrays;

public class MinDaystoMBouq {
  public static void main(String[] args) {
    int[] arr = { 7, 7, 7, 7, 13, 11, 12, 7 };
    int k = 3;
    int m = 2;

    minDaysfortheroses(arr, k, m);
  }

  public static void minDaysfortheroses(int[] arr, int k, int m) {

    int max_arr = Arrays.stream(arr).max().getAsInt();
    for (int i = 1; i < max_arr; i++) {
      if (check_bouquets(arr, k, m, i)) {
        System.out.println(i);
      }
    }
  }

  public static boolean check_bouquets(int[] arr, int k, int m, int day) {

    int count = 0; // count of consecutive bloomed flowers
    int bouquets = 0;

    for (int bloom : arr) {
      if (bloom <= day) {
        count++;
        if (count == k) {
          bouquets++;
          count = 0;
        }
      } else {
        count = 0;
      }
    }

    return bouquets >= m;
  }
}