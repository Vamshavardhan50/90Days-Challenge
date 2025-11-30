class ReverseDigit {
  public int reverse(int x) {
    boolean neg = false;

    if (x < 0) {
      neg = true;
      // Convert to positive, but carefully (avoid MIN_VALUE edge case)
      if (x == Integer.MIN_VALUE)
        return 0;
      x = -x;
    }

    int num = x;
    int result = 0;

    while (num > 0) {
      int ld = num % 10;

      if (result > (Integer.MAX_VALUE - ld) / 10) {
        return 0;
      }

      result = result * 10 + ld;
      num /= 10;
    }

    return neg ? -result : result;
  }

  public static void main(String[] args) {
    ReverseDigit rd = new ReverseDigit();
    int input = -1534236469;
    int reversed = rd.reverse(input);
    System.out.println("Reversed: " + reversed); // Output: Reversed: -54321
  }
}
