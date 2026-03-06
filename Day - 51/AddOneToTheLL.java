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

public class AddOneToTheLL {

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

  public static Node reverse(Node head) {
    Node prev = null;
    Node current = head;

    while (current != null) {
      Node next = current.next;
      current.next = prev;

      prev = current;
      current = next;
    }
    return prev;
  }

  public static Node addNumber(Node head) {
    head = reverse(head);

    Node temp = head;
    int carry = 1; // adding one

    while (temp != null && carry == 1) {
      int sum = temp.data + carry;
      temp.data = sum % 10;
      carry = sum / 10;

      if (carry == 0)
        break;

      if (temp.next == null) {
        temp.next = new Node(carry);
        carry = 0;
      }

      temp = temp.next;
    }

    return reverse(head);
  }

  public static void main(String[] args) {
    int[] arr = { 7, 6, 8 };
    Node head = ConvertToLL(arr);

    head = addNumber(head);

    Node temp = head;
    while (temp != null) {
      System.out.print(temp.data + " -> ");
      temp = temp.next;
    }
  }
}