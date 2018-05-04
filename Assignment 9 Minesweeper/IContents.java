import java.awt.Color;
import javalib.worldimages.*;

// To represent anything that could be in a cell
interface IContents {

  // Checks if this cell is a mine.
  boolean isMine();

  // Add's one to the value of the integer in this cell's contents
  void add1();

  // Draws the contents
  OverlayImage drawSelf();

  // Checks if the value of the cell is zero
  boolean is0();
}

// To represent a mine content
class Mine implements IContents {

  // Is this mine a mine?
  // Custom type check
  public boolean isMine() {
    return true;
  }

  // Add a value of one to this mine's mine-count
  public void add1() {
    // A mine is not a number, so adding 1 has no effect
  }

  // Draw this mine
  public OverlayImage drawSelf() {
    return new OverlayImage(new CircleImage(10, OutlineMode.SOLID, Color.RED),
        new OverlayImage(new RectangleImage(30, 30, OutlineMode.OUTLINE, Color.BLACK),
            new RectangleImage(30, 30, OutlineMode.SOLID, Color.GRAY)));
  }

  // Is this mine a zero-value content?
  public boolean is0() {
    return false;
  }
}

// To represent a space that has a number or zero
class Number implements IContents {
  int num;

  Number() {
    this.num = 0;
  }

  // Is this content a mine?
  // Custom type-check
  public boolean isMine() {
    return false;
  }

  // Add a value of one to this number
  public void add1() {
    this.num += 1;
  }

  // Draw this number
  public OverlayImage drawSelf() {
    if (is0()) {
      return new OverlayImage(new RectangleImage(30, 30, OutlineMode.OUTLINE, Color.BLACK),
          new RectangleImage(30, 30, OutlineMode.SOLID, Color.GRAY));
    }
    return new OverlayImage(new TextImage(Integer.toString(this.num), Color.BLUE),
        new OverlayImage(new RectangleImage(30, 30, OutlineMode.OUTLINE, Color.BLACK),
            new RectangleImage(30, 30, OutlineMode.SOLID, Color.GRAY)));
  }

  // Is this number a zero-value content?
  public boolean is0() {
    return this.num == 0;
  }
}