
// This file contains the definitions for the title screen

import java.awt.Color;
import javalib.funworld.*;
import javalib.worldimages.*;

// To represent the first world that the user should see
class IntroWorld extends World {


  // Make a WorldScene based on this World
  public WorldScene makeScene() {
    WorldScene s = this.getEmptyScene();

    return s.placeImageXY(
        new OverlayImage(new TextImage("Start", 50, Color.WHITE),
            new RectangleImage(250, 150, OutlineMode.SOLID, Color.BLACK)),
        250, 250);
  }

  // Create a new World in response to a mouse click
  public World onMousePressed(Posn pos) {
    Utils u = new Utils();

    // Button pressed
    if (u.posnWithin(pos, 250, 250, 75, 75)) {
      return new InteractWorld(new MtLoColor(), new MtLoColor(), new MtLoColor(), 0);
    }
    else {
      return this;
    }
  }
}