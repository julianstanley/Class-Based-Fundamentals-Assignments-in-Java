import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import javalib.impworld.World;
import javalib.impworld.WorldScene;
import javalib.worldimages.*;

abstract class ALightEmAll extends World {
  // Physical properties of the board
  int width;
  int height;

  // TODO: Make cell size custom
  int cellSize;

  // Board components
  ArrayList<ArrayList<GamePiece>> board;
  ArrayList<GamePiece> nodes;
  ArrayList<Edge> mst;

  // Components of the powerStation
  int powerRow;
  int powerCol;
  int radius;
  int powerX;
  int powerY;

  // Components of the endGame
  boolean active;
  String endText;

  // Testing components
  Random rand;

  // Time and movement
  int time;
  int moves;

  // main constructor
  ALightEmAll(int width, int height) {
    // Define the physical board
    this.width = width;
    this.height = height;

    // TODO: Use this cell size
    this.cellSize = Math.max(5, 640 / Math.max(width, height));

    // Initialize testing components
    rand = new Random();

    this.mst = new ArrayList<Edge>();

    // Define the properties of the powerstation
    this.powerRow = this.height / 2;
    this.powerCol = this.width / 2;
    this.powerX = this.powerCol * 60 + 30;
    this.powerY = this.powerRow * 60 + 30;

    // Generate the board and the pieces
    this.board = new ArrayList<ArrayList<GamePiece>>();
    generateBoard();

    // Collapse the board into a node list
    this.nodes = new ArrayList<GamePiece>();
    generateNodes();

    generateWires();

    generateNeighbors();

    this.radius = this.board.get(powerCol).get(powerRow).radius();

    // Shuffle the pieces around
    shufflePieces();

    // Update the cells to match their surroundings
    generateNeighbors();
    updatePower();

    // Initialize end-of-game considerations
    this.active = true;
    this.endText = "The game ended unexpectedly";

    // Initialize the time and movement score
    this.time = 0;
    this.moves = 0;

  }

  // Convenience constructor for testing: only initializes blank data
  ALightEmAll(boolean testing, int width, int height, Random rand) {
    // Define the physical board
    this.width = width;
    this.height = height;

    // TODO: Use this cell size
    this.cellSize = Math.max(5, 640 / Math.max(width, height));

    // Initialize testing components
    this.rand = rand;

    // TODO: Implement edges
    this.mst = new ArrayList<Edge>();

    // Define the properties of the powerstation
    this.powerRow = this.height / 2;
    this.powerCol = this.width / 2;

    // Generate the board and the pieces
    generateBoard();
    this.radius = this.board.get(powerCol).get(powerRow).radius();

    // Collapse the board into a node list
    this.nodes = new ArrayList<GamePiece>();
    // Do not generateNodes();

    // Shuffle the pieces around
    // Do not shufflePieces();

    // Update the cells to match their surroundings
    // Do not generateNeighbors();
    // Do not updatePower();

    // Initialize end-of-game considerations
    this.active = true;
    this.endText = "";

    // Initialize time and score
    time = 0;
    moves = 0;

  }

  /*-TEMPLATE:
   * Fields:
   * ...this.board... -ArrayList<ArrayList<GamePiece>>
   * ...this.nodes... -ArrayList<GamePiece>
   * ...this.mst... -ArrayList<Edge>
   * ...this.width... -int
   * ...this.height... -int
   * ...this.powerRow... -int
   * ...this.powerCol... -int
   * ...this.radius... -int
   * 
   * Methods:
   * ...this.generateBoard()... -void
   * ...this.makeScene()... -WorldScene
   * ...this.onMouseClicked(Posn mouse, String button)... -void
   * 
   * Methods on Fields:
   * this.board, this.nodes, and this.mst have ArrayList methods
   */

  // Template: same as class
  // EFFECT: increases size of this board, generates new GamePieces
  // Generate the GameBoard with a fractal-based layout
  void generateBoard() {
    this.board = new ArrayList<>();
    for (int col = 0; col < this.width; col += 1) {
      ArrayList<GamePiece> soFar = new ArrayList<GamePiece>();
      for (int row = 0; row < this.height; row += 1) {
        soFar.add(new GamePiece(row, col, false, // Left
            false, // Right
            false, // Up
            false, // Down
            row == this.powerRow && col == this.powerCol));
      }
      this.board.add(soFar);
    }
  }

  abstract void generateWires();

  // Template: Same as class
  // Effect: adds GamePieces to nodes
  // initializes the grid of GamePieces
  void generateNodes() {
    for (int col = 0; col < this.width; col += 1) {
      for (int row = 0; row < this.height; row += 1) {
        this.nodes.add(this.board.get(col).get(row));
      }
    }
  }

  // Template: Same as class
  // EFFECT: Changes the neighbors list in (potentially) every cell on this
  // game's board
  // Constructs/updates the neighbor lists for each cell
  void generateNeighbors() {
    for (int col = 0; col < this.width; col += 1) {
      for (int row = 0; row < this.height; row += 1) {
        GamePiece cur = this.board.get(col).get(row);
        cur.removeAllNeighbors();
        cur.removeAllValue();

        if (col != 0) {
          GamePiece curLeft = this.board.get(col - 1).get(row);
          if (cur.connectedTo(curLeft)) {
            cur.addNeighbor(curLeft);
          }
        }

        if (col < this.width - 1) {
          GamePiece curRight = this.board.get(col + 1).get(row);
          if (cur.connectedTo(curRight)) {
            cur.addNeighbor(curRight);
          }
        }

        if (row != 0) {
          GamePiece curTop = this.board.get(col).get(row - 1);
          if (cur.connectedTo(curTop)) {
            cur.addNeighbor(curTop);
          }
        }

        if (row < this.height - 1) {
          GamePiece curBottom = this.board.get(col).get(row + 1);
          if (cur.connectedTo(curBottom)) {
            cur.addNeighbor(curBottom);
          }
        }
      }
    }
  }

  // Template: Same as class
  // Draw this LightEmAll game
  public WorldScene makeScene() {
    WorldScene s = new WorldScene(this.height * 60, this.width * 60);

    WorldImage timeImg = new TextImage("Time: " + Integer.toString(this.time), 40, Color.BLACK);

    WorldImage moveImg = new TextImage("Moves: " + Integer.toString(this.moves), 40, 
        Color.PINK.darker());

    WorldImage solidStar =

        new StarImage(25, 7, OutlineMode.SOLID, Color.CYAN);

    WorldImage outlineStar = new StarImage(25, 7, OutlineMode.OUTLINE, Color.ORANGE.darker());

    WorldImage mainStar = new OverlayImage(outlineStar, solidStar);

    for (GamePiece tile : this.nodes) {
      s = tile.drawSelf(s);
    }

    s.placeImageXY(mainStar, powerX, powerY + 100);
    s.placeImageXY(moveImg, this.width * 60 / 5, 50);
    s.placeImageXY(timeImg, this.width * 60 * 4 / 5, 50);
    if (this.active) {
      return s;
    }
    else {

      WorldImage endScene = new TextImage(this.endText, 80, Color.GREEN);
      WorldImage restart = new TextImage("Click to restart", 20, Color.WHITE);
      WorldImage total = new AboveImage(endScene, restart);
      s.placeImageXY(total, this.width * 30, this.height * 20);
      return s;
    }
  }

  public void onTick() {
    if (this.gameWon()) {
      endOfWorld("You win!");
    }

    this.time = Math.min(9999, this.time + 1);
  }

  /*-TEMPLATE:
   * Parameters:
   * ...mouse... -Posn
   * ...button... -String
   * 
   * Methods on Parameters:
   * ...mouse.onMouseClicked(Posn)... -void
   * ...button.onMouseClicked(String)... -void
   */
  // EFFECT: Modifies one cell on this board
  // deal with a mouse click in this game
  public void onMouseClicked(Posn mouse, String button) {
    if (this.active) {
      int col = mouse.x / 60;
      if (col == width) {
        col = width - 1;
      }
      int row;
      if (mouse.y - 100 >= 0) {
        row = (mouse.y - 100) / 60;
      }
      else {
        row = -1;
      }

      if (row >= 0) {
        if (button.equals("LeftButton")) {
          this.moves += 1;
          this.board.get(col).get(row).rotateSelf();

          // After a rotation, re-generate neighbors and update power
          this.generateNeighbors();

          this.radius = this.board.get(powerCol).get(powerRow).radius();
          this.updatePower();
        }

        // If you right click, the board will solve itself
        // By re-generating the game without shuffling
        if (button.equals("RightButton")) {
          if (this.mst.size() > 0) {
            this.placeEdges();
            this.radius = this.board.get(powerCol).get(powerRow).radius();
            generateNeighbors();
            this.updatePower();
          }

          else {
            this.board = new ArrayList<ArrayList<GamePiece>>();
            generateBoard();
            this.nodes = new ArrayList<GamePiece>();
            generateNodes();
            generateWires();
            updatePower();
            generateNeighbors();
            updatePower();
            this.radius = this.board.get(powerCol).get(powerRow).radius();
            this.updatePower();
          }
        }
      }
    }

    else {
      restart();
    }
  }

  // Template: ...name... -- String
  // EFFECT: may restart the game
  // Deals with a key event
  public void onKeyEvent(String name) {
    if (name.equals("r")) {
      restart();
    }

    if (this.active) {
      GamePiece current = this.board.get(powerCol).get(powerRow);
      // TODO: Abstract by making three local variables
      // one of which is dx, one dy, one is the relevant wire. If up: dx dy set,
      // wire is my top

      if (name.equals("up")) {
        int powerRowNext = Math.max(this.powerRow - 1, 0);
        GamePiece next = this.board.get(powerCol).get(powerRowNext);
        if (current.connectedTo(next)) {
          this.powerY -= 60;
          this.powerRow = powerRowNext;
          this.moves += 1;
        }
      }

      if (name.equals("down")) {
        int powerRowNext = Math.min(this.powerRow + 1, this.height - 1);
        GamePiece next = this.board.get(powerCol).get(powerRowNext);
        if (current.connectedTo(next)) {
          this.powerY += 60;
          this.powerRow = powerRowNext;
          this.moves += 1;
        }
      }

      if (name.equals("left")) {
        int powerColNext = Math.max(this.powerCol - 1, 0);
        GamePiece next = this.board.get(powerColNext).get(powerRow);
        if (current.connectedTo(next)) {
          this.powerX -= 60;
          this.powerCol = powerColNext;
          this.moves += 1;
        }
      }

      if (name.equals("right")) {
        int powerColNext = Math.min(this.powerCol + 1, this.width - 1);
        GamePiece next = this.board.get(powerColNext).get(powerRow);
        if (current.connectedTo(next)) {
          this.powerX += 60;
          this.powerCol = powerColNext;
          this.moves += 1;
        }
      }

      this.radius = this.board.get(powerCol).get(powerRow).radius();
      this.updatePower();
    }
  }

  // Template: same as class
  // EFFECT: Clears and re-initializes all data
  // Restart this game
  void restart() {

    this.mst = new ArrayList<Edge>();
    this.board = new ArrayList<ArrayList<GamePiece>>();
    this.nodes = new ArrayList<GamePiece>();

    generateBoard();
    generateNodes();
    generateWires();
    this.shufflePieces();

    generateNeighbors();
    this.radius = this.board.get(powerCol).get(powerRow).radius();
    updatePower();
    this.time = 0;
    this.moves = 0;

    this.active = true;
    this.endText = "The game ended unexpectedly";
  }

  // Template: same as class
  // EFFECT: Updates this game's powerstation game piece, setting off a chain
  // reaction of game piece activation
  // Starting with the power source, update the values of reachable pieces
  void updatePower() {
    for (GamePiece cur : this.nodes) {
      cur.updateValue(0, 0, this.width * this.height, new ArrayList<GamePiece>());
    }

    this.board.get(powerCol).get(powerRow).updateValue(1, 0, this.radius,
        new ArrayList<GamePiece>());
  }

  // Randomly shuffles each piece in this game
  public void shufflePieces() {
    for (GamePiece node : this.nodes) {
      node.shuffle(this.rand);
    }
  }

  // Template: same as class
  // Have the win conditions for this world been met?
  boolean gameWon() {
    if (this.active) {
      for (int col = 0; col < width; col += 1) {
        for (int row = 0; row < height; row += 1) {
          GamePiece curr = board.get(col).get(row);
          if (curr.activation < 0.001) {
            return false;
          }
        }
      }
      this.endOfWorld("You Won");
      return true;
    }
    else {
      return false;
    }
  }

  // Template: same as class
  // EFFECT: Inactivates game, changes endText
  // Modify this world to display the appropriate endWorld
  public void endOfWorld(String s) {
    this.active = false;
    this.endText = s;
  }

  // EFFECT: Places edges in this.mst
  // Template: Same as class
  // EFFECT: Connects nodes on this board
  // Uses this game's minimum spanning tree to connect game pieces
  void placeEdges() {

    // Reset cells
    for (GamePiece node : this.nodes) {
      node.removeAllNeighbors();
      node.removeAllValue();
      node.removeAllWires();
    }

    // Use edges to connect cells
    for (Edge edge : this.mst) {
      edge.connectNodes();
    }
  }

  // Template: Same as class
  // EFFECT: Adds cells to the neighbors of all the game pieces in this game
  // Have all cells in this game look next to each other and keep track of their
  // physical neighbors
  void initNeighbors() {
    for (int col = 0; col < this.width; col += 1) {
      for (int row = 0; row < this.height; row += 1) {
        GamePiece curNode = this.board.get(col).get(row);
        if (this.isOnMap(col + 1, row)) {
          curNode.addNeighbor(board.get(col + 1).get(row));
        }

        if (this.isOnMap(col - 1, row)) {
          curNode.addNeighbor(board.get(col - 1).get(row));
        }

        if (this.isOnMap(col, row + 1)) {
          curNode.addNeighbor(board.get(col).get(row + 1));
        }

        if (this.isOnMap(col, row - 1)) {
          curNode.addNeighbor(board.get(col).get(row - 1));
        }
      }
    }
  }

  // Template: Same as class + ...x... int and ...y... int
  // Are the given x and y coordinates within this game's map?
  boolean isOnMap(int x, int y) {
    return x >= 0 && y >= 0 && y < this.height && x < this.width;
  }

  // Template: Same as class
  // Return all possible edges
  ArrayList<Edge> generatePossibleEdges() {
    ArrayList<Edge> answer = new ArrayList<Edge>();
    // Assumes: all GamePieces know their neighbors
    for (GamePiece node : this.nodes) {
      for (Edge edge : node.makeEdges()) {
        answer.add(edge);
      }
    }

    return answer;

  }

}
