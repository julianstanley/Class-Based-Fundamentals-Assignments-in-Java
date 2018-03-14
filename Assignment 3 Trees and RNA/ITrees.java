import tester.*; 
import javalib.worldimages.*;
import javalib.funworld.*; 
import javalib.worldcanvas.WorldCanvas;
import java.awt.Color;

interface ITree {
  
  // Draws this tree
  WorldImage draw();
  
  // is this tree drooping
  boolean isDrooping();
  
  // combines this tree with the other tree
  ITree combine(int leftLength, int rightLength, double leftTheta, double rightTheta,
      ITree otherTree);
  
  // Changes the length and rotation of this ITree
  ITree changeTheta(double theta); 
 
  // get the width of this tree
  double getWidth();
  
  // get the width of the right side of this tree
  double getRight(double max);
  
  // get the width of the left side of this tree
  double getLeft(double min); 
  
  

}

class Leaf implements ITree {
  int size; // represents the radius of the leaf
  Color color; // the color to draw it
  
  Leaf(int size, Color color) {
    this.size = size;
    this.color = color;
  }
  
  /*
   * Fields:
   *...this.size...     --int 
   *...this.color...    --Color
   * 
   * Methods:
   * ...this.draw()... --WorldImage
   * ...this.isDrooping()... -- Boolean
   * ...this.combine(int, int, double, double, ITree) -- ITree
   * ...this.changeTheta(double)... -- ITree
   * ...this.getWidth()...   -- double
   * ...this.getRight(double)... -- double
   * ...this.getLeft(double)...  -- double
   *
   * Methods on Fields:
   * 
   * 
   */
  
  // draw this leaf
  public WorldImage draw() {
    return new CircleImage(this.size, OutlineMode.SOLID, this.color);
  }
  
  // is this leaf drooping?
  public boolean isDrooping() {
    return false;
  }
  
  // Combine this leaf with that tree
  public ITree combine(int leftLength, int rightLength, double leftTheta, double rightTheta,
      ITree otherTree) {
    return new Branch(leftLength, rightLength, 
        leftTheta, rightTheta, this.changeTheta(leftTheta), otherTree.changeTheta(rightTheta)); 
        
  }
  
  // Change the dimensions of this leaf
  public ITree changeTheta(double theta) {
    return this; 
  }

  // Get the width of this leaf
  public double getWidth() {
    return this.size * 2.0; 
  }
  
  // Get the width of the right side of this tree
  public double getRight(double max) {
    return this.size + max;
  }
  
  // Get the width of the left side of this tree
  public double getLeft(double min) {
    return min - this.size; 
  }
}

class Stem implements ITree {
  // How long this stick is
  int length;
  // The angle (in degrees) of this stem, relative to the +x axis
  double theta;
  // The rest of the tree
  ITree tree;
  
  Stem(int length, double theta, ITree tree) {
    this.length = length;
    this.theta = theta;
    this.tree = tree;
  }
  /*
   * Fields.
   * ...this.length...      --int 
   * ...this.theta...       --double 
   * ...this.tree...        --ITree
   * 
   * 
   * 
   * Methods 
   * ...this.draw()... --WorldImage
   * ...this.isDrooping()... -- Boolean
   * ...this.combine(int, int, double, double, ITree) -- ITree
   * ...this.changeTheta(double)... -- ITree
   * ...this.getWidth()...   -- double
   * ...this.getRight(double)... -- double
   * ...this.getLeft(double)...  -- double
   * 
   * 
   * Methods On Fields 
   * ...this.tree.draw()... --WorldImage
   * ...this.tree.isDrooping()... -- Boolean
   * ...this.tree.combine(int, int, double, double, ITree) -- ITree
   * ...this.tree.changeTheta(double)... -- ITree
   * ...this.tree.getWidth()...   -- double
   * ...this.tree.getRight(double)... -- double
   * ...this.tree.getLeft(double)...  -- double
   * 
   */
  
  // draws this stem and the tree it's attached to
  public WorldImage draw() {
    return new OverlayImage(
        this.tree.draw(),
        new LineImage(new Posn((int) this.deltaX(), (int) this.deltaY()), Color.RED)
            .movePinhole(-this.deltaX() / 2, -this.deltaY() / 2)).movePinhole(this.deltaX(),
                this.deltaY());
  }

  // Finds the change in x of this stem
  double deltaX() {
    return -Math.cos(Math.toRadians(this.theta)) * this.length;
  }
  
  // Finds the change in y of this stem
  double deltaY() {
    return Math.sin(Math.toRadians(this.theta)) * this.length;
  }
  
  // Is this stem drooping?
  public boolean isDrooping() {
    return (((this.theta % 360) + 360) % 360) > 180
        || tree.isDrooping();
  }
  
  // Combine this stem with that tree
  public ITree combine(int leftLength, int rightLength, double leftTheta, double rightTheta,
      ITree otherTree) {
    return new Branch(leftLength, rightLength, leftTheta, rightTheta, 
        this.changeTheta(leftTheta), this.changeTheta(rightTheta)); 
  }
  
  // Change the dimensions of this stem
  public ITree changeTheta(double theta) {
    return new Stem(this.length, (this.theta + (theta - 90)), this.tree.changeTheta(theta)); 
  }
  
  // Get the width of this stem
  public double getWidth() {
    return this.getRight(this.tree.getRight(0)) - this.getLeft(this.tree.getLeft(0)); 
  }
  
  // Get the width of the right side of this stem
  public double getRight(double max) {
    return Math.max(max, max - (this.length * Math.cos(Math.toRadians(this.theta)))); 
  }
  
  // Get the width of the left side of this stem
  public double getLeft(double min) {
    return Math.min(min, min + (this.length * Math.sin(Math.toRadians(this.theta)))
        ); 
  }
}

class Branch implements ITree {
  int leftLength;
  int rightLength;
  double leftTheta;
  double rightTheta;
  ITree left;
  ITree right;
  
  Branch(int leftLength, int rightLength, double leftTheta, double rightTheta, ITree left,
      ITree right) {
    this.leftLength = leftLength;
    this.rightLength = rightLength;
    this.leftTheta = leftTheta;
    this.rightTheta = rightTheta;
    this.left = left;
    this.right = right;
  }
  
  /*
   * Fields
   * ...this.leftLength...                    --int
   * ...this.rightLength...                   --int
   * ...this.leftTheta...                     --double
   * ...this.rightTheta...                    --double
   * ...this.left...                          --ITree
   * ...this.right...                         --ITree
   * 
   * Methods
   * ...this.draw()...                        --WorldImage
   * 
   * Methods on Fields...this.left.draw()...  --WorldImage
   * ...this.right.draw()...                  --WorldImage
   * 
   * 
   */
  
  // Draws this branch and the trees attached to it
  public WorldImage draw() {
    return new OverlayImage(
        new Stem(this.leftLength, this.leftTheta, this.left).draw(),
        new Stem(this.rightLength, this.rightTheta, this.right).draw()); 
  }
  
  // Is this branch drooping?
  public boolean isDrooping() {
    return ((((this.leftTheta % 360) + 360) % 360) > 180)
        || ((((this.rightTheta % 360) + 360) % 360) > 180) 
        || this.left.isDrooping()
        || this.right.isDrooping(); 
  }
 
  // Combine this tree with another tree
  public ITree combine(int leftLength, int rightLength, double leftTheta, double rightTheta,
      ITree otherTree) {
    return new Branch(leftLength, rightLength, leftTheta, rightTheta, 
        this.changeTheta(leftTheta), this.changeTheta(rightTheta)); 
    
  }
  
  // Change the dimensions of this branch
  public ITree changeTheta(double theta) {
    return new Branch(this.leftLength, this.rightLength, this.leftTheta, this.rightTheta, 
        this.left.changeTheta(theta),
        this.right.changeTheta(theta)); 
  }
  
  // Return the width of this branch
  public double getWidth() {
    return this.left.getLeft(0) - this.right.getRight(0); 
  }
  
  // Get the width of the right side of the tree
  public double getRight(double max) {
    return Math.max(max, Math.max(Math.max(this.left.getRight(max), this.right.getRight(max)),
        Math.max(this.right.getRight(max), this.right.getRight(max))));
  }
  
  // Get the width of the left side of the tree
  public double getLeft(double min) {
    return Math.max(min, Math.max(Math.max(this.left.getLeft(min), this.right.getLeft(min)),
        Math.max(this.right.getLeft(min), this.right.getLeft(min))));
  }
}

class ExamplesTrees {
  
  // leaves
  ITree leaf1 = new Leaf(5, Color.BLUE);
  ITree leaf2 = new Leaf(20, Color.CYAN);
  ITree leaf3 = new Leaf(40,Color.BLACK);
  
  // Stems
  ITree stem1 = new Stem(90, 45, leaf1);
  ITree dStem1 = new Stem(90, -90, leaf1);
  ITree dStem2 = new Stem(90, 270, leaf1);
  
  ITree stem2 = new Stem(30, 45, leaf2);
  ITree stem3 = new Stem(45,120,leaf3);
  ITree stem4 = new Stem(230,90,leaf2);
  
  // Branches
  ITree branch1 = new Branch(20, 30, 135, 40, leaf1, leaf2);
  ITree branch2 = new Branch(10, 15, 180, 90, stem2, stem1);
  ITree dBranch1 = new Branch(5, 12, 270, 5, branch1,stem1);
  
  ITree tree1 = new Branch(30,30,135,40,new Leaf(10,Color.RED), new Leaf(15,Color.BLUE));
  
  
  boolean testDrawTree(Tester t) {
    WorldCanvas c = new WorldCanvas(500, 500);
    WorldScene s = new WorldScene(500, 500);
    return c.drawScene(s.placeImageXY(tree1.draw()
        , 250, 250))
        && c.show();
  }
  
  boolean testDrooping(Tester t) {
    return t.checkExpect(dStem1.isDrooping(), true)
        && t.checkExpect(dStem2.isDrooping(), true)
        && t.checkExpect(dBranch1.isDrooping(), true)
        && t.checkExpect(stem1.isDrooping(), false)
        && t.checkExpect(branch1.isDrooping(), false);
  }
  
  boolean testCombine(Tester t) {
    WorldCanvas c = new WorldCanvas(500, 500);
    WorldScene s = new WorldScene(500, 500);
    return c.drawScene(s.placeImageXY(tree1.combine(40,50,150,30, branch1).draw()
        , 250, 250))
        && c.show();
  }
  
  boolean testWidth(Tester t) {
    return t.checkInexact(leaf1.getWidth(), 10.0, 0.01)
        && t.checkInexact(stem1.getWidth(), 10.0, 0.01); 
  }
  
}