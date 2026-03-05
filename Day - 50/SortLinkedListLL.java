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

public class SortLinkedListLL {
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

  public static Node Sort(Node head) {
    Node temp = head;

    Node dummy1 = new Node(-1);
    Node temp1 = dummy1;
    Node dummy2 = new Node(-1);
    Node temp2 = dummy2;
    Node dummy3 = new Node(-1);
    Node temp3 = dummy3;

    while (temp != null) {
      Node newNode = new Node(temp.data);
      if (temp.data == 0) {
        temp1.next = newNode;
        temp1 = newNode;
      } else if (temp.data == 1) {
        temp2.next = newNode;
        temp2 = newNode;
      } else {
        temp3.next = newNode;
        temp3 = newNode;
      }
      temp = temp.next;
    }

    temp1.next = (dummy2.next != null) ? dummy2.next : dummy3.next;
    temp2.next = dummy3.next;

    return dummy1.next;
  }

  public static void main(String[] args) {
    int[] arr = { 0, 2 };
    Node head = ConvertToLL(arr);
    head = Sort(head);

    Node temp = head;
    while (temp != null) {
      System.out.print(temp.data + "->");
      temp = temp.next;
    }

  }
}