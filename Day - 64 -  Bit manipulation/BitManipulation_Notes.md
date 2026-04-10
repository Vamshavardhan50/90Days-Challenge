# Bit Manipulation - Complete Notes

## Table of Contents
1. [Swap 2 Numbers](#swap-2-numbers)
2. [Check if ith Bit is Set](#check-if-ith-bit-is-set)
3. [Extract the ith Bit](#extract-the-ith-bit)
4. [Clear the ith Bit](#clear-the-ith-bit)
5. [Toggle ith Bit](#toggle-ith-bit)
6. [Remove the Last Set Bit](#remove-the-last-set-bit)
7. [Count Number of Set Bits](#count-number-of-set-bits)
8. [Check if Number is Power of 2](#check-if-number-is-power-of-2)

---

## Core Bit Manipulation Operators

Before diving into specific operations, understand these fundamental bitwise operators:

- **AND (`&`)**: Returns 1 if both bits are 1
- **OR (`|`)**: Returns 1 if at least one bit is 1
- **XOR (`^`)**: Returns 1 if bits are different
- **NOT (`~`)**: Inverts all bits (one's complement)
- **LEFT SHIFT (`<<`)**: Multiply by 2^n
- **RIGHT SHIFT (`>>`)**: Divide by 2^n

---

## 1. Swap 2 Numbers

### Without Using Third Variable (XOR Method)

```java
public class BitSwap {
    
    // Method 1: Using XOR (Most elegant for bits)
    public static void swapUsingXOR(int[] nums, int a, int b) {
        nums[a] = nums[a] ^ nums[b];
        nums[b] = nums[a] ^ nums[b];
        nums[a] = nums[a] ^ nums[b];
        
        // Or in one line:
        // nums[a] ^= nums[b];
        // nums[b] ^= nums[a];
        // nums[a] ^= nums[b];
    }
    
    // Method 2: Using arithmetic operations
    public static void swapUsingArithmetic(int[] nums, int a, int b) {
        nums[a] = nums[a] + nums[b];
        nums[b] = nums[a] - nums[b];
        nums[a] = nums[a] - nums[b];
    }
    
    // Method 3: Direct swap (Java standard)
    public static void swapDirect(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}

// Example
public static void main(String[] args) {
    int[] nums = {5, 10};
    
    swapUsingXOR(nums, 0, 1);
    System.out.println("After XOR swap: " + nums[0] + ", " + nums[1]);  // 10, 5
    
    swapUsingArithmetic(nums, 0, 1);
    System.out.println("After arithmetic swap: " + nums[0] + ", " + nums[1]);  // 5, 10
}
```

### Explanation:
**XOR Swap Logic:**
- `a ^= b` → a becomes `a ^ b`
- `b ^= a` → b becomes `b ^ (a ^ b)` = `a`
- `a ^= b` → a becomes `(a ^ b) ^ a` = `b`

**Why it works:** XOR has the property that `x ^ x = 0` and `x ^ 0 = x`

---

## 2. Check if ith Bit is Set

**Definition:** Determine if the bit at position `i` (0-indexed from right) is 1 or 0.

```java
public class CheckIthBit {
    
    // Method 1: Using AND with left-shifted 1
    public static boolean isBitSet(int num, int i) {
        return (num & (1 << i)) != 0;
    }
    
    // Method 2: Using AND with right shift
    public static boolean isBitSet_v2(int num, int i) {
        return ((num >> i) & 1) == 1;
    }
    
    // Method 3: Explicit comparison
    public static boolean isBitSet_v3(int num, int i) {
        int bit = (num >> i) & 1;
        return bit == 1;
    }
}

// Example
public static void main(String[] args) {
    int num = 13;  // Binary: 1101
    
    System.out.println("Is bit 0 set? " + isBitSet(num, 0));  // true (1)
    System.out.println("Is bit 1 set? " + isBitSet(num, 1));  // false (0)
    System.out.println("Is bit 2 set? " + isBitSet(num, 2));  // true (1)
    System.out.println("Is bit 3 set? " + isBitSet(num, 3));  // true (1)
}

// Output:
// Is bit 0 set? true
// Is bit 1 set? false
// Is bit 2 set? true
// Is bit 3 set? true
```

### Explanation:
- `1 << i` creates a number with only the ith bit set
- Example: `1 << 2` = 4 = 0100 (binary)
- `(1101 & 0100) = 0100` which is non-zero, so bit is set
- `>>` shifts all bits right by i positions, then check if LSB is 1

---

## 3. Extract the ith Bit

**Definition:** Get the value (0 or 1) of the bit at position `i`.

```java
public class ExtractIthBit {
    
    // Method 1: Using bit shift and AND
    public static int extractBit(int num, int i) {
        return (num >> i) & 1;
    }
    
    // Method 2: Using ternary operator
    public static int extractBit_v2(int num, int i) {
        return ((num & (1 << i)) != 0) ? 1 : 0;
    }
    
    // Method 3: Return as boolean (same as check)
    public static boolean extractBitAsBoolean(int num, int i) {
        return (num & (1 << i)) != 0;
    }
}

// Example
public static void main(String[] args) {
    int num = 13;  // Binary: 1101
    
    for (int i = 0; i < 4; i++) {
        System.out.println("Bit " + i + ": " + extractBit(num, i));
    }
}

// Output:
// Bit 0: 1
// Bit 1: 0
// Bit 2: 1
// Bit 3: 1
```

### Explanation:
- Shift the bit to the rightmost position: `num >> i`
- Mask to get only the rightmost bit: `& 1`
- Result is 0 or 1

---

## 4. Clear the ith Bit

**Definition:** Set the bit at position `i` to 0 (without affecting other bits).

```java
public class ClearIthBit {
    
    // Method 1: Using AND with NOT (most common)
    public static int clearBit(int num, int i) {
        return num & ~(1 << i);
    }
    
    // Method 2: Using AND with complement mask
    public static int clearBit_v2(int num, int i) {
        int mask = 1 << i;
        return num & (~mask);
    }
    
    // Method 3: Set bit to 0 explicitly
    public static int clearBit_v3(int num, int i) {
        if ((num & (1 << i)) != 0) {  // If bit is 1
            num ^= (1 << i);  // Toggle it to 0
        }
        return num;
    }
}

// Example
public static void main(String[] args) {
    int num = 13;  // Binary: 1101
    
    System.out.println("Original: " + num + " (Binary: " + Integer.toBinaryString(num) + ")");
    
    int result = clearBit(num, 0);
    System.out.println("After clearing bit 0: " + result + " (Binary: " + Integer.toBinaryString(result) + ")");
    // Output: 12 (Binary: 1100)
    
    result = clearBit(num, 2);
    System.out.println("After clearing bit 2: " + result + " (Binary: " + Integer.toBinaryString(result) + ")");
    // Output: 9 (Binary: 1001)
}
```

### Explanation:
- `1 << i` creates mask with only ith bit set: 0...0100...0
- `~(1 << i)` inverts to: 1...1011...1 (ith bit is 0)
- `num & ~(1 << i)` keeps all bits except clears the ith bit

**Visual Example:**
```
num =     1101
mask =    0100  (1 << 2)
~mask =   1011
result =  1001  (1101 & 1011)
```

---

## 5. Toggle ith Bit

**Definition:** Flip the bit at position `i` (0→1 or 1→0).

```java
public class ToggleIthBit {
    
    // Method 1: Using XOR (most elegant)
    public static int toggleBit(int num, int i) {
        return num ^ (1 << i);
    }
    
    // Method 2: Using conditional
    public static int toggleBit_v2(int num, int i) {
        if ((num & (1 << i)) != 0) {
            return num & ~(1 << i);  // Clear bit
        } else {
            return num | (1 << i);   // Set bit
        }
    }
}

// Example
public static void main(String[] args) {
    int num = 13;  // Binary: 1101
    
    System.out.println("Original: " + num + " (Binary: " + Integer.toBinaryString(num) + ")");
    
    for (int i = 0; i < 4; i++) {
        int result = toggleBit(num, i);
        System.out.println("After toggling bit " + i + ": " + result + " (Binary: " + Integer.toBinaryString(result) + ")");
    }
}

// Output:
// Original: 13 (Binary: 1101)
// After toggling bit 0: 12 (Binary: 1100)
// After toggling bit 1: 15 (Binary: 1111)
// After toggling bit 2: 9 (Binary: 1001)
// After toggling bit 3: 5 (Binary: 0101)
```

### Explanation:
- XOR with 1 flips the bit: `0 ^ 1 = 1`, `1 ^ 1 = 0`
- `num ^ (1 << i)` flips only the ith bit

**Visual Example:**
```
num =  1101
mask = 0100  (1 << 2)
XOR:   1001  (1101 ^ 0100)
```

---

## 6. Remove the Last Set Bit (Rightmost)

**Definition:** Set the rightmost bit that is 1 to 0.

```java
public class RemoveLastSetBit {
    
    // Method 1: Using n & (n-1) (most elegant)
    public static int removeLastSetBit(int num) {
        return num & (num - 1);
    }
    
    // Method 2: Explicit approach
    public static int removeLastSetBit_v2(int num) {
        if (num == 0) return 0;
        
        // Find position of rightmost set bit
        int rightmostSetBit = num & -num;
        
        // Remove it
        return num ^ rightmostSetBit;
    }
}

// Example
public static void main(String[] args) {
    int[] numbers = {13, 12, 8, 7, 1};
    
    for (int num : numbers) {
        int result = removeLastSetBit(num);
        System.out.println(num + " (Binary: " + Integer.toBinaryString(num) + 
                          ") → " + result + " (Binary: " + Integer.toBinaryString(result) + ")");
    }
}

// Output:
// 13 (Binary: 1101) → 12 (Binary: 1100)
// 12 (Binary: 1100) → 8 (Binary: 1000)
// 8 (Binary: 1000) → 0 (Binary: 0)
// 7 (Binary: 111) → 6 (Binary: 110)
// 1 (Binary: 1) → 0 (Binary: 0)
```

### Explanation - Why `n & (n-1)` works:

**Key insight:** When you subtract 1 from n:
- All bits after the rightmost set bit flip
- The rightmost set bit becomes 0

**Example: n = 13 (1101)**
```
n     = 1101
n-1   = 1100
n & (n-1) = 1100 = 12
```

**Example: n = 12 (1100)**
```
n     = 1100
n-1   = 1011
n & (n-1) = 1000 = 8
```

**Why this is useful:** `n & (n-1) == 0` checks if n is a power of 2!

---

## 7. Count Number of Set Bits

**Definition:** Count how many bits are set to 1 in a number.

```java
public class CountSetBits {
    
    // Method 1: Using Brian Kernighan's Algorithm (optimal)
    public static int countSetBits(int num) {
        int count = 0;
        while (num > 0) {
            num &= (num - 1);  // Remove rightmost set bit
            count++;
        }
        return count;
    }
    
    // Method 2: Using built-in Java method
    public static int countSetBits_v2(int num) {
        return Integer.bitCount(num);
    }
    
    // Method 3: Using bit shift and AND
    public static int countSetBits_v3(int num) {
        int count = 0;
        while (num > 0) {
            if ((num & 1) == 1) {
                count++;
            }
            num >>= 1;
        }
        return count;
    }
    
    // Method 4: Using right shift and AND with mask
    public static int countSetBits_v4(int num) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((num & (1 << i)) != 0) {
                count++;
            }
        }
        return count;
    }
}

// Example
public static void main(String[] args) {
    int[] numbers = {13, 12, 7, 15, 32};
    
    for (int num : numbers) {
        int count = countSetBits(num);
        System.out.println(num + " (Binary: " + Integer.toBinaryString(num) + 
                          ") has " + count + " set bits");
    }
}

// Output:
// 13 (Binary: 1101) has 3 set bits
// 12 (Binary: 1100) has 2 set bits
// 7 (Binary: 111) has 3 set bits
// 15 (Binary: 1111) has 4 set bits
// 32 (Binary: 100000) has 1 set bits
```

### Complexity Analysis:

| Method | Time | Why |
|--------|------|-----|
| Brian Kernighan | O(k) | k = number of set bits |
| Bit shift | O(32) | Fixed 32 iterations |
| Position check | O(32) | Fixed 32 iterations |
| bitCount() | O(1) | Hardware optimized |

**Recommendation:** Use `Integer.bitCount()` for production code (it's optimized).

---

## 8. Check if Number is Power of 2

**Definition:** Determine if a number is a power of 2 (1, 2, 4, 8, 16, 32, ...).

```java
public class PowerOfTwo {
    
    // Method 1: Using n & (n-1) (most elegant)
    public static boolean isPowerOfTwo(int num) {
        if (num <= 0) return false;
        return (num & (num - 1)) == 0;
    }
    
    // Method 2: Using bit count
    public static boolean isPowerOfTwo_v2(int num) {
        if (num <= 0) return false;
        return Integer.bitCount(num) == 1;
    }
    
    // Method 3: Using logarithm
    public static boolean isPowerOfTwo_v3(int num) {
        if (num <= 0) return false;
        return (Math.log(num) / Math.log(2)) % 1 == 0;
    }
    
    // Method 4: Check if ((num - 1) is all 1s)
    public static boolean isPowerOfTwo_v4(int num) {
        if (num <= 0) return false;
        return (num & -num) == num;  // Isolate rightmost set bit
    }
}

// Example
public static void main(String[] args) {
    int[] numbers = {1, 2, 3, 4, 5, 8, 15, 16, 32, 64, 100};
    
    for (int num : numbers) {
        System.out.println(num + " is power of 2? " + isPowerOfTwo(num));
    }
}

// Output:
// 1 is power of 2? true
// 2 is power of 2? true
// 3 is power of 2? false
// 4 is power of 2? true
// 5 is power of 2? false
// 8 is power of 2? true
// 15 is power of 2? false
// 16 is power of 2? true
// 32 is power of 2? true
// 64 is power of 2? true
// 100 is power of 2? false
```

### Why `n & (n-1) == 0` Works:

**Power of 2 has exactly one set bit:**
```
1  = 0001
2  = 0010
4  = 0100
8  = 1000
16 = 10000
```

**When you subtract 1:**
- All bits after the single set bit flip to 1
- The set bit becomes 0

**Example: n = 8 (1000)**
```
n      = 1000
n-1    = 0111
n & (n-1) = 0000 ✓ (equals 0, so it's power of 2)
```

**Example: n = 6 (0110)**
```
n      = 0110
n-1    = 0101
n & (n-1) = 0100 ✗ (not 0, so NOT power of 2)
```

---

## Quick Reference - All Operations

| Operation | Code | Returns |
|-----------|------|---------|
| Check bit i is set | `(num & (1 << i)) != 0` | boolean |
| Extract bit i | `(num >> i) & 1` | 0 or 1 |
| Set bit i | `num \| (1 << i)` | modified num |
| Clear bit i | `num & ~(1 << i)` | modified num |
| Toggle bit i | `num ^ (1 << i)` | modified num |
| Remove last set bit | `num & (num - 1)` | modified num |
| Count set bits | `Integer.bitCount(num)` | count |
| Is power of 2 | `(num & (num - 1)) == 0` | boolean |
| Get rightmost set bit | `num & -num` | isolated bit |

---

## Common Interview Questions Using These Concepts

1. **Single Number** - Find element appearing once when others appear twice (XOR)
2. **Binary Representation** - Convert to binary, count bits
3. **Reverse Bits** - Reverse binary representation
4. **Number of 1 Bits** - Count set bits
5. **Hamming Distance** - Count differing bits between two numbers
6. **Missing Number** - Find missing in array (XOR)
7. **Power of Two** - Check if number is power of 2
8. **Add Two Numbers Without +** - Using XOR and AND

---

## Tips & Tricks

✅ **Do:**
- Remember `n & (n-1)` removes rightmost set bit
- Use XOR for toggling and swapping
- Use AND to check/extract bits
- Use `|` to set bits

❌ **Don't:**
- Forget edge cases (0, negative numbers)
- Assume signed/unsigned behavior
- Mix up left and right shifts in formulas

---

## Complexity Summary

| Operation | Time | Space |
|-----------|------|-------|
| Any single bit operation | O(1) | O(1) |
| Count set bits (Kernighan) | O(k) | O(1) |
| Count set bits (bitCount) | O(1) | O(1) |
| Power of 2 check | O(1) | O(1) |

---

## Practice Problems

1. Write a function to swap bit i and bit j
2. Find the position of rightmost set bit
3. Count total set bits in numbers 1 to n
4. Rotate bits of a number
5. Find two numbers with odd occurrence in array

