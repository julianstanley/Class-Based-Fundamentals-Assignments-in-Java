import java.util.Random;

class LightEmAllPart2 extends ALightEmAll {
  LightEmAllPart2(int width, int height) {
    super(width, height);
  }

  LightEmAllPart2(boolean testing, int width, int height, Random rand) {
    super(testing, width, height, rand);
  }

  void generateWires() {
    this.powerCol = this.width / 2;
    this.powerRow = this.height / 2;
    this.powerX = this.powerCol * 60 + 30;
    this.powerY = 30;
    fractalConnections(0, 0, this.height - 1, this.width - 1);
  }

  // Template: Same as class + ...minRow..., ...minCol..., ...maxRow..., and
  // ...maxCol..., all int
  // EFFECT: Changes neighbor list and connections of GamePieces in this game
  // Connect the pieces of this game in a fractal-based layout
  void fractalConnections(int minRow, int minCol, int maxRow, int maxCol) {

    int midRow = (minRow + maxRow) / 2;
    int midCol = (minCol + maxCol) / 2;

    GamePiece topLeft = this.board.get(minCol).get(minRow);
    GamePiece topRight = this.board.get(maxCol).get(minRow);
    GamePiece botLeft = this.board.get(minCol).get(maxRow);
    GamePiece botRight = this.board.get(maxCol).get(maxRow);

    if (midRow == minRow && midCol == minCol) {
      // Top left piece connects with bottom left
      topLeft.connect(botLeft);

      // Top right to bottom right
      topRight.connect(botRight);

      // Bottom left to bottom right
      botLeft.connect(botRight);

    }

    else if (midRow == minRow) {

      GamePiece midBottom = this.board.get(midCol).get(maxRow);
      GamePiece midBottom1 = this.board.get(midCol + 1).get(maxRow);

      topLeft.connect(botLeft);
      topRight.connect(botRight);
      midBottom.connect(midBottom1);

      fractalConnections(minRow, minCol, maxRow, midCol);
      fractalConnections(minRow, midCol + 1, maxRow, maxCol);

    }

    else if (midCol == minCol) {
      GamePiece midLeft = this.board.get(minCol).get(midRow);
      GamePiece midLeft1 = this.board.get(minCol).get(midRow + 1);

      midLeft.connect(midLeft1);

      fractalConnections(minRow, minCol, midRow, maxCol);
      fractalConnections(midRow + 1, minCol, maxRow, maxCol);

    }

    else {
      GamePiece midLeft = this.board.get(minCol).get(midRow);
      GamePiece midRight = this.board.get(maxCol).get(midRow);
      GamePiece midBottom = this.board.get(midCol).get(maxRow);

      GamePiece midLeft1 = this.board.get(minCol).get(midRow + 1);
      GamePiece midRight1 = this.board.get(maxCol).get(midRow + 1);
      GamePiece midBottom1 = this.board.get(midCol + 1).get(maxRow);

      midLeft.connect(midLeft1);
      midRight.connect(midRight1);
      midBottom.connect(midBottom1);

      fractalConnections(minRow, minCol, midRow, midCol);
      fractalConnections(minRow, midCol + 1, midRow, maxCol);
      fractalConnections(midRow + 1, minCol, maxRow, midCol);
      fractalConnections(midRow + 1, midCol + 1, maxRow, maxCol);
    }

  }

}
