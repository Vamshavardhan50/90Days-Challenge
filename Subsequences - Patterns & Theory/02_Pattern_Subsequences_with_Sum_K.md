# Pattern 2: Subsequences with Constraints (Sum = K)

## Overview

This pattern finds or counts subsequences that satisfy specific constraints, most commonly **sum equals target value**.

## Core Problem: Subset Sum

**Statement:** Given an array and a target sum K, determine if there exists a subsequence with sum equal to K.

---

## Solution Approaches

### Approach 1: Backtracking (Recursive)

```java
public boolean subsequenceSumExists(int[] arr, int target) {
    /*
      Check if a subsequence with sum = target exists.
      Time: O(2^n) - worst case explore all subsequences
      Space: O(n) - recursion depth
    */
    return backtrack(arr, 0, 0, target);
}

private boolean backtrack(int[] arr, int index, int currentSum, int target) {
    // Found the target sum
    if (currentSum == target) return true;

    // Exceeded or at end
    if (index == arr.length || currentSum > target) return false;

    // Include current element
    if (backtrack(arr, index + 1, currentSum + arr[index], target)) return true;

    // Exclude current element
    if (backtrack(arr, index + 1, currentSum, target)) return true;

    return false;
}

// Example
// subsequenceSumExists([3, 34, 4, 12, 5, 2], 9)  // true (4+5)
// subsequenceSumExists([3, 34, 4, 12, 5, 2], 30)  // false
```

**Optimization:** Pruning when sum exceeds target

```java
// Add early termination
if (currentSum > target) {
    return false;  // No point continuing
}
```

---

### Approach 2: DP - 1D Array (Optimized)

```java
public boolean subsetSumDP1D(int[] arr, int target) {
    /*
      Dynamic programming with 1D array.
      dp[i] = true if sum i is achievable
      Time: O(n × target), Space: O(target)
    */
    boolean[] dp = new boolean[target + 1];
    dp[0] = true;  // Sum 0 is always achievable (empty subset)

    for (int num : arr) {
        // Traverse from right to left to avoid using same element twice
        for (int s = target; s >= num; s--) {
            if (dp[s - num]) {
                dp[s] = true;
            }
        }
    }

    return dp[target];
}

// Example
// subsetSumDP1D([3, 34, 4, 12, 5, 2], 9)  // true
// subsetSumDP1D([3, 34, 4, 12, 5, 2], 30)  // false
```

**Why traverse right to left?**

```
Traverse left to right would reuse same element:
  [1, 2], target = 3
  num = 1:
    dp[3] = dp[2] (but dp[2] was using 1!)

Traverse right to left uses value before update:
  num = 1:
    dp[3] = old_dp[2]
    dp[2] = old_dp[1]  (1 used only once)
```

---

### Approach 3: DP - 2D Array (Clearer Logic)

```java
public boolean subsetSumDP2D(int[] arr, int target) {
    /*
      Dynamic programming with 2D array for clarity.
      dp[i][j] = true if sum j is achievable using arr[0..i-1]
      Time: O(n × target), Space: O(n × target)
    */
    int n = arr.length;
    boolean[][] dp = new boolean[n + 1][target + 1];

    // Base case: sum 0 is always achievable (empty subset)
    for (int i = 0; i <= n; i++) {
        dp[i][0] = true;
    }

    // Fill the DP table
    for (int i = 1; i <= n; i++) {
        for (int j = 0; j <= target; j++) {
            // Case 1: Don't include arr[i-1]
            dp[i][j] = dp[i - 1][j];

            // Case 2: Include arr[i-1] (if possible)
            if (j >= arr[i - 1]) {
                dp[i][j] = dp[i][j] || dp[i - 1][j - arr[i - 1]];
            }
        }
    }

    return dp[n][target];
}

// Example
// subsetSumDP2D([3, 34, 4, 12, 5, 2], 9)  // true
```

**Building intuition:**

```
Array: [3, 4, 5], Target: 9

         0    3    4    5    7    9
    0    T    F    F    F    F    F
    3    T    T    F    F    F    F     (can make 0, 3)
    4    T    T    T    F    T    F     (can make 0, 3, 4, 7)
    5    T    T    T    T    T    T     (can make all!)
                                    ↑
                        dp[3][9] = True
```

---

### Approach 4: Memoization (Top-Down DP)

```java
public boolean subsetSumMemo(int[] arr, int target) {
    /*
      Memoization approach - easier to code than bottom-up.
      Time: O(n × target), Space: O(n × target) for memo + O(n) for recursion
    */
    Map<String, Boolean> memo = new HashMap<>();
    return dpMemo(arr, 0, target, memo);
}

private boolean dpMemo(int[] arr, int index, int remaining, Map<String, Boolean> memo) {
    // Base cases
    if (remaining == 0) return true;
    if (index == arr.length || remaining < 0) return false;

    // Check memo
    String key = index + "," + remaining;
    if (memo.containsKey(key)) return memo.get(key);

    // Include or exclude current element
    boolean result = dpMemo(arr, index + 1, remaining - arr[index], memo) ||
                     dpMemo(arr, index + 1, remaining, memo);

    memo.put(key, result);
    return result;
}

// Example
// subsetSumMemo([3, 34, 4, 12, 5, 2], 9)  // true
```

---

## Print Subsequences with Sum = K

### Back Track & Print

```java
public List<List<Integer>> printSubsequencesSumK(int[] arr, int target) {
    /*
      Print ALL subsequences with sum = target.
      Time: O(2^n) - worst case print all
      Space: O(n) - recursion depth
    */
    List<List<Integer>> result = new ArrayList<>();
    backtrackPrint(arr, 0, new ArrayList<>(), result, 0, target);
    return result;
}

private void backtrackPrint(int[] arr, int index, List<Integer> current, List<List<Integer>> result, int currentSum, int target) {
    // Found a valid subsequence
    if (currentSum == target) {
        result.add(new ArrayList<>(current));
        return;
    }

    // Exceeded target or reached end
    if (index == arr.length || currentSum > target) return;

    // Include current element
    current.add(arr[index]);
    backtrackPrint(arr, index + 1, current, result, currentSum + arr[index], target);
    current.remove(current.size() - 1);

    // Exclude current element
    backtrackPrint(arr, index + 1, current, result, currentSum, target);
}

// Example
// printSubsequencesSumK([1, 2, 3, 4, 5], 5)
// Output: [[1, 4], [2, 3], [5]]
```

---

## Count Subsequences with Sum = K

```java
public int countSubsequencesSumK(int[] arr, int target) {
    /*
      Count number of subsequences with sum = target.
      Time: O(n × target), Space: O(n × target)
    */
    Map<String, Integer> memo = new HashMap<>();
    return dpCount(arr, 0, target, memo);
}

private int dpCount(int[] arr, int index, int remaining, Map<String, Integer> memo) {
    if (remaining == 0) return 1;  // Found one valid subsequence

    if (index == arr.length || remaining < 0) return 0;

    String key = index + "," + remaining;
    if (memo.containsKey(key)) return memo.get(key);

    // Count ways including and excluding current element
    int count = dpCount(arr, index + 1, remaining - arr[index], memo) +
                dpCount(arr, index + 1, remaining, memo);

    memo.put(key, count);
    return count;
}

// Example
// countSubsequencesSumK([1, 2, 1], 3)
// Subsequences: [1,2], [2,1], [1,1,1] -> Count = 3
```

---

## Advanced: Zero Sum Subsequences

```java
public List<List<Integer>> zeroSumSubsequences(int[] arr) {
    /*
      Find all subsequences with sum = 0.
      Different because target can be derived from data.
    */
    List<List<Integer>> result = new ArrayList<>();
    backtrackZeroSum(arr, 0, new ArrayList<>(), result, 0);
    return result;
}

private void backtrackZeroSum(int[] arr, int index, List<Integer> current, List<List<Integer>> result, int currentSum) {
    if (index == arr.length) {
        if (currentSum == 0 && !current.isEmpty()) {
            result.add(new ArrayList<>(current));
        }
        return;
    }

    // Include
    current.add(arr[index]);
    backtrackZeroSum(arr, index + 1, current, result, currentSum + arr[index]);
    current.remove(current.size() - 1);

    // Exclude
    backtrackZeroSum(arr, index + 1, current, result, currentSum);
}

// Example
// zeroSumSubsequences([-1, 0, 1, 2, -1, -4])
// Outputs all zero-sum subsequences
```

---

## Complexity Analysis

| Approach     | Time        | Space       | Best For                      |
| ------------ | ----------- | ----------- | ----------------------------- |
| Backtracking | O(2^n)      | O(n)        | Small n, finding one solution |
| DP 1D        | O(n×target) | O(target)   | Large n, checking existence   |
| DP 2D        | O(n×target) | O(n×target) | Clarity, reconstruction       |
| Memoization  | O(n×target) | O(n×target) | Coding interviews             |

**When to use:**

- **Backtracking:** n ≤ 20, need to print/modify subsequences
- **DP 1D:** Large n, minimal space needed
- **DP 2D:** Large n, need to reconstruct solution
- **Memoization:** Interview setting, code clarity

---

## Reconstruction: Get Actual Subsequence

```java
public int[] subsetSumWithReconstruction(int[] arr, int target) {
    /*
      Return actual subsequence, not just true/false.
    */
    int n = arr.length;
    boolean[][] dp = new boolean[n + 1][target + 1];

    for (int i = 0; i <= n; i++) {
        dp[i][0] = true;
    }

    for (int i = 1; i <= n; i++) {
        for (int j = 0; j <= target; j++) {
            dp[i][j] = dp[i - 1][j];
            if (j >= arr[i - 1]) {
                dp[i][j] = dp[i][j] || dp[i - 1][j - arr[i - 1]];
            }
        }
    }

    // Reconstruction
    if (!dp[n][target]) return new int[0];

    List<Integer> result = new ArrayList<>();
    int i = n, j = target;

    while (i > 0 && j > 0) {
        // If value comes from including arr[i-1]
        if (!dp[i - 1][j] && dp[i - 1][j - arr[i - 1]]) {
            result.add(arr[i - 1]);
            j -= arr[i - 1];
        }
        i--;
    }

    Collections.reverse(result);
    return result.stream().mapToInt(Integer::intValue).toArray();
}

// Example
// subsetSumWithReconstruction([3, 34, 4, 12, 5, 2], 9)
// Output: [4, 5] or similar
```

---

## Variations & Related Problems

### 1. Partition Equal Subset Sum

```java
public boolean canPartition(int[] nums) {
    /*
      Can array be partitioned into two subsets with equal sum?
    */
    int total = 0;
    for (int num : nums) total += num;

    if (total % 2 != 0) return false;

    return subsetSumDP1D(nums, total / 2);
}
```

### 2. Target Sum (with +/-)

```java
public int targetSum(int[] nums, int target) {
    /*
      Count ways to place + or - to reach target.
      Convert to: count subsequences with sum = x
    */
    int total = 0;
    for (int num : nums) total += num;

    int needed = target + total;
    if (needed % 2 != 0 || Math.abs(target) > total) return 0;

    return countSubsequencesSumK(nums, needed / 2);
}
```

### 3. Coin Change (1D Optimization)

```java
public int coinChangeMinCoins(int[] coins, int amount) {
    /*
      Minimum coins to make amount.
      Similar DP structure.
    */
    int[] dp = new int[amount + 1];
    for (int i = 1; i <= amount; i++) dp[i] = Integer.MAX_VALUE;

    for (int coin : coins) {
        for (int a = coin; a <= amount; a++) {
            if (dp[a - coin] != Integer.MAX_VALUE) {
                dp[a] = Math.min(dp[a], dp[a - coin] + 1);
            }
        }
    }

    return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
}
```

---

## Common Interview Questions

1. **Subset Sum Problem** - Does a subset with given sum exist?
2. **Partition Equal Subset Sum** - Can array be partitioned equally?
3. **Target Sum** - Count ways to achieve target with +/-
4. **Combination Sum** - Find combinations that sum to target
5. **4 Sum** - Find all unique 4-tuples that sum to target

---

## Key Takeaways

1. **DP 1D is space-efficient** - requires right-to-left traversal
2. **Memoization feels natural** - but DP is more efficient
3. **Reconstruction requires careful backtracking** through DP table
4. **Time: O(n×target)** dominates space for most solutions
5. **Backtracking better for print/enumerate** solutions
6. **Check for negative numbers** - affects algorithm choice

---

## Practice Tips

✅ **Do:**

- Understand why we traverse right-to-left in 1D DP
- Practice 2D and 1D versions
- Learn reconstruction to print actual subset
- Consider edge cases (negative, zero, duplicates)

❌ **Don't:**

- Confuse subsequences with combinations
- Use backtracking for large n
- Forget to backtrack properly
- Traverse left-to-right in 1D DP
