import java.util.HashMap;

// not used until part three
class Edge implements Comparable<Edge> {
  GamePiece fromNode;
  GamePiece toNode;
  int weight;

  // Main constructor
  Edge(GamePiece fromNode, GamePiece toNode, int weight) {
    this.fromNode = fromNode;
    this.toNode = toNode;
    this.weight = weight;
  }

  // Convenience constructor: weight of 1
  Edge(GamePiece fromNode, GamePiece toNode) {
    this.fromNode = fromNode;
    this.toNode = toNode;
    this.weight = 1;
  }

  // Below code to test:
  // public String toString() {
  // return String.format("%s->%s", this.fromNode.toString(),
  // this.toNode.toString());
  // }

  // Compare the weights of this edge with the given edge
  // Value greater than 1 means that this is larger than that
  public int compareTo(Edge that) {
    // Is this compareTo implementation appropriate?
    return this.weight - that.weight;
  }

  // Template: Same as class + ...unionFind... HashMap<GamePiece, GamePiece>
  // Does adding this edge to the given hashmap create a cycle?
  public boolean createsCycle(HashMap<GamePiece, GamePiece> unionFind) {
    Utils u = new Utils();
    return (u.representative(unionFind,
        this.fromNode) == (u.representative(unionFind, this.toNode)));
  }

  // Template: Same as class
  // EFFECT: Connects the fromNode to the ToNode
  // Connect the ends of this edge
  void connectNodes() {
    this.toNode.connect(this.fromNode);
  }
}