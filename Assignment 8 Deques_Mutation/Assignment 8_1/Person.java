
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

  // returns true if this Person has that as a direct buddy
  boolean hasDirectBuddy(Person that) {
    return this.buddies.contains(that);
  }

  // returns the number of people who will show up at the party
  // given by this person
  int partyCount() {
    return this.buddies.len() + this.buddies.countParty(new ConsLoBuddy(this, new MTLoBuddy()));
  }

  // returns the number of people who will show up at the party
  // helps the original function by keeping track of whose friends have already
  // been invited
  int partyCount(ILoBuddy soFar) {
    return this.buddies.len() + this.buddies.countParty(soFar.append(this));
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
  // TODO: Stub method
  double maxLikelihood(Person that) {
    return 0.0;
  }
}
