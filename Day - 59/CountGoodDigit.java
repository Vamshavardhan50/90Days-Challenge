public class CountGoodDigit {
  static final int MOD = 1000000007;

  public static long count(int i, int n, long ans) {
    if (i == n) {
      return ans;
    }
    if (i % 2 == 0) {
      ans = (ans * 5) % MOD;
    } else {
      ans = (ans * 4) % MOD;
    }
    return count(i + 1, n, ans);
  }

  public static void main(String[] args) {
    int n = 50;
    long ans = 1;
    System.out.println(count(0, n, ans));
  }
}