
// represents a list of Person's buddies
interface ILoBuddy {
  // Add the given person to this list of buddies
  ILoBuddy append(Person that);

  // Does this list of people contain the given person?
  boolean contains(Person that);

  // How many people overlap between these two lists?
  int overlap(ILoBuddy that);

  // Is the given person within any of the direct friend lists of any of the
  // people in this list?
  boolean extendedSearch(Person that, ILoBuddy soFar);

  // Find the length of this list
  int len();

  // TODO: Bug in this, I think
  // Count of the number of people invited to a party from this list
  int countParty(ILoBuddy soFar);

}
