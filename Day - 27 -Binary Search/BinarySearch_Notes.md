# Binary Search - Complete Notes

## Table of Contents

1. [Introduction](#introduction)
2. [Prerequisites](#prerequisites)
3. [How Binary Search Works](#how-binary-search-works)
4. [Algorithm Steps](#algorithm-steps)
5. [Implementation Approaches](#implementation-approaches)
6. [Time & Space Complexity](#time--space-complexity)
7. [Advantages & Disadvantages](#advantages--disadvantages)
8. [Common Pitfalls](#common-pitfalls)
9. [Variations of Binary Search](#variations-of-binary-search)
10. [Practice Problems](#practice-problems)

---

## Introduction

**Binary Search** is an efficient searching algorithm used to find the position of a target value within a **sorted array**. It works on the principle of **Divide and Conquer**.

### Key Points:

- Only works on **sorted arrays**
- Repeatedly divides the search interval in half
- Compares the target value with the middle element
- Time Complexity: **O(log n)**
- Space Complexity: **O(1)** for iterative, **O(log n)** for recursive

---

## Prerequisites

### Must-Have Conditions:

1. **Array must be sorted** (ascending or descending)
2. **Random access** to elements (array or vector)
3. **Comparable elements** (must support comparison operators)

### When NOT to Use Binary Search:

- Unsorted data
- Linked Lists (no random access)
- Small datasets (linear search might be faster due to overhead)

---

## How Binary Search Works

### Visual Example:

**Array:** `[2, 5, 8, 12, 16, 23, 38, 45, 56, 67, 78]`
**Target:** `23`

```
Step 1: [2, 5, 8, 12, 16, |23|, 38, 45, 56, 67, 78]
        left=0, right=10, mid=5
        arr[5] = 23 (Found!)

Step 2 (if target was 45):
        Initial: [2, 5, 8, 12, 16, |23|, 38, 45, 56, 67, 78]
        mid=5, arr[5]=23 < 45, so search right half

        [38, |45|, 56, 67, 78]
        left=6, right=10, mid=7
        arr[7] = 45 (Found!)
```

### Concept Explanation:

1. **Start** with the entire array
2. **Find** the middle element
3. **Compare** middle element with target:
   - If **equal** → Found! Return index
   - If **target < middle** → Search left half
   - If **target > middle** → Search right half
4. **Repeat** until found or search space exhausted

---

## Algorithm Steps

### Detailed Algorithm:

1. Initialize two pointers:

   - `left = 0` (start of array)
   - `right = n - 1` (end of array)

2. While `left <= right`:

   - Calculate middle index: `mid = left + (right - left) / 2`
   - If `arr[mid] == target`: Return mid
   - If `arr[mid] < target`: Update `left = mid + 1`
   - If `arr[mid] > target`: Update `right = mid - 1`

3. If target not found: Return -1

---

## Implementation Approaches

### 1. Iterative Approach (Recommended)

```java
public class BinarySearch {
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            // Calculate mid to avoid overflow
            int mid = left + (right - left) / 2;

            // Check if target is at mid
            if (arr[mid] == target) {
                return mid;
            }

            // If target is greater, ignore left half
            if (arr[mid] < target) {
                left = mid + 1;
            }
            // If target is smaller, ignore right half
            else {
                right = mid - 1;
            }
        }

        // Target not found
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 45, 56, 67, 78};
        int target = 23;

        int result = binarySearch(arr, target);

        if (result == -1) {
            System.out.println("Element not found");
        } else {
            System.out.println("Element found at index: " + result);
        }
    }
}
```

**Output:** `Element found at index: 5`

---

### 2. Recursive Approach

```java
public class BinarySearchRecursive {
    public static int binarySearch(int[] arr, int target, int left, int right) {
        // Base case: element not found
        if (left > right) {
            return -1;
        }

        // Calculate mid
        int mid = left + (right - left) / 2;

        // Element found
        if (arr[mid] == target) {
            return mid;
        }

        // Search in right half
        if (arr[mid] < target) {
            return binarySearch(arr, target, mid + 1, right);
        }

        // Search in left half
        return binarySearch(arr, target, left, mid - 1);
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 45, 56, 67, 78};
        int target = 23;

        int result = binarySearch(arr, target, 0, arr.length - 1);

        if (result == -1) {
            System.out.println("Element not found");
        } else {
            System.out.println("Element found at index: " + result);
        }
    }
}
```

---

### 3. Binary Search on Descending Array

```java
public static int binarySearchDescending(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid;
        }

        // Note: condition reversed for descending order
        if (arr[mid] > target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return -1;
}
```

---

### 4. Order-Agnostic Binary Search

```java
public static int orderAgnosticBS(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;

    // Determine if array is ascending or descending
    boolean isAscending = arr[left] < arr[right];

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid;
        }

        if (isAscending) {
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        } else {
            if (arr[mid] > target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }

    return -1;
}
```

---

## Time & Space Complexity

### Time Complexity:

| Case             | Complexity | Explanation                                      |
| ---------------- | ---------- | ------------------------------------------------ |
| **Best Case**    | O(1)       | Target is at middle position in first comparison |
| **Average Case** | O(log n)   | Target found after log n comparisons             |
| **Worst Case**   | O(log n)   | Target at end or not present                     |

### Why O(log n)?

In each step, we eliminate half of the remaining elements:

- After 1st comparison: n/2 elements remain
- After 2nd comparison: n/4 elements remain
- After 3rd comparison: n/8 elements remain
- After k comparisons: n/2^k elements remain

When n/2^k = 1, we get k = log₂(n)

**Example:** For n = 1024

- Linear Search: Up to 1024 comparisons
- Binary Search: Maximum 10 comparisons (log₂ 1024 = 10)

### Space Complexity:

| Approach      | Space Complexity | Reason                         |
| ------------- | ---------------- | ------------------------------ |
| **Iterative** | O(1)             | Only uses constant extra space |
| **Recursive** | O(log n)         | Recursive call stack depth     |

---

## Advantages & Disadvantages

### ✅ Advantages:

1. **Highly Efficient** - O(log n) time complexity
2. **Simple to Implement** - Easy to understand logic
3. **Optimal for Large Datasets** - Performance scales well
4. **Predictable Performance** - Consistent time complexity
5. **Low Memory Usage** - Iterative version uses O(1) space

### ❌ Disadvantages:

1. **Requires Sorted Data** - Array must be pre-sorted
2. **Not for Linked Lists** - Needs random access
3. **Overhead for Small Arrays** - Linear search may be faster
4. **Static Data Only** - Not efficient for frequently changing data
5. **Sorting Cost** - If data unsorted, sorting adds O(n log n) overhead

---

## Common Pitfalls

### 1. Integer Overflow in Mid Calculation

**❌ Wrong Way:**

```java
int mid = (left + right) / 2;  // Can overflow if left + right > Integer.MAX_VALUE
```

**✅ Correct Way:**

```java
int mid = left + (right - left) / 2;  // Prevents overflow
```

**Alternative:**

```java
int mid = (left + right) >>> 1;  // Unsigned right shift
```

---

### 2. Infinite Loop with Wrong Condition

**❌ Wrong:**

```java
while (left < right) {  // May miss the last element
    // ...
}
```

**✅ Correct:**

```java
while (left <= right) {  // Checks all elements including when left == right
    // ...
}
```

---

### 3. Off-by-One Errors

**Common Mistakes:**

- Using `mid` instead of `mid + 1` or `mid - 1`
- Starting right at `arr.length` instead of `arr.length - 1`
- Wrong loop termination condition

---

### 4. Not Handling Edge Cases

**Edge Cases to Consider:**

```java
// Empty array
if (arr == null || arr.length == 0) {
    return -1;
}

// Single element
if (arr.length == 1) {
    return arr[0] == target ? 0 : -1;
}

// Target smaller than smallest element
if (target < arr[0]) {
    return -1;
}

// Target larger than largest element
if (target > arr[arr.length - 1]) {
    return -1;
}
```

---

## Variations of Binary Search

### 1. Find First Occurrence

```java
public static int findFirstOccurrence(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    int result = -1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            result = mid;          // Store the result
            right = mid - 1;       // Continue searching in left half
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return result;
}
```

**Example:** `[1, 2, 2, 2, 3, 4, 5]`, target = 2 → Returns index 1

---

### 2. Find Last Occurrence

```java
public static int findLastOccurrence(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    int result = -1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            result = mid;          // Store the result
            left = mid + 1;        // Continue searching in right half
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return result;
}
```

**Example:** `[1, 2, 2, 2, 3, 4, 5]`, target = 2 → Returns index 3

---

### 3. Count Occurrences

```java
public static int countOccurrences(int[] arr, int target) {
    int first = findFirstOccurrence(arr, target);
    if (first == -1) return 0;

    int last = findLastOccurrence(arr, target);
    return last - first + 1;
}
```

---

### 4. Floor of a Number

Find the **largest element ≤ target**

```java
public static int findFloor(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    int result = -1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return arr[mid];
        } else if (arr[mid] < target) {
            result = arr[mid];     // Potential floor value
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return result;
}
```

**Example:** `[1, 2, 8, 10, 12, 19]`, target = 5 → Returns 2

---

### 5. Ceiling of a Number

Find the **smallest element ≥ target**

```java
public static int findCeiling(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    int result = -1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return arr[mid];
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            result = arr[mid];     // Potential ceiling value
            right = mid - 1;
        }
    }

    return result;
}
```

**Example:** `[1, 2, 8, 10, 12, 19]`, target = 5 → Returns 8

---

### 6. Search in Rotated Sorted Array

```java
public static int searchRotated(int[] arr, int target) {
    int left = 0, right = arr.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid;
        }

        // Check which half is sorted
        if (arr[left] <= arr[mid]) {  // Left half is sorted
            if (target >= arr[left] && target < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        } else {  // Right half is sorted
            if (target > arr[mid] && target <= arr[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }

    return -1;
}
```

**Example:** `[4, 5, 6, 7, 0, 1, 2]`, target = 0 → Returns index 4

---

### 7. Find Peak Element

```java
public static int findPeakElement(int[] arr) {
    int left = 0, right = arr.length - 1;

    while (left < right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] > arr[mid + 1]) {
            right = mid;  // Peak is in left half (including mid)
        } else {
            left = mid + 1;  // Peak is in right half
        }
    }

    return left;  // or right, both are same at this point
}
```

**Example:** `[1, 2, 3, 1]` → Returns index 2 (value 3 is peak)

---

### 8. Search in Infinite Array

```java
public static int searchInfinite(int[] arr, int target) {
    // Find the range where target might exist
    int left = 0, right = 1;

    while (arr[right] < target) {
        left = right;
        right = right * 2;  // Exponentially increase the range
    }

    // Perform binary search in the identified range
    return binarySearch(arr, target, left, right);
}
```

---

### 9. Search in 2D Matrix

```java
public static boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0) return false;

    int rows = matrix.length;
    int cols = matrix[0].length;
    int left = 0, right = rows * cols - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;
        int midElement = matrix[mid / cols][mid % cols];

        if (midElement == target) {
            return true;
        } else if (midElement < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return false;
}
```

---

### 10. Square Root using Binary Search

```java
public static int findSquareRoot(int n) {
    if (n < 2) return n;

    int left = 1, right = n / 2;
    int result = 0;

    while (left <= right) {
        int mid = left + (right - left) / 2;
        long square = (long) mid * mid;  // Use long to prevent overflow

        if (square == n) {
            return mid;
        } else if (square < n) {
            result = mid;
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return result;
}
```

**Example:** sqrt(17) → Returns 4 (floor value)

---

## Practice Problems

### Beginner Level:

1. ✅ Binary Search in Sorted Array
2. ✅ Find First and Last Position of Element
3. ✅ Search Insert Position
4. ✅ Find Floor and Ceil in Array
5. ✅ Count Occurrences in Sorted Array

### Intermediate Level:

6. 🔶 Search in Rotated Sorted Array
7. 🔶 Find Peak Element
8. 🔶 Find Minimum in Rotated Sorted Array
9. 🔶 Search in 2D Matrix
10. 🔶 Find Kth Smallest Element

### Advanced Level:

11. 🔴 Median of Two Sorted Arrays
12. 🔴 Allocate Minimum Pages
13. 🔴 Aggressive Cows (SPOJ)
14. 🔴 Painter's Partition Problem
15. 🔴 Koko Eating Bananas

---

## Important Formulas & Patterns

### Binary Search Template:

```
1. Initialize: left = 0, right = n - 1
2. Loop: while (left <= right)
3. Calculate: mid = left + (right - left) / 2
4. Compare and Update:
   - If found: return mid
   - If target > mid: left = mid + 1
   - If target < mid: right = mid - 1
5. Not found: return -1
```

### Modified Binary Search Template (for variations):

```
1. Initialize: left = 0, right = n - 1, result = -1
2. Loop: while (left <= right)
3. Calculate: mid = left + (right - left) / 2
4. Update result if condition met
5. Move left or right pointer based on problem
6. Return result
```

---

## Key Takeaways

1. ✨ **Always check** if array is sorted before applying binary search
2. ✨ **Use** `left + (right - left) / 2` to prevent integer overflow
3. ✨ **Prefer** iterative approach for better space complexity
4. ✨ **Handle** edge cases (empty array, single element, duplicates)
5. ✨ **Understand** the problem first - not all search problems need exact match
6. ✨ **Practice** variations to master the concept
7. ✨ **Remember** binary search can be applied to "search space" problems, not just arrays
8. ✨ **Time complexity** is always O(log n) - huge improvement over O(n)

---

## When to Use Binary Search?

✅ **Use Binary Search When:**

- Data is sorted
- Need to find specific element efficiently
- Working with large datasets
- Need O(log n) performance
- Finding boundaries (first/last occurrence)
- Solving optimization problems (minimize/maximize)

❌ **Don't Use Binary Search When:**

- Data is unsorted (unless sorting is part of solution)
- Small datasets where linear search is simpler
- Need to find all occurrences (unless using modified approach)
- Data structure doesn't support random access

---

## Comparison with Other Search Algorithms

| Algorithm                | Time Complexity  | Space | Requirement           | Best For                   |
| ------------------------ | ---------------- | ----- | --------------------- | -------------------------- |
| **Linear Search**        | O(n)             | O(1)  | None                  | Small/unsorted data        |
| **Binary Search**        | O(log n)         | O(1)  | Sorted data           | Large sorted data          |
| **Jump Search**          | O(√n)            | O(1)  | Sorted data           | Medium datasets            |
| **Interpolation Search** | O(log log n) avg | O(1)  | Uniformly distributed | Uniformly distributed data |
| **Exponential Search**   | O(log n)         | O(1)  | Sorted, unbounded     | Infinite/unbounded arrays  |

---

## Interview Tips

1. 🎯 **Ask clarifying questions:**

   - Is the array sorted?
   - Are there duplicates?
   - What to return if element not found?

2. 🎯 **Start with brute force:**

   - Explain linear search approach first
   - Then optimize with binary search

3. 🎯 **Explain your thought process:**

   - Why binary search is applicable
   - Time and space complexity analysis
   - Trade-offs

4. 🎯 **Test with examples:**

   - Test with empty array
   - Single element
   - Target at boundaries
   - Target not in array

5. 🎯 **Handle edge cases:**
   - Always validate input
   - Check array bounds
   - Handle integer overflow

---

## Summary

Binary Search is a **fundamental algorithm** that every programmer must master. Its **O(log n)** time complexity makes it incredibly efficient for searching in sorted data. Understanding binary search and its variations opens doors to solving many complex problems efficiently.

**Remember:** The key to mastering binary search is **practice** and understanding when and how to modify the basic template for different problem variations.

---

**Happy Coding! 🚀**
