
// This file contains the definitions for the title screen

import java.awt.Color;
import java.util.Random;
import javalib.funworld.*;
import javalib.worldimages.*;

// To represent the first world that the user should see
class IntroWorld extends World {
  int buttonX;
  int buttonY;
  int buttonSize;
  int textSize;
  Color startColor;
  int rand; 

  // Set the button X's to some predetermined spot. But let the Y values be up
  // to the
  // game-initialization call
  IntroWorld(int buttonY, int buttonSize) {
    this.buttonX = 250;
    this.buttonY = buttonY;
    this.buttonSize = buttonSize;
    this.textSize = buttonSize / 4;

    // Pick a random color. We'll use this later to start the game
    Random r = new Random();
    int pickNum = r.nextInt(4);
    if (pickNum == 0) {
      this.startColor = Color.RED;
    }
    else if (pickNum == 1) {
      this.startColor = Color.CYAN;
    }
    else if (pickNum == 2) {
      this.startColor = Color.BLUE;
    }

    else {
      this.startColor = Color.GREEN;
    }
  }
  
  // Separate constructor used to test random
  IntroWorld(int buttonY, int buttonSize, int rand) {
    this.buttonX = 250;
    this.buttonY = buttonY;
    this.buttonSize = buttonSize;
    this.textSize = buttonSize / 4;
    this.rand = rand; 

    // Pick a random color. We'll use this later to start the game
    Random r = new Random(rand);
    int pickNum = r.nextInt(4);
    if (pickNum == 0) {
      this.startColor = Color.RED;
    }
    else if (pickNum == 1) {
      this.startColor = Color.CYAN;
    }
    else if (pickNum == 2) {
      this.startColor = Color.BLUE;
    }

    else {
      this.startColor = Color.GREEN;
    }
  }

  // Make a WorldScene based on this World
  public WorldScene makeScene() {
    WorldScene s = this.getEmptyScene();

    return s
        .placeImageXY(new OverlayImage(new TextImage("Start", this.textSize, Color.WHITE),
            new RectangleImage(this.buttonSize, this.buttonSize, OutlineMode.SOLID, Color.BLACK)),
            buttonX, buttonY);
  }

  // Create a new World in response to a mouse click
  public World onMousePressed(Posn pos) {
    Utils u = new Utils();

    // Button pressed
    if (u.posnWithin(pos, this.buttonX, this.buttonY, this.buttonSize / 2, this.buttonSize / 2)) {
      return new InteractWorld(new MtLoColor(), new MtLoColor(), new MtLoColor(), 0);
    }
    else {
      return this;
    }
  }
}