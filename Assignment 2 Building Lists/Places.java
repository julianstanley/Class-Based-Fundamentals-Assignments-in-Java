import tester.*; 

interface IFeature {
  // Find the total capacity of this feature
  int totalCapacity(); 
  
  // Find the average rating of this feature
  double foodinessSum(); 
    
  // Find the number of restaurants in this feature
  int countRestaurants(); 
  
  // Return the restaurant info of this feature
  String restaurantInfo(); 
}

// To represent a restaurant feature
class Restaurant implements IFeature {
  String name;
  String type;
  double averageRating; 
  
  Restaurant(String name, String type, double averageRating) {
    this.name = name;
    this.type = type;
    this.averageRating = averageRating;
  }
  
  /*
   Fields:
   ...this.name...                 -- String
   ...this.type...                 -- String
   ...this.averageRating...        -- double
   
   Methods:
   ...this.totalCapacity() ...    -- int
   ...this.foodinessSum()...      -- double
   ...this.countRestaurants()...  -- int
   ...this.restaurantInfo()...    -- String
   
   Methods on fields:
   
   */
  
  // Find the total capacity of this restaurant
  public int totalCapacity() {
    return 0; 
  }
  
  // Return the sum of the restaurant ratings of this restaurant
  public double foodinessSum() {
    return this.averageRating; 
  }
  
  // Return the number of restaurants in this restaurant
  public int countRestaurants() {
    return 1; 
  }
  
  // Return the restaurant info of this restaurant
  public String restaurantInfo() {
    return this.name + " (" + this.type + ")"; 
  }
}

// To represent a venue feature
class Venue implements IFeature {
  String name;
  String type; 
  int capacity; 
  
  Venue(String name, String type, int capacity) {
    this.name = name;
    this.type = type;
    this.capacity = capacity; 
  }
  
  /*
   Fields:
   ...this.name...               -- String
   ...this.type...               -- String
   ...this.capacity...      -- int
   
   Methods:
   ...this.totalCapacity()...    -- int
   ...this.foodinessSum()...     -- double
   ...this.countRestaurants()... -- int
   ...this.restaurantInfo()...   -- String
   Methods on fields:
   
   */
  
  // Find the total capacity of this venue
  public int totalCapacity() {
    return this.capacity;
  }
  
  // Return the sum of the restaurant ratings of this venue
  public double foodinessSum() {
    return 0.0; 
  }
  
  // Return the number of restaurants in this venue
  public int countRestaurants() { 
    return 0; 
  }
  
  // Return the restaurant info of this venue
  public String restaurantInfo() {
    return ""; 
  }
}

// To represent a shuttlebus feature
class ShuttleBus implements IFeature {
  String name;
  Place destination;
  
  ShuttleBus(String name, Place destination) {
    this.name = name;
    this.destination = destination; 
  }
  
  /* 
   Fields:
   ...this.name...                           -- String
   ...this.destination...                    -- Place
   
   Methods:
   ...this.totalCapacity()...                -- int
   ...this.foodinessSum()...                 -- double
   ...this.countRestaurants()...             -- int
   ...this.restaurantInfo()...               -- String
   
   Methods on fields
   ...this.destination.totalCapacity()...    -- int
   ...this.destination.foodinessSum()...     -- double
   ...this.destination.countRestaurants()... -- int
   ...this.destination.foodinessRating()...  -- double
   ...this.destination.restaurantInfo()...   -- String
    */
  
  // Find the total capacity of this shuttlebus
  public int totalCapacity() {
    return this.destination.totalCapacity(); 
  }
  
  // Return the sum of the restaurant ratings of this shuttlebus
  public double foodinessSum() {
    return this.destination.foodinessSum(); 
  }
  
  // Return the number of restaurants in this shuttlebus
  public int countRestaurants() {
    return this.destination.countRestaurants(); 
  }
  
  // Return the restaurant info in this shuttlebus
  public String restaurantInfo() {
    return this.destination.restaurantInfo(); 
  }
}

// To represent a place that contains features
class Place {
  String name;
  ILoFeature features;
  
  Place(String name, ILoFeature features) {
    this.name = name;
    this.features = features;
  }
  
  /* 
   Fields:
   ...this.name...                         -- String
   ...this.features...                     -- ILoFeature
   
   Methods:
   ...this.totalCapacity()...              -- int
   ...this.foodinessSum()...               -- double
   ...this.countRestaurants()...           -- int
   ...this.foodinessRating()...            -- double
   ...this.restaurantInfo()...             -- String
   
   Methods on fields:
   ...this.features.totalCapacity()...     -- int
   ...this.features.foodinessSum()...      -- double
   ...this.features.countResaurants()...   -- int
   ...this.features.foodinessRating()...   -- double
   ...this.features.restaurantInfo()...    -- String
   */
  
  // Returns the total venue capacity of the features in this place
  public int totalCapacity() {
    return this.features.totalCapacity(); 
  }
  
  // Returns the sum of the restaurant ratings in this place
  public double foodinessSum() {
    return this.features.foodinessSum(); 
  }
 
  // Returns the number of restaurants in this place
  public int countRestaurants() {
    return this.features.countRestaurants(); 
  }
  
  // Returns the average restaurant rating in this place
  public double foodinessRating() {
    return this.features.foodinessRating(); 
  }
  
  // Returns the info of the restaurants in this place
  public String restaurantInfo() {
    return this.features.restaurantInfo(); 
  }
}

// Represents a list of features
interface ILoFeature {
  // find the totalCapacity of all features in the list
  int totalCapacity(); 
  
  // count the number of restaurants in this list
  public int countRestaurants(); 
  
  // find the sum of the restaurant ratings the features in this list
  double foodinessSum(); 
  
  // Returns the average restaurant rating of the features in this list
  double foodinessRating(); 
  
  // Returns the restaurant info of the features in this list
  String restaurantInfo(); 
}

// Represents a non-empty list of features
class ConsLoFeature implements ILoFeature {
  IFeature first; 
  ILoFeature rest;
  
  ConsLoFeature(IFeature first, ILoFeature rest) {
    this.first = first;
    this.rest = rest; 
  }
  
  /*
   Fields:
   ...this.first...                      -- IFeature
   ...this.rest...                       -- ILoFeature
   
   Methods:
   ...this.totalCapacity()...            -- int
   ...this.foodinessSum()...             -- double
   ...this.countRestaurants()...         -- int
   ...this.foodinessRating()...          -- double
   ...this.restaurantInfo()...           -- String
   
   Methods on fields:
   ...this.first.totalCapacity()...      -- int
   ...this.rest.totalCapacity()...       -- int
   ...this.first.foodinessSum()...       -- double
   ...this.rest.foodinessSum()...        -- double
   ...this.first.countRestaurants()...   -- int
   ...this.rest.countRestaurants()...    -- int
   ...this.first.restaurantInfo()...     -- String
   ...this.rest.restaurantInfo()...      -- String
   ...this.rest.foodinessRating()...     -- double
   */
  
  // Returns the total venue capacity of this non-empty list
  public int totalCapacity() {
    return this.first.totalCapacity() + this.rest.totalCapacity(); 
  }
  
  // find the sum of the restaurant ratings the features in this non-empty list
  public double foodinessSum() {
    return this.first.foodinessSum() + this.rest.foodinessSum();
  }
  
  //Returns the number of restaurants in this non-empty list of features
  public int countRestaurants() {
    return this.first.countRestaurants() + this.rest.countRestaurants(); 
  }
 
  // Returns the average restaurant rating of the features in this non-empty list
  public double foodinessRating() {
    return (this.first.foodinessSum() + this.rest.foodinessSum()) 
        /
        (this.first.countRestaurants() + this.rest.countRestaurants()); 
  }
  
  // Returns the restaurant info of the features in this non-empty list
  public String restaurantInfo() {
    if (this.rest.restaurantInfo().equals("")) {
      return this.first.restaurantInfo(); 
    }
    else if (this.first.restaurantInfo().equals("")) {
      return this.rest.restaurantInfo(); 
    }
    else {
      return this.first.restaurantInfo() + ", " + this.rest.restaurantInfo(); 
    }
  }   
}

// Represents an empty list of features
class MtLoFeature implements ILoFeature {
  /*
   Fields:
   
   Methods:
   ...this.totalCapacity()...            -- int
   ...this.foodinessSum()...             -- double
   ...this.countRestaurants()...         -- int
   ...this.foodinessRating()...          -- double
   ...this.restaurantInfo()...           -- String
   
   Methods on fields:
   */
  
  // Returns the total venue capacity of this empty list
  public int totalCapacity() {
    return 0; 
  }
  
  // find the sum of the restaurant ratings the features in this empty list
  public double foodinessSum() {
    return 0;
  }
  
  //Returns the number of restaurants in this empty list of features
  public int countRestaurants() {
    return 0;
  }
 
  // Returns the average restaurant rating of the features in this empty list
  public double foodinessRating() {
    return 0; 
  }
  
  // Returns the restaurant info of the features in this empty list
  public String restaurantInfo() {
    return ""; 
  }
} 


class ExamplesPlaces {
  
  // North End
  IFeature tdGarden = new Venue("TD Garden", "stadium", 19580); 
  IFeature mikesPastry = new Restaurant("Mike's Pastry", "cannolis", 4.3);
  ILoFeature northEndFeatures = new ConsLoFeature(tdGarden, 
      new ConsLoFeature(mikesPastry, new MtLoFeature())); 
  Place northEnd = new Place("North End", northEndFeatures); 
  
  // Harvard
  IFeature freshmen15 = new ShuttleBus("Freshmen-15", northEnd); 
  IFeature legal = new Restaurant("Legal Sea Foods", "seafood", 3.9); 
  IFeature stadium = new Venue("Harvard Stadium", "football", 30323);
  ILoFeature harvardFeatures = new ConsLoFeature(freshmen15,
      new ConsLoFeature(legal, 
          new ConsLoFeature(stadium, 
              new MtLoFeature()))); 
  Place harvard = new Place("Harvard", harvardFeatures); 
  
  // South Station
  IFeature littleItalyExpress = new ShuttleBus("Little Italy Express", northEnd); 
  IFeature anne = new Restaurant("Auntie Anne's", "pretzels", 4.0);
  IFeature crimsonCruiser = new ShuttleBus("Crimson Cruiser", harvard); 
  IFeature bostonCommon = new Venue("Boston Common", "public", 150000); 
  ILoFeature southStationFeatures = new ConsLoFeature(littleItalyExpress,
      new ConsLoFeature(anne,
          new ConsLoFeature(crimsonCruiser, 
              new ConsLoFeature(bostonCommon,
                  new MtLoFeature())))); 
  Place southStation = new Place("South Station", southStationFeatures); 
  
  // Logan Airport
  IFeature quiznos = new Restaurant("Quiznos", "sandwiches", 3.2);
  IFeature starbucks = new Restaurant("Starbucks", "coffee", 4.1); 
  IFeature airportShuttle = new ShuttleBus("airport shuttle", southStation);
  ILoFeature loganFeatures = new ConsLoFeature(quiznos,
      new ConsLoFeature(starbucks,
          new ConsLoFeature(airportShuttle,
              new MtLoFeature())));
  Place loganAirport = new Place("Logan airport", loganFeatures); 
  
  boolean testPlaces(Tester t) {
    return t.checkExpect(tdGarden.totalCapacity(), 19580)
        && t.checkExpect(mikesPastry.totalCapacity(), 0)
        && t.checkExpect(freshmen15.totalCapacity(), 19580)
        
        && t.checkExpect(northEndFeatures.totalCapacity(), 19580)
        && t.checkExpect(harvardFeatures.totalCapacity(), 19580 + 30323)
        // North End is reachable from multiple shuttles
        && t.checkExpect(loganFeatures.totalCapacity(), 2 * 19580 + 30323 + 150000)
        
        && t.checkExpect(northEnd.totalCapacity(), 19580)
        && t.checkExpect(harvard.totalCapacity(), 19580 + 30323)
        // North End is reachable from multiple shuttles
        && t.checkExpect(loganAirport.totalCapacity(), 2 * 19580 + 30323 + 150000)
        
        && t.checkExpect(tdGarden.countRestaurants(), 0)
        && t.checkExpect(mikesPastry.countRestaurants(), 1)
        
        && t.checkExpect(northEndFeatures.countRestaurants(), 1) 
        && t.checkExpect(harvardFeatures.countRestaurants(), 2)
        && t.checkExpect(loganFeatures.countRestaurants(), 6)
        
        && t.checkExpect(northEnd.countRestaurants(), 1) 
        && t.checkExpect(harvard.countRestaurants(), 2)
        && t.checkExpect(loganAirport.countRestaurants(), 6)
      
        
        && t.checkInexact(tdGarden.foodinessSum(), 0.0,0.01)
        && t.checkInexact(mikesPastry.foodinessSum(), 4.3, 0.01)
        
        && t.checkInexact(northEndFeatures.foodinessSum(), 4.3, 0.01)
        && t.checkInexact(harvardFeatures.foodinessSum(), 8.2, 0.01)
        && t.checkInexact(loganFeatures.foodinessSum(), 23.8, 0.01)
        
        && t.checkInexact(northEnd.foodinessSum(), 4.3, 0.01)
        && t.checkInexact(harvard.foodinessSum(), 8.2, 0.01)
        && t.checkInexact(loganAirport.foodinessSum(), 23.8, 0.01)
        
        && t.checkInexact(northEndFeatures.foodinessRating(), 4.3, 0.01)
        && t.checkInexact(harvardFeatures.foodinessRating(), 4.1, 0.01)
        && t.checkInexact(loganFeatures.foodinessRating(), 3.97, 0.01)
        
        && t.checkInexact(northEnd.foodinessRating(), 4.3, 0.01)
        && t.checkInexact(harvard.foodinessRating(), 4.1, 0.01)
        && t.checkInexact(loganAirport.foodinessRating(), 3.97, 0.01)
        
        && t.checkExpect(tdGarden.restaurantInfo(), "")
        && t.checkExpect(mikesPastry.restaurantInfo(), "Mike's Pastry (cannolis)")
        && t.checkExpect(freshmen15.restaurantInfo(), "Mike's Pastry (cannolis)")
        
        && t.checkExpect(northEndFeatures.restaurantInfo(), "Mike's Pastry (cannolis)")
        && t.checkExpect(harvardFeatures.restaurantInfo(), 
            "Mike's Pastry (cannolis), Legal Sea Foods (seafood)")
        && t.checkExpect(
            loganFeatures.restaurantInfo(), "Quiznos (sandwiches), Starbucks (coffee), "
                + "Mike's Pastry (cannolis), Auntie Anne's (pretzels), "
                + "Mike's Pastry (cannolis), Legal Sea Foods (seafood)")
        
        && t.checkExpect(northEnd.restaurantInfo(), "Mike's Pastry (cannolis)")
        && t.checkExpect(harvard.restaurantInfo(), 
            "Mike's Pastry (cannolis), Legal Sea Foods (seafood)")
        && t.checkExpect(
            loganAirport.restaurantInfo(), "Quiznos (sandwiches), Starbucks (coffee), "
                + "Mike's Pastry (cannolis), Auntie Anne's (pretzels), "
                + "Mike's Pastry (cannolis), Legal Sea Foods (seafood)");     
  }
}

