// represents a list of Person's buddies
class ConsLoBuddy implements ILoBuddy {

  Person first;
  ILoBuddy rest;

  ConsLoBuddy(Person first, ILoBuddy rest) {
    this.first = first;
    this.rest = rest;
  }
  
  /*
   * Fields:
   * ...this.first...  -- Person
   * ...this.rest...   -- ILoBuddy
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
   * ...this.first.hasDirectBuddy(Person)...       -- boolean
   * ...this.first.partyCount()...                 -- int
   * ...this.first.partyList(ILoBuddy)...          -- ILoBuddy
   * ...this.first.countCommonBuddies(Person)...   -- int
   * ...this.first.countCommonHelp(ILoBuddy)...    -- int
   * ...this.first.hasExtendedBuddy(Person)...     -- boolean
   * ...this.first.addBudy(Person)...              -- void
   * ...this.first.maxLikelihood(Person)...        -- double
   * ...this.first.recieve(double)...              -- double
   * ...this.rest.append(Person)...                           -- ILoBuddy
   * ...this.rest.append(ILoBuddy)...                         -- ILoBuddy
   * ...this.rest.condense()...                               -- ILoBuddy
   * ...this.rest.contains(Person)...                         -- boolean
   * ...this.rest.overlap(ILoBuddy)...                        -- int
   * ...this.rest.extendedSearch(Person, ILoBuddy)...         -- boolean
   * ...this.rest.len()...                                    -- int
   * ...this.rest.partyList(ILoBuddy)...                      -- ILoBuddy
   * ...this.rest.maxLikelihood(Person, double, ILoBuddy)...  -- double
   * 
   */

  // Add the given person to this list of buddies
  public ILoBuddy append(Person that) {
    return new ConsLoBuddy(this.first, this.rest.append(that));
  }

  // Append the given non-empty list onto the end of this list
  public ILoBuddy append(ILoBuddy that) {
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

  // Returns the list of people this non-empty list can invite to a party
  public ILoBuddy partyList(ILoBuddy list) {
    if (list.contains(this.first)) {
      return this.rest.partyList(list);
    }
    else {
      return this.rest.partyList(list.append(this.first.partyList(list)));
    }
  }

  // Removes duplicates from this non-empty list
  public ILoBuddy condense() {
    if (this.rest.contains(this.first)) {
      return this.rest.condense();
    }
    else {
      return new ConsLoBuddy(this.first, this.rest.condense());
    }
  }

  // Returns the maximum likelihood of getting the message to the target thru
  // this non-empty list
  public double maxLikelihood(Person target, double dict, ILoBuddy soFar) {
    if (soFar.contains(this.first)) {
      return this.rest.maxLikelihood(target, dict, soFar);
    }

    return Math.max(this.first.recieve(dict) * this.first.maxLikelihood(target, soFar),  
        this.rest.maxLikelihood(target, dict, soFar.append(this.first)));
  }
}
