import tester.Tester;

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
   * Fields:
   * ...this.header...   -- Sentinel<T>
   * 
   * Methods:
   * ...this.size...                  -- int
   * ...this.addAtHead(T)...          -- void
   * ...this.addAtTail(T)...          -- void
   * ...this.removeFromHead()...      -- void
   * ...this.removeFromTail()...      -- void
   * ...this.find(IPred<T>)...        -- ANode<T>
   * ...this.removeNode(ANode<T>)...  -- void
   * 
   * Methods on Fields:
   * ...this.header.setNext(ANode<T>)...       -- void
   * ...this.header.setPrev(ANode<T>)...       -- void
   * ...this.header.removeThis()...            -- void
   * ...this.header.removeReturn()...          -- T
   * ...this.header.addAtHead(T)...            -- void
   * ...this.header.addAtTail(T)...            -- void
   * ...this.header.size()...                  -- int
   * ...this.header.find(IPred<T>)...          -- ANode<T>
   * ...this.header.removeNode(ANode<T>)       -- void
   * 
   */

  // Template: Same as class
  // Count the number of data points in this deque
  int size() {
    return this.header.next.size();
  }

  // Template: ...item...  -- T, with no additional methods
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
   * Fields:
   * ...this.next...    -- ANode<T>
   * ...this.prev...    -- ANode<T>
   * 
   * Methods:
   * ...this.setthis(ANode<T>)...    -- void
   * ...this.setPrev(ANode<T>)...    -- void
   * ...this.removeThis()...         -- void
   * ...this.removeReturn()..        -- T
   * ...this.addAtHead(T)...         -- void
   * ...this.addAtTail(T)...         -- void
   * ...this.size()...               -- int
   * ...this.find(IPred<T>)...       -- ANode<T>
   * ...this.removeNode(ANode<T>)... -- void
   * 
   * Methods on Fields:
   * ...next.setNext(ANode<T>)...    -- void
   * ...next.setPrev(ANode<T>)...    -- void
   * ...next.removeThis()...         -- void
   * ...next.removeReturn()..        -- T
   * ...next.addAtHead(T)...         -- void
   * ...next.addAtTail(T)...         -- void
   * ...next.size()...               -- int
   * ...next.find(IPred<T>)...       -- ANode<T>
   * ...next.removeNode(ANode<T>)... -- void
   * ...prev.setprev(ANode<T>)...    -- void
   * ...prev.setPrev(ANode<T>)...    -- void
   * ...prev.removeThis()...         -- void
   * ...prev.removeReturn()..        -- T
   * ...prev.addAtHead(T)...         -- void
   * ...prev.addAtTail(T)...         -- void
   * ...prev.size()...               -- int
   * ...prev.find(IPred<T>)...       -- ANode<T>
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
   * Fields:
   * ...this.next...    -- ANode<T>
   * ...this.prev...    -- ANode<T>
   * 
   * Methods:
   * ...this.setthis(ANode<T>)...    -- void
   * ...this.setPrev(ANode<T>)...    -- void
   * ...this.removeThis()...         -- void
   * ...this.removeReturn()..        -- T
   * ...this.addAtHead(T)...         -- void
   * ...this.addAtTail(T)...         -- void
   * ...this.size()...               -- int
   * ...this.find(IPred<T>)...       -- ANode<T>
   * ...this.removeNode(ANode<T>)... -- void
   * 
   * Methods on Fields:
   * ...next.setNext(ANode<T>)...    -- void
   * ...next.setPrev(ANode<T>)...    -- void
   * ...next.removeThis()...         -- void
   * ...next.removeReturn()..        -- T
   * ...next.addAtHead(T)...         -- void
   * ...next.addAtTail(T)...         -- void
   * ...next.size()...               -- int
   * ...next.find(IPred<T>)...       -- ANode<T>
   * ...next.removeNode(ANode<T>)... -- void
   * ...prev.setprev(ANode<T>)...    -- void
   * ...prev.setPrev(ANode<T>)...    -- void
   * ...prev.removeThis()...         -- void
   * ...prev.removeReturn()..        -- T
   * ...prev.addAtHead(T)...         -- void
   * ...prev.addAtTail(T)...         -- void
   * ...prev.size()...               -- int
   * ...prev.find(IPred<T>)...       -- ANode<T>
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
   * Fields:
   * ...this.next...    -- ANode<T>
   * ...this.prev...    -- ANode<T>
   * ...this.data...    -- T
   * 
   * Methods:
   * ...this.setthis(ANode<T>)...    -- void
   * ...this.setPrev(ANode<T>)...    -- void
   * ...this.removeThis()...         -- void
   * ...this.removeReturn()..        -- T
   * ...this.addAtHead(T)...         -- void
   * ...this.addAtTail(T)...         -- void
   * ...this.size()...               -- int
   * ...this.find(IPred<T>)...       -- ANode<T>
   * ...this.removeNode(ANode<T>)... -- void
   * 
   * Methods on Fields:
   * ...next.setNext(ANode<T>)...    -- void
   * ...next.setPrev(ANode<T>)...    -- void
   * ...next.removeThis()...         -- void
   * ...next.removeReturn()..        -- T
   * ...next.addAtHead(T)...         -- void
   * ...next.addAtTail(T)...         -- void
   * ...next.size()...               -- int
   * ...next.find(IPred<T>)...       -- ANode<T>
   * ...next.removeNode(ANode<T>)... -- void
   * ...prev.setprev(ANode<T>)...    -- void
   * ...prev.setPrev(ANode<T>)...    -- void
   * ...prev.removeThis()...         -- void
   * ...prev.removeReturn()..        -- T
   * ...prev.addAtHead(T)...         -- void
   * ...prev.addAtTail(T)...         -- void
   * ...prev.size()...               -- int
   * ...prev.find(IPred<T>)...       -- ANode<T>
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

class ExamplesDeque {

  // deque1 data
  Sentinel<String> empty = new Sentinel<String>(); 
  
  // deque2 data
  Sentinel<String> alphaSent = new Sentinel<String>();
  ANode<String> abc = new Node<String>("abc");
  ANode<String> cde = new Node<String>("cde");
  ANode<String> bcd = new Node<String>("bcd", cde, abc);
  ANode<String> def = new Node<String>("def", alphaSent, cde);

  // deque3 data
  Sentinel<String> palSent = new Sentinel<String>();
  ANode<String> adog = new Node<String>("dog");
  ANode<String> acanal = new Node<String>("acanal");
  ANode<String> aplan = new Node<String>("aplan", acanal, adog);
  ANode<String> pagoda = new Node<String>("pagoda", palSent, acanal);

  // deque 4 data
  Sentinel<String> addedFrontSent = new Sentinel<String>();
  ANode<String> addedFront = new Node<String>("added");
  ANode<String> abc2 = new Node<String>("abc");
  ANode<String> cde2 = new Node<String>("cde");
  ANode<String> bcd2 = new Node<String>("bcd", cde2, abc2);
  ANode<String> def2 = new Node<String>("def", addedFrontSent, cde2);

  // deque 5 data
  Sentinel<String> addedBackSent = new Sentinel<String>();
  ANode<String> addedBack = new Node<String>("added");
  ANode<String> abc3 = new Node<String>("abc");
  ANode<String> cde3 = new Node<String>("cde");
  ANode<String> bcd3 = new Node<String>("bcd", cde3, abc3);
  ANode<String> def3 = new Node<String>("def", addedBackSent, cde3);

  // Initialize dequeues
  Deque<String> deque1 = new Deque<String>(empty);
  Deque<String> deque2 = new Deque<String>(alphaSent);
  Deque<String> deque3 = new Deque<String>(palSent);
  Deque<String> deque4 = new Deque<String>(addedFrontSent);
  Deque<String> deque5 = new Deque<String>(addedBackSent);

  void init() {
    // Reset deque 1 data
    empty = new Sentinel<String>(); 
    
    // Reset deque 2 data
    alphaSent = new Sentinel<String>();
    abc = new Node<String>("abc");
    cde = new Node<String>("cde");
    bcd = new Node<String>("bcd", cde, abc);
    def = new Node<String>("def", alphaSent, cde);

    // Reset deque3 data
    palSent = new Sentinel<String>();
    adog = new Node<String>("dog");
    acanal = new Node<String>("acanal");
    aplan = new Node<String>("aplan", acanal, adog);
    pagoda = new Node<String>("pagoda", palSent, acanal);
    
    // Reset deque4 data
    addedFrontSent = new Sentinel<String>();
    addedFront = new Node<String>("added");
    abc2 = new Node<String>("abc");
    cde2 = new Node<String>("cde");
    bcd2 = new Node<String>("bcd", cde2, abc2);
    def2 = new Node<String>("def", addedFrontSent, cde2);
    
    // Reset deque 5 data
    addedBackSent = new Sentinel<String>();
    addedBack = new Node<String>("added");
    abc3 = new Node<String>("abc");
    cde3 = new Node<String>("cde");
    bcd3 = new Node<String>("bcd", cde3, abc3);
    def3 = new Node<String>("def", addedBack, cde3);

    // Re-initialize dequeues
    deque1 = new Deque<String>(empty);
    deque2 = new Deque<String>(alphaSent);
    deque3 = new Deque<String>(palSent);
    deque4 = new Deque<String>(addedFrontSent);
    deque5 = new Deque<String>(addedBackSent); 

    // Fully init deque2's sentinel
    this.alphaSent.setNext(abc);
    this.abc.setPrev(alphaSent);
    
    // Fully init deque3's sentinel
    this.palSent.setNext(adog);
    this.adog.setPrev(palSent);
    
    // Fully init deque4's sentinel
    this.addedFrontSent.setNext(addedFront);    
    this.addedFront.setPrev(addedFrontSent);
    this.addedFront.setNext(abc2);
    this.abc2.setPrev(addedFront);
    
    // Fully init deque5's sentinel
    this.addedBackSent.setNext(abc3);
    this.abc3.setPrev(addedBackSent);
    this.addedBack.setNext(addedBackSent);
    this.addedBackSent.setPrev(addedBack);
   
  }
  
  void testInit(Tester t) {
    init();
    t.checkExpect(deque2.header.next, abc);
    deque2.header.next = bcd;
    t.checkExpect(deque2.header.next, bcd);
    init();
    t.checkExpect(deque2.header.next, abc);
    
  }

  void testSetNext(Tester t) {
    init();
    t.checkExpect(deque1.header.next, empty);
    deque1.header.setNext(abc); 
    t.checkExpect(deque1.header.next, abc);
    t.checkExpect(deque2.header.next, abc);
    deque2.header.setNext(bcd); 
    t.checkExpect(deque2.header.next, bcd);
    
  }
  
  void testSetPrev(Tester t) {
    init();
    t.checkExpect(deque1.header.prev, empty);
    deque1.header.setPrev(abc); 
    t.checkExpect(deque1.header.prev, abc); 
    t.checkExpect(deque2.header.prev, def);
    deque2.header.setPrev(bcd); 
    t.checkExpect(deque2.header.prev, bcd);
  }
  
  void testRemoveThis(Tester t) {
    init();
    t.checkExpect(deque1.header.next, empty);
    empty.removeThis();
    t.checkExpect(deque1.header.next, empty);
    t.checkExpect(deque2.header.next, abc);
    abc.removeThis();
    t.checkExpect(deque2.header.next, bcd);
  }
  
  void testRemoveReturn(Tester t) {
    init();
    t.checkExpect(deque1.header.next, empty);
    t.checkException(new RuntimeException("Tried to remove an item from an empty list"), empty,
        "removeReturn");
    t.checkExpect(deque2.header.next, abc);
    t.checkExpect(abc.removeReturn(), "abc");
    t.checkExpect(deque2.header.next, bcd);
  }

  void testAddAtHead(Tester t) {
    init();
    t.checkExpect(deque1.header.next, empty);
    deque1.addAtHead("def");
    deque1.addAtHead("cde");
    deque1.addAtHead("bcd");
    deque1.addAtHead("abc");
    t.checkExpect(deque1.header.next, abc);
    t.checkExpect(deque2.header.next, abc);
    deque2.addAtHead("added");
    t.checkExpect(deque2.header.next, addedFront);
  }

  void testAddAtTail(Tester t) {
    init();
    t.checkExpect(deque1.header.next, empty);
    deque1.addAtTail("abc");
    deque1.addAtTail("bcd");
    deque1.addAtTail("cde");
    deque1.addAtTail("def");
    t.checkExpect(deque1.header.next, abc);
    t.checkExpect(deque2.header.prev, def);
    deque2.addAtTail("added");
    t.checkExpect(deque2.header.prev, addedBack);
  }

  void testSize(Tester t) {
    init();
    t.checkExpect(deque1.size(), 0);
    t.checkExpect(deque2.size(), 4);
    t.checkExpect(deque4.size(), 5);
  }
  
  void testFind(Tester t) {
    init();
    IPred<String> abcPred = new ABC();
    t.checkExpect(deque1.find(abcPred), new Sentinel<String>());
    t.checkExpect(deque2.find(abcPred), this.abc);
    t.checkExpect(deque3.find(abcPred), deque3.header);
    deque2.removeFromHead();
    t.checkExpect(deque2.find(abcPred), deque2.header);
  }

  void testRemoveNode(Tester t) {
    init();
    t.checkExpect(deque1.header.next, empty);
    deque1.removeNode(empty);
    t.checkExpect(deque1.header.next, empty);
    t.checkExpect(deque2.header.next, abc);
    deque2.removeNode(abc);
    t.checkExpect(deque2.header.next, bcd);
    deque2.removeNode(cde);
    t.checkExpect(deque2.header.next, bcd);
    deque2.removeNode(bcd);
    t.checkExpect(deque2.header.next, def); 
  }
  
  void testRemoveFromHead(Tester t) {
    init();
    t.checkExpect(deque1.header.next, empty);
    t.checkException(new RuntimeException("Tried to remove an item from an empty list"), deque1,
        "removeFromHead");
    t.checkExpect(deque2.header.next, abc);
    t.checkExpect(deque2.removeFromHead(), "abc");
    t.checkExpect(deque2.header.next, bcd);
  }

  void testRemoveFromTail(Tester t) {
    init();
    t.checkExpect(deque1.header.next, empty);
    t.checkException(new RuntimeException("Tried to remove an item from an empty list"), deque1,
        "removeFromTail");
    t.checkExpect(deque2.header.prev, def);
    t.checkExpect(deque2.size(), 4);
    t.checkExpect(deque2.removeFromTail(), "def");
    t.checkExpect(deque2.header.prev, cde);
    t.checkExpect(deque2.size(), 3);

    t.checkExpect(deque2.removeFromTail(), "cde");
    t.checkExpect(deque2.removeFromTail(), "bcd");
    t.checkExpect(deque2.removeFromTail(), "abc");
    
    t.checkException(new RuntimeException("Tried to remove an item from an empty list"), deque2,
        "removeFromTail");
  }
}
