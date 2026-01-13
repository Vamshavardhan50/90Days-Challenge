import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ReverserPairs {
  static int count = 0;

  public static void mergeSort(int[] arr, int st, int end) {
    int invCount = 0;

    if (st < end) {
      int mid = st + (end - st) / 2;

      mergeSort(arr, st, mid); // left inversions
      mergeSort(arr, mid + 1, end); // right inversions
      CountPairs(arr, st, mid, end);
      merge(arr, st, mid, end); // merge inversions
    }
  }

  public static void CountPairs(int[] arr, int low, int mid, int high) {
    int right = mid + 1;

    for (int i = low; i <= mid; i++) {
      while (right <= high && arr[i] > 2 * arr[right]) {
        right++;
      }
      count = count + (right - (mid + 1));

    }
  }

  public static void merge(int[] arr, int st, int mid, int end) {
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

  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    int[] arr = new int[n];
    System.out.println("Enter the values:");
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }

    mergeSort(arr, 0, arr.length - 1);
    System.out.println(" Count: " + count);
    System.out.println(Arrays.toString(arr));
  }
}
