# Java Arrays and Strings Notes

## 1. Strings in Java
In Java, a `String` is an object that represents a sequence of characters. It is one of the most widely used classes in Java.

### Key Characteristics:
*   **Non-Primitive**: Strings are objects (reference types), not primitives like `int` or `char`.
*   **Immutable**: Once a String object is created, its data or state cannot be changed. If you modify a string, a new object is created.
*   **String Constant Pool**: To save memory, Java stores string literals in a special memory area called the "String Constant Pool".

### Creating Strings:
1.  **String Literal** (Recommended):
    ```java
    String s1 = "Hello World";
    ```
    *   Checks the pool; if "Hello World" exists, returns reference. If not, creates it in the pool.

2.  **Using `new` Keyword**:
    ```java
    String s2 = new String("Hello World");
    ```
    *   Creates a new object in the Heap memory, even if the literal exists in the pool.

### Common String Methods:
*   `length()`: Returns the number of characters.
    *   `s1.length(); // 11`
*   `charAt(int index)`: Returns the character at the specified index.
    *   `s1.charAt(0); // 'H'`
*   `substring(int beginIndex, int endIndex)`: Returns a part of the string.
    *   `s1.substring(0, 5); // "Hello"`
*   `equals(String another)`: Compares values for equality (case-sensitive).
    *   `s1.equals("hello world"); // false`
*   `equalsIgnoreCase(String another)`: Compares values ignoring case.
*   `indexOf(String str)`: Returns the index of the first occurrence of the substring.
*   `toUpperCase()` / `toLowerCase()`: Converts the case of the string.

---

## 2. Arrays in Java
An array is a collection of variables of the **same type** stored in contiguous memory locations.

### Key Characteristics:
*   **Fixed Size**: Once an array is created, its size cannot be changed.
*   **Zero-Indexed**: The first element is at index 0.
*   **Object**: In Java, arrays are objects.

### Declaring and Initializing Arrays:

1.  **Declaration and Memory Allocation**:
    ```java
    // type[] arrayName = new type[size];
    int[] numbers = new int[5]; // Array of 5 integers, initialized to 0
    ```

2.  **Initialization with Values**:
    ```java
    int[] primes = {2, 3, 5, 7, 11};
    String[] names = {"Alice", "Bob", "Charlie"};
    ```

### Accessing Elements:
You access elements using their index number.
```java
int x = primes[0]; // Access first element (2)
primes[1] = 10;    // Change second element to 10
```

### Array Properties:
*   `length`: Returns the size of the array. **Note**: It is a property, not a method (no parentheses).
    ```java
    int size = primes.length; // 5
    ```

### Iterating Over Arrays:
1.  **For Loop**:
    ```java
    for (int i = 0; i < primes.length; i++) {
        System.out.println(primes[i]);
    }
    ```

2.  **Enhanced For-Loop (For-Each)**:
    ```java
    for (int n : primes) {
        System.out.println(n);
    }
    ```

### Multidimensional Arrays:
Arrays of arrays (e.g., a matrix).
```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6}
};
int val = matrix[1][2]; // Accesses row 1, column 2 (value 6)
```
