import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javalib.impworld.WorldScene;
import javalib.worldimages.LineImage;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.OverlayImage;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import tester.Tester;

class Examples {

  Utils u = new Utils();
  ArrayList<Integer> nums;

  // Run the game with this test

  LightEmAllPart1 game1;
  LightEmAllPart2 game2;
  LightEmAll game3;

  void init() {
    game3 = new LightEmAll(12, 14);
    game2 = new LightEmAllPart2(16, 16);
    game1 = new LightEmAllPart1(10, 10);
  }

  void initTest() {
    game1 = new LightEmAllPart1(true, 5, 5, new Random(100));
    game2 = new LightEmAllPart2(true, 5, 5, new Random(100));
    game3 = new LightEmAll(true, 5, 5, new Random(100));
  }

  void initList() {
    nums = new ArrayList<Integer>(Arrays.asList(9, 3, 2, 1, 2, 3, 4));
  }

  void testBigBang(Tester t) {
    init();
    int worldWidth = game3.width * 60;
    int worldHeight = game3.height * 60;

    // Human reaction time is ~0.15 seconds, so need a shorter tick rate
    game3.bigBang(worldWidth, worldHeight + 100, 1);

  }

  void testGenerateBoard(Tester t) {
    initTest();
    game1.generateNodes();
    t.checkExpect(game1.board.size(), 5);

    // Simple convincing
    t.checkExpect(game1.board.get(0).get(0), game1.nodes.get(0));
    t.checkExpect(game1.board.get(4).get(4), game1.nodes.get(24));

    // General convincing
    for (int col = 0; col < game1.width; col += 1) {
      for (int row = 0; row < game1.height; row += 1) {
        t.checkExpect(game1.board.get(col).get(row), game1.nodes.get((col * 5) + row));
      }
    }

    initTest();
    game2.generateBoard();
    game2.generateNodes();
    t.checkExpect(game2.board.size(), 5);

    // General convincing
    for (int col = 0; col < game2.width; col += 1) {
      for (int row = 0; row < game2.height; row += 1) {
        t.checkExpect(game2.board.get(col).get(row), game2.nodes.get((col * 5) + row));
      }
    }

    initTest();
    game3.generateBoard();
    game3.generateNodes();
    t.checkExpect(game3.board.size(), 5);

    // General convincing
    for (int col = 0; col < game3.width; col += 1) {
      for (int row = 0; row < game3.height; row += 1) {
        t.checkExpect(game3.board.get(col).get(row), game3.nodes.get((col * 5) + row));
      }
    }
  }

  void testGenerateNodes(Tester t) {
    initTest();
    t.checkExpect(game1.nodes.size(), 0);
    game1.generateBoard();
    game1.generateWires();
    game1.generateNodes();
    t.checkExpect(game1.nodes.size(), 25);

    int powerStationCount = 0;
    for (GamePiece node : game1.nodes) {
      // Test dimensions
      t.checkExpect(node.row < 5, true);
      t.checkExpect(node.col < 5, true);

      // Test connections
      if (node.row == 0) {
        t.checkExpect(node.bottom, true);
        t.checkExpect(node.left || node.right || node.top, false);
      }

      if (node.row == 1 || node.row == 3) {
        t.checkExpect(node.bottom && node.top, true);
        t.checkExpect(node.left || node.right, false);
      }

      if (node.row == 2) {
        t.checkExpect(node.bottom && node.top, true);
        if (node.col > 0) {
          t.checkExpect(node.left, true);
        }
        if (node.col == 0) {
          t.checkExpect(node.left, false);
        }

        if (node.col < 4) {
          t.checkExpect(node.right, true);
        }

        if (node.col == 4) {
          t.checkExpect(node.right, false);
        }
      }

      if (node.row == 4) {
        t.checkExpect(node.top, true);
        t.checkExpect(node.left || node.right || node.bottom, false);
      }

      // Test number of power stations
      if (node.powerStation) {
        powerStationCount += 1;
      }
    }
    t.checkExpect(powerStationCount, 1);

    initTest();
    t.checkExpect(game2.nodes.size(), 0);
    game2.generateBoard();
    game2.generateNodes();
    t.checkExpect(game2.nodes.size(), 25);

    int powerStationCount2 = 0;
    for (GamePiece node : game2.nodes) {
      // Test dimensions
      t.checkExpect(node.row < 5, true);
      t.checkExpect(node.col < 5, true);

      // Test connections visually

      // Test number of power stations
      if (node.powerStation) {
        powerStationCount2 += 1;
      }
    }
    t.checkExpect(powerStationCount2, 1);

    initTest();
    game3.generateBoard();
    t.checkExpect(game3.nodes.size(), 0);
    game3.generateBoard();
    game3.generateNodes();
    t.checkExpect(game3.nodes.size(), 25);

    int powerStationCount3 = 0;
    for (GamePiece node : game3.nodes) {
      // Test dimensions
      t.checkExpect(node.row < 5, true);
      t.checkExpect(node.col < 5, true);

      // Test connections visually

      // Test number of power stations
      if (node.powerStation) {
        powerStationCount3 += 1;
      }
    }
    t.checkExpect(powerStationCount3, 1);
  }

  void testNeighbors(Tester t) {
    initTest();
    game1.generateBoard();
    game1.generateWires();
    game1.generateNodes();

    GamePiece one = game1.nodes.get(0);
    t.checkExpect(one.neighbors.size(), 0);
    game1.generateNeighbors();
    t.checkExpect(one.neighbors.size(), 1);
    GamePiece power = game1.board.get(game1.powerCol).get(game1.powerRow);
    t.checkExpect(power.neighbors.size(), 4);
  }

  void testMakeScene(Tester t) {
    // Tested visually with big bang
  }

  void testConnect(Tester t) {
    initTest();

    game1.generateBoard();
    game1.generateWires();
    game1.generateNodes();

    GamePiece one = game1.board.get(0).get(0);
    GamePiece two = game1.board.get(1).get(0);
    GamePiece three = game1.board.get(0).get(1);

    t.checkExpect(one.right, false);
    t.checkExpect(two.left, false);
    one.connect(two);
    t.checkExpect(one.right, true);
    t.checkExpect(two.left, true);

    one.bottom = false;
    three.top = false;
    one.connect(three);

    t.checkExpect(one.bottom, true);
    t.checkExpect(three.top, true);
  }

  void testOnTick(Tester t) {
    initTest();

    game1.generateBoard();
    game1.generateWires();
    game1.generateNodes();
    game1.updatePower();

    game1.onTick();
    t.checkExpect(game1.active, true);
    t.checkExpect(game1.endText, "");

    game1.onMouseClicked(new Posn(150, 150), "RightButton");

    game1.onTick();

    t.checkExpect(game1.active, false);
    t.checkExpect(game1.endText, "You win!");

  }

  void testOnClick(Tester t) {
    initTest();
    game1.generateBoard();
    game1.generateWires();
    game1.generateNodes();
    GamePiece one = game1.board.get(2).get(0);
    t.checkExpect(one.bottom, true);
    t.checkExpect(one.left, false);
    game1.onMouseClicked(new Posn(150, 150), "LeftButton");
    t.checkExpect(one.bottom, false);
    t.checkExpect(one.left, true);
  }

  void testOnPress(Tester t) {
    initTest();
    game1.generateBoard();
    game1.generateWires();
    game1.generateNodes();
    game1.generateNeighbors();

    t.checkExpect(game1.gameWon(), false);
    game1.onTick();
    t.checkExpect(game1.endText, "");
    t.checkExpect(game1.active, true);
    game1.onMouseClicked(new Posn(0, 0), "RightButton");
    game1.updatePower();
    game1.endOfWorld("You win!");
    game1.onTick();
    t.checkExpect(game1.endText, "You win!");
    t.checkExpect(game1.active, false);
    game1.onKeyEvent("s");
    t.checkExpect(game1.endText, "You win!");
    t.checkExpect(game1.active, false);
    game1.onKeyEvent("r");
    t.checkExpect(game1.endText, "The game ended unexpectedly");
    t.checkExpect(game1.active, true);

    initTest();
    game3.generateBoard();
    game3.generateNodes();
    game3.initNeighbors();
    game3.mst = game3.generatePossibleEdges();
    game3.placeEdges();
    game3.generateNeighbors();

    t.checkExpect(game3.powerCol, 2);
    t.checkExpect(game3.powerRow, 2);
    game3.onKeyEvent("up");
    t.checkExpect(game3.powerCol, 2);
    t.checkExpect(game3.powerRow, 1);
    game3.onKeyEvent("up");
    t.checkExpect(game3.powerCol, 2);
    t.checkExpect(game3.powerRow, 0);
    game3.onKeyEvent("up");
    t.checkExpect(game3.powerCol, 2);
    t.checkExpect(game3.powerRow, 0);

    game3.onKeyEvent("left");
    t.checkExpect(game3.powerCol, 1);
    t.checkExpect(game3.powerRow, 0);
    game3.onKeyEvent("left");
    t.checkExpect(game3.powerCol, 0);
    t.checkExpect(game3.powerRow, 0);
    game3.onKeyEvent("left");
    t.checkExpect(game3.powerCol, 0);
    t.checkExpect(game3.powerRow, 0);

    game3.onKeyEvent("right");
    t.checkExpect(game3.powerCol, 1);
    t.checkExpect(game3.powerRow, 0);

    game3.onKeyEvent("right");
    t.checkExpect(game3.powerCol, 2);
    t.checkExpect(game3.powerRow, 0);

    game3.onKeyEvent("right");
    t.checkExpect(game3.powerCol, 3);
    t.checkExpect(game3.powerRow, 0);

    game3.onKeyEvent("right");
    t.checkExpect(game3.powerCol, 4);
    t.checkExpect(game3.powerRow, 0);

    game3.onKeyEvent("right");
    t.checkExpect(game3.powerCol, 4);
    t.checkExpect(game3.powerRow, 0);

    game3.onKeyEvent("down");
    t.checkExpect(game3.powerCol, 4);
    t.checkExpect(game3.powerRow, 1);

    game3.onKeyEvent("down");
    t.checkExpect(game3.powerCol, 4);
    t.checkExpect(game3.powerRow, 2);

    game3.onKeyEvent("down");
    t.checkExpect(game3.powerCol, 4);
    t.checkExpect(game3.powerRow, 3);

    game3.onKeyEvent("down");
    t.checkExpect(game3.powerCol, 4);
    t.checkExpect(game3.powerRow, 4);

    game3.onKeyEvent("down");
    t.checkExpect(game3.powerCol, 4);
    t.checkExpect(game3.powerRow, 4);

  }

  void testRestart(Tester t) {
    initTest();
    game1.generateBoard();
    game1.generateWires();
    game1.generateNodes();
    game1.generateNeighbors();

    t.checkExpect(game1.gameWon(), false);
    game1.onTick();
    t.checkExpect(game1.endText, "");
    t.checkExpect(game1.active, true);
    game1.onMouseClicked(new Posn(150, 150), "RightButton");

    game1.onTick();
    t.checkExpect(game1.endText, "You win!");
    t.checkExpect(game1.active, false);
    game1.restart();
    t.checkExpect(game1.endText, "The game ended unexpectedly");
    t.checkExpect(game1.active, true);
  }

  void testUpdatePower(Tester t) {
    initTest();
    game1.generateBoard();
    game1.generateWires();
    game1.generateNodes();
    game1.generateNeighbors();

    GamePiece power = game1.board.get(2).get(2);
    t.checkExpect(power.activation, 0.0);
    game1.updatePower();
    t.checkExpect(power.activation, 1.0);

    // Just go two deep to make sure power is working
    // If 1 layer deep works and 1+1 works, all should work
    for (GamePiece cell : power.neighbors) {
      for (GamePiece cell2 : cell.neighbors) {
        t.checkExpect(cell2.activation > 0, true);
      }
      t.checkExpect(cell.activation > 0, true);
    }
  }

  void testShuffle(Tester t) {
    initTest();
    game1.generateBoard();
    game1.generateWires();
    game1.generateNodes();

    GamePiece one = game1.board.get(0).get(0);
    GamePiece two = game1.board.get(1).get(1);
    GamePiece power = game1.board.get(2).get(2);

    t.checkExpect(one.bottom, true);
    t.checkExpect(one.top, false);
    t.checkExpect(one.left, false);
    t.checkExpect(one.right, false);

    t.checkExpect(two.bottom, true);
    t.checkExpect(two.top, true);
    t.checkExpect(two.left, false);
    t.checkExpect(two.right, false);

    t.checkExpect(power.bottom && power.top && power.left && power.right, true);
    game1.shufflePieces();

    t.checkExpect(one.bottom, false);
    t.checkExpect(one.top, true);
    t.checkExpect(one.left, false);
    t.checkExpect(one.right, false);

    t.checkExpect(two.bottom, false);
    t.checkExpect(two.top, false);
    t.checkExpect(two.left, true);
    t.checkExpect(two.right, true);

    t.checkExpect(power.bottom && power.top && power.left && power.right, true);

  }

  void testWon(Tester t) {
    initTest();
    game1.generateBoard();
    game1.generateWires();
    game1.generateNodes();
    t.checkExpect(game1.gameWon(), false);

    for (GamePiece node : game1.nodes) {
      node.activation = 1.0;
    }

    t.checkExpect(game1.gameWon(), true);
  }

  void testEnd(Tester t) {
    initTest();
    game1.generateBoard();
    game1.generateWires();
    game1.generateNodes();

    t.checkExpect(game1.endText, "");
    t.checkExpect(game1.active, true);

    game1.endOfWorld("Testing the end");

    t.checkExpect(game1.endText, "Testing the end");
    t.checkExpect(game1.active, false);
  }

  void testGamePieceDraw(Tester t) {
    // tested visually plus:
    initTest();
    game1.generateBoard();
    game1.generateWires();
    game1.generateNodes();

    GamePiece one = game1.nodes.get(0);

    OverlayImage answer = new OverlayImage(
        new RectangleImage(60, 60, OutlineMode.OUTLINE, Color.BLACK),
        new RectangleImage(60, 60, OutlineMode.SOLID, Color.DARK_GRAY));

    LineImage left = new LineImage(new Posn(30, 0), Color.ORANGE);
    answer = new OverlayImage(left.movePinhole(15, 0), answer);
    WorldScene s = game1.getEmptyScene();

    s.placeImageXY(answer, one.col * 60 + 30, one.row * 60 + 30);
    t.checkExpect(one.drawSelf(game1.getEmptyScene()), s);
  }

  void testAddNeighbor(Tester t) {
    initTest();
    game1.generateBoard();
    game1.generateWires();
    game1.generateNodes();
    GamePiece one = game1.nodes.get(0);
    GamePiece two = game1.nodes.get(1);
    t.checkExpect(one.neighbors, new ArrayList<GamePiece>(Arrays.asList()));
    one.addNeighbor(two);
    t.checkExpect(one.neighbors, new ArrayList<GamePiece>(Arrays.asList(two)));

  }

  void testRemoveAllNeighbors(Tester t) {
    initTest();
    game1.generateBoard();
    game1.generateWires();
    game1.generateNodes();
    game1.generateNeighbors();

    GamePiece one = game1.board.get(2).get(2);

    t.checkExpect(one.neighbors.size(), 4);
    one.removeAllNeighbors();
    t.checkExpect(one.neighbors.size(), 0);

  }

  void testRemoveAllValue(Tester t) {
    initTest();
    game1.generateBoard();
    game1.generateWires();
    game1.generateNodes();
    game1.generateNeighbors();
    game1.updatePower();

    GamePiece one = game1.board.get(2).get(2);
    GamePiece two = game1.board.get(1).get(1);

    t.checkExpect(one.activation, 1.0);
    t.checkExpect(two.activation, 1.0);
    one.removeAllValue();
    t.checkExpect(one.activation, 0.0);
    t.checkExpect(two.activation, 1.0);

  }

  void testRotate(Tester t) {
    initTest();
    game1.generateBoard();
    game1.generateWires();
    game1.generateNodes();
    GamePiece one = game1.nodes.get(0);
    t.checkExpect(one.bottom, true);
    t.checkExpect(one.left, false);
    one.rotateSelf();
    t.checkExpect(one.bottom, false);
    t.checkExpect(one.left, true);
  }

  void testUpdateValue(Tester t) {
    initTest();
    game1.generateBoard();
    game1.generateWires();
    game1.generateNodes();
    game1.generateNeighbors();
    game1.updatePower();

    GamePiece one = game1.board.get(2).get(2);
    GamePiece two = game1.board.get(1).get(1);

    t.checkExpect(one.activation, 1.0);
    t.checkExpect(two.activation, 1.0);
    one.updateValue(5.0, 0, 0, new ArrayList<GamePiece>());
    t.checkExpect(one.activation, 5.0);
    t.checkExpect(two.activation, 1.0);

  }

  void testShufflePiece(Tester t) {
    initTest();
    game1.generateBoard();
    game1.generateWires();
    game1.generateNodes();

    GamePiece one = game1.board.get(0).get(0);

    t.checkExpect(one.bottom, true);
    t.checkExpect(one.top, false);
    t.checkExpect(one.left, false);
    t.checkExpect(one.right, false);

    one.shuffle(game1.rand);

    t.checkExpect(one.bottom, false);
    t.checkExpect(one.top, true);
    t.checkExpect(one.left, false);
    t.checkExpect(one.right, false);

  }

  void testConnected(Tester t) {
    initTest();
    game1.generateBoard();
    game1.generateWires();
    game1.generateNodes();
    GamePiece one = game1.nodes.get(0);
    GamePiece two = game1.board.get(0).get(1);
    GamePiece three = game1.nodes.get(16);

    t.checkExpect(one.connectedTo(two), true);
    t.checkExpect(two.connectedTo(one), true);
    t.checkExpect(one.connectedTo(three), false);
    t.checkExpect(three.connectedTo(one), false);

  }

  void testHeapsort(Tester t) {
    initList();
    t.checkExpect(u.removeMax(nums), 9);
    t.checkExpect(nums, new ArrayList<Integer>(Arrays.asList(4, 3, 2, 1, 2, 3)));
    t.checkExpect(u.removeMax(nums), 4);
    t.checkExpect(nums, new ArrayList<Integer>(Arrays.asList(3, 3, 2, 1, 2)));
    t.checkExpect(u.removeMax(nums), 3);
    t.checkExpect(nums, new ArrayList<Integer>(Arrays.asList(3, 2, 2, 1)));
    t.checkExpect(u.removeMax(nums), 3);
    t.checkExpect(nums, new ArrayList<Integer>(Arrays.asList(2, 1, 2)));
    t.checkExpect(u.removeMax(nums), 2);
    t.checkExpect(nums, new ArrayList<Integer>(Arrays.asList(2, 1)));
    t.checkExpect(u.removeMax(nums), 2);
    t.checkExpect(nums, new ArrayList<Integer>(Arrays.asList(1)));
    t.checkExpect(u.removeMax(nums), 1);
    t.checkExpect(nums, new ArrayList<Integer>(Arrays.asList()));
    initList();
    t.checkExpect(u.heapsort(nums), new ArrayList<Integer>(Arrays.asList(9, 4, 3, 3, 2, 2, 1)));
  }

  void testGenerateEdges(Tester t) {
    // TODO: Finish testing generate edges
    init();
    t.checkExpect(game3.mst.size(), 167);

  }

  void testPlaceEdges(Tester t) {
    initTest();
    game1.generateBoard();
    game1.generateNodes();

    GamePiece one = game1.board.get(0).get(0);
    GamePiece two = game1.board.get(1).get(0);
    GamePiece three = game1.board.get(0).get(1);

    t.checkExpect(one.right, false);
    t.checkExpect(two.left, false);

    game1.mst.add(new Edge(one, two, 1));
    game1.placeEdges();

    t.checkExpect(one.right, true);
    t.checkExpect(two.left, true);

    one.bottom = false;
    three.top = false;

    game1.mst.add(new Edge(one, three, 1));
    game1.placeEdges();

    t.checkExpect(one.bottom, true);
    t.checkExpect(three.top, true);

  }

  void testInitNeighbors(Tester t) {
    initTest();
    game1.generateBoard();
    game1.generateNodes();

    GamePiece one = game1.board.get(0).get(0);
    t.checkExpect(one.neighbors.size(), 0);

    game1.initNeighbors();
    t.checkExpect(one.neighbors.size(), 2);

  }

  void testIsOnMap(Tester t) {
    initTest();
    game1.generateBoard();
    game1.generateNodes();

    t.checkExpect(game1.isOnMap(0, 0), true);
    t.checkExpect(game1.isOnMap(1, 1), true);
    t.checkExpect(game1.isOnMap(4, 4), true);
    t.checkExpect(game1.isOnMap(0, 5), false);
    t.checkExpect(game1.isOnMap(5, 0), false);
    t.checkExpect(game1.isOnMap(5, 5), false);
  }

  void testGeneratePossibleEdges(Tester t) {
    initTest();
    game1.generateBoard();
    game1.generateNodes();

    GamePiece one = game1.board.get(0).get(0);
    GamePiece two = game1.board.get(1).get(0);
    t.checkExpect(one.neighbors.size(), 0);
    t.checkExpect(two.neighbors.size(), 0);

    t.checkExpect(game1.generatePossibleEdges().size(), 0);

    one.connect(two);

    t.checkExpect(game1.generatePossibleEdges().size(), 2);

    two.connect(one);

    t.checkExpect(game1.generatePossibleEdges().size(), 2);
  }
}

// To store examples related to the core functionality of queues
class ExamplesQueues {

  // Examples of ANodes
  Sentinel<Integer> countThreeSent;
  Node<Integer> one;
  Node<Integer> two;
  Node<Integer> three;

  // Examples of Deques
  Deque<Integer> mtDeque;
  Deque<Integer> countThreeDeque;

  // Examples of Stacks
  Stack<Integer> mtStack;
  Stack<Integer> countThreeStack;

  // ArrayLists
  ArrayList<String> test = new ArrayList<String>();
  ArrayList<String> reverseTest = new ArrayList<String>();

  // Initialize
  void init() {
    new Sentinel<Integer>();
    new Node<Integer>(123);
    new Node<Integer>(234);
    new Node<Integer>(345, countThreeSent, two);

    // Examples of Deques
    new Deque<Integer>(new Sentinel<Integer>());
    new Deque<Integer>(countThreeSent);

    // Examples of Stacks
    new Stack<Integer>(mtDeque);
    new Stack<Integer>(countThreeDeque);

    countThreeSent.setNext(one);
    one.setPrev(countThreeSent);

  }

  void init2() {
    test.add("t");
    test.add("e");
    test.add("s");
    test.add("t");

    reverseTest.add("t");
    reverseTest.add("s");
    reverseTest.add("e");
    reverseTest.add("t");
  }
}

// To store examples related to the core functionality of deques
class ExamplesDeque {

  // deque1 data
  Sentinel<String> empty = new Sentinel<String>();

  // deque2 data
  Sentinel<String> alphaSent = new Sentinel<String>();
  ANode<String> abc = new Node<String>("abc");
  ANode<String> cde = new Node<String>("cde");
  ANode<String> bcd = new Node<String>("bcd", cde, abc);
  ANode<String> def = new Node<String>("def", alphaSent, cde);

  // deque3 data
  Sentinel<String> palSent = new Sentinel<String>();
  ANode<String> adog = new Node<String>("dog");
  ANode<String> acanal = new Node<String>("acanal");
  ANode<String> aplan = new Node<String>("aplan", acanal, adog);
  ANode<String> pagoda = new Node<String>("pagoda", palSent, acanal);

  // deque 4 data
  Sentinel<String> addedFrontSent = new Sentinel<String>();
  ANode<String> addedFront = new Node<String>("added");
  ANode<String> abc2 = new Node<String>("abc");
  ANode<String> cde2 = new Node<String>("cde");
  ANode<String> bcd2 = new Node<String>("bcd", cde2, abc2);
  ANode<String> def2 = new Node<String>("def", addedFrontSent, cde2);

  // deque 5 data
  Sentinel<String> addedBackSent = new Sentinel<String>();
  ANode<String> addedBack = new Node<String>("added");
  ANode<String> abc3 = new Node<String>("abc");
  ANode<String> cde3 = new Node<String>("cde");
  ANode<String> bcd3 = new Node<String>("bcd", cde3, abc3);
  ANode<String> def3 = new Node<String>("def", addedBackSent, cde3);

  // Initialize dequeues
  Deque<String> deque1 = new Deque<String>(empty);
  Deque<String> deque2 = new Deque<String>(alphaSent);
  Deque<String> deque3 = new Deque<String>(palSent);
  Deque<String> deque4 = new Deque<String>(addedFrontSent);
  Deque<String> deque5 = new Deque<String>(addedBackSent);

  void init() {
    // Reset deque 1 data
    empty = new Sentinel<String>();

    // Reset deque 2 data
    alphaSent = new Sentinel<String>();
    abc = new Node<String>("abc");
    cde = new Node<String>("cde");
    bcd = new Node<String>("bcd", cde, abc);
    def = new Node<String>("def", alphaSent, cde);

    // Reset deque3 data
    palSent = new Sentinel<String>();
    adog = new Node<String>("dog");
    acanal = new Node<String>("acanal");
    aplan = new Node<String>("aplan", acanal, adog);
    pagoda = new Node<String>("pagoda", palSent, acanal);

    // Reset deque4 data
    addedFrontSent = new Sentinel<String>();
    addedFront = new Node<String>("added");
    abc2 = new Node<String>("abc");
    cde2 = new Node<String>("cde");
    bcd2 = new Node<String>("bcd", cde2, abc2);
    def2 = new Node<String>("def", addedFrontSent, cde2);

    // Reset deque 5 data
    addedBackSent = new Sentinel<String>();
    addedBack = new Node<String>("added");
    abc3 = new Node<String>("abc");
    cde3 = new Node<String>("cde");
    bcd3 = new Node<String>("bcd", cde3, abc3);
    def3 = new Node<String>("def", addedBack, cde3);

    // Re-initialize dequeues
    deque1 = new Deque<String>(empty);
    deque2 = new Deque<String>(alphaSent);
    deque3 = new Deque<String>(palSent);
    deque4 = new Deque<String>(addedFrontSent);
    deque5 = new Deque<String>(addedBackSent);

    // Fully init deque2's sentinel
    this.alphaSent.setNext(abc);
    this.abc.setPrev(alphaSent);

    // Fully init deque3's sentinel
    this.palSent.setNext(adog);
    this.adog.setPrev(palSent);

    // Fully init deque4's sentinel
    this.addedFrontSent.setNext(addedFront);
    this.addedFront.setPrev(addedFrontSent);
    this.addedFront.setNext(abc2);
    this.abc2.setPrev(addedFront);

    // Fully init deque5's sentinel
    this.addedBackSent.setNext(abc3);
    this.abc3.setPrev(addedBackSent);
    this.addedBack.setNext(addedBackSent);
    this.addedBackSent.setPrev(addedBack);

  }

  void testInit(Tester t) {
    init();
    t.checkExpect(deque2.header.next, abc);
    deque2.header.next = bcd;
    t.checkExpect(deque2.header.next, bcd);
    init();
    t.checkExpect(deque2.header.next, abc);

  }

  void testSetNext(Tester t) {
    init();
    t.checkExpect(deque1.header.next, empty);
    deque1.header.setNext(abc);
    t.checkExpect(deque1.header.next, abc);
    t.checkExpect(deque2.header.next, abc);
    deque2.header.setNext(bcd);
    t.checkExpect(deque2.header.next, bcd);

  }

  void testSetPrev(Tester t) {
    init();
    t.checkExpect(deque1.header.prev, empty);
    deque1.header.setPrev(abc);
    t.checkExpect(deque1.header.prev, abc);
    t.checkExpect(deque2.header.prev, def);
    deque2.header.setPrev(bcd);
    t.checkExpect(deque2.header.prev, bcd);
  }

  void testRemoveThis(Tester t) {
    init();
    t.checkExpect(deque1.header.next, empty);
    empty.removeThis();
    t.checkExpect(deque1.header.next, empty);
    t.checkExpect(deque2.header.next, abc);
    abc.removeThis();
    t.checkExpect(deque2.header.next, bcd);
  }

  void testRemoveReturn(Tester t) {
    init();
    t.checkExpect(deque1.header.next, empty);
    t.checkException(new RuntimeException("Tried to remove an item from an empty list"), empty,
        "removeReturn");
    t.checkExpect(deque2.header.next, abc);
    t.checkExpect(abc.removeReturn(), "abc");
    t.checkExpect(deque2.header.next, bcd);
  }

  void testAddAtHead(Tester t) {
    init();
    t.checkExpect(deque1.header.next, empty);
    deque1.addAtHead("def");
    deque1.addAtHead("cde");
    deque1.addAtHead("bcd");
    deque1.addAtHead("abc");
    t.checkExpect(deque1.header.next, abc);
    t.checkExpect(deque2.header.next, abc);
    deque2.addAtHead("added");
    t.checkExpect(deque2.header.next, addedFront);
  }

  void testAddAtTail(Tester t) {
    init();
    t.checkExpect(deque1.header.next, empty);
    deque1.addAtTail("abc");
    deque1.addAtTail("bcd");
    deque1.addAtTail("cde");
    deque1.addAtTail("def");
    t.checkExpect(deque1.header.next, abc);
    t.checkExpect(deque2.header.prev, def);
    deque2.addAtTail("added");
    t.checkExpect(deque2.header.prev, addedBack);
  }

  void testSize(Tester t) {
    init();
    t.checkExpect(deque1.size(), 0);
    t.checkExpect(deque2.size(), 4);
    t.checkExpect(deque4.size(), 5);
  }

  void testFind(Tester t) {
    init();
    IPred<String> abcPred = new ABC();
    t.checkExpect(deque1.find(abcPred), new Sentinel<String>());
    t.checkExpect(deque2.find(abcPred), this.abc);
    t.checkExpect(deque3.find(abcPred), deque3.header);
    deque2.removeFromHead();
    t.checkExpect(deque2.find(abcPred), deque2.header);
  }

  void testRemoveNode(Tester t) {
    init();
    t.checkExpect(deque1.header.next, empty);
    deque1.removeNode(empty);
    t.checkExpect(deque1.header.next, empty);
    t.checkExpect(deque2.header.next, abc);
    deque2.removeNode(abc);
    t.checkExpect(deque2.header.next, bcd);
    deque2.removeNode(cde);
    t.checkExpect(deque2.header.next, bcd);
    deque2.removeNode(bcd);
    t.checkExpect(deque2.header.next, def);
  }

  void testRemoveFromHead(Tester t) {
    init();
    t.checkExpect(deque1.header.next, empty);
    t.checkExpect(deque2.header.next, abc);
    t.checkExpect(deque2.removeFromHead(), "abc");
    t.checkExpect(deque2.header.next, bcd);
  }

  void testRemoveFromTail(Tester t) {
    init();
    t.checkExpect(deque1.header.next, empty);
    t.checkExpect(deque2.header.prev, def);
    t.checkExpect(deque2.size(), 4);
    t.checkExpect(deque2.removeFromTail(), "def");
    t.checkExpect(deque2.header.prev, cde);
    t.checkExpect(deque2.size(), 3);

    t.checkExpect(deque2.removeFromTail(), "cde");
    t.checkExpect(deque2.removeFromTail(), "bcd");
    t.checkExpect(deque2.removeFromTail(), "abc");

  }

}