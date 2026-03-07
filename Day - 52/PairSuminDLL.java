import java.util.ArrayList;
import java.util.List;

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

public class PairSuminDLL {
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

  // Brute force approach
  // public static List<List<Integer>> PairSum(Node head, int target) {
  // Node temp1 = head;
  // List<List<Integer>> res = new ArrayList<>();

  // while (temp1 != null) {
  // Node temp2 = temp1.next;

  // while (temp2 != null) {
  // if (temp1.data + temp2.data == target) {
  // List<Integer> temp = new ArrayList<>();
  // temp.add(temp1.data);
  // temp.add(temp2.data);
  // res.add(temp);
  // }
  // temp2 = temp2.next;
  // }

  // temp1 = temp1.next;
  // }

  // return res;
  // }

  // Optimal approach :-using left and right pointers

  public static List<List<Integer>> PairSum(Node head, int target) {
    Node left = head;
    Node right = head;
    List<List<Integer>> res = new ArrayList<>();

    while (right.next != null) {
      right = right.next;
    }

    while (left != null && right != null && right.prev != null && left != right) {
      int sum = left.data + right.data;

      if (sum == target) {
        List<Integer> temp = new ArrayList<>();
        temp.add(left.data);
        temp.add(right.data);
        res.add(temp);

        left = left.next;
        right = right.prev;
      } else if (sum < target) {
        left = left.next;
      } else {
        right = right.prev;
      }
    }
    return res;

  }

  public static void main(String[] args) {
    int[] arr = { 1, 2, 4, 5, 6, 8, 9 };
    int targetSum = 7;
    Node head = convert2DLL(arr);
    List<List<Integer>> ans = PairSum(head, targetSum);
    System.out.println(ans);
    // Node temp = head;
    // while (temp != null) {
    // System.out.print(temp.data + "->");
    // temp = temp.next;
    // }

  }

}
