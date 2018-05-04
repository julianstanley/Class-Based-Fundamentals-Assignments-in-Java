import java.util.ArrayList;
import java.awt.Color;

import javalib.worldimages.*;

class Cell {
  ArrayList<Cell> neighbors;
  IContents contents; // Eventually make this an ArrayList?
  boolean activated;
  boolean flagged;

  // Initialize the cell with no neighbors, a number with zero mines, and not
  // activated
  Cell() {
    this.neighbors = new ArrayList<Cell>();
    this.contents = new Number();
    this.activated = false; // Change this to true to see the mine coverings
  }

  /*
   * Fields: ...this.neighbors... -- ArrayList<Cell> ...this.contents... --
   * IContents ...this.activated... -- boolean ...this.flagged... -- boolean
   * 
   * Methods: ...this.addNeighbor(cell)... -- void ...this.hasMine()... --
   * boolean ...this.setMine()... -- void ...this.drawSelf()... -- void
   * ...this.activate()... -- void ...this.floodFill()... -- void
   * ...this.flagged()... -- void ...this.mineTripped()... -- void
   * 
   * Methods on Fields: ...this.neighbors.*ArrayList methods*
   * ...this.contents.isMine()... -- boolean ...this.contents.add1()... -- void
   * ...this.contents.drawSelf()... -- OverlayImage ...this.contents.is0()... --
   * boolean boolean isMine(); void add1(); OverlayImage drawSelf(); boolean
   * is0();
   */

  // EFFECT: Adds a cell to this.neighbors
  // Add the given cell into this cell's list of neighbors
  void addNeighbor(Cell cell) {

    neighbors.add(cell);

    // If the neighbor is a mine, our number needs to go up by one
    if (cell.hasMine()) {
      this.contents.add1();
    }
  }

  // Does this cell have a mine in its contents?
  boolean hasMine() {
    return this.contents.isMine();
  }

  // EFFECT: Modifies this cell's contents to contain a mine
  // Give this cell a mine. Cells love mines.
  void setMine() {
    this.contents = new Mine();
  }

  // Draws this cell
  OverlayImage drawSelf() {
    if (activated) {
      return this.contents.drawSelf();
    }

    if (flagged) {
      return new OverlayImage(new TextImage("X", 30, Color.RED),
          new OverlayImage(new RectangleImage(30, 30, OutlineMode.OUTLINE, Color.BLACK),
              new RectangleImage(30, 30, OutlineMode.SOLID, Color.lightGray)));
    }

    return new OverlayImage(new RectangleImage(30, 30, OutlineMode.OUTLINE, Color.BLACK),
        new RectangleImage(30, 30, OutlineMode.SOLID, Color.lightGray));
  }

  // EFFECT: Set's this cell's activation to true
  // Activate this cell
  void activate() {
    if (!flagged) {
      activated = true;

      if (this.contents.is0()) {
        floodFill();
      }
    }
  }

  // EFFECT: Activates the cell's neighbors
  // Activates all neighbors
  void floodFill() {
    for (int i = 0; i < neighbors.size(); i += 1) {
      if (!neighbors.get(i).activated) {
        neighbors.get(i).activate();
      }
    }
  }

  // EFFECT: this.flagged now equals true
  // Flags this cell
  void flag() {
    flagged = !flagged;
  }

  // Returns true if this cell has a mine and is activated
  boolean mineTripped() {
    return this.contents.isMine() && this.activated;
  }

}
