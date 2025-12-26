import java.util.Arrays;

public class ProductArrayPuzzle {
  public static void main(String[] args) {
    int[] arr = { 10, 3, 5, 6, 2 };
    int n = arr.length;

    int[] preProd = new int[n];
    preProd[0] = arr[0];
    int[] sufProd = new int[n];
    sufProd[n - 1] = arr[n - 1];

    int[] res = new int[n];

    for (int i = 1; i < n; i++) {
      preProd[i] = preProd[i - 1] * arr[i];
      sufProd[n - 1 - i] = sufProd[n - i] * arr[n - 1 - i];
    }

    for (int i = 0; i < n; i++) {
      if (i == 0) {
        res[i] = sufProd[i + 1];
      } else if (i == n - 1) {
        res[i] = preProd[i - 1];
      } else {
        res[i] = preProd[i - 1] * sufProd[i + 1];
      }
    }

    System.out.println(Arrays.toString(preProd));
    System.out.println(Arrays.toString(sufProd));
    System.out.println(Arrays.toString(res));
  }
}
