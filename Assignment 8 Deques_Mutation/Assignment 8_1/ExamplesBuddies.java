import tester.Tester;

// runs tests for the buddies problem
public class ExamplesBuddies {
  Person ann = new Person("Ann", 0.5, 0.5);
  Person bob = new Person("Bob", 0.3, 0.4);
  Person cole = new Person("Cole", 0.8, 0.1);
  Person dan = new Person("Dan", 0.1, 0.8);
  Person ed = new Person("Ed", 0.7, 0.2);
  Person fay = new Person("Fay", 0.1, 0.3);
  Person gabi = new Person("Gabi", 0.8, 0.9);
  Person hank = new Person("Hank", 0.1, 0.2);
  Person jan = new Person("Jan", 0.9, 0.9);
  Person kim = new Person("Kim", 1.0, 1.0);
  Person len = new Person("Len", 0.4, 0.6);
  ILoBuddy mt = new MTLoBuddy(); 

  // EFFECT:
  // Initializes all data
  void init() {
    this.ann.buddies = mt;
    this.bob.buddies = mt;
    this.cole.buddies = mt;
    this.dan.buddies = mt;
    this.ed.buddies = mt;
    this.ed.buddies = mt;
    this.fay.buddies = mt;
    this.gabi.buddies = mt;
    this.hank.buddies = mt;
    this.jan.buddies = mt;
    this.kim.buddies = mt;
    this.len.buddies = mt;
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

  // Tests that addBuddy and the initialization went as planned
  void testInit(Tester t) {
    init();
    t.checkExpect(ann.buddies, new ConsLoBuddy(bob,
        new ConsLoBuddy(cole, this.mt))); 
    t.checkExpect(bob.buddies, new ConsLoBuddy(ann,
        new ConsLoBuddy(ed, 
            new ConsLoBuddy(hank, this.mt)))); 
    t.checkExpect(cole.buddies, new ConsLoBuddy(dan, this.mt)); 
    t.checkExpect(dan.buddies, new ConsLoBuddy(cole, this.mt)); 
    t.checkExpect(ed.buddies, new ConsLoBuddy(fay, this.mt)); 
    t.checkExpect(fay.buddies, new ConsLoBuddy(ed,
        new ConsLoBuddy(gabi, this.mt))); 
    t.checkExpect(gabi.buddies, new ConsLoBuddy(ed,
        new ConsLoBuddy(fay, this.mt))); 
    t.checkExpect(jan.buddies, new ConsLoBuddy(kim,
        new ConsLoBuddy(len, this.mt))); 
    t.checkExpect(kim.buddies, new ConsLoBuddy(jan,
        new ConsLoBuddy(len, this.mt)));
    t.checkExpect(len.buddies, new ConsLoBuddy(jan,
        new ConsLoBuddy(kim, this.mt))); 
  }

  // Tests: returns true if this person has that as a direct buddy
  void testDirectBuddy(Tester t) {
    init();
    t.checkExpect(ann.hasDirectBuddy(ann), false);
    t.checkExpect(ann.hasDirectBuddy(bob), true);
    t.checkExpect(ann.hasDirectBuddy(ed), false);
    t.checkExpect(ann.hasDirectBuddy(len), false);
  }

  // Tests: returns the number of people who will show up at the party given by
  // this person
  void testPartyCount(Tester t) {
    init();
    t.checkExpect(hank.partyCount(), 1);
    t.checkExpect(gabi.partyCount(), 3);
    t.checkExpect(jan.partyCount(), 3);
    t.checkExpect(ann.partyCount(), 8);
    t.checkExpect(bob.partyCount(), 8);
  }

  // Tests: returns the list of people this person will invite to a party
  void testPartyList(Tester t) {
    init();
    t.checkExpect(hank.partyList(mt), new ConsLoBuddy(hank, mt)); 
    t.checkExpect(jan.partyList(mt), new ConsLoBuddy(jan, 
        new ConsLoBuddy(kim,
            new ConsLoBuddy(len, mt)))); 
    t.checkExpect(ann.partyList(mt), new ConsLoBuddy(ann,
        new ConsLoBuddy(bob,
            new ConsLoBuddy(ed,
                new ConsLoBuddy(fay,
                    new ConsLoBuddy(gabi,
                        new ConsLoBuddy(hank,
                            new ConsLoBuddy(cole, 
                                new ConsLoBuddy(dan, mt))))))))); 
  }

  // Tests: returns the number of people that are direct buddies of both this
  // and that person
  void testCountCommon(Tester t) {
    init();
    t.checkExpect(ann.countCommonBuddies(dan), 1);
    t.checkExpect(ann.countCommonBuddies(hank), 0);
    t.checkExpect(len.countCommonBuddies(ann), 0);
    t.checkExpect(gabi.countCommonBuddies(bob), 1);
  }

  // Tests: returns the number of direct buddies of both this person and the
  // given list
  void testCommonHelp(Tester t) {
    init();
    t.checkExpect(dan.countCommonHelp(ann.buddies), 1);
    t.checkExpect(hank.countCommonHelp(ann.buddies), 0);
    t.checkExpect(ann.countCommonHelp(len.buddies), 0);
    t.checkExpect(bob.countCommonHelp(gabi.buddies), 1);
  }

  // Tests: will the given person be invited to this person's party?
  void testExtendedBuddy(Tester t) {
    init();
    t.checkExpect(ann.hasExtendedBuddy(ann), true);
    t.checkExpect(ann.hasExtendedBuddy(hank), true);
    t.checkExpect(ann.hasExtendedBuddy(len), false);
  }

  // Tests: will they be invited to the party? Keeping track of who we've
  // already checked
  void testExtendedHelp(Tester t) {
    init();
    t.checkExpect(ann.hasExtendedBuddy(ann, mt), true);
    t.checkExpect(ann.hasExtendedBuddy(hank, mt), true);
    t.checkExpect(ann.hasExtendedBuddy(len, mt), false);
  }

  // Tests: maximum likelihood that this person can convey a message to that
  // person
  void testMaxLikelihood(Tester t) {
    init();
    t.checkInexact(ann.maxLikelihood(ann), 1.0, 0.001);
    t.checkInexact(ann.maxLikelihood(bob), .2, 0.001);
    
    // Produces a stackoverflow
    t.checkInexact(ann.maxLikelihood(cole), .05, 0.001);
    t.checkInexact(ann.maxLikelihood(dan), 0.03200000000000001, 0.001);
    t.checkInexact(ann.maxLikelihood(ed), 0.012, 0.001);
    t.checkInexact(ann.maxLikelihood(fay), 0.0025199999999999997, 0.001);
    t.checkInexact(ann.maxLikelihood(gabi), 2.268E-4, 0.001);
    t.checkInexact(ann.maxLikelihood(hank), 0.012, 0.001);
    
    t.checkInexact(ann.maxLikelihood(jan), 0.0, 0.001);
    t.checkInexact(ann.maxLikelihood(kim), 0.0, 0.001);
    t.checkInexact(ann.maxLikelihood(len), 0.0, 0.001);

  }

  // Tests: likelihood that this person received the message
  void testRecieve(Tester t) {
    init();
    t.checkExpect(ann.recieve(1.0), 0.5);
    t.checkExpect(ann.recieve(.5), 0.25);
    t.checkExpect(dan.recieve(1), 0.8);
    t.checkExpect(dan.recieve(.25), 0.2);
  }
  
  // Some useful declarations for the following tests
  ILoBuddy annList = new ConsLoBuddy(ann, mt);
  ILoBuddy bobList = new ConsLoBuddy(bob, mt); 
  ILoBuddy annBobList = new ConsLoBuddy(ann, new ConsLoBuddy(bob, mt)); 
  ILoBuddy bobAnnList = new ConsLoBuddy(bob, new ConsLoBuddy(ann, mt)); 
  
  
  // Tests: Appending a person
  void testAppendPerson(Tester t) {
    init();
    t.checkExpect(mt.append(ann), annList); 
    t.checkExpect(annList.append(bob), annBobList); 
  }
  
  // Tests: Appending a list of people
  void testAppendList(Tester t) {
    init();
    t.checkExpect(mt.append(annList), annList);
    t.checkExpect(annList.append(annBobList), new ConsLoBuddy(ann, 
        new ConsLoBuddy(ann, 
            new ConsLoBuddy(bob, mt)))); 
  }
  
  // Tests: condensing a list of people
  void testCondense(Tester t) {
    init();
    t.checkExpect(mt.condense(), mt);
    t.checkExpect(annList.condense(), annList);
    t.checkExpect(annList.append(annBobList).condense(), annBobList); 
  }
  
  // Tests: list contains
  void testContains(Tester t) {
    init();
    t.checkExpect(mt.contains(ann), false);
    t.checkExpect(annList.contains(bob), false);
    t.checkExpect(annList.contains(ann), true);
    t.checkExpect(annBobList.contains(bob), true);
  }
  
  // Tests: List overlap
  void testOverlap(Tester t) {
    init();
    t.checkExpect(mt.overlap(mt), 0);
    t.checkExpect(mt.overlap(annList), 0);
    t.checkExpect(annList.overlap(annBobList), 1);
    t.checkExpect(annList.overlap(bobList), 0);
    t.checkExpect(annBobList.overlap(bobAnnList), 2);
  }
  
  // Tests: list extendedSearch
  void testExtended(Tester t) {
    init();
    t.checkExpect(ann.buddies.extendedSearch(ann, mt), true);
    t.checkExpect(ann.buddies.extendedSearch(hank, mt), true);
    t.checkExpect(ann.buddies.extendedSearch(len, mt), false);
  }
  
  // Tests: length of a list
  void testLen(Tester t) {
    init(); 
    t.checkExpect(mt.len(), 0);
    t.checkExpect(annList.len(), 1);
    t.checkExpect(annBobList.len(), 2);
  }
  
  // Tests: Party list
  void testPartyListL(Tester t) {
    init();

    // These should pass by themselves, but they have duplicates:
    t.checkExpect(hank.buddies.partyList(mt), mt);  
    t.checkExpect(ann.buddies.partyList(annList).condense(), new ConsLoBuddy(ann,
        new ConsLoBuddy(bob,
            new ConsLoBuddy(ed,
                new ConsLoBuddy(fay,
                    new ConsLoBuddy(gabi,
                        new ConsLoBuddy(hank,
                            new ConsLoBuddy(cole, 
                                new ConsLoBuddy(dan, mt))))))))); 
                                     
  }
  
  // Tests: maxLikeliHood on a list
  void testMaxList(Tester t) {
    init();
    t.checkExpect(mt.maxLikelihood(ann, 1, mt), 0.0);
    t.checkExpect(annList.maxLikelihood(ann, 1, mt), 0.5);
    t.checkExpect(annList.maxLikelihood(bob, 1, mt), 0.1);
  }
}


