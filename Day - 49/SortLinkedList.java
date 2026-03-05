import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Node {
  int data;
  Node next;

  Node(int data) {
    this.data = data;
    this.next = null;
  }
}

public class SortLinkedList {
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

  public static Node ConvertToLL(List<Integer> arr) {
    if (arr.size() == 0)
      return null;

    Node head = new Node(arr.get(0));
    Node current = head;

    for (int i = 1; i < arr.size(); i++) {
      current.next = new Node(arr.get(i));
      current = current.next;
    }

    return head;
  }

  public static int LengthofLL(Node head) {
    Node temp = head;
    int count = 0;

    while (temp != null) {
      count++;
      temp = temp.next;
    }
    return count;
  }

  public static Node Sort(Node head) {
    List<Integer> list = new ArrayList<>();

    Node temp = head;
    while (temp != null) {
      list.add(temp.data);
      temp = temp.next;
    }
    Collections.sort(list);

    Node newHead = ConvertToLL(list);

    return newHead;
  }

  public static void main(String[] args) {
    int[] arr = { 2, 1, 3, 5, 6, 4, 7 };
    Node head = ConvertToLL(arr);
    head = Sort(head);
    Node temp = head;
    while (temp != null) {
      System.out.print(temp.data + " -> ");
      temp = temp.next;
    }

  }
}
