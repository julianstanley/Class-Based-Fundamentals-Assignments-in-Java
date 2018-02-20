import tester.Tester;

// To represent any function that compares to objects
interface IComparator<T> {
  // Returns negative if t1 comes before t2. Zero if same
  int compare(T t1, T t2);
}

// To represent a function that compares books by title
class BooksByTitle implements IComparator<Book> {
  // Returns negative if book1's title comes alphabetically before book2's. Zero
  // if same title
  public int compare(Book book1, Book book2) {
    return book1.title.compareTo(book2.title);
  }
}

// To represent a function that compares books by author
class BooksByAuthor implements IComparator<Book> {
  // Returns negative if book1's author comes alphabetically before book2's.
  // Zero if same author
  public int compare(Book book1, Book book2) {
    return book1.author.compareTo(book2.author);
  }
}

// To represent a function that compares books by price
class BooksByPrice implements IComparator<Book> {
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

  // Insert an item into this binary search tree
  abstract ABST<T> insert(T item);

  // Find the leftmost item in this binary search tree
  abstract T getLeftmost();

  // Hold on to the left item in this tree until you find the left most item
  abstract T holdLeftMost(T soFar);

  // Return all but the leftmost item in this binary search tree
  abstract ABST<T> getRight();

  // Is this tree the same as that tree?
  abstract boolean sameTree(ABST<T> that);

  // Is this tree the same as that leaf?
  abstract boolean sameLeaf(Leaf<T> that);

  // Is this tree the same as that node?
  abstract boolean sameNode(Node<T> that);

  // Is the data in this tree the same as the data in that tree?
  abstract boolean sameData(ABST<T> that);

  // Is the data in this tree the same as the data in that leaf?
  abstract boolean sameDataLeaf(Leaf<T> that);

  // Is the data in this tree the same as the data in that node?
  abstract boolean sameDataNode(Node<T> that);

  // Add the items in this tree to the given list
  abstract IList<T> buildList(IList<T> inital);

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

  // Insert an item onto this leaf
  ABST<T> insert(T item) {
    return new Node<T>(item, new Leaf<T>(this.order), new Leaf<T>(this.order), this.order);
  }

  // Find the leftmost item in this leaf
  public T getLeftmost() {
    throw new RuntimeException("No leftmost item of an empty tree");
  }

  // Hold on to the left item in this leaf until you find the left most item
  public T holdLeftMost(T soFar) {
    return soFar;
  }

  // Return all but the leftmost item in this leaf
  public ABST<T> getRight() {
    throw new RuntimeException("No right of an empty tree");
  }

  // Is this leaf the same as that tree?
  public boolean sameTree(ABST<T> that) {
    return that.sameLeaf(this);
  }

  // Is this leaf the same as that leaf?
  public boolean sameLeaf(Leaf<T> that) {
    return true;
  }

  // Is this leaf the same as that node?
  public boolean sameNode(Node<T> that) {
    return false;
  }

  // Is the data in this leaf the same as the data in that tree?
  public boolean sameData(ABST<T> that) {
    return that.sameDataLeaf(this);
  }

  // Is the data in this leaf the same as the data in that leaf?
  public boolean sameDataLeaf(Leaf<T> that) {
    return true;
  }

  // Is the data in this leaf the same as the data in that node?
  public boolean sameDataNode(Node<T> that) {
    return false;
  }

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

  // Insert an item onto this node
  ABST<T> insert(T item) {
    if (order.compare(this.data, item) < 0) {
      return new Node<T>(this.data, this.left.insert(item), this.right, this.order); 
    }

    else {
      return new Node<T>(this.data, this.left, this.right.insert(item), this.order); 
    }
  }

  // Find the leftmost item in this node
  public T getLeftmost() {
    return this.left.holdLeftMost(this.data);
  }

  // Hold on to the left item in this node until you find the left most item
  public T holdLeftMost(T soFar) {
    return this.left.holdLeftMost(this.data);
  }

  // Return all but the leftmost item in this node
  public ABST<T> getRight() {
    if (this.order.compare(this.data, this.getLeftmost()) == 0) {
      return this.right;
    }
    else {
      return new Node<T>(this.data, this.left.getRight(), this.right, this.order);
    }
  }

  // Is this node the same as that tree?
  public boolean sameTree(ABST<T> that) {
    return that.sameNode(this);
  }

  // Is this node the same as that leaf?
  public boolean sameLeaf(Leaf<T> that) {
    return false;
  }

  // Is this node the same as that node?
  public boolean sameNode(Node<T> that) {
    return (this.order.compare(this.data, that.data) == 0) && this.left.sameTree(that.left)
        && this.right.sameTree(that.right);
  }

  // Is the data in this node the same as the data in that tree?
  public boolean sameData(ABST<T> that) {
    return that.sameDataNode(this);
  }

  // Is the data in this node the same as the data in that leaf?
  public boolean sameDataLeaf(Leaf<T> that) {
    return false;
  }

  // Is the data in this node the same as the data in that node?
  public boolean sameDataNode(Node<T> that) {
    return (this.order.compare(this.getLeftmost(), that.getLeftmost()) == 0)
        && this.getRight().sameData(that.getRight());
  }

  // Add the items in this tree to the given list
  public IList<T> buildList(IList<T> inital) {
    return this.left.buildList(new MtList<T>()).insertList(inital.insert(this.data))
        .insertList(this.right.buildList(new MtList<T>()));
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
}

// To represent an generic list
interface IList<T> {
  // Insert all items in this list into the given tree
  ABST<T> buildTree(ABST<T> inital);

  // Insert the given item into the end of this list
  IList<T> insert(T that);

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

  // Insert all items in this non-empty list into the given tree
  public ABST<T> buildTree(ABST<T> inital) {
    return this.rest.buildTree(inital.insert(this.first));
  }

  // Insert the given item into the end of this non-empty list
  public IList<T> insert(T that) {
    return this.rest.insert(that);
  }

  // Insert the given list into the end of this non-empty list
  public IList<T> insertList(IList<T> that) {
    return new ConsList<T>(this.first, this.rest.insertList(that));
  }
}

// To represent an empty generic list
class MtList<T> implements IList<T> {
  // Insert all items in this empty list into the given tree
  public ABST<T> buildTree(ABST<T> inital) {
    return inital;
  }

  // Insert the given item into the end of this empty list
  public IList<T> insert(T that) {
    return new ConsList<T>(that, this);
  }

  // Insert the given list into the end of this empty list
  public IList<T> insertList(IList<T> that) {
    return that;
  }
}

class ExamplesABST {
  Book nineteen = new Book("Nineteen Eighty-Four", "Orwell", 8);
  Book mock = new Book("To Kill a Mockingbird", "Lee", 3);
  Book catcher = new Book("The Catcher in the Rye", "Salinger", 10);
  Book emma = new Book("Emma", "Austen", 7);

  IComparator<Book> title = new BooksByTitle();
  IComparator<Book> author = new BooksByAuthor();
  IComparator<Book> price = new BooksByPrice();

  ABST<Book> leafTitle = new Leaf<Book>(title);

  ABST<Book> booksTitleSimple1 = new Node<Book>(emma, leafTitle,
      new Node<Book>(nineteen, leafTitle, leafTitle, title), title);

  ABST<Book> booksTitleSimple2 = new Node<Book>(nineteen,
      new Node<Book>(emma, leafTitle, leafTitle, title), leafTitle, title);

  ABST<Book> booksTitle = new Node<Book>(emma, leafTitle, new Node<Book>(nineteen, leafTitle,
      new Node<Book>(catcher, leafTitle, new Node<Book>(mock, leafTitle, leafTitle, title), title),
      title), title);

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

  public boolean testSameness(Tester t) {
    return t.checkExpect(booksTitle.sameTree(booksTitle), true)
        && t.checkExpect(booksTitle.sameTree(booksAuthor), false)
        && t.checkExpect(booksTitle.insert(mock).sameTree(booksTitle), false)
        && t.checkExpect(booksTitle.sameTree(booksTitle2), false)
        && t.checkExpect(leafTitle.sameData(leafTitle), true)
        && t.checkExpect(booksTitleSimple1.sameTree(booksTitleSimple2), false)
        && t.checkExpect(booksTitleSimple1.sameData(booksTitleSimple2), true)
        && t.checkExpect(booksTitle.sameData(booksTitle2), true);
  }
}
