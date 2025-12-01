# Merge Sort Algorithm

## What is Merge Sort?

Merge Sort is a highly efficient, comparison-based **Divide and Conquer** sorting algorithm. It divides the array into smaller subarrays, sorts them, and then merges them back together in sorted order. Unlike simple sorting algorithms (Bubble, Selection, Insertion), Merge Sort guarantees **O(n log n)** time complexity in all cases.

### Key Concept: Divide and Conquer

1. **Divide**: Split the array into two halves
2. **Conquer**: Recursively sort each half
3. **Combine**: Merge the two sorted halves into one sorted array

---

## How Merge Sort Works

### Algorithm Steps:

1. **Divide** the unsorted array into two halves
2. Recursively apply Merge Sort to both halves
3. **Merge** the two sorted halves into a single sorted array
4. Base case: If array has 1 or 0 elements, it's already sorted

### Visual Example:

Let's sort the array: `[38, 27, 43, 3, 9, 82, 10]`

```
DIVIDE PHASE (Top-Down):
================================

                    [38, 27, 43, 3, 9, 82, 10]
                              |
                    +---------+---------+
                    |                   |
            [38, 27, 43, 3]         [9, 82, 10]
                    |                   |
            +-------+-------+     +-----+-----+
            |               |     |           |
        [38, 27]         [43, 3]  [9, 82]   [10]
            |               |        |         |
        +---+---+       +---+---+  +-+--+     |
        |       |       |       |  |    |     |
       [38]    [27]    [43]    [3] [9] [82]  [10]
        ✓       ✓       ✓       ✓   ✓   ✓     ✓
    (Base case: single elements are sorted)


MERGE PHASE (Bottom-Up):
================================

       [38]    [27]    [43]    [3] [9] [82]  [10]
        |       |       |       |  |    |     |
        +---↓---+       +---↓---+  +-↓--+     |
            |               |        |         |
        [27, 38]         [3, 43]  [9, 82]   [10]
            |               |        |         |
            +-------↓-------+     +--↓--+------+
                    |                  |
            [3, 27, 38, 43]      [9, 10, 82]
                    |                  |
                    +--------↓---------+
                             |
                [3, 9, 10, 27, 38, 43, 82]
                         ✓ SORTED!
```

### Detailed Step-by-Step Visualization:

```
Original Array: [38, 27, 43, 3, 9, 82, 10]

Step 1: DIVIDE into halves
┌────┬────┬────┬────┐   ┌────┬────┬────┐
│ 38 │ 27 │ 43 │ 3  │   │ 9  │ 82 │ 10 │
└────┴────┴────┴────┘   └────┴────┴────┘
   Left Half (L)            Right Half (R)

Step 2: Further DIVIDE left half
┌────┬────┐   ┌────┬────┐
│ 38 │ 27 │   │ 43 │ 3  │
└────┴────┘   └────┴────┘
   L-L            L-R

Step 3: Divide until single elements
┌────┐ ┌────┐ ┌────┐ ┌────┐ ┌────┐ ┌────┐ ┌────┐
│ 38 │ │ 27 │ │ 43 │ │ 3  │ │ 9  │ │ 82 │ │ 10 │
└────┘ └────┘ └────┘ └────┘ └────┘ └────┘ └────┘
   ✓     ✓      ✓      ✓      ✓      ✓      ✓
(All single elements are inherently sorted)

Step 4: MERGE [38] and [27]
Compare: 38 vs 27 → 27 is smaller
┌────┬────┐
│ 27 │ 38 │
└────┴────┘

Step 5: MERGE [43] and [3]
Compare: 43 vs 3 → 3 is smaller
┌────┬────┐
│ 3  │ 43 │
└────┴────┘

Step 6: MERGE [9] and [82]
Compare: 9 vs 82 → 9 is smaller
┌────┬────┐
│ 9  │ 82 │
└────┴────┘

Step 7: [10] is alone (already sorted)
┌────┐
│ 10 │
└────┘

Step 8: MERGE [27, 38] and [3, 43]
Compare: 27 vs 3  → 3 smaller, add 3
Compare: 27 vs 43 → 27 smaller, add 27
Compare: 38 vs 43 → 38 smaller, add 38
Remaining: 43, add 43
┌────┬────┬────┬────┐
│ 3  │ 27 │ 38 │ 43 │
└────┴────┴────┴────┘

Step 9: MERGE [9, 82] and [10]
Compare: 9 vs 10  → 9 smaller, add 9
Compare: 82 vs 10 → 10 smaller, add 10
Remaining: 82, add 82
┌────┬────┬────┐
│ 9  │ 10 │ 82 │
└────┴────┴────┘

Step 10: MERGE [3, 27, 38, 43] and [9, 10, 82]
Compare: 3 vs 9   → 3 smaller, add 3
Compare: 27 vs 9  → 9 smaller, add 9
Compare: 27 vs 10 → 10 smaller, add 10
Compare: 27 vs 82 → 27 smaller, add 27
Compare: 38 vs 82 → 38 smaller, add 38
Compare: 43 vs 82 → 43 smaller, add 43
Remaining: 82, add 82

┌────┬────┬────┬────┬────┬────┬────┐
│ 3  │ 9  │ 10 │ 27 │ 38 │ 43 │ 82 │
└────┴────┴────┴────┴────┴────┴────┘
              ✓ SORTED!
```

### The Merge Process in Detail:

```
Merging two sorted arrays: [3, 27, 38] and [9, 10]

Pointers:     i           j
Arrays:    [3, 27, 38]  [9, 10]
Result:    []

Step 1: Compare 3 vs 9 → 3 smaller
        [3, 27, 38]  [9, 10]
           i            j
Result: [3]

Step 2: Compare 27 vs 9 → 9 smaller
        [3, 27, 38]  [9, 10]
           i               j
Result: [3, 9]

Step 3: Compare 27 vs 10 → 10 smaller
        [3, 27, 38]  [9, 10]
           i                ↓ (exhausted)
Result: [3, 9, 10]

Step 4: Copy remaining from first array
        [3, 27, 38]
              ↓   ↓
Result: [3, 9, 10, 27, 38] ✓
```

---

## Interactive Learning Resources

For better visual understanding, check out these resources:

🔗 **Visualizations & Animations:**

- [VisuAlgo - Merge Sort Animation](https://visualgo.net/en/sorting) - Interactive step-by-step visualization
- [USFCA Merge Sort Visualization](https://www.cs.usfca.edu/~galles/visualization/ComparisonSort.html) - Animated sorting
- [GeeksforGeeks Merge Sort](https://www.geeksforgeeks.org/merge-sort/) - Detailed explanation with diagrams

📺 **Video Tutorials:**

- Search "Merge Sort Algorithm" on YouTube for animated explanations

---

## Pseudocode

### Main Merge Sort Function:

```
Algorithm MergeSort(arr[], left, right)
Input: Array arr, starting index left, ending index right
Output: Sorted array

Begin
    if left < right then
        // Find the middle point
        mid = left + (right - left) / 2

        // Recursively sort first half
        MergeSort(arr, left, mid)

        // Recursively sort second half
        MergeSort(arr, mid + 1, right)

        // Merge the sorted halves
        Merge(arr, left, mid, right)
    end if
End
```

### Merge Function:

```
Algorithm Merge(arr[], left, mid, right)
Input: Array arr, indices left, mid, right
Output: Merged sorted array from left to right

Begin
    n1 = mid - left + 1      // Size of left subarray
    n2 = right - mid         // Size of right subarray

    // Create temporary arrays
    L[] = new array of size n1
    R[] = new array of size n2

    // Copy data to temporary arrays
    for i = 0 to n1-1 do
        L[i] = arr[left + i]

    for j = 0 to n2-1 do
        R[j] = arr[mid + 1 + j]

    // Merge the temporary arrays back
    i = 0  // Initial index of L[]
    j = 0  // Initial index of R[]
    k = left  // Initial index of merged array

    while i < n1 AND j < n2 do
        if L[i] <= R[j] then
            arr[k] = L[i]
            i = i + 1
        else
            arr[k] = R[j]
            j = j + 1
        end if
        k = k + 1
    end while

    // Copy remaining elements of L[] if any
    while i < n1 do
        arr[k] = L[i]
        i = i + 1
        k = k + 1
    end while

    // Copy remaining elements of R[] if any
    while j < n2 do
        arr[k] = R[j]
        j = j + 1
        k = k + 1
    end while
End
```

### Pseudocode Trace Example:

For array `[4, 2, 5, 1]`:

```
MergeSort([4, 2, 5, 1], 0, 3)
  |
  ├─ MergeSort([4, 2], 0, 1)
  |    ├─ MergeSort([4], 0, 0) → Base case
  |    ├─ MergeSort([2], 1, 1) → Base case
  |    └─ Merge([4], [2]) → [2, 4]
  |
  ├─ MergeSort([5, 1], 2, 3)
  |    ├─ MergeSort([5], 2, 2) → Base case
  |    ├─ MergeSort([1], 3, 3) → Base case
  |    └─ Merge([5], [1]) → [1, 5]
  |
  └─ Merge([2, 4], [1, 5]) → [1, 2, 4, 5] ✓
```

---

## Time Complexity

| Case             | Time Complexity | Explanation                              |
| ---------------- | --------------- | ---------------------------------------- |
| **Best Case**    | **O(n log n)**  | Even if sorted, still divides and merges |
| **Average Case** | **O(n log n)**  | For random arrangement of elements       |
| **Worst Case**   | **O(n log n)**  | Consistent performance in all cases      |

### Why O(n log n)?

**Divide Phase:**

- Each level divides array in half
- Number of levels = log₂(n)
- Example: Array of size 8 → 3 levels (8→4→2→1)

**Merge Phase:**

- At each level, we merge all elements once = O(n)
- Total: O(n) work × log(n) levels = **O(n log n)**

### Visual Breakdown:

```
Array size: n = 8

Level 0: [8 elements]                    → n comparisons
           |
Level 1: [4] [4]                         → n comparisons
           |   |
Level 2: [2][2][2][2]                    → n comparisons
           | | | |
Level 3: [1][1][1][1][1][1][1][1]       → n comparisons

Total levels: log₂(8) = 3
Work per level: n
Total: n × log n = 8 × 3 = 24 operations
```

---

## Space Complexity

- **Space Complexity**: **O(n)**
- Merge Sort is **NOT in-place** - requires extra space for temporary arrays
- Each merge operation needs temporary arrays to store subarrays
- Recursive call stack adds O(log n) space
- Total: **O(n) auxiliary space**

### Space Usage Breakdown:

```
Temporary arrays during merge: O(n)
Recursion stack depth: O(log n)
Total: O(n) + O(log n) ≈ O(n)
```

---

## Characteristics

### Advantages:

✅ **Guaranteed O(n log n)** performance in all cases  
✅ **Stable** sorting algorithm (maintains relative order)  
✅ **Predictable** performance - no worst-case degradation  
✅ **Excellent for linked lists** (no random access needed)  
✅ **Parallelizable** - can sort subarrays independently  
✅ **External sorting** - works well for large datasets on disk  
✅ **Better than Quick Sort** for linked lists  
✅ **Consistent** - no performance surprises

### Disadvantages:

❌ **O(n) extra space** required (not in-place)  
❌ **Slower for small arrays** compared to Insertion Sort  
❌ **Not adaptive** - doesn't benefit from partially sorted data  
❌ **Overhead** of copying elements to temporary arrays  
❌ **Cache performance** - not as cache-friendly as Quick Sort  
❌ **Recursive overhead** - function call stack usage

---

## When to Use Merge Sort?

Merge Sort is ideal when:

- **Guaranteed O(n log n)** performance is required
- **Stability** is important (preserving order of equal elements)
- Sorting **linked lists** (no random access penalty)
- **External sorting** (data too large for memory)
- **Parallel processing** available (divide and conquer nature)
- **Predictable performance** is critical (no worst-case scenarios)
- Working with **large datasets** where consistency matters

**Not recommended when:**

- Memory is extremely limited (requires O(n) extra space)
- Sorting small arrays (use Insertion Sort)
- In-place sorting is mandatory

---

## Stability

Merge Sort is **STABLE**. During the merge phase, when two elements are equal, we take from the left subarray first, preserving original order.

Example: In `[4a, 3, 4b, 2]`, after sorting: `[2, 3, 4a, 4b]` (order preserved).

### Why Stable?

```
Merge [4a, 5] and [4b, 6]:
Compare: 4a == 4b → Take 4a first (from left)
Result: [4a, 4b, 5, 6] ✓ Order preserved
```

---

## Merge Sort Variants

### 1. **Bottom-Up Merge Sort** (Iterative)

- Non-recursive implementation
- Starts with subarrays of size 1, merges into size 2, then 4, etc.
- Avoids recursion overhead
- Better cache performance

### 2. **In-Place Merge Sort**

- Attempts to reduce space complexity
- Complex implementation
- Still O(n log n) time but reduces space usage
- Rarely used due to complexity

### 3. **Natural Merge Sort**

- Adaptive variant
- Takes advantage of existing runs (sorted sequences)
- Better for partially sorted data

### 4. **3-Way Merge Sort**

- Divides array into 3 parts instead of 2
- Reduces recursion depth
- Can be faster in practice

---

## Comparison with Other Algorithms

| Algorithm      | Best Case      | Average Case   | Worst Case     | Space    | Stable  | In-Place |
| -------------- | -------------- | -------------- | -------------- | -------- | ------- | -------- |
| **Merge Sort** | **O(n log n)** | **O(n log n)** | **O(n log n)** | **O(n)** | **Yes** | **No**   |
| Quick Sort     | O(n log n)     | O(n log n)     | O(n²)          | O(log n) | No      | Yes      |
| Heap Sort      | O(n log n)     | O(n log n)     | O(n log n)     | O(1)     | No      | Yes      |
| Insertion Sort | O(n)           | O(n²)          | O(n²)          | O(1)     | Yes     | Yes      |
| Bubble Sort    | O(n)           | O(n²)          | O(n²)          | O(1)     | Yes     | Yes      |
| Selection Sort | O(n²)          | O(n²)          | O(n²)          | O(1)     | No      | Yes      |

---

## Merge Sort vs Quick Sort

| Feature               | Merge Sort                     | Quick Sort               |
| --------------------- | ------------------------------ | ------------------------ |
| **Worst Case**        | O(n log n) ✓                   | O(n²)                    |
| **Space**             | O(n)                           | O(log n) ✓               |
| **Stability**         | Stable ✓                       | Unstable                 |
| **Adaptability**      | Not adaptive                   | Not adaptive             |
| **Cache Performance** | Moderate                       | Better ✓                 |
| **Linked Lists**      | Excellent ✓                    | Poor                     |
| **Arrays**            | Good                           | Excellent ✓              |
| **Parallelization**   | Easy ✓                         | Harder                   |
| **Best for**          | Linked lists, external sorting | Arrays, in-place sorting |

### Key Difference:

- **Merge Sort**: Does most work during **merge** (combine) phase
- **Quick Sort**: Does most work during **partition** (divide) phase

---

## Applications of Merge Sort

### Real-World Usage:

1. **External Sorting**

   - Sorting data larger than available RAM
   - Database systems
   - File sorting

2. **Sorting Linked Lists**

   - Default choice for linked list sorting
   - No random access penalty
   - Java's `Collections.sort()` for linked lists

3. **Inversion Count Problems**

   - Counting inversions in an array
   - Similarity measurements

4. **E-commerce Systems**

   - Stable sorting of products by multiple criteria
   - Maintaining relative order important

5. **Parallel Processing**
   - MapReduce frameworks
   - Distributed sorting algorithms

---

## Recursion Tree Analysis

```
For array of size n = 8:

                    T(8)                    Level 0: 1 × 8 = 8
                   /    \
                 /        \
              T(4)        T(4)              Level 1: 2 × 4 = 8
             /    \      /    \
            /      \    /      \
          T(2)   T(2) T(2)   T(2)          Level 2: 4 × 2 = 8
          / \    / \   / \    / \
         T(1)T(1)T(1)T(1)T(1)T(1)T(1)T(1)  Level 3: 8 × 1 = 8

Height of tree: log₂(8) = 3
Work per level: 8
Total work: 3 × 8 = 24 = O(n log n)
```

---

## Key Takeaways

1. **Merge Sort** uses Divide and Conquer strategy - divide, sort, merge
2. **Guaranteed O(n log n)** in all cases - best, average, and worst
3. **Stable algorithm** - maintains relative order of equal elements
4. **Requires O(n) extra space** - not in-place due to temporary arrays
5. **Excellent for linked lists** - no random access penalty
6. **Ideal for external sorting** - handles data larger than memory
7. **Parallelizable** - subarrays can be sorted independently
8. **Trade-off**: Predictable performance vs extra memory usage
9. **Used in production**: Java's Collections.sort() for linked lists, external sorting systems
10. **Merge step is crucial** - combining two sorted arrays efficiently

---

## Practice Questions

1. Why is Merge Sort's time complexity O(n log n) in all cases?
2. Calculate the number of comparisons for sorting an array of size 16.
3. Why is Merge Sort better than Quick Sort for linked lists?
4. How would you implement Merge Sort iteratively (bottom-up)?
5. Given array `[7, 2, 5, 1, 8, 3]`, draw the complete recursion tree.
6. Why does Merge Sort require O(n) extra space?
7. How does Merge Sort maintain stability during the merge phase?
8. When would you choose Merge Sort over Quick Sort?
9. Can Merge Sort be made adaptive? How?
10. Explain how Merge Sort is used in external sorting.

---

## Code Implementation Hints

```java
// Key points for implementation:
// 1. Base case: if left >= right, return (1 or 0 elements)
// 2. Find mid: mid = left + (right - left) / 2
// 3. Recursively sort both halves
// 4. Merge the sorted halves using temporary arrays
// 5. In merge: compare elements and copy smaller one
// 6. Handle remaining elements from both subarrays
// 7. Use <= for stability (left array element when equal)
```

---

## Complexity Comparison Summary

```
Time Complexity:
├─ All Cases: O(n log n) ✓ Consistent
├─ Comparisons: O(n log n)
└─ No best/worst case variation

Space Complexity:
├─ Auxiliary Space: O(n) ✗ Not in-place
├─ Recursion Stack: O(log n)
└─ Total: O(n)

Key Metrics:
├─ Stable: Yes ✓
├─ Adaptive: No
├─ In-Place: No
└─ Online: No
```
