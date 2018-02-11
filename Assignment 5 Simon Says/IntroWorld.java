
// This file contains the definitions for the title screen

import java.awt.Color;
import java.util.Random;
import javalib.funworld.*;
import javalib.worldimages.*;

// To represent the first world that the user should see
class IntroWorld extends World {
  int easyX;
  int easyY;
  int medX;
  int medY;
  int hardX;
  int hardY;
  int buttonSize;
  int textSize;
  Color startColor;
  int rand; 

  // Set the button X's to some predetermined spot. But let the Y values be up
  // to the
  // game-initialization call
  IntroWorld(int easyY, int medY, int hardY, int buttonSize) {
    this.easyX = 250;
    this.easyY = easyY;
    this.medX = 250;
    this.medY = medY;
    this.hardX = 250;
    this.hardY = hardY;
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
  IntroWorld(int easyY, int medY, int hardY, int buttonSize, int rand) {
    this.easyX = 250;
    this.easyY = easyY;
    this.medX = 250;
    this.medY = medY;
    this.hardX = 250;
    this.hardY = hardY;
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
        .placeImageXY(new OverlayImage(new TextImage("Easy", this.textSize, Color.WHITE),
            new RectangleImage(this.buttonSize, this.buttonSize, OutlineMode.SOLID, Color.BLACK)),
            easyX, easyY)
        .placeImageXY(new OverlayImage(new TextImage("Medium", textSize, Color.WHITE),
            new RectangleImage(this.buttonSize, this.buttonSize, OutlineMode.SOLID, Color.BLACK)),
            medX, medY)
        .placeImageXY(new OverlayImage(new TextImage("Hard", this.textSize, Color.WHITE),
            new RectangleImage(this.buttonSize, this.buttonSize, OutlineMode.SOLID, Color.BLACK)),
            hardX, hardY);
  }

  // Create a new World in response to a mouse click
  public World onMousePressed(Posn pos) {
    Utils u = new Utils();

    // Easy button pressed
    if (u.posnWithin(pos, this.easyX, this.easyY, this.buttonSize / 2, this.buttonSize / 2)) {
      return new InteractWorld(new MtLoColor(), new MtLoColor(), new MtLoColor(), 0);
    }

    // Medium button pressed
    else if (u.posnWithin(pos, this.medX, this.medY, this.buttonSize / 2, this.buttonSize / 2)) {
      return new InteractWorld(new MtLoColor(), new MtLoColor(), new MtLoColor(), 0);
    }

    // Hard button pressed
    else if (u.posnWithin(pos, this.hardX, this.hardY, this.buttonSize / 2, this.buttonSize / 2)) {

      return new InteractWorld(new MtLoColor(), new MtLoColor(), new MtLoColor(), 0);
    }

    else {
      return this;
    }
  }
}