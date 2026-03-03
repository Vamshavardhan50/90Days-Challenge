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

public class ReverseLL {

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

  public static Node Reverse(Node head) {
    if (head == null || head.next == null)
      return head;
    Node prev = null;
    Node current = head;
    Node next = null;

    while (current != null) {
      next = current.next;
      current.next = prev;

      prev = current;
      current = next;
    }

    return prev;
  }

  public static void main(String[] args) {

    int[] arr = { 1, 2, 3, 4, 5 };
    Node head = convertToLL(arr);
    head = Reverse(head);
    Node temp = head;
    while (temp != null) {
      System.out.println(temp.data);
      temp = temp.next;
    }
  }
}
