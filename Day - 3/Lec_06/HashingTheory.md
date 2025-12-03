# Hashing - Complete Theory Notes

## What is Hashing?

**Hashing** is a technique used to uniquely identify a specific object from a group of similar objects. It converts data (keys) into a fixed-size value (hash code) using a **hash function**, which is then used as an index to store or retrieve data from a data structure called a **hash table**.

### Real-World Analogy:
Think of a library system where books are assigned specific shelf numbers based on their ISBN. Instead of searching through all books, you use the ISBN to directly find the shelf location - this is hashing!

---

## Key Components of Hashing

### 1. **Hash Function**
A mathematical function that converts a key into an array index (hash code).

```
Hash Function: h(key) = index

Example: h(key) = key % table_size
```

**Properties of a Good Hash Function:**
- **Deterministic**: Same key always produces same hash value
- **Uniform Distribution**: Spreads keys evenly across hash table
- **Fast Computation**: Quick to calculate
- **Minimize Collisions**: Different keys should produce different hash values

### 2. **Hash Table**
An array-based data structure that stores key-value pairs using hash codes as indices.

```
Index:  0    1    2    3    4    5    6
      ┌────┬────┬────┬────┬────┬────┬────┐
      │ 14 │    │ 16 │    │ 11 │    │ 20 │
      └────┴────┴────┴────┴────┴────┴────┘

Hash Function: h(key) = key % 7
14 % 7 = 0 → stored at index 0
16 % 7 = 2 → stored at index 2
11 % 7 = 4 → stored at index 4
20 % 7 = 6 → stored at index 6
```

### 3. **Hash Code**
The integer value returned by the hash function, used as an index in the hash table.

---

## Why Use Hashing?

### Advantages:
✅ **Fast Access**: O(1) average time for search, insert, delete  
✅ **Efficient**: No need to compare with every element  
✅ **Direct Access**: Calculate position instead of searching  
✅ **Space Efficient**: When implemented properly  
✅ **Flexible**: Can store any type of data  

### Use Cases:
- **Database Indexing**: Quick data retrieval
- **Caching**: Store frequently accessed data
- **Password Storage**: Secure password verification
- **Cryptography**: Digital signatures, message authentication
- **Symbol Tables**: Compiler design
- **Dictionaries**: Fast key-value lookups
- **Sets**: Check membership in O(1) time

---

## Hash Functions

### Common Hash Functions:

#### 1. **Division Method** (Most Common)
```
h(key) = key % table_size

Example: key = 25, table_size = 10
h(25) = 25 % 10 = 5
```
**Note**: Choose table_size as a prime number to reduce collisions.

#### 2. **Multiplication Method**
```
h(key) = ⌊table_size × (key × A mod 1)⌋
where A is a constant (0 < A < 1)

Knuth suggests: A ≈ 0.6180339887 (Golden Ratio - 1)
```

#### 3. **Mid-Square Method**
```
1. Square the key
2. Extract middle digits
3. Use as hash value

Example: key = 60
60² = 3600
Middle digits: 60
h(60) = 60 % table_size
```

#### 4. **Folding Method**
```
1. Divide key into equal parts
2. Add the parts
3. Use result as hash value

Example: key = 123456789
Parts: 123 + 456 + 789 = 1368
h(key) = 1368 % table_size
```

#### 5. **String Hashing**
```java
// Polynomial rolling hash
h(string) = (s[0] × p⁰ + s[1] × p¹ + ... + s[n-1] × pⁿ⁻¹) % m

// Java's String.hashCode()
h = s[0] × 31^(n-1) + s[1] × 31^(n-2) + ... + s[n-1]
```

---

## Collision in Hashing

### What is a Collision?
When two or more different keys produce the same hash value (same index).

```
Hash Function: h(key) = key % 7

h(15) = 15 % 7 = 1
h(22) = 22 % 7 = 1  ← COLLISION!

Both keys map to index 1
```

### Types of Collisions:

1. **Minor Collision**: Two keys hash to same bucket but different slots
2. **Major Collision**: Two keys hash to exact same slot

---

## Collision Resolution Techniques

### A. **Open Addressing** (Closed Hashing)
All elements stored in the hash table itself. When collision occurs, probe for next available slot.

#### 1. **Linear Probing**
Check next slot sequentially until empty slot found.

```
Formula: h(key, i) = (h(key) + i) % table_size
where i = 0, 1, 2, 3, ...

Example: Insert 15, 22, 8 into table of size 7
h(key) = key % 7

Insert 15: h(15) = 1 → Place at index 1
Insert 22: h(22) = 1 → Collision!
           Try h(22, 1) = (1 + 1) % 7 = 2 → Place at index 2
Insert 8:  h(8) = 1 → Collision!
           Try h(8, 1) = (1 + 1) % 7 = 2 → Collision!
           Try h(8, 2) = (1 + 2) % 7 = 3 → Place at index 3

Index:  0    1    2    3    4    5    6
      ┌────┬────┬────┬────┬────┬────┬────┐
      │    │ 15 │ 22 │ 8  │    │    │    │
      └────┴────┴────┴────┴────┴────┴────┘
```

**Advantages**: Simple, good cache performance  
**Disadvantages**: Primary clustering (consecutive occupied slots)

#### 2. **Quadratic Probing**
Check slots at quadratic intervals.

```
Formula: h(key, i) = (h(key) + i²) % table_size
where i = 0, 1, 2, 3, ...

Example: Insert 15, 22, 8 into table of size 7

Insert 15: h(15) = 1 → Place at index 1
Insert 22: h(22) = 1 → Collision!
           Try h(22, 1) = (1 + 1²) % 7 = 2 → Place at index 2
Insert 8:  h(8) = 1 → Collision!
           Try h(8, 1) = (1 + 1²) % 7 = 2 → Collision!
           Try h(8, 2) = (1 + 4) % 7 = 5 → Place at index 5

Index:  0    1    2    3    4    5    6
      ┌────┬────┬────┬────┬────┬────┬────┐
      │    │ 15 │ 22 │    │    │ 8  │    │
      └────┴────┴────┴────┴────┴────┴────┘
```

**Advantages**: Reduces primary clustering  
**Disadvantages**: Secondary clustering, may not probe all slots

#### 3. **Double Hashing**
Use second hash function to determine probe interval.

```
Formula: h(key, i) = (h₁(key) + i × h₂(key)) % table_size
where:
  h₁(key) = key % table_size (primary hash)
  h₂(key) = prime - (key % prime) (secondary hash)

Example:
h₁(key) = key % 7
h₂(key) = 5 - (key % 5)

Insert 15: h₁(15) = 1 → Place at index 1
Insert 22: h₁(22) = 1 → Collision!
           h₂(22) = 5 - (22 % 5) = 3
           Try (1 + 1×3) % 7 = 4 → Place at index 4
```

**Advantages**: Minimal clustering, better distribution  
**Disadvantages**: More computation required

### B. **Chaining** (Open Hashing)
Each slot in hash table contains a linked list. Colliding elements stored in same list.

```
Hash Table with Chaining:

Index:  0    1         2       3    4         5    6
      ┌────┬────┬────┬────┬────┬────┬────┬────┐
      │    │ 15 │    │ 16 │    │ 11 │    │ 20 │
      └────┴─│──┴────┴────┴────┴─│──┴────┴────┘
             │                    │
             ↓                    ↓
           ┌────┐               ┌────┐
           │ 22 │               │ 18 │
           └─│──┘               └────┘
             │
             ↓
           ┌────┐
           │ 36 │
           └────┘

Keys: 15, 22, 36 → all hash to index 1
Keys: 11, 18 → both hash to index 4
```

**Advantages**:
- Simple implementation
- Hash table never fills up
- Less sensitive to hash function
- Easy deletion

**Disadvantages**:
- Extra memory for pointers
- Cache performance suffers
- Can degrade to O(n) if many collisions

---

## Load Factor

**Load Factor (α)** = Number of elements / Table size

```
α = n / m
where:
  n = number of elements
  m = table size

Example: 10 elements in table of size 20
α = 10 / 20 = 0.5
```

### Impact of Load Factor:

| Load Factor | Performance | Action |
|-------------|-------------|---------|
| α < 0.5 | Excellent | Good performance |
| 0.5 ≤ α < 0.7 | Good | Acceptable |
| 0.7 ≤ α < 0.9 | Fair | Consider resizing |
| α ≥ 0.9 | Poor | **Rehashing needed** |

### Guidelines:
- **Open Addressing**: Keep α < 0.7
- **Chaining**: Can handle α > 1, but keep < 1 for best performance

---

## Rehashing

When load factor exceeds threshold, create larger hash table and reinsert all elements.

### Rehashing Process:

```
1. Create new hash table (usually double size)
2. For each element in old table:
   - Calculate new hash value
   - Insert into new table
3. Delete old table

Example:
Old table size: 7, Load factor: 6/7 = 0.857
New table size: 17 (next prime after 14)

Reinsert all 6 elements using h(key) = key % 17
```

**Time Complexity**: O(n) where n is number of elements

---

## Time Complexity

### Average Case (with good hash function):
- **Search**: O(1)
- **Insert**: O(1)
- **Delete**: O(1)

### Worst Case (all elements hash to same index):
- **Search**: O(n)
- **Insert**: O(n)
- **Delete**: O(n)

### Factors Affecting Performance:
1. Quality of hash function
2. Load factor
3. Collision resolution method
4. Distribution of keys

---

## Space Complexity

- **Open Addressing**: O(n) - all elements in table
- **Chaining**: O(n + m) - elements + table + pointers
  - where n = elements, m = table size

---

## Applications of Hashing

### 1. **HashSet** (Unique Elements)
```java
// Check if element exists: O(1)
HashSet<Integer> set = new HashSet<>();
set.add(5);
set.contains(5); // true - O(1)
```

### 2. **HashMap** (Key-Value Pairs)
```java
// Store and retrieve by key: O(1)
HashMap<String, Integer> map = new HashMap<>();
map.put("Alice", 25);
map.get("Alice"); // 25 - O(1)
```

### 3. **Caching**
Store frequently accessed data for quick retrieval.

### 4. **Database Indexing**
Quick lookup of database records.

### 5. **Password Verification**
Store password hashes instead of actual passwords.

### 6. **Blockchain**
Each block contains hash of previous block.

### 7. **Compiler Symbol Tables**
Store variable names and their properties.

---

## Hash Table vs Other Data Structures

| Operation | Array | Linked List | BST | Hash Table |
|-----------|-------|-------------|-----|------------|
| **Search** | O(n) | O(n) | O(log n) | **O(1)*** |
| **Insert** | O(1)* | O(1)* | O(log n) | **O(1)*** |
| **Delete** | O(n) | O(n) | O(log n) | **O(1)*** |
| **Space** | O(n) | O(n) | O(n) | O(n) |
| **Ordered** | ✓ | ✗ | ✓ | ✗ |

*Average case with good hash function

---

## Perfect Hashing

**Perfect Hash Function**: No collisions - each key maps to unique slot.

### Types:

1. **Minimal Perfect Hash Function**
   - No collisions
   - Table size = number of keys
   - No wasted space

2. **Static Perfect Hashing**
   - Keys known in advance
   - Two-level hashing
   - O(1) worst-case lookup

**Used in**: Compilers, reserved keywords, static datasets

---

## Universal Hashing

Family of hash functions where collision probability is minimized.

```
Probability of collision for any two keys ≤ 1/m
where m = table size
```

**Purpose**: Defense against adversarial input (deliberate collisions)

---

## Comparison: Open Addressing vs Chaining

| Feature | Open Addressing | Chaining |
|---------|----------------|----------|
| **Storage** | All in table | Table + linked lists |
| **Cache** | Better (contiguous) | Worse (scattered) |
| **Space** | Fixed size | Can grow |
| **Load Factor** | Must be < 1 | Can be > 1 |
| **Deletion** | Complex (need tombstones) | Simple |
| **Implementation** | Harder | Easier |
| **Performance** | Degrades quickly when full | More stable |

---

## Best Practices

### 1. **Choose Good Hash Function**
- Distributes keys uniformly
- Fast to compute
- Minimizes collisions

### 2. **Select Appropriate Table Size**
- Use prime numbers
- Avoid powers of 2
- Provides better distribution

### 3. **Monitor Load Factor**
- Keep α < 0.7 for open addressing
- Rehash when threshold exceeded

### 4. **Choose Right Collision Resolution**
- **Chaining**: When memory isn't critical, frequent deletions
- **Open Addressing**: When cache performance critical, fixed size

### 5. **Handle Edge Cases**
- Null keys
- Empty table
- Full table (open addressing)

---

## Common Problems Using Hashing

1. **Find duplicates in array**: O(n) time
2. **Two Sum problem**: Find pair with given sum
3. **Longest consecutive sequence**: Find length
4. **Group anagrams**: Group strings with same letters
5. **Subarray sum equals K**: Count subarrays
6. **Frequency counting**: Count occurrences of elements
7. **First non-repeating character**: Find first unique char
8. **LRU Cache**: Implement cache with O(1) operations

---

## Key Takeaways

1. **Hashing provides O(1) average-case** search, insert, delete operations
2. **Hash function quality** determines performance
3. **Collisions are inevitable** - need resolution strategy
4. **Load factor** must be monitored and managed
5. **Trade-off**: Time efficiency vs space usage
6. **Not suitable** when ordered traversal needed
7. **Perfect for**: Lookups, caching, duplicate detection
8. **Widely used** in real-world applications (databases, compilers, caching)

---

## Practice Questions

1. What is the difference between hashing and encryption?
2. Why are prime numbers preferred for hash table size?
3. Explain primary clustering in linear probing.
4. When would you choose chaining over open addressing?
5. How does HashMap in Java handle collisions?
6. What happens when load factor exceeds threshold?
7. Design a hash function for strings.
8. How would you detect a cycle using hashing?
9. Implement a hash table with linear probing.
10. Explain how password hashing works for security.

---

## Java Implementation References

```java
// HashSet - stores unique elements
HashSet<Integer> set = new HashSet<>();

// HashMap - stores key-value pairs
HashMap<String, Integer> map = new HashMap<>();

// LinkedHashMap - maintains insertion order
LinkedHashMap<String, Integer> linkedMap = new LinkedHashMap<>();

// TreeMap - maintains sorted order (not hash-based)
TreeMap<String, Integer> treeMap = new TreeMap<>();

// Hashtable - synchronized (legacy, thread-safe)
Hashtable<String, Integer> table = new Hashtable<>();
```

---

## Cryptographic Hash Functions

Used for security purposes:

- **MD5**: 128-bit hash (deprecated - collisions found)
- **SHA-1**: 160-bit hash (deprecated - collisions found)
- **SHA-256**: 256-bit hash (widely used, secure)
- **SHA-3**: Latest standard (most secure)

**Properties**:
- **Pre-image resistance**: Can't find input from hash
- **Collision resistance**: Hard to find two inputs with same hash
- **Avalanche effect**: Small change in input drastically changes output

---

## Summary Diagram

```
                    HASHING
                       |
        +--------------+--------------+
        |                             |
   HASH FUNCTION              HASH TABLE
        |                             |
   Converts key                Array-based
   to index                    storage
        |                             |
        +-------------+---------------+
                      |
                 COLLISION?
                      |
         +------------+------------+
         |                         |
   OPEN ADDRESSING           CHAINING
         |                         |
    +---------+              Linked lists
    |         |              at each slot
Linear  Quadratic
Probing  Probing
Double Hashing
```

---

## Interactive Learning Resources

🔗 **Visualizations & Animations:**
- [VisuAlgo - Hash Table Visualization](https://visualgo.net/en/hashtable) - Interactive hashing visualization
- [USFCA Hash Table Visualization](https://www.cs.usfca.edu/~galles/visualization/OpenHash.html) - Animated hash tables
- [GeeksforGeeks Hashing](https://www.geeksforgeeks.org/hashing-data-structure/) - Comprehensive guide

📺 **Video Tutorials:**
- Search "Hashing Data Structure" on YouTube for animated explanations
