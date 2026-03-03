class Node {
  int data;
  Node next;

  Node(int data) {
    this.data = data;
    this.next = null;
  }
}

public class DelMiddleNodeoftheLL {

  public static Node ConvertToLL(int[] arr) {
    if (arr.length == 0)
      return null;

    Node head = new Node(arr[0]);
    Node current = head;

    for (int i = 1; i < arr.length; i++) {
      current.next = new Node(arr[i]);
      current = current.next;
    }

    return head;
  }

  // Delete middle node
  public static Node delete(Node head) {

    // Edge cases
    if (head == null || head.next == null)
      return null;

    Node slow = head;
    Node fast = head;
    Node prev = null;

    // Correct loop condition
    while (fast != null && fast.next != null) {
      prev = slow;
      slow = slow.next;
      fast = fast.next.next;
    }

    // Remove middle node
    prev.next = slow.next;

    return head;
  }

  public static void main(String[] args) {
    int[] arr = { 1, 2, 3, 4, 5 };
    Node head = ConvertToLL(arr);

    head = delete(head);

    Node temp = head;
    while (temp != null) {
      System.out.print(temp.data + " -> ");
      temp = temp.next;
    }
  }
}