
// This file contains the world that displays color sequences to the user

import java.awt.Color;
import javalib.funworld.World;
import javalib.funworld.WorldScene;
import javalib.worldimages.CircleImage;
import javalib.worldimages.OutlineMode;

/*
 * A MovieWorld does one of three things:
 * 1. If there is a color sequence to display: display the user that color sequence
 * 
 * 2. If there are no colors to display: pass the user along to an interactive world
 * 
 * 3. If the color sequence is long enough, the user has won, so send them to a winning endWorld
 */

// To represent a world where the user is shown a sequence of colors
class MovieWorld extends World {
  ILoColor colors;
  ILoColor allColors;
  int colorCount;
  int tick;

  // Main constructor: pass in the initial colors and the amount of colors to be
  // shown,
  // Initialize tick to 0
  MovieWorld(ILoColor colors, ILoColor allColors, int colorCount) {
    this.colors = colors;
    this.allColors = allColors;
    this.colorCount = colorCount;
    this.tick = 0;

  }

  // Convenience constructor: if colors and allColors are the (colors get
  // smaller as we iterate, but
  // allColors stays intact so we can send the full list of colors to the
  // InteractWorld
  MovieWorld(ILoColor colors, int colorCount) {
    this.colors = colors;
    this.allColors = colors;
    this.colorCount = colorCount;
    this.tick = 0;

  }

  // Convenience constructor: like the main constructor, but takes in a tick so
  // we can modify the
  // tick with each iteration.
  MovieWorld(ILoColor colors, ILoColor allColors, int colorCount, int tick) {
    this.colors = colors;
    this.allColors = allColors;
    this.colorCount = colorCount;
    this.tick = tick;
  }

  // Creates a WorldScene from this scene
  public WorldScene makeScene() {
    WorldScene s = this.getEmptyScene();
    Utils u = new Utils();

    if (tick % 2 == 1) {
      return s.placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.RED), 200, 200)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.CYAN), 300, 200)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.BLUE), 200, 300)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.GREEN), 300, 300);
    }
    else if (colors.startsWith(new ConsLoColor(Color.RED, new MtLoColor()))) {
      return s
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, u.transparent(Color.RED)), 200, 200)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.CYAN), 300, 200)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.BLUE), 200, 300)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.GREEN), 300, 300);
    }
    else if (colors.startsWith(new ConsLoColor(Color.CYAN, new MtLoColor()))) {
      return s.placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.RED), 200, 200)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, u.transparent(Color.CYAN)), 300, 200)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.BLUE), 200, 300)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.GREEN), 300, 300);
    }
    else if (colors.startsWith(new ConsLoColor(Color.BLUE, new MtLoColor()))) {
      return s.placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.RED), 200, 200)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.CYAN), 300, 200)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, u.transparent(Color.BLUE)), 200, 300)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.GREEN), 300, 300);
    }

    else if (colors.startsWith(new ConsLoColor(Color.GREEN, new MtLoColor()))) {
      return s.placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.RED), 200, 200)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.CYAN), 300, 200)
          .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.BLUE), 200, 300).placeImageXY(
              new CircleImage(30, OutlineMode.SOLID, u.transparent(Color.GREEN)), 300, 300);
    }

    // In proper game-play, all options should be covered by the above options.
    // This error should only be thrown during development, never during
    // game-play.
    else {
      throw new IllegalArgumentException(
          "Development error: simon says couldn't find a color to display!");
    }
  }

  // Respond to a tick by producing a new world from this world
  public World onTick() {
    // If your color list is long enough, then you win!
    if (this.colorCount > 5) {
      return this.endOfWorld("You've won!");
    }

    // Only do things on every other tick. This makes the color sequence more
    // clear if two of the same color are being shown in sequence
    else if (tick % 2 == 0) {
      return new MovieWorld(this.colors, this.allColors, this.colorCount, this.tick + 1);
    }

    // If you still have colors in your list, go ahead and show them
    else if (this.colorCount > 1) {
      return new MovieWorld(this.colors.removeOne(), this.allColors, this.colorCount - 1,
          this.tick + 1);
    }

    // If you don't have colors in your list, you can't do anything else, so you
    // should send the player to an interactive world
    else {
      return new InteractWorld(new MtLoColor(), this.allColors, this.allColors,
          this.allColors.countColors());
    }
  }

  // End this world by sending the user to a WonWorld
  public World endOfWorld(String str) {
    return new WonWorld(str);
  }
}
