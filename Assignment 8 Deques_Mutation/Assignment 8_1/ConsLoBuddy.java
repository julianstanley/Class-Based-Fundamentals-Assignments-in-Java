// represents a list of Person's buddies
class ConsLoBuddy implements ILoBuddy {

  Person first;
  ILoBuddy rest;

  ConsLoBuddy(Person first, ILoBuddy rest) {
    this.first = first;
    this.rest = rest;
  }

  // Add the given person to this list of buddies
  public ILoBuddy append(Person that) {
    return new ConsLoBuddy(this.first, this.rest.append(that));
  }

  // Does this non-empty list of people contain the given person?
  public boolean contains(Person that) {
    return this.first == that || this.rest.contains(that);
  }

  // How many people overlap between these two lists?
  public int overlap(ILoBuddy that) {
    if (that.contains(this.first)) {
      return 1 + this.rest.overlap(that);
    }

    else {
      return this.rest.overlap(that);
    }
  }

  // Is the given person within any of the direct friend lists of any of the
  // people in this list?
  public boolean extendedSearch(Person that, ILoBuddy soFar) {
    if (soFar.contains(this.first)) {
      return this.rest.extendedSearch(that, soFar);
    }
    else {
      return this.first.hasExtendedBuddy(that, soFar.append(this.first))
          || this.rest.extendedSearch(that, soFar.append(this.first));
    }
  }

  // Find the length of this list
  public int len() {
    return 1 + this.rest.len();
  }

  // Find the number of people invited to the party from this non-empty list
  public int countParty(ILoBuddy soFar) {
    if (soFar.contains(this.first)) {
      return this.rest.countParty(soFar);
    }

    else {
      return this.first.partyCount(soFar) + this.rest.countParty(soFar);
    }
  }
}
