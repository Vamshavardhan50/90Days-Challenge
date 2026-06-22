# Binary Trees - Complete DSA Notes (Detailed)

> **Binary Tree** - A hierarchical tree data structure where each node has **at most two children**, called `left` and `right`.  
> Fundamental for interviews, used in searching, sorting, expression parsing, and many advanced algorithms.

---

## Table of Contents

1. Basic Terminology & Structure
2. Types of Binary Trees
3. Tree Traversals (DFS + BFS)
4. Binary Search Tree (BST)
5. Balanced Trees (AVL / Red-Black)
6. Important Properties & Formulas
7. Tree Construction / Serialization
8. Top Interview Problems with Dry Runs
9. Tips, Tricks & Patterns
10. Complexity Cheat Sheet

---

## 1. Basic Terminology & Structure

### 1.1 Java Node Definition

```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
```

### 1.2 Terminology

| Term | Definition |
|------|-----------|
| **Root** | Topmost node (no parent) |
| **Parent** | Node that has children |
| **Child** | Node connected downward from a parent |
| **Leaf** | Node with **no children** (both left & right are null) |
| **Internal Node** | Any node that has at least one child |
| **Depth of a Node** | Number of edges from **root** to that node |
| **Height of a Node** | Number of edges on the **longest path** from that node to a leaf |
| **Height of Tree** | Height of the root = max depth of any node |
| **Degree of a Node** | Number of children (0, 1, or 2 in binary tree) |
| **Subtree** | A tree rooted at any node of the original tree |
| **Sibling** | Nodes sharing the same parent |
| **Ancestor** | Any node on the path from root to a given node |
| **Descendant** | Any node in the subtree of a given node |

### 1.3 Visual Example

```
            1          <-- Root (depth=0, height=3)
           / \
          2   3        <-- depth=1
         / \   \
        4   5   6      <-- depth=2
           / \
          7   8        <-- depth=3 (Leaves: 4, 7, 8, 6)
```

**Key observations:**
- Nodes 4, 7, 8, 6 are **leaves** (no children)
- Height of tree = **3**
- Degree of node 2 = 2, degree of node 3 = 1
- Siblings: (2,3), (4,5), (7,8)

---

## 2. Types of Binary Trees

### 2.1 Full Binary Tree (Strict Binary Tree)
> Every node has **0 or 2 children** (no node has exactly 1 child).

```
        1
       / \
      2   3
     / \
    4   5
```
âœ… Nodes 4, 5, 3 are leaves (0 children)
âœ… Nodes 1, 2 have exactly 2 children
âœ… This IS a full binary tree

```
        1
       /
      2
```
âŒ Node 1 has only 1 child â†’ NOT a full binary tree

### 2.2 Complete Binary Tree
> Every level is **completely filled** except possibly the last, which is filled **left to right**.

```
        1           Level 0: âœ… Full
       / \
      2   3         Level 1: âœ… Full
     / \  /
    4  5 6          Level 2: Filled left to right âœ…
```
âœ… This IS a complete binary tree

```
        1
       / \
      2   3
       \   \
        5   6       âŒ NOT complete (gap at left side of level 2)
```
ðŸ’¡ Used in **Heap** implementation. Can be stored efficiently in an **array** without gaps.

### 2.3 Perfect Binary Tree
> All internal nodes have 2 children **AND** all leaves are at the **same level**.

```
        1           Level 0
       / \
      2   3         Level 1
     / \ / \
    4  5 6  7       Level 2
```
âœ… Every level is completely filled

**Formulas for Perfect Binary Tree of height `h`:**

| Property | Formula |
|----------|---------|
| Total nodes | `n = 2^(h+1) âˆ’ 1` |
| Leaf nodes | `2^h` |
| Internal nodes | `2^h âˆ’ 1` |
| Height from nodes | `h = logâ‚‚(n+1) âˆ’ 1` |

| Height (h) | Total Nodes | Leaf Nodes | Internal Nodes |
|------------|-------------|------------|----------------|
| 0          | 1           | 1          | 0              |
| 1          | 3           | 2          | 1              |
| 2          | 7           | 4          | 3              |
| 3          | 15          | 8          | 7              |

### 2.4 Balanced Binary Tree (Height-Balanced)
> For **every** node, the height difference between left and right subtrees is **at most 1**.
> Formally: `|height(left) âˆ’ height(right)| â‰¤ 1` for all nodes.

```
        1              âœ… Balanced
       / \
      2   3            |hL - hR| for node 1 = |1 - 0| = 1 âœ…
     / \
    4   5              |hL - hR| for node 2 = |0 - 0| = 0 âœ…
```

```
        1              âŒ NOT balanced
       /
      2                |hL - hR| for node 1 = |2 - 0| = 2 âŒ
     /
    3
```
ðŸ’¡ **AVL Tree** is a self-balancing BST that maintains this property automatically after every insertion/deletion.

### 2.5 Degenerate (Skewed) Binary Tree
> Every parent has **only one child** â€” degenerates into a **linked list**.

```
Right-skewed:        Left-skewed:
    1                     1
     \                   /
      2                 2
       \               /
        3             3
         \           /
          4         4
```
âš ï¸ Height = nâˆ’1 (worst case). All operations degrade to **O(n)**.
This happens when you insert **sorted data** into a BST without balancing.

### 2.6 Summary Table

| Type | Children Rule | Fill Rule | Height | Array Representation |
|------|--------------|-----------|--------|---------------------|
| **Full** | 0 or 2 only | Any | O(log n) to O(n) | No |
| **Complete** | Any | Leftâ†’right, last level | O(log n) | Yes (no gaps) |
| **Perfect** | All have 2 | All levels full | logâ‚‚(n+1)âˆ’1 | Yes |
| **Balanced** | Any | \|hLâˆ’hR\| â‰¤ 1 | O(log n) | No |
| **Degenerate** | 1 only | One chain | nâˆ’1 | N/A |

---

## 3. Tree Traversals (DFS + BFS)

### 3.1 Sample Tree (Used for All Examples Below)

```
            1
           / \
          2    3
         / \    \
        4   5    6
       /   / \
      7   8   9
```

### 3.2 Pre-Order Traversal (Root â†’ Left â†’ Right)

> **Visit** the current node first, then recursively traverse **left** subtree, then **right** subtree.

```java
void preOrder(TreeNode root) {
    if (root == null) return;
    System.out.print(root.val + " ");  // 1ï¸âƒ£ Visit
    preOrder(root.left);               // 2ï¸âƒ£ Left
    preOrder(root.right);              // 3ï¸âƒ£ Right
}
```

**Dry Run (recursive call trace):**

| Step | Call | Action | Output |
|------|------|--------|--------|
| 1 | preOrder(1) | Print 1, go left | 1 |
| 2 | preOrder(2) | Print 2, go left | 1, 2 |
| 3 | preOrder(4) | Print 4, go left â†’ null, go right â†’ null | 1, 2, 4 |
| 4 | Back to 2 | Go right â†’ preOrder(5) | 1, 2, 4 |
| 5 | preOrder(5) | Print 5, go left â†’ preOrder(8) | 1, 2, 4, 5 |
| 6 | preOrder(8) | Print 8, both null, return | 1, 2, 4, 5, 8 |
| 7 | Back to 5 | Go right â†’ preOrder(9) | 1, 2, 4, 5, 8 |
| 8 | preOrder(9) | Print 9, both null, return | 1, 2, 4, 5, 8, 9 |
| 9 | Back to 1 | Go right â†’ preOrder(3) | 1, 2, 4, 5, 8, 9 |
| 10 | preOrder(3) | Print 3, go left â†’ null, go right â†’ preOrder(6) | 1, 2, 4, 5, 8, 9, 3 |
| 11 | preOrder(6) | Print 6, both null, return | 1, 2, 4, 5, 8, 9, 3, 6 |

**Output:** `1 2 4 5 8 9 3 6`

### 3.3 In-Order Traversal (Left â†’ Root â†’ Right)

> Recursively traverse **left** subtree, **visit** current node, then traverse **right** subtree.
> â­ For BST, this gives nodes in **sorted ascending order**.

```java
void inOrder(TreeNode root) {
    if (root == null) return;
    inOrder(root.left);                // 1ï¸âƒ£ Left
    System.out.print(root.val + " ");  // 2ï¸âƒ£ Visit
    inOrder(root.right);               // 3ï¸âƒ£ Right
}
```

**Dry Run:**

| Step | Call | Action | Output |
|------|------|--------|--------|
| 1 | inOrder(1) | Go left â†’ inOrder(2) | |
| 2 | inOrder(2) | Go left â†’ inOrder(4) | |
| 3 | inOrder(4) | Go left â†’ inOrder(7) | |
| 4 | inOrder(7) | Left=null, **print 7**, right=null | 7 |
| 5 | Back to 4 | **print 4**, right=null | 7, 4 |
| 6 | Back to 2 | **print 2**, go right â†’ inOrder(5) | 7, 4, 2 |
| 7 | inOrder(5) | Go left â†’ inOrder(8) | |
| 8 | inOrder(8) | Left=null, **print 8**, right=null | 7, 4, 2, 8 |
| 9 | Back to 5 | **print 5**, go right â†’ inOrder(9) | 7, 4, 2, 8, 5 |
| 10 | inOrder(9) | Left=null, **print 9**, right=null | 7, 4, 2, 8, 5, 9 |
| 11 | Back to 1 | Left done, **print 1**, go right â†’ inOrder(3) | 7, 4, 2, 8, 5, 9, 1 |
| 12 | inOrder(3) | Left=null, **print 3**, go right â†’ inOrder(6) | 7, 4, 2, 8, 5, 9, 1, 3 |
| 13 | inOrder(6) | Left=null, **print 6**, right=null | 7, 4, 2, 8, 5, 9, 1, 3, 6 |

**Output:** `7 4 2 8 5 9 1 3 6`

### 3.4 Post-Order Traversal (Left â†’ Right â†’ Root)

> Recursively traverse **left** subtree, then **right** subtree, and **visit** current node last.
> ðŸ’¡ Used for **deleting** trees, evaluating **postfix expressions**, and computing directory sizes.

```java
void postOrder(TreeNode root) {
    if (root == null) return;
    postOrder(root.left);              // 1ï¸âƒ£ Left
    postOrder(root.right);             // 2ï¸âƒ£ Right
    System.out.print(root.val + " ");  // 3ï¸âƒ£ Visit
}
```

**Dry Run:**

| Step | Call | Action | Output |
|------|------|--------|--------|
| 1 | postOrder(1) | Go left â†’ postOrder(2) â†’ postOrder(4) â†’ postOrder(7) | |
| 2 | postOrder(7) | Both null, **print 7** | 7 |
| 3 | Back to 4 | Left done, right=null, **print 4** | 7, 4 |
| 4 | Back to 2 | Left done, go right â†’ postOrder(5) | |
| 5 | postOrder(5) | Go left â†’ postOrder(8) | |
| 6 | postOrder(8) | Both null, **print 8** | 7, 4, 8 |
| 7 | Back to 5 | Go right â†’ postOrder(9) | |
| 8 | postOrder(9) | Both null, **print 9** | 7, 4, 8, 9 |
| 9 | Back to 5 | Left done, right done, **print 5** | 7, 4, 8, 9, 5 |
| 10 | Back to 2 | Left done, right done, **print 2** | 7, 4, 8, 9, 5, 2 |
| 11 | Back to 1 | Left done, go right â†’ postOrder(3) â†’ postOrder(6) | |
| 12 | postOrder(6) | Both null, **print 6** | 7, 4, 8, 9, 5, 2, 6 |
| 13 | Back to 3 | Left=null, right done, **print 3** | 7, 4, 8, 9, 5, 2, 6, 3 |
| 14 | Back to 1 | Left done, right done, **print 1** | 7, 4, 8, 9, 5, 2, 6, 3, 1 |

**Output:** `7 4 8 9 5 2 6 3 1`

### 3.5 Level-Order Traversal (BFS)

> Visit nodes **level by level**, left to right, using a **Queue**.

```java
List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
        int size = queue.size();  // # nodes at current level
        List<Integer> level = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            level.add(node.val);
            if (node.left != null)  queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        result.add(level);
    }
    return result;
}
```

**Dry Run:**

| Step | Queue (before) | Dequeue | Enqueue Children | Level Output |
|------|---------------|---------|-----------------|-------------|
| 1 | [1] | 1 | 2, 3 | Level 0: [1] |
| 2 | [2, 3] | 2 | 4, 5 | Level 1: [2] |
| 3 | [3, 4, 5] | 3 | 6 | Level 1: [2, 3] |
| 4 | [4, 5, 6] | 4 | 7 | Level 2: [4] |
| 5 | [5, 6, 7] | 5 | 8, 9 | Level 2: [4, 5] |
| 6 | [6, 7, 8, 9] | 6 | â€” | Level 2: [4, 5, 6] |
| 7 | [7, 8, 9] | 7 | â€” | Level 3: [7] |
| 8 | [8, 9] | 8 | â€” | Level 3: [7, 8] |
| 9 | [9] | 9 | â€” | Level 3: [7, 8, 9] |

**Output:** `[[1], [2, 3], [4, 5, 6], [7, 8, 9]]`

**Time:** O(n) | **Space:** O(w) where w = max width of tree

### 3.6 Iterative Pre-Order (Using Stack)

> Simulate recursion using an explicit **Stack**. Push right first, then left (so left is processed first).

```java
List<Integer> preOrderIterative(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    while (!stack.isEmpty()) {
        TreeNode node = stack.pop();
        result.add(node.val);            // Visit
        if (node.right != null) stack.push(node.right);  // Push right first
        if (node.left != null)  stack.push(node.left);   // Then left (popped first)
    }
    return result;
}
```

**Dry Run:**

| Step | Stack (before) | Pop | Output | Push (right, then left) |
|------|---------------|-----|--------|------------------------|
| 1 | [1] | 1 | 1 | push 3, push 2 â†’ [3, 2] |
| 2 | [3, 2] | 2 | 1, 2 | push 5, push 4 â†’ [3, 5, 4] |
| 3 | [3, 5, 4] | 4 | 1, 2, 4 | push 7 â†’ [3, 5, 7] |
| 4 | [3, 5, 7] | 7 | 1, 2, 4, 7 | â€” â†’ [3, 5] |
| 5 | [3, 5] | 5 | 1, 2, 4, 7, 5 | push 9, push 8 â†’ [3, 9, 8] |
| 6 | [3, 9, 8] | 8 | 1, 2, 4, 7, 5, 8 | â€” â†’ [3, 9] |
| 7 | [3, 9] | 9 | 1, 2, 4, 7, 5, 8, 9 | â€” â†’ [3] |
| 8 | [3] | 3 | 1, 2, 4, 7, 5, 8, 9, 3 | push 6 â†’ [6] |
| 9 | [6] | 6 | 1, 2, 4, 7, 5, 8, 9, 3, 6 | â€” â†’ [] |

**Output:** `1 2 4 7 5 8 9 3 6` âœ… Matches recursive Pre-order

### 3.7 Iterative In-Order (Using Stack)

> Go as far left as possible. When you can't go left, pop (visit), then go right.

```java
List<Integer> inOrderIterative(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    TreeNode curr = root;
    while (curr != null || !stack.isEmpty()) {
        // Go as far left as possible
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
        curr = stack.pop();
        result.add(curr.val);    // Visit
        curr = curr.right;       // Go right
    }
    return result;
}
```

**Dry Run:**

| Step | curr | Stack Action | Stack | Pop/Visit | curr becomes |
|------|------|-------------|-------|-----------|-------------|
| 1 | 1 | push 1,2,4,7 | [1,2,4,7] | pop 7 â†’ visit 7 | null (7.left) â†’ null |
| 2 | null | pop | [1,2,4] | pop 4 â†’ visit 4 | 4.right = null |
| 3 | null | pop | [1,2] | pop 2 â†’ visit 2 | 2.right = 5 |
| 4 | 5 | push 5,8 | [1,5,8] | pop 8 â†’ visit 8 | null |
| 5 | null | pop | [1,5] | pop 5 â†’ visit 5 | 5.right = 9 |
| 6 | 9 | push 9 | [1,9] | pop 9 â†’ visit 9 | null |
| 7 | null | pop | [1] | pop 1 â†’ visit 1 | 1.right = 3 |
| 8 | 3 | push 3 | [3] | pop 3 â†’ visit 3 | 3.right = 6 |
| 9 | 6 | push 6 | [6] | pop 6 â†’ visit 6 | null |

**Output:** `7 4 2 8 5 9 1 3 6` âœ… Matches recursive In-order

### 3.8 Iterative Post-Order (Two Stacks)

> Use stack1 for traversal, stack2 to reverse the order. Push to stack2 when popped from stack1.

```java
List<Integer> postOrderTwoStacks(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;
    Stack<TreeNode> s1 = new Stack<>();
    Stack<TreeNode> s2 = new Stack<>();
    s1.push(root);
    while (!s1.isEmpty()) {
        TreeNode node = s1.pop();
        s2.push(node);
        if (node.left != null)  s1.push(node.left);
        if (node.right != null) s1.push(node.right);
    }
    while (!s2.isEmpty()) {
        result.add(s2.pop().val);
    }
    return result;
}
```

**Key Idea:** s1 processes Rootâ†’Leftâ†’Right, s2 reverses it to Leftâ†’Rightâ†’Root.

**Dry Run:**

| Step | s1 (pop) | Push to s2 | Push to s1 | s2 contents |
|------|----------|-----------|-----------|-------------|
| 1 | 1 | push 1 | push 2, push 3 | [1] |
| 2 | 3 | push 3 | push 6 | [1, 3] |
| 3 | 6 | push 6 | â€” | [1, 3, 6] |
| 4 | 2 | push 2 | push 4, push 5 | [1, 3, 6, 2] |
| 5 | 5 | push 5 | push 8, push 9 | [1, 3, 6, 2, 5] |
| 6 | 9 | push 9 | â€” | [1, 3, 6, 2, 5, 9] |
| 7 | 8 | push 8 | â€” | [1, 3, 6, 2, 5, 9, 8] |
| 8 | 4 | push 4 | push 7 | [1, 3, 6, 2, 5, 9, 8, 4] |
| 9 | 7 | push 7 | â€” | [1, 3, 6, 2, 5, 9, 8, 4, 7] |

Now pop all from s2: `7 4 8 9 5 2 6 3 1` âœ…

### 3.9 Iterative Post-Order (One Stack)

> Trickier: push node, go left. When left is null, check right. Only visit when at top and no right child pending.

```java
List<Integer> postOrderOneStack(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;
    Stack<TreeNode> stack = new Stack<>();
    TreeNode curr = root;
    TreeNode lastVisited = null;
    while (curr != null || !stack.isEmpty()) {
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
        TreeNode peek = stack.peek();
        if (peek.right != null && peek.right != lastVisited) {
            curr = peek.right;
        } else {
            result.add(peek.val);
            lastVisited = stack.pop();
        }
    }
    return result;
}
```

**Key Idea:** Visit a node only after its right subtree has been fully processed (tracked by `lastVisited`).

### 3.10 Morris Traversal (In-Order, O(1) Space)

> Uses **threaded binary tree** concept: temporarily modify null pointers to create "threads" back to the in-order successor. No stack, no recursion.

```java
List<Integer> morrisInOrder(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    TreeNode curr = root;
    while (curr != null) {
        if (curr.left == null) {
            result.add(curr.val);    // Visit
            curr = curr.right;
        } else {
            // Find inorder predecessor (rightmost node in left subtree)
            TreeNode pred = curr.left;
            while (pred.right != null && pred.right != curr) {
                pred = pred.right;
            }
            if (pred.right == null) {
                // Create thread: pred.right = curr
                pred.right = curr;
                curr = curr.left;
            } else {
                // Thread exists â†’ we've returned, remove it
                pred.right = null;
                result.add(curr.val);  // Visit
                curr = curr.right;
            }
        }
    }
    return result;
}
```

**How it works (visual for node 2's left subtree):**
```
Step 1: curr=4, pred of 4 in its left subtree = 7
        Set 7.right = 4 (create thread), go left to 7
Step 2: curr=7, left=null â†’ visit 7, go right (thread) â†’ back to 4
Step 3: curr=4, pred.right == curr (thread exists) â†’ remove thread, visit 4, go right
```

**Time:** O(n) | **Space:** O(1) â­ (only uses Morris threading, no stack/recursion)

### 3.11 Traversal Summary Table

| Traversal | Order | Recursive | Iterative | Morris | Time | Space |
|-----------|-------|-----------|-----------|--------|------|-------|
| **Pre-Order** | Rootâ†’Leftâ†’Right | âœ… Simple | Stack O(n) | Possible | O(n) | O(h) / O(n) / O(1) |
| **In-Order** | Leftâ†’Rootâ†’Right | âœ… Simple | Stack O(n) | âœ… O(1) | O(n) | O(h) / O(n) / O(1) |
| **Post-Order** | Leftâ†’Rightâ†’Root | âœ… Simple | 1 or 2 Stacks | Complex | O(n) | O(h) / O(n) / O(n) |
| **Level-Order** | BFS level by level | N/A | Queue O(w) | N/A | O(n) | O(w) |

> `h` = height of tree, `w` = max width of tree, `n` = number of nodes

---

## 4. Binary Search Tree (BST) Properties

### 4.1 BST Properties

```
        8
       / \
      3   10
     / \    \
    1   6    14
       / \   /
      4   7 13
```
- **Left subtree** values < node value < **Right subtree** values
- **In-order traversal** of BST gives **sorted ascending** order: `1 3 4 6 7 8 10 13 14`
- No duplicate values (unless explicitly allowed)
- Enables efficient search/insert/delete in **O(h)** time

### 4.2 BST Insert

> Traverse to find the correct leaf position, then insert.

```java
TreeNode insert(TreeNode root, int key) {
    if (root == null) return new TreeNode(key);
    if (key < root.val)
        root.left = insert(root.left, key);
    else if (key > root.val)
        root.right = insert(root.right, key);
    // If key == root.val, do nothing (no duplicates)
    return root;
}
```

**Dry Run â€” Insert 5 into the BST above:**

| Step | Node | Comparison | Action |
|------|------|-----------|--------|
| 1 | 8 | 5 < 8 | Go left |
| 2 | 3 | 5 > 3 | Go right |
| 3 | 6 | 5 < 6 | Go left |
| 4 | null | â€” | Insert 5 as left child of 6 |

**Result:**
```
        8
       / \
      3   10
     / \    \
    1   6    14
       / \   /
      4   7 13
     /
    5        â† newly inserted
```

### 4.3 BST Search

> Compare at each node: go left if smaller, go right if larger.

```java
TreeNode search(TreeNode root, int key) {
    if (root == null || root.val == key) return root;
    if (key < root.val) return search(root.left, key);
    return search(root.right, key);
}
```

**Dry Run â€” Search for 7:**

| Step | Node | Comparison | Result |
|------|------|-----------|--------|
| 1 | 8 | 7 < 8 | Go left |
| 2 | 3 | 7 > 3 | Go right |
| 3 | 6 | 7 > 6 | Go right |
| 4 | 7 | 7 == 7 | âœ… Found! |

**Time:** O(h) â€” O(log n) if balanced, O(n) worst case (skewed)

### 4.4 BST Delete (3 Cases)

```java
TreeNode delete(TreeNode root, int key) {
    if (root == null) return null;
    if (key < root.val) {
        root.left = delete(root.left, key);
    } else if (key > root.val) {
        root.right = delete(root.right, key);
    } else {
        // Case 1: Leaf node (0 children)
        if (root.left == null && root.right == null)
            return null;
        // Case 2: One child
        if (root.left == null) return root.right;
        if (root.right == null) return root.left;
        // Case 3: Two children â€” replace with in-order successor
        TreeNode successor = findMin(root.right);
        root.val = successor.val;
        root.right = delete(root.right, successor.val);
    }
    return root;
}

TreeNode findMin(TreeNode node) {
    while (node.left != null) node = node.left;
    return node;
}
```

**Dry Run â€” Delete 3 from BST (two children case):**

```
Before:          After deleting 3:
    8                8
   / \              / \
  3   10           4   10        â† 3 replaced by successor 4
 / \    \         / \    \
1   6    14      1   6    14
   / \   /          / \   /
  4   7 13         5   7 13      â† 4 moved up, original 4 deleted
```

| Step | Action |
|------|--------|
| 1 | Found node 3 (has 2 children) |
| 2 | Find in-order successor = leftmost in right subtree = node 4 |
| 3 | Copy 4's value into node 3's position |
| 4 | Delete original node 4 from right subtree (it's a leaf â†’ Case 1) |

### 4.5 BST from Sorted Array

> Use **binary search** to pick the middle element as root, then recurse on halves.

```java
TreeNode sortedArrayToBST(int[] nums) {
    return helper(nums, 0, nums.length - 1);
}

TreeNode helper(int[] nums, int left, int right) {
    if (left > right) return null;
    int mid = left + (right - left) / 2;
    TreeNode node = new TreeNode(nums[mid]);
    node.left = helper(nums, left, mid - 1);
    node.right = helper(nums, mid + 1, right);
    return node;
}
```

**Dry Run â€” `sortedArrayToBST([1, 2, 3, 4, 5, 6, 7])`:**

| Call | Range | mid | Root | Left subtree | Right subtree |
|------|-------|-----|------|-------------|--------------|
| 1 | [0,6] | 3 | 4 | [0,2] â†’ 2 | [4,6] â†’ 6 |
| 2 | [0,2] | 1 | 2 | [0,0] â†’ 1 | [2,2] â†’ 3 |
| 3 | [4,6] | 5 | 6 | [4,4] â†’ 5 | [6,6] â†’ 7 |

**Result:**
```
        4
       / \
      2    6
     / \  / \
    1  3 5   7     â† Height-balanced BST!
```

---

## 5. Balanced Trees (AVL / Red-Black)

### 5.1 Why Balance?
A BST with height h supports operations in O(h). If the tree is skewed, h = n â†’ O(n).
**Balanced trees guarantee h = O(log n)** via rotations after each insert/delete.

### 5.2 AVL Tree

> For every node: `|height(left) âˆ’ height(right)| â‰¤ 1`
> Uses **balance factor** = height(left) âˆ’ height(right). Must be âˆ’1, 0, or 1.

**Four Rotation Cases:**

| Case | When | Fix |
|------|------|-----|
| **LL (Left-Left)** | Imbalance at node X, inserted into left subtree of left child | **Right Rotate** X |
| **RR (Right-Right)** | Imbalance at node X, inserted into right subtree of right child | **Left Rotate** X |
| **LR (Left-Right)** | Imbalance at node X, inserted into right subtree of left child | **Left Rotate** left child, then **Right Rotate** X |
| **RL (Right-Left)** | Imbalance at node X, inserted into left subtree of right child | **Right Rotate** right child, then **Left Rotate** X |

#### Right Rotation (LL Case)

```
Before:        After Right Rotate on Z:
    Z                Y
   / \             /   \
  Y   T4    â†’     X     Z
 / \             / \   / \
X   T3          T1 T2 T3 T4
/ \
T1 T2

In-order preserved: T1 X T2 Y T3 Z T4
```

```java
TreeNode rightRotate(TreeNode y) {
    TreeNode x = y.left;
    TreeNode T2 = x.right;
    x.right = y;
    y.left = T2;
    y.height = Math.max(height(y.left), height(y.right)) + 1;
    x.height = Math.max(height(x.left), height(x.right)) + 1;
    return x;  // new root
}
```

#### Left-Right Rotation (LR Case)

```
Before LR:      After Left Rotate Y:    After Right Rotate Z:
    Z                Z                     Y
   / \              / \                   / \
  Y   T4           X   T4                X   Z
 / \              / \                   / \ / \
T1  X            T1  T2               T1 T2 T3 T4
   / \
  T2 T3
```

```java
TreeNode leftRotate(TreeNode x) {
    TreeNode y = x.right;
    TreeNode T2 = y.left;
    y.left = x;
    x.right = T2;
    x.height = Math.max(height(x.left), height(x.right)) + 1;
    y.height = Math.max(height(y.left), height(y.right)) + 1;
    return y;  // new root
}
```

### 5.3 Red-Black Tree Properties

A self-balancing BST where each node has a **color** (Red or Black) satisfying:

| # | Property | Why |
|---|----------|-----|
| 1 | Every node is either **Red** or **Black** | Color constraint |
| 2 | The **root** is always **Black** | Stability |
| 3 | Every **leaf** (null) is **Black** | Sentinel consistency |
| 4 | If a node is **Red**, both its children are **Black** | No two consecutive Red nodes |
| 5 | Every path from a node to its descendant leaves has the **same number of Black nodes** | Balanced height |

ðŸ’¡ **Result:** Height is guaranteed â‰¤ 2Â·logâ‚‚(n+1), so all operations are O(log n).
ðŸ’¡ Used in **Java TreeMap**, **Linux CFS scheduler**, **C++ std::map**.

### 5.4 Comparison

| Feature | AVL Tree | Red-Black Tree |
|---------|----------|---------------|
| Balance | Strict (|hLâˆ’hR| â‰¤ 1) | Relaxed (height â‰¤ 2Â·log n) |
| Lookup | Faster (more balanced) | Slightly slower |
| Insert/Delete | More rotations (slower) | Fewer rotations (faster) |
| Use case | Read-heavy | Write-heavy (e.g., TreeMap) |

---

## 6. Important Properties & Formulas

### 6.1 Height Relations

| Property | Formula |
|----------|---------|
| Height of tree | `h = max(height(left), height(right)) + 1` |
| Min nodes at height h | `N_min(h) = N_min(h-1) + N_min(h-2) + 1` (Fibonacci-like) |
| Max height with n nodes (balanced) | `h â‰¤ âŒŠlogâ‚‚(n)âŒ‹` |
| Max height with n nodes (skewed) | `h = n âˆ’ 1` |
| Min height with n nodes | `h_min = âŒŠlogâ‚‚(n)âŒ‹` |

### 6.2 Array Representation (0-indexed)

> Complete binary trees can be stored in arrays with NO gaps.

For a node at index `i`:

| Relationship | Formula |
|-------------|---------|
| **Left child** | `2*i + 1` |
| **Right child** | `2*i + 2` |
| **Parent** | `âŒŠ(i - 1) / 2âŒ‹` |

**Example:** Tree stored as array `[1, 2, 3, 4, 5, 6, 7]`

```
        1 (i=0)
       / \
      2(1) 3(2)
     / \   / \
    4(3) 5(4) 6(5) 7(6)

Node 5 is at index 4:
  Left child  = 2*4+1 = 9  (out of bounds â†’ no left child)
  Right child = 2*4+2 = 10 (out of bounds â†’ no right child)
  Parent      = (4-1)/2 = 1 â†’ node 2 âœ…
```

### 6.3 Counting Nodes

```java
int countNodes(TreeNode root) {
    if (root == null) return 0;
    return 1 + countNodes(root.left) + countNodes(root.right);
}

// For complete binary tree â€” O(log n) using tree structure:
int countComplete(TreeNode root) {
    if (root == null) return 0;
    int lh = heightLeft(root);
    int rh = heightRight(root);
    if (lh == rh) return (1 << lh) - 1;  // Perfect subtree
    return 1 + countComplete(root.left) + countComplete(root.right);
}

int heightLeft(TreeNode node) {
    int h = 0;
    while (node != null) { h++; node = node.left; }
    return h;
}

int heightRight(TreeNode node) {
    int h = 0;
    while (node != null) { h++; node = node.right; }
    return h;
}
```

### 6.4 Key Formulas Cheat Sheet

| Concept | Formula |
|---------|---------|
| Max nodes at level `L` | `2^L` |
| Max nodes in tree of height `h` | `2^(h+1) âˆ’ 1` |
| Min height with `n` nodes | `âŒŠlogâ‚‚(n)âŒ‹` |
| Max edges | `n âˆ’ 1` |
| Leaf count (full binary tree) | `internal_nodes + 1` |
| Array: left child of `i` | `2i + 1` |
| Array: right child of `i` | `2i + 2` |
| Array: parent of `i` | `(i - 1) / 2` |

---

## 7. Tree Construction / Serialization

### 7.1 Serialize & Deserialize (Pre-order with null markers, LC 297)

```java
// ---- Serialize: Tree -> String ----
String serialize(TreeNode root) {
    StringBuilder sb = new StringBuilder();
    serializeHelper(root, sb);
    return sb.toString();
}

void serializeHelper(TreeNode node, StringBuilder sb) {
    if (node == null) {
        sb.append("null,");
        return;
    }
    sb.append(node.val).append(",");
    serializeHelper(node.left, sb);
    serializeHelper(node.right, sb);
}

// ---- Deserialize: String -> Tree ----
TreeNode deserialize(String data) {
    Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
    return deserializeHelper(queue);
}

TreeNode deserializeHelper(Queue<String> queue) {
    String val = queue.poll();
    if (val.equals("null")) return null;
    TreeNode node = new TreeNode(Integer.parseInt(val));
    node.left = deserializeHelper(queue);
    node.right = deserializeHelper(queue);
    return node;
}
```

#### Dry Run - Serialize

```
Tree:
    1
   / \
  2   3
     / \
    4   5

serialize(1):
  append "1,"
  serialize(2):
    append "2,"
    serialize(null) -> "null,"
    serialize(null) -> "null,"
  serialize(3):
    append "3,"
    serialize(4):
      append "4,"
      serialize(null) -> "null,"
      serialize(null) -> "null,"
    serialize(5):
      append "5,"
      serialize(null) -> "null,"
      serialize(null) -> "null,"

Result: "1,2,null,null,3,4,null,null,5,null,null,"
```

#### Dry Run - Deserialize

```
Input: "1,2,null,null,3,4,null,null,5,null,null,"
Queue: [1, 2, null, null, 3, 4, null, null, 5, null, null]

deserializeHelper:
  poll "1" -> node(1)
  |-- left = deserializeHelper:
  |   poll "2" -> node(2)
  |   |-- left = poll "null" -> null
  |   |-- right = poll "null" -> null
  |-- right = deserializeHelper:
      poll "3" -> node(3)
      |-- left = deserializeHelper:
      |   poll "4" -> node(4) -> null, null
      |-- right = deserializeHelper:
          poll "5" -> node(5) -> null, null

Reconstructed:
    1              CORRECT!
   / \
  2   3
     / \
    4   5
```

### 7.2 Build Tree from Inorder & Preorder (LC 105)

```java
Map<Integer, Integer> indexMap;
int preIdx = 0;

TreeNode buildTree(int[] preorder, int[] inorder) {
    indexMap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++)
        indexMap.put(inorder[i], i);
    return build(preorder, 0, inorder.length - 1);
}

TreeNode build(int[] preorder, int inStart, int inEnd) {
    if (inStart > inEnd) return null;
    int rootVal = preorder[preIdx++];
    TreeNode root = new TreeNode(rootVal);
    int inIdx = indexMap.get(rootVal);
    root.left = build(preorder, inStart, inIdx - 1);
    root.right = build(preorder, inIdx + 1, inEnd);
    return root;
}
```

#### Dry Run

```
preorder = [3, 9, 20, 15, 7]
inorder  = [9, 3, 15, 20, 7]

Step 1: preIdx=0, rootVal=3
  3 found at inorder index 1
  Left: inorder[0..0] = [9]
  Right: inorder[2..4] = [15, 20, 7]

Step 2: preIdx=1, rootVal=9
  9 found at inorder index 0
  Left: null, Right: null -> Leaf

Step 3: preIdx=2, rootVal=20
  20 found at inorder index 3
  Left: inorder[2..2] = [15]
  Right: inorder[4..4] = [7]

Step 4: preIdx=3, rootVal=15 -> Leaf
Step 5: preIdx=4, rootVal=7 -> Leaf

Result:
        3
       / \
      9  20
        /  \
       15   7      CORRECT!
```

### 7.3 Build Tree from Inorder & Postorder (LC 106)

```java
int postIdx;

TreeNode buildTree(int[] inorder, int[] postorder) {
    postIdx = postorder.length - 1;
    Map<Integer, Integer> indexMap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++)
        indexMap.put(inorder[i], i);
    return build(inorder, postorder, 0, inorder.length - 1, indexMap);
}

TreeNode build(int[] inorder, int[] postorder, int inStart, int inEnd,
               Map<Integer, Integer> indexMap) {
    if (inStart > inEnd) return null;
    int rootVal = postorder[postIdx--];
    TreeNode root = new TreeNode(rootVal);
    int inIdx = indexMap.get(rootVal);
    root.right = build(inorder, postorder, inIdx + 1, inEnd, indexMap);
    root.left = build(inorder, postorder, inStart, inIdx - 1, indexMap);
    return root;
}
```

> Note: In postorder, build **right first** (last element is root, so traverse right then left).

### 7.4 Array Representation (0-indexed)

For a node at index `i`:
- **Left child:** `2*i + 1`
- **Right child:** `2*i + 2`
- **Parent:** `(i - 1) / 2`

```
Array: [1, 2, 3, 4, 5, 6, 7]

Index:  0  1  2  3  4  5  6

Tree:
        1          (i=0)
       / \
      2   3        (i=1, i=2)
     / \ / \
    4  5 6  7      (i=3,4,5,6)
```

---

## 8. Top Interview Problems with Dry Runs

---

### 8.1 Maximum Depth of Binary Tree (LeetCode 104)

```java
int maxDepth(TreeNode root) {
    if (root == null) return 0;
    int leftDepth = maxDepth(root.left);
    int rightDepth = maxDepth(root.right);
    return 1 + Math.max(leftDepth, rightDepth);
}
```

#### Dry Run

```
        3
       / \
      9  20
        /  \
       15   7

maxDepth(3):
|-- maxDepth(9):
|   |-- null -> 0, null -> 0
|   |-- return 1 + max(0,0) = 1
|-- maxDepth(20):
|   |-- maxDepth(15) -> 1
|   |-- maxDepth(7)  -> 1
|   |-- return 1 + max(1,1) = 2
|-- return 1 + max(1,2) = 3
```

**Answer: 3**

---

### 8.2 Diameter of Binary Tree (LeetCode 543)

```java
int diameter = 0;

int height(TreeNode root) {
    if (root == null) return 0;
    int left = height(root.left);
    int right = height(root.right);
    diameter = Math.max(diameter, left + right);
    return 1 + Math.max(left, right);
}
```

#### Dry Run

```
        1
       / \
      2   3
     / \
    4   5

| Call    | left | right | diameter | return |
|---------|------|-------|----------|--------|
| h(4)    | 0    | 0     | -        | 1      |
| h(5)    | 0    | 0     | -        | 1      |
| h(2)    | 1    | 1     | max(0,2)=2 | 2    |
| h(3)    | 0    | 0     | -        | 1      |
| h(1)    | 2    | 1     | max(2,3)=3 | 3    |

Diameter = 3 (path: 4->2->1->3)
```

---

### 8.3 Validate BST (LeetCode 98)

```java
boolean isValidBST(TreeNode root) {
    return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
}

boolean validate(TreeNode node, long min, long max) {
    if (node == null) return true;
    if (node.val <= min || node.val >= max) return false;
    return validate(node.left, min, node.val)
        && validate(node.right, node.val, max);
}
```

#### Dry Run - Valid

```
        5
       / \
      1   7
         / \
        6   8

validate(5, -INF, +INF) -> 5 in range
  validate(1, -INF, 5) -> 1 in range -> true
  validate(7, 5, +INF) -> 7 in range
    validate(6, 5, 7) -> 6 in range -> true
    validate(8, 7, +INF) -> 8 in range -> true
Result: TRUE
```

#### Dry Run - Invalid

```
        5
       / \
      1   7
         / \
        4   8

validate(4, 5, 7) -> 4 <= 5 -> FALSE!
```

---

### 8.4 Lowest Common Ancestor (LeetCode 236)

```java
TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) return root;
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    if (left != null && right != null) return root;
    return left != null ? left : right;
}
```

#### Dry Run - LCA of 4 and 5

```
        3
       / \
      5   1
     / \ / \
    6  2 0  8
      / \
     7   4

LCA(3, p=4, q=5):
  left  = LCA(5, 4, 5): root==q -> return 5
  right = LCA(1, 4, 5):
    LCA(0) -> null, LCA(8) -> null -> return null
  left=5, right=null -> return 5

LCA = 5
```

---

### 8.5 Level Order Traversal (LeetCode 102)

```java
List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
        int size = queue.size();
        List<Integer> level = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            level.add(node.val);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        result.add(level);
    }
    return result;
}
```

#### Dry Run

```
        3
       / \
      9  20
        /  \
       15   7

Iter 1: queue=[3], size=1 -> level=[3]
Iter 2: queue=[9,20], size=2 -> level=[9,20]
Iter 3: queue=[15,7], size=2 -> level=[15,7]

Result: [[3], [9,20], [15,7]]
```

---

### 8.6 Symmetric Tree (LeetCode 101)

```java
boolean isSymmetric(TreeNode root) {
    return isMirror(root.left, root.right);
}

boolean isMirror(TreeNode l, TreeNode r) {
    if (l == null && r == null) return true;
    if (l == null || r == null) return false;
    return l.val == r.val
        && isMirror(l.left, r.right)
        && isMirror(l.right, r.left);
}
```

#### Dry Run

```
        1
       / \
      2   2
     / \ / \
    3  4 4  3

isMirror(2,2): 2==2
  isMirror(3,3): 3==3, null/null, null/null -> true
  isMirror(4,4): 4==4, null/null, null/null -> true
Result: TRUE
```

---

### 8.7 Path Sum (LeetCode 112)

```java
boolean hasPathSum(TreeNode root, int targetSum) {
    if (root == null) return false;
    if (root.left == null && root.right == null)
        return targetSum - root.val == 0;
    return hasPathSum(root.left, targetSum - root.val)
        || hasPathSum(root.right, targetSum - root.val);
}
```

#### Dry Run - target = 26

```
        5
       / \
      4   8
     /   / \
    11  13  4
   / \       \
  7   2       1

hasPathSum(5, 26):
  hasPathSum(4, 21):
    hasPathSum(11, 17):
      hasPathSum(7, 6): leaf, 6-7!=0 -> false
      hasPathSum(2, 6): leaf, 6-2!=0 -> false
  hasPathSum(8, 21):
    hasPathSum(13, 13): leaf, 13-13==0 -> TRUE!

Path: 5 -> 8 -> 13 = 26
```

---

### 8.8 Invert Binary Tree (LeetCode 226)

```java
TreeNode invertTree(TreeNode root) {
    if (root == null) return null;
    TreeNode temp = root.left;
    root.left = invertTree(root.right);
    root.right = invertTree(temp);
    return root;
}
```

#### Dry Run

```
Original:            Inverted:
    4                    4
   / \                  / \
  2   7                7   2
 / \ / \              / \ / \
1  3 6  9            9  6 3  1

At root 4:
  invert(2): swap 1<->3
  invert(7): swap 9<->6
  swap left(2) <-> right(7)
```

---

### 8.9 Flatten Binary Tree to Linked List (LeetCode 114)

```java
TreeNode prev = null;

void flatten(TreeNode root) {
    if (root == null) return;
    flatten(root.right);
    flatten(root.left);
    root.right = prev;
    root.left = null;
    prev = root;
}
```

#### Dry Run

```
Original:
    1
   / \
  2   5
 / \   \
3   4   6

Process (reverse postorder: right -> left -> root):

Visit 6: prev=6              (6->null)
Visit 5: 5.right=6, prev=5   (5->6)
Visit 4: 4.right=5, prev=4   (4->5->6)
Visit 3: 3.right=4, prev=3   (3->4->5->6)
Visit 2: 2.right=3, prev=2   (2->3->4->5->6)
Visit 1: 1.right=2, prev=1   (1->2->3->4->5->6)

Result: 1->2->3->4->5->6
```

---

### 8.10 Count Good Nodes (LeetCode 1448)

```java
int count = 0;

void dfs(TreeNode node, int maxSoFar) {
    if (node == null) return;
    if (node.val >= maxSoFar) count++;
    maxSoFar = Math.max(maxSoFar, node.val);
    dfs(node.left, maxSoFar);
    dfs(node.right, maxSoFar);
}

int goodNodes(TreeNode root) {
    dfs(root, Integer.MIN_VALUE);
    return count;
}
```

#### Dry Run

```
        3
       / \
      1   4
     /   / \
    3   1   5

dfs(3, -INF): count=1, max=3
  dfs(1, 3): no count
    dfs(3, 3): count=2
  dfs(4, 3): count=3, max=4
    dfs(1, 4): no count
    dfs(5, 4): count=4

Answer: 4
```

---

### 8.11 Right Side View (LeetCode 199)

```java
List<Integer> rightSideView(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            if (i == size - 1) result.add(node.val);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
    }
    return result;
}
```

#### Dry Run

```
        1
       / \
      2   3
       \   \
        5   4

Level 0: [1]    -> last = 1
Level 1: [2,3]  -> last = 3
Level 2: [5,4]  -> last = 4

Result: [1, 3, 4]
```

---

### 8.12 Path Sum III (LeetCode 437)

```java
int pathCount = 0;

void dfs(TreeNode node, long currSum, int target, Map<Long, Integer> prefix) {
    if (node == null) return;
    currSum += node.val;
    if (prefix.containsKey(currSum - target))
        pathCount += prefix.get(currSum - target);
    prefix.merge(currSum, 1, Integer::sum);
    dfs(node.left, currSum, target, prefix);
    dfs(node.right, currSum, target, prefix);
    prefix.merge(currSum, -1, Integer::sum);
}

int pathSum(TreeNode root, int targetSum) {
    Map<Long, Integer> prefix = new HashMap<>();
    prefix.put(0L, 1);
    dfs(root, 0, targetSum, prefix);
    return pathCount;
}
```

> Same idea as **subarray sum equals k** - prefix sum + hashmap!

---

## 9. Tips, Tricks & Patterns

### 9.1 Key Recursion Patterns

| Pattern | When to Use | Template |
|---------|-------------|----------|
| **DFS** | Explore all paths | Visit, recurse left, recurse right |
| **Divide & Conquer** | Compute per subtree | Solve left, solve right, combine |
| **Top-Down** | Pass info root->leaves | Pass accumulator as parameter |
| **Bottom-Up** | Compute and return | Return values, combine at parent |

### 9.2 When to Use What

| Problem Type | Approach |
|-------------|----------|
| Find something on a path | DFS with backtracking |
| Level-by-level processing | BFS with queue |
| Find min/max depth | DFS or BFS |
| Validate property (BST) | DFS with range |
| Find LCA | DFS, return node when found in both subtrees |
| Count paths with sum | Prefix sum + hashmap |
| Serialize/deserialize | Pre-order with null markers |
| Construct from traversals | Recursion + hashmap for indices |

### 9.3 Common Mistakes to Avoid

1. **Forgetting base case** - Always handle `root == null`
2. **Not restoring state** - In backtracking, undo changes after recursion
3. **Off-by-one in BFS** - Use `queue.size()` before the loop, not inside
4. **Mixing up preorder/inorder** - pre=in first, in=middle, post=last
5. **Integer overflow** - Use `long` for range checking in BST validation
6. **Modifying tree during traversal** - Be careful with Morris or flattening

### 9.4 Java Tips for Tree Problems

```java
// Null check
if (root == null) return 0;

// Create node
TreeNode node = new TreeNode(val);

// Queue for BFS
Queue<TreeNode> queue = new LinkedList<>();
queue.offer(root);

// Stack for DFS
Stack<TreeNode> stack = new Stack<>();
stack.push(root);

// HashMap for parent mapping
Map<TreeNode, TreeNode> parentMap = new HashMap<>();

// HashSet for visited nodes
Set<TreeNode> visited = new HashSet<>();
```

---

## 10. Complexity Cheat Sheet

| Operation | BST (Avg) | BST (Worst) | AVL | Red-Black |
|-----------|-----------|-------------|-----|-----------|
| Search | O(log n) | O(n) | O(log n) | O(log n) |
| Insert | O(log n) | O(n) | O(log n) | O(log n) |
| Delete | O(log n) | O(n) | O(log n) | O(log n) |
| Traversal | O(n) | O(n) | O(n) | O(n) |

| Algorithm | Time | Space |
|-----------|------|-------|
| DFS (recursive) | O(n) | O(h) |
| DFS (iterative) | O(n) | O(h) |
| BFS (level-order) | O(n) | O(w) |
| Morris Traversal | O(n) | O(1) |
| Build from traversals | O(n) | O(n) |

### Space Complexity by Tree Shape

| Tree Shape | Height (h) | Max Width (w) |
|-----------|------------|---------------|
| Perfect | log2(n) | n/2 |
| Complete | log2(n) | n/2 |
| Skewed | n-1 | 1 |

---

## LeetCode Must-Do Problems

| # | Problem | Key Concept |
|---|---------|-------------|
| 104 | Maximum Depth of Binary Tree | DFS |
| 100 | Same Tree | Recursive comparison |
| 101 | Symmetric Tree | Mirror check |
| 226 | Invert Binary Tree | Swap left/right |
| 110 | Balanced Binary Tree | Height with -1 sentinel |
| 124 | Binary Tree Maximum Path Sum | Global max tracking |
| 236 | Lowest Common Ancestor | Recursive search |
| 98 | Validate BST | Range checking |
| 102 | Level Order Traversal | BFS |
| 105 | Construct from Inorder & Preorder | Divide & Conquer |
| 106 | Construct from Inorder & Postorder | Divide & Conquer |
| 114 | Flatten to Linked List | Reverse postorder |
| 297 | Serialize and Deserialize | Preorder + null markers |
| 437 | Path Sum III | Prefix sum |
| 199 | Right Side View | BFS last node |
| 958 | Check Completeness | Level-order + null check |
| 1448 | Count Good Nodes | DFS with max tracker |

---

## Further Reading

- **Books** - *Introduction to Algorithms* (CLRS) ch. 12-13, *Algorithms* by Sedgewick & Wayne
- **Visualizers** - https://www.cs.usfca.edu/~galles/visualization/BST.html
- **Practice** - https://leetcode.com/tag/binary-tree/

---

*Good luck with your 90-day DSA challenge!*
