import java.util.HashMap;
import java.util.Map;

class Node {
  int data;
  Node next;
  Node random;

  Node() {
    this.data = 0;
    this.next = null;
    this.random = null;
  }

  Node(int x) {
    this.data = x;
    this.next = null;
    this.random = null;
  }

  Node(int x, Node nextNode, Node randomNode) {
    this.data = x;
    this.next = nextNode;
    this.random = randomNode;
  }
}

public class CopyListwithRandomPtr {
  public static Node cloneLL(Node head) {
    Map<Node, Node> map = new HashMap<>();

    Node temp = head;
    while (temp != null) {
      Node newNode = new Node(temp.data);
      map.put(temp, newNode);
      temp = temp.next;
    }

    temp = head;
    while (temp != null) {
      Node copiedNode = map.get(temp);
      copiedNode.next = map.get(temp.next);
      copiedNode.random = map.get(temp.random);

      temp = temp.next;
    }
    return map.get(head);
  }

  public static void main(String[] args) {
    Node head = new Node(7);
    head.next = new Node(14);
    head.next.next = new Node(21);
    head.next.next.next = new Node(28);

    // Assigning random pointers
    head.random = head.next.next;
    head.next.random = head;
    head.next.next.random = head.next.next.next;
    head.next.next.next.random = head.next;

    Node clonedList = cloneLL(head);
    Node temp = clonedList;
    while (temp != null) {
      System.out.print(temp.data + " ");
      temp = temp.next;
    }

  }

}