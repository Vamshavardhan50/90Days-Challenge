import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.print.attribute.HashAttributeSet;

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

public class RemdupliDLL {
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

  public static Node convert2DLL(List<Integer> arr) {
    if (arr == null || arr.size() == 0) {
      return null;
    }
    Node head = new Node(arr.get(0));
    Node prev = head;
    for (int i = 1; i < arr.size(); i++) {
      Node newNode = new Node(arr.get(i));
      prev.next = newNode;
      newNode.prev = prev;
      prev = newNode;
    }
    return head;
  }

  public static Node RemoveDuplicates(Node head) {
    Set<Integer> set = new HashSet<>();

    Node temp = head;
    while (temp != null) {
      set.add(temp.data);
      temp = temp.next;
    }

    List<Integer> list = new ArrayList<>(set);

    return convert2DLL(list);
  }

  public static void main(String[] args) {
    int[] arr = { 1, 2, 1, 1, 4, 5, 7 };
    Node head = convert2DLL(arr);
    head = RemoveDuplicates(head);

    Node temp = head;
    while (temp != null) {
      System.out.print(temp.data + "->");
      temp = temp.next;
    }
  }

}
