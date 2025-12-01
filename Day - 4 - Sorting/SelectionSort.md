# Selection Sort Algorithm

## What is Selection Sort?

Selection Sort is a simple comparison-based sorting algorithm. It divides the input array into two parts:

- **Sorted portion** (initially empty, on the left)
- **Unsorted portion** (initially the entire array, on the right)

The algorithm repeatedly selects the smallest (or largest) element from the unsorted portion and moves it to the end of the sorted portion.

---

## How Selection Sort Works

### Algorithm Steps:

1. Start with the first element as the current position
2. Find the minimum element in the unsorted portion (from current position to end)
3. Swap the minimum element with the element at the current position
4. Move the boundary of the sorted portion one step forward
5. Repeat steps 2-4 until the entire array is sorted

### Visual Example:

Let's sort the array: `[64, 25, 12, 22, 11]`

```
Initial Array:
┌────┬────┬────┬────┬────┐
│ 64 │ 25 │ 12 │ 22 │ 11 │
└────┴────┴────┴────┴────┘
  ↑
  i (start)

Pass 1: Search from index 0 to 4
        Find minimum = 11 (at index 4)
        Swap 64 ↔ 11

┌────┬────┬────┬────┬────┐
│ 11 │ 25 │ 12 │ 22 │ 64 │
└────┴────┴────┴────┴────┘
  ✓    ↑
sorted  i (moves forward)


Pass 2: Search from index 1 to 4
        Find minimum = 12 (at index 2)
        Swap 25 ↔ 12

┌────┬────┬────┬────┬────┐
│ 11 │ 12 │ 25 │ 22 │ 64 │
└────┴────┴────┴────┴────┘
  ✓    ✓    ↑
sorted     i (moves forward)


Pass 3: Search from index 2 to 4
        Find minimum = 22 (at index 3)
        Swap 25 ↔ 22

┌────┬────┬────┬────┬────┐
│ 11 │ 12 │ 22 │ 25 │ 64 │
└────┴────┴────┴────┴────┘
  ✓    ✓    ✓    ↑
sorted          i (moves forward)


Pass 4: Search from index 3 to 4
        Find minimum = 25 (already in place)
        No swap needed

┌────┬────┬────┬────┬────┐
│ 11 │ 12 │ 22 │ 25 │ 64 │
└────┴────┴────┴────┴────┘
  ✓    ✓    ✓    ✓    ✓
    Fully Sorted Array!
```

### Detailed Step-by-Step Visualization:

```
Legend:
[✓] = Sorted portion (correct position)
[*] = Current position being filled
[→] = Searching for minimum in this range
[↕] = Elements being swapped

Step 0: Initial state
Index:  0    1    2    3    4
      ┌────┬────┬────┬────┬────┐
      │ 64 │ 25 │ 12 │ 22 │ 11 │
      └────┴────┴────┴────┴────┘
       [*]  [→]  [→]  [→]  [→]  → Found min: 11
       [↕]                  [↕]  → Swap!

Step 1: After first swap
Index:  0    1    2    3    4
      ┌────┬────┬────┬────┬────┐
      │ 11 │ 25 │ 12 │ 22 │ 64 │
      └────┴────┴────┴────┴────┘
       [✓]  [*]  [→]  [→]  [→]  → Found min: 12
            [↕]  [↕]              → Swap!

Step 2: After second swap
Index:  0    1    2    3    4
      ┌────┬────┬────┬────┬────┐
      │ 11 │ 12 │ 25 │ 22 │ 64 │
      └────┴────┴────┴────┴────┘
       [✓]  [✓]  [*]  [→]  [→]  → Found min: 22
                 [↕]  [↕]         → Swap!

Step 3: After third swap
Index:  0    1    2    3    4
      ┌────┬────┬────┬────┬────┐
      │ 11 │ 12 │ 22 │ 25 │ 64 │
      └────┴────┴────┴────┴────┘
       [✓]  [✓]  [✓]  [*]  [→]  → Found min: 25 (no swap)

Step 4: Final state
Index:  0    1    2    3    4
      ┌────┬────┬────┬────┬────┐
      │ 11 │ 12 │ 22 │ 25 │ 64 │
      └────┴────┴────┴────┴────┘
       [✓]  [✓]  [✓]  [✓]  [✓]  → SORTED!
```

---

## Pseudocode

```
Algorithm SelectionSort(arr[], n)
Input: Array arr of size n
Output: Sorted array in ascending order

Begin
    for i = 0 to n-2 do
        // Assume current position has minimum
        minIndex = i

        // Search for smallest element in remaining array
        for j = i+1 to n-1 do
            if arr[j] < arr[minIndex] then
                minIndex = j  // Update minimum index
            end if
        end for

        // Swap minimum element with element at position i
        if minIndex ≠ i then
            swap arr[i] with arr[minIndex]
        end if
    end for
End
```

### Pseudocode Trace Example:

For array `[5, 2, 8, 1]`:

````
i=0: minIndex=0(5) → search→ found 1 at index 3 → swap → [1, 2, 8, 5]
i=1: minIndex=1(2) → search→ no smaller found → no swap → [1, 2, 8, 5]
i=2: minIndex=2(8) → search→ found 5 at index 3 → swap → [1, 2, 5, 8]
Done! Array is sorted: [1, 2, 5, 8]
```**Video Tutorials:**
- Search "Selection Sort Algorithm" on YouTube for animated explanations

---

## Pseudocode

````

for i = 0 to n-2:
minIndex = i
for j = i+1 to n-1:
if arr[j] < arr[minIndex]:
minIndex = j
swap arr[i] with arr[minIndex]

```

---

## Time Complexity

| Case | Time Complexity | Explanation |
|------|----------------|-------------|
| **Best Case** | O(n²) | Even if array is sorted, algorithm checks all elements |
| **Average Case** | O(n²) | For random arrangement of elements |
| **Worst Case** | O(n²) | When array is reverse sorted |

- **Number of comparisons**: n(n-1)/2 = O(n²)
- **Number of swaps**: O(n) - at most n-1 swaps

---

## Space Complexity

- **Space Complexity**: O(1)
- Selection Sort is an **in-place** sorting algorithm (no extra space needed)

---

## Characteristics

### Advantages:
✅ Simple and easy to understand
✅ Works well on small datasets
✅ Performs well when swapping is costly (only n-1 swaps)
✅ In-place algorithm (no extra memory required)
✅ Number of swaps is minimum compared to other algorithms

### Disadvantages:
❌ Inefficient for large datasets (O(n²) complexity)
❌ Not stable (may change relative order of equal elements)
❌ Not adaptive (doesn't take advantage of already sorted elements)
❌ Poor performance compared to modern algorithms like Quick Sort or Merge Sort

---

## When to Use Selection Sort?

Selection Sort is suitable when:
- The array is small (< 50 elements)
- Memory is limited (in-place sorting)
- Swapping operation is expensive (minimizes number of swaps)
- You need a simple implementation
- Checking/comparing is cheap but swapping is costly

---

## Stability

Selection Sort is **NOT stable** by default. Equal elements may not maintain their original relative order.

Example: In `[4a, 3, 4b, 2]`, after sorting you might get `[2, 3, 4b, 4a]` instead of `[2, 3, 4a, 4b]`.

---

## Comparison with Other Sorting Algorithms

| Algorithm | Best Case | Average Case | Worst Case | Space | Stable |
|-----------|-----------|--------------|------------|-------|--------|
| Selection Sort | O(n²) | O(n²) | O(n²) | O(1) | No |
| Bubble Sort | O(n) | O(n²) | O(n²) | O(1) | Yes |
| Insertion Sort | O(n) | O(n²) | O(n²) | O(1) | Yes |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) | Yes |
| Quick Sort | O(n log n) | O(n log n) | O(n²) | O(log n) | No |

---

## Key Takeaways

1. Selection Sort always performs O(n²) comparisons regardless of input
2. It minimizes the number of swaps (exactly n-1 swaps)
3. Best used when swapping is expensive but comparison is cheap
4. Not recommended for large datasets due to quadratic time complexity
5. Simple to implement but inefficient for practical applications
```
