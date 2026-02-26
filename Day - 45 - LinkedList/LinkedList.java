class Node {
  int data;
  Node next;

  Node(int data) {
    this.data = data;
    this.next = null;
  }
}

public class LinkedList {
  public static Node convertArr2LL(int[] arr) {
    Node head = new Node(arr[0]);
    Node mover = head;
    for (int i = 1; i < arr.length; i++) {
      Node temp = new Node(arr[i]);
      mover.next = temp;
      mover = temp;

    }
    return head;
  }

  public static void travserNode(Node head) {
    Node temp = head;
    System.out.print("LinkedList Data : ");
    while (temp != null) {
      System.out.print(temp.data + " ");
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
    System.out.println("Length : " + count);
  }

  public static void searchElement(Node head, int target) {
    Node temp = head;
    while (temp != null) {
      if (temp.data == target) {
        System.out.println("element is present");
      }
      temp = temp.next;
    }
  }

  public static Node removeTail(Node head) {
    if (head == null || head.next.next == null) {
      return null;
    }
    Node temp = head;

    while (temp.next.next != null) {
      temp = temp.next;
    }
    temp.next = null;
    return head;
  }

  public static Node removehead(Node head) {
    if (head == null) {
      return null;
    }
    return head.next;
  }

  public static void main(String[] args) {
    int[] arr = { 1, 2, 3, 4, 5 };
    Node head = convertArr2LL(arr);
    LengthOfLL(head);
    // searchElement(head, 5);
    head = removeTail(head);
    travserNode(head);
    LengthOfLL(head);

    head = removehead(head);
    travserNode(head);
    LengthOfLL(head);
  }
}
