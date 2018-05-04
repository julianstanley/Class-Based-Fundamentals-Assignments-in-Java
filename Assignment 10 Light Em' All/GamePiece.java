import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javalib.impworld.*;
import javalib.worldimages.*;

class GamePiece {
  int row;
  int col;
  boolean left;
  boolean right;
  boolean top;
  boolean bottom;
  boolean powerStation;
  double activation;

  ArrayList<GamePiece> neighbors;

  // main constructor
  GamePiece(int row, int col, boolean left, boolean right, boolean top, boolean bottom,
      boolean powerStation) {

    this.row = row;
    this.col = col;

    this.left = left;
    this.right = right;
    this.top = top;
    this.bottom = bottom;
    this.powerStation = powerStation;
    this.activation = 0.0;
    this.neighbors = new ArrayList<GamePiece>();
  }

  // Code below used for testing:
  // public String toString() {
  // return String.format("(%d,%d)%c%c%c%c%c", this.row, this.col, (left ? 'L' :
  // '_'),
  // (top ? 'T' : '_'), (right ? 'R' : '_'), (bottom ? 'B' : '_'), (powerStation
  // ? 'P' : '_'));
  // }

  /*-TEMPLATE:
   * Fields:
   * ...this.row... -int
   * ...this.col... -int
   * ...this.left... -boolean
   * ...this.right... -boolean
   * ...this.top... -boolean
   * ...this.bottom... -boolean
   * ...this.powerStation... -boolean
   * ...this.neighbors... ArrayList<GamePiece>
   * 
   * Methods:
   * ...this.drawSelf(WorldScene s)... -WorldScene
   * ...this.rotateSelf()... -void
   * 
   * Methods on Fields:
   */

  /*-TEMPLATE:
   * Parameters:
   * ...s... -WorldScene
   * Methods on Parameters:
   */

  // Template: same as class + ...s... -- WorldScene
  // Draws this game piece onto the given scene
  WorldScene drawSelf(WorldScene s) {
    Utils u = new Utils(); 
    Color wireColor;
    if (Math.abs(this.activation - 1.0) < 0.001) {
      wireColor = Color.YELLOW;
    }
    
   

    else {
      wireColor = Color.WHITE;
    }
    
    
    // TODO: Simplify logic by making the wire, and then moving the pinhole
    // afterwards

    OverlayImage answer = new OverlayImage(
        new RectangleImage(60, 60, OutlineMode.OUTLINE, Color.BLACK),
        new RectangleImage(60, 60, OutlineMode.SOLID, Color.DARK_GRAY));

    if (left) {
      LineImage left = new LineImage(new Posn(30, 0), wireColor);
      answer = new OverlayImage(left.movePinhole(15, 0), answer);
    }

    if (right) {
      LineImage right = new LineImage(new Posn(-30, 0), wireColor);
      answer = new OverlayImage(right.movePinhole(-15, 0), answer);
    }

    if (top) {
      LineImage top = new LineImage(new Posn(0, 30), wireColor);
      answer = new OverlayImage(top.movePinhole(0, 15), answer);
    }

    if (bottom) {
      LineImage bottom = new LineImage(new Posn(0, -30), wireColor);
      answer = new OverlayImage(bottom.movePinhole(0, -15), answer);
    }

    s.placeImageXY(answer, this.col * 60 + 30, this.row * 60 + 130);

    return s;
  }

  // Template: Same as class + ...that... GamePiece
  // EFFECT: Increases length of neighbors by 1
  // Adds a neighbor to this game piece
  void addNeighbor(GamePiece that) {
    this.neighbors.add(that);
  }

  // Template: Same as class + ...that... GamePiece
  // EFFECT: adds wires to connect this piece to the other, plus calls
  // addNeighbor
  // Connect this gamepiece to the one given
  void connect(GamePiece that) {

    if (that.col < this.col) {
      this.left = true;
      that.right = true;
    }

    else if (that.col > this.col) {
      this.right = true;
      that.left = true;
    }

    else if (that.row < this.row) {
      this.top = true;
      that.bottom = true;

    }

    else if (that.row > this.row) {
      this.bottom = true;
      that.top = true;
    }

    if (!this.neighbors.contains(that)) {
      this.addNeighbor(that);
      that.addNeighbor(this);

    }
  }

  // Template: same as class
  // EFFECT: Neighbors.size --> 0
  // Removes all neighbors
  void removeAllNeighbors() {
    this.neighbors = new ArrayList<GamePiece>();
  }

  // Template: Same as class
  // EFFECT: Removes all connecting wire booleans
  // Removes all wires
  void removeAllWires() {
    this.left = false;
    this.right = false;
    this.top = false;
    this.bottom = false;
  }

  // Template: Same as class
  // EFFECT: this.activtion --> 0
  // Removes all value
  void removeAllValue() {
    this.activation = 0.0;
  }

  // Template: Same as class
  // EFFECT: modifies the bottom, top, left and right fields of this game piece
  // rotates this game piece clockwise
  // Also updates the value of affected pieces
  void rotateSelf() {
    this.activation = 0.0;
    boolean tempLeft = this.bottom;
    boolean tempRight = this.top;
    boolean tempTop = this.left;
    boolean tempBottom = this.right;

    this.left = tempLeft;
    this.right = tempRight;
    this.top = tempTop;
    this.bottom = tempBottom;
  }

  // Template: ...value... -- Double
  // EFFECT: Modifies this value and potentially the value of neighbors
  // Updates the value of this gamepiece to the value given
  // In turn updates the value of any reachable game pieces
  void updateValue(double value, int curLayer, int radius, ArrayList<GamePiece> soFar) {
    soFar.add(this);
    if (curLayer <= radius) {
      this.activation = value;
    }

    for (GamePiece cur : this.neighbors) {
      if (!soFar.contains(cur)) {
        cur.updateValue(value, curLayer + 1, radius, soFar);
      }
    }
  }

  // Template: same as class
  // EFFECT: Randomly changes wiring, keeping number and relative position of
  // wires constant
  // Shuffle this gamePiece
  void shuffle(Random rand) {
    int rotations = rand.nextInt(4);

    for (int i = 0; i < rotations; i += 1) {
      this.rotateSelf();
    }
  }

  // Template: ...other... -- GamePiece
  // Is this GamePiece connected to the other GamePiece?
  boolean connectedTo(GamePiece other) {
    if (this.col + 1 == other.col && this.row == other.row) {
      return (this.right && other.left);
    }

    else if (this.col - 1 == other.col && this.row == other.row) {
      return (this.left && other.right);
    }

    else if (this.col == other.col && this.row + 1 == other.row) {
      return (this.bottom && other.top);
    }

    else if (this.col == other.col && this.row - 1 == other.row) {
      return (this.top && other.bottom);
    }

    else {
      return (false);
    }
  }

  // Template: Same as class
  // Find the radius of the board starting at this GamePiece
  int radius() {
    return this.furthestFrom().maxDist() / 2 + 1;
  }

  // Template: Same as class
  // Find the one of the furthest gamepiece from this gamepiece
  GamePiece furthestFrom() {
    Queue<GamePiece> worklist = new Queue<GamePiece>(new Deque<GamePiece>());
    worklist.enqueue(this);

    ArrayList<GamePiece> soFar = new ArrayList<GamePiece>();

    GamePiece current = this;

    while (!worklist.isEmpty()) {
      current = worklist.dequeue();

      if (current.neighbors.size() != 0) {
        for (GamePiece neighbor : current.neighbors) {
          if (!soFar.contains(neighbor)) {
            soFar.add(neighbor);
            worklist.enqueue(neighbor);
          }
        }
      }

    }

    GamePiece lastPiece = current;
    return lastPiece;

  }

  // Template: Same as class
  // Find the max distance connected from this GamePiece
  int maxDist() {

    // Each GamePiece has a distance from this gamepiece
    HashMap<GamePiece, Integer> distToThis = new HashMap<GamePiece, Integer>();
    distToThis.put(this, 0);

    Queue<GamePiece> worklist = new Queue<GamePiece>(new Deque<GamePiece>());
    worklist.enqueue(this);

    ArrayList<GamePiece> soFar = new ArrayList<GamePiece>();

    GamePiece current = this;

    while (!worklist.isEmpty()) {
      current = worklist.dequeue();

      if (current.neighbors.size() != 0) {
        for (GamePiece neighbor : current.neighbors) {
          if (!soFar.contains(neighbor)) {
            soFar.add(neighbor);
            distToThis.put(neighbor, distToThis.get(current) + 1);
            worklist.enqueue(neighbor);
          }
        }
      }

    }

    int lastDistance = distToThis.get(current);

    return lastDistance;
  }

  ArrayList<Edge> makeEdges() {
    ArrayList<Edge> answer = new ArrayList<Edge>();
    for (GamePiece to : this.neighbors) {
      Edge addend = new Edge(this, to, new Random().nextInt());
      answer.add(addend);
    }
    return (answer);
  }
}