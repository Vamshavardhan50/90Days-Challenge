import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;

public class MinHeap {
  public static void main(String[] args) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    minHeap.add(4);
    minHeap.add(3);
    minHeap.add(2);
    minHeap.add(1);
    System.out.println("MinHeap : " + minHeap);
    System.out.println("root element: " + minHeap.peek());

    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    maxHeap.add(1);
    maxHeap.add(2);
    maxHeap.add(3);
    maxHeap.add(4);

    System.out.println("MaxHeap : " + maxHeap);
    System.out.println("root element: " + maxHeap.peek());

  }
}