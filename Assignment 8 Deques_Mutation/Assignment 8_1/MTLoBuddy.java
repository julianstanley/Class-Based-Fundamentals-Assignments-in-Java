// represents an empty list of Person's buddies
class MTLoBuddy implements ILoBuddy {

  
  /*
   * Fields:
   * 
   * Methods:
   * ...this.append(Person)...                           -- ILoBuddy
   * ...this.append(ILoBuddy)...                         -- ILoBuddy
   * ...this.condense()...                               -- ILoBuddy
   * ...this.contains(Person)...                         -- boolean
   * ...this.overlap(ILoBuddy)...                        -- int
   * ...this.extendedSearch(Person, ILoBuddy)...         -- boolean
   * ...this.len()...                                    -- int
   * ...this.partyList(ILoBuddy)...                      -- ILoBuddy
   * ...this.maxLikelihood(Person, double, ILoBuddy)...  -- double
   * 
   * Methods on Fields:
   */

  // Add the given person to this list of buddies
  public ILoBuddy append(Person that) {
    return new ConsLoBuddy(that, this);
  }

  // Append the given empty list onto the end of this list
  public ILoBuddy append(ILoBuddy that) {
    return that;
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

  // Returns the list of people this empty list can invite to a party
  public ILoBuddy partyList(ILoBuddy list) {
    return list;
  }

  // Removes duplicates from this empty list
  public ILoBuddy condense() {
    return this;
  }

  // Returns the maximum likelihood of getting the message to the target thru
  // this empty list
  public double maxLikelihood(Person target, double dict, ILoBuddy soFar) {
    return 0.0;
  }
}
