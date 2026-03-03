import java.util.List;

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

public class LinkedListisPalindrome {
  public static Node convertToLL(int[] arr) {
    Node head = new Node(arr[0]);
    Node mover = head;

    for (int i = 1; i < arr.length; i++) {
      Node temp = new Node(arr[i]);
      mover.next = temp;
      mover = temp;
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

  // Brute force approach
  // public static boolean ISpalindromic(Node head) {
  // StringBuilder sb = new StringBuilder();

  // Node temp = head;
  // while (temp != null) {
  // sb.append(String.valueOf(temp.data));
  // temp = temp.next;
  // }

  // String original = sb.toString();
  // String reversed = new StringBuilder(original).reverse().toString();

  // return original.equals(reversed);
  // }

  // optimal approach
  public static boolean ISpalindromic(Node head) {
    Node slow = head;
    Node fast = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    // after the upper while completes the slow will be pointing to the middle of
    // the LL

    Node secondHalf = reverse(slow);
    Node firstHalf = head;

    while (secondHalf != null) {
      if (firstHalf.data != secondHalf.data) {
        return false;
      }
      firstHalf = firstHalf.next;
      secondHalf = secondHalf.next;
    }
    return true;
  }

  public static Node reverse(Node head) {
    Node prev = null;
    while (head != null) {
      Node nextNode = head.next;
      head.next = prev;
      prev = head;
      head = nextNode;
    }
    return prev;
  }

  public static void main(String[] args) {
    int[] arr = { 3, 7, 5, 7, 3 };
    Node head = convertToLL(arr);
    System.out.println(ISpalindromic(head));
  }
}