//To represent a predicate function object
interface IPred<T> {

  // Template has ...t... -- T
  // Apply this predicate to the given object
  boolean apply(T t);
}

// To represent a function object that checks if this string has "abc"
class ABC implements IPred<String> {

  // Template has ...s... -- String
  // Does this string contain "abc"?
  public boolean apply(String s) {
    return s.contains("abc");
  }
}

// To represent a double-ended queue
class Deque<T> {
  Sentinel<T> header;

  // Main constructor
  Deque() {
    this.header = new Sentinel<T>();
  }

  // Convenience, takes a specific header sentinel
  Deque(Sentinel<T> header) {
    this.header = header;
  }

  /*
   * Fields: ...this.header... -- Sentinel<T>
   * 
   * Methods: ...this.size... -- int ...this.addAtHead(T)... -- void
   * ...this.addAtTail(T)... -- void ...this.removeFromHead()... -- void
   * ...this.removeFromTail()... -- void ...this.find(IPred<T>)... -- ANode<T>
   * ...this.removeNode(ANode<T>)... -- void
   * 
   * Methods on Fields: ...this.header.setNext(ANode<T>)... -- void
   * ...this.header.setPrev(ANode<T>)... -- void ...this.header.removeThis()...
   * -- void ...this.header.removeReturn()... -- T
   * ...this.header.addAtHead(T)... -- void ...this.header.addAtTail(T)... --
   * void ...this.header.size()... -- int ...this.header.find(IPred<T>)... --
   * ANode<T> ...this.header.removeNode(ANode<T>) -- void
   * 
   */

  // Template: Same as class
  // Count the number of data points in this deque
  int size() {
    return this.header.next.size();
  }

  // Template: ...item... -- T, with no additional methods
  // Add the given item onto the head of this deque
  // EFFECT: This deque now has the given item at its head
  void addAtHead(T item) {
    this.header.addAtHead(item);
  }

  // Template: ...item... -- T, with no additional methods
  // Add the given item onto the tail of this deque
  // EFFECT: This deque now has the given item at its head
  void addAtTail(T item) {
    this.header.addAtTail(item);
  }

  // Template: Same as class
  // EFFECT: This deque has its first item removed
  // Removes the first item from this list and then returns that item
  T removeFromHead() {
    return this.header.next.removeReturn();
  }

  // Template: Same as class
  // EFFECT: This deque has its last item removed
  // Removes the last item from this list and then returns that item
  T removeFromTail() {
    return this.header.prev.removeReturn();
  }

  // Template: ...pred... -- IPred<T>, with pred.apply(T)... -- boolean
  // Returns the first node in this deque that satisfies the given predicate
  ANode<T> find(IPred<T> pred) {
    return this.header.next.find(pred);
  }

  // Template: ...that... -- ANode<T>, with all (9) of ANode's methods
  // EFFECT: This deque might lose one node or stay the same
  // Remove the given node from this deque
  void removeNode(ANode<T> that) {
    that.removeThis();
  }
}

// To represent a node in a queue
abstract class ANode<T> {
  ANode<T> next;
  ANode<T> prev;

  /*
   * Fields: ...this.next... -- ANode<T> ...this.prev... -- ANode<T>
   * 
   * Methods: ...this.setthis(ANode<T>)... -- void ...this.setPrev(ANode<T>)...
   * -- void ...this.removeThis()... -- void ...this.removeReturn().. -- T
   * ...this.addAtHead(T)... -- void ...this.addAtTail(T)... -- void
   * ...this.size()... -- int ...this.find(IPred<T>)... -- ANode<T>
   * ...this.removeNode(ANode<T>)... -- void
   * 
   * Methods on Fields: ...next.setNext(ANode<T>)... -- void
   * ...next.setPrev(ANode<T>)... -- void ...next.removeThis()... -- void
   * ...next.removeReturn().. -- T ...next.addAtHead(T)... -- void
   * ...next.addAtTail(T)... -- void ...next.size()... -- int
   * ...next.find(IPred<T>)... -- ANode<T> ...next.removeNode(ANode<T>)... --
   * void ...prev.setprev(ANode<T>)... -- void ...prev.setPrev(ANode<T>)... --
   * void ...prev.removeThis()... -- void ...prev.removeReturn().. -- T
   * ...prev.addAtHead(T)... -- void ...prev.addAtTail(T)... -- void
   * ...prev.size()... -- int ...prev.find(IPred<T>)... -- ANode<T>
   * ...prev.removeNode(ANode<T>)... -- void
   */

  // Template: ...that... -- ANode<T> with all (9) of ANode's methods
  // EFFECT: Changes this node's next node to the one given
  // Set this node's next to the given node
  void setNext(ANode<T> that) {
    this.next = that;
  }

  // Template: ...that... - ANode<T> with all (9) of ANode's methods
  // EFFECT: Changes this node's previous node to the one given
  // Set this node's previous to the given node
  void setPrev(ANode<T> that) {
    this.prev = that;
  }

  // Template: Same as class
  // EFFECT: Sets this node to reference itself
  // EFFECT: Sets this node's next and prev to reference eachother
  // Removes this node
  void removeThis() {
    this.next.setPrev(this.prev);
    this.prev.setNext(this.next);
    this.setPrev(this);
    this.setNext(this);
  }

  // Template: Same as class
  // EFFECT: Sets this node to reference itself
  // EFFECT: Sets this node's next and prev to reference eachother
  // Removes this node and attempts to return its data
  abstract T removeReturn();

  // Template: ...that... -- T, no additional methods
  // EFFECT: This node now has the given item at its front
  // Add the given item at the head of this node
  void addAtHead(T that) {
    this.setNext(new Node<T>(that, this.next, this));
  }

  // Template: ...that... -- T, no additional methods
  // EFFECT: This node now has the given item at its end
  // Add the given item at the tail of this node
  void addAtTail(T that) {
    this.setPrev(new Node<T>(that, this, this.prev));
  }

  // Template: Same as class
  // Counts the number of non-sentinel nodes connected to this node
  abstract int size();

  // Template: ...pred... -- IPred<T>, with pred.apply(T)--Boolean
  // Returns the first node linked to this node that satisfies the given
  // predicate
  abstract ANode<T> find(IPred<T> pred);

  // Template: ...that... -- ANode<T>, with all (9) of ANode's methods
  // Removes the given node from this node
  abstract void removeNode(ANode<T> that);
}

// To represent a sentinel node that sits in the front and back of a
// double-ended queue
class Sentinel<T> extends ANode<T> {
  Sentinel() {
    this.next = this;
    this.prev = this;
  }

  /*
   * Fields: ...this.next... -- ANode<T> ...this.prev... -- ANode<T>
   * 
   * Methods: ...this.setthis(ANode<T>)... -- void ...this.setPrev(ANode<T>)...
   * -- void ...this.removeThis()... -- void ...this.removeReturn().. -- T
   * ...this.addAtHead(T)... -- void ...this.addAtTail(T)... -- void
   * ...this.size()... -- int ...this.find(IPred<T>)... -- ANode<T>
   * ...this.removeNode(ANode<T>)... -- void
   * 
   * Methods on Fields: ...next.setNext(ANode<T>)... -- void
   * ...next.setPrev(ANode<T>)... -- void ...next.removeThis()... -- void
   * ...next.removeReturn().. -- T ...next.addAtHead(T)... -- void
   * ...next.addAtTail(T)... -- void ...next.size()... -- int
   * ...next.find(IPred<T>)... -- ANode<T> ...next.removeNode(ANode<T>)... --
   * void ...prev.setprev(ANode<T>)... -- void ...prev.setPrev(ANode<T>)... --
   * void ...prev.removeThis()... -- void ...prev.removeReturn().. -- T
   * ...prev.addAtHead(T)... -- void ...prev.addAtTail(T)... -- void
   * ...prev.size()... -- int ...prev.find(IPred<T>)... -- ANode<T>
   * ...prev.removeNode(ANode<T>)... -- void
   */

  // setNext is inherited from ANode
  // setPrev is inherited from ANode
  // removeThis is inherited from ANode

  // Template: Same as class
  // Removes this sentinel and attempts to return it
  T removeReturn() {
    throw new RuntimeException("Tried to remove an item from an empty list");
  }

  // addAtHead is inherited from ANode
  // addAtTail is inherited from ANode

  // Template: Same as class
  // Find the size of this sentinel
  int size() {
    return 0;
  }

  // Template: ...pred... -- IPred<T> has pred.apply(T)... -- boolean
  // Returns the first node linked to this sentinel that satisfies the given
  // predicate
  ANode<T> find(IPred<T> pred) {
    return this;
  }

  // Template: ...that... -- ANode<T>, which has all (9) ANode methods
  // EFFECT: This sentinel could remain the same or lose one node
  // Removes the given node from this sentinel
  void removeNode(ANode<T> that) {
    if (this.size() > 0) {
      this.next.removeNode(that);
    }
  }
}

// To represent a data-containing node in a queue
class Node<T> extends ANode<T> {
  T data;

  /*
   * Fields: ...this.next... -- ANode<T> ...this.prev... -- ANode<T>
   * ...this.data... -- T
   * 
   * Methods: ...this.setthis(ANode<T>)... -- void ...this.setPrev(ANode<T>)...
   * -- void ...this.removeThis()... -- void ...this.removeReturn().. -- T
   * ...this.addAtHead(T)... -- void ...this.addAtTail(T)... -- void
   * ...this.size()... -- int ...this.find(IPred<T>)... -- ANode<T>
   * ...this.removeNode(ANode<T>)... -- void
   * 
   * Methods on Fields: ...next.setNext(ANode<T>)... -- void
   * ...next.setPrev(ANode<T>)... -- void ...next.removeThis()... -- void
   * ...next.removeReturn().. -- T ...next.addAtHead(T)... -- void
   * ...next.addAtTail(T)... -- void ...next.size()... -- int
   * ...next.find(IPred<T>)... -- ANode<T> ...next.removeNode(ANode<T>)... --
   * void ...prev.setprev(ANode<T>)... -- void ...prev.setPrev(ANode<T>)... --
   * void ...prev.removeThis()... -- void ...prev.removeReturn().. -- T
   * ...prev.addAtHead(T)... -- void ...prev.addAtTail(T)... -- void
   * ...prev.size()... -- int ...prev.find(IPred<T>)... -- ANode<T>
   * ...prev.removeNode(ANode<T>)... -- void
   */

  // Main constructor
  Node(T data) {
    this.data = data;
    this.next = null;
    this.prev = null;
  }

  // Convenience constructor
  Node(T data, ANode<T> next, ANode<T> prev) {
    this.data = data;

    if ((next == null) || (prev == null)) {
      throw new IllegalArgumentException("Trying to construct a node from null nodes");
    }
    else {
      // Assign previous and update
      this.prev = prev;
      prev.setNext(this);

      // Assign next and update
      this.next = next;
      next.setPrev(this);

    }
  }

  // Template: Same as class
  // Find the size of this non-sentinel node
  int size() {
    return 1 + this.next.size();
  }

  // Template: Same as class
  // EFFECT: Sets this node to reference itself
  // EFFECT: Sets this node's next and prev to reference eachother
  // Remove this node and return its data
  T removeReturn() {
    this.removeThis();
    return this.data;
  }

  // Template: ...pred... -- IPred<T>, pred.apply(T)... -- boolean
  // Returns the first node linked to this node that satisfies the given
  // predicate
  ANode<T> find(IPred<T> pred) {
    if (pred.apply(this.data)) {
      return this;
    }
    else {
      return this.next.find(pred);
    }
  }

  // Template: ...that... -- ANode<T>, including all (9) ANode methods
  // EFFECT: This node may stay the same or lose one node
  // Removes the given node from this non-empty node
  void removeNode(ANode<T> that) {
    if (this.equals(that)) {
      this.prev.setNext(this.next);
      this.next.setPrev(this.prev);
    }

    else {
      this.next.removeNode(that);
    }
  }
}
