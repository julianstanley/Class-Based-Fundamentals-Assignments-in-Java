
// To represent a first-in-first-out list
class Stack<T> {
  Deque<T> contents;

  Stack(Deque<T> contents) {
    this.contents = contents;
  }

  // adds an item to the head of the list
  void push(T item) {
    this.contents.addAtHead(item);
  }

  // Checks if this stack is empty
  boolean isEmpty() {
    return this.contents.size() == 0;
  }

  T pop() {
    return this.contents.removeFromHead();
  }
}

class Queue<T> {
  Deque<T> contents;

  Queue(Deque<T> contents) {
    this.contents = contents;
  }

  // adds an item to the tail of the list
  void enqueue(T item) {
    this.contents.addAtTail(item);
  }

  // Checks if this queue is empty
  boolean isEmpty() {
    return this.contents.size() == 0;
  }

  // removes and returns the head of the list
  T dequeue() {
    return this.contents.removeFromHead();
  }
}
