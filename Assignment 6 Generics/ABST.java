import tester.Tester;

// To represent any function that compares to objects
interface IComparator<T> {
  // Template: same as class + 
  // Parameters:
  /*
   * ...t1...    -- T
   * ...t2...    -- T
   */
  // Returns negative if t1 comes before t2. Zero if same
  int compare(T t1, T t2);
}

// To represent a function that compares books by title
class BooksByTitle implements IComparator<Book> {
  /*
   * Fields:
   * Methods:
   * ...this.compare(Book, Book)...   -- int
   * 
   * Methods on Fields:
   * 
   */
  
  /* Template:
   * Same as class +
   * Parameters: 
   * ...book1...   -- Book
   * ...book2...   -- Book
   * 
   * Methods on Parameters:
   * 
   */
  // Returns negative if book1's title comes alphabetically before book2's. Zero
  // if same title
  public int compare(Book book1, Book book2) {
    return book1.title.compareTo(book2.title);
  }
}

// To represent a function that compares books by author
class BooksByAuthor implements IComparator<Book> {
  /*
   * Fields:
   * Methods:
   * ...this.compare(Book, Book)...   -- int
   * 
   * Methods on Fields:
   * 
   */
  
  /* Template:
   * Same as class +
   * Parameters: 
   * ...book1...   -- Book
   * ...book2...   -- Book
   * 
   * Methods on Parameters:
   * 
   */
  // Returns negative if book1's author comes alphabetically before book2's.
  // Zero if same author
  public int compare(Book book1, Book book2) {
    return book1.author.compareTo(book2.author);
  }
}

// To represent a function that compares books by price
class BooksByPrice implements IComparator<Book> {
  /*
   * Fields:
   * Methods:
   * ...this.compare(Book, Book)...   -- int
   * 
   * Methods on Fields:
   * 
   */
  
  /* Template:
   * Same as class +
   * Parameters: 
   * ...book1...   -- Book
   * ...book2...   -- Book
   * 
   * Methods on Parameters:
   * 
   */
  // Returns negative if book1's price is lower than book2's. Zero if same price
  public int compare(Book book1, Book book2) {
    return book1.price - book2.price;
  }
}

// To represent a binary search tree
abstract class ABST<T> {
  IComparator<T> order;

  ABST(IComparator<T> order) {
    this.order = order;
  }
  
  /*
   * Fields:
   * ...this.order...   -- IComparator<T>
   * 
   * Methods:
   * ...this.insert(T)...   -- ABST<T>
   * ...this.getLeftMost()...  -- T
   * ...this.holdLeftMost(T)... -- T
   * ...this.sameTree(ABST<T>)... -- boolean
   * ...this.sameLeaf(Leaf<T>)... -- boolean
   * ...this.sameNode(Node<T>)... -- boolean
   * ...this.sameData(ABST<T>)...  -- boolean
   * ...this.sameDataLeaf(Leaf<T>)... -- boolean
   * ...this.sameDataNode(Node<T>)... -- boolean
   * ...this.buildList(IList<T>)...   -- IList<T>
   * 
   * Methods on Fields:
   * ...this.order.compare(Book, Book)... -- int
   */

  // Template: Same as class +
  // Parameters: ...item... -- T
  // Methods on parameters:
  // Insert an item into this binary search tree
  abstract ABST<T> insert(T item);

  // Template: Same as class
  // Find the leftmost item in this binary search tree
  abstract T getLeftmost();

  // Template: Same as class +
  // Parameters: ...soFar... T
  // Hold on to the left item in this tree until you find the left most item
  abstract T holdLeftMost(T soFar);

  // Template: Same as class
  // Return all but the leftmost item in this binary search tree
  abstract ABST<T> getRight();

  // Template: Same as class + 
  // Parameters: ...that... ABST<T>
  // Is this tree the same as that tree?
  abstract boolean sameTree(ABST<T> that);

  // Template: Same as class + 
  // Parameters: ...that... -- Leaf<T>
  // Methods on parameters:
  /*
  * ...that.insert(T)...   -- ABST<T>
  * ...that.getLeftMost()...  -- T
  * ...that.holdLeftMost(T)... -- T
  * ...that.sameTree(ABST<T>)... -- boolean
  * ...that.sameLeaf(Leaf<T>)... -- boolean
  * ...that.sameNode(Node<T>)... -- boolean
  * ...that.sameData(ABST<T>)...  -- boolean
  * ...that.sameDataLeaf(Leaf<T>)... -- boolean
  * ...that.sameDataNode(Node<T>)... -- boolean
  * ...that.buildList(IList<T>)...   -- IList<T>
  */
  // Is this tree the same as that leaf?
  abstract boolean sameLeaf(Leaf<T> that);

  // Template: Same as class + 
  // Parameters: ...that... -- Node<T>
  // Methods on parameters:
  /*
  * ...that.insert(T)...   -- ABST<T>
  * ...that.getLeftMost()...  -- T
  * ...that.holdLeftMost(T)... -- T
  * ...that.sameTree(ABST<T>)... -- boolean
  * ...that.sameLeaf(Leaf<T>)... -- boolean
  * ...that.sameNode(Node<T>)... -- boolean
  * ...that.sameData(ABST<T>)...  -- boolean
  * ...that.sameDataLeaf(Leaf<T>)... -- boolean
  * ...that.sameDataNode(Node<T>)... -- boolean
  * ...that.buildList(IList<T>)...   -- IList<T>
  */
  // Is this tree the same as that node?
  abstract boolean sameNode(Node<T> that);

  // Template: Same as class + 
  // Parameters: ...that... -- ABST<T>
  // Methods on parameters:
  /*
  * ...that.insert(T)...   -- ABST<T>
  * ...that.getLeftMost()...  -- T
  * ...that.holdLeftMost(T)... -- T
  * ...that.sameTree(ABST<T>)... -- boolean
  * ...that.sameLeaf(Leaf<T>)... -- boolean
  * ...that.sameNode(Node<T>)... -- boolean
  * ...that.sameData(ABST<T>)...  -- boolean
  * ...that.sameDataLeaf(Leaf<T>)... -- boolean
  * ...that.sameDataNode(Node<T>)... -- boolean
  * ...that.buildList(IList<T>)...   -- IList<T>
  */
  // Is the data in this tree the same as the data in that tree?
  abstract boolean sameData(ABST<T> that);

  
  // Template: Same as class + 
  // Parameters: ...that... -- Leaf<T>
  // Methods on parameters:
  /*
  * ...that.insert(T)...   -- ABST<T>
  * ...that.getLeftMost()...  -- T
  * ...that.holdLeftMost(T)... -- T
  * ...that.sameTree(ABST<T>)... -- boolean
  * ...that.sameLeaf(Leaf<T>)... -- boolean
  * ...that.sameNode(Node<T>)... -- boolean
  * ...that.sameData(ABST<T>)...  -- boolean
  * ...that.sameDataLeaf(Leaf<T>)... -- boolean
  * ...that.sameDataNode(Node<T>)... -- boolean
  * ...that.buildList(IList<T>)...   -- IList<T>
  */
  // Is the data in this tree the same as the data in that leaf?
  abstract boolean sameDataLeaf(Leaf<T> that);

  // Template: Same as class + 
  // Parameters: ...that... -- Node<T>
  // Methods on parameters:
  /*
  * ...that.insert(T)...   -- ABST<T>
  * ...that.getLeftMost()...  -- T
  * ...that.holdLeftMost(T)... -- T
  * ...that.sameTree(ABST<T>)... -- boolean
  * ...that.sameLeaf(Leaf<T>)... -- boolean
  * ...that.sameNode(Node<T>)... -- boolean
  * ...that.sameData(ABST<T>)...  -- boolean
  * ...that.sameDataLeaf(Leaf<T>)... -- boolean
  * ...that.sameDataNode(Node<T>)... -- boolean
  * ...that.buildList(IList<T>)...   -- IList<T>
  */
  // Is the data in this tree the same as the data in that node?
  abstract boolean sameDataNode(Node<T> that);

  // Template: Same as class + 
  // Parameters: ... initial... -- IList<T>
  // Methods on parameters:
  // ...this.buildTree(ABST<T>)...   -- ABST<T>
  // ...this.insertList(IList<T>)... -- IList<T>
  
  // Add the items in this tree to the given list
  abstract IList<T> buildList(IList<T> inital);

  // Template: Same as class + 
  // Parameters: ... initial... -- IList<T>
  // Methods on parameters:
  // ...this.buildTree(ABST<T>)...   -- ABST<T>
  // ...this.insertList(IList<T>)... -- IList<T>
  // Is this tree the same as the given list?
  boolean sameAsList(IList<T> that) {
    return that.buildTree(new Leaf<T>(this.order)).sameData(this);
  }
}

// To represent a leaf of a binary search tree
class Leaf<T> extends ABST<T> {
  Leaf(IComparator<T> order) {
    super(order);
  }
  
  /*
   * Fields:
   * ...this.order...   -- IComparator<T>
   * 
   * Methods:
   * ...this.insert(T)...   -- ABST<T>
   * ...this.getLeftMost()...  -- T
   * ...this.holdLeftMost(T)... -- T
   * ...this.sameTree(ABST<T>)... -- boolean
   * ...this.sameLeaf(Leaf<T>)... -- boolean
   * ...this.sameNode(Node<T>)... -- boolean
   * ...this.sameData(ABST<T>)...  -- boolean
   * ...this.sameDataLeaf(Leaf<T>)... -- boolean
   * ...this.sameDataNode(Node<T>)... -- boolean
   * ...this.buildList(IList<T>)...   -- IList<T>
   * 
   * Methods on Fields:
   * ...this.order.compare(Book, Book)... -- int
   */

  // Template: same as class + 
  // Parameters: ...item... -- T
  // Insert an item onto this leaf
  ABST<T> insert(T item) {
    return new Node<T>(item, new Leaf<T>(this.order), new Leaf<T>(this.order), this.order);
  }

  // Template: same as class
  // Find the leftmost item in this leaf
  public T getLeftmost() {
    throw new RuntimeException("No leftmost item of an empty tree");
  }

  // Template: same as class + 
  // Parameters: ...soFar... -- T
  // Insert an item onto this leaf
  // Hold on to the left item in this leaf until you find the left most item
  public T holdLeftMost(T soFar) {
    return soFar;
  }

  // Template: same as class
  // Return all but the leftmost item in this leaf
  public ABST<T> getRight() {
    throw new RuntimeException("No right of an empty tree");
  }

  // Template: Same as class + 
  // Parameters: ...that... -- ABST<T>
  // Methods on parameters:
  /*
  * ...that.insert(T)...   -- ABST<T>
  * ...that.getLeftMost()...  -- T
  * ...that.holdLeftMost(T)... -- T
  * ...that.sameTree(ABST<T>)... -- boolean
  * ...that.sameLeaf(Leaf<T>)... -- boolean
  * ...that.sameNode(Node<T>)... -- boolean
  * ...that.sameData(ABST<T>)...  -- boolean
  * ...that.sameDataLeaf(Leaf<T>)... -- boolean
  * ...that.sameDataNode(Node<T>)... -- boolean
  * ...that.buildList(IList<T>)...   -- IList<T>
  */
  // Is this leaf the same as that tree?
  public boolean sameTree(ABST<T> that) {
    return that.sameLeaf(this);
  }

  // Template: Same as class + 
  // Parameters: ...that... -- Leaf<T>
  // Methods on parameters:
  /*
  * ...that.insert(T)...   -- ABST<T>
  * ...that.getLeftMost()...  -- T
  * ...that.holdLeftMost(T)... -- T
  * ...that.sameTree(ABST<T>)... -- boolean
  * ...that.sameLeaf(Leaf<T>)... -- boolean
  * ...that.sameNode(Node<T>)... -- boolean
  * ...that.sameData(ABST<T>)...  -- boolean
  * ...that.sameDataLeaf(Leaf<T>)... -- boolean
  * ...that.sameDataNode(Node<T>)... -- boolean
  * ...that.buildList(IList<T>)...   -- IList<T>
  */
  // Is this leaf the same as that leaf?
  public boolean sameLeaf(Leaf<T> that) {
    return true;
  }

  // Template: Same as class + 
  // Parameters: ...that... -- Node<T>
  // Methods on parameters:
  /*
  * ...that.insert(T)...   -- ABST<T>
  * ...that.getLeftMost()...  -- T
  * ...that.holdLeftMost(T)... -- T
  * ...that.sameTree(ABST<T>)... -- boolean
  * ...that.sameLeaf(Leaf<T>)... -- boolean
  * ...that.sameNode(Node<T>)... -- boolean
  * ...that.sameData(ABST<T>)...  -- boolean
  * ...that.sameDataLeaf(Leaf<T>)... -- boolean
  * ...that.sameDataNode(Node<T>)... -- boolean
  * ...that.buildList(IList<T>)...   -- IList<T>
  */
  // Is this leaf the same as that node?
  public boolean sameNode(Node<T> that) {
    return false;
  }

  // Template: Same as class + 
  // Parameters: ...that... -- ABST<T>
  // Methods on parameters:
  /*
  * ...that.insert(T)...   -- ABST<T>
  * ...that.getLeftMost()...  -- T
  * ...that.holdLeftMost(T)... -- T
  * ...that.sameTree(ABST<T>)... -- boolean
  * ...that.sameLeaf(Leaf<T>)... -- boolean
  * ...that.sameNode(Node<T>)... -- boolean
  * ...that.sameData(ABST<T>)...  -- boolean
  * ...that.sameDataLeaf(Leaf<T>)... -- boolean
  * ...that.sameDataNode(Node<T>)... -- boolean
  * ...that.buildList(IList<T>)...   -- IList<T>
  */
  // Is the data in this leaf the same as the data in that tree?
  public boolean sameData(ABST<T> that) {
    return that.sameDataLeaf(this);
  }

  // Template: Same as class + 
  // Parameters: ...that... -- Leaf<T>
  // Methods on parameters:
  /*
  * ...that.insert(T)...   -- ABST<T>
  * ...that.getLeftMost()...  -- T
  * ...that.holdLeftMost(T)... -- T
  * ...that.sameTree(ABST<T>)... -- boolean
  * ...that.sameLeaf(Leaf<T>)... -- boolean
  * ...that.sameNode(Node<T>)... -- boolean
  * ...that.sameData(ABST<T>)...  -- boolean
  * ...that.sameDataLeaf(Leaf<T>)... -- boolean
  * ...that.sameDataNode(Node<T>)... -- boolean
  * ...that.buildList(IList<T>)...   -- IList<T>
  */
  // Is the data in this leaf the same as the data in that leaf?
  public boolean sameDataLeaf(Leaf<T> that) {
    return true;
  }

  // Template: Same as class + 
  // Parameters: ...that... -- Node<T>
  // Methods on parameters:
  /*
  * ...that.insert(T)...   -- ABST<T>
  * ...that.getLeftMost()...  -- T
  * ...that.holdLeftMost(T)... -- T
  * ...that.sameTree(ABST<T>)... -- boolean
  * ...that.sameLeaf(Leaf<T>)... -- boolean
  * ...that.sameNode(Node<T>)... -- boolean
  * ...that.sameData(ABST<T>)...  -- boolean
  * ...that.sameDataLeaf(Leaf<T>)... -- boolean
  * ...that.sameDataNode(Node<T>)... -- boolean
  * ...that.buildList(IList<T>)...   -- IList<T>
  */
  // Is the data in this leaf the same as the data in that node?
  public boolean sameDataNode(Node<T> that) {
    return false;
  }

  // Template: Same as class + 
  // Parameters: ... initial... -- IList<T>
  // Methods on parameters:
  // ...this.buildTree(ABST<T>)...   -- ABST<T>
  // ...this.insertList(IList<T>)... -- IList<T>
  // Add the items in this leaf to the given list
  public IList<T> buildList(IList<T> inital) {
    return inital;
  }

}

// To represent a node of a binary search tree
class Node<T> extends ABST<T> {
  T data;
  ABST<T> left;
  ABST<T> right;

  Node(T data, ABST<T> left, ABST<T> right, IComparator<T> order) {
    super(order);
    this.data = data;
    this.left = left;
    this.right = right;
  }

  // Convenience constructor to make the checker happy without having to jumble
  // code
  Node(IComparator<T> order, T data, ABST<T> left, ABST<T> right) {
    super(order);
    this.data = data;
    this.left = left;
    this.right = right;
  }
  
  /*
   * Fields:
   * ...this.order...   -- IComparator<T>
   * ...this.left...    -- ABST<T>
   * ...this.right...   -- ABST<T>
   * 
   * Methods:
   * ...this.insert(T)...   -- ABST<T>
   * ...this.getLeftMost()...  -- T
   * ...this.holdLeftMost(T)... -- T
   * ...this.sameTree(ABST<T>)... -- boolean
   * ...this.sameLeaf(Leaf<T>)... -- boolean
   * ...this.sameNode(Node<T>)... -- boolean
   * ...this.sameData(ABST<T>)...  -- boolean
   * ...this.sameDataLeaf(Leaf<T>)... -- boolean
   * ...this.sameDataNode(Node<T>)... -- boolean
   * ...this.buildList(IList<T>)...   -- IList<T>
   * 
   * Methods on Fields:
   * ...this.order.compare(Book, Book)... -- int
   * ...this.left.insert(T)...   -- ABST<T>
   * ...this.left.getLeftMost()...  -- T
   * ...this.left.holdLeftMost(T)... -- T
   * ...this.left.sameTree(ABST<T>)... -- boolean
   * ...this.left.sameLeaf(Leaf<T>)... -- boolean
   * ...this.left.sameNode(Node<T>)... -- boolean
   * ...this.left.sameData(ABST<T>)...  -- boolean
   * ...this.left.sameDataLeaf(Leaf<T>)... -- boolean
   * ...this.left.sameDataNode(Node<T>)... -- boolean
   * ...this.left.buildList(IList<T>)...   -- IList<T>
   * ...this.right.insert(T)...   -- ABST<T>
   * ...this.right.getLeftMost()...  -- T
   * ...this.right.holdLeftMost(T)... -- T
   * ...this.right.sameTree(ABST<T>)... -- boolean
   * ...this.right.sameLeaf(Leaf<T>)... -- boolean
   * ...this.right.sameNode(Node<T>)... -- boolean
   * ...this.right.sameData(ABST<T>)...  -- boolean
   * ...this.right.sameDataLeaf(Leaf<T>)... -- boolean
   * ...this.right.sameDataNode(Node<T>)... -- boolean
   * ...this.right.buildList(IList<T>)...   -- IList<T>
   */

  // Template: Same as class +
  // Parameters: ...item... -- T
  // Insert an item onto this node
  ABST<T> insert(T item) {
    if (order.compare(this.data, item) > 0) {
      return new Node<T>(this.data, this.left.insert(item), this.right, this.order);
    }

    else {
      return new Node<T>(this.data, this.left, this.right.insert(item), this.order);
    }
  }

  // Template same as class
  // Find the leftmost item in this node
  public T getLeftmost() {
    return this.left.holdLeftMost(this.data);
  }

  // Template: Same as class + 
  // Parameters: ...soFar... -- T
  // Hold on to the left item in this node until you find the left most item
  public T holdLeftMost(T soFar) {
    return this.left.holdLeftMost(this.data);
  }

  // Template same as class
  // Return all but the leftmost item in this node
  public ABST<T> getRight() {
    if (this.order.compare(this.data, this.getLeftmost()) == 0) {
      return this.right;
    }
    else {
      return new Node<T>(this.data, this.left.getRight(), this.right, this.order);
    }
  }

  // Template: Same as class + 
  // Parameters: ...that... -- ABST<T>
  // Methods on parameters:
  /*
  * ...that.insert(T)...   -- ABST<T>
  * ...that.getLeftMost()...  -- T
  * ...that.holdLeftMost(T)... -- T
  * ...that.sameTree(ABST<T>)... -- boolean
  * ...that.sameLeaf(Leaf<T>)... -- boolean
  * ...that.sameNode(Node<T>)... -- boolean
  * ...that.sameData(ABST<T>)...  -- boolean
  * ...that.sameDataLeaf(Leaf<T>)... -- boolean
  * ...that.sameDataNode(Node<T>)... -- boolean
  * ...that.buildList(IList<T>)...   -- IList<T>
  */
  // Is this node the same as that tree?
  public boolean sameTree(ABST<T> that) {
    return that.sameNode(this);
  }

  // Template: Same as class + 
  // Parameters: ...that... -- Leaf<T>
  // Methods on parameters:
  /*
  * ...that.insert(T)...   -- ABST<T>
  * ...that.getLeftMost()...  -- T
  * ...that.holdLeftMost(T)... -- T
  * ...that.sameTree(ABST<T>)... -- boolean
  * ...that.sameLeaf(Leaf<T>)... -- boolean
  * ...that.sameNode(Node<T>)... -- boolean
  * ...that.sameData(ABST<T>)...  -- boolean
  * ...that.sameDataLeaf(Leaf<T>)... -- boolean
  * ...that.sameDataNode(Node<T>)... -- boolean
  * ...that.buildList(IList<T>)...   -- IList<T>
  */
  // Is this node the same as that leaf?
  public boolean sameLeaf(Leaf<T> that) {
    return false;
  }

  // Template: Same as class + 
  // Parameters: ...that... -- Node<T>
  // Methods on parameters:
  /*
  * ...that.insert(T)...   -- ABST<T>
  * ...that.getLeftMost()...  -- T
  * ...that.holdLeftMost(T)... -- T
  * ...that.sameTree(ABST<T>)... -- boolean
  * ...that.sameLeaf(Leaf<T>)... -- boolean
  * ...that.sameNode(Node<T>)... -- boolean
  * ...that.sameData(ABST<T>)...  -- boolean
  * ...that.sameDataLeaf(Leaf<T>)... -- boolean
  * ...that.sameDataNode(Node<T>)... -- boolean
  * ...that.buildList(IList<T>)...   -- IList<T>
  */
  // Is this node the same as that node?
  public boolean sameNode(Node<T> that) {
    return (this.order.compare(this.data, that.data) == 0) && this.left.sameTree(that.left)
        && this.right.sameTree(that.right);
  }

  // Template: Same as class + 
  // Parameters: ...that... -- ABST<T>
  // Methods on parameters:
  /*
  * ...that.insert(T)...   -- ABST<T>
  * ...that.getLeftMost()...  -- T
  * ...that.holdLeftMost(T)... -- T
  * ...that.sameTree(ABST<T>)... -- boolean
  * ...that.sameLeaf(Leaf<T>)... -- boolean
  * ...that.sameNode(Node<T>)... -- boolean
  * ...that.sameData(ABST<T>)...  -- boolean
  * ...that.sameDataLeaf(Leaf<T>)... -- boolean
  * ...that.sameDataNode(Node<T>)... -- boolean
  * ...that.buildList(IList<T>)...   -- IList<T>
  */
  // Is the data in this node the same as the data in that tree?
  public boolean sameData(ABST<T> that) {
    return that.sameDataNode(this);
  }

  // Template: Same as class + 
  // Parameters: ...that... -- Leaf<T>
  // Methods on parameters:
  /*
  * ...that.insert(T)...   -- ABST<T>
  * ...that.getLeftMost()...  -- T
  * ...that.holdLeftMost(T)... -- T
  * ...that.sameTree(ABST<T>)... -- boolean
  * ...that.sameLeaf(Leaf<T>)... -- boolean
  * ...that.sameNode(Node<T>)... -- boolean
  * ...that.sameData(ABST<T>)...  -- boolean
  * ...that.sameDataLeaf(Leaf<T>)... -- boolean
  * ...that.sameDataNode(Node<T>)... -- boolean
  * ...that.buildList(IList<T>)...   -- IList<T>
  */
  // Is the data in this node the same as the data in that leaf?
  public boolean sameDataLeaf(Leaf<T> that) {
    return false;
  }

  // Template: Same as class + 
  // Parameters: ...that... -- Node<T>
  // Methods on parameters:
  /*
  * ...that.insert(T)...   -- ABST<T>
  * ...that.getLeftMost()...  -- T
  * ...that.holdLeftMost(T)... -- T
  * ...that.sameTree(ABST<T>)... -- boolean
  * ...that.sameLeaf(Leaf<T>)... -- boolean
  * ...that.sameNode(Node<T>)... -- boolean
  * ...that.sameData(ABST<T>)...  -- boolean
  * ...that.sameDataLeaf(Leaf<T>)... -- boolean
  * ...that.sameDataNode(Node<T>)... -- boolean
  * ...that.buildList(IList<T>)...   -- IList<T>
  */
  // Is the data in this node the same as the data in that node?
  public boolean sameDataNode(Node<T> that) {
    return (this.order.compare(this.getLeftmost(), that.getLeftmost()) == 0)
        && this.getRight().sameData(that.getRight());
  }

  // Template: Same as class + 
  // Parameters: ... initial... -- IList<T>
  // Methods on parameters:
  // ...this.buildTree(ABST<T>)...   -- ABST<T>
  // ...this.insertList(IList<T>)... -- IList<T>
  // Add the items in this tree to the given list
  public IList<T> buildList(IList<T> inital) {
    return this.right.buildList(new MtList<T>()).insertList(new ConsList<T>(this.data, inital))
        .insertList(this.left.buildList(new MtList<T>()));
  }

}

// To represent a book
class Book {
  String title;
  String author;
  int price;

  Book(String title, String author, int price) {
    this.title = title;
    this.author = author;
    this.price = price;
  }
  
  /*
   * Fields:
   * ...this.title...    -- String
   * ...this.author...   -- String
   * ...this.price...    -- int
   * 
   * Methods:
   * 
   * Methods on Fields:
   * 
   */
}

// To represent an generic list
interface IList<T> {
  
  // Template: Same as class + 
  // Parameters: ...that... -- ABST<T>
  // Methods on parameters:
  /*
  * ...that.insert(T)...   -- ABST<T>
  * ...that.getLeftMost()...  -- T
  * ...that.holdLeftMost(T)... -- T
  * ...that.sameTree(ABST<T>)... -- boolean
  * ...that.sameLeaf(Leaf<T>)... -- boolean
  * ...that.sameNode(Node<T>)... -- boolean
  * ...that.sameData(ABST<T>)...  -- boolean
  * ...that.sameDataLeaf(Leaf<T>)... -- boolean
  * ...that.sameDataNode(Node<T>)... -- boolean
  * ...that.buildList(IList<T>)...   -- IList<T>
  */
  // Insert all items in this list into the given tree
  ABST<T> buildTree(ABST<T> inital);

  // Template: Same as class + 
  // Parameters: ... initial... -- IList<T>
  // Methods on parameters:
  // ...this.buildTree(ABST<T>)...   -- ABST<T>
  // ...this.insertList(IList<T>)... -- IList<T>
  // Insert the given list into the end of this list
  IList<T> insertList(IList<T> that);
}

// To represent a non-empty generic list
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  /*
   * Fields:
   * ...this.first...   -- T
   * ...this.rest...    -- IList<T>
   * 
   * Methods:
   * ...this.buildTree(ABST<T>)...   -- ABST<T>
   * ...this.insertList(IList<T>)... -- IList<T>
   * 
   * Methods on Fields:
   * ...this.rest.buildTree(ABST<T>)... -- ABST<T>
   * ...this.rest.insertList(IList<T>)... -- IList<T>
   * 
   */
  
  // Template: Same as class + 
  // Parameters: ...that... -- ABST<T>
  // Methods on parameters:
  /*
  * ...that.insert(T)...   -- ABST<T>
  * ...that.getLeftMost()...  -- T
  * ...that.holdLeftMost(T)... -- T
  * ...that.sameTree(ABST<T>)... -- boolean
  * ...that.sameLeaf(Leaf<T>)... -- boolean
  * ...that.sameNode(Node<T>)... -- boolean
  * ...that.sameData(ABST<T>)...  -- boolean
  * ...that.sameDataLeaf(Leaf<T>)... -- boolean
  * ...that.sameDataNode(Node<T>)... -- boolean
  * ...that.buildList(IList<T>)...   -- IList<T>
  */
  // Insert all items in this non-empty list into the given tree
  public ABST<T> buildTree(ABST<T> inital) {
    return this.rest.buildTree(inital.insert(this.first));
  }

  // Template: Same as class + 
  // Parameters: ... initial... -- IList<T>
  // Methods on parameters:
  // ...this.buildTree(ABST<T>)...   -- ABST<T>
  // ...this.insertList(IList<T>)... -- IList<T>
  // Insert the given list into the end of this non-empty list
  public IList<T> insertList(IList<T> that) {
    return new ConsList<T>(this.first, this.rest.insertList(that));
  }
}

// To represent an empty generic list
class MtList<T> implements IList<T> {
  
  // Template: Same as class + 
  // Parameters: ...that... -- ABST<T>
  // Methods on parameters:
  /*
  * ...that.insert(T)...   -- ABST<T>
  * ...that.getLeftMost()...  -- T
  * ...that.holdLeftMost(T)... -- T
  * ...that.sameTree(ABST<T>)... -- boolean
  * ...that.sameLeaf(Leaf<T>)... -- boolean
  * ...that.sameNode(Node<T>)... -- boolean
  * ...that.sameData(ABST<T>)...  -- boolean
  * ...that.sameDataLeaf(Leaf<T>)... -- boolean
  * ...that.sameDataNode(Node<T>)... -- boolean
  * ...that.buildList(IList<T>)...   -- IList<T>
  */
  // Insert all items in this empty list into the given tree
  public ABST<T> buildTree(ABST<T> inital) {
    return inital;
  }

  // Template: Same as class + 
  // Parameters: ... initial... -- IList<T>
  // Methods on parameters:
  // ...this.buildTree(ABST<T>)...   -- ABST<T>
  // ...this.insertList(IList<T>)... -- IList<T>
  // Insert the given list into the end of this empty list
  public IList<T> insertList(IList<T> that) {
    return that;
  }
}

class ExamplesABST {

  // Books
  Book nineteen = new Book("Nineteen Eighty-Four", "Orwell", 8);
  Book mock = new Book("To Kill a Mockingbird", "Lee", 3);
  Book catcher = new Book("Catcher in the Rye", "Salinger", 10);
  Book emma = new Book("Emma", "Austen", 7);

  // Comparators
  IComparator<Book> title = new BooksByTitle();
  IComparator<Book> author = new BooksByAuthor();
  IComparator<Book> price = new BooksByPrice();

  // Leaves
  ABST<Book> leafTitle = new Leaf<Book>(title);
  ABST<Book> leafAuthor = new Leaf<Book>(author);
  ABST<Book> leafPrice = new Leaf<Book>(price);

  // Nodes. Each example builds on the last
  ABST<Book> nineteenTitle = new Node<Book>(nineteen, leafTitle, leafTitle, title);
  ABST<Book> booksTitleSimple2 = new Node<Book>(nineteen,
      new Node<Book>(emma, leafTitle, leafTitle, title), leafTitle, title);
  ABST<Book> booksTitle3 = new Node<Book>(nineteen,
      new Node<Book>(emma, new Node<Book>(catcher, leafTitle, leafTitle, title), leafTitle, title),
      leafTitle, title);
  ABST<Book> booksTitle4 = new Node<Book>(nineteen,
      new Node<Book>(emma, new Node<Book>(catcher, leafTitle, leafTitle, title), leafTitle, title),
      new Node<Book>(mock, leafTitle, leafTitle, title), title);

  ABST<Book> booksTitle = new Node<Book>(emma, leafTitle, new Node<Book>(nineteen, leafTitle,
      new Node<Book>(catcher, leafTitle, new Node<Book>(mock, leafTitle, leafTitle, title), title),
      title), title);

  ABST<Book> booksTitleSimple1 = new Node<Book>(emma, leafTitle,
      new Node<Book>(nineteen, leafTitle, leafTitle, title), title);

  ABST<Book> booksTitle2 = new Node<Book>(nineteen,
      new Node<Book>(emma, new Leaf<Book>(title), new Leaf<Book>(title), title),
      new Node<Book>(catcher, new Leaf<Book>(title),
          new Node<Book>(mock, new Leaf<Book>(title), new Leaf<Book>(title), title), title),
      title);

  ABST<Book> booksAuthor = new Node<Book>(emma, new Leaf<Book>(author),
      new Node<Book>(nineteen, new Leaf<Book>(author), new Node<Book>(mock, new Leaf<Book>(author),
          new Node<Book>(catcher, new Leaf<Book>(author), new Leaf<Book>(author), author), author),
          author),
      author);

  ABST<Book> booksPrice = new Node<Book>(mock, new Leaf<Book>(price),
      new Node<Book>(emma, new Leaf<Book>(price),
          new Node<Book>(nineteen, new Leaf<Book>(price),
              new Node<Book>(catcher, new Leaf<Book>(price), new Leaf<Book>(price), price), price),
          price),
      price);
  
  // Lists
  IList<Book> mt = new MtList<Book>();
  IList<Book> list1 = new ConsList<Book>(nineteen, mt);
  IList<Book> list2 = new ConsList<Book>(nineteen, new ConsList<Book>(emma, mt));

  // Testing IComparator.compare()
  public boolean testCompare(Tester t) {
    return t.checkExpect(title.compare(nineteen, mock), -6)
        && t.checkExpect(title.compare(mock, nineteen), 6)
        && t.checkExpect(title.compare(nineteen, nineteen), 0)

        && t.checkExpect(author.compare(nineteen, mock), 3)
        && t.checkExpect(author.compare(mock, nineteen), -3)
        && t.checkExpect(author.compare(nineteen, nineteen), 0)

        && t.checkExpect(price.compare(nineteen, mock), 5)
        && t.checkExpect(price.compare(mock, nineteen), -5)
        && t.checkExpect(price.compare(nineteen, nineteen), 0);
  }

  // Testing ABST.insert(Book)
  public boolean testInsert(Tester t) {
    return t.checkExpect(leafTitle.insert(nineteen), nineteenTitle)
        && t.checkExpect(nineteenTitle.insert(emma), booksTitleSimple2)
        && t.checkExpect(booksTitleSimple2.insert(catcher), booksTitle3)
        && t.checkExpect(booksTitle3.insert(mock), booksTitle4)
        && t.checkExpect(booksTitle.insert(mock).sameTree(booksTitle), false);
  }

  // Testing ABST.getLeftmost()
  public boolean testLeft(Tester t) {
    return t.checkException(new RuntimeException("No leftmost item of an empty tree"), leafTitle,
        "getLeftmost") && t.checkExpect(nineteenTitle.getLeftmost(), nineteen)
        && t.checkExpect(booksTitleSimple2.getLeftmost(), emma)
        && t.checkExpect(booksTitle3.getLeftmost(), catcher)
        && t.checkExpect(booksTitle4.getLeftmost(), catcher);
  }

  // Testing ABST.holdLeftMost(T soFar)
  public boolean testHold(Tester t) {
    return t.checkExpect(leafTitle.holdLeftMost(emma), emma)
        && t.checkExpect(nineteenTitle.holdLeftMost(catcher), nineteen)
        && t.checkExpect(booksTitle4.holdLeftMost(emma), catcher);
  }

  // Testing ABST.getRight()
  public boolean testRight(Tester t) {
    return t.checkException(new RuntimeException("No right of an empty tree"), leafTitle,
        "getRight") && t.checkExpect(nineteenTitle.getRight(), leafTitle)
        && t.checkExpect(booksTitleSimple2.getRight(), nineteenTitle);
  }

  // Testing ABST.sameTree(ABST)
  public boolean sameTree(Tester t) {
    return t.checkExpect(leafTitle.sameTree(leafTitle), true)
        // The below test is a little weird. They look the same, but they have
        // different
        // comparators. If I were to make this a little better, I would create a
        // sameComparator
        // method to make two leaves with different comparators return false.
        && t.checkExpect(leafTitle.sameTree(leafPrice), true)
        && t.checkExpect(leafTitle.sameTree(nineteenTitle), false)
        && t.checkExpect(nineteenTitle.sameTree(nineteenTitle), true)
        && t.checkExpect(nineteenTitle.sameTree(booksTitle4), false)
        && t.checkExpect(booksTitle.sameTree(booksTitle), true)
        && t.checkExpect(booksTitle.sameTree(booksAuthor), false)
        && t.checkExpect(booksTitleSimple1.sameTree(booksTitleSimple2), false)
        && t.checkExpect(booksTitle.sameTree(booksTitle2), false);
  }

  // Testing ABST.sameData(ABST)
  public boolean testSameData(Tester t) {
    return t.checkExpect(leafTitle.sameData(leafTitle), true)
        && t.checkExpect(leafTitle.sameData(nineteenTitle), false)
        && t.checkExpect(booksTitleSimple1.sameData(booksTitleSimple2), true)
        && t.checkExpect(booksTitle.sameData(booksTitle2), true)
        && t.checkExpect(booksTitle.sameData(booksTitle4), false);
  }

  // SameNode and SameLeaf, SameDataNode/Leaf are implicitly checked through
  // sameTree

  // Testing ABST.buildList
  public boolean testBuildList(Tester t) {
    return t.checkExpect(leafTitle.buildList(mt), mt)
        && t.checkExpect(nineteenTitle.buildList(mt), list1)
        && t.checkExpect(booksTitleSimple2.buildList(mt), list2)
        && t.checkExpect(leafTitle.buildList(list1), list1);
  }

  // Testing ABST.buildTree
  public boolean testBuildTree(Tester t) {
    return t.checkExpect(mt.buildTree(leafTitle), leafTitle)
        && t.checkExpect(list1.buildTree(leafTitle), nineteenTitle)
        && t.checkExpect(list2.buildTree(leafTitle), booksTitleSimple2)
        && t.checkExpect(leafTitle.buildList(mt).buildTree(leafTitle), leafTitle)
        && t.checkExpect(booksTitleSimple2.buildList(mt).buildTree(leafTitle), booksTitleSimple2);
  }
}
  

