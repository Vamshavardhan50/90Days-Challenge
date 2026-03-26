# Pattern 6: Palindromic Subsequences

## Overview

This pattern deals with finding, counting, or analyzing palindromic subsequences. A palindrome reads the same forwards and backwards.

---

## Problem 1: Longest Palindromic Subsequence (LPS)

**Statement:** Find the longest subsequence that is a palindrome.

### Solution 1: LCS Approach

**Key Insight:** LPS(s) = LCS(s, reverse(s))

```java
public int longestPalindromicSubseqLCS(String s) {
    /*
      LPS = Longest Common Subsequence of s and reverse(s).
      Time: O(n²), Space: O(n²)
    */
    String sRev = new StringBuilder(s).reverse().toString();
    int n = s.length();

    int[][] dp = new int[n + 1][n + 1];

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            if (s.charAt(i - 1) == sRev.charAt(j - 1)) {
                dp[i][j] = 1 + dp[i - 1][j - 1];
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }

    return dp[n][n];
}

// Example
// longestPalindromicSubseqLCS("bbbab") -> 4 ("bbbb")
// longestPalindromicSubseqLCS("ac") -> 1 ("a" or "c")
```

### Solution 2: Range DP Approach

**Key Concept:** Use 2D table with `dp[i][j]` = LPS of s[i...j]

```java
public int longestPalindromicSubseqRange(String s) {
    /*
      Range DP: dp[i][j] = LPS of s[i...j].
      Time: O(n²), Space: O(n²)
    */
    int n = s.length();
    int[][] dp = new int[n][n];

    // Base case: single characters are palindromes
    for (int i = 0; i < n; i++) {
        dp[i][i] = 1;
    }

    // Fill for increasing lengths
    for (int length = 2; length <= n; length++) {
        for (int i = 0; i <= n - length; i++) {
            int j = i + length - 1;

            if (s.charAt(i) == s.charAt(j)) {
                // Characters match
                if (length == 2) {
                    dp[i][j] = 2;
                } else {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                }
            } else {
                // Characters don't match
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
    }

    return dp[0][n - 1];
}

// Example
// longestPalindromicSubseqRange("bbbab") -> 4
```

### Get Actual Palindrome

```java
public String getLongestPalindrome(String s) {
    /*
      Return the actual palindromic subsequence.
    */
    int n = s.length();
    int[][] dp = new int[n][n];

    for (int i = 0; i < n; i++) {
        dp[i][i] = 1;
    }

    for (int length = 2; length <= n; length++) {
        for (int i = 0; i < n - length + 1; i++) {
            int j = i + length - 1;
            if (s.charAt(i) == s.charAt(j)) {
                dp[i][j] = 2 + (i + 1 <= j - 1 ? dp[i + 1][j - 1] : 0);
            } else {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
    }

    // Reconstruct palindrome
    return backtrack(s, dp, 0, n - 1);
}

private String backtrack(String s, int[][] dp, int i, int j) {
    if (i > j) {
        return "";
    }
    if (i == j) {
        return String.valueOf(s.charAt(i));
    }

    if (s.charAt(i) == s.charAt(j)) {
        return s.charAt(i) + backtrack(s, dp, i + 1, j - 1) + s.charAt(j);
    } else {
        if (dp[i + 1][j] > dp[i][j - 1]) {
            return backtrack(s, dp, i + 1, j);
        } else {
            return backtrack(s, dp, i, j - 1);
        }
    }
}

// Example
// getLongestPalindrome("bbbab") -> "bbbb"
```

#### Walkthrough: "bbbab"

```
        b   b   b   a   b
    b   1   2   3   3   3
    b       1   2   2   3
    b           1   1   3
    a               1   1
    b                   1

dp[0][0]=1 (b)
dp[0][1]=2 (bb)
dp[0][2]=3 (bbb)
dp[0][3]=3 (bbb, a doesn't extend)
dp[0][4]=3 (ba vs bbb)
...
Wait, this doesn't look right. Let me recalculate.

Actually for "bbbab":
- Palindromes: "b", "b", "b", "a", "b", "bb", "bb", "bbb", "bab"
- Longest: "bbb" or "bab" (both length 3)

Let me trace the algorithm again more carefully.
length=1: dp[i][i] = 1 for all i
length=2:
  dp[0][1]: s[0]='b'==s[1]='b' -> 2 + dp[1][0] = 2 + 0 = 2
  dp[1][2]: s[1]='b'==s[2]='b' -> 2 + 0 = 2
  dp[2][3]: s[2]='b'!=s[3]='a' -> max(dp[3][3], dp[2][2]) = max(1,1) = 1
  dp[3][4]: s[3]='a'!=s[4]='b' -> max(1,1) = 1
length=3:
  dp[0][2]: s[0]='b'==s[2]='b' -> 2 + dp[1][1] = 2+1 = 3
  dp[1][3]: s[1]='b'!=s[3]='a' -> max(dp[2][3], dp[1][2]) = max(1,2) = 2
  dp[2][4]: s[2]='b'==s[4]='b' -> 2 + dp[3][3] = 2+1 = 3
length=4:
  dp[0][3]: s[0]='b'!=s[3]='a' -> max(dp[1][3], dp[0][2]) = max(2,3) = 3
  dp[1][4]: s[1]='b'==s[4]='b' -> 2 + dp[2][3] = 2+1 = 3
length=5:
  dp[0][4]: s[0]='b'==s[4]='b' -> 2 + dp[1][3] = 2+2 = 4

Result: 4 (makes sense: "bbbab" -> "bbb" or "bbab" or...)
Actually the longest is "bbbb"? But "bbbab" doesn't contain "bbbb"...
Oh I see my mistake. Let me reconsider what "bbbb" means.
"bbbab" has subsequences that are palindromes.
Can I form "bbbb" as subsequence? b, b, b, (skip a), b -> yes!
So LPS("bbbab") = 4, and one LPS is "bbbb".
```

---

## Problem 2: Palindromic Partitions

**Statement:** Partition string into minimum palindromic substrings.

```java
public int minPalindromicCuts(String s) {
    /*
      Minimum number of cuts needed to partition string into palindromes.

      Time: O(n²)
      Space: O(n²)
    */
    int n = s.length();

    // First, check which substrings are palindromes
    boolean[][] isPalindrome = new boolean[n][n];
    for (int i = 0; i < n; i++) {
        isPalindrome[i][i] = true;
    }

    for (int length = 2; length <= n; length++) {
        for (int i = 0; i < n - length + 1; i++) {
            int j = i + length - 1;
            if (s.charAt(i) == s.charAt(j)) {
                isPalindrome[i][j] = (length == 2) || isPalindrome[i + 1][j - 1];
            } else {
                isPalindrome[i][j] = false;
            }
        }
    }

    // DP for minimum cuts
    int[] cuts = new int[n];
    for (int i = 0; i < n; i++) {
        cuts[i] = Integer.MAX_VALUE;
    }

    for (int i = 0; i < n; i++) {
        if (isPalindrome[0][i]) {
            cuts[i] = 0;  // Entire s[0...i] is palindrome
        } else {
            for (int j = 0; j < i; j++) {
                if (isPalindrome[j + 1][i]) {
                    cuts[i] = Math.min(cuts[i], cuts[j] + 1);
                }
            }
        }
    }

    return cuts[n - 1];
}

// Example
// minPalindromicCuts("nitin") -> 0 (palindrome itself)
// minPalindromicCuts("geeks") -> 1 ("g" | "eeks" doesn't work)
```

---

## Problem 3: Count Different Palindromic Subsequences

```java
public int countDiffPalindromicSubseq(String s) {
    /*
      Count different palindromic subsequences.

      Approach: Use memoization with range DP.
    */
    final int MOD = (int) (1e9 + 7);
    Map<String, Integer> memo = new HashMap<>();
    return dp(0, s.length() - 1, s, memo, MOD);
}

private int dp(int left, int right, String s, Map<String, Integer> memo, int MOD) {
    if (left > right) {
        return 0;
    }
    if (left == right) {
        return 1;  // Single character is palindrome
    }

    String key = left + "," + right;
    if (memo.containsKey(key)) {
        return memo.get(key);
    }

    int result = 0;

    if (s.charAt(left) == s.charAt(right)) {
        // Characters match, can form palindromes
        int innerLeft = left + 1;
        int innerRight = right - 1;

        // Find indices for inner range
        while (innerLeft <= innerRight && s.charAt(innerLeft) != s.charAt(left)) {
            innerLeft++;
        }
        while (innerLeft <= innerRight && s.charAt(innerRight) != s.charAt(right)) {
            innerRight--;
        }

        if (innerLeft > innerRight) {
            // No duplicate in middle
            result = (dp(left + 1, right - 1, s, memo, MOD) * 2 + 2) % MOD;
        } else if (innerLeft == innerRight) {
            // One duplicate in middle
            result = (dp(left + 1, right - 1, s, memo, MOD) * 2 + 1) % MOD;
        } else {
            // Multiple duplicates in middle
            int calc = (dp(left + 1, right - 1, s, memo, MOD) * 2 - dp(innerLeft + 1, innerRight - 1, s, memo, MOD)) % MOD;
            result = (calc + MOD) % MOD;
        }
    } else {
        // Different characters
        int calc = (dp(left + 1, right, s, memo, MOD) + dp(left, right - 1, s, memo, MOD) - dp(left + 1, right - 1, s, memo, MOD)) % MOD;
        result = (calc + MOD) % MOD;
    }

    memo.put(key, result);
    return result;
}

// Example
// countDiffPalindromicSubseq("bccccdd") -> 6
```

---

## Problem 4: Shortest Palindrome

**Statement:** Find shortest palindrome by adding characters to front.

```java
public String shortestPalindrome(String s) {
    /*
      Add minimum characters to front to make palindrome.

      Approach: Combine s and reverse(s) with KMP algorithm.
    */
    if (s == null || s.length() == 0) {
        return s;
    }

    String sRev = new StringBuilder(s).reverse().toString();
    String combined = s + "#" + sRev;

    // Build KMP table
    int n = combined.length();
    int[] lps = new int[n];

    for (int i = 1; i < n; i++) {
        int j = lps[i - 1];
        while (j > 0 && combined.charAt(i) != combined.charAt(j)) {
            j = lps[j - 1];
        }
        if (combined.charAt(i) == combined.charAt(j)) {
            j++;
        }
        lps[i] = j;
    }

    int palindromeLength = lps[n - 1];
    String toAdd = sRev.substring(0, s.length() - palindromeLength);

    return toAdd + s;
}

// Example
// shortestPalindrome("abcd") -> "dcbabcd"
// shortestPalindrome("") -> ""
```

---

## Problem 5: Longest Palindromic Substring

**Statement:** Find longest substring that is a palindrome (not subsequence).

```java
public String longestPalindromicSubstring(String s) {
    /*
      Find longest palindromic substring using expand-around-center.

      Time: O(n²)
      Space: O(1)
    */
    if (s == null || s.length() == 0) {
        return "";
    }

    String longest = "";

    for (int i = 0; i < s.length(); i++) {
        // Odd length palindromes
        String palindrome1 = expandAroundCenter(s, i, i);
        if (palindrome1.length() > longest.length()) {
            longest = palindrome1;
        }

        // Even length palindromes
        String palindrome2 = expandAroundCenter(s, i, i + 1);
        if (palindrome2.length() > longest.length()) {
            longest = palindrome2;
        }
    }

    return longest;
}

private String expandAroundCenter(String s, int left, int right) {
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
        left--;
        right++;
    }
    return s.substring(left + 1, right);
}

// Example
// longestPalindromicSubstring("babad") -> "bab" or "aba"
// longestPalindromicSubstring("cbbd") -> "bb"
```

---

## Comparison: LPS vs LCS vs Different Palindromes

| Problem                       | Type            | Time  | Meaning              |
| ----------------------------- | --------------- | ----- | -------------------- |
| LPS                           | Subsequence     | O(n²) | "bbbab" -> "bbbb"    |
| Longest Palindromic Substring | Substring       | O(n²) | "babbab" -> "babbab" |
| Count Different Palindromic   | Subsequence     | O(n²) | Count distinct       |
| Shortest Palindrome           | Substring + Add | O(n)  | Add chars to front   |
| Min Palindromic Cuts          | Partition       | O(n²) | Cut into palindromes |

---

## Key Techniques

| Technique            | Use Case                                   |
| -------------------- | ------------------------------------------ |
| Range DP             | When s[i] == s[j] and inner is independent |
| LCS with reverse     | Simple LPS calculation                     |
| Expand around center | Palindromic substrings (contiguous)        |
| KMP algorithm        | Shortest palindrome addition               |
| Interval tracking    | Complex palindromic problems               |

---

## Common Mistakes

❌ **Mistake 1:** Confusing subsequence and substring

```
"babbab": LPS subsequence can be the whole string
"babbab": LPS substring is also the whole string (but usually different)
```

✅ **Correct:** Check the problem statement

❌ **Mistake 2:** Not handling i+1 < j-1 correctly

```python
# WRONG
dp[i][j] = 2 + dp[i+1][j-1]  # What if i+1 > j-1?

# RIGHT
dp[i][j] = 2 + dp[i+1][j-1] if i+1 <= j-1 else 2
```

❌ **Mistake 3:** Off-by-one in string reconstruction

```python
# Make sure indices stay within bounds
```

---

## Interview Tips

✅ **Do:**

- Clarify subsequence vs substring
- Draw DP tables
- Test with odd/even lengths
- Consider edge cases

❌ **Don't:**

- Confuse LCS approach with direct DP
- Miss base cases
- Forget to track indices properly

---

## Key Takeaways

1. **LPS via LCS** - elegant but uses extra space
2. **Range DP** - more intuitive for many variants
3. **Single character** - base case is always 1
4. **Matching endpoints** - key insight for optimization
5. **Many variants** - substring, partition, count, etc.
