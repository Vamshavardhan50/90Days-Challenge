class Node {
  int data;
  Node next;

  Node(int data) {
    this.data = data;
    this.next = null;
  }
}

public class OddEvenLL {
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

  public static Node EvenOddLL(Node head) {
    // Handle the edge cases
    if (head == null || head.next == null)
      return head;

    Node EvenHead = null, EvenTail = null;
    Node OddHead = null, OddTail = null;

    Node current = head;
    int index = 0;
    while (current != null) {
      if (index % 2 == 0) {
        if (EvenHead == null) {
          EvenHead = current;
          EvenTail = current;
        } else {
          EvenTail.next = current;
          EvenTail = current;
        }
      } else {
        if (OddHead == null) {
          OddHead = current;
          OddTail = current;
        } else {
          OddTail.next = current;
          OddTail = current;
        }
      }
      index++;
      current = current.next;
    }
    // it tells that only odd numbers are present in the LL
    if (EvenHead == null) {
      return OddHead;
    }

    // it tells that only Even numbers are present in the LL
    if (OddHead == null) {
      return EvenHead;
    }
    OddTail.next = null;

    EvenTail.next = OddHead;
    return EvenHead;
  }

  public static void main(String[] args) {
    int[] arr = { 2, 1, 3, 5, 6, 4, 7 };
    Node head = ConvertToLL(arr);

    Node temp = EvenOddLL(head);
    while (temp != null) {
      System.out.print(temp.data + " -> ");
      temp = temp.next;
    }

  }
}
