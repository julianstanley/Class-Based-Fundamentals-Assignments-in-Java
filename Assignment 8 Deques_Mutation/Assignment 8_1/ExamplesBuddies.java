import tester.Tester;

// runs tests for the buddies problem
public class ExamplesBuddies {
  Person ann = new Person("Ann");
  Person bob = new Person("Bob");
  Person cole = new Person("Cole");
  Person dan = new Person("Dan");
  Person ed = new Person("Ed");
  Person fay = new Person("Fay");
  Person gabi = new Person("Gabi");
  Person hank = new Person("Hank");
  Person jan = new Person("Jan");
  Person kim = new Person("Kim");
  Person len = new Person("Len");

  // EFFECT:
  // Initializes all data
  void init() {
    initBuddies(); 
  }
  
  // EFFECT:
  // Initializes people to have the list of friends that they are supposed to
  // have
  void initBuddies() {
    this.ann.addBuddy(this.bob);
    this.ann.addBuddy(this.cole);
    this.bob.addBuddy(this.ann);
    this.bob.addBuddy(this.ed);
    this.bob.addBuddy(this.hank);
    this.cole.addBuddy(this.dan);
    this.dan.addBuddy(this.cole);
    this.ed.addBuddy(this.fay);
    this.fay.addBuddy(this.ed);
    this.fay.addBuddy(this.gabi);
    this.gabi.addBuddy(this.ed);
    this.gabi.addBuddy(this.fay);
    this.jan.addBuddy(this.kim);
    this.jan.addBuddy(this.len);
    this.kim.addBuddy(this.jan);
    this.kim.addBuddy(this.len);
    this.len.addBuddy(this.jan);
    this.len.addBuddy(this.kim);
  }

  boolean testInit(Tester t) {
    init();
    return t.checkExpect(ann.hasDirectBuddy(bob), true)
        && t.checkExpect(ann.hasDirectBuddy(hank), false)
        && t.checkExpect(ann.hasDirectBuddy(len), false)
        && t.checkExpect(ann.countCommonBuddies(dan), 1)
        && t.checkExpect(ann.countCommonBuddies(hank), 0)
        && t.checkExpect(len.countCommonBuddies(ann), 0)
        && t.checkExpect(gabi.countCommonBuddies(bob), 1)
        && t.checkExpect(ann.hasExtendedBuddy(hank), true)
        && t.checkExpect(ann.hasExtendedBuddy(len), false) && t.checkExpect(ann.partyCount(), 10);
  }
}