
import java.awt.Color;
import javalib.worldimages.Posn;

class Utils {

  // Give that color a transparency of a = 70
  Color transparent(Color that) {
    return new Color(that.getRed(), that.getGreen(), that.getBlue(), 70);
  }
  
  // Check if that posn is within the x and y values given and the tolerances given
  boolean posnWithin(Posn that, int x, int y, int xTol, int yTol) {
    return Math.abs(that.x - x) <= xTol && Math.abs(that.y - y) <= yTol; 
  }
}
