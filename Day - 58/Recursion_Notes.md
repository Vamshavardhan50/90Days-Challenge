# Recursion - Detailed Notes

## 1) What Is Recursion?

Recursion is a programming technique where a function solves a problem by calling itself on smaller subproblems.

A recursive solution usually has:

- A **base case**: condition where recursion stops.
- A **recursive case**: function calls itself with a smaller input.

Think of recursion as: "Solve one small part now, and let the same function solve the rest."

---

## 2) Why Use Recursion?

Recursion is useful when a problem has a naturally repeating structure.

Common use cases:

- Trees (DFS, traversals)
- Graph DFS/backtracking
- Divide and conquer (Merge Sort, Quick Sort)
- Combinatorics (subsets, permutations)
- Dynamic programming (memoized recursion)

Benefits:

- Code can be short and expressive.
- Mirrors mathematical definitions.

Drawbacks:

- Extra function call overhead.
- Risk of stack overflow for very deep recursion.
- Sometimes iterative solutions are more memory efficient.

---

## 3) Core Terms

- **Call stack**: memory structure storing active function calls.
- **Stack frame**: one function call context (local vars, return info).
- **Recursion depth**: maximum number of active calls at once.
- **Tail recursion**: recursive call is the last operation of function.

---

## 4) Anatomy of a Recursive Function

Template:

```java
returnType function(params) {
    // 1) Base case
    if (stopCondition) {
        return baseValue;
    }

    // 2) Work + recursive call on smaller input
    return combine(currentWork, function(smallerInput));
}
```

Checklist while writing recursion:

1. What is the smallest input?
2. What should happen for smallest input? (base case)
3. How does one call reduce problem size?
4. Will every path eventually hit base case?

---

## 5) Dry Run Example: Factorial

Definition:

- `n! = n * (n-1)!`
- `0! = 1`

```java
static int factorial(int n) {
    if (n == 0) return 1;       // base case
    return n * factorial(n - 1); // recursive case
}
```

Call flow for `factorial(4)`:

- `factorial(4)` -> `4 * factorial(3)`
- `factorial(3)` -> `3 * factorial(2)`
- `factorial(2)` -> `2 * factorial(1)`
- `factorial(1)` -> `1 * factorial(0)`
- `factorial(0)` -> `1` (base)

Unwinding:

- `factorial(1) = 1 * 1 = 1`
- `factorial(2) = 2 * 1 = 2`
- `factorial(3) = 3 * 2 = 6`
- `factorial(4) = 4 * 6 = 24`

Complexity:

- Time: `O(n)`
- Space (stack): `O(n)`

---

## 6) Preorder, Inorder, Postorder Thinking (For Recursion Tracing)

In recursion, you can place work in 3 places:

```java
void f(int n) {
    if (n == 0) return;

    // Preorder: work before recursive call
    f(n - 1);
    // Inorder: work between recursive calls (if 2 calls)
    // Postorder: work after recursive call
}
```

Use this to control output pattern.

Example:

```java
static void print1ToN(int n) {
    if (n == 0) return;
    print1ToN(n - 1);
    System.out.print(n + " "); // postorder style
}
```

---

## 7) Types of Recursion

### a) Direct recursion

Function calls itself directly.

### b) Indirect recursion

Function A calls B, and B calls A.

### c) Linear recursion

Each call makes only one recursive call.
Example: factorial, sum of first N.

### d) Binary/Tree recursion

Each call makes two recursive calls.
Example: Fibonacci (naive).

### e) Tail recursion

Recursive call is the last statement.

```java
static int sumTail(int n, int acc) {
    if (n == 0) return acc;
    return sumTail(n - 1, acc + n);
}
```

Note: Java does not guarantee tail call optimization.

---

## 8) Classic Problems and Patterns

### 8.1 Sum of first N numbers

```java
static int sumN(int n) {
    if (n == 0) return 0;
    return n + sumN(n - 1);
}
```

### 8.2 Reverse an array using recursion

```java
static void reverse(int[] arr, int l, int r) {
    if (l >= r) return;
    int t = arr[l];
    arr[l] = arr[r];
    arr[r] = t;
    reverse(arr, l + 1, r - 1);
}
```

### 8.3 Check palindrome (string)

```java
static boolean isPalindrome(String s, int l, int r) {
    if (l >= r) return true;
    if (s.charAt(l) != s.charAt(r)) return false;
    return isPalindrome(s, l + 1, r - 1);
}
```

### 8.4 Fibonacci (naive)

```java
static int fib(int n) {
    if (n <= 1) return n;
    return fib(n - 1) + fib(n - 2);
}
```

Complexity:

- Time: `O(2^n)` (very slow)
- Space: `O(n)` stack

Better approach: DP/memoization.

---

## 9) Recurrence Relations (Time Complexity)

Many recursive algorithms are analyzed by recurrence equations:

- Linear recursion: `T(n) = T(n-1) + O(1)` => `O(n)`
- Binary recursion (naive fib): `T(n) = T(n-1) + T(n-2) + O(1)` => exponential
- Merge sort: `T(n) = 2T(n/2) + O(n)` => `O(n log n)`

For divide-and-conquer, Master theorem is often used.

---

## 10) Backtracking (Recursion + Undo)

Backtracking is recursion where we:

1. Choose an option.
2. Recurse.
3. Undo the choice (backtrack).

Used in:

- Subsets
- Permutations
- N-Queens
- Sudoku
- Combination sum

Skeleton:

```java
void backtrack(State state, List<Result> ans) {
    if (isSolution(state)) {
        ans.add(copyOf(state));
        return;
    }

    for (Choice c : choices(state)) {
        apply(state, c);      // choose
        backtrack(state, ans); // explore
        undo(state, c);       // un-choose
    }
}
```

---

## 11) Memoization (Top-Down DP)

If recursion recomputes same states, store results in a cache.

Example: Fibonacci memoized

```java
static int fibMemo(int n, int[] dp) {
    if (n <= 1) return n;
    if (dp[n] != -1) return dp[n];
    dp[n] = fibMemo(n - 1, dp) + fibMemo(n - 2, dp);
    return dp[n];
}
```

Complexity:

- Time: `O(n)`
- Space: `O(n)` (dp + stack)

---

## 12) Common Mistakes in Recursion

1. Missing base case.
2. Base case never reachable.
3. Wrong recursive transition (not reducing problem size).
4. Modifying shared data without undo in backtracking.
5. Assuming recursion is always faster than iteration.
6. Ignoring stack overflow risk for large input.

---

## 13) How To Convert Recursion to Iteration

Use an explicit stack.

General idea:

- Recursive call stack -> manual `Stack<Frame>`.
- Push initial state.
- Loop until stack empty.

This is useful when recursion depth can be huge.

---

## 14) Interview Strategy for Recursive Questions

When asked a recursion problem:

1. Define function meaning clearly.
2. Identify parameters that represent state.
3. Write base case first.
4. Write recursive transition.
5. Do a quick dry run on small input.
6. State time/space complexity.
7. Mention optimization (memoization/tabulation) if overlapping subproblems exist.

---

## 15) Practice Set (Recommended)

Easy:

- Print 1 to N / N to 1
- Sum of first N
- Factorial
- Palindrome check

Medium:

- Power function (`x^n` with fast exponentiation)
- Subsets
- Subsequences with sum K
- Combination Sum

Hard:

- N-Queens
- Sudoku solver
- Word search

---

## 16) Quick Revision Cheatsheet

- Recursion = function calls itself on smaller input.
- Must have base case + progress toward base case.
- Stack space usually equals recursion depth.
- Backtracking = choose, recurse, undo.
- Memoization removes repeated subproblems.
- If depth is too large, prefer iterative or optimize recursion.

---

## 17) One Good Mental Model

Treat each function call as a "black box":

- Assume smaller recursive call gives correct answer.
- Focus only on combining that answer with current step.

This mindset makes recursive coding much easier.

---

## 18) Bonus: Fast Power (Important Recursion Example)

`pow(x, n)` in `O(log n)`:

```java
static long fastPow(long x, long n) {
    if (n == 0) return 1;

    long half = fastPow(x, n / 2);
    long result = half * half;

    if ((n & 1) == 1) result *= x;
    return result;
}
```

Complexity:

- Time: `O(log n)`
- Space: `O(log n)`

---

Keep practicing dry runs by drawing the call stack for small inputs. That single habit improves recursion speed and accuracy the most.
