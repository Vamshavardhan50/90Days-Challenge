public class ArrayStack {
  protected int[] data;
  private int top;
  private int capacity;

  private static final int DEFAULT_SIZE = 10;

  // Default constructor
  public ArrayStack() {
    this.capacity = DEFAULT_SIZE;
    this.data = new int[capacity];
    this.top = -1;
  }

  // Parameterized constructor
  public ArrayStack(int size) {
    this.capacity = size;
    this.data = new int[capacity];
    this.top = -1;
  }

  public void push(int value) {
    if (isfull()) {
      System.out.println("Stack OverFlow");
      return;
    }
    data[++top] = value;
  }

  public int pop() {
    if (isEmpty()) {
      System.out.println("Stack UnderFlow");
      return -1;
    }
    return data[top--];
  }

  public boolean isfull() {
    return top == capacity - 1;
  }

  public boolean isEmpty() {
    return top == -1;
  }

  public int peek() {
    if (isEmpty()) {
      System.out.println("Stack is empty");
      return -1;
    }
    return data[top];
  }
}