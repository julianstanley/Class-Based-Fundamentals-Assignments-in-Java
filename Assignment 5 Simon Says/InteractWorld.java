
// This file contains the world that the user can interact with

import java.awt.Color;
import javalib.funworld.World;
import javalib.funworld.WorldScene;
import javalib.worldimages.CircleImage;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.Posn;
import java.util.Random;

/*
 * An InteractWorld does one of three things:
 * 1. If the user clicks somewhere, see if they clicked the right circle
 * 
 * 2. If the user clicks the right circle, decide whether they need to click more circles. 
 * If they've clicked enough circles, add a new color and send them over to a MovieWorld
 * 
 * 3. If the user clicked the wrong circle, send them to a LostWorld.
 */

// To represent a world where the user can interact with the colored circles
// The Posn values are never constructed, but they are useful for code-readability in the
// mouse pressed method
class InteractWorld extends World {
  ILoColor selectedColor;
  ILoColor matchColors;
  ILoColor allColors;
  Posn redPosn;
  Posn cyanPosn;
  Posn bluePosn;
  Posn greenPosn;
  int circleSize;
  int winNum;
  Random rand;

  // Main constructor: Set the color locations to a predetermined location, but
  // let the game
  // (usually a MovieWorld) pass in the color sequence that the user needs to
  // match
  InteractWorld(ILoColor selectedColor, ILoColor matchColors, ILoColor allColors, int winNum) {
    this(selectedColor, matchColors, allColors, winNum, new Random());
  }

  // For testing: accept a random seed
  InteractWorld(ILoColor selectedColor, ILoColor matchColors, ILoColor allColors, int winNum,
      Random rand) {
    this.selectedColor = selectedColor;
    this.matchColors = matchColors;
    this.allColors = allColors;
    this.redPosn = new Posn(200, 200);
    this.cyanPosn = new Posn(300, 200);
    this.bluePosn = new Posn(200, 300);
    this.greenPosn = new Posn(300, 300);
    this.circleSize = 30;
    this.winNum = winNum;
    this.rand = rand;
  }

  // Create a WorldScene from this World
  public WorldScene makeScene() {
    WorldScene s = this.getEmptyScene();
    Utils u = new Utils();

    // Red circle is selected
    if (selectedColor.startsWith(new ConsLoColor(Color.RED, new MtLoColor()))) {
      return s
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, u.transparent(Color.RED)), 200, 200)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.CYAN), 300, 200)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.BLUE), 200, 300)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.GREEN), 300, 300);
    }

    // Cyan circle is selected
    else if (selectedColor.startsWith(new ConsLoColor(Color.CYAN, new MtLoColor()))) {
      return s.placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.RED), 200, 200)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, u.transparent(Color.CYAN)), 300, 200)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.BLUE), 200, 300)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.GREEN), 300, 300);
    }

    // Blue circle is selected
    else if (selectedColor.startsWith(new ConsLoColor(Color.BLUE, new MtLoColor()))) {
      return s.placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.RED), 200, 200)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.CYAN), 300, 200)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, u.transparent(Color.BLUE)), 200, 300)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.GREEN), 300, 300);
    }

    // Green circle is selected
    else if (selectedColor.startsWith(new ConsLoColor(Color.GREEN, new MtLoColor()))) {
      return s.placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.RED), 200, 200)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.CYAN), 300, 200)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.BLUE), 200, 300).placeImageXY(
              new CircleImage(30, OutlineMode.SOLID, u.transparent(Color.GREEN)), 300, 300);
    }

    // If we don't have any colors to display, just hang out with no circles
    // selected
    else {
      return s.placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.RED), 200, 200)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.CYAN), 300, 200)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.BLUE), 200, 300)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.GREEN), 300, 300);
    }
  }

  // Respond to a tick by producing a new world from this world
  public World onTick() {

    // If the user doesn't need to press any more colors, pick a random color to
    // add to the queue
    // and pass that queue off to be displayed to the user
    if (this.winNum < 1) {
      int pickNum = this.rand.nextInt(4);
      if (pickNum == 0) {
        return new MovieWorld(this.allColors.append(Color.RED), 1 + this.allColors.countColors());
      }
      else if (pickNum == 1) {
        return new MovieWorld(this.allColors.append(Color.CYAN), 1 + this.allColors.countColors());
      }
      else if (pickNum == 2) {
        return new MovieWorld(this.allColors.append(Color.BLUE), 1 + this.allColors.countColors());
      }

      else {
        return new MovieWorld(this.allColors.append(Color.GREEN), 1 + this.allColors.countColors());
      }

    }

    // If the user still needs to press more colors, we should wait for them to
    // do that
    else {
      return this;
    }
  }

  // Create a new world in response to a mouse click
  public World onMousePressed(Posn pos) {
    Utils u = new Utils();

    // If the user correctly clicked on the red circle
    if (u.posnWithin(pos, this.redPosn.x, this.redPosn.y, this.circleSize, this.circleSize)
        && this.matchColors.startsWith(new ConsLoColor(Color.RED, new MtLoColor()))) {
      return new InteractWorld(new ConsLoColor(Color.RED, new MtLoColor()),
          this.matchColors.removeOne(), this.allColors, this.winNum - 1);
    }

    // If the user correctly clicked on the cyan circle
    else if (u.posnWithin(pos, this.cyanPosn.x, this.cyanPosn.y, this.circleSize, this.circleSize)
        && this.matchColors.startsWith(new ConsLoColor(Color.CYAN, new MtLoColor()))) {
      return new InteractWorld(new ConsLoColor(Color.CYAN, new MtLoColor()),
          this.matchColors.removeOne(), this.allColors, this.winNum - 1);
    }

    // If the user correctly clicked on the blue circle
    else if (u.posnWithin(pos, this.bluePosn.x, this.bluePosn.y, this.circleSize, this.circleSize)
        && this.matchColors.startsWith(new ConsLoColor(Color.BLUE, new MtLoColor()))) {
      return new InteractWorld(new ConsLoColor(Color.BLUE, new MtLoColor()),
          this.matchColors.removeOne(), this.allColors, this.winNum - 1);
    }

    // If the user correctly clicked on the green circle
    else if (u.posnWithin(pos, this.greenPosn.x, this.greenPosn.y, this.circleSize, this.circleSize)
        && this.matchColors.startsWith(new ConsLoColor(Color.GREEN, new MtLoColor()))) {
      return new InteractWorld(new ConsLoColor(Color.GREEN, new MtLoColor()),
          this.matchColors.removeOne(), this.allColors, this.winNum - 1);
    }

    // If the user clicked, but not on any particular circle
    else if (!(u.posnWithin(pos, this.greenPosn.x, this.greenPosn.y, this.circleSize,
        this.circleSize))
        && !(u.posnWithin(pos, this.bluePosn.x, this.bluePosn.y, this.circleSize, this.circleSize))
        && !(u.posnWithin(pos, this.cyanPosn.x, this.cyanPosn.y, this.circleSize, this.circleSize))
        && !(u.posnWithin(pos, this.redPosn.x, this.redPosn.y, this.circleSize, this.circleSize))) {
      return this;
    }

    // If the user didn't correctly click on a circle, but clicked on a circle,
    // then they
    // must have clicked on the wrong circle
    else {
      return this.endOfWorld("You lost!" + " Score: " + (this.allColors.countColors() - 1));
    }
  }

  // Create a new world in response to the mouse being released
  public World onMouseReleased(Posn pos) {
    return new InteractWorld(new MtLoColor(), this.matchColors, this.allColors, this.winNum);
  }

  // End this world by sending the user to a LostWorld
  public World endOfWorld(String msg) {
    return new LostWorld(msg);
  }
}