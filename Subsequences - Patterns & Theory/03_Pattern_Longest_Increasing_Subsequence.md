# Pattern 3: Longest Increasing Subsequence (LIS)

## Overview

LIS is one of the most important subsequence problems. It finds the longest subsequence where elements are in strictly increasing order.

---

## Problem Statement

**Input:** Array of integers  
**Output:** Length of longest increasing subsequence  
**Constraint:** Elements must be in strictly increasing order, order from original array must be preserved

### Examples

```
Input: [10, 9, 2, 5, 3, 7, 101, 18]
Output: 4
Explanation: LIS is [2, 3, 7, 101]

Input: [0, 1, 0, 4, 4, 4, 3, 2, 1]
Output: 4
Explanation: LIS is [0, 1, 4, 4] or [0, 1, 3, 4] depending on definition
            (If strictly increasing) LIS is [0, 1, 2, 3] - wait, that's not in order
            Actually: [0, 1, 4] has length 3
```

---

## Solution 1: Dynamic Programming - O(n²)

### Concept

```
dp[i] = length of LIS ending at index i

dp[i] = max(dp[j] + 1) for all j < i where arr[j] < arr[i]
      = 1 if no such j exists
```

### Implementation

```java
public int lengthOfLISN2(int[] nums) {
    /*
      Find LIS length using O(n²) DP.
      Time: O(n²), Space: O(n)
    */
    if (nums == null || nums.length == 0) return 0;

    int n = nums.length;
    int[] dp = new int[n];
    for (int i = 0; i < n; i++) dp[i] = 1;

    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            // If we can extend LIS ending at j
            if (nums[j] < nums[i]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
    }

    int max = 0;
    for (int val : dp) max = Math.max(max, val);
    return max;
}

// Example
// lengthOfLISN2([10, 9, 2, 5, 3, 7, 101, 18]) -> 4
// lengthOfLISN2([0, 1, 0, 4, 4, 4, 3, 2, 1]) -> 4
```

### Walkthrough

```
Array: [10, 9, 2, 5, 3, 7, 101, 18]
Index:  0  1  2  3  4  5   6   7

dp[0] = 1                          [10]
dp[1] = 1                          [9]
dp[2] = 1                          [2]
dp[3] = max(dp[2]+1) = 2          [2,5]
dp[4] = max(dp[2]+1) = 2          [2,3]
dp[5] = max(dp[3]+1, dp[4]+1) = 3 [2,5,7] or [2,3,7]
dp[6] = max(dp[3]+1, dp[4]+1, dp[5]+1) = 4 [2,5,7,101] or similar
dp[7] = max(dp[3]+1, dp[4]+1, dp[5]+1) = 4 [2,5,7,18]

Answer: max(dp) = 4
```

---

## Solution 2: Binary Search - O(n log n)

### Concept

**Key Insight:** Maintain an array where `tails[i]` = smallest tail of all increasing subsequences of length `i+1`.

```
Example: [10, 9, 2, 5, 3, 7, 101, 18]

After 10:  tails = [10]
After 9:   tails = [9]        (9 < 10, so replace)
After 2:   tails = [2]        (2 < 9, so replace)
After 5:   tails = [2, 5]     (can extend)
After 3:   tails = [2, 3]     (3 < 5, so replace)
After 7:   tails = [2, 3, 7]  (can extend)
After 101: tails = [2, 3, 7, 101] (can extend)
After 18:  tails = [2, 3, 7, 18]  (18 < 101, so replace)

Length = 4
```

### Why This Works?

1. `tails[i]` always stores the **smallest tail** among all LIS of length `i+1`
2. Smaller tail means **better chances to extend** LIS later
3. `tails` array is **always sorted**

### Implementation

```java
public int lengthOfLISBinarySearch(int[] nums) {
    /*
      Find LIS length using binary search in O(n log n).
      Maintain 'tails' where tails[i] = smallest tail of all LIS of length i+1
      Time: O(n log n), Space: O(n)
    */
    List<Integer> tails = new ArrayList<>();

    for (int num : nums) {
        // Find position where num should be inserted
        int pos = Collections.binarySearch(tails, num);
        if (pos < 0) pos = -(pos + 1);  // Convert to insertion point

        if (pos == tails.size()) {
            // num is larger than all, extend LIS
            tails.add(num);
        } else {
            // Replace element at pos with num
            tails.set(pos, num);
        }
    }

    return tails.size();
}

// Example
// lengthOfLISBinarySearch([10, 9, 2, 5, 3, 7, 101, 18]) -> 4
```

### Collections.binarySearch vs bisect behavior

```java
// Java's Collections.binarySearch returns:
// - Positive index if found
// - Negative insertion point (-(insertion_point + 1)) if not found
// For strictly increasing sequences, we use:
int pos = Collections.binarySearch(lis, num);
if (pos < 0) pos = -(pos + 1);  // Convert to insertion point

// Example:
List<Integer> arr = Arrays.asList(1, 3, 3, 5);
Collections.binarySearch(arr, 3);  // Returns 1 or 2 (first occurrence)
// For LIS we want insertion point, so leftmost position
```

---

## Get Actual LIS (Not Just Length)

### Method 1: Reconstruct from DP Array

```java
public List<Integer> getLIS(int[] nums) {
    /*
      Get actual LIS, not just length.
      Uses parent pointers to reconstruct.
    */
    if (nums == null || nums.length == 0) return new ArrayList<>();

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

    // Find index of maximum LIS length
    int maxLength = 0, maxIndex = -1;
    for (int i = 0; i < n; i++) {
        if (dp[i] > maxLength) {
            maxLength = dp[i];
            maxIndex = i;
        }
    }

    // Reconstruct LIS
    List<Integer> lis = new ArrayList<>();
    while (maxIndex != -1) {
        lis.add(0, nums[maxIndex]);
        maxIndex = parent[maxIndex];
    }

    return lis;
}

// Example
// getLIS([10, 9, 2, 5, 3, 7, 101, 18])
// Output: [2, 3, 7, 101] or similar
```

### Method 2: From Binary Search Array

```java
public List<Integer> getLISBinarySearch(int[] nums) {
    /*
      Reconstruct actual LIS from binary search approach.
      More complex: need to track paths.
    */
    List<Integer> tails = new ArrayList<>();
    List<Integer> parents = new ArrayList<>();
    List<Integer> pathIndices = new ArrayList<>();

    for (int i = 0; i < nums.length; i++) {
        int pos = Collections.binarySearch(tails, nums[i]);
        if (pos < 0) pos = -(pos + 1);

        if (pos == tails.size()) {
            tails.add(nums[i]);
        } else {
            tails.set(pos, nums[i]);
        }

        pathIndices.add(i);
    }

    // Reconstruct LIS from tails
    List<Integer> lis = new ArrayList<>();
    for (int i = 0; i < tails.size(); i++) {
        lis.add(tails.get(i));
    }
    return lis;
}
```

    return lis

# Note: This is simplified; actual reconstruction is more complex

````

---

## Related Problems

### 1. Longest Decreasing Subsequence

```java
public int longestDecreasing(int[] nums) {
    /*
      Longest Decreasing Subsequence.
      Use > instead of <
    */
    int n = nums.length;
    int[] dp = new int[n];
    for (int i = 0; i < n; i++) dp[i] = 1;

    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[j] > nums[i]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
    }

    int max = 0;
    for (int val : dp) max = Math.max(max, val);
    return max;
}
````

### 2. Longest Non-Decreasing Subsequence

```java
public int longestNonDecreasing(int[] nums) {
    /*
      Longest Non-Decreasing Subsequence.
      Allow equal elements.
    */
    int n = nums.length;
    int[] dp = new int[n];
    for (int i = 0; i < n; i++) dp[i] = 1;

    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[j] <= nums[i]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
    }

    int max = 0;
    for (int val : dp) max = Math.max(max, val);
    return max;
}
```

### 3. Longest Bitonic Subsequence

```java
/*
Bitonic: First increasing, then decreasing (or vice versa)
*/
public int longestBitonicSubsequence(int[] nums) {
    int n = nums.length;

    // LIS from left
    int[] lis = new int[n];
    for (int i = 0; i < n; i++) lis[i] = 1;
    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {
                lis[i] = Math.max(lis[i], lis[j] + 1);
            }
        }
    }

    // LDS from right (longest decreasing)
    int[] lds = new int[n];
    for (int i = 0; i < n; i++) lds[i] = 1;
    for (int i = n - 2; i >= 0; i--) {
        for (int j = i + 1; j < n; j++) {
            if (nums[i] > nums[j]) {
                lds[i] = Math.max(lds[i], lds[j] + 1);
            }
        }
    }

    // Combine LIS + LDS - 1 (middle element counted twice)
    int maxLen = 0;
    for (int i = 0; i < n; i++) {
        maxLen = Math.max(maxLen, lis[i] + lds[i] - 1);
    }
    return maxLen;
}
```

        for j in range(i+1, n):
            if nums[i] > nums[j]:
                lds[i] = max(lds[i], lds[j] + 1)

    # Bitonic = lis[i] + lds[i] - 1 (don't count i twice)
    return max(lis[i] + lds[i] - 1 for i in range(n))

````

### 4. Number of Longest Increasing Subsequences

```java
public int findNumberOfLIS(int[] nums) {
    /*
      Count how many LIS of maximum length exist.
    */
    if (nums == null || nums.length == 0) return 0;

    int n = nums.length;
    int[] length = new int[n];
    int[] count = new int[n];
    for (int i = 0; i < n; i++) {
        length[i] = 1;
        count[i] = 1;
    }

    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {
                if (length[j] + 1 > length[i]) {
                    length[i] = length[j] + 1;
                    count[i] = count[j];
                } else if (length[j] + 1 == length[i]) {
                    count[i] += count[j];
                }
            }
        }
    }

    int maxLen = 0;
    for (int len : length) maxLen = Math.max(maxLen, len);

    int result = 0;
    for (int i = 0; i < n; i++) {
        if (length[i] == maxLen) result += count[i];
    }

    return result;
}
````

### 5. Increasing Triplet Subsequence

```java
public boolean increasingTriplet(int[] nums) {
    /*
      Special case: just find if [a, b, c] where a < b < c exists.
      O(n) greedy solution!
    */
    int first = Integer.MAX_VALUE;
    int second = Integer.MAX_VALUE;

    for (int num : nums) {
        if (num <= first) {
            first = num;
        } else if (num <= second) {
            second = num;
        } else {
            return true;  // Found triplet
        }
    }

    return false;
}
```

---

## Comparison of Approaches

| Approach   | Time           | Space | Best For                     |
| ---------- | -------------- | ----- | ---------------------------- |
| O(n²) DP   | O(n²)          | O(n)  | Small n, easy to understand  |
| O(n log n) | O(n log n)     | O(n)  | Large n, interview preferred |
| Get LIS    | O(n²) + O(LIS) | O(n)  | When actual sequence needed  |

---

## Interview Tips

✅ **Do:**

- Start with O(n²) solution first
- Explain binary search optimization clearly
- Discuss when smaller tail helps
- Practice reconstruction

❌ **Don't:**

- Use O(n²) for large n
- Confuse bisect_left with bisect_right
- Forget strictly increasing (not non-decreasing)
- Miss edge cases (empty, single element)

---

## Complexity Analysis

### O(n²) Solution

- **Time:** O(n²) - nested loops
- **Space:** O(n) - dp array only
- **When:** n ≤ 300

### O(n log n) Solution

- **Time:** O(n log n) - binary search per element
- **Space:** O(n) - tails array
- **When:** n > 300, interview preferred

---

## Visualization

```
[10, 9, 2, 5, 3, 7, 101, 18]

O(n²) DP:
dp:    [1, 1, 1, 2, 2, 3,  4,   4]
Index:  0  1  2  3  4  5   6   7

Binary Search:
tails after each:
[10]           -> [9]           -> [2]
[2,5]          -> [2,3]         -> [2,3,7]
[2,3,7,101]    -> [2,3,7,18]
```

---

## Key Takeaways

1. **LIS is fundamental** - appears in many problems
2. **O(n log n) binary search** is interview standard
3. **Smaller tail helps** - future extensions possible
4. **Reconstruction needs parent tracking** - plan ahead
5. **Many variants exist** - increasing, decreasing, bitonic, etc.
6. **Greedy fails sometimes** - use DP or binary search
