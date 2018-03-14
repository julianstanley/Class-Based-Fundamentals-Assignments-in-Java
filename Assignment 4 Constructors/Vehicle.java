import tester.*;

// Represents all vehicles
interface IVehicle {
  
  // Template: Same as class
  // computes the total revenue of this Vehicle
  double totalRevenue();

  // Template: Same as class
  // computes the cost of fully fueling this Vehicle
  double fuelCost();

  // Template: Same as class
  // computes the per-passenger profit of this Vehicle
  double perPassengerProfit();

  // Template: Same as class
  // produce a String that shows the name and passenger capacity of this Vehicle
  String format();

  // Template: Same as class +
  /*
   * Parameters: 
   * ...that...    -- IVehicle
   * 
   * Methods on Parameters:
   * ...that.totalRevenue()...         -- double
   * ...that.fuelCost()...             -- double
   * ...that.perPassengerProfit()...   -- double
   * ...that.format()...               -- String
   * ...that.sameVehicle(IVehicle)...  -- boolean
   * ...that.sameAirplane(Airplane)... -- boolean
   * ...that.sameTrain(Train)...       -- boolean
   * ...that.sameBus(Bus)...           -- boolean
   * 
   */
  // is this IVehicle the same as that one?
  boolean sameVehicle(IVehicle that);

  // Template: Same as class +
  /*
   * Parameters: 
   * ...that...    -- Airplane
   * 
   * Methods on Parameters:
   * ...that.totalRevenue()...         -- double
   * ...that.fuelCost()...             -- double
   * ...that.perPassengerProfit()...   -- double
   * ...that.format()...               -- String
   * ...that.sameVehicle(IVehicle)...  -- boolean
   * ...that.sameAirplane(Airplane)... -- boolean
   * ...that.sameTrain(Train)...       -- boolean
   * ...that.sameBus(Bus)...           -- boolean
   * 
   */
  // is this IVehicle the same as that Airplane?
  boolean sameAirplane(Airplane that);

  // Template: Same as class +
  /*
   * Parameters: 
   * ...that...    -- Train
   * 
   * Methods on Parameters:
   * ...that.totalRevenue()...         -- double
   * ...that.fuelCost()...             -- double
   * ...that.perPassengerProfit()...   -- double
   * ...that.format()...               -- String
   * ...that.sameVehicle(IVehicle)...  -- boolean
   * ...that.sameAirplane(Airplane)... -- boolean
   * ...that.sameTrain(Train)...       -- boolean
   * ...that.sameBus(Bus)...           -- boolean
   * 
   */
  // is this IVehicle the same as that Train?
  boolean sameTrain(Train that);

  // Template: Same as class +
  /*
   * Parameters: 
   * ...that...    -- Bus
   * 
   * Methods on Parameters:
   * ...that.totalRevenue()...         -- double
   * ...that.fuelCost()...             -- double
   * ...that.perPassengerProfit()...   -- double
   * ...that.format()...               -- String
   * ...that.sameVehicle(IVehicle)...  -- boolean
   * ...that.sameAirplane(Airplane)... -- boolean
   * ...that.sameTrain(Train)...       -- boolean
   * ...that.sameBus(Bus)...           -- boolean
   * 
   */
  // is this IVehicle the same as that Bus?
  boolean sameBus(Bus that);
}

// Represents an abstract vehicle
abstract class AVehicle implements IVehicle {
  String name;
  int passengerCapacity;
  double fare;
  int fuelCapacity;

  AVehicle(String name, int passengerCapacity, double fare, int fuelCapacity) {
    this.name = name;
    this.passengerCapacity = passengerCapacity;
    this.fare = fare;
    this.fuelCapacity = fuelCapacity;
  }
  
  /*
   * Fields:
   * ...this.name...               -- String
   * ...this.passengerCapacity...  -- int
   * ...this.fare...               -- double
   * ...this.fuelCapacity...       -- int
   * 
   * Methods:
   * ...this.totalRevenue()...         -- double
   * ...this.fuelCost()...             -- double
   * ...this.perPassengerProfit()...   -- double
   * ...this.format()...               -- String
   * ...this.sameVehicle(IVehicle)...  -- boolean
   * ...this.sameAirplane(Airplane)... -- boolean
   * ...this.sameTrain(Train)...       -- boolean
   * ...this.sameBus(Bus)...           -- boolean
   * 
   * Methods of Fields:
   * 
   */

  // Template: Same as class
  // computes the total revenue of operating this AVehicle
  public double totalRevenue() {
    return this.passengerCapacity * this.fare;
  }

  // Template: Same as class
  // computes the cost of fully fueling this AVehicle
  public double fuelCost() {
    return this.fuelCapacity * 2.55;
  }

  // Template: Same as class
  // computes the per-passenger profit of this AVehicle
  public double perPassengerProfit() {
    return (this.totalRevenue() - this.fuelCost()) / this.passengerCapacity;
  }

  // Template: Same as class
  // produce a String that shows the name and passenger capacity of this
  // AVehicle
  public String format() {
    return this.name + ", " + Integer.toString(this.passengerCapacity) + ".";
  }

  // Template: Same as class +
  /*
   * Parameters: 
   * ...that...    -- IVehicle
   * 
   * Methods on Parameters:
   * ...that.totalRevenue()...         -- double
   * ...that.fuelCost()...             -- double
   * ...that.perPassengerProfit()...   -- double
   * ...that.format()...               -- String
   * ...that.sameVehicle(IVehicle)...  -- boolean
   * ...that.sameAirplane(Airplane)... -- boolean
   * ...that.sameTrain(Train)...       -- boolean
   * ...that.sameBus(Bus)...           -- boolean
   * 
   */
  // is this AVehicle the same as that AVehicle?
  public abstract boolean sameVehicle(IVehicle that);

  // Template: Same as class + 
  /*
   * Parameters: 
   * ...that...    -- Airplane
   * 
   * Methods on Parameters:
   * ...that.totalRevenue()...         -- double
   * ...that.fuelCost()...             -- double
   * ...that.perPassengerProfit()...   -- double
   * ...that.format()...               -- String
   * ...that.sameVehicle(IVehicle)...  -- boolean
   * ...that.sameAirplane(Airplane)... -- boolean
   * ...that.sameTrain(Train)...       -- boolean
   * ...that.sameBus(Bus)...           -- boolean
   * 
   */
  // Is this AVehicle the same as that Airplane?
  public boolean sameAirplane(Airplane that) {
    return false;
  }

  // Template: Same as class + 
  /*
   * Parameters: 
   * ...that...    -- Train
   * 
   * Methods on Parameters:
   * ...that.totalRevenue()...         -- double
   * ...that.fuelCost()...             -- double
   * ...that.perPassengerProfit()...   -- double
   * ...that.format()...               -- String
   * ...that.sameVehicle(IVehicle)...  -- boolean
   * ...that.sameAirplane(Airplane)... -- boolean
   * ...that.sameTrain(Train)...       -- boolean
   * ...that.sameBus(Bus)...           -- boolean
   * 
   */
  // Is this AVehicle the same as that Train?
  public boolean sameTrain(Train that) {
    return false;
  }

  
  // Template: Same as class +
  /*
   * Parameters: 
   * ...that...    -- Bus
   * 
   * Methods on Parameters:
   * ...that.totalRevenue()...         -- double
   * ...that.fuelCost()...             -- double
   * ...that.perPassengerProfit()...   -- double
   * ...that.format()...               -- String
   * ...that.sameVehicle(IVehicle)...  -- boolean
   * ...that.sameAirplane(Airplane)... -- boolean
   * ...that.sameTrain(Train)...       -- boolean
   * ...that.sameBus(Bus)...           -- boolean
   * 
   */
  // Is this AVehicle the same as that Bus?
  public boolean sameBus(Bus that) {
    return false;
  }
}

// Represents an airplane vehicle
class Airplane extends AVehicle {
  String code;
  boolean isWideBody;

  Airplane(String name, int passengerCapacity, double fare, int fuelCapacity, String code,
      boolean isWideBody) {
    super(name, passengerCapacity, fare, fuelCapacity);
    this.code = code;
    this.isWideBody = isWideBody;
  }

  /*
   * Fields:
   * ...this.name...               -- String
   * ...this.passengerCapacity...  -- int
   * ...this.fare...               -- double
   * ...this.fuelCapacity...       -- int
   * ...this.code...               -- String
   * ...this.isWideBody...         -- boolean
   * 
   * Methods:
   * ...this.totalRevenue()...         -- double
   * ...this.fuelCost()...             -- double
   * ...this.perPassengerProfit()...   -- double
   * ...this.format()...               -- String
   * ...this.sameVehicle(IVehicle)...  -- boolean
   * ...this.sameAirplane(Airplane)... -- boolean
   * ...this.sameTrain(Train)...       -- boolean
   * ...this.sameBus(Bus)...           -- boolean
   * 
   * Methods of Fields:
   * 
   */
  
  // computes the cost of fully fueling this Airplane
  public double fuelCost() {
    return this.fuelCapacity * 1.94;
  }

  // Template: Same as class + 
  /*
   * Parameters: 
   * ...that...    -- IVehicle
   * 
   * Methods on Parameters:
   * ...that.totalRevenue()...         -- double
   * ...that.fuelCost()...             -- double
   * ...that.perPassengerProfit()...   -- double
   * ...that.format()...               -- String
   * ...that.sameVehicle(IVehicle)...  -- boolean
   * ...that.sameAirplane(Airplane)... -- boolean
   * ...that.sameTrain(Train)...       -- boolean
   * ...that.sameBus(Bus)...           -- boolean
   * 
   */
  // is this Airplane the same as that IVehicle?
  public boolean sameVehicle(IVehicle that) {
    return that.sameAirplane(this);
  }

  // Template: Same as class +   
  /*
   * Parameters: 
   * ...that...    -- Airplane
   * 
   * Methods on Parameters:
   * ...that.totalRevenue()...         -- double
   * ...that.fuelCost()...             -- double
   * ...that.perPassengerProfit()...   -- double
   * ...that.format()...               -- String
   * ...that.sameVehicle(IVehicle)...  -- boolean
   * ...that.sameAirplane(Airplane)... -- boolean
   * ...that.sameTrain(Train)...       -- boolean
   * ...that.sameBus(Bus)...           -- boolean
   * 
   */
  // is this Airplane the same as that airplane?
  public boolean sameAirplane(Airplane that) {
    return (this.name.equals(that.name) && (this.passengerCapacity == that.passengerCapacity)
        && (that.fare - this.fare < 0.01) && (that.fuelCapacity - this.fuelCapacity < 0.01)
        && (this.code.equals(that.code)) && (this.isWideBody == that.isWideBody));
  }
}

// Represents a train vehicle
class Train extends AVehicle {
  int numberOfCars;
  int gauge;

  Train(String name, int passengerCapacity, double fare, int fuelCapacity, int numberOfCars,
      int gauge) {
    super(name, passengerCapacity, fare, fuelCapacity);
    this.numberOfCars = numberOfCars;
    this.gauge = gauge;
  }
  
  /*
   * Fields:
   * ...this.name...               -- String
   * ...this.passengerCapacity...  -- int
   * ...this.fare...               -- double
   * ...this.fuelCapacity...       -- int
   * ...this.numberofCars...       -- int
   * ...this.guage...              -- int
   * 
   * Methods:
   * ...this.totalRevenue()...         -- double
   * ...this.fuelCost()...             -- double
   * ...this.perPassengerProfit()...   -- double
   * ...this.format()...               -- String
   * ...this.sameVehicle(IVehicle)...  -- boolean
   * ...this.sameAirplane(Airplane)... -- boolean
   * ...this.sameTrain(Train)...       -- boolean
   * ...this.sameBus(Bus)...           -- boolean
   * 
   * Methods of Fields:
   * 
   */

  // Template: Same as class + 
  /*
   * Parameters: 
   * ...that...    -- IVehicle
   * 
   * Methods on Parameters:
   * ...that.totalRevenue()...         -- double
   * ...that.fuelCost()...             -- double
   * ...that.perPassengerProfit()...   -- double
   * ...that.format()...               -- String
   * ...that.sameVehicle(IVehicle)...  -- boolean
   * ...that.sameAirplane(Airplane)... -- boolean
   * ...that.sameTrain(Train)...       -- boolean
   * ...that.sameBus(Bus)...           -- boolean
   * 
   */
  // is this Train the same as that IVehicle?
  public boolean sameVehicle(IVehicle that) {
    return that.sameTrain(this);
  }

  // Template: Same as class +
  /*
   * Parameters: 
   * ...that...    -- Train
   * 
   * Methods on Parameters:
   * ...that.totalRevenue()...         -- double
   * ...that.fuelCost()...             -- double
   * ...that.perPassengerProfit()...   -- double
   * ...that.format()...               -- String
   * ...that.sameVehicle(IVehicle)...  -- boolean
   * ...that.sameAirplane(Airplane)... -- boolean
   * ...that.sameTrain(Train)...       -- boolean
   * ...that.sameBus(Bus)...           -- boolean
   * 
   */
  // is this Train the same as that Train?
  public boolean sameTrain(Train that) {
    return (this.name.equals(that.name) && (this.passengerCapacity == that.passengerCapacity)
        && (that.fare - this.fare < 0.01) && (that.fuelCapacity - this.fuelCapacity < 0.01)
        && (this.numberOfCars == that.numberOfCars) && (this.gauge == that.gauge));
  }
}

// Represents a bus vehicle
class Bus extends AVehicle {
  int length;

  Bus(String name, int passengerCapacity, double fare, int fuelCapacity, int length) {
    super(name, passengerCapacity, fare, fuelCapacity);
    this.length = length;
  }
  
  /*
   * Fields:
   * ...this.name...               -- String
   * ...this.passengerCapacity...  -- int
   * ...this.fare...               -- double
   * ...this.fuelCapacity...       -- int
   * ...this.length...             -- int
   * 
   * Methods:
   * ...this.totalRevenue()...         -- double
   * ...this.fuelCost()...             -- double
   * ...this.perPassengerProfit()...   -- double
   * ...this.format()...               -- String
   * ...this.sameVehicle(IVehicle)...  -- boolean
   * ...this.sameAirplane(Airplane)... -- boolean
   * ...this.sameTrain(Train)...       -- boolean
   * ...this.sameBus(Bus)...           -- boolean
   * 
   * Methods of Fields:
   * 
   */

  // Template: Same as class + 
  /*
   * Parameters: 
   * ...that...    -- IVehicle
   * 
   * Methods on Parameters:
   * ...that.totalRevenue()...         -- double
   * ...that.fuelCost()...             -- double
   * ...that.perPassengerProfit()...   -- double
   * ...that.format()...               -- String
   * ...that.sameVehicle(IVehicle)...  -- boolean
   * ...that.sameAirplane(Airplane)... -- boolean
   * ...that.sameTrain(Train)...       -- boolean
   * ...that.sameBus(Bus)...           -- boolean
   * 
   */
  // is this Bus the same as that IVehicle?
  public boolean sameVehicle(IVehicle that) {
    return that.sameBus(this);
  }

  // Template: Same as class +
  /*
   * Parameters: 
   * ...that...    -- Bus
   * 
   * Methods on Parameters:
   * ...that.totalRevenue()...         -- double
   * ...that.fuelCost()...             -- double
   * ...that.perPassengerProfit()...   -- double
   * ...that.format()...               -- String
   * ...that.sameVehicle(IVehicle)...  -- boolean
   * ...that.sameAirplane(Airplane)... -- boolean
   * ...that.sameTrain(Train)...       -- boolean
   * ...that.sameBus(Bus)...           -- boolean
   * 
   */
  // is this Bus the same as that bus?
  public boolean sameBus(Bus that) {
    return (this.name.equals(that.name) && (this.passengerCapacity == that.passengerCapacity)
        && (that.fare - this.fare < 0.01) && (that.fuelCapacity - this.fuelCapacity < 0.01)
        && (this.length == that.length));
  }
}

class ExamplesVehicle {
  IVehicle dreamliner = new Airplane("Boeing 787", 242, 835.0, 33340, "B788", false);
  IVehicle commuterRail = new Train("MPI HSP46", 500, 11.50, 2000, 6, 1435);
  IVehicle silverLine = new Bus("Neoplan AN460LF", 77, 1.70, 100, 60);

  // Some airplanes
  IVehicle air1 = new Airplane("Woosh", 81, 9001.2, 4300, "Y827", false);
  IVehicle air2 = new Airplane("Soar", 364, 400.0, 43020, "A123", true);

  // Some trains
  IVehicle train1 = new Train("Blue train", 300, 2.75, 1200, 9, 1300);
  IVehicle train2 = new Train("Red train", 200, 1.92, 3200, 23, 8700);

  // Some buses
  IVehicle bus1 = new Bus("Green bus", 72, 1.23, 240, 20);
  IVehicle bus2 = new Bus("Pink bus", 2304, 5.98, 32039, 650);

  // testing total revenue method
  boolean testTotalRevenue(Tester t) {
    return t.checkInexact(this.dreamliner.totalRevenue(), 242 * 835.0, .0001)
        && t.checkInexact(this.commuterRail.totalRevenue(), 500 * 11.5, .0001)
        && t.checkInexact(this.silverLine.totalRevenue(), 77 * 1.7, 0.0001)

        && t.checkInexact(this.air1.totalRevenue(), 81 * 9001.2, .0001)
        && t.checkInexact(this.air2.totalRevenue(), 364 * 400.0, .0001)

        && t.checkInexact(this.train1.totalRevenue(), 300 * 2.75, .0001)
        && t.checkInexact(this.train2.totalRevenue(), 200 * 1.92, .0001)

        && t.checkInexact(this.bus1.totalRevenue(), 72 * 1.23, .0001)
        && t.checkInexact(this.bus2.totalRevenue(), 2304 * 5.98, .0001);
  }

  // testing the total cost of fuel
  boolean testFuelCost(Tester t) {
    return t.checkInexact(this.dreamliner.fuelCost(), 33340 * 1.94, .0001)
        && t.checkInexact(this.commuterRail.fuelCost(), 2000 * 2.55, .0001)
        && t.checkInexact(this.silverLine.fuelCost(), 100 * 2.55, 0.0001)

        && t.checkInexact(this.air1.fuelCost(), 4300 * 1.94, .0001)
        && t.checkInexact(this.air2.fuelCost(), 43020 * 1.94, .0001)

        && t.checkInexact(this.train1.fuelCost(), 1200 * 2.55, .0001)
        && t.checkInexact(this.train2.fuelCost(), 3200 * 2.55, .0001)

        && t.checkInexact(this.bus1.fuelCost(), 240 * 2.55, .0001)
        && t.checkInexact(this.bus2.fuelCost(), 32039 * 2.55, .0001);
  }

  // testing the per passenger profit
  boolean testPerPassengerProfit(Tester t) {
    return t.checkInexact(this.dreamliner.perPassengerProfit(),
        ((242 * 835.0) - (33340 * 1.94)) / 242, .0001)
        && t.checkInexact(this.commuterRail.perPassengerProfit(),
            ((500 * 11.5) - (2000 * 2.55)) / 500, .0001)
        && t.checkInexact(this.silverLine.perPassengerProfit(), ((77 * 1.7) - (100 * 2.55)) / 77,
            0.0001)

        && t.checkInexact(this.air1.perPassengerProfit(), ((81 * 9001.2) - (4300 * 1.94)) / 81,
            .0001)
        && t.checkInexact(this.air2.perPassengerProfit(), ((364 * 400.0) - (43020 * 1.94)) / 364,
            .0001)

        && t.checkInexact(this.train1.perPassengerProfit(), ((300 * 2.75) - (1200 * 2.55)) / 300,
            .0001)
        && t.checkInexact(this.train2.perPassengerProfit(), ((200 * 1.92) - (3200 * 2.55)) / 200,
            .0001)

        && t.checkInexact(this.bus1.perPassengerProfit(), ((72 * 1.23) - (240 * 2.55)) / 72, .0001)
        && t.checkInexact(this.bus2.perPassengerProfit(), ((2304 * 5.98) - (32039 * 2.55)) / 2304,
            .0001);
  }

  // testing name and passenger capacity format
  boolean testFormat(Tester t) {
    return t.checkExpect(this.dreamliner.format(), "Boeing 787, 242.")
        && t.checkExpect(this.commuterRail.format(), "MPI HSP46, 500.")
        && t.checkExpect(this.silverLine.format(), "Neoplan AN460LF, 77.")

        && t.checkExpect(this.air1.format(), "Woosh, 81.")
        && t.checkExpect(this.air2.format(), "Soar, 364.")

        && t.checkExpect(this.train1.format(), "Blue train, 300.")
        && t.checkExpect(this.train2.format(), "Red train, 200.")

        && t.checkExpect(this.bus1.format(), "Green bus, 72.")
        && t.checkExpect(this.bus2.format(), "Pink bus, 2304.");
  }

  // testing equality
  boolean testEquality(Tester t) {
    return t.checkExpect(dreamliner.sameVehicle(dreamliner), true)
        && t.checkExpect(dreamliner.sameAirplane((Airplane)dreamliner), true)
        && t.checkExpect(dreamliner.sameAirplane((Airplane)air1), false)
        && t.checkExpect(dreamliner.sameVehicle(commuterRail), false)
        && t.checkExpect(dreamliner.sameVehicle(air1), false)

        && t.checkExpect(commuterRail.sameVehicle(commuterRail), true)
        && t.checkExpect(commuterRail.sameTrain((Train)(commuterRail)), true)
        && t.checkExpect(commuterRail.sameTrain((Train)(train1)), false)
        && t.checkExpect(commuterRail.sameVehicle(dreamliner), false)
        && t.checkExpect(commuterRail.sameVehicle(train1), false)

        && t.checkExpect(silverLine.sameVehicle(silverLine), true)
        && t.checkExpect(silverLine.sameBus((Bus)silverLine), true)
        && t.checkExpect(silverLine.sameBus((Bus)bus1), false)
        && t.checkExpect(silverLine.sameVehicle(commuterRail), false)
        && t.checkExpect(silverLine.sameVehicle(bus1), false);
  }
}