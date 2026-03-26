# Pattern 1: Generate All Subsequences

## Overview

This is the most fundamental subsequence pattern. It involves exploring all possible combinations of elements while maintaining their original order.

## Key Concepts

### 1. Understanding the Pattern

- For each element, we have **2 choices**: include it or exclude it
- Total subsequences = **2ⁿ** (including empty)
- Non-empty = **2ⁿ - 1**

### 2. Recursion Tree Example

```
Input: [1, 2, 3]

                    ()
                   /  \
                  /    \
                 /      \
              (1)        ()
              / \        / \
           (1,2) (1)  (2)  ()
           / \    / \  / \ / \
        (1,2,3) ... (3) (2,3) ...

Total nodes = 2^3 = 8
```

---

## Solution Approaches

### Approach 1: Backtracking with Arrays

**Concept:** Build one subsequence at a time, backtrack to explore other paths.

```java
public List<List<Integer>> generateSubsequences(int[] arr) {
    /*
      Generate all non-empty subsequences using backtracking.
      Time: O(n * 2^n) - 2^n subsequences, each takes O(n) to copy
      Space: O(n) - recursion depth
    */
    List<List<Integer>> result = new ArrayList<>();

    backtrack(arr, 0, new ArrayList<>(), result);
    return result;
}

private void backtrack(int[] arr, int index, List<Integer> current, List<List<Integer>> result) {
    // Add current subsequence to result
    if (!current.isEmpty()) {
        result.add(new ArrayList<>(current));
    }

    // Base case: reached end
    if (index == arr.length) return;

    // Try all remaining elements starting from index
    for (int i = index; i < arr.length; i++) {
        // Include arr[i]
        current.add(arr[i]);
        backtrack(arr, i + 1, current, result);
        current.remove(current.size() - 1);  // Backtrack
    }
}

// Example
// int[] arr = {1, 2, 3};
// Output: [[1], [1,2], [1,2,3], [1,3], [2], [2,3], [3]]
```

---

### Approach 2: Pick or Not Pick (Cleaner)

**Concept:** At each position, decide whether to include the element or skip it.

```java
public List<List<Integer>> generateSubsequencesPickNotPick(int[] arr) {
    /*
      Generate all subsequences using pick/not-pick paradigm.
      This approach is cleaner and easier to extend.
    */
    List<List<Integer>> result = new ArrayList<>();
    backtrackPickNotPick(arr, 0, new ArrayList<>(), result);
    return result;
}

private void backtrackPickNotPick(int[] arr, int index, List<Integer> current, List<List<Integer>> result) {
    // Base case: processed all elements
    if (index == arr.length) {
        if (!current.isEmpty()) {
            result.add(new ArrayList<>(current));
        }
        return;
    }

    // Choice 1: Pick current element
    current.add(arr[index]);
    backtrackPickNotPick(arr, index + 1, current, result);
    current.remove(current.size() - 1);

    // Choice 2: Don't pick current element
    backtrackPickNotPick(arr, index + 1, current, result);
}

// Example
// int[] arr = {1, 2};
// Output: [[1], [1,2], [2]]
```

---

### Approach 3: Iterative with Bitmask

**Concept:** Use each bit position to represent whether to include that element.

```java
public List<List<Integer>> generateSubsequencesBitmask(int[] arr) {
    /*
      Generate all subsequences using bitmask iteration.
      For each number from 1 to 2^n - 1:
      - If bit i is set, include arr[i]
    */
    int n = arr.length;
    List<List<Integer>> result = new ArrayList<>();

    // Start from 1 to skip empty subsequence
    for (int mask = 1; mask < (1 << n); mask++) {
        List<Integer> current = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) != 0) {  // Check if bit i is set
                current.add(arr[i]);
            }
        }
        result.add(current);
    }

    return result;
}

// Example
// int[] arr = {1, 2, 3};
// Output (in different order): [[1], [2], [1,2], [3], [1,3], [2,3], [1,2,3]]
```

---

### Approach 4: String Input

**For strings instead of arrays:**

```java
public List<String> generateStrSubsequences(String s) {
    /*
      Generate all subsequences of a string.
    */
    List<String> result = new ArrayList<>();
    backtrackString(s, 0, "", result);
    return result;
}

private void backtrackString(String s, int index, String current, List<String> result) {
    if (index == s.length()) {
        if (!current.isEmpty()) {
            result.add(current);
        }
        return;
    }

    // Include current character
    backtrackString(s, index + 1, current + s.charAt(index), result);

    // Exclude current character
    backtrackString(s, index + 1, current, result);
}

// Example
// String s = "abc";
// Output: ["a", "ab", "abc", "ac", "b", "bc", "c"]
```

---

## Print Subsequences with Conditions

### 1. Count Subsequences

```java
public int countSubsequences(int[] arr) {
    /*
      Count total non-empty subsequences.
    */
    int[] count = {0};
    backtrackCount(arr, 0, new ArrayList<>(), count);
    return count[0];
}

private void backtrackCount(int[] arr, int index, List<Integer> current, int[] count) {
    if (!current.isEmpty()) {
        count[0]++;
    }

    if (index == arr.length) return;

    for (int i = index; i < arr.length; i++) {
        current.add(arr[i]);
        backtrackCount(arr, i + 1, current, count);
        current.remove(current.size() - 1);
    }
}

// Example: [1,2,3] -> 7 (all except empty)
```

### 2. Subsequences of Given Length

```java
public List<List<Integer>> generateSubsequencesLengthK(int[] arr, int k) {
    /*
      Generate all subsequences of exactly length k.
    */
    List<List<Integer>> result = new ArrayList<>();
    backtrackLengthK(arr, 0, new ArrayList<>(), result, k);
    return result;
}

private void backtrackLengthK(int[] arr, int index, List<Integer> current, List<List<Integer>> result, int k) {
    // Successfully found a subsequence of length k
    if (current.size() == k) {
        result.add(new ArrayList<>(current));
        return;
    }

    // No point continuing if we can't reach length k
    if (index == arr.length || current.size() + (arr.length - index) < k) {
        return;
    }

    // Include current element
    current.add(arr[index]);
    backtrackLengthK(arr, index + 1, current, result, k);
    current.remove(current.size() - 1);

    // Exclude current element
    backtrackLengthK(arr, index + 1, current, result, k);
}

// Example
// generateSubsequencesLengthK([1, 2, 3, 4], 2)
// Output: [[1,2], [1,3], [1,4], [2,3], [2,4], [3,4]]
```

### 3. Print All Subsequences with Sum = K

```java
public List<List<Integer>> printSubsequencesSumK(int[] arr, int k) {
    /*
      Print all subsequences with sum equal to k.
    */
    List<List<Integer>> result = new ArrayList<>();
    backtrackSumK(arr, 0, new ArrayList<>(), result, 0, k);
    return result;
}

private void backtrackSumK(int[] arr, int index, List<Integer> current, List<List<Integer>> result, int currentSum, int k) {
    if (currentSum == k) {
        result.add(new ArrayList<>(current));
        return;
    }

    if (index == arr.length || currentSum > k) {
        return;
    }

    // Include
    current.add(arr[index]);
    backtrackSumK(arr, index + 1, current, result, currentSum + arr[index], k);
    current.remove(current.size() - 1);

    // Exclude
    backtrackSumK(arr, index + 1, current, result, currentSum, k);
}

// Example
// printSubsequencesSumK([1, 2, 3, 5], 5)
// Output: [[2,3], [5]]
```

---

## Complexity Analysis

| Aspect               | Value                                |
| -------------------- | ------------------------------------ |
| **Time Complexity**  | O(n × 2ⁿ)                            |
| **Space Complexity** | O(n) - recursion stack depth only    |
| **Output Space**     | O(n × 2ⁿ) - storing all subsequences |

### Breakdown:

- **2ⁿ subsequences** to generate
- **O(n) copy time** for each subsequence when adding to result
- **O(n) recursion depth** at most
- **Total = O(n × 2ⁿ)**

### When to Use Each Approach:

| Approach                  | Best For             | Pros                         | Cons                          |
| ------------------------- | -------------------- | ---------------------------- | ----------------------------- |
| Backtracking (Array loop) | Combinations/Subsets | Clean, can prune easily      | Slightly harder to understand |
| Pick/Not Pick             | All subsequences     | Most natural, easy to modify | Need two recursive calls      |
| Bitmask                   | Small n (≤ 20)       | Simple, no recursion         | Not intuitive, limited by n   |
| String input              | String problems      | Direct string manipulation   | Only for strings              |

---

## Common Variations

### 1. Include Empty Subsequence

```java
public List<List<Integer>> generateWithEmpty(int[] arr) {
    /*
      Generate all subsequences including empty.
    */
    List<List<Integer>> result = new ArrayList<>();
    backtrackIncludeEmpty(arr, 0, new ArrayList<>(), result);
    return result;
}

private void backtrackIncludeEmpty(int[] arr, int index, List<Integer> current, List<List<Integer>> result) {
    result.add(new ArrayList<>(current));  // Add even when current is empty

    if (index == arr.length) return;

    for (int i = index; i < arr.length; i++) {
        current.add(arr[i]);
        backtrackIncludeEmpty(arr, i + 1, current, result);
        current.remove(current.size() - 1);
    }
}
```

### 2. No Duplicates in Result

```java
public List<List<Integer>> generateNoDuplicates(int[] arr) {
    /*
      Generate all unique subsequences (avoid duplicates in result).
    */
    Arrays.sort(arr);  // Sort first
    List<List<Integer>> result = new ArrayList<>();
    backtrackNoDup(arr, 0, new ArrayList<>(), result);
    return result;
}

private void backtrackNoDup(int[] arr, int index, List<Integer> current, List<List<Integer>> result) {
    result.add(new ArrayList<>(current));

    for (int i = index; i < arr.length; i++) {
        // Skip duplicates
        if (i > index && arr[i] == arr[i - 1]) {
            continue;
        }

        current.add(arr[i]);
        backtrackNoDup(arr, i + 1, current, result);
        current.remove(current.size() - 1);
    }
}
```

### 3. Maintain Order in Output

```java
public List<List<Integer>> generateOrdered(int[] arr) {
    /*
      Simply use pick/not-pick in order.
    */
    List<List<Integer>> result = new ArrayList<>();
    backtrackOrdered(arr, 0, new ArrayList<>(), result);
    return result;
}

private void backtrackOrdered(int[] arr, int index, List<Integer> current, List<List<Integer>> result) {
    if (index == arr.length) {
        if (!current.isEmpty()) {
            result.add(new ArrayList<>(current));
        }
        return;
    }

    // Pick current element
    current.add(arr[index]);
    backtrackOrdered(arr, index + 1, current, result);
    current.remove(current.size() - 1);

    // Don't pick current element
    backtrackOrdered(arr, index + 1, current, result);
}
```

---

## Practice Problems

1. **LeetCode 78 - Subsets**
   - Generate all subsets (including empty)
2. **LeetCode 90 - Subsets II**
   - Generate all unique subsets (with duplicates in input)
3. **LeetCode 46 - Permutations**
   - Different from subsequences (order can change)
4. **LeetCode 77 - Combinations**
   - Similar to subsequences of specific length

5. **GeeksforGeeks - Print All Subsequence of String**
   - Print all string subsequences

---

## Interview Tips

✅ **Do:**

- Explain the "pick or not pick" paradigm clearly
- Discuss time/space complexity
- Consider edge cases (empty, single element)
- Optimize if needed (bitmask for small n)
- Mention when backtracking is better than iteration

❌ **Don't:**

- Confuse sequences with permutations
- Generate duplicates without handling
- Use bitmask for large n (will timeout)
- Forget to backtrack properly

---

## Visual Walkthrough

### Example: Generate all subsequences of [1, 2]

```
Start: []

Pick 1: [1]
  |
  Pick 2: [1, 2] ✓ Add to result
  |
  Don't pick 2: Backtrack

Skip 1: []
  |
  Pick 2: [2] ✓ Add to result
  |
  Don't pick 2: Backtrack

Result: [[1], [1,2], [2]]
```

---

## Key Takeaways

1. **Recursion is natural** for subsequence generation
2. **Pick/not-pick** is the clearest paradigm
3. **Time complexity 2^n** means n ≤ 20 for practical use
4. **Bitmask** is iterative alternative for small n
5. **Always backtrack** properly to maintain state
6. **Consider duplicates** handling if input has them
