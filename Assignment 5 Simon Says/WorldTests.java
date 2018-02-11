// This file contains tests for the Worlds

import java.awt.Color;

import javalib.funworld.*;
import javalib.worldimages.CircleImage;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.OverlayImage;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.TextImage;
import tester.Tester;

// To store examples and tests for the ColorList file
class ExamplesColors {
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
  
  // InteractWorld example
  World iw0 = new IntroWorld();
  
  // EndWorld example
  World ew0 = new LostWorld("You lost! Score: 0");
  
  // InteractWorld examples
  World interact0 = new InteractWorld(new MtLoColor(), new MtLoColor(), new MtLoColor(), 0);
  World interact1 = new InteractWorld(new ConsLoColor(Color.RED, new MtLoColor()), new MtLoColor(), new MtLoColor(), 0);
  World interact2 = new InteractWorld(new ConsLoColor(Color.CYAN, new MtLoColor()), new MtLoColor(), new MtLoColor(), 0);
  World interact3 = new InteractWorld(new ConsLoColor(Color.BLUE, new MtLoColor()), new MtLoColor(), new MtLoColor(), 0);
  World interact4 = new InteractWorld(new ConsLoColor(Color.GREEN, new MtLoColor()), new MtLoColor(), new MtLoColor(), 0);
  
  // MovieWorld examples
  World mw0 = new MovieWorld(new ConsLoColor(Color.RED, new MtLoColor()), 1);
  World mw1 = new MovieWorld(new ConsLoColor(Color.CYAN, new ConsLoColor(Color.RED, new MtLoColor())), 2);
  World mw2 = new MovieWorld(new ConsLoColor(Color.BLUE, new MtLoColor()), 1);
  World mw3 = new MovieWorld(new ConsLoColor(Color.GREEN, new MtLoColor()), 1);
  World mw4 = new MovieWorld(new ConsLoColor(Color.RED, new MtLoColor()), new ConsLoColor(Color.RED, new MtLoColor()), 1, 1);
  
  // Tests makeScene() for each World type
  boolean testMakeScene(Tester t) {
    Utils u = new Utils();
    return 
        // Testing IntroWorld.makeScene()
        t.checkExpect(iw0.makeScene(), new WorldScene(500, 500).placeImageXY(
        new OverlayImage(new TextImage("Start", 50, Color.WHITE),
            new RectangleImage(250, 150, OutlineMode.SOLID, Color.BLACK)),
        250, 250))
        
        // Testing EndWorld.makeScene()
        && t.checkExpect(ew0.makeScene(),
            new WorldScene(500,
                500).placeImageXY(new TextImage("You lost! Score: 0", 50, Color.GRAY), 250,
                    250))
        
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
        
        && t.checkExpect(interact3.makeScene(), new WorldScene(500, 500)
            .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.RED), 200, 200)
            .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.CYAN), 300,
                200)
            .placeImageXY(new CircleImage(30, OutlineMode.SOLID, u.transparent(Color.BLUE)), 200, 300)
            .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.GREEN), 300, 300))
        
        && t.checkExpect(interact4.makeScene(), new WorldScene(500, 500)
            .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.RED), 200, 200)
            .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.CYAN), 300,
                200)
            .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.BLUE), 200, 300)
            .placeImageXY(new CircleImage(30, OutlineMode.SOLID, u.transparent(Color.GREEN)), 300, 300))
        
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
        
        && t.checkExpect(mw2.makeScene(), new WorldScene(500, 500)
            .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.RED), 200, 200)
            .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.CYAN), 300,
                200)
            .placeImageXY(new CircleImage(30, OutlineMode.SOLID, u.transparent(Color.BLUE)), 200, 300)
            .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.GREEN), 300, 300))
        
        && t.checkExpect(mw3.makeScene(), new WorldScene(500, 500)
            .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.RED), 200, 200)
            .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.CYAN), 300,
                200)
            .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.BLUE), 200, 300)
            .placeImageXY(new CircleImage(30, OutlineMode.SOLID, u.transparent(Color.GREEN)), 300, 300))
        
        && t.checkExpect(mw4.makeScene(),
            new WorldScene(500, 500)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.RED), 200, 200)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.CYAN), 300, 200)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.BLUE), 200, 300)
                .placeImageXY(new CircleImage(30, OutlineMode.SOLID, Color.GREEN), 300, 300));
    }

  // Interactive
  World interact = new InteractWorld(new MtLoColor(), new MtLoColor(), new MtLoColor(), 0);

  // End World
  World lostWorld = new LostWorld("You Lost");
}
