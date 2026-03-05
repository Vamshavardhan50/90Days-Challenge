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
    int count++;

    while (temp!=null) {
      count++;
      temp = temp.next;
    }
    return count;

  }

  public static Node CollosionPoint(Node head1, Node head2) {
    int l1 = LenghtOfLL(head1);
    int l2 = LenghtOfLL(head2);
    int difference = (l1 > l2) ? l1 - l2 : l2 - l1;

    
    
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
