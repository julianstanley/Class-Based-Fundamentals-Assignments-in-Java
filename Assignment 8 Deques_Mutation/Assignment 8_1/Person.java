
// represents a Person with a user name and a list of buddies
class Person {

  String username;
  ILoBuddy buddies;
  Double diction;
  Double hearing;

  Person(String username) {
    this.username = username;
    this.buddies = new MTLoBuddy();
    this.diction = 0.0;
    this.hearing = 0.0;
  }

  Person(String username, Double diction, Double hearing) {
    this(username);
    this.diction = diction;
    this.hearing = hearing;
  }
  
  /*
   * Fields:
   * ...this.username...  -- String
   * ...this.buddies...   -- ILoBuddy
   * ...this.diction...   -- Double
   * ...this.hearing...   -- Double
   * 
   * Methods:
   *    * ...this.hasDirectBuddy(Person)...       -- boolean
   * ...this.partyCount()...                 -- int
   * ...this.partyList(ILoBuddy)...          -- ILoBuddy
   * ...this.countCommonBuddies(Person)...   -- int
   * ...this.countCommonHelp(ILoBuddy)...    -- int
   * ...this.hasExtendedBuddy(Person)...     -- boolean
   * ...this.addBudy(Person)...              -- void
   * ...this.maxLikelihood(Person)...        -- double
   * ...this.recieve(double)...              -- double
   * 
   * Methods on fields:
   *    * ...this.buddies.append(Person)...                           -- ILoBuddy
   * ...this.buddies.append(ILoBuddy)...                         -- ILoBuddy
   * ...this.buddies.condense()...                               -- ILoBuddy
   * ...this.buddies.contains(Person)...                         -- boolean
   * ...this.buddies.overlap(ILoBuddy)...                        -- int
   * ...this.buddies.extendedSearch(Person, ILoBuddy)...         -- boolean
   * ...this.buddies.len()...                                    -- int
   * ...this.buddies.partyList(ILoBuddy)...                      -- ILoBuddy
   * ...this.buddies.maxLikelihood(Person, double, ILoBuddy)...  -- double
   * 
   */

  
  /*
   * Parameters: ...that... -- Person
   * 
   * Methods on Parameters: ...that.hasDirectBuddy(Person)... -- boolean
   * ...that.partyCount()... -- int ...that.partyList(ILoBuddy)... -- ILoBuddy
   * ...that.countCommonBuddies(Person)... -- int
   * ...that.countCommonHelp(ILoBuddy)... -- int
   * ...that.hasExtendedBuddy(Person)... -- boolean ...that.addBudy(Person)...
   * -- void ...that.maxLikelihood(Person)... -- double
   * ...that.recieve(double)... -- double
   */
  // returns true if this Person has that as a direct buddy
  boolean hasDirectBuddy(Person that) {
    return this.buddies.contains(that);
  }

  // Template: Same as class
  // returns the number of people who will show up at the party
  // given by this person
  // Strategy: accumulate myself. The list.countParty will take care of the
  // counting people once it's done accumulating them.
  int partyCount() {
    return this.partyList(new MTLoBuddy()).len();
  }

  // Returns the list of people this person can invite to a party
  ILoBuddy partyList(ILoBuddy list) {
    return this.buddies.partyList(list.append(this)).condense();
  }

  // returns the number of people that are direct buddies
  // of both this and that person
  int countCommonBuddies(Person that) {
    return that.countCommonHelp(this.buddies);
  }

  // Returns the number of direct buddies in this person
  // and also in the given list
  int countCommonHelp(ILoBuddy that) {
    return that.overlap(this.buddies);
  }

  // will the given person be invited to a party
  // organized by this person?
  boolean hasExtendedBuddy(Person that) {
    return this.hasDirectBuddy(that) || this.buddies.extendedSearch(that, new MTLoBuddy());
  }

  // Will the given person be invited to the party? Helps by keeping track of
  // whose friend lists we have already checked
  boolean hasExtendedBuddy(Person that, ILoBuddy soFar) {
    return this.hasDirectBuddy(that) || this.buddies.extendedSearch(that, soFar);
  }

  // EFFECT:
  // Change this person's buddy list so that it includes the given person
  void addBuddy(Person buddy) {
    this.buddies = this.buddies.append(buddy);
  }

  // Maximum likelihood that this person can convey a message to that person
  double maxLikelihood(Person that) {
    if (this.equals(that)) {
      return 1.0;
    }
    else if (!this.hasExtendedBuddy(that)) {
      return 0.0;
    }
    else {
      return this.buddies.maxLikelihood(that, this.diction, new MTLoBuddy().append(this));
    }
  }
  
  // Maximum likelihood that this person can convey a message to that person, keeps track of soFar
  double maxLikelihood(Person that, ILoBuddy soFar) {
    if (this.equals(that)) {
      return 1.0;
    }
    else if (!this.hasExtendedBuddy(that)) {
      return 0.0;
    }
    else {
      return this.buddies.maxLikelihood(that, this.diction, soFar.append(this));
    }
  }

  // Returns the likelihood of this person recieving a message at the given
  // intensity
  double recieve(double msgInt) {
    return this.hearing * msgInt;
  }

}
