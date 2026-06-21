import java.util.*;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class TwinSuminLL {
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

  public static int[] LLtoArray(Node head) {
    // Find length
    int size = 0;
    Node temp = head;

    while (temp != null) {
        size++;
        temp = temp.next;
    }

    // Create array
    int[] arr = new int[size];

    // Fill array
    temp = head;
    int i = 0;

    while (temp != null) {
        arr[i++] = temp.data;
        temp = temp.next;
    }

    return arr;
}

public static int pairSum(Node head) {
    int[] arr = LLtoArray(head);

    int maxSum = 0;
    int n = arr.length;

    for (int i = 0; i < n / 2; i++) { 
        maxSum = Math.max(maxSum, arr[i] + arr[n - 1 - i]);
    }

    return maxSum;
}

    public static void main(String[] args) {
        int[] arr = { 5,4,2,1 };
        Node head = ConvertToLL(arr);

        int[] res= new int[arr.length];

        System.out.println(pairSum(head));

        
    }
}
