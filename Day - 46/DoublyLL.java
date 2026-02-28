class Node {
  Node prev;
  int data;
  Node next;

  Node(int data) {
    this.prev = null;
    this.data = data;
    this.next = null;
  }

  Node(int data, Node next, Node prev) {
    this.prev = prev;
    this.data = data;
    this.next = next;
  }
}

public class DoublyLL {
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

  public static Node DeleteHead(Node head) {
    if (head == null || head.next == null) {
      return null;
    }
    Node prev = head;
    head = head.next;

    head.prev = null;
    prev.next = null;
    return head;
  }

  public static Node DeleteTail(Node head) {
    if (head == null || head.next == null) {
      return null;
    }
    Node tail = head;
    while (tail.next != null) {
      tail = tail.next;
    }
    Node newTail = tail.prev;
    newTail.next = null;
    tail.prev = null;
    return head;
  }

  public static Node removeKthElement(Node head, int k) {
    if (head == null)
      return null;
    Node temp = head;
    int count = 0;

    while (temp != null) {
      count++;
      if (count == k)
        break;
      temp = temp.next;
    }

    Node backNode = temp.prev;
    Node frontNode = temp.next;

    if (backNode == null && backNode == null) {
      return null;
    } else if (backNode == null) {
      return DeleteHead(head);
    } else if (frontNode == null) {
      return DeleteTail(head);
    }

    backNode.next = frontNode;
    frontNode.prev = backNode;

    return head;

  }

  public static void DeleteNode(Node temp) {

    if (temp == null)
      return;

    Node backNode = temp.prev;
    Node frontNode = temp.next;

    backNode.next = frontNode;

    if (frontNode != null) {
      frontNode.prev = backNode;
    }

    temp.prev = null;
    temp.next = null;
  }

  public static Node InsertHead(Node head, int val) {
    Node newNode = new Node(val);
    newNode.next = head;
    head.prev = newNode;
    head = newNode;
    return head;
  }

  public static Node InsertBeforeTail(Node head, int val) {
    if (head.next == null) {
      return InsertHead(head, val);
    }
    Node temp = head;
    while (temp.next != null) {
      temp = temp.next;
    }
    Node backNode = temp.prev;
    Node newNode = new Node(val, temp, backNode);

    backNode.next = newNode;
    temp.prev = newNode;
    return head;

  }

  public static void print(Node head) {
    Node temp = head;
    while (temp != null) {
      System.out.print(temp.data + "->");
      temp = temp.next;
    }
  }

  public static void main(String[] args) {
    int[] arr = { 1, 2, 3, 4, 5 };
    Node head = convert2DLL(arr);
    // head = InsertHead(head, 10);
    head = InsertBeforeTail(head, 20);
    print(head);

  }
}
