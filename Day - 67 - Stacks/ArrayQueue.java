public class ArrayQueue {

  private int[] data;
  private int front;
  private int rear;
  private int size;
  private int capacity;

  public ArrayQueue(int capacity) {
    this.capacity = capacity;
    data = new int[capacity];
    front = 0;
    rear = -1;
    size = 0;
  }

  public void enqueue(int value) {
    if (isFull()) {
      System.out.println("Queue Overflow");
      return;
    }
    rear = (rear + 1) % capacity;
    data[rear] = value;
    size++;
  }

  public int dequeue() {
    if (isEmpty()) {
      System.out.println("Queue Underflow");
      return -1;
    }
    int value = data[front];
    front = (front + 1) % capacity;
    size--;
    return value;
  }

  public int peek() {
    if (isEmpty()) {
      System.out.println("Queue is empty");
      return -1;
    }
    return data[front];
  }

  public boolean isFull() {
    return size == capacity;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public static void main(String[] args) {

    ArrayQueue q = new ArrayQueue(5);

    // Enqueue elements
    System.out.println("Enqueueing elements:");
    q.enqueue(10);
    q.enqueue(20);
    q.enqueue(30);
    q.enqueue(40);
    q.enqueue(50);

    // Try overflow
    q.enqueue(60);

    // Peek front
    System.out.println("\nFront element: " + q.peek());

    // Dequeue elements
    System.out.println("\nDequeuing elements:");
    System.out.println(q.dequeue()); // 10
    System.out.println(q.dequeue()); // 20

    // Add more (circular behavior)
    System.out.println("\nAdding after dequeue (circular):");
    q.enqueue(60);
    q.enqueue(70);

    // Final dequeue all
    System.out.println("\nFinal queue elements:");
    while (!q.isEmpty()) {
      System.out.println(q.dequeue());
    }

    // Try underflow
    q.dequeue();
  }
}
