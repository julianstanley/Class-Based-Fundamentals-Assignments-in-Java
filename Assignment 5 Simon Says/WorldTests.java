
// This file contains tests for the Worlds

import java.awt.Color;
import java.util.Random;

import javalib.funworld.*;
import javalib.worldimages.CircleImage;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.OverlayImage;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.TextImage;
import tester.Tester;

// To store examples and tests for the ColorList file
class ExamplesColors {

  // Example
  ILoColor mt = new MtLoColor();
  ILoColor red = new ConsLoColor(Color.RED, new MtLoColor());
  ILoColor redPink = new ConsLoColor(Color.RED, new ConsLoColor(Color.PINK, new MtLoColor()));
  ILoColor pinkRed = new ConsLoColor(Color.PINK, new ConsLoColor(Color.RED, new MtLoColor()));
  ILoColor rainbow = new ConsLoColor(Color.RED,
      new ConsLoColor(Color.ORANGE, new ConsLoColor(Color.YELLOW, new ConsLoColor(Color.GREEN,
          new ConsLoColor(Color.BLUE, new ConsLoColor(Color.MAGENTA, new MtLoColor()))))));
  ILoColor halfRainbow = new ConsLoColor(Color.RED,
      new ConsLoColor(Color.ORANGE, new ConsLoColor(Color.YELLOW, new MtLoColor())));

  boolean testSequence(Tester t) {
    return t.checkExpect(mt.sameSequence(mt), true) && t.checkExpect(red.sameSequence(red), true)
        && t.checkExpect(red.sameSequence(mt), false) && t.checkExpect(mt.sameSequence(red), false)
        && t.checkExpect(redPink.sameSequence(redPink), true)
        && t.checkExpect(redPink.sameSequence(mt), false)
        && t.checkExpect(redPink.sameSequence(pinkRed), false)
        && t.checkExpect(pinkRed.sameSequence(redPink), false)
        && t.checkExpect(rainbow.sameSequence(rainbow), true)
        && t.checkExpect(halfRainbow.sameSequence(rainbow), false)
        && t.checkExpect(rainbow.sameSequence(halfRainbow), false);
  }

  boolean testEquality(Tester t) {
    return t.checkExpect(mt.startsWith(mt), true) && t.checkExpect(mt.startsWith(red), false)
        && t.checkExpect(red.startsWith(mt), true)
        && t.checkExpect(rainbow.startsWith(halfRainbow), true)
        && t.checkExpect(halfRainbow.startsWith(rainbow), false);
  }
}

// Movie worlds
class ExamplesWorlds {

  // Examples of IntroWorlds
  World iw0 = new IntroWorld();

  // Examples of MovieWorlds

  // Examples of MovieWorlds made with the first constructor
  World mw7 = new MovieWorld(new MtLoColor(), new MtLoColor(), 0);
  World mw8 = new MovieWorld(new MtLoColor(),
      new ConsLoColor(Color.RED,
          new ConsLoColor(Color.ORANGE,
              new ConsLoColor(Color.YELLOW, new ConsLoColor(Color.GREEN,
                  new ConsLoColor(Color.BLUE, new ConsLoColor(Color.MAGENTA, new MtLoColor())))))),
      0);
  World mw9 = new MovieWorld(
      new ConsLoColor(Color.RED,
          new ConsLoColor(Color.ORANGE, new ConsLoColor(Color.YELLOW,
              new ConsLoColor(Color.GREEN,
                  new ConsLoColor(Color.BLUE, new ConsLoColor(Color.MAGENTA, new MtLoColor())))))),
      new ConsLoColor(Color.RED,
          new ConsLoColor(Color.ORANGE,
              new ConsLoColor(Color.YELLOW, new ConsLoColor(Color.GREEN,
                  new ConsLoColor(Color.BLUE, new ConsLoColor(Color.MAGENTA, new MtLoColor())))))),
      6);

  // Examples of MovieWorlds made with the second constructor
  World mw0 = new MovieWorld(new ConsLoColor(Color.RED, new MtLoColor()), 1);
  World mw1 = new MovieWorld(
      new ConsLoColor(Color.CYAN, new ConsLoColor(Color.RED, new MtLoColor())), 2);
  World mw2 = new MovieWorld(new ConsLoColor(Color.BLUE, new MtLoColor()), 1);
  World mw3 = new MovieWorld(new ConsLoColor(Color.GREEN, new MtLoColor()), 1);

  // Examples of MovieWorlds made with the third constructor
  World mw4 = new MovieWorld(new ConsLoColor(Color.RED, new MtLoColor()),
      new ConsLoColor(Color.RED, new MtLoColor()), 1, 1);
  World mw5 = new MovieWorld(new ConsLoColor(Color.RED, new MtLoColor()),
      new ConsLoColor(Color.RED, new MtLoColor()), 1, 2);
  World mw6 = new MovieWorld(new ConsLoColor(Color.RED, new MtLoColor()),
      new ConsLoColor(Color.RED, new MtLoColor()), 2, 1);

  // Examples of InteractWorlds

  // Examples of InteractWorlds made with the first constructor
  World interact0 = new InteractWorld(new MtLoColor(), new MtLoColor(), new MtLoColor(), 0);
  World interact1 = new InteractWorld(new ConsLoColor(Color.RED, new MtLoColor()), new MtLoColor(),
      new MtLoColor(), 0);
  World interact2 = new InteractWorld(new ConsLoColor(Color.CYAN, new MtLoColor()), new MtLoColor(),
      new MtLoColor(), 0);
  World interact3 = new InteractWorld(new ConsLoColor(Color.BLUE, new MtLoColor()), new MtLoColor(),
      new MtLoColor(), 0);
  World interact4 = new InteractWorld(new ConsLoColor(Color.GREEN, new MtLoColor()),
      new MtLoColor(), new MtLoColor(), 0);
  World interact5 = new InteractWorld(new MtLoColor(), new ConsLoColor(Color.RED, new MtLoColor()),
      new ConsLoColor(Color.RED, new MtLoColor()), 1);
  World interact6 = new InteractWorld(new MtLoColor(), new ConsLoColor(Color.CYAN, new MtLoColor()),
      new ConsLoColor(Color.CYAN, new MtLoColor()), 1);
  World interact7 = new InteractWorld(new MtLoColor(), new ConsLoColor(Color.BLUE, new MtLoColor()),
      new ConsLoColor(Color.BLUE, new MtLoColor()), 1);
  World interact8 = new InteractWorld(new MtLoColor(),
      new ConsLoColor(Color.GREEN, new MtLoColor()), new ConsLoColor(Color.GREEN, new MtLoColor()),
      1);

  // Examples of InteractWorlds made with the second constructor
  World interact9 = new InteractWorld(new MtLoColor(), new MtLoColor(), new MtLoColor(), 0,
      new Random(1));
  World interact10 = new InteractWorld(new MtLoColor(), new MtLoColor(), new MtLoColor(), 10,
      new Random(1));

  // Tests makeScene() for each World type
  boolean testMakeScene(Tester t) {
    Utils u = new Utils();
    return
    // Testing IntroWorld.makeScene()
    t.checkExpect(iw0.makeScene(),
        new WorldScene(500, 500)
            .placeImageXY(new OverlayImage(new TextImage("Start", 50, Color.WHITE),
                new RectangleImage(250, 150, OutlineMode.SOLID, Color.BLACK)), 250, 250))

        // Testing InteractWorld.makeScene()
        && t.checkExpect(interact0.makeScene(),
            new WorldScene(500, 500)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.RED), 200, 200)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.CYAN), 300, 200)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.BLUE), 200, 300)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.GREEN), 300, 300))

        && t.checkExpect(interact1.makeScene(),
            new WorldScene(500, 500)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, u.transparent(Color.RED)), 200,
                    200)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.CYAN), 300, 200)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.BLUE), 200, 300)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.GREEN), 300, 300))

        && t.checkExpect(interact2.makeScene(), new WorldScene(500, 500)
            .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.RED), 200, 200)
            .placeImageXY(new CircleImage(30, OutlineMode.SOLID, u.transparent(Color.CYAN)), 300,
                200)
            .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.BLUE), 200, 300)
            .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.GREEN), 300, 300))

        && t.checkExpect(interact3.makeScene(),
            new WorldScene(500, 500)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.RED), 200, 200)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.CYAN), 300, 200)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, u.transparent(Color.BLUE)),
                    200, 300)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.GREEN), 300, 300))

        && t.checkExpect(interact4.makeScene(),
            new WorldScene(500, 500)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.RED), 200, 200)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.CYAN), 300, 200)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.BLUE), 200, 300)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, u.transparent(Color.GREEN)),
                    300, 300))

        // Testing MovieWorld.makeScene()
        && t.checkExpect(mw0.makeScene(),
            new WorldScene(500, 500)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, u.transparent(Color.RED)), 200,
                    200)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.CYAN), 300, 200)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.BLUE), 200, 300)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.GREEN), 300, 300))

        && t.checkExpect(mw1.makeScene(), new WorldScene(500, 500)
            .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.RED), 200, 200)
            .placeImageXY(new CircleImage(30, OutlineMode.SOLID, u.transparent(Color.CYAN)), 300,
                200)
            .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.BLUE), 200, 300)
            .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.GREEN), 300, 300))

        && t.checkExpect(mw2.makeScene(),
            new WorldScene(500, 500)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.RED), 200, 200)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.CYAN), 300, 200)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, u.transparent(Color.BLUE)),
                    200, 300)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.GREEN), 300, 300))

        && t.checkExpect(mw3.makeScene(),
            new WorldScene(500, 500)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.RED), 200, 200)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.CYAN), 300, 200)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.BLUE), 200, 300)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, u.transparent(Color.GREEN)),
                    300, 300))

        && t.checkExpect(mw4.makeScene(),
            new WorldScene(500, 500)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.RED), 200, 200)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.CYAN), 300, 200)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.BLUE), 200, 300)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.GREEN), 300, 300));
  }

  // Tests onMousePressed(Posn pos) for the world types that implement it
  boolean testOnMousePressed(Tester t) {
    return
    // Tests IntroWorld.onMousePressed(Posn pos)
    t.checkExpect(iw0.onMousePressed(new Posn(250, 250)),
        new InteractWorld(new MtLoColor(), new MtLoColor(), new MtLoColor(), 0))
        && t.checkExpect(iw0.onMousePressed(new Posn(300, 200)),
            new InteractWorld(new MtLoColor(), new MtLoColor(), new MtLoColor(), 0))
        && t.checkExpect(iw0.onMousePressed(new Posn(326, 250)), iw0)

        // Tests InteractWorld.onMousePressed for successful clicks
        && t.checkExpect(interact5.onMousePressed(new Posn(200, 200)),
            new InteractWorld(new ConsLoColor(Color.RED, new MtLoColor()), new MtLoColor(),
                new ConsLoColor(Color.RED, new MtLoColor()), 0))
        && t.checkExpect(interact6.onMousePressed(new Posn(300, 200)),
            new InteractWorld(new ConsLoColor(Color.CYAN, new MtLoColor()), new MtLoColor(),
                new ConsLoColor(Color.CYAN, new MtLoColor()), 0))
        && t.checkExpect(interact7.onMousePressed(new Posn(200, 300)),
            new InteractWorld(new ConsLoColor(Color.BLUE, new MtLoColor()), new MtLoColor(),
                new ConsLoColor(Color.BLUE, new MtLoColor()), 0))
        && t.checkExpect(interact8.onMousePressed(new Posn(300, 300)),
            new InteractWorld(new ConsLoColor(Color.GREEN, new MtLoColor()), new MtLoColor(),
                new ConsLoColor(Color.GREEN, new MtLoColor()), 0))

        // Tests InteractWorld.onMousePressed for unsuccessful clicks
        && t.checkExpect(interact5.onMousePressed(new Posn(300, 300)),
            interact5.endOfWorld("You lost!" + " Score: " + 0))
        && t.checkExpect(interact5.onMousePressed(new Posn(0, 0)), interact5);
  }

  // Tests InteractWorld.onMouseReleased(Posn pos)
  boolean testOnMouseReleased(Tester t) {
    return t.checkExpect(interact1.onMouseReleased(new Posn(200, 200)),
        new InteractWorld(new MtLoColor(), new MtLoColor(), new MtLoColor(), 0));
  }

  // Tests InteractWorld.lastScene
  boolean testLastScene(Tester t) {
    return t.checkExpect(interact1.lastScene("Oopsies"),
        new WorldScene(500, 500).placeImageXY(new TextImage("Oopsies", 50, Color.GRAY), 250, 250));
  }

  // Tests for on-tick
  boolean testOnTick(Tester t) {
    return
    // IntroWorld.ontick() does not exist

    // Tests on MovieWorld.onTick()
    t.checkExpect(mw5.onTick(),
        new MovieWorld(new ConsLoColor(Color.RED, new MtLoColor()),
            new ConsLoColor(Color.RED, new MtLoColor()), 1, 3))
        && t.checkExpect(mw6.onTick(),
            new MovieWorld(new MtLoColor(), new ConsLoColor(Color.RED, new MtLoColor()), 1, 2))
        && t.checkExpect(mw5.onTick().onTick(),
            new InteractWorld(new MtLoColor(), new ConsLoColor(Color.RED, new MtLoColor()),
                new ConsLoColor(Color.RED, new MtLoColor()), 1))

        // tests on InteractWorld
        && t.checkExpect(interact9.onTick(),
            new MovieWorld(new ConsLoColor(Color.BLUE, new MtLoColor()), 1))
        && t.checkExpect(interact10.onTick(), interact10);

  }

  // Tests for Utils
  boolean testUtils(Tester t) {
    Utils u = new Utils();
    return t.checkExpect(u.transparent(Color.RED), new Color(255, 0, 0, 70))
        && t.checkExpect(u.posnWithin(new Posn(0, 0), 0, 0, 0, 0), true)
        && t.checkExpect(u.posnWithin(new Posn(0, 0), 1, 1, 0, 0), false)
        && t.checkExpect(u.posnWithin(new Posn(0, 0), 1, 1, 1, 1), true);
  }

}
