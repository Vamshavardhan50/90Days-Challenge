import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CountInversion {

  public static int mergeSort(int[] arr, int st, int end) {
    int invCount = 0;

    if (st < end) {
      int mid = st + (end - st) / 2;

      invCount += mergeSort(arr, st, mid); // left inversions
      invCount += mergeSort(arr, mid + 1, end); // right inversions
      invCount += merge(arr, st, mid, end); // merge inversions
    }

    return invCount;
  }

  public static int merge(int[] arr, int st, int mid, int end) {
    int invCount = 0;
    int i = st;
    int j = mid + 1;
    List<Integer> temp = new ArrayList<>();

    while (i <= mid && j <= end) {
      if (arr[i] <= arr[j]) {
        temp.add(arr[i]);
        i++;
      } else {
        temp.add(arr[j]);
        invCount += (mid - i + 1); // count inversions
        j++;
      }
    }

    while (i <= mid) {
      temp.add(arr[i++]);
    }

    while (j <= end) {
      temp.add(arr[j++]);
    }

    for (int k = 0; k < temp.size(); k++) {
      arr[st + k] = temp.get(k);
    }

    return invCount;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    int[] arr = new int[n];
    System.out.println("Enter the values:");
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }

    int inversions = mergeSort(arr, 0, arr.length - 1);
    System.out.println("Inversion Count: " + inversions);
    System.out.println(Arrays.toString(arr));
  }
}
