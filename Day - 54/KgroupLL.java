class Node {
  int data;
  Node next;

  Node(int data) {
    this.data = data;
    this.next = null;
  }
}

public class KgroupLL {

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

  public static Node getkthNode(Node head, int k) {
    Node temp = head;
    while (temp != null && k > 1) {
      k--;
      temp = temp.next;
    }
    return temp;

  }

  public static Node ReverseLL(Node head) {
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

  public static Node KRotate(Node head, int k) {

    Node temp = head;
    Node prevNode = null;

    while (temp != null) {

      Node KthNode = getkthNode(temp, k);

      if (KthNode == null) {
        if (prevNode != null)
          prevNode.next = temp;
        break;
      }

      Node nextNode = KthNode.next;
      KthNode.next = null;

      Node newHead = ReverseLL(temp);

      if (temp == head) {
        head = newHead;
      } else {
        prevNode.next = newHead;
      }

      prevNode = temp;
      temp = nextNode;
    }

    return head;
  }

  public static void main(String[] args) {
    int[] arr = { 1, 2, 3, 4, 5 };
    int k = 2;
    Node head = ConvertToLL(arr);
    head = KRotate(head, k);
    Node temp = head;
    while (temp != null) {
      System.out.println(temp.data);
      temp = temp.next;
    }

  }

}
