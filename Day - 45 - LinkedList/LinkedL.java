class Node {
  int data;
  Node next;

  Node(int data) {
    this.data = data;
    this.next = null;
  }

  Node(int data, Node next) {
    this.data = data;
    this.next = next;
  }
}

public class LinkedL {
  public static Node convertToLL(int[] arr) {
    Node head = new Node(arr[0]);
    Node mover = head;

    for (int i = 1; i < arr.length; i++) {
      Node temp = new Node(arr[i]);
      mover.next = temp;
      mover = temp;
    }
    return head;
  }

  public static void TrasverseLL(Node head) {
    Node temp = head;
    while (temp != null) {
      System.out.print(temp.data + "-> ");
      temp = temp.next;
    }
  }

  public static void LengthOfLL(Node head) {
    Node temp = head;
    int count = 0;
    while (temp != null) {
      count++;
      temp = temp.next;
    }
    System.out.println(count);
  }

  public static Node removehead(Node head) {
    if (head == null)
      return null;

    return head.next;
  }

  public static Node removeTail(Node head) {
    if (head == null || head.next.next == null) {
      return null;
    }
    Node temp = head;
    int count = 0;

    while (temp.next.next != null) {
      temp = temp.next;
    }
    temp.next = null;
    return head;
  }

  public static Node InsertNode(Node head, int val) {
    return new Node(val, head);
  }

  public static Node InsertLast(Node head, int val) {
    if (head == null) {
      return new Node(val);
    }
    Node temp = head;
    while (temp.next != null) {
      temp = temp.next;
    }
    Node newNode = new Node(val);
    temp.next = newNode;
    return head;
  }

  public static void main(String[] args) {
    int[] arr = { 1, 2, 3, 4, 5 };
    Node head = convertToLL(arr);
    head = InsertLast(head, 50);
    Node temp = head;
    while (temp != null) {
      System.out.println(temp.data);
      temp = temp.next;
    }
  }

}
