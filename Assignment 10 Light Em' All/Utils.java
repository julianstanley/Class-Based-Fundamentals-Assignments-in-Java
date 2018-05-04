import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

// To store any general-use utility methods
class Utils {

  // Template: ...source... -- ArrayList<T>, where T is comparable
  // Sorts a given ArrayList using heapsort
  <T extends Comparable<T>> ArrayList<T> heapsort(ArrayList<T> source) {
    ArrayList<T> heap = this.buildHeap(source);
    ArrayList<T> answer = new ArrayList<T>();
    for (int i = 0; i < source.size(); i += 1) {
      T max = this.removeMax(heap);
      answer.add(max);
    }
    return (answer);
  }

  // Template: ...source... -- ArrayList<T>, where T is comparable
  // EFFECT: Removes the maximum item from the given source
  // Removes and returns the maximum item from the given heap-satisfying
  // ArrayList
  <T extends Comparable<T>> T removeMax(ArrayList<T> source) {
    this.swap(source, 0, source.size() - 1);
    T answer = source.remove(source.size() - 1);
    if (source.size() > 1) {
      this.downheap(source, 0);
    }
    return answer;

  }

  // Returns a new heapified version of the given arraylist
  <T extends Comparable<T>> ArrayList<T> buildHeap(ArrayList<T> source) {
    ArrayList<T> answer = new ArrayList<T>(source);
    for (int i = 1; i < source.size(); i += 1) {
      this.upheap(answer, i);
    }
    return answer;
  }

  // EFFECT: Up-heaps to re-heapify the given arraylist
  // Up-heaps the given list at the given index
  <T extends Comparable<T>> ArrayList<T> upheap(ArrayList<T> source, int index) {

    int parentIndex = Math.floorDiv(index - 1, 2);
    if (parentIndex > 0) {
      if (source.get(index).compareTo(source.get(parentIndex)) >= 0) {
        this.swap(source, index, parentIndex);
        this.upheap(source, parentIndex);
      }
    }
    return (source);
  }

  // EFFECT: Down-heaps to re-heapify the given arraylist
  // Down-heaps the list at the given index
  <T extends Comparable<T>> ArrayList<T> downheap(ArrayList<T> source, int index) {
    int leftIdx = Math.min(2 * index + 1, source.size() - 1);
    int rightIdx = Math.min(2 * index + 2, source.size() - 1);
    T leftItem = source.get(leftIdx);
    T rightItem = source.get(rightIdx);
    T curItem = source.get(index);

    if (curItem.compareTo(leftItem) < 0 || curItem.compareTo(rightItem) < 0) {

      if (leftItem.compareTo(rightItem) < 0) {
        int biggestIdx = rightIdx;
        this.swap(source, index, biggestIdx);
      }
      else {
        int biggestIdx = leftIdx;
        this.swap(source, index, biggestIdx);
      }

    }
    return (source);
  }

  // EFFECT: Changes the order of two items in the given arraylist
  // Swaps the items at the two given indices within the given list
  <T> void swap(ArrayList<T> source, int idx1, int idx2) {
    T temp1 = source.get(idx1);
    T temp2 = source.get(idx2);

    source.set(idx2, temp1);
    source.set(idx1, temp2);
  }

  <T> ArrayList<T> reverse(ArrayList<T> source) {
    // This will work on a stack
    Stack<T> fin = new Stack<T>(new Deque<T>(new Sentinel<T>()));

    for (T item : source) {
      fin.push(item);
    }

    source = new ArrayList<T>();

    while (!fin.isEmpty()) {
      source.add(fin.pop());
    }

    return source;
  }

  // Find the representative of the given item in the given union-find-style
  // hashmap
  <T> T representative(HashMap<T, T> source, T key) {
    if (source.get(key) != key) {
      return this.representative(source, source.get(key));
    }

    else {
      return key;
    }

  }
  
  // Give that color the transparency given
  Color brighter(Color that, int amount) {
    return new Color(that.getRed() + amount , that.getGreen(), that.getBlue()); 
  }
}
