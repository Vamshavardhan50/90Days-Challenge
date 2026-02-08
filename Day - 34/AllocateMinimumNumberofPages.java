import java.util.Arrays;

public class AllocateMinimumNumberofPages {
  public static void main(String[] args) {

    int[] arr = { 12, 34, 67, 90 };
    int stu = 2;

    int low = 0; // max element
    int high = Arrays.stream(arr).sum(); // sum of array

    int ans = 0;

    int mid = low + (high - low) / 2;

    if (isValid(mid, arr, stu)) {
      ans = mid; // possible answer
      high = mid - 1; // try smaller
    } else {
      low = mid + 1; // increase limit
    }
  }

  System.out.println(ans);

  }

  public static boolean isValid(int mid, int[] arr, int stu) {

    int pages = 0;
    int students = 1;

    for (int i = 0; i < arr.length; i++) {

      if (pages + arr[i] <= mid) {
        pages += arr[i];
      } else {
        students++;
        pages = arr[i];
      }

    }

    return students <= stu;
  }

