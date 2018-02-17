import tester.*;

// Functions with one input and one output

// To represent a function that takes in one value and returns another
interface IFunc<T, U> {
  // Apply this operation to the given object
  U applyTo(T t); 
}

// Takes a Double and squares it
class SquareNum implements IFunc<Double, Double> {
  // Return the square of the given double
  public Double applyTo(Double that) {
    return that * that; 
  }
}

// Takes a double and computes it sine
class SineNum implements IFunc<Double, Double> {
  // Return the sine of the given double
  public Double applyTo(Double that) {
    return Math.sin(that); 
  }
}

// Returns the given object
class Identity<T> implements IFunc<T, T> {
  // Return the given object
  public T applyTo(T that) {
    return that; 
  }
}

// Adds this object's n value to the given double
class PlusN implements IFunc<Double, Double> {
  double n;
  
  PlusN(double n) {
    this.n = n;
  }
  
  // Add n to the value of the given double
  public Double applyTo(Double that) {
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
  
  // Applies func1 and func2 to the given object
  public U applyTo(T t) {
    return func2.applyTo(func1.applyTo(t)); 
  }
}


// Functions with two inputs and one output

interface IFunc2<T, U, V> {
  // Apply this operation to the given object
  V applyTo(T t, U u); 
}


class ComposeFunctions<T, U, V> implements IFunc2<IFunc<T, U>, IFunc<U, V>, IFunc<T, V>> {
  
  // Returns a new IFunc that combines func1 and func2
  // ComposeFunction, instead of being an object with two functions as fields,
  // Is a function object that takes in two objects as parameters
  public IFunc<T, V> applyTo(IFunc<T,U> func1, IFunc<U, V> func2) {
    return new FunctionComposition<T, U, V>(func1, func2); 
  }
}



// To represent an generic list
interface IList<T> {
  // Fold this list of objects onto the base using the function
  <U> U fold(IFunc2<T, U, U> fun, U base);  
}

// To represent a non-empty generic list
class Cons<T> implements IList<T> {
  T  first;
  IList<T> rest;

  Cons(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest; 
  }
  
  // Fold this non-empty list of objects onto the base using the function
  public <U> U fold(IFunc2<T, U, U> fun, U base) {
    return this.rest.fold(fun, fun.applyTo(this.first, base));  
  }
}

// To represent an empty generic list
class Empty<T> implements IList<T> {
  public <U> U fold(IFunc2<T, U, U> fun, U base) {
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
  
  IList<IFunc<Double, Double>> eq1List = new Cons<IFunc<Double, Double>>(sin,
      new Cons<IFunc<Double, Double>>(plus1,
          new Cons<IFunc<Double, Double>>(sqr, new Empty<IFunc<Double, Double>>())));
  
  
  IFunc2<IFunc<Double, Double>, IFunc<Double, Double>, IFunc<Double, Double>> compose = 
      new ComposeFunctions<Double, Double, Double>(); 
  
  //IFunc<Double, Double> fold1 = eq1List.fold(, idDouble); 
  
  public boolean testFun(Tester t) {
    return t.checkExpect(sqr.applyTo(3.0), 9.0)
    && t.checkExpect(sin.applyTo(Math.PI/2), 1.0)
    && t.checkExpect(id.applyTo("Echo"), "Echo")
    && t.checkExpect(plus1.applyTo(1.0), 2.0)
    && t.checkExpect(eq1.applyTo(Math.PI/2), 4.0)
    && t.checkExpect(eq1List.fold(compose, idDouble), eq1); 
  }
}

