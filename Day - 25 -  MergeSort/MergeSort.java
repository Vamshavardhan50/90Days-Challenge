import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MergeSort {

  public static void mergeSort(int[] arr, int st, int end) {

    if (st < end) {
      int mid = st + (end - st) / 2;
      mergeSort(arr, st, mid);
      mergeSort(arr, mid + 1, end);

      // merging
      merge(arr, st, mid, end);
    }
  }

  public static void merge(int[] arr, int st, int mid, int end) {
    int i = st;
    int j = mid + 1;
    List<Integer> temp = new ArrayList<>();

    while (i <= mid && j <= end) {
      if (arr[i] <= arr[j]) {
        temp.add(arr[i]);
        i++;
      } else {
        temp.add(arr[j]);
        j++;
      }

    }

    while (i <= mid) {
      temp.add(arr[i]);
      i++;
    }

    while (j <= end) {
      temp.add(arr[j]);
      j++;
    }
    for (int k = 0; k < temp.size(); k++) {
      arr[st + k] = temp.get(k);
    }

  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    int[] arr = new int[n];
    System.out.println("Enter the values :");
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }
    mergeSort(arr, 0, arr.length - 1);
    System.out.println(Arrays.toString(arr));
  }

}
