import java.util.HashSet;
import java.util.Set;

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

public class StartingpointOfLL {
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
  // public static Node Detech(Node head) {
  // // HashSet to store visited nodes
  // // If we encounter a node already in the set,
  // // it means a cycle exists and we've reached the start of the cycle.
  // Set<ListNode> hashSet = new HashSet<>();

  // // Pointer to traverse the linked list
  // ListNode temp = head;

  // // Traverse the list until the end (null)
  // while (temp != null) {

  // // If current node is already visited,
  // // we found the starting node of the cycle
  // if (hashSet.contains(temp)) {
  // return temp;
  // }

  // // Otherwise, mark current node as visited
  // hashSet.add(temp);

  // // Move to the next node
  // temp = temp.next;
  // }

  // // If we reach null, no cycle exists
  // return null;
  // }

  // optimal approach

  public static Node Detech(Node head) {
    Node slow = head;
    Node fast = head;

    // Phase 1: Detect whether a cycle exists
    // Move slow by 1 step and fast by 2 steps.
    // If a cycle exists, they will eventually meet.
    while (fast != null && fast.next != null) {

      slow = slow.next; // Move 1 step
      fast = fast.next.next; // Move 2 steps

      // Collision point found → cycle detected
      if (slow == fast) {

        // Phase 2: Find the starting node of the cycle
        // Reset slow to head.
        // Keep fast at collision point.
        slow = head;

        // Move both pointers one step at a time.
        // The point where they meet again is the
        // start of the cycle.
        while (slow != fast) {
          slow = slow.next;
          fast = fast.next;
        }

        return slow; // Starting node of the cycle
      }
    }

    // No cycle exists
    return null;
  }

  public static void main(String[] args) {
    int[] arr = { 1, 2, 3, 4, 5 };
    Node head = convertToLL(arr);
    System.out.println(Detech(head));

  }
}