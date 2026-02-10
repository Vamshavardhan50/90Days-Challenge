public class MinMaxiDistbtwGasStations {

  public static void main(String[] args) {
    int[] arr = { 1, 2, 3, 4, 5 };
    int k = 4;

    double low = 0;
    double high = arr[arr.length - 1] - arr[0];

    while (high - low > 1e-6) {
      double mid = (low + high) / 2;

      if (isValid(mid, k, arr)) {
        high = mid;
      } else {
        low = mid;
      }
    }

    System.out.println(high);
  }

  public static boolean isValid(double dist, int k, int[] arr) {
    int count = 0;

    for (int i = 1; i < arr.length; i++) {
      double gap = arr[i] - arr[i - 1];

      // number of stations needed in this gap
      count += (int) Math.ceil(gap / dist) - 1;
    }

    return count <= k;
  }
}
