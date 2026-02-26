# LinkedList - Complete Guide for Competitive Programming

---

## 1. What is a LinkedList?

A **LinkedList** is a linear data structure where elements are stored in **nodes**, and each node points to the next node in the sequence.

### Key Characteristics:

- **Non-contiguous memory**: Elements are NOT stored in consecutive memory locations
- **Dynamic size**: Can grow or shrink at runtime
- **Each element (node) contains**:
  - `data` → actual value
  - `next` → reference/pointer to the next node

### Visual Representation:

```
[data|next] → [data|next] → [data|next] → NULL
    ↑
   HEAD
```

### Types of LinkedList:

| Type                    | Description                                      |
| ----------------------- | ------------------------------------------------ |
| **Singly LinkedList**   | Each node points to the next node only           |
| **Doubly LinkedList**   | Each node points to both next and previous nodes |
| **Circular LinkedList** | Last node points back to the first node          |

---

## 2. Where is LinkedList Used?

### Real-World Applications:

| Application               | Why LinkedList?                         |
| ------------------------- | --------------------------------------- |
| **Browser History**       | Back/Forward navigation (Doubly LL)     |
| **Music Playlist**        | Next/Previous song functionality        |
| **Undo/Redo Operations**  | Stack-like behavior with dynamic size   |
| **Memory Allocation**     | OS uses LL for free memory blocks       |
| **Hash Table Chaining**   | Collision handling in HashMap           |
| **Graph Adjacency List**  | Efficient graph representation          |
| **Polynomial Arithmetic** | Each term as a node                     |
| **LRU Cache**             | Doubly LL + HashMap for O(1) operations |

### When to Use LinkedList over Array:

| Use LinkedList When                               | Use Array When                  |
| ------------------------------------------------- | ------------------------------- |
| Frequent insertions/deletions at beginning/middle | Frequent random access by index |
| Unknown size at compile time                      | Known fixed size                |
| Memory is fragmented                              | Need cache-friendly traversal   |
| No need for random access                         | Need O(1) access by index       |

---

## 3. LinkedList Class in Java

### Node Class Definition (Custom Implementation):

```java
// For Singly LinkedList
class Node {
    int data;       // can be any data type
    Node next;      // reference to next node

    // Constructor
    Node(int data) {
        this.data = data;
        this.next = null;
    }

    // Constructor with next pointer
    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}
```

### For Doubly LinkedList:

```java
class Node {
    int data;
    Node next;
    Node prev;      // additional pointer to previous node

    Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
```

### Java's Built-in LinkedList Class:

```java
import java.util.LinkedList;

// Creating LinkedList
LinkedList<Integer> list = new LinkedList<>();

// Common Methods
list.add(10);           // Add at end - O(1)
list.addFirst(5);       // Add at beginning - O(1)
list.addLast(15);       // Add at end - O(1)
list.add(2, 100);       // Add at index 2 - O(n)

list.get(0);            // Get element - O(n)
list.getFirst();        // Get first - O(1)
list.getLast();         // Get last - O(1)

list.remove();          // Remove first - O(1)
list.removeFirst();     // Remove first - O(1)
list.removeLast();      // Remove last - O(1)
list.remove(2);         // Remove at index - O(n)

list.size();            // Size of list - O(1)
list.isEmpty();         // Check empty - O(1)
list.contains(10);      // Search element - O(n)
```

---

## 4. Memory Space Used

### Memory Layout:

#### Array:

```
Memory: [10][20][30][40][50] → Contiguous blocks
         ↑
     All in sequence
```

#### LinkedList:

```
Memory: [10|ptr] ... [20|ptr] ... [30|ptr] ... [40|ptr] ... [50|NULL]
           ↓            ↓            ↓            ↓
        addr:100    addr:250     addr:180     addr:420
        (scattered in memory)
```

### Memory Calculation:

| Data Structure       | Memory per Element                                    |
| -------------------- | ----------------------------------------------------- |
| **Array (int)**      | 4 bytes (only data)                                   |
| **LinkedList (int)** | 4 bytes (data) + 4/8 bytes (pointer) = **8-12 bytes** |

### Detailed Memory for LinkedList Node:

```
┌─────────────────────────────────────┐
│           Node Object               │
├─────────────────────────────────────┤
│ Object Header    : 12 bytes (Java)  │
│ int data         : 4 bytes          │
│ Node next (ref)  : 4/8 bytes        │
│ Padding          : variable         │
├─────────────────────────────────────┤
│ Total            : ~24-32 bytes     │
└─────────────────────────────────────┘
```

### Comparison Table:

| Aspect                 | Array                | LinkedList              |
| ---------------------- | -------------------- | ----------------------- |
| **Memory per element** | 4 bytes              | 24-32 bytes             |
| **Memory allocation**  | Contiguous           | Non-contiguous          |
| **Cache performance**  | Excellent (locality) | Poor (scattered)        |
| **Extra overhead**     | None                 | Pointer + Object header |

### Key Point for CP:

> **LinkedList uses ~4-6x more memory than arrays** for the same data. Consider this for memory-constrained problems.

---

## 5. Difference Between `Node` and `Node*` (node pointer)

### In C/C++:

| `Node`                    | `Node*`                         |
| ------------------------- | ------------------------------- |
| Actual node object        | Pointer to a node object        |
| Stores data directly      | Stores memory address           |
| Created on stack (local)  | Points to heap memory           |
| Fixed size = sizeof(Node) | Size = 4/8 bytes (pointer size) |

```cpp
// C++ Example
Node n1;           // n1 IS a Node (object itself)
Node* ptr = &n1;   // ptr POINTS TO a Node (stores address)

// Accessing
n1.data = 10;      // Direct access
ptr->data = 10;    // Access through pointer
(*ptr).data = 10;  // Same as above
```

### Visual Representation:

```
Stack Memory           Heap Memory
┌──────────┐          ┌───────────────┐
│ Node n1  │          │   Node obj    │
│ data: 10 │          │   data: 20    │
│ next:NULL│          │   next: NULL  │
└──────────┘          └───────────────┘
                            ↑
┌──────────┐                │
│ Node* ptr│ ───────────────┘
│ (address)│
└──────────┘
```

### In Java:

> **Java does NOT have explicit pointers like C/C++**

```java
Node node;          // This is actually a REFERENCE (similar to pointer)
node = new Node(5); // 'node' holds reference to the object
```

| Java Reference       | C++ Pointer               |
| -------------------- | ------------------------- |
| `Node node`          | `Node* node`              |
| `node.data`          | `node->data`              |
| Cannot do arithmetic | Can do pointer arithmetic |
| Garbage collected    | Manual deallocation       |

### Key Point:

> In Java, **all object variables are references** (internally similar to pointers). When you write `Node head`, `head` is a reference variable that can point to a Node object.

---

## 6. Array to LinkedList Conversion

### Method 1: Iterative Approach

```java
public static Node arrayToLL(int[] arr) {
    if (arr.length == 0) return null;

    // Create head with first element
    Node head = new Node(arr[0]);
    Node current = head;

    // Iterate and create nodes
    for (int i = 1; i < arr.length; i++) {
        Node newNode = new Node(arr[i]);
        current.next = newNode;  // Link current to new node
        current = newNode;       // Move current forward
    }

    return head;
}
```

### Dry Run:

```
Input: arr = [1, 2, 3, 4]

Step 1: head = [1|null], current = head
Step 2: newNode = [2|null], current.next = [2|null], current = [2|null]
        LL: [1] → [2]
Step 3: newNode = [3|null], current.next = [3|null], current = [3|null]
        LL: [1] → [2] → [3]
Step 4: newNode = [4|null], current.next = [4|null], current = [4|null]
        LL: [1] → [2] → [3] → [4] → null

Output: head pointing to [1] → [2] → [3] → [4] → null
```

### Method 2: Building from End (Reverse)

```java
public static Node arrayToLLReverse(int[] arr) {
    Node head = null;

    // Build from end to start
    for (int i = arr.length - 1; i >= 0; i--) {
        Node newNode = new Node(arr[i]);
        newNode.next = head;
        head = newNode;
    }

    return head;
}
```

### Time & Space Complexity:

| Approach  | Time | Space              |
| --------- | ---- | ------------------ |
| Iterative | O(n) | O(n) for new nodes |
| From End  | O(n) | O(n) for new nodes |

---

## 7. Traversal of LinkedList

### Basic Traversal (Print All Elements):

```java
public static void traverse(Node head) {
    Node temp = head;  // Use temp to preserve head reference

    while (temp != null) {
        System.out.print(temp.data + " → ");
        temp = temp.next;  // Move to next node
    }
    System.out.println("NULL");
}
```

### Dry Run:

```
Input: head → [1] → [2] → [3] → [4] → null

Iteration 1: temp = [1], print "1 → ", temp = [2]
Iteration 2: temp = [2], print "2 → ", temp = [3]
Iteration 3: temp = [3], print "3 → ", temp = [4]
Iteration 4: temp = [4], print "4 → ", temp = null
Loop ends: temp == null

Output: 1 → 2 → 3 → 4 → NULL
```

### Traversal Patterns:

```java
// Pattern 1: While loop (most common)
while (temp != null) {
    // process temp.data
    temp = temp.next;
}

// Pattern 2: For loop style
for (Node temp = head; temp != null; temp = temp.next) {
    // process temp.data
}

// Pattern 3: When you need previous node
Node prev = null;
Node curr = head;
while (curr != null) {
    // prev is the node before curr
    prev = curr;
    curr = curr.next;
}
```

### Common Traversal Operations:

```java
// Find Maximum
public static int findMax(Node head) {
    int max = Integer.MIN_VALUE;
    Node temp = head;
    while (temp != null) {
        max = Math.max(max, temp.data);
        temp = temp.next;
    }
    return max;
}

// Find Sum
public static int findSum(Node head) {
    int sum = 0;
    Node temp = head;
    while (temp != null) {
        sum += temp.data;
        temp = temp.next;
    }
    return sum;
}
```

### Time Complexity: O(n)

### Space Complexity: O(1)

---

## 8. Length of LinkedList

### Iterative Approach:

```java
public static int length(Node head) {
    int count = 0;
    Node temp = head;

    while (temp != null) {
        count++;
        temp = temp.next;
    }

    return count;
}
```

### Dry Run:

```
Input: head → [5] → [10] → [15] → [20] → null

count = 0, temp = [5]
count = 1, temp = [10]
count = 2, temp = [15]
count = 3, temp = [20]
count = 4, temp = null (loop ends)

Output: 4
```

### Recursive Approach:

```java
public static int lengthRecursive(Node head) {
    // Base case: empty list
    if (head == null) {
        return 0;
    }

    // Recursive case: 1 + length of remaining list
    return 1 + lengthRecursive(head.next);
}
```

### Recursion Tree:

```
lengthRecursive([5] → [10] → [15] → null)
    = 1 + lengthRecursive([10] → [15] → null)
    = 1 + (1 + lengthRecursive([15] → null))
    = 1 + (1 + (1 + lengthRecursive(null)))
    = 1 + (1 + (1 + 0))
    = 1 + (1 + 1)
    = 1 + 2
    = 3
```

### Complexity Analysis:

| Approach  | Time | Space             |
| --------- | ---- | ----------------- |
| Iterative | O(n) | O(1)              |
| Recursive | O(n) | O(n) - call stack |

> **CP Tip**: Prefer iterative for length calculation to avoid stack overflow on large lists.

---

## 9. Search an Element in LinkedList

### Linear Search (Only Option for LL):

```java
public static boolean search(Node head, int target) {
    Node temp = head;

    while (temp != null) {
        if (temp.data == target) {
            return true;  // Found
        }
        temp = temp.next;
    }

    return false;  // Not found
}
```

### Return Index (Position):

```java
public static int searchIndex(Node head, int target) {
    Node temp = head;
    int index = 0;

    while (temp != null) {
        if (temp.data == target) {
            return index;  // Return 0-based index
        }
        temp = temp.next;
        index++;
    }

    return -1;  // Not found
}
```

### Dry Run:

```
Input: head → [10] → [20] → [30] → [40] → null, target = 30

temp = [10], index = 0, 10 ≠ 30, move next
temp = [20], index = 1, 20 ≠ 30, move next
temp = [30], index = 2, 30 == 30, return 2 ✓

Output: 2
```

### Recursive Search:

```java
public static boolean searchRecursive(Node head, int target) {
    // Base case 1: Empty list
    if (head == null) {
        return false;
    }

    // Base case 2: Found
    if (head.data == target) {
        return true;
    }

    // Recursive case: Search in rest of list
    return searchRecursive(head.next, target);
}
```

### Complexity Analysis:

| Operation  | Time | Space (Iterative) | Space (Recursive) |
| ---------- | ---- | ----------------- | ----------------- |
| Search     | O(n) | O(1)              | O(n)              |
| Best Case  | O(1) | O(1)              | O(1)              |
| Worst Case | O(n) | O(1)              | O(n)              |

---

## Quick Reference - Time Complexity

| Operation           | Array  | LinkedList       |
| ------------------- | ------ | ---------------- |
| Access by Index     | O(1)   | O(n)             |
| Search              | O(n)   | O(n)             |
| Insert at Beginning | O(n)   | **O(1)**         |
| Insert at End       | O(1)\* | O(n) or O(1)\*\* |
| Insert at Middle    | O(n)   | O(n)             |
| Delete at Beginning | O(n)   | **O(1)**         |
| Delete at End       | O(1)   | O(n)             |
| Delete at Middle    | O(n)   | O(n)             |

\*Amortized for dynamic arrays  
\*\*O(1) if tail pointer maintained

---

## Complete Implementation Template

```java
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedList {
    private Node head;

    // Convert array to LL
    public void fromArray(int[] arr) {
        if (arr.length == 0) return;
        head = new Node(arr[0]);
        Node curr = head;
        for (int i = 1; i < arr.length; i++) {
            curr.next = new Node(arr[i]);
            curr = curr.next;
        }
    }

    // Traverse and print
    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " → ");
            temp = temp.next;
        }
        System.out.println("NULL");
    }

    // Get length
    public int length() {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    // Search element
    public boolean search(int target) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == target) return true;
            temp = temp.next;
        }
        return false;
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        int[] arr = {1, 2, 3, 4, 5};

        ll.fromArray(arr);
        ll.display();           // 1 → 2 → 3 → 4 → 5 → NULL
        System.out.println("Length: " + ll.length());  // 5
        System.out.println("Search 3: " + ll.search(3)); // true
        System.out.println("Search 10: " + ll.search(10)); // false
    }
}
```

---

## Key Points for CP

1. **Always check for null** before accessing `.data` or `.next`
2. **Use temp variable** to traverse, never modify `head` directly
3. **LinkedList search is O(n)** - binary search NOT possible
4. **Memory overhead is significant** - use arrays when possible for memory constraints
5. **Insert/Delete at head is O(1)** - exploit this in problems
6. **Two-pointer technique** is powerful for LL problems (slow/fast pointers)

---

_Created for 90 Days DSA Challenge - Day 45_
