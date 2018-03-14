import tester.*;

// To represent a recipe to make a bagel. All weights in ounces
class BagelRecipe {
  double flour;
  double water;
  double yeast;
  double malt;
  double salt;

  BagelRecipe(double flour, double water, double yeast, double salt, double malt) {
    Utils u = new Utils();
    this.flour = u.compare(flour, water, 1, "Weight of flour does not equal water");
    this.water = water;
    this.yeast = u.compare(yeast, malt, 1, "Weight of yeast does not equal weight of malt");
    this.malt = malt;
    this.salt = (u.compare(salt + yeast, flour, 20,
        "Weight of salt + yeast does not equal 1/20 the weight of flour") - yeast);
  }

  // Main constructor
  BagelRecipe(double flour, double yeast) {
    this(flour, flour, yeast, new Utils().computeSalt(flour, yeast), yeast);
  }

  // If you only provide flour, yeast, and salt, then units are volumes
  BagelRecipe(double flour, double yeast, double salt) {
    this(flour * 4.25, flour * 4.25, yeast * 5 / 48, salt * 10 / 48, yeast * 5 / 48);
  }

  /*
   * Parameters: 
   * ...other...          -- BagelRecipe
   * Methods on Parameters:
   * ...other.sameRecipe(BagelRecipe)...  -- boolean
   */
  // Does this recipe's ingredients have the same weights as that recipe's
  // ingredients?
  boolean sameRecipe(BagelRecipe other) {
    return (Math.abs(this.flour - other.flour) < 0.001)
        && (Math.abs(this.water - other.water) < 0.001)
        && (Math.abs(this.yeast - other.yeast) < 0.001)
        && (Math.abs(this.malt - other.malt) < 0.001) && (Math.abs(this.salt - other.salt) < 0.001);
  }
}

// To store utility functions
class Utils {

  /*
   * Parameters:
   * ...v1...        -- double
   * ...v2...        -- double
   * ...factor...    -- double
   * ...error...     -- String
   * 
   * Methods on Parameters:
   * 
   */
  // compare has a double v1, v2, factor, and a String error
  // If v1 multiplied by the factor equals v2, return that factor
  public double compare(double v1, double v2, double factor, String error) {
    if ((Math.abs((v1 * factor) - v2)) < 0.001) {
      return v1;
    }
    else {
      throw new IllegalArgumentException(error);
    }
  }

  /*
   * Parameters:
   * ...flour...     -- double
   * ...yeast...     -- double
   * 
   * Methods on parameters:
   */
  // computes the amount of salt needed, given the amount of flour and yeast
  public double computeSalt(double flour, double yeast) {
    if ((0.05 * flour - yeast) > 0) {
      return ((0.05 * flour - yeast));
    }

    else {
      throw new IllegalArgumentException("Not a high enough flour/yeast ratio");
    }
  }
}

// To store examples of bagel recipes and tests
class ExamplesBagels {

  // Examples with first constructor
  BagelRecipe bagel1p1 = new BagelRecipe(1.0, 1.0, 0.04, 0.01, 0.04);
  BagelRecipe bagelp2 = new BagelRecipe(3.0, 3.0, 0.05, 0.1, 0.05);
  BagelRecipe bagel1p3 = new BagelRecipe(5.0, 5.0, 0.1, 0.15, 0.1);

  // Examples with second constructor
  BagelRecipe bagel2p1 = new BagelRecipe(1.0, 0.01);
  BagelRecipe bagel2p2 = new BagelRecipe(5.0, 0.05);
  BagelRecipe bagel2p3 = new BagelRecipe(6.0, 0.2);

  // Examples with third constructor
  BagelRecipe bagel3p1 = new BagelRecipe(0.23529, 0.008 * 48, 0.001 * 48);

  boolean testCompare(Tester t) {
    Utils u = new Utils();
    return t.checkExpect(u.compare(1.0, 1.0, 1.0, "None"), 1.0)
        && t.checkExpect(u.compare(0.05, 1.0, 20, "None"), 0.05)
        && t.checkException(new IllegalArgumentException(""), u, "compare", 1.0, 3.0, 1.0, "");
  }

  boolean testConstructor1(Tester t) {
    return t.checkConstructorException(
        new IllegalArgumentException("Weight of flour does not equal water"), "BagelRecipe", 1.0,
        3.0, 0.04, 0.01, 0.04)

        && t.checkConstructorException(
            new IllegalArgumentException("Weight of yeast does not equal weight of malt"),
            "BagelRecipe", 1.0, 1.0, 0.05, 0.01, 0.01)

        && t.checkConstructorException(
            new IllegalArgumentException(
                "Weight of salt + yeast does not equal 1/20 the weight of flour"),
            "BagelRecipe", 1.0, 1.0, 0.05, 0.05, 0.05);
  }

  boolean testConstructor2(Tester t) {
    return t.checkConstructorException(
        new IllegalArgumentException("Not a high enough flour/yeast ratio"), "BagelRecipe", 2.0,
        2.0);
  }

  boolean testConstructor3(Tester t) {
    return t.checkConstructorException(
        new IllegalArgumentException(
            "Weight of salt + yeast does not equal 1/20 the weight of flour"),
        "BagelRecipe", 1.0 / 4.25, 0.05 / 5, 0.05 / 10);
  }

  boolean testComputeSalt(Tester t) {
    Utils u = new Utils(); 
    return t.checkInexact(u.computeSalt(10, .1), 0.4, 0.01)
        && t.checkInexact(u.computeSalt(10, 0), 0.5, 0.01)
        && t.checkInexact(u.computeSalt(1, 0), 0.05, 0.01);
  }

  boolean testSameRecipe(Tester t) {
    return t.checkExpect(bagel1p1.sameRecipe(bagel1p1), true)
        && t.checkExpect(bagel2p1.sameRecipe(bagel2p1), true)
        && t.checkExpect(bagel3p1.sameRecipe(bagel3p1), true)
        && t.checkExpect(bagel1p1.sameRecipe(bagel3p1), true)
        && t.checkExpect(bagel1p1.sameRecipe(bagel2p1), false);
  }
}