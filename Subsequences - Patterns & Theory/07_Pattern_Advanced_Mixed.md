# Pattern 7: Advanced & Mixed Patterns

## Problem 1: Russian Doll Envelopes

**Statement:** Given envelopes with (width, height), find max number of nested envelopes.

**Approach:** Convert to LIS on one dimension.

```java
public int russianDollEnvelopes(int[][] envelopes) {
    /*
      Max envelopes that can be Russian dolled.
      Sort by width ascending, height descending.
      Find LIS on heights using binary search.
      Time: O(n log n), Space: O(n)
    */
    if (envelopes == null || envelopes.length == 0) return 0;

    // Sort: width ascending, height descending (if width equal)
    Arrays.sort(envelopes, (a, b) -> {
        if (a[0] != b[0]) return a[0] - b[0];
        return b[1] - a[1];
    });

    // Find LIS on heights
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

// Example
// russianDollEnvelopes([[5, 4], [6, 4], [6, 7], [2, 3]]) -> 3
// ([2,3] -> [5,4] -> [6,7])
```

**Why sort height descending when width equal?**

- Prevents using two envelopes with same width
- [5,4] and [5,2]: can't nest due to same width
- By sorting [5,4] before [5,2], we ensure only one is picked

---

## Problem 2: Maximum Height by Stacking Cuboids

```java
public int maxHeightStack(int[][] cuboids) {
    /*
      Stack cuboids to maximize height.
      Each dimension of cuboid a must be <= cuboid b to place b on top.
      Time: O(n² log n), Space: O(n)
    */
    // Sort each cuboid's dimensions
    for (int[] c : cuboids) Arrays.sort(c);
    // Sort cuboids by dimensions
    Arrays.sort(cuboids);

    int n = cuboids.length;
    int[] dp = new int[n];

    for (int i = 0; i < n; i++) {
        dp[i] = cuboids[i][2];  // Height from each cuboid
    }

    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            // Check if cuboid[i] can stack on cuboid[j]
            if (cuboids[j][0] <= cuboids[i][0] &&
                cuboids[j][1] <= cuboids[i][1] &&
                cuboids[j][2] <= cuboids[i][2]) {
                dp[i] = Math.max(dp[i], dp[j] + cuboids[i][2]);
            }
        }
    }

    int max = 0;
    for (int h : dp) max = Math.max(max, h);
    return max;
}

// Example
// maxHeightStack([[50, 45, 20], [95, 37, 53], [45, 23, 12]])
```

---

## Problem 3: Weighted Job Scheduling

**Statement:** Given jobs with start, end, profit, maximize profit with non-overlapping jobs.

```python
def max_profit_jobs(jobs):
    """
    Maximum profit scheduling non-overlapping jobs.

    jobs: [(start, end, profit), ...]

    Time: O(n² + n log n)
    Space: O(n)
    """
    # Sort by end time
    jobs.sort(key=lambda x: x[1])
    n = len(jobs)

    # dp[i] = max profit considering jobs[0...i]
    dp = [0] * n
    dp[0] = jobs[0][2]

    for i in range(1, n):
        # Option 1: Don't take job i
        profit_without = dp[i-1]

        # Option 2: Take job i
        # Find latest job that doesn't overlap
        profit_with = jobs[i][2]

        # Binary search for latest non-overlapping job
        latest_non_overlap = -1
        for j in range(i - 1, -1, -1):
            if jobs[j][1] <= jobs[i][0]:
                latest_non_overlap = j
                break

        if latest_non_overlap != -1:
            profit_with += dp[latest_non_overlap]

        dp[i] = max(profit_without, profit_with)

    return dp[n-1]

# Example
jobs = [(1, 2, 50), (3, 5, 20), (6, 19, 100), (2, 100, 200)]
print(max_profit_jobs(jobs))  # 250 (jobs with end 2 and start 6+)
```

---

## Problem 4: Interleaving String

**Statement:** Check if s3 is formed by interleaving s1 and s2.

```python
def is_interleave(s1, s2, s3):
    """
    Check if s3 can be formed by interleaving s1 and s2.

    Time: O(m × n) where m = len(s1), n = len(s2)
    Space: O(m × n)
    """
    if len(s1) + len(s2) != len(s3):
        return False

    m, n = len(s1), len(s2)
    dp = [[False] * (n + 1) for _ in range(m + 1)]
    dp[0][0] = True

    # Initialize first row (s1)
    for i in range(1, m + 1):
        dp[i][0] = dp[i-1][0] and s1[i-1] == s3[i-1]

    # Initialize first column (s2)
    for j in range(1, n + 1):
        dp[0][j] = dp[0][j-1] and s2[j-1] == s3[j-1]

    # Fill DP table
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            # Case 1: Take from s1
            take_s1 = dp[i-1][j] and s1[i-1] == s3[i+j-1]
            # Case 2: Take from s2
            take_s2 = dp[i][j-1] and s2[j-1] == s3[i+j-1]

            dp[i][j] = take_s1 or take_s2

    return dp[m][n]

# Example
print(is_interleave("aabcc", "dbbca", "aadbbcbcac"))  # True
```

---

## Problem 5: Delete Operation for Two Strings

**Statement:** Return minimum total characters to delete to make strings equal.

```python
def min_delete_to_make_equal(s1, s2):
    """
    Minimum deletions to make s1 and s2 equal.

    Approach: LCS then delete characters not in LCS

    Time: O(m × n)
    Space: O(m × n)
    """
    m, n = len(s1), len(s2)
    dp = [[0] * (n + 1) for _ in range(m + 1)]

    # Find LCS length
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if s1[i-1] == s2[j-1]:
                dp[i][j] = 1 + dp[i-1][j-1]
            else:
                dp[i][j] = max(dp[i-1][j], dp[i][j-1])

    lcs_length = dp[m][n]

    # Characters to delete = total - 2 * lcs_length
    return m + n - 2 * lcs_length

# Example
print(min_delete_to_make_equal("leetcode", "etco"))  # 4
```

---

## Problem 6: Maximum Length of Pair Chain

**Statement:** Given pairs, max length chain where (c, d) follows (a, b) if b < c.

```python
def max_pair_chain_length(pairs):
    """
    Maximum non-overlapping pairs chain.

    Greedy: Sort by end point, greedily select pairs.

    Time: O(n log n)
    Space: O(1)
    """
    pairs.sort(key=lambda x: x[1])

    current_end = float('-inf')
    chain_length = 0

    for start, end in pairs:
        if start > current_end:
            chain_length += 1
            current_end = end

    return chain_length

# Example
pairs = [[1, 2], [7, 8], [4, 5]]
print(max_pair_chain_length(pairs))  # 3 (all can be chained)
```

---

## Problem 7: Minimum Difficulty of Job Schedule

**Statement:** Schedule jobs over d days, each day consecutive, maximize hardest job on each day.

```python
def min_job_difficulty(jobs, d):
    """
    Minimum difficulty of scheduling jobs over d days.
    Each day: consecutive jobs, maximize difficulty of that day.

    Time: O(n² × d)
    Space: O(n × d)
    """
    n = len(jobs)
    if d > n:
        return -1

    # dp[i][j] = min difficulty for first i jobs over j days
    dp = [[float('inf')] * (d + 1) for _ in range(n + 1)]
    dp[0][0] = 0

    for i in range(1, n + 1):
        for j in range(1, d + 1):
            # Try to end day j at position k
            for k in range(j - 1, i):
                # Day j: jobs[k...i-1]
                max_difficulty = max(jobs[k:i])
                dp[i][j] = min(dp[i][j], dp[k][j-1] + max_difficulty)

    return dp[n][d]

# Example
jobs = [6, 5, 4, 3, 2, 1]
d = 2
print(min_job_difficulty(jobs, d))  # min sum of max difficulties
```

---

## Pattern Summary Table

| Problem             | Key Idea          | Time       | Space  |
| ------------------- | ----------------- | ---------- | ------ |
| Russian Doll        | Convert to LIS    | O(n log n) | O(n)   |
| Stacking Cuboids    | 3D LIS            | O(n²)      | O(n)   |
| Job Scheduling      | Weighted interval | O(n²)      | O(n)   |
| Interleaving        | State (i, j)      | O(m×n)     | O(m×n) |
| Delete for Equality | LCS               | O(m×n)     | O(m×n) |
| Pair Chain          | Greedy on end     | O(n log n) | O(1)   |
| Job Difficulty      | Day k split       | O(n²×d)    | O(n×d) |

---

## Advanced Techniques

### 1. Coordinate Compression

Use when values are large but count is small.

```python
def compress_coordinates(values):
    """Map large values to small indices."""
    sorted_vals = sorted(set(values))
    mapping = {v: i for i, v in enumerate(sorted_vals)}
    return [mapping[v] for v in values]
```

### 2. Segment Trees / Fenwick Trees

Use for range queries in LIS variants.

```python
class FenwickTree:
    def __init__(self, n):
        self.n = n
        self.tree = [0] * (n + 1)

    def update(self, i, delta):
        i += 1
        while i <= self.n:
            self.tree[i] += delta
            i += i & (-i)

    def query(self, i):
        i += 1
        res = 0
        while i > 0:
            res += self.tree[i]
            i -= i & (-i)
        return res
```

### 3. Greedy with DP

Combine greedy choices with DP verification.

### 4. Binary Search + DP

Optimize overlapping problems.

---

## Interview Tips

✅ **Do:**

- Identify if it's a variant of known pattern
- Reduce 2D problems to 1D if possible
- Use coordinate compression for large values
- Consider greedy vs DP tradeoffs

❌ **Don't:**

- Force greedy when DP needed
- Overlook sorting order
- Miss state transitions
- Forget to validate constraints

---

## Key Takeaways

1. **Many problems reduce to LIS/LCS**
2. **Sorting often simplifies**
3. **Greedy sometimes works**
4. **State definition is crucial**
5. **Combine multiple techniques**
