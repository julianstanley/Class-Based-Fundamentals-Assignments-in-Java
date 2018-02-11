
// This file contains tests for the Worlds

import java.awt.Color;

import javalib.funworld.World;
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

  World w = new MovieWorld(new MtLoColor(), new MtLoColor(), 0, 5);
  World wR = new MovieWorld(new ConsLoColor(Color.RED, new MtLoColor()), new MtLoColor(), 1, 5);
  World wRainbow = new MovieWorld(new ConsLoColor(Color.RED, new ConsLoColor(Color.BLUE,
      new ConsLoColor(Color.GREEN, new ConsLoColor(Color.CYAN, new MtLoColor())))), 4);

  // Interactive
  World interact = new InteractWorld(new MtLoColor(), new MtLoColor(), new MtLoColor(), 0);

  // End World
  World lostWorld = new LostWorld("You Lost");
  World wonWorld = new WonWorld("You Won");
}