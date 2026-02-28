# <span style="color:#FF6B6B">Doubly Linked List (DLL) — Complete Notes</span>

---

## <span style="color:#4ECDC4">Table of Contents</span>

1. [What is a Doubly Linked List?](#1-what-is-a-doubly-linked-list)
2. [Representation in Java — Syntax & Dry Run](#2-representation-in-java--syntax--dry-run)
3. [Array to DLL](#3-array-to-dll)
4. [Deletion of a Node](#4-deletion-of-a-node)
5. [Insertion of a Node](#5-insertion-of-a-node)

---

## <span style="color:#FF6B6B">1. What is a Doubly Linked List?</span>

A **Doubly Linked List (DLL)** is a linear data structure in which each node contains **three parts**:

| Part | Description |
|------|-------------|
| `prev` | Pointer to the **previous** node |
| `data` | The **actual value** stored |
| `next` | Pointer to the **next** node |

### <span style="color:#FFE66D">Visual Structure</span>

```
 NULL ← [ prev | 10 | next ] ↔ [ prev | 20 | next ] ↔ [ prev | 30 | next ] → NULL
           HEAD                                              TAIL
```

### <span style="color:#FFE66D">Key Differences from Singly Linked List</span>

| Feature | Singly Linked List | Doubly Linked List |
|---------|-------------------|-------------------|
| Pointers per node | 1 (`next`) | 2 (`prev` + `next`) |
| Traversal | One direction only | Both directions |
| Memory | Less | More |
| Deletion | Harder (need prev node) | Easier (has prev pointer) |
| Insertion | Harder | Easier |

### <span style="color:#FFE66D">Advantages of DLL</span>

- <span style="color:#6BCB77">✔</span> Can traverse in **both forward and backward** directions
- <span style="color:#6BCB77">✔</span> **Deletion** is more efficient — no need to track previous node separately
- <span style="color:#6BCB77">✔</span> Useful in **browser history**, **undo/redo** operations, **LRU Cache**

### <span style="color:#FFE66D">Disadvantages of DLL</span>

- <span style="color:#FF6B6B">✘</span> Extra memory for `prev` pointer
- <span style="color:#FF6B6B">✘</span> More complex implementation

---

## <span style="color:#FF6B6B">2. Representation in Java — Syntax & Dry Run</span>

### <span style="color:#FFE66D">Node Class</span>

```java
// Each node of Doubly Linked List
class Node {
    int data;       // stores the value
    Node prev;      // pointer to previous node
    Node next;      // pointer to next node

    // Constructor
    Node(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}
```

### <span style="color:#FFE66D">Creating a Simple DLL</span>

```java
public class DoublyLinkedList {
    Node head; // head points to the first node

    public static void main(String[] args) {

        // Step 1: Create nodes
        Node node1 = new Node(10);
        Node node2 = new Node(20);
        Node node3 = new Node(30);

        // Step 2: Link nodes forward (next pointers)
        node1.next = node2;
        node2.next = node3;
        node3.next = null;

        // Step 3: Link nodes backward (prev pointers)
        node1.prev = null;
        node2.prev = node1;
        node3.prev = node2;

        // head points to the first node
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.head = node1;
    }
}
```

### <span style="color:#FFE66D">Dry Run — Step by Step</span>

```
Step 1: Create nodes
  node1 → [null | 10 | null]
  node2 → [null | 20 | null]
  node3 → [null | 30 | null]

Step 2: Link next pointers
  node1.next = node2  →  [null | 10 | •]──→[null | 20 | null]
  node2.next = node3  →  [null | 10 | •]──→[null | 20 | •]──→[null | 30 | null]

Step 3: Link prev pointers
  node2.prev = node1  →  [null | 10 | •]⟷[• | 20 | •]──→[null | 30 | null]
  node3.prev = node2  →  [null | 10 | •]⟷[• | 20 | •]⟷[• | 30 | null]

Final State:
  NULL ← [10] ⟷ [20] ⟷ [30] → NULL
          HEAD                   TAIL
```

### <span style="color:#FFE66D">Print DLL (Forward & Backward)</span>

```java
// Print forward
void printForward(Node head) {
    Node temp = head;
    while (temp != null) {
        System.out.print(temp.data + " ↔ ");
        temp = temp.next;
    }
    System.out.println("NULL");
}

// Print backward (requires tail node)
void printBackward(Node tail) {
    Node temp = tail;
    while (temp != null) {
        System.out.print(temp.data + " ↔ ");
        temp = temp.prev;
    }
    System.out.println("NULL");
}
```

**Output:**
```
Forward  : 10 ↔ 20 ↔ 30 ↔ NULL
Backward : 30 ↔ 20 ↔ 10 ↔ NULL
```

---

## <span style="color:#FF6B6B">3. Array to DLL</span>

### <span style="color:#FFE66D">Concept</span>

Convert a given integer array into a Doubly Linked List where each element of the array becomes a node.

```
arr = [1, 2, 3, 4, 5]

Result DLL:
NULL ← [1] ⟷ [2] ⟷ [3] ⟷ [4] ⟷ [5] → NULL
        HEAD                               TAIL
```

### <span style="color:#FFE66D">Java Code</span>

```java
class Node {
    int data;
    Node prev, next;

    Node(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

class Solution {

    // Convert array to DLL
    static Node arrayToDLL(int[] arr) {
        if (arr == null || arr.length == 0) return null;

        // Create the head node with first element
        Node head = new Node(arr[0]);
        Node prev = head;

        // Iterate through remaining elements
        for (int i = 1; i < arr.length; i++) {
            Node curr = new Node(arr[i]);   // create new node
            curr.prev = prev;               // set prev pointer
            prev.next = curr;               // set next pointer
            prev = curr;                    // move prev forward
        }

        return head;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        Node head = arrayToDLL(arr);

        // Print the DLL
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.next != null) System.out.print(" ⟷ ");
            temp = temp.next;
        }
        // Output: 1 ⟷ 2 ⟷ 3 ⟷ 4 ⟷ 5
    }
}
```

### <span style="color:#FFE66D">Dry Run</span>

```
arr = [1, 2, 3, 4, 5]

i=0 (head creation):
  head → [null | 1 | null],  prev = head

i=1 (arr[1]=2):
  curr = [null | 2 | null]
  curr.prev = prev (node 1)   →  [•←2→null]
  prev.next = curr             →  [null←1→•]
  State: NULL←[1]⟷[2]→NULL,  prev = node 2

i=2 (arr[2]=3):
  curr = [null | 3 | null]
  curr.prev = prev (node 2)   →  [•←3→null]
  prev.next = curr             →  [null←1]⟷[2→•]
  State: NULL←[1]⟷[2]⟷[3]→NULL,  prev = node 3

i=3 (arr[3]=4):
  State: NULL←[1]⟷[2]⟷[3]⟷[4]→NULL,  prev = node 4

i=4 (arr[4]=5):
  State: NULL←[1]⟷[2]⟷[3]⟷[4]⟷[5]→NULL  ✅

Return head → node(1)
```

> <span style="color:#FFE66D">**Time Complexity:** O(n)</span>  
> <span style="color:#FFE66D">**Space Complexity:** O(n)</span> (for n nodes)

---

## <span style="color:#FF6B6B">4. Deletion of a Node</span>

There are **3 cases** for deletion in DLL:

| Case | Description |
|------|-------------|
| Case 1 | Delete the **Head** node |
| Case 2 | Delete the **Tail** node |
| Case 3 | Delete a node at a specific **position / value** |

---

### <span style="color:#FFE66D">Case 1 — Delete Head Node</span>

```
Before: NULL ← [10] ⟷ [20] ⟷ [30] → NULL
                HEAD

After:  NULL ← [20] ⟷ [30] → NULL
                HEAD
```

```java
Node deleteHead(Node head) {
    if (head == null) return null;           // empty list
    if (head.next == null) return null;      // only one node

    Node newHead = head.next;
    newHead.prev = null;    // disconnect prev pointer
    head.next = null;       // disconnect old head's next
    return newHead;
}
```

---

### <span style="color:#FFE66D">Case 2 — Delete Tail Node</span>

```
Before: NULL ← [10] ⟷ [20] ⟷ [30] → NULL
                                TAIL

After:  NULL ← [10] ⟷ [20] → NULL
                        TAIL
```

```java
Node deleteTail(Node head) {
    if (head == null) return null;
    if (head.next == null) return null;  // only one node

    Node temp = head;
    while (temp.next != null) {
        temp = temp.next;         // traverse to tail
    }

    // temp is now the tail
    Node newTail = temp.prev;
    newTail.next = null;   // disconnect next pointer
    temp.prev = null;      // disconnect tail's prev
    return head;
}
```

---

### <span style="color:#FFE66D">Case 3 — Delete Node at Position K</span>

```
DLL: NULL ← [10] ⟷ [20] ⟷ [30] ⟷ [40] → NULL
             pos1   pos2   pos3   pos4

Delete at K=3 (value=30):
  prevNode = node(20),  currNode = node(30),  nextNode = node(40)
  prevNode.next = nextNode
  nextNode.prev = prevNode

After: NULL ← [10] ⟷ [20] ⟷ [40] → NULL
```

```java
Node deleteAtPosition(Node head, int k) {
    if (head == null) return null;

    // Delete head
    if (k == 1) return deleteHead(head);

    Node temp = head;
    int count = 1;

    // Traverse to kth node
    while (temp != null && count < k) {
        temp = temp.next;
        count++;
    }

    // If k is out of range
    if (temp == null) return head;

    // If it's the tail
    if (temp.next == null) return deleteTail(head);

    // General case: node in the middle
    Node prevNode = temp.prev;
    Node nextNode = temp.next;

    prevNode.next = nextNode;   // bypass current node (forward)
    nextNode.prev = prevNode;   // bypass current node (backward)

    temp.next = null;           // isolate deleted node
    temp.prev = null;

    return head;
}
```

### <span style="color:#FFE66D">Dry Run — Delete at K=3</span>

```
DLL: [10] ⟷ [20] ⟷ [30] ⟷ [40]

  k=3, traverse: count=1→node10, count=2→node20, count=3→node30
  temp = node30

  prevNode = node20,  nextNode = node40

  node20.next = node40   →  [10] ⟷ [20] → [40]
  node40.prev = node20   →  [10] ⟷ [20] ⟷ [40]

  node30 isolated: [null | 30 | null]  (will be garbage collected)

Result: NULL ← [10] ⟷ [20] ⟷ [40] → NULL  ✅
```

> <span style="color:#FFE66D">**Time Complexity:** O(n)</span>  
> <span style="color:#FFE66D">**Space Complexity:** O(1)</span>

---

## <span style="color:#FF6B6B">5. Insertion of a Node</span>

There are **3 cases** for insertion in DLL:

| Case | Description |
|------|-------------|
| Case 1 | Insert at the **Beginning (Head)** |
| Case 2 | Insert at the **End (Tail)** |
| Case 3 | Insert at a specific **Position** |

---

### <span style="color:#FFE66D">Case 1 — Insert at Beginning</span>

```
Before: NULL ← [10] ⟷ [20] ⟷ [30] → NULL
                HEAD

Insert 5 at beginning:

After:  NULL ← [5] ⟷ [10] ⟷ [20] ⟷ [30] → NULL
               HEAD
```

```java
Node insertAtHead(Node head, int val) {
    Node newNode = new Node(val);

    if (head == null) return newNode;  // empty list

    newNode.next = head;    // new node's next → old head
    head.prev = newNode;    // old head's prev → new node
    return newNode;         // new node becomes head
}
```

### <span style="color:#FFE66D">Dry Run</span>

```
DLL: [10] ⟷ [20] ⟷ [30]
Insert 5

  newNode = [null | 5 | null]
  newNode.next = head (node10)  → [null | 5 | •──→10]
  node10.prev = newNode         → [5←•  | 10 | •──→20]

Result: NULL ← [5] ⟷ [10] ⟷ [20] ⟷ [30] → NULL  ✅
```

---

### <span style="color:#FFE66D">Case 2 — Insert at End (Tail)</span>

```
Before: NULL ← [10] ⟷ [20] ⟷ [30] → NULL
                                TAIL

Insert 40 at end:

After: NULL ← [10] ⟷ [20] ⟷ [30] ⟷ [40] → NULL
                                       TAIL
```

```java
Node insertAtTail(Node head, int val) {
    Node newNode = new Node(val);

    if (head == null) return newNode;  // empty list

    Node temp = head;
    while (temp.next != null) {
        temp = temp.next;             // traverse to last node
    }

    temp.next = newNode;    // last node's next → new node
    newNode.prev = temp;    // new node's prev → last node
    return head;
}
```

### <span style="color:#FFE66D">Dry Run</span>

```
DLL: [10] ⟷ [20] ⟷ [30]
Insert 40 at tail

  Traverse: temp = node30 (last node, temp.next == null)

  newNode = [null | 40 | null]
  node30.next = newNode  → [...| 30 | •──→40]
  newNode.prev = node30  → [30←• | 40 | null]

Result: NULL ← [10] ⟷ [20] ⟷ [30] ⟷ [40] → NULL  ✅
```

---

### <span style="color:#FFE66D">Case 3 — Insert at Position K</span>

```
DLL: [10] ⟷ [20] ⟷ [30] ⟷ [40]
      p1     p2     p3     p4

Insert 25 at position K=3 (between 20 and 30):

After: [10] ⟷ [20] ⟷ [25] ⟷ [30] ⟷ [40]
```

```java
Node insertAtPosition(Node head, int val, int k) {

    // Case: insert at head
    if (k == 1) return insertAtHead(head, val);

    Node newNode = new Node(val);
    Node temp = head;
    int count = 1;

    // Traverse to (k-1)th node
    while (temp != null && count < k - 1) {
        temp = temp.next;
        count++;
    }

    // If k is beyond list length, insert at tail
    if (temp == null || temp.next == null) {
        return insertAtTail(head, val);
    }

    // temp is now at position (k-1)
    Node nextNode = temp.next;   // node at position k

    // Connect newNode with neighbours
    newNode.prev = temp;         // newNode ← temp
    newNode.next = nextNode;     // newNode → nextNode

    temp.next = newNode;         // temp → newNode
    nextNode.prev = newNode;     // nextNode ← newNode

    return head;
}
```

### <span style="color:#FFE66D">Dry Run — Insert 25 at K=3</span>

```
DLL: [10] ⟷ [20] ⟷ [30] ⟷ [40]
      p1     p2     p3     p4

k=3, traverse to (k-1)=2nd node:
  count=1 → node10,  count=2 → node20
  temp = node20

nextNode = temp.next = node30

newNode = [null | 25 | null]

  newNode.prev = temp (node20)    →  [20←• | 25 | null]
  newNode.next = nextNode (node30)→  [20←• | 25 | •→30]
  temp.next = newNode             →  [10]⟷[20→•25]
  nextNode.prev = newNode         →  [25←•30]

Result: NULL ← [10] ⟷ [20] ⟷ [25] ⟷ [30] ⟷ [40] → NULL  ✅
```

> <span style="color:#FFE66D">**Time Complexity:** O(n)</span>  
> <span style="color:#FFE66D">**Space Complexity:** O(1)</span>

---

## <span style="color:#FF6B6B">Complete DLL Implementation in Java</span>

```java
class Node {
    int data;
    Node prev, next;

    Node(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

public class DoublyLinkedList {
    Node head;

    // ─── Array to DLL ───────────────────────────────────────────
    Node arrayToDLL(int[] arr) {
        Node head = new Node(arr[0]);
        Node prev = head;
        for (int i = 1; i < arr.length; i++) {
            Node curr = new Node(arr[i]);
            curr.prev = prev;
            prev.next = curr;
            prev = curr;
        }
        return head;
    }

    // ─── Insert at Head ─────────────────────────────────────────
    Node insertAtHead(Node head, int val) {
        Node newNode = new Node(val);
        if (head == null) return newNode;
        newNode.next = head;
        head.prev = newNode;
        return newNode;
    }

    // ─── Insert at Tail ─────────────────────────────────────────
    Node insertAtTail(Node head, int val) {
        Node newNode = new Node(val);
        if (head == null) return newNode;
        Node temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = newNode;
        newNode.prev = temp;
        return head;
    }

    // ─── Insert at Position ─────────────────────────────────────
    Node insertAtPosition(Node head, int val, int k) {
        if (k == 1) return insertAtHead(head, val);
        Node newNode = new Node(val);
        Node temp = head;
        int count = 1;
        while (temp != null && count < k - 1) {
            temp = temp.next;
            count++;
        }
        if (temp == null || temp.next == null) return insertAtTail(head, val);
        Node nextNode = temp.next;
        newNode.prev = temp;
        newNode.next = nextNode;
        temp.next = newNode;
        nextNode.prev = newNode;
        return head;
    }

    // ─── Delete Head ────────────────────────────────────────────
    Node deleteHead(Node head) {
        if (head == null || head.next == null) return null;
        Node newHead = head.next;
        newHead.prev = null;
        head.next = null;
        return newHead;
    }

    // ─── Delete Tail ────────────────────────────────────────────
    Node deleteTail(Node head) {
        if (head == null || head.next == null) return null;
        Node temp = head;
        while (temp.next != null) temp = temp.next;
        Node newTail = temp.prev;
        newTail.next = null;
        temp.prev = null;
        return head;
    }

    // ─── Delete at Position ─────────────────────────────────────
    Node deleteAtPosition(Node head, int k) {
        if (head == null) return null;
        if (k == 1) return deleteHead(head);
        Node temp = head;
        int count = 1;
        while (temp != null && count < k) {
            temp = temp.next;
            count++;
        }
        if (temp == null) return head;
        if (temp.next == null) return deleteTail(head);
        Node prevNode = temp.prev;
        Node nextNode = temp.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        temp.next = null;
        temp.prev = null;
        return head;
    }

    // ─── Print DLL ──────────────────────────────────────────────
    void print(Node head) {
        Node temp = head;
        System.out.print("NULL ← ");
        while (temp != null) {
            System.out.print("[" + temp.data + "]");
            if (temp.next != null) System.out.print(" ⟷ ");
            temp = temp.next;
        }
        System.out.println(" → NULL");
    }

    // ─── Main ───────────────────────────────────────────────────
    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();

        int[] arr = {10, 20, 30, 40};
        Node head = dll.arrayToDLL(arr);

        System.out.println("Original DLL:");
        dll.print(head);                              // [10]⟷[20]⟷[30]⟷[40]

        head = dll.insertAtHead(head, 5);
        System.out.println("After insertAtHead(5):");
        dll.print(head);                              // [5]⟷[10]⟷[20]⟷[30]⟷[40]

        head = dll.insertAtTail(head, 50);
        System.out.println("After insertAtTail(50):");
        dll.print(head);                              // [5]⟷[10]⟷[20]⟷[30]⟷[40]⟷[50]

        head = dll.insertAtPosition(head, 25, 4);
        System.out.println("After insertAtPosition(25, 4):");
        dll.print(head);                              // [5]⟷[10]⟷[20]⟷[25]⟷[30]⟷[40]⟷[50]

        head = dll.deleteHead(head);
        System.out.println("After deleteHead:");
        dll.print(head);                              // [10]⟷[20]⟷[25]⟷[30]⟷[40]⟷[50]

        head = dll.deleteTail(head);
        System.out.println("After deleteTail:");
        dll.print(head);                              // [10]⟷[20]⟷[25]⟷[30]⟷[40]

        head = dll.deleteAtPosition(head, 3);
        System.out.println("After deleteAtPosition(3):");
        dll.print(head);                              // [10]⟷[20]⟷[30]⟷[40]
    }
}
```

---

## <span style="color:#FF6B6B">Quick Summary — Time & Space Complexities</span>

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|-----------------|
| Array to DLL | O(n) | O(n) |
| Insert at Head | O(1) | O(1) |
| Insert at Tail | O(n) | O(1) |
| Insert at Position | O(n) | O(1) |
| Delete at Head | O(1) | O(1) |
| Delete at Tail | O(n) | O(1) |
| Delete at Position | O(n) | O(1) |
| Traverse / Print | O(n) | O(1) |

---

> <span style="color:#4ECDC4">**Tip:** Always handle edge cases — empty list (`head == null`) and single element list (`head.next == null`) before performing any insertion or deletion!</span>

---

*Notes by — 90 Days Challenge | Day 45 — Doubly Linked List*
