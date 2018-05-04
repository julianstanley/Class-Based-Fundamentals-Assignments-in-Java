import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class LightEmAll extends ALightEmAll {
  // main constructor
  LightEmAll(int width, int height) {
    super(width, height);
  }

  // Convenience constructor for testing: only initializes blank data
  LightEmAll(boolean testing, int width, int height, Random rand) {
    super(testing, width, height, rand);
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

  void generateWires() {
    this.powerCol = 0;
    this.powerRow = 0;
    this.powerX = 30;
    this.powerY = 30;
    this.initMST();
    this.placeEdges();
  }

  // Template: same as class
  // Increases size of this board, generates new GamePieces
  // Generate the GameBoard with Kruskal's Algorithm
  void initMST() {

    // Connect all pieces
    this.initNeighbors();

    // Generate edges between every set of neighbors
    ArrayList<Edge> allEdges = generatePossibleEdges();

    // Sort the list of all edges by weight

    Utils u = new Utils();

    // ArrayList<Edge> sortEdges = allEdges;
    ArrayList<Edge> sortEdges = new Utils().heapsort(allEdges);

    // Initialize a union-find hashmap
    HashMap<GamePiece, GamePiece> unionFind = new HashMap<GamePiece, GamePiece>();

    // Fill unionFind with each node
    for (GamePiece node : this.nodes) {
      unionFind.put(node, node);
    }

    // Move through each edge, and add edges that do not create a cycle
    while (this.mst.size() < this.nodes.size() - 1) {
      Edge next = sortEdges.remove(0);
      if (!next.createsCycle(unionFind)) {
        this.mst.add(next);
        unionFind.replace(u.representative(unionFind, next.toNode),
            u.representative(unionFind, next.fromNode));
      }
    }
  }
}