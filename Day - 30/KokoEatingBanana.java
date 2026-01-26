import java.util.Arrays;

public class KokoEatingBanana {

  public static void main(String[] args) {

    int[] piles = {
        332484035, 524908576, 855865114, 632922376,
        222257295, 690155293, 112677673, 679580077,
        337406589, 290818316, 877337160, 901728858,
        679284947, 688210097, 692137887, 718203285,
        629455728, 941802184
    };

    int h = 823855818;

    int result = minEatingSpeed(piles, h);

    System.out.println(result);
  }

  public static int minEatingSpeed(int[] piles, int h) {

    int low = 0;
    int high = Arrays.stream(piles).max().getAsInt();

    int answer = 0;

    while (low <= high) {

      int mid = low + (high - low) / 2;

      long hours = 0;

      for (int pile : piles) {
        hours += (pile + mid - 1) / mid; // ceil
      }

      if (hours <= h) {
        answer = mid; // possible
        high = mid - 1; // try smaller
      } else {
        low = mid + 1; // need faster
      }
    }

    return answer;
  }
}
