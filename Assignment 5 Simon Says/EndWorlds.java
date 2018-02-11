
// This file contains the worlds that represent winning and losing

import java.awt.Color;
import javalib.funworld.World;
import javalib.funworld.WorldScene;
import javalib.worldimages.TextImage;

// To represent any end-of-game world
abstract class EndWorld extends World {
  String msg;

  EndWorld(String msg) {
    this.msg = msg;
  }
  
  /*
   * Fields:
   * ...msg...  -- String
   * 
   * Methods:
   * ...this.makeScene()... -- WorldScene
   * 
   * Methods on Fields:
   * 
   */

  // Template: same as class
  // Create a WorldScene based on this world
  public WorldScene makeScene() {
    WorldScene s = this.getEmptyScene();
    return s.placeImageXY(new TextImage(this.msg, 50, Color.GRAY),
        250, 250);
  }
}

// To represent a world in which the user has lost ):
class LostWorld extends EndWorld {
  String msg;

  LostWorld(String msg) {
    super(msg);
  }
  
  /*
   * Fields:
   * ...msg...  -- String
   * 
   * Methods:
   * ...this.makeScene()... -- WorldScene
   * 
   * Methods on Fields:
   * 
   */
}
