// represents an empty list of Person's buddies
class MTLoBuddy implements ILoBuddy {

  // Add the given person to this list of buddies
  public ILoBuddy append(Person that) {
    return new ConsLoBuddy(that, this);
  }

  // Does this empty list of people contain the given person?
  public boolean contains(Person that) {
    return false;
  }

  // How many people overlap between these two lists?
  public int overlap(ILoBuddy that) {
    return 0;
  }

  // Is the given person within any of the direct friend lists of any of the
  // people in this list?
  public boolean extendedSearch(Person that, ILoBuddy soFar) {
    return false;
  }

  // Find the length of this empty list
  public int len() {
    return 0;
  }

  // Find the number of people invited to the party from this empty list
  public int countParty(ILoBuddy soFar) {
    return 0;
  }
}
