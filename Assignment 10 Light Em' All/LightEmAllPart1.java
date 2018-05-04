import java.util.Random;

class LightEmAllPart1 extends ALightEmAll {
  // main constructor
  LightEmAllPart1(int width, int height) {
    super(width, height);
    this.radius = 1000;
  }

  // Convenience constructor for testing: only initializes blank data
  LightEmAllPart1(boolean testing, int width, int height, Random rand) {
    super(testing, width, height, rand);
    this.radius = 1000;
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
  // EFFECT: Increases size of this board, generates new GamePieces
  // Generate the GameBoard with a simple, fixed layout
  void generateWires() {
    for (int col = 0; col < this.width; col += 1) {
      for (int row = 0; row < this.height; row += 1) {
        GamePiece cur = this.board.get(col).get(row);
        cur.left = (row == this.powerRow && col > 0);
        cur.right = (row == this.powerRow && col < (width - 1));
        cur.top = (row > 0);
        cur.bottom = (row < (height - 1));
        cur.powerStation = (row == this.powerRow && col == this.powerCol);
      }
    }
  }
}