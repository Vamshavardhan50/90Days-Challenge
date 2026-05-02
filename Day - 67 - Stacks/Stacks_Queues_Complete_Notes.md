# Stacks and Queues - Complete Notes & Implementation

## Table of Contents
1. [Stacks - Fundamentals](#stacks---fundamentals)
2. [Stack Implementation using Array](#stack-implementation-using-array)
3. [Stack Implementation using Linked List](#stack-implementation-using-linked-list)
4. [Queues - Fundamentals](#queues---fundamentals)
5. [Queue Implementation using Array](#queue-implementation-using-array)
6. [Queue Implementation using Linked List](#queue-implementation-using-linked-list)
7. [Stack using Queue](#stack-using-queue)
8. [Queue using Stack](#queue-using-stack)
9. [Deque (Double Ended Queue)](#deque-double-ended-queue)
10. [Common Problems](#common-problems)

---

# STACKS - FUNDAMENTALS

## What is a Stack?

A **Stack** is a linear data structure that follows the **LIFO (Last In First Out)** principle.
- Last element added is the first one to be removed
- Think of a stack of plates: you add/remove from the top

## Key Operations

| Operation | Description | Complexity |
|-----------|-------------|------------|
| `push(x)` | Add element to top | O(1) |
| `pop()` | Remove element from top | O(1) |
| `peek()` or `top()` | View top element | O(1) |
| `isEmpty()` | Check if stack is empty | O(1) |
| `size()` | Get number of elements | O(1) |

## Real-World Examples

- Browser back button (history)
- Undo/Redo in text editors
- Expression evaluation
- Backtracking algorithms
- Function call stack in memory

---

# STACK IMPLEMENTATION USING ARRAY

## Dynamic Array Implementation

```java
public class StackArray<T> {
    private T[] elements;
    private int top;
    private static final int INITIAL_CAPACITY = 10;
    
    // Constructor
    @SuppressWarnings("unchecked")
    public StackArray() {
        elements = new Object[INITIAL_CAPACITY];
        top = -1;
    }
    
    // Push operation - O(1) amortized
    public void push(T value) {
        if (top == elements.length - 1) {
            resize();  // Grow array when full
        }
        elements[++top] = value;
    }
    
    // Pop operation - O(1)
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T value = elements[top];
        elements[top] = null;  // Help garbage collection
        top--;
        
        // Shrink array if it's 25% full
        if (top > 0 && top == elements.length / 4) {
            resize(elements.length / 2);
        }
        return value;
    }
    
    // Peek operation - O(1)
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements[top];
    }
    
    // Check if empty - O(1)
    public boolean isEmpty() {
        return top == -1;
    }
    
    // Get size - O(1)
    public int size() {
        return top + 1;
    }
    
    // Resize array - O(n)
    @SuppressWarnings("unchecked")
    private void resize() {
        resize(elements.length * 2);
    }
    
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        T[] newElements = new Object[capacity];
        System.arraycopy(elements, 0, newElements, 0, top + 1);
        elements = newElements;
    }
    
    // Print stack
    public void print() {
        System.out.print("Stack (top to bottom): ");
        for (int i = top; i >= 0; i--) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }
}

// Example Usage
public static void main(String[] args) {
    StackArray<Integer> stack = new StackArray<>();
    
    // Push operations
    stack.push(10);
    stack.push(20);
    stack.push(30);
    stack.push(40);
    
    stack.print();  // Stack (top to bottom): 40 30 20 10
    
    System.out.println("Peek: " + stack.peek());  // 40
    System.out.println("Pop: " + stack.pop());    // 40
    System.out.println("Size: " + stack.size()); // 3
    
    stack.print();  // Stack (top to bottom): 30 20 10
    
    System.out.println("Is empty? " + stack.isEmpty());  // false
}
```

### Advantages
- ✅ Fast access to top element O(1)
- ✅ Memory efficient
- ✅ Simple implementation

### Disadvantages
- ❌ Fixed size initially (need resizing)
- ❌ Wasted space when shrinking

---

# STACK IMPLEMENTATION USING LINKED LIST

## Node-based Implementation

```java
public class StackLinkedList<T> {
    
    // Node inner class
    private class Node {
        T data;
        Node next;
        
        Node(T data) {
            this.data = data;
        }
    }
    
    private Node top;
    private int size;
    
    // Constructor
    public StackLinkedList() {
        top = null;
        size = 0;
    }
    
    // Push operation - O(1)
    public void push(T value) {
        Node newNode = new Node(value);
        newNode.next = top;
        top = newNode;
        size++;
    }
    
    // Pop operation - O(1)
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T value = top.data;
        top = top.next;
        size--;
        return value;
    }
    
    // Peek operation - O(1)
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.data;
    }
    
    // Check if empty - O(1)
    public boolean isEmpty() {
        return top == null;
    }
    
    // Get size - O(1)
    public int size() {
        return size;
    }
    
    // Print stack
    public void print() {
        System.out.print("Stack (top to bottom): ");
        Node current = top;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}

// Example Usage
public static void main(String[] args) {
    StackLinkedList<Integer> stack = new StackLinkedList<>();
    
    // Push operations
    stack.push(5);
    stack.push(15);
    stack.push(25);
    
    stack.print();  // Stack (top to bottom): 25 15 5
    
    System.out.println("Peek: " + stack.peek());     // 25
    System.out.println("Pop: " + stack.pop());       // 25
    System.out.println("Size: " + stack.size());    // 2
    
    stack.print();  // Stack (top to bottom): 15 5
}
```

### Advantages
- ✅ Dynamic size (no resizing needed)
- ✅ No wasted space
- ✅ Efficient for frequent insertions/deletions

### Disadvantages
- ❌ Extra memory for pointers
- ❌ Cache unfriendly (non-contiguous memory)
- ❌ Slightly slower than array (pointer chase)

---

# QUEUES - FUNDAMENTALS

## What is a Queue?

A **Queue** is a linear data structure that follows the **FIFO (First In First Out)** principle.
- First element added is the first one to be removed
- Think of a queue at a ticket counter: first person in is first person out

## Key Operations

| Operation | Description | Complexity |
|-----------|-------------|------------|
| `enqueue(x)` | Add element to rear | O(1) |
| `dequeue()` | Remove element from front | O(1) |
| `front()` | View front element | O(1) |
| `rear()` | View rear element | O(1) |
| `isEmpty()` | Check if queue is empty | O(1) |
| `size()` | Get number of elements | O(1) |

## Real-World Examples

- Print job queue in printers
- Customer service lines
- BFS (Breadth First Search) traversal
- Task scheduling in operating systems
- Message queues in systems

---

# QUEUE IMPLEMENTATION USING ARRAY

## Circular Queue (Optimized Array Implementation)

```java
public class QueueArray<T> {
    private T[] elements;
    private int front;
    private int rear;
    private int size;
    private static final int INITIAL_CAPACITY = 10;
    
    // Constructor
    @SuppressWarnings("unchecked")
    public QueueArray() {
        elements = new Object[INITIAL_CAPACITY];
        front = 0;
        rear = -1;
        size = 0;
    }
    
    // Enqueue operation - O(1) amortized
    public void enqueue(T value) {
        if (size == elements.length) {
            resize();
        }
        rear = (rear + 1) % elements.length;
        elements[rear] = value;
        size++;
    }
    
    // Dequeue operation - O(1)
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T value = elements[front];
        elements[front] = null;  // Help garbage collection
        front = (front + 1) % elements.length;
        size--;
        
        // Shrink if only 25% full
        if (size > 0 && size == elements.length / 4) {
            resize(elements.length / 2);
        }
        return value;
    }
    
    // Front operation - O(1)
    public T front() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return elements[front];
    }
    
    // Rear operation - O(1)
    public T rear() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return elements[rear];
    }
    
    // Check if empty - O(1)
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Get size - O(1)
    public int size() {
        return size;
    }
    
    // Resize array - O(n)
    @SuppressWarnings("unchecked")
    private void resize() {
        resize(elements.length * 2);
    }
    
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        T[] newElements = new Object[capacity];
        
        // Copy elements in order
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[(front + i) % elements.length];
        }
        
        elements = newElements;
        front = 0;
        rear = size - 1;
    }
    
    // Print queue
    public void print() {
        System.out.print("Queue (front to rear): ");
        for (int i = 0; i < size; i++) {
            System.out.print(elements[(front + i) % elements.length] + " ");
        }
        System.out.println();
    }
}

// Example Usage
public static void main(String[] args) {
    QueueArray<Integer> queue = new QueueArray<>();
    
    // Enqueue operations
    queue.enqueue(10);
    queue.enqueue(20);
    queue.enqueue(30);
    queue.enqueue(40);
    
    queue.print();  // Queue (front to rear): 10 20 30 40
    
    System.out.println("Front: " + queue.front());    // 10
    System.out.println("Rear: " + queue.rear());      // 40
    System.out.println("Dequeue: " + queue.dequeue()); // 10
    System.out.println("Size: " + queue.size());     // 3
    
    queue.print();  // Queue (front to rear): 20 30 40
}
```

### Key Points:
- **Circular Queue:** Uses modulo to wrap around (avoids wasted space)
- **Why circular?** Without it, queue wastes front space as elements are dequeued

### Advantages
- ✅ O(1) operations
- ✅ Memory efficient with circular design
- ✅ No shifting needed

### Disadvantages
- ❌ Fixed size initially
- ❌ Complex indexing with circular logic

---

# QUEUE IMPLEMENTATION USING LINKED LIST

## Simple and Elegant Implementation

```java
public class QueueLinkedList<T> {
    
    // Node inner class
    private class Node {
        T data;
        Node next;
        
        Node(T data) {
            this.data = data;
        }
    }
    
    private Node front;
    private Node rear;
    private int size;
    
    // Constructor
    public QueueLinkedList() {
        front = null;
        rear = null;
        size = 0;
    }
    
    // Enqueue operation - O(1)
    public void enqueue(T value) {
        Node newNode = new Node(value);
        
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
        size++;
    }
    
    // Dequeue operation - O(1)
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T value = front.data;
        front = front.next;
        size--;
        
        if (isEmpty()) {
            rear = null;
        }
        return value;
    }
    
    // Front operation - O(1)
    public T front() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return front.data;
    }
    
    // Rear operation - O(1)
    public T rear() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return rear.data;
    }
    
    // Check if empty - O(1)
    public boolean isEmpty() {
        return front == null;
    }
    
    // Get size - O(1)
    public int size() {
        return size;
    }
    
    // Print queue
    public void print() {
        System.out.print("Queue (front to rear): ");
        Node current = front;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}

// Example Usage
public static void main(String[] args) {
    QueueLinkedList<String> queue = new QueueLinkedList<>();
    
    // Enqueue operations
    queue.enqueue("Alice");
    queue.enqueue("Bob");
    queue.enqueue("Charlie");
    
    queue.print();  // Queue (front to rear): Alice Bob Charlie
    
    System.out.println("Front: " + queue.front());      // Alice
    System.out.println("Rear: " + queue.rear());        // Charlie
    System.out.println("Dequeue: " + queue.dequeue()); // Alice
    System.out.println("Size: " + queue.size());       // 2
    
    queue.print();  // Queue (front to rear): Bob Charlie
}
```

### Advantages
- ✅ Dynamic size
- ✅ No resizing overhead
- ✅ Natural FIFO behavior (always remove from front, add to rear)

### Disadvantages
- ❌ Extra memory for pointers
- ❌ Cache unfriendly
- ❌ Slightly slower than array operations

---

# STACK USING QUEUE

## Implement Stack with 2 Queues

```java
public class StackUsingQueue<T> {
    private Queue<T> q1;  // Main queue
    private Queue<T> q2;  // Helper queue
    
    // Constructor
    public StackUsingQueue() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }
    
    // Push operation - O(n) to maintain stack order
    public void push(T value) {
        // Move all elements from q1 to q2
        while (!q1.isEmpty()) {
            q2.offer(q1.poll());
        }
        
        // Add new element to q1
        q1.offer(value);
        
        // Move all elements back from q2 to q1
        while (!q2.isEmpty()) {
            q1.offer(q2.poll());
        }
    }
    
    // Pop operation - O(1)
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return q1.poll();
    }
    
    // Peek operation - O(1)
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return q1.peek();
    }
    
    // Check if empty - O(1)
    public boolean isEmpty() {
        return q1.isEmpty();
    }
    
    // Get size - O(1)
    public int size() {
        return q1.size();
    }
}

// Alternative: Using single queue (more efficient)
public class StackUsingSingleQueue<T> {
    private Queue<T> queue;
    
    public StackUsingSingleQueue() {
        queue = new LinkedList<>();
    }
    
    // Push operation - O(n)
    public void push(T value) {
        queue.offer(value);
        
        // Rotate to bring new element to front
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.offer(queue.poll());
        }
    }
    
    // Pop operation - O(1)
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return queue.poll();
    }
    
    // Peek operation - O(1)
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return queue.peek();
    }
    
    // Check if empty - O(1)
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

// Example Usage
public static void main(String[] args) {
    StackUsingQueue<Integer> stack = new StackUsingQueue<>();
    
    stack.push(1);
    stack.push(2);
    stack.push(3);
    
    System.out.println("Peek: " + stack.peek());  // 3
    System.out.println("Pop: " + stack.pop());    // 3
    System.out.println("Pop: " + stack.pop());    // 2
    System.out.println("Size: " + stack.size()); // 1
}
```

### Complexity Analysis:
- **Push:** O(n) - need to rotate elements
- **Pop:** O(1) - remove from front
- **Peek:** O(1)

---

# QUEUE USING STACK

## Implement Queue with 2 Stacks

```java
public class QueueUsingStack<T> {
    private Stack<T> s1;  // Input stack
    private Stack<T> s2;  // Output stack
    
    // Constructor
    public QueueUsingStack() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    
    // Enqueue operation - O(1)
    public void enqueue(T value) {
        s1.push(value);
    }
    
    // Dequeue operation - O(n) worst case, O(1) amortized
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        
        // Move all from s1 to s2 (reverse order)
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        
        return s2.pop();
    }
    
    // Front operation - O(n) worst case, O(1) amortized
    public T front() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        
        return s2.peek();
    }
    
    // Check if empty - O(1)
    public boolean isEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    }
    
    // Get size - O(1)
    public int size() {
        return s1.size() + s2.size();
    }
}

// Example Usage
public static void main(String[] args) {
    QueueUsingStack<Integer> queue = new QueueUsingStack<>();
    
    queue.enqueue(10);
    queue.enqueue(20);
    queue.enqueue(30);
    
    System.out.println("Front: " + queue.front());     // 10
    System.out.println("Dequeue: " + queue.dequeue()); // 10
    System.out.println("Dequeue: " + queue.dequeue()); // 20
    System.out.println("Size: " + queue.size());      // 1
}
```

### Complexity Analysis:
- **Enqueue:** O(1)
- **Dequeue:** O(n) worst case, O(1) amortized
- **Key idea:** Use s2 as output buffer, only move when needed

### Why efficient?
- Each element moves from s1 to s2 only once
- Amortized: O(n) total operations / n elements = O(1) per element

---

# DEQUE (DOUBLE ENDED QUEUE)

## Deque Implementation using Linked List

```java
public class Deque<T> {
    
    private class Node {
        T data;
        Node next;
        Node prev;
        
        Node(T data) {
            this.data = data;
        }
    }
    
    private Node front;
    private Node rear;
    private int size;
    
    // Constructor
    public Deque() {
        front = null;
        rear = null;
        size = 0;
    }
    
    // Add to front - O(1)
    public void addFront(T value) {
        Node newNode = new Node(value);
        
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            newNode.next = front;
            front.prev = newNode;
            front = newNode;
        }
        size++;
    }
    
    // Add to rear - O(1)
    public void addRear(T value) {
        Node newNode = new Node(value);
        
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            newNode.prev = rear;
            rear = newNode;
        }
        size++;
    }
    
    // Remove from front - O(1)
    public T removeFront() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        T value = front.data;
        front = front.next;
        size--;
        
        if (isEmpty()) {
            rear = null;
        } else {
            front.prev = null;
        }
        return value;
    }
    
    // Remove from rear - O(1)
    public T removeRear() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        T value = rear.data;
        rear = rear.prev;
        size--;
        
        if (isEmpty()) {
            front = null;
        } else {
            rear.next = null;
        }
        return value;
    }
    
    // Get front - O(1)
    public T getFront() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return front.data;
    }
    
    // Get rear - O(1)
    public T getRear() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return rear.data;
    }
    
    // Check if empty - O(1)
    public boolean isEmpty() {
        return front == null;
    }
    
    // Get size - O(1)
    public int size() {
        return size;
    }
}

// Example Usage
public static void main(String[] args) {
    Deque<Integer> deque = new Deque<>();
    
    deque.addFront(10);
    deque.addRear(20);
    deque.addFront(5);
    deque.addRear(25);
    
    System.out.println("Front: " + deque.getFront());  // 5
    System.out.println("Rear: " + deque.getRear());    // 25
    System.out.println("Remove front: " + deque.removeFront()); // 5
    System.out.println("Remove rear: " + deque.removeRear());   // 25
    System.out.println("Size: " + deque.size());      // 2
}
```

### Use Cases:
- Undo/Redo functionality
- Sliding window problems
- Palindrome checking
- Processing both front and rear efficiently

---

# COMMON PROBLEMS

## 1. Valid Parentheses

```java
public class ValidParentheses {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
}
```

## 2. Next Greater Element

```java
public class NextGreaterElement {
    public static int[] nextGreater(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        
        return result;
    }
}
```

## 3. LRU Cache (using Queue + HashMap)

```java
public class LRUCache {
    private int capacity;
    private Map<Integer, Integer> cache;
    private Queue<Integer> queue;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.queue = new LinkedList<>();
    }
    
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        queue.remove(key);  // Remove from queue
        queue.offer(key);   // Add to rear (most recent)
        return cache.get(key);
    }
    
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            queue.remove(key);
        } else if (cache.size() == capacity) {
            int lru = queue.poll();
            cache.remove(lru);
        }
        cache.put(key, value);
        queue.offer(key);
    }
}
```

---

## Comparison Table

| Aspect | Stack | Queue | Deque |
|--------|-------|-------|-------|
| **Order** | LIFO | FIFO | Both |
| **Insertion** | Top | Rear | Both ends |
| **Deletion** | Top | Front | Both ends |
| **Array Impl** | O(1) | O(1) circular | O(1) |
| **LL Impl** | O(1) | O(1) | O(1) |
| **Use Cases** | DFS, Backtrack | BFS, Scheduling | Sliding window |

---

## Key Takeaways

✅ **Use Stack when:**
- Need LIFO behavior
- Solving backtracking problems
- Evaluating expressions
- DFS traversal

✅ **Use Queue when:**
- Need FIFO behavior
- Level-order traversal (BFS)
- Task scheduling
- Producer-consumer pattern

✅ **Use Deque when:**
- Need operations on both ends
- Sliding window problems
- Undo/Redo with history

---

## Time/Space Complexity Summary

| Operation | Array | Linked List |
|-----------|-------|------------|
| Stack Push | O(1)* | O(1) |
| Stack Pop | O(1)* | O(1) |
| Queue Enqueue | O(1)* | O(1) |
| Queue Dequeue | O(1) | O(1) |
| Space | O(n) | O(n) + pointers |

*Amortized with resizing

