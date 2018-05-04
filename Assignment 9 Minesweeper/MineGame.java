import java.awt.Color;
import java.util.ArrayList;
import javalib.impworld.*;
import javalib.worldimages.*;

class MineGame extends World {
  int columns;
  int rows;
  int numMines;
  ArrayList<ArrayList<Cell>> cells;
  boolean active;
  String endText;
  int score;
  int time;

  // For testing
  ArrayList<Boolean> mineList;

  // Generate this game with mines at the position specified by the given
  // mineList.
  MineGame(int columns, int rows, int numMines, ArrayList<Boolean> mineList) {
    this.columns = columns;
    this.rows = rows;
    this.numMines = numMines;
    this.cells = new ArrayList<ArrayList<Cell>>(columns);
    this.mineList = mineList;
    this.active = true;
    this.endText = "";

    this.score = numMines;
    this.time = 0;

    // For every column, add an empty row
    for (int i = 0; i < columns; i += 1) {
      cells.add(new ArrayList<Cell>(rows));
    }

    // For every column, we know there will be an empty row
    // In each of those empty rows, add a blank cell
    for (int i = 0; i < columns; i += 1) {
      for (int j = 0; j < rows; j += 1) {
        cells.get(i).add(new Cell());
      }
    }

    // Create mines based on this game's minelist
    initMines();

    // Connect all neighboring cells
    initNeighbors();

  }

  // Used exclusively for testing: doeesn't initialize mines or neighbors
  MineGame(int columns, int rows, int numMines, ArrayList<Boolean> mineList, Boolean testing) {

    this.columns = columns;
    this.rows = rows;
    this.numMines = numMines;
    this.cells = new ArrayList<ArrayList<Cell>>(columns);
    this.mineList = mineList;
    this.active = true;
    this.score = numMines;
    this.time = 0;
    this.endText = "";

    // For every column, add an empty row
    for (int i = 0; i < columns; i += 1) {
      cells.add(new ArrayList<Cell>(rows));
    }

    // For every column, we know there will be an empty row
    // In each of those empty rows, add a blank cell
    for (int i = 0; i < columns; i += 1) {
      for (int j = 0; j < rows; j += 1) {
        cells.get(i).add(new Cell());
      }
    }

  }

  // Generate this game with mines at random positions.
  MineGame(int columns, int rows, int numMines) {
    this(columns, rows, numMines, new Utils().scrambledList(numMines, columns * rows));
  }

  // Used exclusively for testing: doeesn't initialize mines or neighbors

  /*
   * Fields: ...this.columns... -- int ...this.rows... -- int
   * ...this.numMines... -- int ...this.cells... -- ArrayList<ArrayList<Cell>>
   * ...this.mineList... -- ArrayList<Boolean>
   * 
   * Methods: ...this.initMines()... -- void ...this.initNeighbors()... -- void
   * ...this.isOnMap(int, int)... -- boolean ...this.makeScene()... --
   * WorldScene ...this.onMouseClicked(Posn, String)... -- void
   * ...this.gameWon()... -- boolean
   * 
   */

  // EFFECT: Modifies some of the cells within the cells ArrayList
  // Randomly creates mines
  void initMines() {

    // Use the boolean list to assign mines
    for (int x = 0; x < columns; x += 1) {
      for (int y = 0; y < rows; y += 1) {
        if (this.mineList.get(x + y * columns)) {
          this.cells.get(x).get(y).setMine();
        }
      }
    }
  }

  // EFFECT: Adds cells to the neighbors lists of the cells in ArrayList
  // Linking neighboring cells together
  void initNeighbors() {
    for (int x = 0; x < columns; x++) {
      for (int y = 0; y < rows; y++) {
        Cell curr = cells.get(x).get(y);
        for (int i = -1; i <= 1; i++) {
          for (int j = -1; j <= 1; j++) {
            if (i == 0 && j == 0) {
              // No neighbor initialization needed here
            }
            else if (isOnMap(x + i, y + j)) {
              curr.addNeighbor(cells.get(x + i).get(y + j));
            }
          }
        }
      }
    }
  }

  // Is the given coordinate on the playing map?
  boolean isOnMap(int x, int y) {
    return x >= 0 && y >= 0 && x < columns && y < rows;
  }

  // Draw this world
  public WorldScene makeScene() {
    WorldScene s = new WorldScene(columns * 30, rows * 30 + 100);

    s.placeImageXY(drawScore(), columns * 7, 50);
    s.placeImageXY(drawTime(), columns * 23, 50);

    for (int i = 0; i < columns; i++) {
      for (int j = 0; j < rows; j++) {
        s.placeImageXY(cells.get(i).get(j).drawSelf(), i * 30 + 15, j * 30 + 115);
      }
    }

    if (this.active) {
      return s;
    }

    else {
      WorldScene end = new WorldScene(columns * 30, rows * 30);
      end.placeImageXY(new AboveImage(new TextImage(this.endText, 40, Color.BLACK),
          new TextImage("Click anywhere to restart", 30, Color.RED)), columns * 15, rows * 15);
      return end;
    }
  }

  // Draws the score onto this world. Score is the number of mines remaining
  WorldImage drawScore() {
    return new BesideImage(new TextImage("Mines Remaining: ", 20, Color.BLACK),
        new TextImage(Integer.toString(this.score), 50, Color.BLACK));
  }

  // Draws the time elapsed onto this world.
  WorldImage drawTime() {
    return new BesideImage(new TextImage("Time: ", 20, Color.BLACK),
        new TextImage(Integer.toString(this.time), 50, Color.BLACK));
  }

  // EFFECT: Modifies the cells in this world, or ends the game
  // Deal with a mouse click
  public void onMouseClicked(Posn mouse, String button) {
    if (this.active) {

      int x = mouse.x / 30;
      int y = Math.max(-1, (mouse.y - 100) / 30);

      if (y >= 0) {
        if (button.equals("LeftButton")) {
          cells.get(x).get(y).activate();
          makeScene();
          if (cells.get(x).get(y).mineTripped()) {
            this.endOfWorld("You Lose");
          }
          else if (gameWon()) {
            this.endOfWorld("You Win");
          }
        }
        else if (button.equals("RightButton")) {
          Cell cell = cells.get(x).get(y);
          if (!cell.activated) {
            if (cell.flagged) {
              this.score += 1;
            }
            else {
              this.score -= 1;
            }
            cell.flag();
            makeScene();
          }
        }
      }
    }

    else {
      this.reset();
      this.active = true;
    }
  }

  // Deal with each world tick
  public void onTick() {
    this.time += 1;
    makeScene();
  }

  // Modify this world to display the appropriate endWorld
  // Overrides endOfWorld
  public void endOfWorld(String s) {
    this.endText = s;
    this.active = false;
  }

  // Have the win conditions for this world been met?
  boolean gameWon() {
    for (int x = 0; x < columns; x++) {
      for (int y = 0; y < rows; y++) {
        Cell curr = cells.get(x).get(y);
        if (!curr.hasMine() && !curr.activated) {
          return false;
        }
      }
    }
    return true;
  }

  // EFFECT: Changes most of this world's fields to reset the board. 
  // Reset this world
  void reset() {

    for (int i = 0; i < columns; i++) {
      for (int j = 0; j < rows; j++) {
        Cell curr = cells.get(i).get(j);
        curr.neighbors = new ArrayList<Cell>();
        curr.flagged = false;
        if (curr.activated) {
          curr.activated = false;
        }
      }
    }
    this.score = this.numMines;
    this.time = 0;
    this.mineList = new Utils().scrambledList(this.numMines, this.columns * this.rows);
    this.initMines();
    this.initNeighbors();
  }
}
