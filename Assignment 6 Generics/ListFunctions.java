import tester.*;

// Functions with one input and one output

// To represent a function that takes in one value and returns another
interface IFunc<T, U> {
  // Apply this operation to the given object
  U apply(T t);
}

// Takes a Double and squares it
class SquareNum implements IFunc<Double, Double> {
 
  /*
   * METHODS:
   * ...this.apply(Double that)... -- Double
   */
  
  // Return the square of the given double
  public Double apply(Double that) {
    return that * that;
  }
}

// Takes a double and computes it sine
class SineNum implements IFunc<Double, Double> {
  
  /*
   * METHODS:
   * ...this.apply(Double that)... -- Double
   */
  
  // Return the sine of the given double
  public Double apply(Double that) {
    return Math.sin(that);
  }
}

// Returns the given object
class Identity<T> implements IFunc<T, T> {
  
  /*
   * METHODS:
   * ...this.apply(T that)... -- T
   */
  
  // Return the given object
  public T apply(T that) {
    return that;
  }
}

// Adds this object's n value to the given double
class PlusN implements IFunc<Double, Double> {
  double n;

  PlusN(double n) {
    this.n = n;
  }
  
  /*
   * FIELDS:
   * ...this.n... -- double
   * METHODS:
   * ...this.apply(Double that)... -- Double
   */

  // Add n to the value of the given double
  public Double apply(Double that) {
    return that + n;
  }
}

// Represents a combination of two IFunc objects
class FunctionComposition<T, V, U> implements IFunc<T, U> {
  IFunc<T, V> func1;
  IFunc<V, U> func2;

  FunctionComposition(IFunc<T, V> func1, IFunc<V, U> func2) {
    this.func1 = func1;
    this.func2 = func2;
  }
  
  /*
   * FIELDS:
   * ...this.func1... -- IFunc<T, V>
   * ...this.func2... -- IFunc<V, U>
   * METHODS:
   * ...this.apply(T t)... -- U
   * METHODS ON FIELDS:
   * ...this.func1.apply(T t)... -- U
   * ...this.func2.apply(T t)... -- U
   */

  // Applies func1 and func2 to the given object
  public U apply(T t) {
    return func2.apply(func1.apply(t));
  }
}

// Functions with two inputs and one output

interface IFunc2<T, U, V> {
  
  /* 
   * METHODS:
   * ...this.apply(T t, U u)... -- V
   */
  
  // Apply this operation to the given object
  V apply(T t, U u);
}

class ComposeFunctions<T, U, V> implements IFunc2<IFunc<T, U>, IFunc<U, V>, IFunc<T, V>> {
  
  /*
   * METHODS:
   * ...this.apply(IFunc<T, U> func1, IFunc<U, V> func2)... -- IFunc<T, V>
   */
  
  // Returns a new IFunc that combines func1 and func2
  // ComposeFunction, instead of being an object with two functions as fields,
  // Is a function object that takes in two objects as parameters
  public IFunc<T, V> apply(IFunc<T, U> func1, IFunc<U, V> func2) {
    return new FunctionComposition<T, U, V>(func1, func2);
  }
}

// To represent a generic list
interface IList<T> {
  // folds this list of objects onto the base using the function
  <U> U foldl(IFunc2<T, U, U> fun, U base);
}

// To represent a non-MtList generic list
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }
  
  /* 
   * FIELDS:
   * ...this.first... -- T
   * ...this.rest... -- IList<T>
   * METHODS:
   * ...this.foldl(IFunc2<T, U, U> fun, U base)... -- U
   * METHODS ON FIELDS:
   * ...this.rest.foldl(IFunc2<T, U, U> fun, U base)... -- U
   */

  // folds this non-empty list of objects onto the base using the function
  public <U> U foldl(IFunc2<T, U, U> fun, U base) {
    return this.rest.foldl(fun, fun.apply(this.first, base));
  }
}

// To represent an MtList generic list
class MtList<T> implements IList<T> {
  
  /* 
   * METHODS:
   * ...this.foldl(IFunc2<T, U, U> fun, U base)... -- U
   */
  
  // folds this empty list of objects onto the base using the function
  public <U> U foldl(IFunc2<T, U, U> fun, U base) {
    return base;
  }
}

class ExamplesFunc {
  IFunc<Double, Double> sqr = new SquareNum();
  IFunc<Double, Double> sin = new SineNum();
  IFunc<String, String> id = new Identity<String>();
  IFunc<Double, Double> idDouble = new Identity<Double>();
  IFunc<Double, Double> plus1 = new PlusN(1.0);
  IFunc<Double, Double> eq1 = new FunctionComposition<Double, Double, Double>(sin,
      new FunctionComposition<Double, Double, Double>(plus1, sqr));

  IList<IFunc<Double, Double>> eq1List = new ConsList<IFunc<Double, Double>>(sin,
      new ConsList<IFunc<Double, Double>>(plus1,
          new ConsList<IFunc<Double, Double>>(sqr, new MtList<IFunc<Double, Double>>())));

  IFunc2<IFunc<Double, Double>, IFunc<Double, Double>, IFunc<Double, Double>> compose = 
      new ComposeFunctions<Double, Double, Double>();

  // IFunc<Double, Double> foldl = eq1List.foldl(, idDouble);

  public boolean testFun(Tester t) {
    return t.checkExpect(sqr.apply(3.0), 9.0) 
        && t.checkExpect(sin.apply(Math.PI / 2), 1.0)
        && t.checkExpect(id.apply("Echo"), "Echo") 
        && t.checkExpect(plus1.apply(1.0), 2.0)
        && t.checkExpect(eq1.apply(Math.PI / 2), 4.0);
    // && t.checkExpect(eq1List.foldl(compose, idDouble), eq1);
  }
}