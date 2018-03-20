// represents a list of Person's buddies
interface ILoBuddy {

  /* Template: Same as class +
   * 
   * Parameters:
   * ...that...   -- Person
   * 
   * Methods on Parameters:
   * 
   * ...that.hasDirectBuddy(Person)...       -- boolean
   * ...that.partyCount()...                 -- int
   * ...that.partyList(ILoBuddy)...          -- ILoBuddy
   * ...that.countCommonBuddies(Person)...   -- int
   * ...that.countCommonHelp(ILoBuddy)...    -- int
   * ...that.hasExtendedBuddy(Person)...     -- boolean
   * ...that.addBudy(Person)...              -- void
   * ...that.maxLikelihood(Person)...        -- double
   * ...that.recieve(double)...              -- double
   * 
   * 
   */
  // Add the given person to this list of buddies
  ILoBuddy append(Person that);

  /* Template: Same as class +
   * 
   * Parameters:
   * ...that...   -- ILoBuddy
   * 
   * Methods on Parameters: 
   * ...that.append(Person)...                           -- ILoBuddy
   * ...that.append(ILoBuddy)...                         -- ILoBuddy
   * ...that.condense()...                               -- ILoBuddy
   * ...that.contains(Person)...                         -- boolean
   * ...that.overlap(ILoBuddy)...                        -- int
   * ...that.extendedSearch(Person, ILoBuddy)...         -- boolean
   * ...that.len()...                                    -- int
   * ...that.partyList(ILoBuddy)...                      -- ILoBuddy
   * ...that.maxLikelihood(Person, double, ILoBuddy)...  -- double
   * 
   */
  // Append the given list onto the end of this list
  ILoBuddy append(ILoBuddy that);

  /* Template: Same as class +
   * 
   * Parameters:
   * 
   * Methods on Parameters:
   * 
   * 
   * 
   */
  // Removes duplicates from this list
  ILoBuddy condense();

  /* Template: Same as class +
   * 
   * Parameters:
   * ...that...    -- Person
   * 
   * Methods on Parameters:
   * 
   *    * ...that.hasDirectBuddy(Person)...       -- boolean
   * ...that.partyCount()...                 -- int
   * ...that.partyList(ILoBuddy)...          -- ILoBuddy
   * ...that.countCommonBuddies(Person)...   -- int
   * ...that.countCommonHelp(ILoBuddy)...    -- int
   * ...that.hasExtendedBuddy(Person)...     -- boolean
   * ...that.addBudy(Person)...              -- void
   * ...that.maxLikelihood(Person)...        -- double
   * ...that.recieve(double)...              -- double
   * 
   * 
   */
  // Does this list of people contain the given person?
  boolean contains(Person that);

  /* Template: Same as class +
   * 
   * Parameters:
   * ...that...   -- ILoBuddy
   * 
   * Methods on Parameters:
   *    * ...that.append(Person)...                           -- ILoBuddy
   * ...that.append(ILoBuddy)...                         -- ILoBuddy
   * ...that.condense()...                               -- ILoBuddy
   * ...that.contains(Person)...                         -- boolean
   * ...that.overlap(ILoBuddy)...                        -- int
   * ...that.extendedSearch(Person, ILoBuddy)...         -- boolean
   * ...that.len()...                                    -- int
   * ...that.partyList(ILoBuddy)...                      -- ILoBuddy
   * ...that.maxLikelihood(Person, double, ILoBuddy)...  -- double
   * 
   * 
   */
  // How many people overlap between these two lists?
  int overlap(ILoBuddy that);

  /* Template: Same as class +
   * 
   * Parameters:
   * ...that...  -- Person
   * ...soFar... -- ILoBuddy
   * 
   * Methods on Parameters:
   *    * ...that.hasDirectBuddy(Person)...       -- boolean
   * ...that.partyCount()...                 -- int
   * ...that.partyList(ILoBuddy)...          -- ILoBuddy
   * ...that.countCommonBuddies(Person)...   -- int
   * ...that.countCommonHelp(ILoBuddy)...    -- int
   * ...that.hasExtendedBuddy(Person)...     -- boolean
   * ...that.addBudy(Person)...              -- void
   * ...that.maxLikelihood(Person)...        -- double
   * ...that.recieve(double)...              -- double
   *    * ...soFar.append(Person)...                           -- ILoBuddy
   * ...soFar.append(ILoBuddy)...                         -- ILoBuddy
   * ...soFar.condense()...                               -- ILoBuddy
   * ...soFar.contains(Person)...                         -- boolean
   * ...soFar.overlap(ILoBuddy)...                        -- int
   * ...soFar.extendedSearch(Person, ILoBuddy)...         -- boolean
   * ...soFar.len()...                                    -- int
   * ...soFar.partyList(ILoBuddy)...                      -- ILoBuddy
   * ...soFar.maxLikelihood(Person, double, ILoBuddy)...  -- double
   * 
   * 
   */
  // Is the given person within any of the direct friend lists of any of the
  // people in this list?
  boolean extendedSearch(Person that, ILoBuddy soFar);

  /* Template: Same as class +
   * 
   * Parameters:
   * 
   * Methods on Parameters:
   * 
   * 
   * 
   */
  // Find the length of this list
  int len();

  /* Template: Same as class +
   * 
   * Parameters:
   * ...that...  -- ILoBuddy
   * 
   * Methods on Parameters:
   * 
   * ...that.append(Person)...                           -- ILoBuddy
   * ...that.append(ILoBuddy)...                         -- ILoBuddy
   * ...that.condense()...                               -- ILoBuddy
   * ...that.contains(Person)...                         -- boolean
   * ...that.overlap(ILoBuddy)...                        -- int
   * ...that.extendedSearch(Person, ILoBuddy)...         -- boolean
   * ...that.len()...                                    -- int
   * ...that.partyList(ILoBuddy)...                      -- ILoBuddy
   * ...that.maxLikelihood(Person, double, ILoBuddy)...  -- double
   * 
   * 
   */
  // Returns the list of people this person can invite to a party
  ILoBuddy partyList(ILoBuddy that);

  /* Template: Same as class +
   * 
   * Parameters:
   * ...that...   -- Person
   * ...dict...     -- Double
   * ...soFar...   -- ILoBuddy
   * Methods on Parameters:
   * 
   *    *    * ...that.hasDirectBuddy(Person)...       -- boolean
   * ...that.partyCount()...                 -- int
   * ...that.partyList(ILoBuddy)...          -- ILoBuddy
   * ...that.countCommonBuddies(Person)...   -- int
   * ...that.countCommonHelp(ILoBuddy)...    -- int
   * ...that.hasExtendedBuddy(Person)...     -- boolean
   * ...that.addBudy(Person)...              -- void
   * ...that.maxLikelihood(Person)...        -- double
   * ...that.recieve(double)...              -- double
   *    * ...soFar.append(Person)...                           -- ILoBuddy
   * ...soFar.append(ILoBuddy)...                         -- ILoBuddy
   * ...soFar.condense()...                               -- ILoBuddy
   * ...soFar.contains(Person)...                         -- boolean
   * ...soFar.overlap(ILoBuddy)...                        -- int
   * ...soFar.extendedSearch(Person, ILoBuddy)...         -- boolean
   * ...soFar.len()...                                    -- int
   * ...soFar.partyList(ILoBuddy)...                      -- ILoBuddy
   * ...soFar.maxLikelihood(Person, double, ILoBuddy)...  -- double
   * 
   * 
   * 
   */
  // Returns the maximum likelihood of getting the message to the target through
  // this list
  double maxLikelihood(Person that, double dict, ILoBuddy soFar);
}
