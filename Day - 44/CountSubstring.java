import java.util.Arrays;

public class CountSubstring {
  public static void main(String[] args) {

    String s = "bbacba";
    int n = s.length();
    int count = 0;

    // lastseen[0] -> last index of 'a'
    // lastseen[1] -> last index of 'b'
    // lastseen[2] -> last index of 'c'
    int[] lastseen = { -1, -1, -1 };

    for (int i = 0; i < n; i++) {

      // Update last seen index of current character
      lastseen[s.charAt(i) - 'a'] = i;

      // If all three characters have appeared at least once
      if (lastseen[0] != -1 && lastseen[1] != -1 && lastseen[2] != -1) {

        // Find the earliest (minimum) last occurrence
        int minValue = Arrays.stream(lastseen).min().getAsInt();

        // All substrings ending at index i
        // and starting from index 0 to minValue
        // will contain at least one 'a', 'b', and 'c'
        count += (minValue + 1);
      }
    }

    System.out.println(count);
  }
}

/*
 * ---------------------------------------------
 * DRY RUN EXPLANATION FOR s = "bbacba"
 * ---------------------------------------------
 * 
 * Initial:
 * lastseen = [-1, -1, -1]
 * count = 0
 * 
 * 
 * i = 0 → 'b'
 * lastseen = [-1, 0, -1]
 * Not all characters seen → skip
 * 
 * 
 * i = 1 → 'b'
 * lastseen = [-1, 1, -1]
 * Not all characters seen → skip
 * 
 * 
 * i = 2 → 'a'
 * lastseen = [2, 1, -1]
 * Still missing 'c' → skip
 * 
 * 
 * i = 3 → 'c'
 * lastseen = [2, 1, 3]
 * 
 * All characters seen.
 * minValue = min(2, 1, 3) = 1
 * 
 * Valid starting indices = 0, 1
 * Substrings ending at index 3:
 * 0 → "bbac"
 * 1 → "bac"
 * 
 * Total added = 2
 * count = 2
 * 
 * 
 * i = 4 → 'b'
 * lastseen = [2, 4, 3]
 * 
 * minValue = min(2, 4, 3) = 2
 * 
 * Valid starting indices = 0, 1, 2
 * Substrings:
 * 0 → "bbacb"
 * 1 → "bacb"
 * 2 → "acb"
 * 
 * Total added = 3
 * count = 5
 * 
 * 
 * i = 5 → 'a'
 * lastseen = [5, 4, 3]
 * 
 * minValue = min(5, 4, 3) = 3
 * 
 * Valid starting indices = 0, 1, 2, 3
 * Substrings:
 * 0 → "bbacba"
 * 1 → "bacba"
 * 2 → "acba"
 * 3 → "cba"
 * 
 * Total added = 4
 * count = 9
 * 
 * 
 * Final Answer:
 * count = 9
 * 
 * 
 * CORE IDEA:
 * At each index i,
 * the smallest last-seen index tells us
 * how far right we are allowed to move the start.
 * 
 * All starting indices from 0 to minValue are valid.
 * Number of such substrings = minValue + 1.
 */