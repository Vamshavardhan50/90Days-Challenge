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

public class InterSectionInLL {
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

  public static int LenghtOfLL(Node head) {
    Node temp = head;
    int count = 0;

    while (temp != null) {
      count++;
      temp = temp.next;
    }
    return count;

  }

  public static Node CollosionPoint(Node head1, Node head2) {
    int l1 = LenghtOfLL(head1);
    int l2 = LenghtOfLL(head2);
    System.out.println("L1" + l1);
    System.out.println("L2" + l2);
    int difference = (l1 > l2) ? l1 - l2 : l2 - l1;
    System.out.println(difference);

    Node temp1 = head1;
    Node temp2 = head2;

    if (l1 > l2) {
      for (int i = 0; i < difference; i++) {
        temp1 = temp1.next;
      }
    } else {
      for (int i = 0; i < difference; i++) {
        temp2 = temp2.next;
      }
    }

    while (temp1 != null && temp2 != null) {
      if (temp1 == temp2) {
        return temp1;
      }
      temp1 = temp1.next;
      temp2 = temp2.next;
    }
    return null;

  }

  public static void main(String[] args) {
    int[] arr1 = { 1, 3, 1, 2, 4 };
    int[] arr2 = { 3, 2, 4 };
    Node head1 = ConvertToLL(arr1);
    Node head2 = ConvertToLL(arr2);

    Node head = CollosionPoint(head1, head2);

    Node temp = head;
    while (temp != null) {
      System.out.print(temp.data + "->");
      temp = temp.next;
    }
  }

}
