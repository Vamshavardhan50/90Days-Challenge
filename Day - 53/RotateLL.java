import java.nio.channels.Pipe.SourceChannel;

import javax.sound.sampled.SourceDataLine;

class Node {
  int data;
  Node next;

  Node(int data) {
    this.data = data;
    this.next = null;
  }
}

public class RotateLL {
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

  public static int LengthOfLL(Node head) {
    Node temp = head;
    int count = 0;
    while (temp != null) {
      count++;
      temp = temp.next;
    }
    return count;
  }

  public static Node rotate(Node head, int k) {
    int n = LengthOfLL(head);
    Node temp = head;
    int count = 0;

    for (int i = 0; i < k; i++) {
      temp = temp.next;
    }

    Node newHead = temp.next;
    temp.next = null;

    Node tail = newHead;
    while (tail.next != null) {
      tail = tail.next;
    }
    tail.next = head;

    return newHead;
  }

  public static void main(String[] args) {
    int[] arr = { 1, 2, 3, 4, 5 };
    int k = 2;
    Node head = ConvertToLL(arr);
    head = rotate(head, k);
    Node temp = head;
    while (temp != null) {
      System.out.println(temp.data);
      temp = temp.next;
    }

  }
}