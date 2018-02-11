
// This file contains the backbone for the list of colors used in the other files

import java.awt.Color;

// To represent a list of colors
interface ILoColor {

  /*
   * Parameters: ...other... -- ILoColor
   * 
   * Methods on Parameters: ...other.sameSequence(ILoColor)... -- boolean
   * ...other.startsWith(ILoColor)... -- boolean
   * ...other.startsWithSpecific(ConsLoColor)... -- boolean
   * ...other.startswithSpecific(MtLoColor)... -- boolean
   */
  // Is this list of colors in the same sequence as that list of colors?
  boolean sameSequence(ILoColor other);

  /*
   * Parameters: ...other... -- ILoColor
   * 
   * Methods on Parameters: ...other.sameSequence(ILoColor)... -- boolean
   * ...other.startsWith(ILoColor)... -- boolean
   * ...other.startsWithSpecific(ConsLoColor)... -- boolean
   * ...other.startswithSpecific(MtLoColor)... -- boolean
   */
  // Does this list of colors start with that list of colors?
  boolean startsWith(ILoColor other);

  /*
   * Parameters: ...other... -- ILoColor
   * 
   * Methods on Parameters: ...other.sameSequence(ILoColor)... -- boolean
   * ...other.startsWith(ILoColor)... -- boolean
   * ...other.startsWithSpecific(ConsLoColor)... -- boolean
   * ...other.startswithSpecific(MtLoColor)... -- boolean
   */
  // Does the other list of colors start with this non-empty list of colors?
  boolean startsWithSpecific(ConsLoColor other);

  /*
   * Parameters: ...other... -- ILoColor
   * 
   * Methods on Parameters: ...other.sameSequence(ILoColor)... -- boolean
   * ...other.startsWith(ILoColor)... -- boolean
   * ...other.startsWithSpecific(ConsLoColor)... -- boolean
   * ...other.startswithSpecific(MtLoColor)... -- boolean
   */
  // Does the other list of colors start with this empty list of colors?
  boolean startsWithSpecific(MtLoColor other);

  // Remove one item from this list of colors
  ILoColor removeOne();

  // How many items are in this list of colors?
  int countColors();

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
   * ...this.startswithSpecific(MtLoColor)... -- boolean
   * 
   * Methods on Fields: ...this.rest.sameSequence(ILoColor)... -- boolean
   * ...this.rest.startsWith(ILoColor)... -- boolean
   * ...this.rest.startsWithSpecific(ConsLoColor)... -- boolean
   * ...this.rest.startswithSpecific(MtLoColor)... -- boolean
   */
  /*
   * Parameters: ...other... -- ILoColor
   * 
   * Methods on Parameters: ...other.sameSequence(ILoColor)... -- boolean
   * ...other.startsWith(ILoColor)... -- boolean
   * ...other.startsWithSpecific(ConsLoColor)... -- boolean
   * ...other.startswithSpecific(MtLoColor)... -- boolean
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
   * Parameters: ...other... -- ILoColor
   * 
   * Methods on Parameters: ...other.sameSequence(ILoColor)... -- boolean
   * ...other.startsWith(ILoColor)... -- boolean
   * ...other.startsWithSpecific(ConsLoColor)... -- boolean
   * ...other.startswithSpecific(MtLoColor)... -- boolean
   */
  // Does this list of colors start with that list of colors?
  public boolean startsWith(ILoColor other) {
    return other.startsWithSpecific(this);
  }

  /*
   * Parameters: ...other... -- ILoColor
   * 
   * Methods on Parameters: ...other.sameSequence(ILoColor)... -- boolean
   * ...other.startsWith(ILoColor)... -- boolean
   * ...other.startsWithSpecific(ConsLoColor)... -- boolean
   * ...other.startswithSpecific(MtLoColor)... -- boolean
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
   * Parameters: ...other... -- ILoColor
   * 
   * Methods on Parameters: ...other.sameSequence(ILoColor)... -- boolean
   * ...other.startsWith(ILoColor)... -- boolean
   * ...other.startsWithSpecific(ConsLoColor)... -- boolean
   * ...other.startswithSpecific(MtLoColor)... -- boolean
   */
  // Does the other empty list of colors start with this non-empty list of
  // colors?
  public boolean startsWithSpecific(MtLoColor other) {
    return false;
  }

  // Remove one color from this non-empty list of colors
  public ILoColor removeOne() {
    return this.rest;
  }

  // Count the number of colors in this non-empty list of colors
  public int countColors() {
    return 1 + this.rest.countColors();
  }

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
   * Parameters: ...other... -- ILoColor
   * 
   * Methods on Parameters: ...other.sameSequence(ILoColor)... -- boolean
   * ...other.startsWith(ILoColor)... -- boolean
   * ...other.startsWithSpecific(ConsLoColor)... -- boolean
   * ...other.startswithSpecific(MtLoColor)... -- boolean
   */
  // Is this list of colors in the same sequence as that list of colors?
  public boolean sameSequence(ILoColor other) {
    return other instanceof MtLoColor;
  }

  /*
   * Parameters: ...other... -- ILoColor
   * 
   * Methods on Parameters: ...other.sameSequence(ILoColor)... -- boolean
   * ...other.startsWith(ILoColor)... -- boolean
   * ...other.startsWithSpecific(ConsLoColor)... -- boolean
   * ...other.startswithSpecific(MtLoColor)... -- boolean
   */
  // Does this list of colors start with that list of colors?
  public boolean startsWith(ILoColor other) {
    return other.startsWithSpecific(this);
  }

  /*
   * Parameters: ...other... -- ILoColor
   * 
   * Methods on Parameters: ...other.sameSequence(ILoColor)... -- boolean
   * ...other.startsWith(ILoColor)... -- boolean
   * ...other.startsWithSpecific(ConsLoColor)... -- boolean
   * ...other.startswithSpecific(MtLoColor)... -- boolean
   */
  // Does the other non-empty list of colors start with this empty list of
  // colors?
  public boolean startsWithSpecific(ConsLoColor other) {
    return true;
  }

  /*
   * Parameters: ...other... -- ILoColor
   * 
   * Methods on Parameters: ...other.sameSequence(ILoColor)... -- boolean
   * ...other.startsWith(ILoColor)... -- boolean
   * ...other.startsWithSpecific(ConsLoColor)... -- boolean
   * ...other.startswithSpecific(MtLoColor)... -- boolean
   */
  // Does the other empty list of colors start with this empty list of colors?
  public boolean startsWithSpecific(MtLoColor other) {
    return true;
  }

  // Remove one color from this empty list of colors
  public ILoColor removeOne() {
    return this;
  }

  // Count the number of colors in this empty list of colors
  public int countColors() {
    return 0;
  }

  // Append that color to the end of this empty list of colors
  public ILoColor append(Color that) {
    return new ConsLoColor(that, this);
  }
}
