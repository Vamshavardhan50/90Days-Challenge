# 📚 Subarrays - Complete Guide

## 🔍 What is a Subarray?

A **subarray** is a contiguous part of an array. It must maintain the order of elements and cannot skip elements.

### Example:

For array `[1, 2, 3, 4]`:

- **Valid Subarrays**: `[1]`, `[1, 2]`, `[1, 2, 3]`, `[2, 3]`, `[3, 4]`, `[2, 3, 4]`, etc.
- **Invalid** (not contiguous): `[1, 3]`, `[2, 4]` ❌

### Total Number of Subarrays

For an array of size `n`, the total number of subarrays = **n × (n + 1) / 2**

For `n = 4`: Total = `4 × 5 / 2 = 10` subarrays

---

## 🎯 Key Concepts

### 1️⃣ Prefix Sum

**Prefix Sum** at index `i` is the sum of all elements from index `0` to `i`.

```
arr[] = [1, 2, 3, 4, 5]
prefix[] = [1, 3, 6, 10, 15]
```

**Why it's useful?**

- Calculate sum of any subarray in O(1) time
- Sum(i to j) = prefix[j] - prefix[i-1]

### 2️⃣ HashMap for Subarray Problems

Using HashMap to store **prefix sums** helps us find subarrays efficiently:

- **Key**: Prefix sum value
- **Value**: Index where this sum first occurred

**Core Logic:**
If `prefix_sum - target` exists in map, we found a subarray with sum = target!

---

## 💡 Problem Patterns

### Pattern 1: Find Indexes of Subarray with Given Sum

**Problem**: Find the start and end indexes of a subarray with sum = target

**Approach**: Use HashMap to store prefix sums

```java
HashMap<Integer, Integer> map = new HashMap<>();
map.put(0, -1); // prefix sum 0 before array starts

for (int i = 0; i < arr.length; i++) {
    sum += arr[i];

    if (map.containsKey(sum - target)) {
        int start = map.get(sum - target) + 1;
        int end = i;
        // Found subarray from start to end
    }

    map.putIfAbsent(sum, i);
}
```

**Example**:

```
arr = [1, 2, 3, 7, 5], target = 6
Output: [0, 2] → subarray [1, 2, 3] has sum 6
```

**Time Complexity**: O(n)  
**Space Complexity**: O(n)

---

### Pattern 2: Longest Subarray with Given Sum

**Problem**: Find the maximum length of subarray with sum = target

**Key Insight**:

- If current prefix sum = target → length = i + 1
- If (prefix_sum - target) exists at index j → length = i - j

**Implementation**:

```java
HashMap<Integer, Integer> map = new HashMap<>();
map.put(0, -1); // Handle subarrays starting from index 0

int maxLen = 0;
for (int i = 0; i < arr.length; i++) {
    sum += arr[i];

    if (map.containsKey(sum - target)) {
        int j = map.get(sum - target);
        maxLen = Math.max(maxLen, i - j);
    }

    map.putIfAbsent(sum, i); // Store only first occurrence
}
```

**Example**:

```
arr = [10, 5, 2, 7, 1, -10], target = 15
Output: 5 → subarray [5, 2, 7, 1] has sum 15
```

**Why `putIfAbsent`?**  
We want the **earliest** occurrence to maximize the length!

---

### Pattern 3: Subarray with Sum Divisible by K

**Problem**: Find longest subarray with sum divisible by K

**Mathematical Insight**:

- If `sum[i] % k == sum[j] % k`, then sum(i+1 to j) is divisible by k
- Use remainder as key in HashMap

**Special Case**: Handle negative remainders

```java
int rem = ((sum % k) + k) % k;
```

**Implementation**:

```java
HashMap<Integer, Integer> map = new HashMap<>();
map.put(0, -1); // remainder 0 before array

for (int i = 0; i < arr.length; i++) {
    sum += arr[i];
    int rem = ((sum % k) + k) % k; // Handle negative

    if (map.containsKey(rem)) {
        maxLen = Math.max(maxLen, i - map.get(rem));
    } else {
        map.put(rem, i);
    }
}
```

**Example**:

```
arr = [2, 7, 6, 1, 4, 5], k = 3
Output: 4 → subarray [7, 6, 1, 4] has sum 18, divisible by 3
```

---

### Pattern 4: Count Subarrays with Equal 0s and 1s

**Problem**: Count subarrays with equal number of 0s and 1s in binary array

**Trick**: Convert 0 → -1, then find subarrays with sum = 0

- Equal 0s and 1s → sum = 0 (after conversion)

**Implementation**:

```java
Map<Integer, Integer> freq = new HashMap<>();
freq.put(0, 1); // prefix sum 0 occurs once

int prefixSum = 0, count = 0;
for (int x : arr) {
    prefixSum += (x == 0) ? -1 : 1;

    count += freq.getOrDefault(prefixSum, 0);
    freq.put(prefixSum, freq.getOrDefault(prefixSum, 0) + 1);
}
```

**Example**:

```
arr = [1, 0, 0, 1, 0, 1, 1]
After conversion: [1, -1, -1, 1, -1, 1, 1]
Output: 7 subarrays with equal 0s and 1s
```

**Why count all occurrences?**
Every previous occurrence of same prefix sum gives us one valid subarray!

---

## 🎓 Important Points to Remember

### 1. When to use `map.put(0, -1)`?

When you need to handle subarrays **starting from index 0**

- For finding indexes: Use `-1`
- For counting: Use `1` (frequency)

## ⚠️ BASE CASE - WHY IT'S CRITICAL!

### 🚨 The Most Important Concept in Subarray Problems

**Without proper base case initialization, your code will FAIL to detect subarrays starting from index 0!**

### ❓ Why do we need `map.put(0, -1)` or `map.put(0, 1)`?

#### **Scenario 1: Finding Subarray Starting from Index 0**

```java
arr = [3, 7], target = 10
```

**Without base case:**

- At i=0: sum=3, check if map has (3-10=-7)? NO ❌
- At i=1: sum=10, check if map has (10-10=0)? NO ❌
- Result: FAIL to detect [3, 7]

**With base case `map.put(0, -1)`:**

- At i=0: sum=3, check if map has (3-10=-7)? NO
- At i=1: sum=10, check if map has (10-10=0)? YES! ✅
- start = map.get(0) + 1 = -1 + 1 = 0
- end = 1
- Result: Found subarray [0, 1] ✅

#### **Scenario 2: Counting Subarrays**

```java
arr = [1, -1, 1, -1], target = 0
```

**Without base case `map.put(0, 1)`:**

- At i=0: sum=1, count += freq.get(1) = 0
- At i=1: sum=0, count += freq.get(0) = **FAIL!** Missing first subarray [1, -1]

**With base case `map.put(0, 1)`:**

- At i=1: sum=0, count += freq.get(0) = 1 ✅
- Correctly counts subarray [1, -1]

### 🎯 Base Case Rules

| Problem Type          | Base Case        | Why?                                                  |
| --------------------- | ---------------- | ----------------------------------------------------- |
| **Find Index/Length** | `map.put(0, -1)` | So that index calculation works: `start = -1 + 1 = 0` |
| **Count Subarrays**   | `map.put(0, 1)`  | Represents "one way to get sum 0" (empty prefix)      |
| **Divisible by K**    | `map.put(0, -1)` | Remainder 0 exists before array starts                |

### 💡 Think of it as:

- **`0`**: Represents "no elements taken yet" or "empty prefix"
- **`-1`**: Position "before the array starts"
- **`1`**: "One occurrence of empty prefix"

### 2. `putIfAbsent` vs `put`

- **`putIfAbsent`**: For longest subarray (store first occurrence)
- **`put`**: For counting subarrays (store all occurrences)

### 3. Handling Negative Numbers

For divisibility problems: `rem = ((sum % k) + k) % k`

### 4. Time Complexity

All HashMap-based solutions: **O(n)** time, **O(n)** space

---

## 🔄 Common Mistakes to Avoid

❌ **Mistake 1**: Forgetting to initialize map with base case

```java
map.put(0, -1); // or map.put(0, 1) depending on problem
```

❌ **Mistake 2**: Using `put` instead of `putIfAbsent` for longest subarray

```java
// WRONG for longest subarray
map.put(sum, i);

// CORRECT for longest subarray
map.putIfAbsent(sum, i);
```

❌ **Mistake 3**: Not handling negative remainders

```java
// WRONG
int rem = sum % k;

// CORRECT
int rem = ((sum % k) + k) % k;
```

---

## 🔥 MOST COMMON MISTAKES THAT BREAK YOUR CODE

### ❌ **MISTAKE #1: Missing Base Case** (80% of failures!)

```java
// WRONG ❌
HashMap<Integer, Integer> map = new HashMap<>();
int sum = 0;
for (int i = 0; i < arr.length; i++) {
    sum += arr[i];
    if (map.containsKey(sum - target)) {
        // This will NEVER find subarrays starting from index 0!
    }
    map.put(sum, i);
}
```

```java
// CORRECT ✅
HashMap<Integer, Integer> map = new HashMap<>();
map.put(0, -1); // BASE CASE - Don't forget!
int sum = 0;
// Now it works for all subarrays!
```

**Why it fails**: Without `map.put(0, -1)`, when the entire array from index 0 equals the target, you won't detect it!

---

### ❌ **MISTAKE #2: Using `put` for ALL Problems**

```java
// WRONG for LONGEST subarray ❌
for (int i = 0; i < arr.length; i++) {
    sum += arr[i];
    if (map.containsKey(sum - target)) {
        maxLen = Math.max(maxLen, i - map.get(sum - target));
    }
    map.put(sum, i); // OVERWRITES earlier index!
}
```

**Test Case**: `arr = [1, 0, 1], target = 1`

- i=0: sum=1, map={1:0}
- i=1: sum=1, map={1:1} ← **Overwrote index 0!**
- i=2: sum=2, length = 2 - 1 = **1** (WRONG! Should be 3)

```java
// CORRECT for LONGEST subarray ✅
map.putIfAbsent(sum, i); // Keeps earliest index
```

---

### ❌ **MISTAKE #3: Wrong Base Case Value**

```java
// WRONG for counting ❌
map.put(0, -1); // Wrong value for counting!

// CORRECT for counting ✅
map.put(0, 1); // "One way to achieve sum 0"
```

**Why?** For counting, we add frequencies, not calculate differences!

---

### ❌ **MISTAKE #4: Forgetting Negative Modulo**

```java
// WRONG ❌
arr = [-2, 3, -1], k = 3
sum = -2, rem = -2 % 3 = -2 (in Java)
// Negative remainder breaks HashMap lookup!
```

```java
// CORRECT ✅
int rem = ((sum % k) + k) % k;
// -2 → ((-2 % 3) + 3) % 3 = (-2 + 3) % 3 = 1
```

---

### ❌ **MISTAKE #5: Not Understanding When to Update Map**

```java
// WRONG for counting ❌
for (int i = 0; i < arr.length; i++) {
    sum += arr[i];
    map.put(sum, map.getOrDefault(sum, 0) + 1); // Updates BEFORE counting
    count += map.getOrDefault(sum - target, 0); // Uses wrong count!
}
```

```java
// CORRECT for counting ✅
for (int i = 0; i < arr.length; i++) {
    sum += arr[i];
    count += map.getOrDefault(sum - target, 0); // Count FIRST
    map.put(sum, map.getOrDefault(sum, 0) + 1); // Update AFTER
}
```

**Order matters!** Count first, then update frequency.

---

### ❌ **MISTAKE #6: Confusing Index Problems with Count Problems**

**Index/Length Problems**: Store **first occurrence index**

```java
map.putIfAbsent(sum, i);
```

**Counting Problems**: Store **frequency**

```java
map.put(sum, map.getOrDefault(sum, 0) + 1);
```

---

### ❌ **MISTAKE #7: Off-by-One in Index Calculation**

```java
// Length calculation for subarray [j+1 to i]
int length = i - j; // CORRECT
int length = i - j + 1; // WRONG!

// Because map stores index j where we saw sum
// Subarray is from j+1 to i
// Length = i - (j+1) + 1 = i - j
```

---

## 🎯 Quick Debug Checklist

When your subarray code fails, check:

1. ✅ Did I add base case? `map.put(0, -1)` or `map.put(0, 1)`?
2. ✅ Am I using `putIfAbsent` for longest subarray?
3. ✅ Am I handling negative modulo? `((sum % k) + k) % k`
4. ✅ For counting, do I update map AFTER counting?
5. ✅ Am I using correct data structure? Index vs Frequency?
6. ✅ Test with edge case: Does entire array from index 0 equal target?

---

## 🚀 Quick Reference Table

| Problem Type     | HashMap Key | HashMap Value | Initial State |
| ---------------- | ----------- | ------------- | ------------- |
| Find Indexes     | Prefix Sum  | Index         | `{0: -1}`     |
| Longest Subarray | Prefix Sum  | First Index   | `{0: -1}`     |
| Count Subarrays  | Prefix Sum  | Frequency     | `{0: 1}`      |
| Divisible by K   | Remainder   | First Index   | `{0: -1}`     |

---

## 📝 Practice Tips

1. Always think: "Can I use prefix sum here?"
2. Draw the array and mark prefix sums manually
3. Identify what to store in HashMap (sum vs remainder vs frequency)
4. Test with edge cases: empty array, single element, all negatives

---

## 🎯 Related Problems

- Maximum Subarray Sum (Kadane's Algorithm)
- Subarray with XOR = K
- Subarray with product = K
- Count distinct elements in all subarrays
- Sliding window problems

---

**Happy Coding! 🚀**
