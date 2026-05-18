# Sliding Window + Two Pointer Pattern

## Overview
This pattern combines **sliding window** (maintaining a window with adjustable boundaries) with **two pointer** technique (using pointers to traverse/compare elements). It's powerful for problems requiring simultaneous window maintenance and element comparison.

## Core Idea
- Use two pointers to define window boundaries
- Expand/contract window based on conditions
- Process window contents as needed
- Both pointers move in one direction (usually forward)

## Key Characteristics
1. **Two pointers** define the window: `left` and `right`
2. Both pointers **only move forward**
3. Window can expand or contract based on condition
4. Often used for substring/subarray problems with constraints

---

## Common Problem Types

### 1. Minimum Size Subarray Problems
Find minimum length subarray that satisfies a condition.

**Template:**
```java
int minSubArrayLen(int target, int[] nums) {
    int left = 0, sum = 0, minLen = Integer.MAX_VALUE;
    
    for (int right = 0; right < nums.length; right++) {
        sum += nums[right];
        
        while (sum >= target) {
            minLen = Math.min(minLen, right - left + 1);
            sum -= nums[left];
            left++;
        }
    }
    return minLen == Integer.MAX_VALUE ? 0 : minLen;
}
```

**Problems:**
- Minimum size subarray sum
- Minimum number of subarrays with sum >= target
- Shortest subarray with sum at least K

---

### 2. Longest Substring Problems
Find longest substring satisfying constraints.

**Template:**
```java
int lengthOfLongestSubstring(String s) {
    int[] freq = new int[256];
    int left = 0, maxLen = 0;
    
    for (int right = 0; right < s.length(); right++) {
        char c = s.charAt(right);
        freq[c]++;
        
        while (freq[c] > 1) {
            freq[s.charAt(left)]--;
            left++;
        }
        maxLen = Math.max(maxLen, right - left + 1);
    }
    return maxLen;
}
```

**Problems:**
- Longest substring without repeating characters
- Longest substring with K distinct characters
- Find the length of longest substring with at most K repeating characters

---

### 3. Count Subarray Problems
Count subarrays satisfying conditions.

**Template:**
```java
// Similar structure - expand window, process, shrink
// Use HashMap or array for frequency tracking
// Count valid windows
```

**Problems:**
- Count number of nice subarrays
- Number of subarrays with sum divisible by K
- Count subarrays with maximum element at least K

---

### 4. String/Array Window Problems
Transform string based on window conditions.

**Problems:**
- Find all anagrams in a string
- Minimum window substring
- Longest word with all characters present

---

## General Algorithm Pattern

```
left = 0
for right in 0 to n-1:
    Add nums[right] to window
    
    While condition is met:
        Update answer
        Remove nums[left] from window
        left++
```

---

## Key Variations

### Fixed Window Size
```java
for (int i = 0; i <= n - k; i++) {
    // Process window [i, i+k-1]
}
```

### Variable Window Size
- Expand until condition breaks
- Contract to find minimum
- Track best answer

### Two Pointer Variations
| Pattern | Left Pointer | Right Pointer | Use Case |
|---------|--------------|---------------|----------|
| Both forward | Expand | Expand | Subarray/substring |
| Opposite direction | Shrink | Expand | Binary search style |
| Same direction | Traverse | Traverse | Merge sorted arrays |

---

## Example Problems with Solutions

### Example 1: Maximum Average Subarray (LeetCode 643)
```java
public double findMaxAverage(int[] nums, int k) {
    double sum = 0;
    for (int i = 0; i < k; i++) sum += nums[i];
    double maxSum = sum;
    
    for (int i = k; i < nums.length; i++) {
        sum += nums[i] - nums[i - k];
        maxSum = Math.max(maxSum, sum);
    }
    return maxSum / k;
}
```

### Example 2: Longest Subarray with Sum Divisible by K (Day 20)
```java
public static int longestSubarray(int[] arr, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, -1);
    int maxLen = 0, sum = 0;
    
    for (int i = 0; i < arr.length; i++) {
        sum += arr[i];
        int rem = sum % k;
        if (rem < 0) rem += k;
        
        if (map.containsKey(rem)) {
            maxLen = Math.max(maxLen, i - map.get(rem));
        } else {
            map.put(rem, i);
        }
    }
    return maxLen;
}
```

### Example 3: Count Nice Subarrays (LeetCode 1248)
```java
public int numberOfSubarrays(int[] nums, int k) {
    return atMostK(nums, k) - atMostK(nums, k - 1);
}

private int atMostK(int[] nums, int k) {
    int left = 0, count = 0, oddCount = 0;
    
    for (int right = 0; right < nums.length; right++) {
        if (nums[right] % 2 == 1) oddCount++;
        
        while (oddCount > k) {
            if (nums[left] % 2 == 1) oddCount--;
            left++;
        }
        count += right - left + 1;
    }
    return count;
}
```

### Example 4: Binary Subarrays With Sum (LeetCode 930)
```java
public int numSubarraysWithSum(int[] nums, int goal) {
    return atMost(nums, goal) - atMost(nums, goal - 1);
}

private int atMost(int[] nums, int goal) {
    if (goal < 0) return 0;
    int left = 0, sum = 0, count = 0;
    
    for (int right = 0; right < nums.length; right++) {
        sum += nums[right];
        
        while (sum > goal && left <= right) {
            sum -= nums[left];
            left++;
        }
        count += right - left + 1;
    }
    return count;
}
```

---

## Time & Space Complexity

| Pattern | Time | Space |
|---------|------|-------|
| Basic sliding window | O(n) | O(1) |
| With frequency map | O(n) | O(k) or O(256) |
| With hashmap for sums | O(n) | O(n) |
| Two array pointers | O(n + m) | O(1) |

---

## Practice Problems List

**Easy:**
- Maximum average subarray I
- Number of subarrays with sum 0

**Medium:**
- Minimum size subarray sum
- Longest substring without repeating characters
- Find all anagrams in a string
- Minimum window subsequence
- Count number of nice subarrays

**Hard:**
- Shortest subarray with sum at least K
- Longest substring with at most K distinct characters
- Count subarrays with sum divisible by K

---

## Key Takeaways

1. **Identify the window**: What constitutes your window?
2. **Define condition**: What makes a valid window?
3. **Track state**: Use appropriate data structure (frequency map, sum, etc.)
4. **Two pointers**: left moves forward, right expands window
5. **Update answer**: At each valid window state