# Pattern 4: Longest Common Subsequence (LCS)

## Overview

LCS finds the longest subsequence common to two sequences. This is fundamental to string alignment, diff algorithms, and edit distance problems.

---

## Problem Statement

**Input:** Two strings/arrays s1 and s2  
**Output:** Length of longest common subsequence

### Examples

```
s1 = "abcde"
s2 = "ace"
Output: 3
LCS: "ace"

s1 = "oxcpqrsvwf"
s2 = "sxyspmqyhbt"
Output: 2
LCS: "qr" or similar

s1 = "abc"
s2 = "xyz"
Output: 0
LCS: "" (no common subsequence)
```

---

## Core Recurrence Relation

```
lcs(i, j) = LCS of s1[0...i-1] and s2[0...j-1]

If s1[i-1] == s2[j-1]:
    lcs(i, j) = 1 + lcs(i-1, j-1)
Else:
    lcs(i, j) = max(lcs(i-1, j), lcs(i, j-1))

Base Cases:
lcs(0, j) = 0 (empty s1)
lcs(i, 0) = 0 (empty s2)
```

---

## Solution 1: Dynamic Programming - 2D

```java
public int lcsLength(String s1, String s2) {
    /*
      Find length of LCS using 2D DP.
      Time: O(m × n) where m = len(s1), n = len(s2)
      Space: O(m × n)
    */
    int m = s1.length(), n = s2.length();
    int[][] dp = new int[m + 1][n + 1];

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                // Characters match
                dp[i][j] = 1 + dp[i - 1][j - 1];
            } else {
                // Characters don't match, take max
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }

    return dp[m][n];
}

// Example
// lcsLength("abcde", "ace") -> 3
// lcsLength("abc", "xyz") -> 0
// lcsLength("aggtab", "gxtxayb") -> 5 ("gtab")
```

### Walkthrough: "abcde" vs "ace"

```
        ""  a   c   e
    ""  0   0   0   0
    a   0   1   1   1
    b   0   1   1   1
    c   0   1   2   2
    d   0   1   2   2
    e   0   1   2   3
                    ↑
                  Answer: 3
```

**Explanation:**

- When `s1[0]='a'` matches `s2[0]='a'`: dp[1][1] = 1 + dp[0][0] = 1
- When `s1[2]='c'` matches `s2[1]='c'`: dp[3][2] = 1 + dp[2][1] = 2
- When `s1[4]='e'` matches `s2[2]='e'`: dp[5][3] = 1 + dp[4][2] = 3

---

## Solution 2: Space-Optimized 1D DP

```java
public int lcsLength1D(String s1, String s2) {
    /*
      Find LCS length using only 1D array.
      Time: O(m × n), Space: O(min(m, n))
    */
    if (s1.length() < s2.length()) {
        String temp = s1;
        s1 = s2;
        s2 = temp;  // Make s1 the longer string
    }

    int m = s1.length(), n = s2.length();
    int[] prev = new int[n + 1];
    int[] curr = new int[n + 1];

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                curr[j] = 1 + prev[j - 1];
            } else {
                curr[j] = Math.max(prev[j], curr[j - 1]);
            }
        }
        int[] temp = prev;
        prev = curr;
        curr = temp;  // Swap rows
    }

    return prev[n];
}

// Example
// lcsLength1D("abcde", "ace") -> 3
```

---

## Get Actual LCS String

### Method 1: Backtracking Through DP Table

```java
public String getLCS(String s1, String s2) {
    /*
      Get actual LCS string (not just length).
    */
    int m = s1.length(), n = s2.length();
    int[][] dp = new int[m + 1][n + 1];

    // Fill DP table
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                dp[i][j] = 1 + dp[i - 1][j - 1];
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }

    // Reconstruct LCS
    StringBuilder lcs = new StringBuilder();
    int i = m, j = n;

    while (i > 0 && j > 0) {
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            // Character is part of LCS
            lcs.insert(0, s1.charAt(i - 1));
            i--;
            j--;
        } else if (dp[i - 1][j] > dp[i][j - 1]) {
            // Move up in s1
            i--;
        } else {
            // Move left in s2
            j--;
        }
    }

    return lcs.toString();
}

// Example
// getLCS("abcde", "ace") -> "ace"
// getLCS("aggtab", "gxtxayb") -> "gtab"
```

### Reconstruction Walkthrough

```
DP Table for "abcde" vs "ace":
        ""  a   c   e
    ""  0   0   0   0
    a   0   1   1   1
    b   0   1   1   1
    c   0   1   2   2
    d   0   1   2   2
    e   0   1   2   3
                    ↑
Start at (5, 3)

i=5, j=3: s1[4]='e' == s2[2]='e' -> lcs = "e", i=4, j=2
i=4, j=2: s1[3]='d' != s2[1]='c', dp[3][2]=2 > dp[4][1]=1 -> i=3
i=3, j=2: s1[2]='c' == s2[1]='c' -> lcs = "ce", i=2, j=1
i=2, j=1: s1[1]='b' != s2[0]='a', dp[1][1]=1 = dp[2][0]=0 -> j=0
i=2, j=0: Loop ends

Result: "ace"
```

---

## Related Problems

### 1. Longest Common Substring

**Difference:** Must be contiguous (consecutive positions)

```java
public int lcsSubstring(String s1, String s2) {
    /*
      Find length of longest common substring (contiguous).
    */
    int m = s1.length(), n = s2.length();
    int[][] dp = new int[m + 1][n + 1];
    int maxLength = 0;

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                dp[i][j] = 1 + dp[i - 1][j - 1];
                maxLength = Math.max(maxLength, dp[i][j]);
            } else {
                dp[i][j] = 0;  // Reset if no match (contiguity broken)
            }
        }
    }

    return maxLength;
}

// Example: "abcde" and "cde" -> 3 ("cde")
```

### 2. Edit Distance (Levenshtein Distance)

```java
public int editDistance(String s1, String s2) {
    /*
      Minimum operations (insert, delete, replace) to transform s1 to s2.
      Related to LCS: distance = m + n - 2*lcs_length
    */
    int m = s1.length(), n = s2.length();
    int[][] dp = new int[m + 1][n + 1];

    // Base cases
    for (int i = 0; i <= m; i++) {
        dp[i][0] = i;  // Delete all from s1
    }
    for (int j = 0; j <= n; j++) {
        dp[0][j] = j;  // Insert all from s2
    }

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1];  // No operation needed
            } else {
                dp[i][j] = 1 + Math.min(
                    dp[i - 1][j],      // Delete from s1
                    Math.min(
                        dp[i][j - 1],      // Insert into s1
                        dp[i - 1][j - 1]   // Replace
                    )
                );
            }
        }
    }

    return dp[m][n];
}

// Example
// editDistance("horse", "ros") -> 3
```

### 3. Shortest Common Supersequence

```java
public String shortestCommonSuperseq(String s1, String s2) {
    /*
      Shortest string that contains both s1 and s2 as subsequences.
    */
    int m = s1.length(), n = s2.length();
    int[][] dp = new int[m + 1][n + 1];

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                dp[i][j] = 1 + dp[i - 1][j - 1];
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }

    // Reconstruct supersequence
    StringBuilder result = new StringBuilder();
    int i = m, j = n;

    while (i > 0 || j > 0) {
        if (i == 0) {
            result.insert(0, s2.charAt(j - 1));
            j--;
        } else if (j == 0) {
            result.insert(0, s1.charAt(i - 1));
            i--;
        } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            result.insert(0, s1.charAt(i - 1));
            i--;
            j--;
        } else if (dp[i - 1][j] > dp[i][j - 1]) {
            result.insert(0, s1.charAt(i - 1));
            i--;
        } else {
            result.insert(0, s2.charAt(j - 1));
            j--;
        }
    }

    return result.toString();
}

// Example: "acb", "cab" -> "acab" (supersequence)
```

### 4. Number of LCS

```java
public int countLCS(String s1, String s2) {
    /*
      Count number of different LCS of maximum length.
    */
    int m = s1.length(), n = s2.length();
    int[][] dp = new int[m + 1][n + 1];
    int[][] count = new int[m + 1][n + 1];

    for (int i = 0; i <= m; i++) {
        count[i][0] = 1;
    }
    for (int j = 0; j <= n; j++) {
        count[0][j] = 1;
    }

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                dp[i][j] = 1 + dp[i - 1][j - 1];
                count[i][j] = count[i - 1][j - 1];
            } else {
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    dp[i][j] = dp[i - 1][j];
                    count[i][j] = count[i - 1][j];
                } else if (dp[i - 1][j] < dp[i][j - 1]) {
                    dp[i][j] = dp[i][j - 1];
                    count[i][j] = count[i][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j];
                    count[i][j] = count[i - 1][j] + count[i][j - 1];
                }
            }
        }
    }

    return count[m][n];
}
```

### 5. Distinct Subsequences (LeetCode 115)

```java
public int distinctSubsequences(String s, String t) {
    /*
      Count distinct subsequences of s that equal t.
    */
    int m = s.length(), n = t.length();
    // dp[i][j] = number of distinct subseq of s[0...i-1] that match t[0...j-1]
    int[][] dp = new int[m + 1][n + 1];

    // Empty t is always a subsequence (empty string)
    for (int i = 0; i <= m; i++) {
        dp[i][0] = 1;
    }

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (s.charAt(i - 1) == t.charAt(j - 1)) {
                // Either use this char or don't
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            } else {
                // Can't use this char
                dp[i][j] = dp[i - 1][j];
            }
        }
    }

    return dp[m][n];
}

// Example: "rabbbit", "rabbit" -> 3
```

---

## Complexity Analysis

| Problem         | Time   | Space       | Notes               |
| --------------- | ------ | ----------- | ------------------- |
| LCS Length      | O(m×n) | O(m×n)      | Standard DP         |
| LCS Length (1D) | O(m×n) | O(min(m,n)) | Space optimized     |
| Get LCS         | O(m×n) | O(m×n)      | Reconstruction      |
| LCS Substring   | O(m×n) | O(m×n)      | Contiguous required |
| Edit Distance   | O(m×n) | O(m×n)      | Related problem     |

---

## Memoization Alternative

```java
public int lcsMemo(String s1, String s2) {
    /*
      Top-down DP with memoization.
    */
    Map<String, Integer> memo = new HashMap<>();
    return dp(0, 0, s1, s2, memo);
}

private int dp(int i, int j, String s1, String s2, Map<String, Integer> memo) {
    if (i == s1.length() || j == s2.length()) {
        return 0;
    }

    String key = i + "," + j;
    if (memo.containsKey(key)) {
        return memo.get(key);
    }

    int result;
    if (s1.charAt(i) == s2.charAt(j)) {
        result = 1 + dp(i + 1, j + 1, s1, s2, memo);
    } else {
        result = Math.max(dp(i + 1, j, s1, s2, memo), dp(i, j + 1, s1, s2, memo));
    }

    memo.put(key, result);
    return result;
}
```

---

## Applications

1. **Diff Tools** - Compare files to find changes
2. **DNA Sequence** - Align biological sequences
3. **Plagiarism Detection** - Find common passages
4. **Version Control** - Merge algorithms
5. **Spell Correction** - Edit distance
6. **String Matching** - Pattern matching with gaps

---

## Interview Tips

✅ **Do:**

- Explain recurrence clearly
- Draw DP table for examples
- Discuss reconstruction
- Consider space optimization

❌ **Don't:**

- Confuse with LCS substring
- Use 2D when 1D sufficient
- Forget reconstruction tracking
- Miss edge cases

---

## Visualization

```
"AGGTAB" vs "GXTXAYB"

        ""  G   X   T   X   A   Y   B
    ""  0   0   0   0   0   0   0   0
    A   0   0   0   0   0   1   1   1
    G   0   1   1   1   1   1   1   1
    G   0   1   1   1   1   1   1   1
    T   0   1   1   2   2   2   2   2
    A   0   1   1   2   2   3   3   3
    B   0   1   1   2   2   3   3   4
                                    ↑
                                Answer: 4 ("GTAB")
```

---

## Key Takeaways

1. **LCS is fundamental** - basis for many algorithms
2. **2D DP clear** - easier to understand
3. **1D DP optimized** - saves space
4. **Reconstruction possible** - track path through table
5. **Many variants** - substring, edit distance, etc.
6. **Applications vast** - diff, alignment, plagiarism
