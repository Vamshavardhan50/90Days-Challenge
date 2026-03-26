# Quick Reference Guide: Subsequence Patterns

## Pattern Quick Lookup

### Pattern 1: Generate All Subsequences

**When to Use:** n ≤ 20, need all possible subsequences  
**Approach:** Backtracking with pick/not-pick  
**Time:** O(2ⁿ) | **Space:** O(n)

```java
List<List<Integer>> result = new ArrayList<>();

private void backtrack(int[] arr, int index, List<Integer> current) {
    if (index == arr.length) {
        if (!current.isEmpty()) {
            result.add(new ArrayList<>(current));
        }
        return;
    }
    // Pick
    current.add(arr[index]);
    backtrack(arr, index + 1, current);
    current.remove(current.size() - 1);
    // Not pick
    backtrack(arr, index + 1, current);
}

// Call: backtrack(arr, 0, new ArrayList<>());
```

**LeetCode:** 78 (Subsets), 90 (Subsets II)

---

### Pattern 2: Subsequence with Constraint (Sum = K)

**When to Use:** Find subsequences meeting criteria  
**Approach:** DP with state (index, remaining_sum)  
**Time:** O(n × target) | **Space:** O(n × target)

```java
boolean dp(int[] arr, int index, int remaining) {
    if (remaining == 0) return true;
    if (index == arr.length) return false;
    // Include or exclude
    return dp(arr, index + 1, remaining - arr[index]) || dp(arr, index + 1, remaining);
}
```

**LeetCode:** 416 (Partition), 494 (Target Sum)

---

### Pattern 3: Longest Increasing Subsequence (LIS)

**When to Use:** Find longest increasing sequence  
**Approach 1:** O(n²) DP, **Approach 2:** O(n log n) Binary Search  
**Best:** Binary search with bisect

```java
public int lengthOfLIS(int[] nums) {
    List<Integer> tails = new ArrayList<>();

    for (int num : nums) {
        int pos = Collections.binarySearch(tails, num);
        if (pos < 0) pos = -(pos + 1);  // Convert to insertion point

        if (pos == tails.size()) {
            tails.add(num);
        } else {
            tails.set(pos, num);
        }
    }
    return tails.size();
}
```

**LeetCode:** 300 (LIS), 673 (Number of LIS), 1157 (Russian Doll)

---

### Pattern 4: Longest Common Subsequence (LCS)

**When to Use:** Compare two sequences, alignment problems  
**Approach:** 2D DP with character matching  
**Time:** O(m × n) | **Space:** O(m × n) or O(min(m,n)) optimized

```java
public int lcsLength(String s1, String s2) {
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
    return dp[m][n];
}
```

**LeetCode:** 1143 (LCS), 72 (Edit Distance), 1092 (Shortest Supersequence)

---

### Pattern 5: Count Subsequences

**When to Use:** Count ways to achieve something  
**Approach:** Memoization with state (index, constraint)  
**Time:** O(n × constraint) | **Space:** O(n × constraint)

```java
Map<String, Integer> memo = new HashMap<>();

public int countDP(int[] arr, int index, int remaining) {
    if (remaining == 0) return 1;
    if (index == arr.length) return 0;

    String key = index + "," + remaining;
    if (memo.containsKey(key)) return memo.get(key);

    int result = countDP(arr, index + 1, remaining - arr[index]) +
                 countDP(arr, index + 1, remaining);
    memo.put(key, result);
    return result;
}
```

**LeetCode:** 115 (Distinct Subsequences), 494 (Target Sum count)

---

### Pattern 6: Palindromic Subsequences

**When to Use:** Find palindromic patterns  
**Approach:** Range DP [i][j] = LPS of s[i..j]  
**Time:** O(n²) | **Space:** O(n²)

```java
public int lps(String s) {
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
```

**LeetCode:** 516 (Longest Palindromic Subseq), 1216 (Count Different Palindromic)

---

## When to Use Each Approach

| Goal              | Use                         | Time                | Space         |
| ----------------- | --------------------------- | ------------------- | ------------- |
| Generate all      | Backtracking                | O(2ⁿ)               | O(n)          |
| Check existence   | DP 1D                       | O(n×k)              | O(k)          |
| Count ways        | Memoization                 | O(n×k)              | O(n×k)        |
| Find optimal      | DP                          | O(n²) or O(n log n) | O(n) or O(n²) |
| Get actual result | Backtracking/Reconstruction | -                   | -             |
| Range queries     | Range DP                    | O(n²)               | O(n²)         |

---

## Template: DP State Definition

### Bottom-Up DP Template

```java
// 1. Define state
int[] dp = new int[n];  // or int[][]dp for 2D range

// 2. Initialize base cases
dp[0] = baseValue;

// 3. Transition
for (int i = 1; i < n; i++) {  // or nested loops for 2D
    for (int j = 0; j < i; j++) {
        if (condition) {
            dp[i] = Math.max(dp[i], update(dp[j]));
        }
    }
}

// 4. Return
return dp[n - 1];
```

### Top-Down DP (Memoization) Template

```java
Map<String, Integer> memo = new HashMap<>();

public int dp(String state) {
    if (memo.containsKey(state)) return memo.get(state);

    if (isBaseCase()) return baseValue;

    int result = combinationOfSubproblems();
    memo.put(state, result);
    return result;
}

return dp(initialState);
```

---

## Complexity Cheat Sheet

| Problem Type | Time       | Space  | When          |
| ------------ | ---------- | ------ | ------------- |
| Generate All | O(2ⁿ)      | O(n)   | n ≤ 20        |
| Subset Sum   | O(n×sum)   | O(sum) | 1D optimized  |
| LIS          | O(n log n) | O(n)   | Binary search |
| LCS          | O(m×n)     | O(m×n) | 2D DP         |
| Palindrome   | O(n²)      | O(n²)  | Range DP      |
| Count        | O(n×k)     | O(n×k) | Depends on k  |

---

## Common Mistakes & Fixes

| Mistake                         | Problem                | Fix                           |
| ------------------------------- | ---------------------- | ----------------------------- |
| Confuse subsequence/substring   | String problems        | Check if contiguous           |
| Use O(2ⁿ) for large n           | Will TLE               | Use DP instead                |
| Traverse left-to-right in 1D DP | Use same element twice | Traverse right-to-left        |
| Forget base cases               | Wrong answer           | Initialize properly           |
| Over-count duplicates           | Wrong count            | Track last occurrence         |
| Miss backtracking               | Wrong reconstruction   | Restore state after recursion |

---

## State Design Decisions

### For Array Problems

```
dp[i] = property of arr[0..i]
        Include current element?
        Best value ending at i?
```

### For String/Subsequence

```
dp[i][j] = property of s[i..j]
           Characters match?
           What about inner region?
```

### For Two String

```
dp[i][j] = property using s1[0..i-1] and s2[0..j-1]
           Do characters match?
           Take from s1 or s2?
```

---

## Interview Checklist

Before coding:

- [ ] Clarify if subsequence or substring
- [ ] Identify constraints (n size, value range)
- [ ] Determine if exact solution or optimization
- [ ] Choose approach (backtracking vs DP)
- [ ] Define state clearly
- [ ] Plan base cases

While coding:

- [ ] Implement template correctly
- [ ] Handle edge cases (empty, single)
- [ ] Verify time/space complexity
- [ ] Test with small examples
- [ ] Add comments for clarity

After coding:

- [ ] Check for off-by-one errors
- [ ] Verify base case initialization
- [ ] Test edge cases
- [ ] Discuss optimization opportunities

---

## Quick Problem Identification

**Generate/Print:**

- Use backtracking
- Pick/not-pick pattern
- O(2ⁿ) complexity

**Check Existence:**

- Use DP with boolean
- Memoization helps
- O(n × k) typically

**Count Ways:**

- Use memoization/DP counter
- Track state carefully
- Handle duplicates

**Find Maximum/Minimum:**

- Use DP with optimization
- LIS uses binary search
- Range DP for palindromes

**Compare Sequences:**

- LCS approach
- 2D DP typical
- O(m × n)

---

## Code Snippets: Ready to Use

### LIS Binary Search

```java
public int lis(int[] nums) {
    List<Integer> tails = new ArrayList<>();
    for (int num : nums) {
        int pos = Collections.binarySearch(tails, num);
        if (pos < 0) pos = -(pos + 1);
        if (pos == tails.size()) tails.add(num);
        else tails.set(pos, num);
    }
    return tails.size();
}
```

### LCS Length

```java
public int lcs(String s1, String s2) {
    int m = s1.length(), n = s2.length();
    int[][] dp = new int[m + 1][n + 1];
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1))
                dp[i][j] = 1 + dp[i - 1][j - 1];
            else
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
    }
    return dp[m][n];
}
```

### Subset Sum DP

```java
public boolean canSubsetSum(int[] arr, int target) {
    boolean[] dp = new boolean[target + 1];
    dp[0] = true;
    for (int num : arr) {
        for (int s = target; s >= num; s--) {
            if (dp[s - num]) dp[s] = true;
        }
    }
    return dp[target];
}
```

### Range DP Palindrome

```java
public int lps(String s) {
    int n = s.length();
    int[][] dp = new int[n][n];
    for (int i = 0; i < n; i++) dp[i][i] = 1;
    for (int l = 2; l <= n; l++) {
        for (int i = 0; i <= n - l; i++) {
            int j = i + l - 1;
            if (s.charAt(i) == s.charAt(j))
                dp[i][j] = 2 + (i + 1 <= j - 1 ? dp[i + 1][j - 1] : 0);
            else
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
        }
    }
    return dp[0][n - 1];
}
```

---

## Study Plan

### Day 1-2: Fundamentals

- [ ] Understand subsequence concept
- [ ] Learn pick/not-pick pattern
- [ ] Implement generate all subsequences

### Day 3-4: DP Basics

- [ ] LIS with O(n²) approach
- [ ] Subset sum problem
- [ ] Basic counting

### Day 5-6: Optimization

- [ ] LIS with binary search O(n log n)
- [ ] Space optimization techniques
- [ ] Memoization vs bottom-up

### Day 7-8: Advanced

- [ ] LCS and its variants
- [ ] Palindromic subsequences
- [ ] Count problems

### Day 9-10: Mixed Patterns

- [ ] Problem identification
- [ ] Multi-problem practice
- [ ] Interview simulations

---

## Practice Resources

| Topic      | Resources                |
| ---------- | ------------------------ |
| LIS        | LeetCode 300, 673, 1157  |
| LCS        | LeetCode 1143, 115, 1092 |
| Palindrome | LeetCode 516, 1216, 1312 |
| Generation | LeetCode 78, 90, 46      |
| Counting   | LeetCode 115, 494, 1223  |

---

## Remember

1. **Start Simple:** Understand fundamentals first
2. **Practice Templates:** Memorize solutions
3. **Optimize Later:** Get working solution first
4. **Test Edge Cases:** Don't assume
5. **Communicate:** Explain your approach clearly
