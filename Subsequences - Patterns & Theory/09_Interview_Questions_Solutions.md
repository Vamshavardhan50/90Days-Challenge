# Common Interview Questions: Subsequence Problems

## Easy Level

### Q1. Longest Increasing Subsequence Length

**LeetCode 300**

```java
public int lengthOfLIS(int[] nums) {
    /*
      Find length of longest increasing subsequence.
      Example: [10,9,2,5,3,7,101,18] -> 4 ([2,3,7,101])
    */
    List<Integer> tails = new ArrayList<>();

    for (int num : nums) {
        int pos = Collections.binarySearch(tails, num);
        if (pos < 0) pos = -(pos + 1);

        if (pos == tails.size()) {
            tails.add(num);
        } else {
            tails.set(pos, num);
        }
    }
    return tails.size();
}
// Time: O(n log n) | Space: O(n)
```

---

### Q2. Two String - Longest Common Subsequence

**LeetCode 1143**

```java
public int longestCommonSubsequence(String text1, String text2) {
    /*
      Find length of LCS.
      Example: "ace", "abcde" -> 3 ("ace")
    */
    int m = text1.length(), n = text2.length();
    int[][] dp = new int[m + 1][n + 1];

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                dp[i][j] = 1 + dp[i - 1][j - 1];
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }
    return dp[m][n];
}
// Time: O(m × n) | Space: O(m × n)
```

---

### Q3. Subset Sum Problem

**Classic, appears everywhere**

```java
public boolean canPartition(int[] nums) {
    /*
      Can array be partitioned into two equal sum subsets?
      Example: [1,5,11,5] -> True (partition [11] and [5,5,1])
    */
    int total = 0;
    for (int num : nums) total += num;

    if (total % 2 != 0) return false;

    int target = total / 2;
    boolean[] dp = new boolean[target + 1];
    dp[0] = true;

    for (int num : nums) {
        for (int s = target; s >= num; s--) {
            if (dp[s - num]) dp[s] = true;
        }
    }
    return dp[target];
}
// Time: O(n × sum/2) | Space: O(sum/2)
```

---

## Medium Level

### Q4. Number of Distinct Subsequences

**LeetCode 115**

```java
public int numDistinct(String s, String t) {
    /*
      Count distinct subsequences of s that equal t.
      Example: "rabbbit", "rabbit" -> 3
    */
    int m = s.length(), n = t.length();
    int[][] dp = new int[m + 1][n + 1];

    for (int i = 0; i <= m; i++) {
        dp[i][0] = 1;
    }

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (s.charAt(i - 1) == t.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            } else {
                dp[i][j] = dp[i - 1][j];
            }
        }
    }
    return dp[m][n];
}
// Time: O(m × n) | Space: O(m × n)
```

---

### Q5. Longest Palindromic Subsequence

**LeetCode 516**

```java
public int longestPalindromeSubseq(String s) {
    /*
      Find longest palindromic subsequence.
      Example: "bbbab" -> 4 ("bbbb")
    */
    int n = s.length();
    int[][] dp = new int[n][n];

    for (int i = 0; i < n; i++) {
        dp[i][i] = 1;
    }

    for (int length = 2; length <= n; length++) {
        for (int i = 0; i <= n - length; i++) {
            int j = i + length - 1;
            if (s.charAt(i) == s.charAt(j)) {
                dp[i][j] = (i + 1 <= j - 1) ? 2 + dp[i + 1][j - 1] : 2;
            } else {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
    }
    return dp[0][n - 1];
}
// Time: O(n²) | Space: O(n²)
```

---

### Q6. Edit Distance

**LeetCode 72 - Classic**

```java
public int minDistance(String word1, String word2) {
    /*
      Minimum edit operations (insert, delete, replace).
      Example: "horse", "ros" -> 3
    */
    int m = word1.length(), n = word2.length();
    int[][] dp = new int[m + 1][n + 1];

    for (int i = 0; i <= m; i++) {
        dp[i][0] = i;
    }
    for (int j = 0; j <= n; j++) {
        dp[0][j] = j;
    }

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1];
            } else {
                dp[i][j] = 1 + Math.min(
                    Math.min(dp[i - 1][j], dp[i][j - 1]),  // Delete or Insert
                    dp[i - 1][j - 1]  // Replace
                );
            }
        }
    }
    return dp[m][n];
}
// Time: O(m × n) | Space: O(m × n)
```

---

### Q7. Target Sum

**LeetCode 494**

```java
public int findTargetSumWays(int[] nums, int target) {
    /*
      Count ways to assign +/- to reach target sum.
      Example: [1,1,1,1,1], target=3 -> 5
    */
    int total = 0;
    for (int num : nums) total += num;

    if ((target + total) % 2 != 0 || Math.abs(target) > total) {
        return 0;
    }

    int subsetSum = (target + total) / 2;
    Map<String, Integer> memo = new HashMap<>();

    return countDP(nums, 0, subsetSum, memo);
}

private int countDP(int[] nums, int index, int remaining, Map<String, Integer> memo) {
    if (remaining == 0) return 1;
    if (index == nums.length) return 0;

    String key = index + "," + remaining;
    if (memo.containsKey(key)) return memo.get(key);

    int count = countDP(nums, index + 1, remaining - nums[index], memo) +
                countDP(nums, index + 1, remaining, memo);

    memo.put(key, count);
    return count;
}
// Time: O(n × subset_sum) | Space: O(n × subset_sum)
```

---

### Q8. Longest Increasing Subsequence - Get Actual Array

**LeetCode 300 (variation)**

```java
public int[] get_lis(int[] nums) {
    /*
      Get actual LIS array, not just length.
    */
    if (nums.length == 0) return new int[0];

    int n = nums.length;
    int[] dp = new int[n];
    int[] parent = new int[n];

    for (int i = 0; i < n; i++) {
        dp[i] = 1;
        parent[i] = -1;
    }

    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i] && dp[j] + 1 > dp[i]) {
                dp[i] = dp[j] + 1;
                parent[i] = j;
            }
        }
    }

    int maxLen = Arrays.stream(dp).max().orElse(0);
    int maxIdx = -1;
    for (int i = 0; i < n; i++) {
        if (dp[i] == maxLen) {
            maxIdx = i;
            break;
        }
    }

    int[] lis = new int[maxLen];
    int idx = maxLen - 1;
    while (maxIdx != -1) {
        lis[idx--] = nums[maxIdx];
        maxIdx = parent[maxIdx];
    }
    return lis;
}
// Time: O(n²) | Space: O(n)
```

---

## Hard Level

### Q9. Russian Doll Envelopes

**LeetCode 354**

```java
public int maxEnvelopes(int[][] envelopes) {
    /*
      Max nested envelopes (a,b) can fit in (c,d) if a<c and b<d.
      Example: [[2,100],[3,4],[4,5],[5,5],[6,4],[6,7],[6,8]]
      Returns: 5
    */
    if (envelopes.length == 0) return 0;

    // Sort: width ascending, height descending
    Arrays.sort(envelopes, (a, b) -> {
        if (a[0] != b[0]) return a[0] - b[0];
        return b[1] - a[1];  // Descending order for height
    });

    List<Integer> tails = new ArrayList<>();

    for (int[] envelope : envelopes) {
        int h = envelope[1];
        int pos = Collections.binarySearch(tails, h);
        if (pos < 0) pos = -(pos + 1);

        if (pos == tails.size()) {
            tails.add(h);
        } else {
            tails.set(pos, h);
        }
    }
    return tails.size();
}
// Time: O(n log n) | Space: O(n)
```

---

### Q10. Shortest Common Supersequence

**LeetCode 1092**

```java
public String shortestCommonSupersequence(String str1, String str2) {
    /*
      Shortest string containing both as subsequences.
      Example: "acb", "cab" -> "acab" or "cabac"
    */
    int m = str1.length(), n = str2.length();
    int[][] dp = new int[m + 1][n + 1];

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                dp[i][j] = 1 + dp[i - 1][j - 1];
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }

    StringBuilder result = new StringBuilder();
    int i = m, j = n;

    while (i > 0 || j > 0) {
        if (i == 0) {
            result.insert(0, str2.charAt(j - 1));
            j--;
        } else if (j == 0) {
            result.insert(0, str1.charAt(i - 1));
            i--;
        } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
            result.insert(0, str1.charAt(i - 1));
            i--;
            j--;
        } else if (dp[i - 1][j] > dp[i][j - 1]) {
            result.insert(0, str1.charAt(i - 1));
            i--;
        } else {
            result.insert(0, str2.charAt(j - 1));
            j--;
        }
    }
    return result.toString();
}
// Time: O(m × n) | Space: O(m × n)
```

---

### Q11. Count Different Palindromic Subsequences

**LeetCode 1930**

```java
public int countPalindromes(String s) {
    /*
      Count different palindromic subsequences.
      Example: "BCC" -> 4 (B, C, BB, CC)
    */
    final int MOD = 1_000_000_007;
    Map<String, Integer> memo = new HashMap<>();

    int result = dp(s, 0, s.length() - 1, memo, MOD);
    return result;
}

private int dp(String s, int left, int right, Map<String, Integer> memo, int MOD) {
    if (left > right) return 0;
    if (left == right) return 1;

    String key = left + "," + right;
    if (memo.containsKey(key)) return memo.get(key);

    long result = 0;

    if (s.charAt(left) == s.charAt(right)) {
        long inner = dp(s, left + 1, right - 1, memo, MOD);

        // Find first and last occurrence in inner range
        int lInner = left + 1, rInner = right - 1;
        while (lInner <= rInner && s.charAt(lInner) != s.charAt(left)) lInner++;
        while (lInner <= rInner && s.charAt(rInner) != s.charAt(right)) rInner--;

        if (lInner > rInner) {
            result = (inner * 2 + 2) % MOD;
        } else if (lInner == rInner) {
            result = (inner * 2 + 1) % MOD;
        } else {
            long innerDp = dp(s, lInner + 1, rInner - 1, memo, MOD);
            result = (inner * 2 - innerDp + MOD) % MOD;
        }
    } else {
        long left_dp = dp(s, left + 1, right, memo, MOD);
        long right_dp = dp(s, left, right - 1, memo, MOD);
        long both_dp = dp(s, left + 1, right - 1, memo, MOD);
        result = (left_dp + right_dp - both_dp + MOD) % MOD;
    }

    memo.put(key, (int)result);
    return (int)result;
}
// Time: O(n²) | Space: O(n²)
```

---

### Q12. Interleaving String

**LeetCode 97**

```java
public boolean isInterleave(String s1, String s2, String s3) {
    /*
      Check if s3 is interleaving of s1 and s2.
      Example: s1="aabcc", s2="dbbca", s3="aadbbcbcac" -> true
    */
    if (s1.length() + s2.length() != s3.length()) return false;

    int m = s1.length(), n = s2.length();
    boolean[][] dp = new boolean[m + 1][n + 1];
    dp[0][0] = true;

    // Initialize first row
    for (int i = 1; i <= m; i++) {
        dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
    }

    // Initialize first column
    for (int j = 1; j <= n; j++) {
        dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
    }

    // Fill DP table
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            boolean takeS1 = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
            boolean takeS2 = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
            dp[i][j] = takeS1 || takeS2;
        }
    }

    return dp[m][n];
}
// Time: O(m × n) | Space: O(m × n)
```

---

## Tricky & Edge Cases

### Q13: Subsequences with All Zeros and Ones

```java
public int countBinarySubstrings(String s) {
    /*
      Subsequences with matching 0s and 1s?
      Not really a subsequence problem, but pattern matching.
    */
    // This is usually a different type of problem
    return 0;  // depends on problem definition
}
```

### Q14: Duplicate Handling in Distinct Subsequences

```java
public int countDistinctSubseq(String s) {
    /*
      When duplicates exist: track last occurrence
    */
    int n = s.length();
    int[] dp = new int[n + 1];
    for (int i = 0; i <= n; i++) {
        dp[i] = 1;  // Start with empty
    }
    Map<Character, Integer> last = new HashMap<>();

    for (int i = 1; i <= n; i++) {
        dp[i] = dp[i - 1] * 2;
        if (last.containsKey(s.charAt(i - 1))) {
            dp[i] -= dp[last.get(s.charAt(i - 1))];
        }
        last.put(s.charAt(i - 1), i - 1);
    }

    return dp[n] - 1;
}
```

---

## Interview Preparation Checklist

### Before Interview

- [ ] Memorize O(n log n) LIS solution
- [ ] Understand 2D DP template
- [ ] Practice LCS and reconstruction
- [ ] Know subset sum variations
- [ ] Be comfortable with memoization

### During Interview

- [ ] Ask clarifying questions
- [ ] Start with brute force
- [ ] Optimize step by step
- [ ] Discuss time/space tradeoffs
- [ ] Write clean code
- [ ] Test edge cases

### After Interview

- [ ] Review your solution
- [ ] Learn from mistakes
- [ ] Practice similar problems

---

## Resources

**Must Know Patterns:**

1. LIS with binary search
2. LCS with 2D DP
3. Subset sum with 1D DP
4. Range DP for palindromes
5. Memoization for counting

**Practice Difficulty:**

- Easy: 300, 1143, 416
- Medium: 72, 115, 516, 494
- Hard: 354, 1092, 1930, 97

---

## Final Tips

✅ **Always:**

- Start with clear state definition
- Test on small examples
- Consider time/space complexity
- Handle edge cases

❌ **Never:**

- Use O(2ⁿ) for large n
- Forget to clear/restore state in backtracking
- Confuse subsequence and substring
- Miss memoization opportunities
