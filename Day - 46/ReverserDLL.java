class Node {
  Node prev;
  int data;
  Node next;

  Node(int data) {
    this.prev = null;
    this.data = data;
    this.next = null;
  }

  Node(int data, Node next, Node prev) {
    this.prev = prev;
    this.data = data;
    this.next = next;
  }
}

public class ReverserDLL {
  public static Node convert2DLL(int[] arr) {
    if (arr == null || arr.length == 0) {
      return null;
    }
    Node head = new Node(arr[0]);
    Node prev = head;
    for (int i = 1; i < arr.length; i++) {
      Node newNode = new Node(arr[i]);
      prev.next = newNode;
      newNode.prev = prev;
      prev = newNode;
    }
    return head;
  }

  public static Node Reverse(Node head) {
    if (head == null || head.next == null)
      return head;

    Node current = head;
    Node temp = null;
    while (current != null) {
      temp = current.prev;

      current.prev = current.next;
      current.next = temp;

      current = current.prev;
    }

    if (temp != null)
      return temp.prev;
    return head;

  }

  public static void main(String[] args) {
    int[] arr = { 1, 2, 3, 4, 5 };
    Node head = convert2DLL(arr);
    head = Reverse(head);

    Node temp = head;
    while (temp != null) {
      System.out.println(temp.data);
      temp = temp.next;

    }
  }
}
