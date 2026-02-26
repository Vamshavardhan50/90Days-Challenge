class Node {
  int data;
  Node next;

  Node(int data) {
    this.data = data;
    this.next = null;
  }
}

public class DeleteNodeAtK {
  public static Node ConvertToLL(int[] arr) {
    Node head = new Node(arr[0]);
    Node current = head;
    for (int i = 1; i < arr.length; i++) {
      Node temp = new Node(arr[i]);
      current.next = temp;
      current = temp;
    }
    return head;
  }

  public static Node deleteNode(Node head, int k) {
    if (head == null)
      return null;

    if (head.data == k) {
      Node temp = head;
      head = head.next;
      temp = null;
      return head;
    }
    Node temp = head;
    Node prev = null;
    while (temp != null) {
      if (temp.data == k) {
        prev.next = temp  .next;
        temp = null;
        break;
      }
      prev = temp;
      temp = temp.next;
    }
    return head;
  }

  public static void main(String[] args) {
    int[] arr = { 4, 5, 1, 9 };

    Node head = ConvertToLL(arr); // first create list
    head = deleteNode(head, 5); // then delete

    Node temp = head; // then print
    while (temp != null) {
      System.out.print(temp.data + "->");
      temp = temp.next;
    }
  }
}
