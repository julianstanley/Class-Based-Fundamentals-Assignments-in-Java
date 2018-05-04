import tester.Tester;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import javalib.impworld.WorldScene;
import javalib.worldimages.BesideImage;
import javalib.worldimages.Posn;
import javalib.worldimages.TextImage;

class ExamplesMines {

  // Used for setting specific mine locations
  ArrayList<Boolean> fiveMines = new ArrayList<Boolean>(Arrays.asList(true, true, true, false, true,
      false, false, false, false, false, false, false, false, false, false, false, false, false,
      false, false, false, false, false, false, false));

  // We have a game
  MineGame game1;

  // This initialization is a normal game
  void init() {
    game1 = new MineGame(20, 20, 50);
  }

  // This initialization uses fiveMines to set its mine locations
  void initSpecific() {
    game1 = new MineGame(5, 5, 5, fiveMines);
  }

  // This initialization has no mines
  void initNone() {
    game1 = new MineGame(5, 5, 0);
  }

  void initTesting() {
    game1 = new MineGame(5, 5, 5, fiveMines, true);
  }

  // Tests the number of mines in a real game
  void testInit(Tester t) {
    init();
    int numMines = 0;
    for (ArrayList<Cell> i : game1.cells) {
      for (Cell j : i) {
        if (j.hasMine()) {
          numMines += 1;
        }
      }
    }
    t.checkExpect(numMines, game1.numMines);
  }

  // Run the game with this test
  void testBigBang(Tester t) {
    init();
    int worldWidth = game1.columns * 30;
    int worldHeight = game1.rows * 30;
    game1.bigBang(worldWidth, worldHeight, 1);
  }

  void testHasMine(Tester t) {
    initSpecific();
    Cell first = game1.cells.get(0).get(0);
    t.checkExpect(first.hasMine(), true);
    Cell tenth = game1.cells.get(4).get(2);
    t.checkExpect(tenth.hasMine(), false);
  }

  void testSetMine(Tester t) {
    initNone();
    Cell first = game1.cells.get(0).get(0);
    t.checkExpect(first.hasMine(), false);
    first.setMine();
    t.checkExpect(first.hasMine(), true);
  }

  void testInitMines(Tester t) {
    initTesting();
    Cell first = game1.cells.get(0).get(0);
    t.checkExpect(first.hasMine(), false);
    game1.initMines();
    t.checkExpect(first.hasMine(), true);
    Cell tenth = game1.cells.get(4).get(2);
    t.checkExpect(tenth.hasMine(), false);
  }

  void testInitNeighbors(Tester t) {
    initTesting();
    // Three neighbors
    Cell first = game1.cells.get(0).get(0);
    ArrayList<Cell> firstNeighbors = first.neighbors;
    Cell second = game1.cells.get(1).get(0);
    ArrayList<Cell> secondNeighbors = second.neighbors;
    Cell third = game1.cells.get(1).get(1);
    ArrayList<Cell> thirdNeighbors = third.neighbors;

    t.checkExpect(firstNeighbors.size(), 0);
    t.checkExpect(secondNeighbors.size(), 0);
    t.checkExpect(thirdNeighbors.size(), 0);

    t.checkExpect(firstNeighbors.contains(game1.cells.get(0).get(1)), false);
    game1.initNeighbors();

    t.checkExpect(firstNeighbors.size(), 3);
    t.checkExpect(firstNeighbors.contains(game1.cells.get(0).get(1)), true);
    t.checkExpect(firstNeighbors.contains(game1.cells.get(1).get(1)), true);
    t.checkExpect(firstNeighbors.contains(game1.cells.get(1).get(0)), true);

    // Five neighbors

    t.checkExpect(secondNeighbors.size(), 5);
    t.checkExpect(secondNeighbors.contains(game1.cells.get(0).get(0)), true);
    t.checkExpect(secondNeighbors.contains(game1.cells.get(0).get(1)), true);
    t.checkExpect(secondNeighbors.contains(game1.cells.get(1).get(1)), true);
    t.checkExpect(secondNeighbors.contains(game1.cells.get(2).get(1)), true);
    t.checkExpect(secondNeighbors.contains(game1.cells.get(2).get(0)), true);

    // Eight neighbors

    t.checkExpect(thirdNeighbors.size(), 8);
    t.checkExpect(thirdNeighbors.contains(game1.cells.get(0).get(0)), true);
    t.checkExpect(thirdNeighbors.contains(game1.cells.get(1).get(0)), true);
    t.checkExpect(thirdNeighbors.contains(game1.cells.get(2).get(0)), true);
    t.checkExpect(thirdNeighbors.contains(game1.cells.get(2).get(1)), true);
    t.checkExpect(thirdNeighbors.contains(game1.cells.get(2).get(2)), true);
    t.checkExpect(thirdNeighbors.contains(game1.cells.get(1).get(2)), true);
    t.checkExpect(thirdNeighbors.contains(game1.cells.get(0).get(2)), true);
    t.checkExpect(thirdNeighbors.contains(game1.cells.get(0).get(1)), true);
  }

  void testAddNeighbor(Tester t) {
    init();
    Cell first = game1.cells.get(0).get(0);
    ArrayList<Cell> firstNeighbors = first.neighbors;
    t.checkExpect(firstNeighbors.contains(game1.cells.get(0).get(3)), false);
    first.addNeighbor(game1.cells.get(0).get(3));
    t.checkExpect(firstNeighbors.contains(game1.cells.get(0).get(3)), true);
  }

  void testNeighbors(Tester t) {
    // Three neighbors
    init();
    Cell first = game1.cells.get(0).get(0);
    ArrayList<Cell> firstNeighbors = first.neighbors;

    t.checkExpect(firstNeighbors.size(), 3);
    t.checkExpect(firstNeighbors.contains(game1.cells.get(0).get(1)), true);
    t.checkExpect(firstNeighbors.contains(game1.cells.get(1).get(1)), true);
    t.checkExpect(firstNeighbors.contains(game1.cells.get(1).get(0)), true);

    // Five neighbors
    Cell second = game1.cells.get(1).get(0);
    ArrayList<Cell> secondNeighbors = second.neighbors;

    t.checkExpect(secondNeighbors.size(), 5);
    t.checkExpect(secondNeighbors.contains(game1.cells.get(0).get(0)), true);
    t.checkExpect(secondNeighbors.contains(game1.cells.get(0).get(1)), true);
    t.checkExpect(secondNeighbors.contains(game1.cells.get(1).get(1)), true);
    t.checkExpect(secondNeighbors.contains(game1.cells.get(2).get(1)), true);
    t.checkExpect(secondNeighbors.contains(game1.cells.get(2).get(0)), true);

    // Eight neighbors
    Cell third = game1.cells.get(1).get(1);
    ArrayList<Cell> thirdNeighbors = third.neighbors;

    t.checkExpect(thirdNeighbors.size(), 8);
    t.checkExpect(thirdNeighbors.contains(game1.cells.get(0).get(0)), true);
    t.checkExpect(thirdNeighbors.contains(game1.cells.get(1).get(0)), true);
    t.checkExpect(thirdNeighbors.contains(game1.cells.get(2).get(0)), true);
    t.checkExpect(thirdNeighbors.contains(game1.cells.get(2).get(1)), true);
    t.checkExpect(thirdNeighbors.contains(game1.cells.get(2).get(2)), true);
    t.checkExpect(thirdNeighbors.contains(game1.cells.get(1).get(2)), true);
    t.checkExpect(thirdNeighbors.contains(game1.cells.get(0).get(2)), true);
    t.checkExpect(thirdNeighbors.contains(game1.cells.get(0).get(1)), true);
  }

  void testScramble(Tester t) {
    Utils u = new Utils();

    ArrayList<Integer> twenty = new ArrayList<Integer>(
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
    ArrayList<Integer> twentyS = u.scramble(twenty);
    t.checkExpect(twenty.equals(twentyS), false);

    t.checkExpect(twenty.size(), twentyS.size());

    for (int num : twenty) {
      t.checkExpect(twentyS.contains(num), true);
    }
  }

  void testScrambleList(Tester t) {
    Utils u = new Utils();

    ArrayList<Boolean> testScramble = u.scrambledList(10, 30);

    // Check to make sure the scrambled list has the correct number of true and
    // false
    for (int i = 0; i < 10; i += 1) {
      t.checkExpect(testScramble.remove(testScramble.indexOf(true)), true);
    }

    for (int i = 0; i < (30 - 10); i += 1) {
      t.checkExpect(testScramble.remove(testScramble.indexOf(false)), false);
    }

  }

  void testActivate(Tester t) {
    init();

    Cell first = game1.cells.get(0).get(0);
    t.checkExpect(first.activated, false);

    // Should not activate a flagged cell
    first.flagged = true;
    first.activate();
    t.checkExpect(first.activated, false);

    // Should activate a non-flagged cell
    first.flagged = false;
    first.activate();
    t.checkExpect(first.activated, true);
  }

  void testFloodFill(Tester t) {
    init();
    Cell first = game1.cells.get(0).get(0);
    ArrayList<Cell> firstNeighbors = first.neighbors;

    for (int i = 0; i < firstNeighbors.size(); i += 1) {
      if (!firstNeighbors.get(i).activated) {
        t.checkExpect(firstNeighbors.get(i).activated, false);
      }
    }

    first.floodFill();

    for (int i = 0; i < firstNeighbors.size(); i += 1) {
      if (!firstNeighbors.get(i).activated) {
        t.checkExpect(firstNeighbors.get(i).activated, true);
      }
    }
  }

  void testFlagged(Tester t) {
    init();

    Cell first = game1.cells.get(0).get(0);

    // Cell isn't flagged, so let's flag it
    // Also not activated
    t.checkExpect(first.flagged, false);
    t.checkExpect(first.activated, false);
    first.flag();

    // Cell is now flagged, and it can't be activated
    t.checkExpect(first.flagged, true);
    t.checkExpect(first.activated, false);
    first.activate();
    t.checkExpect(first.activated, false);

    // But if un-flag it, now it can be activated
    first.flag();
    t.checkExpect(first.flagged, false);
    t.checkExpect(first.activated, false);
    first.activate();
    t.checkExpect(first.activated, true);

  }

  void testMineTripped(Tester t) {
    initSpecific();
    Cell first = game1.cells.get(3).get(3);

    t.checkExpect(first.hasMine(), false);
    t.checkExpect(first.activated, false);
    t.checkExpect(first.mineTripped(), false);

    first.activate();
    t.checkExpect(first.mineTripped(), false);

    // Reset activation
    first.activated = false;

    // Make first a mine, but mine isn't tripped until it's activated
    first.setMine();
    t.checkExpect(first.mineTripped(), false);
    first.activate();
    t.checkExpect(first.mineTripped(), true);

  }

  void testIsOnMap(Tester t) {
    init();
    t.checkExpect(game1.isOnMap(0, 0), true);
    t.checkExpect(game1.isOnMap(-1, 0), false);
    t.checkExpect(game1.isOnMap(0, -1), false);
    t.checkExpect(game1.isOnMap(game1.columns + 1, 0), false);
    t.checkExpect(game1.isOnMap(0, game1.rows + 1), false);
  }

  void testDrawScore(Tester t) {
    init();
    t.checkExpect(game1.drawScore(),
        new BesideImage(new TextImage("Mines Remaining: ", 20, Color.BLACK),
            new TextImage(Integer.toString(game1.score), 50, Color.BLACK)));

  }

  void testMakeScene(Tester t) {
    init();

    WorldScene s = new WorldScene(game1.columns * 30, game1.rows * 30 + 100);

    s.placeImageXY(game1.drawScore(), game1.columns * 7, 50);
    s.placeImageXY(game1.drawTime(), game1.columns * 23, 50);

    for (int i = 0; i < game1.columns; i++) {
      for (int j = 0; j < game1.rows; j++) {
        s.placeImageXY(game1.cells.get(i).get(j).drawSelf(), i * 30 + 15, j * 30 + 115);
      }
    }

    t.checkExpect(game1.makeScene(), s);
  }

  void testDrawTime(Tester t) {
    init();
    t.checkExpect(game1.drawTime(), new BesideImage(new TextImage("Time: ", 20, Color.BLACK),
        new TextImage(Integer.toString(game1.time), 50, Color.BLACK)));
  }

  void testOnMouseClick(Tester t) {
    initSpecific();
    t.checkExpect(game1.active, true);
    Cell first = game1.cells.get(3).get(3);

    t.checkExpect(first.activated, false);
    t.checkExpect(first.flagged, false);
    game1.onMouseClicked(new Posn(95, 190), "LeftButton");
    t.checkExpect(first.activated, true);
    t.checkExpect(first.flagged, false);

    // If it's activated, we can't flag
    game1.onMouseClicked(new Posn(95, 190), "RightButton");
    t.checkExpect(first.activated, true);
    t.checkExpect(first.flagged, false);

    // Reset activation
    first.activated = false;

    // Now we can flag it
    t.checkExpect(game1.score, game1.numMines);
    t.checkExpect(first.activated, false);
    t.checkExpect(first.flagged, false);
    game1.onMouseClicked(new Posn(95, 190), "RightButton");
    t.checkExpect(first.activated, false);
    t.checkExpect(first.flagged, true);
    t.checkExpect(game1.score, game1.numMines - 1);

    // If it's flagged, we can't activate
    game1.onMouseClicked(new Posn(95, 190), "LeftButton");
    t.checkExpect(first.activated, false);
    t.checkExpect(first.flagged, true);

  }

  void testOnTick(Tester t) {
    init();

    // Make sure the timer works for amount of time
    for (int time = 0; time < 50; time += 1) {
      t.checkExpect(game1.time, time);
      game1.onTick();
      t.checkExpect(game1.time, time + 1);
    }
  }

  void testEndWorld(Tester t) {
    init();

    t.checkExpect(game1.endText, "");
    t.checkExpect(game1.active, true);
    game1.endOfWorld("Preparing for the end");
    t.checkExpect(game1.endText, "Preparing for the end");
    t.checkExpect(game1.active, false);
  }

  void testGameWon(Tester t) {
    init();

    t.checkExpect(game1.gameWon(), false);

    for (int x = 0; x < game1.columns; x++) {
      for (int y = 0; y < game1.rows; y++) {
        Cell curr = game1.cells.get(x).get(y);
        if (!curr.activated) {
          curr.activate();
        }
      }
    }

    t.checkExpect(game1.gameWon(), true);
  }
}
