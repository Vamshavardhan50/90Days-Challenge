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

public class MiddleOftheLL {

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

  public static int LengthOfLL(Node head) {
    Node temp = head;
    int count = 0;
    while (temp != null) {
      count++;
      temp = temp.next;
    }
    return count;
  }

  public static Node Middle(Node head) {
    int Length = LengthOfLL(head);
    int mid = Length / 2;

    Node temp = head;
    int count = 0;
    while (temp != null) {
      if (count == mid) {
        return temp;
      }
      count++;
      temp = temp.next;
    }
    return null;

  }

  public static void main(String[] args) {

    int[] arr = { 1, 2, 3, 4, 5 };
    Node head = convertToLL(arr);
    head = Middle(head);
    Node temp = head;
    while (temp != null) {
      System.out.println(temp.data);
      temp = temp.next;
    }
  }
}
