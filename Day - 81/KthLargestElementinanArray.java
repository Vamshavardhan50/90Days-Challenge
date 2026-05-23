import java.util.PriorityQueue;

public class KthLargestElementinanArray {
  public static void main(String[] args) {

    int[] arr = { 3, 2, 1, 5, 6, 4 };
    int k = 2;

    PriorityQueue<Integer> heap = new PriorityQueue<>();

    for (int num : arr) {

      if (heap.size() < k) {
        heap.add(num);
      } else if (num > heap.peek()) {
        heap.poll();
        heap.add(num);
      }
    }

    System.out.println(heap); // [5, 6]
    System.out.println(heap.peek()); // 5
  }
}