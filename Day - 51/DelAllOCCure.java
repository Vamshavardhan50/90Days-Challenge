class Node {
  int data;
  Node prev;
  Node next;

  Node(int data) {
    this.data = data;
    this.prev = null;
    this.next = null;
  }
}

public class DelAllOCCure {
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

  public static Node delete(Node head, int n) {
    Node current = head;

    while (current != null) {

      Node nextNode = current.next;

      if (current.data == n) {

        // deleting head
        if (current.prev == null) {
          head = current.next;
          if (head != null) {
            head.prev = null;
          }
        } else {
          current.prev.next = current.next;

          if (current.next != null) {
            current.next.prev = current.prev;
          }
        }
      }

      current = nextNode;
    }

    return head;
  }

  public static void main(String[] args) {
    int[] arr = { 2, 2, 10, 8, 4, 2, 5, 2 };
    int n = 2;
    Node head = convert2DLL(arr);
    head = delete(head, n);

    Node temp = head;
    while (temp != null) {
      System.out.print(temp.data + "->");
      temp = temp.next;
    }

  }
}
