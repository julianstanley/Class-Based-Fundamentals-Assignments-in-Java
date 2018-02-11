
// This file contains the backbone for the list of colors used in the other files

import java.awt.Color;

// To represent a list of colors
interface ILoColor {

  /*
   * Template: Same as class + Parameters: ...other... -- ILoColor
   * 
   * Methods on Parameters: ...other.sameSequence(ILoColor)... -- boolean
   * ...other.startsWith(ILoColor)... -- boolean
   * ...other.startsWithSpecific(ConsLoColor)... -- boolean
   * ...other.startswithSpecific(MtLoColor)... -- boolean
   * ...other.removeOne()... -- ILoColor ...other.countColors()... -- int
   * ...other.append(Color)... -- ILoColor
   */
  // Is this list of colors in the same sequence as that list of colors?
  boolean sameSequence(ILoColor other);

  /*
   * Template: Same as class + Parameters: ...other... -- ILoColor
   * 
   * Methods on Parameters: ...other.sameSequence(ILoColor)... -- boolean
   * ...other.startsWith(ILoColor)... -- boolean
   * ...other.startsWithSpecific(ConsLoColor)... -- boolean
   * ...other.startswithSpecific(MtLoColor)... -- boolean
   * ...other.removeOne()... -- ILoColor ...other.countColors()... -- int
   * ...other.append(Color)... -- ILoColor
   */
  // Does this list of colors start with that list of colors?
  boolean startsWith(ILoColor other);

  /*
   * Template: Same as class + Parameters: ...other... -- ILoColor
   * 
   * Methods on Parameters: ...other.sameSequence(ILoColor)... -- boolean
   * ...other.startsWith(ILoColor)... -- boolean
   * ...other.startsWithSpecific(ConsLoColor)... -- boolean
   * ...other.startswithSpecific(MtLoColor)... -- boolean
   * ...other.removeOne()... -- ILoColor ...other.countColors()... -- int
   * ...other.append(Color)... -- ILoColor
   */
  // Does the other list of colors start with this non-empty list of colors?
  boolean startsWithSpecific(ConsLoColor other);

  /*
   * Template: Same as class + Parameters: ...other... -- ILoColor
   * 
   * Methods on Parameters: ...other.sameSequence(ILoColor)... -- boolean
   * ...other.startsWith(ILoColor)... -- boolean
   * ...other.startsWithSpecific(ConsLoColor)... -- boolean
   * ...other.startswithSpecific(MtLoColor)... -- boolean
   * ...other.removeOne()... -- ILoColor ...other.countColors()... -- int
   * ...other.append(Color)... -- ILoColor
   */
  // Does the other list of colors start with this empty list of colors?
  boolean startsWithSpecific(MtLoColor other);

  // Template: Same as class
  // Remove one item from this list of colors
  ILoColor removeOne();

  // Template: Same as class
  // How many items are in this list of colors?
  int countColors();

  // Template: Same as class +
  /*
   * Parameters: ...that... -- Color
   * 
   * Methods on Parameters:
   */
  // Append that color to the end of this list of colors
  ILoColor append(Color that);

}

// To represent a non-empty list of colors
class ConsLoColor implements ILoColor {
  Color first;
  ILoColor rest;

  ConsLoColor(Color first, ILoColor rest) {
    this.first = first;
    this.rest = rest;
  }

  /*
   * Fields: ...this.first... -- Color ...this.rest... -- ILoColor
   * 
   * Methods: ...this.sameSequence(ILoColor)... -- boolean
   * ...this.startsWith(ILoColor)... -- boolean
   * ...this.startsWithSpecific(ConsLoColor)... -- boolean
   * ...this.startswithSpecific(MtLoColor)... -- boolean ...this.removeOne()...
   * -- ILoColor ...this.countColors()... -- int ...this.append(Color)... --
   * ILoColor
   * 
   * Methods on Fields: ...this.rest.sameSequence(ILoColor)... -- boolean
   * ...this.rest.startsWith(ILoColor)... -- boolean
   * ...this.rest.startsWithSpecific(ConsLoColor)... -- boolean
   * ...this.rest.startswithSpecific(MtLoColor)... -- boolean
   * ...this.rest.removeOne()... -- ILoColor ...this.rest.countColors()... --
   * int ...this.rest.append(Color)... -- ILoColor
   */

  /*
   * Template: Same as class + Parameters: ...other... -- ILoColor
   * 
   * Methods on Parameters: ...other.sameSequence(ILoColor)... -- boolean
   * ...other.startsWith(ILoColor)... -- boolean
   * ...other.startsWithSpecific(ConsLoColor)... -- boolean
   * ...other.startswithSpecific(MtLoColor)... -- boolean
   * ...other.removeOne()... -- ILoColor ...other.countColors()... -- int
   * ...other.append(Color)... -- ILoColor
   */
  // Is this list of colors in the same sequence as that list of colors?
  public boolean sameSequence(ILoColor other) {
    if (other instanceof ConsLoColor) {
      return this.first.equals(((ConsLoColor) other).first)
          && this.rest.sameSequence(((ConsLoColor) other).rest);
    }

    else {
      return false;
    }
  }

  /*
   * Template: Same as class + Parameters: ...other... -- ILoColor
   * 
   * Methods on Parameters: ...other.sameSequence(ILoColor)... -- boolean
   * ...other.startsWith(ILoColor)... -- boolean
   * ...other.startsWithSpecific(ConsLoColor)... -- boolean
   * ...other.startswithSpecific(MtLoColor)... -- boolean
   * ...other.removeOne()... -- ILoColor ...other.countColors()... -- int
   * ...other.append(Color)... -- ILoColor
   */
  // Does this list of colors start with that list of colors?
  public boolean startsWith(ILoColor other) {
    return other.startsWithSpecific(this);
  }

  /*
   * Template: Same as class + Parameters: ...other... -- ILoColor
   * 
   * Methods on Parameters: ...other.sameSequence(ILoColor)... -- boolean
   * ...other.startsWith(ILoColor)... -- boolean
   * ...other.startsWithSpecific(ConsLoColor)... -- boolean
   * ...other.startswithSpecific(MtLoColor)... -- boolean
   * ...other.removeOne()... -- ILoColor ...other.countColors()... -- int
   * ...other.append(Color)... -- ILoColor
   */
  // Does the other non-empty list of colors start with this non-empty list of
  // colors?
  public boolean startsWithSpecific(ConsLoColor other) {
    if (this.first.equals(other.first)) {
      return other.rest.startsWith(this.rest);
    }

    else {
      return false;
    }
  }

  /*
   * Template: Same as class + Parameters: ...other... -- ILoColor
   * 
   * Methods on Parameters: ...other.sameSequence(ILoColor)... -- boolean
   * ...other.startsWith(ILoColor)... -- boolean
   * ...other.startsWithSpecific(ConsLoColor)... -- boolean
   * ...other.startswithSpecific(MtLoColor)... -- boolean
   * ...other.removeOne()... -- ILoColor ...other.countColors()... -- int
   * ...other.append(Color)... -- ILoColor
   */

  // Does the other empty list of colors start with this non-empty list of
  // colors?
  public boolean startsWithSpecific(MtLoColor other) {
    return false;
  }

  // Template: Same as class
  // Remove one color from this non-empty list of colors
  public ILoColor removeOne() {
    return this.rest;
  }

  // Template: Same as class
  // Count the number of colors in this non-empty list of colors
  public int countColors() {
    return 1 + this.rest.countColors();
  }

  // Template: Same as class +
  /*
   * Parameters: ...c... -- Color
   * 
   * Methods on Parameters:
   */
  // Append that color to the end of this non-empty list of colors
  public ILoColor append(Color c) {
    return new ConsLoColor(this.first, this.rest.append(c));
  }
}

// To represent an empty list of colors
class MtLoColor implements ILoColor {
  /*
   * Fields:
   * 
   * Methods: ...this.sameSequence(ILoColor)... -- boolean
   * ...this.startsWith(ILoColor)... -- boolean
   * ...this.startsWithSpecific(ConsLoColor)... -- boolean
   * ...this.startswithSpecific(MtLoColor)... -- boolean
   * 
   * Methods on fields:
   *
   */

  /*
   * Template: Same as class + Parameters: ...other... -- ILoColor
   * 
   * Methods on Parameters: ...other.sameSequence(ILoColor)... -- boolean
   * ...other.startsWith(ILoColor)... -- boolean
   * ...other.startsWithSpecific(ConsLoColor)... -- boolean
   * ...other.startswithSpecific(MtLoColor)... -- boolean
   * ...other.removeOne()... -- ILoColor ...other.countColors()... -- int
   * ...other.append(Color)... -- ILoColor
   */
  // Is this list of colors in the same sequence as that list of colors?
  public boolean sameSequence(ILoColor other) {
    return other instanceof MtLoColor;
  }

  /*
   * Template: Same as class + Parameters: ...other... -- ILoColor
   * 
   * Methods on Parameters: ...other.sameSequence(ILoColor)... -- boolean
   * ...other.startsWith(ILoColor)... -- boolean
   * ...other.startsWithSpecific(ConsLoColor)... -- boolean
   * ...other.startswithSpecific(MtLoColor)... -- boolean
   * ...other.removeOne()... -- ILoColor ...other.countColors()... -- int
   * ...other.append(Color)... -- ILoColor
   */
  // Does this list of colors start with that list of colors?
  public boolean startsWith(ILoColor other) {
    return other.startsWithSpecific(this);
  }

  /*
   * Template: Same as class + Parameters: ...other... -- ILoColor
   * 
   * Methods on Parameters: ...other.sameSequence(ILoColor)... -- boolean
   * ...other.startsWith(ILoColor)... -- boolean
   * ...other.startsWithSpecific(ConsLoColor)... -- boolean
   * ...other.startswithSpecific(MtLoColor)... -- boolean
   * ...other.removeOne()... -- ILoColor ...other.countColors()... -- int
   * ...other.append(Color)... -- ILoColor
   */
  // Does the other non-empty list of colors start with this empty list of
  // colors?
  public boolean startsWithSpecific(ConsLoColor other) {
    return true;
  }

  /*
   * Template: Same as class + Parameters: ...other... -- ILoColor
   * 
   * Methods on Parameters: ...other.sameSequence(ILoColor)... -- boolean
   * ...other.startsWith(ILoColor)... -- boolean
   * ...other.startsWithSpecific(ConsLoColor)... -- boolean
   * ...other.startswithSpecific(MtLoColor)... -- boolean
   * ...other.removeOne()... -- ILoColor ...other.countColors()... -- int
   * ...other.append(Color)... -- ILoColor
   */
  // Does the other empty list of colors start with this empty list of colors?
  public boolean startsWithSpecific(MtLoColor other) {
    return true;
  }

  // Template: Same as class
  // Remove one color from this empty list of colors
  public ILoColor removeOne() {
    return this;
  }

  // Template: same as class
  // Count the number of colors in this empty list of colors
  public int countColors() {
    return 0;
  }

  // Template: Same as class +
  /*
   * Parameters: ...that... -- Color
   * 
   * Methods on Parameters:
   */
  // Append that color to the end of this empty list of colors
  public ILoColor append(Color that) {
    return new ConsLoColor(that, this);
  }
}
