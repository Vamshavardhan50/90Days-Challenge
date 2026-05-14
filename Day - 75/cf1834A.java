public class cf1834A {
  public static void main(String[] args) {

    int[] arr = { -1, -1, -1 };

    int sum = 0;
    int mult = 1;
    int operations = 0;

    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];
      mult *= arr[i];
    }

    // Make sum non-negative
    while (sum < 0) {
      sum += 2;
      mult *= -1;
      operations++;
    }

    // Product should be 1
    if (mult == -1) {
      operations++;
    }

    System.out.println(operations);
  }
}