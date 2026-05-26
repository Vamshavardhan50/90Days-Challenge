import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ListNode {
  int val;
  ListNode next;

  ListNode(int val) {
    this.val = val;
    this.next = null;
  }
}

public class MergeSortedList {

  public static ListNode ListToLL(List<Integer> arr) {
    if (arr.isEmpty())
      return null;

    ListNode head = new ListNode(arr.get(0));
    ListNode current = head;

    for (int i = 1; i < arr.size(); i++) {
      current.next = new ListNode(arr.get(i));
      current = current.next;
    }

    return head;
  }

  public static ListNode MergeList(ListNode head1, ListNode head2) {

    ListNode dummy = new ListNode(-1);
    ListNode tail = dummy;

    while (head1 != null && head2 != null) {

      if (head1.val <= head2.val) {
        tail.next = head1;
        head1 = head1.next;
      } else {
        tail.next = head2;
        head2 = head2.next;
      }

      tail = tail.next;
    }

    // Attach remaining nodes
    if (head1 != null)
      tail.next = head1;
    if (head2 != null)
      tail.next = head2;

    return dummy.next;
  }

  public static void PrintLL(ListNode head) {
    while (head != null) {
      System.out.print(head.val + " ");
      head = head.next;
    }
    System.out.println();
  }

  public static void main(String[] args) {

    List<Integer> arr1 = new ArrayList<>(Arrays.asList(1, 2, 4));
    List<Integer> arr2 = new ArrayList<>(Arrays.asList(1, 3, 4));

    ListNode linked1 = ListToLL(arr1);
    ListNode linked2 = ListToLL(arr2);

    ListNode merged = MergeList(linked1, linked2);

    PrintLL(merged);
  }
}