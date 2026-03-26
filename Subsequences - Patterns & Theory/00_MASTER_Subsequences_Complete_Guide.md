# Master Guide: All Subsequence Patterns & Theory

## Table of Contents

1. [Fundamentals](#fundamentals)
2. [Core Concepts](#core-concepts)
3. [Pattern Categories](#pattern-categories)
4. [Common Problems & Solutions](#common-problems--solutions)
5. [Complexity Analysis](#complexity-analysis)
6. [Implementation Templates](#implementation-templates)
7. [Tips & Tricks](#tips--tricks)

---

## Fundamentals

### What is a Subsequence?

A **subsequence** is a sequence derived from another sequence by deleting some or no elements **without changing the order** of the remaining elements.

**Key Points:**

- Order must be preserved
- Elements don't need to be contiguous
- Can skip any number of elements
- Different from substrings (which are contiguous)

### Examples

```
String: "abcde"

Subsequences:
- "a", "b", "c", "d", "e" (single char)
- "ab", "ac", "ad", "ae", "bc", "bd", "be", "cd", "ce", "de" (two chars)
- "abc", "abd", "abe", "acd", "ace", "ade", "bcd", "bce", "bde", "cde" (three chars)
- ... and so on

NOT a subsequence: "ba" (order is wrong), "ae" (skipped 2 letters but order matters)
Subsequence: "ace" (skipped b and d, but order preserved)
```

---

## Core Concepts

### 1. Total Number of Subsequences

For a string of length **n**, the total number of subsequences is **2ⁿ** (including empty).

**Explanation:**

- Each character has 2 choices: include or exclude
- Total combinations = 2 × 2 × 2 × ... (n times) = 2ⁿ
- This includes the empty subsequence
- Non-empty subsequences = 2ⁿ - 1

**Example:** For "abc" (n=3):

- Total = 2³ = 8 subsequences
- "", "a", "b", "c", "ab", "ac", "bc", "abc"
- Non-empty = 7

### 2. Subsequence vs Substring vs Subarray

| Aspect         | Subsequence        | Substring          | Subarray               |
| -------------- | ------------------ | ------------------ | ---------------------- |
| **Contiguous** | No                 | Yes                | Yes                    |
| **Order**      | Must maintain      | Must maintain      | Must maintain          |
| **Count**      | 2ⁿ                 | n(n+1)/2           | n(n+1)/2               |
| **Example**    | "ace" from "abcde" | "bcd" from "abcde" | [1,2,3] from [1,2,3,4] |
| **Gaps**       | Allowed            | Not allowed        | Not allowed            |

### 3. How to Think About Subsequences

Two main approaches:

**Approach 1: Pick or Not Pick**

```
For each element:
  - Option 1: Include it in the subsequence
  - Option 2: Exclude it from the subsequence
```

**Approach 2: Relative Order**

```
No matter which elements you select,
their relative order from the original sequence must be maintained
```

---

## Pattern Categories

### Pattern 1: Generate All Subsequences

**Template:**

```
def generate_subsequences(arr):
    result = []

    def backtrack(index, current):
        if index == len(arr):
            result.append(current[:])  # Add a copy
            return

        # Include current element
        current.append(arr[index])
        backtrack(index + 1, current)
        current.pop()

        # Exclude current element
        backtrack(index + 1, current)

    backtrack(0, [])
    return result
```

**Time Complexity:** O(2ⁿ)  
**Space Complexity:** O(n) for recursion stack

**Problems:**

- Generate all subsequences
- Print all subsequences string
- Generate all subsets (Leetcode 78)

---

### Pattern 2: Subsequences with Constraint

**Template (Sum = K):**

```
def subsequences_with_sum(arr, target):
    result = []

    def backtrack(index, current, current_sum):
        if current_sum == target:
            result.append(current[:])
            return

        if index == len(arr) or current_sum > target:
            return

        # Include
        current.append(arr[index])
        backtrack(index + 1, current, current_sum + arr[index])
        current.pop()

        # Exclude
        backtrack(index + 1, current, current_sum)

    backtrack(0, [], 0)
    return result
```

**Time Complexity:** O(2ⁿ)  
**Space Complexity:** O(n)

**Problems:**

- Combination Sum I, II, III, IV
- Partition to K Equal Sum Subsets
- Permutations
- Target Sum (LeetCode 494)

---

### Pattern 3: Longest Increasing Subsequence (LIS)

**Problem:** Find the longest subsequence in an array where elements are in strictly increasing order.

**DP Solution:**

```
dp[i] = length of LIS ending at index i

dp[i] = 1 + max(dp[j]) for all j < i where arr[j] < arr[i]
       = 1 if no such j exists
```

**Time Complexity:** O(n²) or O(n log n) with binary search  
**Space Complexity:** O(n)

**Related Problems:**

- Longest Increasing Subsequence (LeetCode 300)
- Russian Doll Envelopes
- Increasing Triplet Subsequence
- Largest Divisible Subset
- Longest Bitonic Subsequence

---

### Pattern 4: Longest Common Subsequence (LCS)

**Problem:** Find the longest subsequence common to two sequences.

**DP Solution:**

```
dp[i][j] = LCS length of s1[0...i-1] and s2[0...j-1]

if s1[i-1] == s2[j-1]:
    dp[i][j] = 1 + dp[i-1][j-1]
else:
    dp[i][j] = max(dp[i-1][j], dp[i][j-1])
```

**Time Complexity:** O(m × n)  
**Space Complexity:** O(m × n)

**Related Problems:**

- Longest Common Subsequence (LeetCode 1143)
- Edit Distance (LeetCode 72)
- Shortest Common Supersequence
- Distinct Subsequences (LeetCode 115)
- Interleaving String (LeetCode 97)

---

### Pattern 5: Count Subsequences

**Problem:** Count the number of subsequences satisfying a condition.

**Template:**

```
def count_subsequences(arr, target):
    memo = {}

    def backtrack(index, current_sum):
        if index == len(arr):
            return 1 if current_sum == target else 0

        if (index, current_sum) in memo:
            return memo[(index, current_sum)]

        # Include
        include = backtrack(index + 1, current_sum + arr[index])

        # Exclude
        exclude = backtrack(index + 1, current_sum)

        memo[(index, current_sum)] = include + exclude
        return memo[(index, current_sum)]

    return backtrack(0, 0)
```

**Time Complexity:** O(n × sum)  
**Space Complexity:** O(n × sum)

**Related Problems:**

- Count Subsequences with Sum K
- Number of Distinct Subsequences
- Distinct Subsequences II
- Target Sum (count ways)

---

### Pattern 6: Palindromic Subsequences

**Problem:** Find or count palindromic subsequences.

**Key Insight:**

- A palindrome reads the same forwards and backwards
- Approach: Use DP with indices i and j representing start and end

**DP Template:**

```
dp[i][j] = true if s[i...j] is a palindromic subsequence

if s[i] == s[j]:
    dp[i][j] = dp[i+1][j-1] or true (if i+1 > j-1)
else:
    dp[i][j] = dp[i+1][j] or dp[i][j-1]
```

**Related Problems:**

- Longest Palindromic Subsequence
- Palindromic Subsequences
- Count Different Palindromic Subsequences
- Minimum Deletions to Make Palindrome

---

### Pattern 7: Bitmasking on Subsequences

**Approach:** Use bitmask to represent which elements are included.

```
for mask in range(2^n):
    current_subsequence = []
    for i in range(n):
        if mask & (1 << i):
            current_subsequence.append(arr[i])
    # Process current_subsequence
```

**Time Complexity:** O(2ⁿ × n)  
**Space Complexity:** O(1) if not storing results

**When to use:**

- When n is small (≤ 20)
- Need all subsequences with specific properties
- Optimization problems on subsets

---

### Pattern 8: DP on Subsequences (Advanced)

**Common Variants:**

1. **Minimum/Maximum operations to achieve a subsequence**
2. **Number of ways to form a specific subsequence**
3. **Longest subsequence with property X**

**Template:**

```
dp[i] = some property of subsequence ending at i

For each i:
    For each j < i:
        if condition(arr[j], arr[i]):
            dp[i] = update(dp[i], dp[j])
```

**Related Problems:**

- Longest Increasing Subsequence
- Jump Game II
- House Robber
- Largest Sum of Subarray
- Weighted Job Scheduling

---

## Common Problems & Solutions

### Problem 1: Generate All Subsequences

**Statement:** Generate all non-empty subsequences of a given array.

**Solution - Recursive Backtracking:**

```java
public List<List<Integer>> generateSubsequences(int[] arr) {
    List<List<Integer>> result = new ArrayList<>();
    helper(arr, 0, new ArrayList<>(), result);
    return result;
}

private void helper(int[] arr, int index, List<Integer> current, List<List<Integer>> result) {
    if (index == arr.length) {
        if (!current.isEmpty()) {
            result.add(new ArrayList<>(current));
        }
        return;
    }

    // Include arr[index]
    current.add(arr[index]);
    helper(arr, index + 1, current, result);
    current.remove(current.size() - 1);

    // Exclude arr[index]
    helper(arr, index + 1, current, result);
}

// Example
// generateSubsequences({1, 2, 3})
// Output: [[1], [1,2], [1,2,3], [1,3], [2], [2,3], [3]]
```

---

### Problem 2: Subsequence with Sum = K

**Statement:** Find if there exists a subsequence with sum equal to K.

**Solution - DP:**

```java
public boolean isSubsequenceSumK(int[] arr, int k) {
    int n = arr.length;
    // dp[i][j] = true if sum j is possible using arr[0...i-1]
    boolean[][] dp = new boolean[n + 1][k + 1];

    // Sum 0 is always possible (empty subsequence)
    for (int i = 0; i <= n; i++) {
        dp[i][0] = true;
    }

    for (int i = 1; i <= n; i++) {
        for (int j = 0; j <= k; j++) {
            // Exclude arr[i-1]
            dp[i][j] = dp[i - 1][j];

            // Include arr[i-1]
            if (j >= arr[i - 1]) {
                dp[i][j] = dp[i][j] || dp[i - 1][j - arr[i - 1]];
            }
        }
    }

    return dp[n][k];
}

// Example
// isSubsequenceSumK({3, 2, 5, 6}, 15) -> true (3+5+6 or 2+6+5)
```

---

### Problem 3: Longest Increasing Subsequence

**Statement:** Find the length of the longest increasing subsequence.

**Solution 1 - O(n²) DP:**

```java
public int lengthOfLIS(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    for (int i = 0; i < n; i++) dp[i] = 1;

    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
    }

    int max = 0;
    for (int val : dp) max = Math.max(max, val);
    return max;
}
```

**Solution 2 - O(n log n) Binary Search:**

```java
public int lengthOfLIS(int[] nums) {
    List<Integer> lis = new ArrayList<>();
    for (int num : nums) {
        int pos = Collections.binarySearch(lis, num);
        if (pos < 0) pos = -(pos + 1);
        if (pos == lis.size()) {
            lis.add(num);
        } else {
            lis.set(pos, num);
        }
    }
    return lis.size();
}
```

---

### Problem 4: Longest Common Subsequence

**Statement:** Find the length of LCS of two strings.

**Solution:**

```java
public int longestCommonSubsequence(String text1, String text2) {
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

// Example
// longestCommonSubsequence("ace", "abcde") -> 3 ("ace")
```

---

### Problem 5: Number of Distinct Subsequences

**Statement:** Count the number of distinct non-empty subsequences.

**Solution:**

```java
public long distinctSubsequences(String s) {
    int n = s.length();
    long[] dp = new long[n + 1];
    dp[0] = 1; // Empty subsequence

    HashMap<Character, Integer> last = new HashMap<>();

    for (int i = 1; i <= n; i++) {
        dp[i] = dp[i - 1] * 2; // Include or exclude s[i-1]

        // If s[i-1] seen before at position j,
        // we subtract dp[j] to avoid duplicates
        if (last.containsKey(s.charAt(i - 1))) {
            dp[i] -= dp[last.get(s.charAt(i - 1))];
        }

        last.put(s.charAt(i - 1), i - 1);
    }

    return dp[n] - 1; // Exclude empty
}

// Example
// distinctSubsequences("ab") -> 3 ("a", "b", "ab")
// distinctSubsequences("aab") -> 4 ("a", "aa", "ab", "aab")
```

---

### Problem 6: Longest Palindromic Subsequence

**Statement:** Find the length of the longest palindromic subsequence.

**Solution 1 - Using LCS:**

```java
public int longestPalindromeSubseq(String s) {
    // LPS = LCS(s, reverse(s))
    String reversedS = new StringBuilder(s).reverse().toString();
    int m = s.length(), n = reversedS.length();
    int[][] dp = new int[m + 1][n + 1];

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (s.charAt(i - 1) == reversedS.charAt(j - 1)) {
                dp[i][j] = 1 + dp[i - 1][j - 1];
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }

    return dp[m][n];
}
```

**Solution 2 - DP with Range:**

```java
public int longestPalindromeSubseq(String s) {
    int n = s.length();
    int[][] dp = new int[n][n];

    // Every single character is palindrome length 1
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

    return dp[0][n - 1];
}
```

---

## Complexity Analysis

### Time & Space Complexities Chart

| Problem Type              | Time       | Space    | Notes                             |
| ------------------------- | ---------- | -------- | --------------------------------- |
| Generate All Subsequences | O(2ⁿ)      | O(n)     | Exponential growth                |
| Subsequence Sum (DP)      | O(n×sum)   | O(n×sum) | Pseudo-polynomial                 |
| LIS (DP)                  | O(n²)      | O(n)     | Optimizable to O(n log n)         |
| LIS (Binary Search)       | O(n log n) | O(n)     | Best for LIS                      |
| LCS                       | O(m×n)     | O(m×n)   | Can optimize space to O(min(m,n)) |
| Palindrome Subsequence    | O(n²)      | O(n²)    | Range DP                          |
| Count Distinct Subseq     | O(n)       | O(1)     | With last occurrence map          |
| Bitmasking                | O(2ⁿ×n)    | O(1)     | For small n only                  |

---

## Implementation Templates

### Template 1: Basic Backtracking

```java
public List<List<Integer>> backtrackTemplate(int[] arr) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(0, arr, new ArrayList<>(), result);
    return result;
}

private void backtrack(int index, int[] arr, List<Integer> current, List<List<Integer>> result) {
    // Base case
    if (index == arr.length) {
        // Add to result if valid
        result.add(new ArrayList<>(current));
        return;
    }

    // Choice 1: Include current element
    current.add(arr[index]);
    backtrack(index + 1, arr, current, result);
    current.remove(current.size() - 1);

    // Choice 2: Exclude current element
    backtrack(index + 1, arr, current, result);
}
```

### Template 2: DP with Memoization

```java
public boolean dpMemoTemplate(int[] arr, int target) {
    Map<String, Boolean> memo = new HashMap<>();
    return dp(0, target, arr, memo);
}

private boolean dp(int index, int remaining, int[] arr, Map<String, Boolean> memo) {
    // Base case
    if (remaining == 0) return true;
    if (index == arr.length || remaining < 0) return false;

    // Check memo
    String key = index + "," + remaining;
    if (memo.containsKey(key)) {
        return memo.get(key);
    }

    // Include or Exclude
    boolean result = dp(index + 1, remaining - arr[index], arr, memo) ||
                     dp(index + 1, remaining, arr, memo);

    memo.put(key, result);
    return result;
}
```

### Template 3: Iterative DP

```java
public boolean iterativeDPTemplate(int[] arr, int target) {
    int n = arr.length;
    // dp[i][j] = some property
    boolean[][] dp = new boolean[n + 1][target + 1];

    // Initialize base case
    for (int i = 0; i <= n; i++) {
        dp[i][0] = true;
    }

    // Fill DP table
    for (int i = 1; i <= n; i++) {
        for (int j = 0; j <= target; j++) {
            // Transition
            dp[i][j] = dp[i - 1][j];  // Exclude
            if (j >= arr[i - 1]) {
                dp[i][j] = dp[i][j] || dp[i - 1][j - arr[i - 1]];  // Include
            }
        }
    }

    return dp[n][target];
}
```

### Template 4: Range DP (Palindrome Problems)

```java
public int rangeDP(String s) {
    int n = s.length();
    int[][] dp = new int[n][n];

    // Base case: single characters
    for (int i = 0; i < n; i++) {
        dp[i][i] = 1;
    }

    // Fill for increasing lengths
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

    return dp[0][n - 1];
}
```

---

## Tips & Tricks

### 1. Choosing the Right Approach

**Use Backtracking when:**

- You need to generate all subsequences
- n is small (≤ 20)
- You need to explore all possibilities
- Constraints on elements matter

**Use DP when:**

- You need to count or find optimal subsequences
- Overlapping subproblems exist
- State transitions are clear
- Need to optimize for large n

**Use Binary Search when:**

- Finding LIS and n is large
- Need O(n log n) solution
- Working with sorted sequences

### 2. Common Mistakes

❌ **Mistake 1:** Forgetting to maintain order

```java
// WRONG: Can lose order
List<int[]> perms = getPermutations(arr);
// This gives permutations, not subsequences
```

✅ **Correct:** Always respect original order

```java
// RIGHT: Order preserved by index progression
for (int i = index + 1; i < n; i++) {
    // Only look ahead
}
```

❌ **Mistake 2:** Not handling duplicates in subsequences

```java
// WRONG: Might count same subsequence multiple times
if (arr[i] == arr[i+1]) {
    // Need special handling
}
```

✅ **Correct:** Track last occurrence

```java
Map<Character, Integer> last = new HashMap<>();
if (last.containsKey(ch)) {
    subtract += dp[last.get(ch)];
}
last.put(ch, current_pos);
```

❌ **Mistake 3:** Exponential complexity when n is large

```java
// WRONG: Will TLE for n > 25
if (backtrackOnLargeN && n == 1000) {
    // 2^1000 iterations!
}
```

✅ **Correct:** Use DP for large n

```java
// RIGHT: O(n²) or O(n log n)
if (useDPOnLargeN) {
    // Polynomial time complexity
}
```

### 3. Optimization Techniques

**Pruning:** Stop exploring invalid branches early

```java
if (currentSum > target) {
    return;  // No point continuing
}
```

**Memoization:** Store intermediate results

```java
if (memo.containsKey(state)) {
    return memo.get(state);
}
```

**Space Optimization:** Use 1D DP instead of 2D when possible

```java
// Instead of dp[i][j], use rolling array or 1D
int[] dp = new int[target + 1];
for (int i = 0; i < n; i++) {
    for (int j = target; j >= arr[i]; j--) {
        dp[j] = Math.max(dp[j], dp[j - arr[i]] + arr[i]);
    }
}
```

---

## Key Takeaways

1. **Subsequences** preserve order but not contiguity
2. **2ⁿ subsequences** exist for n elements
3. **Pick or Not Pick** is the fundamental recursion pattern
4. **DP + Memoization** handles large n efficiently
5. **LIS** needs O(n log n) binary search for optimal solution
6. **LCS** is used in many string problems
7. **Backtracking** best for generation when n is small
8. **Range DP** works well for palindrome variants

---

## Practice Problems by Difficulty

### Easy

- Generate All Subsequences
- Longest Increasing Subsequence
- Number of Distinct Subsequences (simple variant)

### Medium

- Subsequence with Sum K
- Longest Common Subsequence
- Longest Palindromic Subsequence
- Distinct Subsequences
- Count Different Palindromic Subsequences

### Hard

- Distinct Subsequences II
- Number of Longest Increasing Subsequence
- Shortest Common Supersequence
- Russian Doll Envelopes
- Weighted Job Scheduling

---

## Final Checklist

When solving subsequence problems:

- [ ] Identify if "pick or not pick" applies
- [ ] Check if it's a generation or optimization problem
- [ ] Verify n size and choose approach accordingly
- [ ] Consider if overlapping subproblems exist
- [ ] Plan your DP state clearly
- [ ] Test with small examples first
- [ ] Verify time and space complexity
- [ ] Handle edge cases (empty, single element, etc.)
- [ ] Check for duplicate subsequences
- [ ] Optimize if possible (BinSearch, rolling array, etc.)
