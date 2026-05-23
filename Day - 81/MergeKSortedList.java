import java.util.*;

class ListNode {
  int val;
  ListNode next;

  ListNode(int val) {
    this.val = val;
    this.next = null;
  }
}

public class MergeKSortedList {

  public static void print(ListNode[] lists) {
    for (ListNode head : lists) {
      ListNode curr = head;

      while (curr != null) {
        System.out.print(curr.val + " ");
        curr = curr.next;
      }

      System.out.println();
    }
  }

  public static void MergeList(ListNode[] lists) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    for (ListNode head : lists) {
      ListNode cur = head;
      while (cur != null) {
        pq.add(cur.val);
        cur = cur.next;
      }
    }
    System.out.println(pq);
    convertToLL(pq);

  }

  public static void convertToLL(PriorityQueue<Integer> pq) {

    if (pq.isEmpty()) {
      System.out.println("null");
    }

    ListNode head = new ListNode(pq.poll());
    ListNode dummy = head;

    while (!pq.isEmpty()) {
      ListNode newNode = new ListNode(pq.poll());
      dummy.next = newNode;
      dummy = dummy.next;

    }

    printLL(head);

  }

  public static void printLL(ListNode head) {

    ListNode dummy = head;

    while (dummy != null) {
      System.out.print(dummy.val + "->");
      dummy = dummy.next;
    }
  }

  public static void main(String[] args) {

    // First linked list: 1 -> 4 -> 5
    ListNode l1 = new ListNode(1);
    l1.next = new ListNode(4);
    l1.next.next = new ListNode(5);

    // second LL
    ListNode l2 = new ListNode(1);
    l2.next = new ListNode(3);
    l2.next.next = new ListNode(4);

    // third LL
    ListNode l3 = new ListNode(2);
    l3.next = new ListNode(6);

    ListNode[] lists = { l1, l2, l3 };

    print(lists);
    MergeList(lists);

  }
}