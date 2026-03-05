class Node {
  int data;
  Node next;

  Node(int data) {
    this.data = data;
    this.next = null;
  }
}

public class AddTwoNumbers {
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

  public static Node AddNumbers(Node l1, Node l2) {
    Node temp1 = l1;
    Node temp2 = l2;
    Node dummy = new Node(-1);
    Node temp = dummy;
    int carry = 0;
    while (temp1 != null || temp2 != null) {
      int x = (temp1 != null) ? temp1.val : 0;
      int y = (temp2 != null) ? temp2.val : 0;
      int data = x + y + carry;

      
      if (data > 9) {
        int add = data % 10;
        Node newNode = new Node(add);
        temp.next = newNode;
        temp = newNode;
        carry = 1;
      } else {
        Node newNode = new Node(data);
        temp.next = newNode;
        temp = newNode;
        carry = 0;
      }
      if (carry > 0) {
        temp.next = new Node(carry);
      }

      if (temp1 != null)
        temp1 = temp1.next;
      if (temp2 != null)
        temp2 = temp2.next;
    }
    return dummy.next;
  }

  public static void main(String[] args) {
    int[] arr1 = { 2, 3, 4 };
    int[] arr2 = { 5, 6, 4 };

    Node l1 = ConvertToLL(arr1);
    Node l2 = ConvertToLL(arr2);

    Node result = AddNumbers(l1, l2);
    Node temp = result;

    while (temp != null) {
      System.out.print(temp.data + " -> ");
      temp = temp.next;
    }

  }

}
