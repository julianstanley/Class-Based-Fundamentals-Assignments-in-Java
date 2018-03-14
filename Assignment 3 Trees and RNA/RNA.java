import tester.Tester;

// To represent a list of proteins
interface ILoLoString {

  // Append a list of strings to this list of proteins
  ILoLoString append(ILoString that); 
}

// To represent a non-empty list of proteins
class ConsLoLoString implements ILoLoString {
  ILoString first;
  ILoLoString rest;

  ConsLoLoString(ILoString first, ILoLoString rest) {
    this.first = first;
    this.rest = rest;
  }
  
  /*
   * Fields:
   * ...this.first...                                     -- ILoString
   * ...this.rest...                                      -- ILoLoString
   * 
   * Methods:
   * ...this.append(ILoString)...                         -- ILoLoString
   * 
   * Methods on Fields:
   * ...this.rest.append(ILoString)...                    -- ILoLoString
   */
  
  /*
   * Parameters:
   * ...that...                                           -- ILoString
   * 
   * Methods on Parameters:
   * ...that.translate()...                               -- ILoLoString
   * ...that.ribosome(ILoString, ILoLoString, boolean)... -- ILoLoString
   * ...that.getFirst(int)...                             -- String
   * ...that.removeFirst(int)...                          -- ILoString
   * ...that.append(String)...                            -- ILoString
   * ...that.length()...                                  -- int
   */
  // Append a list of strings to this non-empty list of proteins
  public ILoLoString append(ILoString that) {
    if (that.length() < 1) {
      return this;
    }
    else {
      return new ConsLoLoString(this.first, this.rest.append(that)); 
    }
  }
}

// To represent an empty list of proteins
class MtLoLoString implements ILoLoString {
  /*
   * Methods:
   * ...this.append(ILoString)...                         -- ILoLoString
   */
  
  /*
   * Parameters:
   * ...that...                                           -- ILoString
   * 
   * Methods on Parameters:
   * ...that.translate()...                               -- ILoLoString
   * ...that.ribosome(ILoString, ILoLoString, boolean)... -- ILoLoString
   * ...that.getFirst(int)...                             -- String
   * ...that.removeFirst(int)...                          -- ILoString
   * ...that.append(String)...                            -- ILoString
   * ...that.length()                                     -- int
   */
  // Append a list of strings to this empty list of proteins
  public ILoLoString append(ILoString that) {
    return new ConsLoLoString(that, new MtLoLoString());
  }
}

// To represent a list of nucleotides/codons
interface ILoString {
  
  // Initiates translating this list of nucleotides into a list of proteins
  ILoLoString translate(); 
  
  // Translates this list of nucleotides into a list of proteins
  ILoLoString ribosome(ILoString loc, ILoLoString lop, boolean go); 
  
  // Take the first n characters from this list of nucleotides
  String getFirst(int n); 
  
  // Remove the first n characters from this list of nucleotides/codons
  ILoString removeFirst(int n);
  
  // Append given string onto this list of nucleotides/codons
  ILoString append(String that); 
  
  // Finds the length of this list of nucleotides/codons
  int length(); 
  
}





// To represent a non-empty list of nucleotides/codons
class ConsLoString implements ILoString {
  String first;
  ILoString rest;

  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;
  }
  
  /*
   * Fields:
   * ...this.first...                                        -- String
   * ...this.rest...                                         -- ILoString
   * 
   * Methods:
   * ...this.translate()...                                   -- ILoLoString
   * ...this.ribosome(ILoString, ILoLoString, boolean)...     -- ILoLoString
   * ...this.getFirst(int)...                                 -- String
   * ...this.removeFirst(int)...                              -- ILoString
   * ...this.append(String)...                                -- ILoString
   * ...this.length()...                                      -- int
   * 
   * Methods on Fields:
   * ...this.rest.translate()...                               -- ILoLoString
   * ...this.rest.ribosome(ILoString, ILoLoString, boolean)... -- ILoLoString
   * ...this.rest.getFirst(int)...                             -- String
   * ...this.rest.removeFirst(int)...                          -- ILoString
   * ...this.rest.append(String)...                            -- ILoString
   * ...this.rest.length(int)...                               -- int
   */
  
  // Initiates translating this non-empty list of nucleotides into a list of proteins
  public ILoLoString translate() {
    return this.ribosome(new MtLoString(), new MtLoLoString(), false); 
  }
  
  /*
   * Parameters:
   * ...loc...  -- ILoString
   * ...lop...  -- ILoLoString
   * ...go...   -- boolean
   * 
   * Methods on Parameters:
   * ...lop.append(ILoString)...                         -- ILoLoString
   * ...loc.translate()...                               -- ILoLoString
   * ...loc.ribosome(ILoString, ILoLoString, boolean)... -- ILoLoString
   * ...loc.getFirst(int)...                             -- String
   * ...loc.removeFirst(int)...                          -- ILoString
   * ...loc.append(String)...                            -- ILoString
   * ...loc.length()...                                  -- int
   */
  
  // Translates this non-empty list of nucleotides into a list of proteins
  public ILoLoString ribosome(ILoString loc, ILoLoString lop, boolean go) {
    String firstThree = this.getFirst(3); 
    if (go) { 
      if ((firstThree.equals("UAG") || firstThree.equals("UAA") || firstThree.equals("UGA"))) {
        return this.removeFirst(3).ribosome(new MtLoString(), lop.append(loc), false); 
      }
      
      else {
        if (firstThree.length() > 2) {
          return this.removeFirst(3).ribosome(loc.append(firstThree), lop, go); 
        }
        
        else {
          return lop.append(loc); 
        }
      }
    }
    
    else {
      if (firstThree.equals("AUG")) {
        return this.removeFirst(3).ribosome(loc.append("AUG"),  lop,  true); 
      }
      else {
        return this.rest.ribosome(loc, lop, go); 
      }
    }
  }

  // Take the first n characters from this non-empty list of nucleotides
  public String getFirst(int n) {
    if (n > 0) {
      return this.first.concat(this.rest.getFirst(n - 1)); 
    }
    
    else {
      return ""; 
    }
  } 
  
  // Remove the first n characters from this non-empty list of nucleotides/codons
  public ILoString removeFirst(int n) {
    if (n > 0) {
      return this.rest.removeFirst(n - 1);
    }

    else {
      return this;
    }
  }
  
  /*
   * Parameters:
   * ...that... -- String
   *
   */
  // Append given string onto this non-empty list of nucleotides/codons
  public ILoString append(String that) {
    return new ConsLoString(this.first, this.rest.append(that)); 
  }
  
 
  // Finds the length of this non-empty list of nucleotides/codons
  public int length() {
    return 1 + this.rest.length(); 
  }
} 

class MtLoString implements ILoString {
  /*
   * Fields:
   * 
   * Methods:
   * ...this.translate()...                               -- ILoLoString
   * ...this.ribosome(ILoString, ILoLoString, boolean)... -- ILoLoString
   * ...this.getFirst(int)...                             -- String
   * ...this.removeFirst(int)...                          -- ILoString
   * ...this.append(String)...                            -- ILoString
   * ...this.length()...                                  -- int
   * 
   * Methods on Fields:
   */
  
  // Initiates translating this empty list of nucleotides into a list of proteins
  public ILoLoString translate() {
    return new MtLoLoString(); 
  }
  
  /*
   * Parameters:
   * ...loc...  -- ILoString
   * ...lop...  -- ILoLoString
   * ...go...   -- boolean
   * 
   * Methods on Parameters:
   * ...lop.append(ILoString)...                         -- ILoLoString
   * ...loc.translate()...                               -- ILoLoString
   * ...loc.ribosome(ILoString, ILoLoString, boolean)... -- ILoLoString
   * ...loc.getFirst(int)...                             -- String
   * ...loc.removeFirst(int)...                          -- ILoString
   * ...loc.append(String)...                            -- ILoString
   * ...loc.length()...                                  -- int
   */
  /// Translates this empty list of nucleotides into a list of proteins
  public ILoLoString ribosome(ILoString loc, ILoLoString lop, boolean go) {
    return lop.append(loc);  
  }

  // Take the first n characters from this empty list of nucleotides
  public String getFirst(int n) {
    return "";
  }

  // Remove the first n characters from this empty list of nucleotides/codons
  public ILoString removeFirst(int n) {
    return this;
  }
  
  /*
   * Parameters:
   * ...that... -- String
   *
   */
  // Append given string onto this non-empty list of nucleotides/codons
  public ILoString append(String that) {
    return new ConsLoString(that, new MtLoString()); 
  }
  
  /* 
   * Parameters:
   * ...acc... -- int
   */
  // Finds the length of this non-empty list of nucleotides/codons
  public int length() {
    return 0;  
  }
} 


class ExamplesCell {
  
  // Empty translation example
  ILoString emptyRNA = new MtLoString();
  ILoString emptyCodon = new MtLoString();
  ILoLoString emptyProtein = new ConsLoLoString(new MtLoString(), new MtLoLoString());

  // Just stop codon example
  ILoString stopRNA = new ConsLoString("U", 
      new ConsLoString("A", new ConsLoString("G", new MtLoString()))); 
  ILoString stopCodon = new ConsLoString("UAG", new MtLoString()); 
  ILoLoString stopProtein = new ConsLoLoString(new MtLoString(), new MtLoLoString());
  
  // Two stop codons example
  ILoString stop2RNA = new ConsLoString("U",
      new ConsLoString("A",
          new ConsLoString("G",
              new ConsLoString("U",
                  new ConsLoString("A",
                      new ConsLoString("A", new MtLoString())))))); 
  ILoString stop2Codon = new ConsLoString("UAG", new ConsLoString("UAA", new MtLoString())); 
  ILoLoString stop2Protein = new ConsLoLoString(new MtLoString(), new MtLoLoString()); 
      
  // Just start codon example
  ILoString startCodon = new ConsLoString("AUG", new MtLoString()); 
  ILoLoString startProtein = new ConsLoLoString(startCodon, new MtLoLoString()); 
  
  // Small translation example
  ILoString augRNA = new ConsLoString("A", new ConsLoString("U", 
      new ConsLoString("G", new MtLoString()))); 
  ILoString augCodon = new ConsLoString("AUG", new MtLoString()); 
  ILoLoString augProtein = new ConsLoLoString(augCodon, new MtLoLoString()); 

  
  // Partial codon translation example AUGUUUAU
  ILoString partialRNA = new ConsLoString("A",
      new ConsLoString("U", 
          new ConsLoString("G",
              new ConsLoString("U",
                  new ConsLoString("U",
                      new ConsLoString("U",
                          new ConsLoString("A",
                              new ConsLoString("U", new MtLoString())))))))); 
  
  ILoLoString partialProtein = new ConsLoLoString(
      new ConsLoString("AUG", new ConsLoString("UUU", new MtLoString())),
      new MtLoLoString()); 
  
  // Big translation example
  ILoString rnaTest2 = new ConsLoString("G",
      new ConsLoString("A", new ConsLoString("U",
          new ConsLoString("G", new ConsLoString("U", new ConsLoString("U", new ConsLoString("U",
              new ConsLoString("U", new ConsLoString("G", new ConsLoString("A", new ConsLoString(
                  "A", new ConsLoString("U", new ConsLoString("G", new ConsLoString("A",
                      new ConsLoString("A", new ConsLoString("G", new MtLoString()))))))))))))))));
 
  ILoLoString proteinTest2 = new ConsLoLoString(
      new ConsLoString("AUG", new ConsLoString("UUU", new MtLoString())),
      new ConsLoLoString(
          new ConsLoString("AUG", new ConsLoString("AAG", new MtLoString())), new MtLoLoString()));

  
  boolean testBiology(Tester t) {
    
    return t.checkExpect(new MtLoLoString().append(new MtLoString()), 
        new ConsLoLoString(new MtLoString(), new MtLoLoString()))
        && t.checkExpect(new MtLoLoString().append(new ConsLoString("AUG", new MtLoString())), 
            new ConsLoLoString(new ConsLoString("AUG", new MtLoString()), new MtLoLoString()))
        && t.checkExpect(new ConsLoLoString(new ConsLoString("AUG", 
            new MtLoString()), new MtLoLoString()).append(new MtLoString()), 
            new ConsLoLoString(new ConsLoString("AUG", 
                new MtLoString()), new MtLoLoString()))
        
        
        // Test the append string method
        && t.checkExpect(new MtLoString().append(""),  new ConsLoString("", new MtLoString()))
        && t.checkExpect(new MtLoString().append("AUG"), new ConsLoString("AUG", new MtLoString()))
        && t.checkExpect(new ConsLoString("UUU", new MtLoString()).append("AUG"),
            new ConsLoString("UUU", new ConsLoString("AUG", new MtLoString())))
        
        // Test the getFirst method
        && t.checkExpect(new MtLoString().getFirst(3), "")
        && t.checkExpect(new MtLoString().getFirst(0), "")
        && t.checkExpect(new ConsLoString("A",
            new ConsLoString("U", new MtLoString())).getFirst(3), "AU")
        && t.checkExpect(augRNA.getFirst(3), "AUG")
        && t.checkExpect(rnaTest2.getFirst(3), "GAU")
        
        // Test the removeFirst method
        && t.checkExpect(emptyCodon.removeFirst(3), emptyCodon)
        && t.checkExpect(emptyCodon.removeFirst(0), emptyCodon)
        && t.checkExpect(augRNA.removeFirst(2), new ConsLoString("G", new MtLoString()))
        && t.checkExpect(augRNA.removeFirst(3), emptyCodon)
        
      
        // Test the ribosome method
        && t.checkExpect(new MtLoString().ribosome(new MtLoString(), new MtLoLoString(), false), 
            new ConsLoLoString(new MtLoString(), new MtLoLoString()))
        && t.checkExpect(stopRNA.ribosome(new MtLoString(),  new MtLoLoString(), false), 
            new ConsLoLoString(new MtLoString(), new MtLoLoString()))
        && t.checkExpect(augRNA.ribosome(new MtLoString(),  new MtLoLoString(), false),
            new ConsLoLoString(new ConsLoString("AUG", new MtLoString()), new MtLoLoString()))
        && t.checkExpect(partialRNA.ribosome(new MtLoString(),  new MtLoLoString(), false),
            partialProtein)
        && t.checkExpect(rnaTest2.ribosome(new MtLoString(),  new MtLoLoString(), false),
            proteinTest2)       
        
        // Test the translate method  
        && t.checkExpect(new MtLoString().translate(), 
            new MtLoLoString())
        && t.checkExpect(stopRNA.translate(), 
            new ConsLoLoString(new MtLoString(), new MtLoLoString()))
        && t.checkExpect(augRNA.translate(), augProtein)
        && t.checkExpect(partialRNA.translate(), partialProtein)
        && t.checkExpect(rnaTest2.translate(), proteinTest2);           
  }
}

