import javalib.funworld.*;
import tester.*;

/*
 * To do list:
 * - Write some damn tests
 * - Implement the random color checking as in instructions
 * - Actually implement easy, medium, and hard levels
 * 
 * 
 */

// Used to initialize the game
class ExamplesGame {

  // Initial world: change these parameters to change the button sizes
  // IntroWorld arguments: new IntroWorld('Easy' button Y value, 'Medium' button
  // Y Value, 'Hard' button Y value, button size)
  World inital = new IntroWorld(100, 250, 400, 100);

  // Initialize the game. bigBang takes in WorldScene X and Y values and a tick
  // rate
  // Perceived tick rate will be half that of the one passed, since this
  // MovieWorld only
  // operates every-other-tick.
  boolean testInitalize(Tester t) {
    return inital.bigBang(500, 500, 0.2);
  }
}
